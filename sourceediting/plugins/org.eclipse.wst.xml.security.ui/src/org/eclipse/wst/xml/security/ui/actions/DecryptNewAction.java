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

import java.io.FileOutputStream;

import org.apache.xml.security.utils.XMLUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.decrypt.CreateDecryption;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.eclipse.wst.xml.security.ui.decrypt.NewDecryptionWizard;
import org.w3c.dom.Document;

/**
 * <p>Action class used to start the <b>XML Decryption Wizard</b> for a new decryption in the selected
 * XML document. The decryption process differs depending on whether editor content or a file via a
 * view should be decrypted.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class DecryptNewAction extends XmlSecurityActionAdapter {
    /** Active editor. */
    private ITextEditor editor = null;
    /** The XML document to decrypt. */
    private IFile file = null;
    /** Error message for the log. */
    private static final String ERROR_TEXT = "An error occured during decryption"; //$NON-NLS-1$
    /** Action type. */
    private static final String ACTION = "decrypt";

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
     * Called when clicked on the <i>New Decryption...</i> entry in the plug-ins context menu.
     *
     * @param action The IAction
     */
    public void run(final IAction action) {
        createDecryption();
    }

    /**
     * Takes the resource (selected file or editor content) and starts the XML Decryption Wizard.
     * The returned decrypted XML document is pretty printed before the editor or the file is
     * updated.
     */
    private void createDecryption() {
        try {
            NewDecryptionWizard wizard = new NewDecryptionWizard();
            CreateDecryption decryption = new CreateDecryption();
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

                if (file != null) {
                    wizard.init(file);

                    decryptData(decryption, wizard, document, "");
                } else {
                    showInfo(Messages.decryptionImpossible, NLS.bind(Messages.protectedDoc, ACTION));
                }
            } else if (file != null && file.isAccessible() && !file.isReadOnly()) { // call in view
                wizard.init(file);

                decryptData(decryption, wizard, null, file.getLocation().toString());
            } else {
                showInfo(Messages.decryptionImpossible, NLS.bind(Messages.protectedDoc, ACTION));
            }
        } catch (Exception ex) {
            showErrorDialog(Messages.error, Messages.decryptingError, ex);
            log(ERROR_TEXT, ex);
        }
    }

    /**
     * Called when decrypting an XML resource inside an opened editor or via a view. The
     * output XML can not be pretty printed since this would break an existing XML
     * signature in the document.
     *
     * @param data The resource to decrypt
     * @param wizard The Decryption Wizard
     * @param document The document to decrypt, null if a file is decrypted directly
     * @param filename The filename, empty if editor content is decrypted
     * @throws Exception to indicate any exceptional condition
     */
    private void decryptData(final CreateDecryption data, final NewDecryptionWizard wizard,
            final IDocument document, final String filename) throws Exception {
        WizardDialog dialog = new WizardDialog(getShell(), wizard);
        dialog.create();
        dialog.open();

        if (dialog.getReturnCode() == Dialog.OK && wizard.getModel() != null) {
            Job job = new Job("XML Decryption") {
                public IStatus run(final IProgressMonitor monitor) {
                    try {
                        monitor.beginTask(Messages.decryptionTaskInfo, 6);

                        Document doc = data.decrypt(wizard.getModel(), monitor);

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
                                showErrorDialog(Messages.error, Messages.decryptingError, ex);
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
}
