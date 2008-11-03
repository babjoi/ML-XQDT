/*******************************************************************************
 * Copyright (c) 2008 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.actions;

import java.io.FileOutputStream;

import org.apache.xml.security.utils.XMLUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.encrypt.CreateEncryption;
import org.eclipse.wst.xml.security.core.encrypt.NewEncryptionWizard;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.w3c.dom.Document;

/**
 * <p>Action class used to start the <b>Encryption Wizard</b> for a new encryption
 * in the selected XML document. The encryption process differs depending on
 * whether editor content or a file via a view should be encrypted.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class EncryptNewAction extends XmlSecurityActionAdapter {
    /** Active editor. */
    private ITextEditor editor = null;
    /** The file to encrypt. */
    private IFile file = null;
    /** Selected text in the editor. */
    private ITextSelection textSelection = null;
    /** Error message for the logfile. */
    private static final String ERROR_TEXT = "An error occured during encryption"; //$NON-NLS-1$
    /** Action type. */
    private static final String ACTION = "encrypt";

    /**
     * Called when the selection in the active workbench part changes.
     *
     * @param action The executed action
     * @param selection The selection
     */
    public void selectionChanged(final IAction action, final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            file = (IFile) ((IStructuredSelection) selection).getFirstElement();
        }
    }

    /**
     * Called when clicked on the <i>New Encryption...</i> entry in the
     * plug-ins context menu.
     *
     * @param action The IAction
     */
    public void run(final IAction action) {
        createEncryption();
    }

    /**
     * Takes the resource (selected file or editor content) and starts the XML
     * Encryption Wizard. The returned encrypted XML document is pretty printed
     * before the editor or the file is updated.
     */
    private void createEncryption() {
        try {
            final NewEncryptionWizard encWizard = new NewEncryptionWizard();

            IWorkbenchPart workbenchPart = getWorkbenchPart();

            if (workbenchPart != null && workbenchPart instanceof ITextEditor) {
                editor = (ITextEditor) workbenchPart;
            }

            if (editor != null && editor.isEditable()) { // call in editor
                if (editor.isDirty()) {
                    saveEditorContent(editor);
                }

                IEditorInput input = editor.getEditorInput();
                file = (IFile) input.getAdapter(IFile.class);

                IDocument document = editor.getDocumentProvider().getDocument(input);
                textSelection = (ITextSelection) editor.getSelectionProvider().getSelection();
                boolean validSelection = parseSelection(textSelection.getText());

                if (file != null) {
                    IProject project = file.getProject();

                    if (validSelection) { // with text selection
                        encWizard.init(project, file, textSelection);
                    } else { // without text selection
                        encWizard.init(project, file);
                    }

                    CreateEncryption encryption = new CreateEncryption();
                    encryptData(encryption, encWizard, document, "");
                    if (encWizard.getModel().getLaunchSignatureWizard()) {
                        callDigitalSignatureWizard();
                    }
                } else {
                    showInfo(Messages.encryptionImpossible, NLS.bind(Messages.protectedDoc, ACTION));
                }
            } else if (file != null && file.isAccessible() && !file.isReadOnly()) { // call in view
                IProject project = file.getProject();
                encWizard.init(project, file);

                CreateEncryption encryption = new CreateEncryption();
                encryptData(encryption, encWizard, null, file.getLocation().toString());
                project.refreshLocal(IProject.DEPTH_INFINITE, null);
                if (encWizard.getModel().getLaunchSignatureWizard()) {
                    callDigitalSignatureWizard();
                }
            } else {
                showInfo(Messages.encryptionImpossible, NLS.bind(Messages.protectedDoc, ACTION));
            }
        } catch (Exception ex) {
            showErrorDialog(Messages.error, Messages.encryptingError, ex);
            log(ERROR_TEXT, ex);
        }
    }

    /**
     * Called when encrypting an XML resource inside an opened editor (with or
     * without a text selection) or via a view.
     *
     * @param data The resource to encrypt
     * @param wizard The Encryption Wizard
     * @param document The document to encrypt, null if a file is encrypted directly
     * @param filename The filename, empty if editor content is encrypted
     * @throws Exception to indicate any exceptional condition
     */
    private void encryptData(final CreateEncryption data, final NewEncryptionWizard wizard,
        final IDocument document, final String filename) throws Exception {
        WizardDialog dialog = new WizardDialog(getShell(), wizard);
        dialog.create();
        dialog.open();

        if (dialog.getReturnCode() == 0 && wizard.getModel() != null) {
            Job job = new Job("XML Encryption") {
                public IStatus run(final IProgressMonitor monitor) {
                    try {
                        monitor.beginTask(Messages.encryptionTaskInfo, 3);

                        Document doc = data.encrypt(wizard.getModel(), textSelection.getText(), monitor);

                        if (monitor.isCanceled()) {
                            return Status.CANCEL_STATUS;
                        }

                        if (doc != null) {
                            if (document != null) {
                                document.set(Utils.docToString(doc, true));
                            } else {
                                FileOutputStream fos = new FileOutputStream(filename);
                                XMLUtils.outputDOM(doc, fos);
                                fos.flush();
                                fos.close();
                            }
                        }
                    } catch (Exception ex) {
                    	ex.printStackTrace();
                        showErrorDialog(Messages.error, Messages.encryptingError, ex);
                        log(ERROR_TEXT, ex);
                    } finally {
                        monitor.done();
                    }

                    return Status.OK_STATUS;
                }
            };
            job.setUser(true);
            job.schedule();
        }

        dialog.close();
        wizard.dispose();
    }

    /**
     * Calls the <i>XML Digital Signature Wizard</i> after successfully encrypting the
     * selected resource if the user selected the checkbox in the
     * <i>XML Encryption Wizard</i>.
     */
    private void callDigitalSignatureWizard() {
        SignNewAction sign = new SignNewAction();
        sign.signAfterEncryption(file);
    }

    /**
     * Encrypts the given XML document after successfully signing it.
     *
     * @param signedFile The signed file, now used to encrypt
     */
    public void encryptAfterSignature(final IFile signedFile) {
        this.file = signedFile;

        createEncryption();
    }
}
