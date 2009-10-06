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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.wst.xml.security.core.cryptography.Keystore;
import org.eclipse.wst.xml.security.core.decrypt.CreateDecryption;
import org.eclipse.wst.xml.security.core.decrypt.Decryption;
import org.eclipse.wst.xml.security.core.utils.Globals;
import org.eclipse.wst.xml.security.ui.XSTUIPlugin;
import org.eclipse.wst.xml.security.ui.dialogs.MissingPreferenceDialog;
import org.eclipse.wst.xml.security.ui.dialogs.PasswordDialog;
import org.eclipse.wst.xml.security.ui.preferences.PreferenceConstants;
import org.eclipse.wst.xml.security.ui.utils.Utils;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

/**
 * <p>Command used to start the decryption of the selected XML document (fragment) with predefined
 * settings defined in the preferences (Quick Decryption).</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class QuickDecryptionCommand extends AbstractHandler {
    /** The file to decrypt. */
    private IFile file = null;
    /** Keystore. */
    private Keystore keystore;
    /** Keystore password. */
    private char[] keystorePassword;
    /** Key name. */
    private String keyName;
    /** Key password. */
    private char[] keyPassword;
    /** Encryption ID. */
    private String encryptionId;
    /** Key file. */
    private String keyFile;
    /** All necessary preferences are available. */
    private boolean completePrefs = false;
    private ExecutionEvent event;

    public Object execute(ExecutionEvent event) throws ExecutionException {
        this.event = event;

        getPreferenceValues();

        if (checkPreferences()) {
            createDecryption();
        }

        return null;
    }

    private void createDecryption() {
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
                        PlatformUI.getWorkbench().getProgressService().runInUI(XSTUIPlugin.getActiveWorkbenchWindow(),
                                op, ResourcesPlugin.getWorkspace().getRoot());
                    } catch (InvocationTargetException ite) {
                        Utils.log("Error while saving editor content", ite); //$NON-NLS-1$
                    } catch (InterruptedException ie) {
                        Utils.log("Error while saving editor content", ie); //$NON-NLS-1$
                    }
                } else {
                    editorPart.doSaveAs();
                }
            }

            file = (IFile) editorPart.getEditorInput().getAdapter(IFile.class);
            document = (IDocument) editorPart.getAdapter(IDocument.class);
        } else {
            ISelection selection = HandlerUtil.getCurrentSelection(event);
            if (selection instanceof IStructuredSelection) {
                file = (IFile) ((IStructuredSelection) selection).getFirstElement();
            }
        }

        // Ask the user for the passwords
        PasswordDialog keystorePasswordDialog = new PasswordDialog(HandlerUtil.getActiveShell(event),
                "Keystore Password", "Enter the keystore password.", "");
        if (keystorePasswordDialog.open() == Dialog.OK) {
            keystorePassword = keystorePasswordDialog.getValue().toCharArray();
        } else {
            return;
        }

        PasswordDialog privateKeyPasswordDialog = new PasswordDialog(HandlerUtil.getActiveShell(event),
                "Key Password", "Enter the key password.", "");
        if (privateKeyPasswordDialog.open() == Dialog.OK) {
            keyPassword = privateKeyPasswordDialog.getValue().toCharArray();
        } else {
            return;
        }

        if (checkPasswords()) {
            try {
                if (loadKeystore()) {
                    if (file != null && file.isAccessible()) {
                        decryptData(document);
                    } else {
                        MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "XML Decryption",
                                NLS.bind(Messages.RemoveReadOnlyFlag, "decrypt"));
                    }
                } else {
                    MessageDialog.openError(HandlerUtil.getActiveShell(event), "XML Decryption",
                            "An error occurred during key recovery. Verify all key information (name and passwords) and try again.");
                }
            } catch (SAXParseException spe) {
                Utils.showErrorDialog(HandlerUtil.getActiveShell(event), "XML Decryption",
                        "Could not decrypt the document because of a parsing error.", spe);
            } catch (FileNotFoundException fnfe) {
                MessageDialog.openError(HandlerUtil.getActiveShell(event), "XML Decryption",
                        "Could not find the selected keystore.");
            } catch (IOException ioe) {
                Utils.showErrorDialog(HandlerUtil.getActiveShell(event), "XML Decryption",
                        "An error occurred while trying to use the selected keystore.", ioe);
            } catch (Exception ex) {
                Utils.showErrorDialog(HandlerUtil.getActiveShell(event), "XML Decryption",
                        "An error occurred during decrypting the XML document.", ex);
                Utils.log("An error occured during quick decrypting", ex); //$NON-NLS-1$
            }
        }
    }

    private void decryptData(final IDocument document) {
        final CreateDecryption decryption = new CreateDecryption();
        final Decryption data = new Decryption();
        data.setKeystore(keystore);
        data.setKeystorePassword(keystorePassword);
        data.setKeyName(keyName);
        data.setKeyPassword(keyPassword);
        data.setEncryptionId(encryptionId);
        data.setFile(file.getLocation().toString());

        Job job = new Job("XML Decryption") {
            public IStatus run(final IProgressMonitor monitor) {
                try {
                    monitor.beginTask("Creating a new Quick XML Decryption...", 5);

                    Document doc = null;

                    doc = decryption.decrypt(data, monitor);

                    if (monitor.isCanceled()) {
                        return Status.CANCEL_STATUS;
                    }

                    if (doc != null) {
                        if (document != null) {
                            document.set(org.eclipse.wst.xml.security.core.utils.Utils.docToString(doc, false));
                        } else {
                            FileOutputStream fos = new FileOutputStream(file.getLocation().toOSString());
                            XMLUtils.outputDOM(doc, fos);
                            fos.flush();
                            fos.close();
                        }
                    }
                } catch (final Exception ex) {
                    HandlerUtil.getActiveShell(event).getDisplay().asyncExec(new Runnable() {
                        public void run() {
                            Utils.showErrorDialog(HandlerUtil.getActiveShell(event), "XML Decryption",
                                    "An error occurred during decrypting the XML document.", ex);
                            Utils.log("An error occured during decrypting", ex); //$NON-NLS-1$
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

    /**
     * Loads the entered key in the selected keystore.
     *
     * @return Keystore/ key information correct or not
     *
     * @throws Exception to indicate any exceptional condition
     */
    private boolean loadKeystore() throws Exception {
        try {
            keystore = new Keystore(keyFile, keystorePassword.toString(), Globals.KEYSTORE_TYPE);
            keystore.load();

            if (!keystore.containsKey(keyName)) {
                return false;
            }

            if (keystore.getSecretKey(keyName, keyPassword) == null) {
                return false;
            }

            return true;
        } catch (Exception ex) {
            Utils.log("An error occured during quick encryption", ex); //$NON-NLS-1$
            return false;
        }
    }

    /**
     * Determines the preference values for <i>Quick Decryption</i>.
     */
    private void getPreferenceValues() {
        IPreferenceStore store = XSTUIPlugin.getDefault().getPreferenceStore();

        keyFile = store.getString(PreferenceConstants.ENCRYPT_KEY_STORE);
        keyName = store.getString(PreferenceConstants.ENCRYPT_KEY_NAME);
        encryptionId = store.getString(PreferenceConstants.ENCRYPT_ID);
    }

    /**
     * Checks if the preferences contain all necessary information. Shows a dialog with a warning
     * message and a link to the preference page.<br/> If the preference dialog was closed with the
     * OK button the necessary preference settings are automatically verified again.
     *
     * @return Preferences OK or not
     */
    private boolean checkPreferences() {
        final String title = "XML Decryption";
        final String prefId = "org.eclipse.wst.xml.security.ui.preferences.Encryption";
        int result = 2;

        if (keystore == null || keystore.equals("")) {
            result = showMissingParameterDialog(title, NLS.bind(Messages.MissingParameter,
                    "keystore"), prefId);
        } else if (keyName == null || keyName.equals("")) {
            result = showMissingParameterDialog(title, NLS.bind(Messages.MissingParameter,
                    "key name"), prefId);
        } else {
            completePrefs = true;
        }

        if (result == 0) {
            completePrefs = false;
            getPreferenceValues();
            checkPreferences();
        }

        return completePrefs;
    }

    /**
     * Checks the entered passwords for the Keystore and key.
     *
     * @return Password is OK or not
     */
    private boolean checkPasswords() {
        if (keystorePassword == null || keystorePassword.length == 0) {
            MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "XML Decryption", "Enter the keystore password.");
            return false;
        } else if (keyPassword == null || keyPassword.length == 0) {
            MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "XML Decryption", "Enter the key password.");
            return false;
        }

        return true;
    }

    /**
     * Shows a dialog with a message for a missing preference parameter.
     *
     * @param title The title of the message box
     * @param message The message to display
     * @param prefId The preference page id to show
     * @return The clicked button in the preferences dialog
     */
    private int showMissingParameterDialog(final String title, final String message, final String prefId) {
        MissingPreferenceDialog dialog = new MissingPreferenceDialog(HandlerUtil.getActiveShell(event), title, message, prefId);
        return dialog.open();
    }

}
