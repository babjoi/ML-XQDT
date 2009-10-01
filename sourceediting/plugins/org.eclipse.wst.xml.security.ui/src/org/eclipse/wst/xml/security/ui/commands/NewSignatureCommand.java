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
package org.eclipse.wst.xml.security.ui.commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.xml.security.utils.XMLUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.sign.CreateSignature;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.eclipse.wst.xml.security.ui.XSTUIPlugin;
import org.eclipse.wst.xml.security.ui.actions.EncryptNewAction;
import org.eclipse.wst.xml.security.ui.sign.NewSignatureWizard;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

/**
 * <p>Command used to start the <b>XML Signature</b> wizard for a new XML Signature for the selected XML document. 
 * The signature process differs depending on whether editor content or a file via a view should be signed.</p>
 * 
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class NewSignatureCommand extends AbstractHandler {
    /** Selected text in the editor. */
    private ITextSelection textSelection = null;
    private ExecutionEvent event;
    /** The file to sign. */
    private IFile file = null;

    public Object execute(ExecutionEvent event) throws ExecutionException {
        this.event = event;

        createSignature();

        return null;
    }

    /**
     * Takes the resource (selected file or editor content) and starts the XML Signature Wizard. The returned signed XML
     * document is not pretty printed because this would change the hash value of the signed content and verification
     * would fail.
     */
    private void createSignature() {
        try {
            NewSignatureWizard wizard = new NewSignatureWizard();
            IFile file = null;
            IDocument document = null;

            if (HandlerUtil.getActivePart(event) instanceof IEditorPart) {
                final IEditorPart editorPart = (IEditorPart) HandlerUtil.getActivePart(event);

                if (editorPart.isDirty()) {
                    if (null != editorPart.getTitle() && editorPart.getTitle().length() > 0) {
                        IRunnableWithProgress op = new IRunnableWithProgress() {
                            public void run(final IProgressMonitor monitor) {
                                editorPart.doSave(monitor);
                            }
                        };
                        try {
                            PlatformUI.getWorkbench().getProgressService().runInUI(XSTUIPlugin.getActiveWorkbenchWindow(), op, ResourcesPlugin.getWorkspace().getRoot());
                        } catch (InvocationTargetException ite) {
                            log("Error while saving editor content", ite); //$NON-NLS-1$
                        } catch (InterruptedException ie) {
                            log("Error while saving editor content", ie); //$NON-NLS-1$
                        }
                    } else {
                        editorPart.doSaveAs();
                    }
                }

                textSelection = (ITextSelection) ((ITextEditor) editorPart.getAdapter(ITextEditor.class)).getSelectionProvider().getSelection();
                file = (IFile) editorPart.getEditorInput().getAdapter(IFile.class);
                document = (IDocument) editorPart.getAdapter(IDocument.class);
            } else {
                textSelection = null;
                ISelection selection = HandlerUtil.getCurrentSelection(event);
                if (selection instanceof IStructuredSelection) {
                    file = (IFile) ((IStructuredSelection) selection).getFirstElement();
                }
            }

            if (file != null) {
                if (textSelection != null && Utils.parseSelection(textSelection.getText())) {
                    // with valid text selection
                    wizard.init(file, textSelection);
                } else {
                    // without text selection (or invalid)
                    wizard.init(file);
                }

                CreateSignature signature = new CreateSignature();
                String fileLocation = "";

                if (document == null) {
                    fileLocation = file.getLocation().toString();
                }

                signData(signature, wizard, document, fileLocation);

                if (wizard.getModel().getLaunchEncryptionWizard()) {
                    callEncryptionWizard();
                }
            } else {
                MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "XML Signature", NLS.bind("It is impossible to {0} the selected document. Please remove the read-only-flag and try again.", "sign"));
            }
        } catch (SAXParseException spe) {
            showErrorDialog("XML Signature", "Could not sign the document because of a parsing error.", spe);
        } catch (FileNotFoundException fnfe) {
            MessageDialog.openError(HandlerUtil.getActiveShell(event), "XML Signature", "Could not find the selected keystore.");
        } catch (IOException ioe) {
            showErrorDialog("XML Signature", "An error occurred while trying to use the selected keystore.", ioe);
        } catch (Exception ex) {
            showErrorDialog("XML Signature", "An error occurred during signing the XML document.", ex);
            log("An error occured during signing", ex);
        }
    }

    /**
     * Shows an error dialog with an details button for detailed error information.
     * 
     * @param title The title of the message box
     * @param message The message to display
     * @param ex The exception
     */
    private void showErrorDialog(final String title, final String message, final Exception ex) {
        String reason = ex.getMessage();
        if (reason == null || "".equals(reason)) {
            reason = "Error reason not available.";
        }

        IStatus status = new Status(IStatus.ERROR, XSTUIPlugin.getDefault().getBundle().getSymbolicName(), 0, reason, ex);

        ErrorDialog.openError(HandlerUtil.getActiveShell(event), title, message, status);
    }

    private void log(final String message, final Exception ex) {
        IStatus status = new Status(IStatus.ERROR, XSTUIPlugin.getDefault().getBundle().getSymbolicName(), 0, message, ex);
        XSTUIPlugin.getDefault().getLog().log(status);
    }

    /**
     * Signing an XML resource inside an opened editor (with or without a text selection) or via a view.
     * 
     * @param data The resource to sign
     * @param wizard The Signature Wizard
     * @param document The document to sign, null if a file is signed directly
     * @param filename The filename, empty if editor content is signed
     * @throws Exception to indicate any exceptional condition
     */
    private void signData(final CreateSignature data, final NewSignatureWizard wizard, final IDocument document, final String filename)
            throws Exception {
        WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
        dialog.create();
        dialog.open();

        if (dialog.getReturnCode() == Dialog.OK && wizard.getModel() != null) {
            Job job = new Job("XML Signature") {
                public IStatus run(final IProgressMonitor monitor) {
                    try {
                        monitor.beginTask("Creating a new XML Signature...", 5);

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
                        HandlerUtil.getActiveShell(event).getDisplay().asyncExec(new Runnable() {
                            public void run() {
                                showErrorDialog("XML Signature", "An error occurred during signing the XML document.", ex);
                                log("An error occured during signing", ex);
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
     * Calls the <i>XML Encryption Wizard</i> after successfully signing the selected resource if the user selected the
     * checkbox in the <i>XML Signature Wizard</i>.
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
