/*******************************************************************************
 * Copyright (c) 2009 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.ui.actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.xml.security.utils.XMLUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.sign.CreateSignature;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.eclipse.wst.xml.security.ui.sign.NewSignatureWizard;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

/**
 * <p>Action class used to start the <b>XML Signature</b> wizard for a new XML
 * Signature in the selected XML document. The signature process differs depending on
 * whether editor content or a file via a view should be signed.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class SignNewAction extends XmlSecurityActionAdapter {
    /** Active editor. */
    private ITextEditor editor = null;
    /** The file to sign. */
    private IFile file = null;
    /** Selected text in the editor. */
    private ITextSelection textSelection = null;
    /** Error message for the log file. */
    private static final String ERROR_TEXT = "An error occured during signing"; //$NON-NLS-1$
    /** Action type. */
    private static final String ACTION = "sign";

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
     * Called when clicked on the <i>New Signature...</i> entry in the
     * plug-ins context menu.
     *
     * @param action The IAction
     */
    public void run(final IAction action) {
        createSignature();
    }

    /**
     * Takes the resource (selected file or editor content) and starts the XML
     * Signature Wizard. The returned signed XML document is not pretty
     * printed because this would change the hash value of the signed content
     * and verification would fail.
     */
    private void createSignature() {
        try {
            final NewSignatureWizard sigWizard = new NewSignatureWizard();

            IWorkbenchPart workbenchPart = getWorkbenchPart();

            if (workbenchPart != null && workbenchPart instanceof ITextEditor) {
                editor = (ITextEditor) workbenchPart;
            } else {
                editor = null;
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
                    if (validSelection) { // with text selection
                        sigWizard.init(file, textSelection);
                    } else { // without text selection
                        sigWizard.init(file);
                    }

                    CreateSignature signature = new CreateSignature();
                    signData(signature, sigWizard, document, "");

                    if (sigWizard.getModel().getLaunchEncryptionWizard()) {
                        callEncryptionWizard();
                    }
                } else {
                    showInfo(Messages.signatureImpossible, NLS.bind(Messages.protectedDoc, ACTION));
                }
            } else if (file != null && file.isAccessible() && !file.isReadOnly()) { // call in view
                sigWizard.init(file);

                CreateSignature signature = new CreateSignature();
                signData(signature, sigWizard, null, file.getLocation().toString());
                if (sigWizard.getModel().getLaunchEncryptionWizard()) {
                    callEncryptionWizard();
                }
            } else {
                showInfo(Messages.signatureImpossible, NLS.bind(Messages.protectedDoc, ACTION));
            }
        } catch (SAXParseException spe) {
            showError(Messages.parsingError, Messages.parsingErrorText + spe.getLocalizedMessage());
        } catch (FileNotFoundException fnfe) {
            showError(Messages.keystore, Messages.keystoreNotFound);
        } catch (IOException ioe) {
            showError(Messages.keystore, Messages.keystoreError + ioe.getLocalizedMessage());
        } catch (Exception ex) {
            showErrorDialog(Messages.error, Messages.signingError, ex);
            log(ERROR_TEXT, ex);
        }
    }

    /**
     * Called when signing an XML resource inside an opened editor (with or
     * without a text selection) or via a view.
     *
     * @param data The resource to sign
     * @param wizard The Signature Wizard
     * @param document The document to sign, null if a file is signed directly
     * @param filename The filename, empty if editor content is signed
     * @throws Exception to indicate any exceptional condition
     */
    private void signData(final CreateSignature data, final NewSignatureWizard wizard,
        final IDocument document, final String filename) throws Exception {
        WizardDialog dialog = new WizardDialog(getShell(), wizard);
        dialog.create();
        dialog.open();

        if (dialog.getReturnCode() == Dialog.OK && wizard.getModel() != null) {
            Job job = new Job("XML Signature") {
                public IStatus run(final IProgressMonitor monitor) {
                    try {
                        monitor.beginTask(Messages.signatureTaskInfo, 5);

                        Document doc = data.sign(wizard.getModel(), textSelection.getText(), monitor);

                        if (monitor.isCanceled()) {
                            return Status.CANCEL_STATUS;
                        }

                        if (doc != null) {
                            if (document != null) {
                                document.set(Utils.docToString(doc, false));
                            } else {
                                FileOutputStream fos = new FileOutputStream(filename);
                                XMLUtils.outputDOM(doc, fos);
                                fos.flush();
                                fos.close();
                            }
                        }
                    } catch (final Exception ex) {
                        getShell().getDisplay().asyncExec(new Runnable() {
                            public void run() {
                                showErrorDialog(Messages.error, Messages.signingError, ex);
                                log(ERROR_TEXT, ex);
                            }
                        });
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
     * Calls the <i>XML Encryption Wizard</i> after successfully signing the
     * selected resource if the user selected the checkbox in the
     * <i>XML Signature Wizard</i>.
     */
    private void callEncryptionWizard() {
        EncryptNewAction encrypt = new EncryptNewAction();
        encrypt.encryptAfterSignature(file);
    }

    /**
     * Signs the given XML document after successfully encrypting it.
     *
     * @param encryptedFile The encrypted file, now used to sign
     */
    public void signAfterEncryption(final IFile encryptedFile) {
        this.file = encryptedFile;

        createSignature();
    }
}
