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
package org.eclipse.wst.xml.security.core.actions;

import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;
import org.eclipse.wst.xml.security.core.cryptography.Keystore;
import org.eclipse.wst.xml.security.core.encrypt.CreateEncryption;
import org.eclipse.wst.xml.security.core.encrypt.Encryption;
import org.eclipse.wst.xml.security.core.preferences.PreferenceConstants;
import org.eclipse.wst.xml.security.core.utils.Globals;
import org.eclipse.wst.xml.security.core.utils.MissingPreferenceDialog;
import org.eclipse.wst.xml.security.core.utils.PasswordDialog;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.w3c.dom.Document;

/**
 * <p>Action class used to start the encryption of the selected XML document (fragment) with predefined
 * settings defined in the preferences (Quick Encryption).</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class EncryptQuickAction extends XmlSecurityActionAdapter {
    /** Active editor. */
    private ITextEditor editor = null;
    /** The file to encrypt. */
    private IFile file = null;
    /** Document (-fragment) to encrypt. */
    private String resource;
    /** XPath expression to encrypt. */
    private String xpath = "";
    /** Encryption type. */
    private String encryptionType;
    /** Key wrap algorithm. */
    private String keyWrapAlgorithm;
    /** Encryption algorithm. */
    private String encryptionAlgorithm;
    /** Keystore password. */
    private char[] keystorePassword;
    /** Key file. */
    private String keyFile;
    /** Keystore. */
    private Keystore keystore;
    /** Key name. */
    private String keyName;
    /** Key password. */
    private char[] keyPassword;
    /** Encryption ID. */
    private String encryptionId;
    /** All necessary preferences are available. */
    private boolean completePrefs = false;
    /** Error message for the log file. */
    private static final String ERROR_TEXT = "An error occured during quick encryption"; //$NON-NLS-1$
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
     * Takes the selected file, selection or editor content and starts the XML Encryption Wizard.
     * The encrypted XML document is pretty printed before the editor or the file is updated.<br/>
     * Before any operation on the XML, the preference store is searched for the necessary settings.
     * Then these settings are verified. If the settings are valid the encrypt operation begins. If
     * not the user has the possibility to provide valid information.
     *
     * @param action The IAction
     */
    public void run(final IAction action) {
        getPreferenceValues();

        if (checkPreferences()) {
            // Ask the user for the passwords
            PasswordDialog keystorePasswordDialog = new PasswordDialog(getShell(),
                    Messages.keystorePassword, Messages.enterKeystorePassword, ""); //$NON-NLS-3$
            if (keystorePasswordDialog.open() == Dialog.OK) {
                keystorePassword = keystorePasswordDialog.getValue().toCharArray();
            } else {
                return;
            }

            PasswordDialog privateKeyPasswordDialog = new PasswordDialog(getShell(),
                    Messages.keyPassword, Messages.enterKeyPassword, ""); //$NON-NLS-3$
            if (privateKeyPasswordDialog.open() == Dialog.OK) {
                keyPassword = privateKeyPasswordDialog.getValue().toCharArray();
            } else {
                return;
            }

            if (checkPasswords()) {
                try {
                    if (loadKeystore()) {
                        encryptData();
                    } else {
                        showError(Messages.error, Messages.failedLoadingKeystore);
                    }
                } catch (Exception ex) {
                    showErrorDialog(Messages.error, Messages.encryptingError, ex);
                    log(ERROR_TEXT, ex);
                }
            }
        }
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
            log(ERROR_TEXT, ex);
            return false;
        }
    }

    /**
     * Determines the preference values for <i>Quick Encryption</i>.
     */
    private void getPreferenceValues() {
        IPreferenceStore store = XmlSecurityPlugin.getDefault().getPreferenceStore();

        resource = store.getString(PreferenceConstants.ENCRYPT_RESOURCE);
        encryptionType = store.getString(PreferenceConstants.ENCRYPT_TYPE);
        keyWrapAlgorithm = store.getString(PreferenceConstants.ENCRYPT_KEY_WRAP);
        encryptionAlgorithm = store.getString(PreferenceConstants.ENCRYPT_ENCRYPTION);
        keyName = store.getString(PreferenceConstants.ENCRYPT_KEY_NAME);
        keyFile = store.getString(PreferenceConstants.ENCRYPT_KEY_STORE);
        encryptionId = store.getString(PreferenceConstants.ENCRYPT_ID);

        if (resource != null && "xpath".equals(resource)) {
            xpath = store.getString(PreferenceConstants.ENCRYPT_XPATH);
        }
    }

    /**
     * Encrypts the XML document with the stored settings.
     *
     * @throws Exception to indicate any exceptional condition
     */
    private void encryptData() throws Exception {
        final Encryption encryption = new Encryption();
        encryption.setResource(resource);
        encryption.setEncryptionType(encryptionType);
        encryption.setXpath(xpath);
        encryption.setContent(false);
        encryption.setBsp(false);
        encryption.setKeyWrapAlgorithm(keyWrapAlgorithm);
        encryption.setEncryptionAlgorithm(encryptionAlgorithm);
        encryption.setKeystore(keystore);
        encryption.setKeystorePassword(keystorePassword);
        encryption.setKeyName(keyName);
        encryption.setKeyPassword(keyPassword);
        encryption.setEncryptionId(encryptionId);

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

            if (file != null) {
                final IDocument document = editor.getDocumentProvider().getDocument(input);
                encryption.setFile(file.getLocation().toString());

                IRunnableWithProgress op = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask(Messages.encryptionTaskInfo, 3);

                            CreateEncryption content = new CreateEncryption();
                            Document doc = null;

                            if ("selection".equals(resource)) {
                                ITextSelection textSelection = (ITextSelection) editor.getSelectionProvider().getSelection();

                                if (textSelection != null && !textSelection.isEmpty()
                                    && textSelection.getLength() > 0 && parseSelection(textSelection.getText())) {
                                    doc = content.encrypt(encryption, textSelection.getText(), monitor);
                                } else {
                                    getShell().getDisplay().asyncExec(new Runnable() {
                                        public void run() {
                                            showInfo(Messages.invalidTextSelection, Messages.invalidTextSelectionText);
                                        }
                                    });
                                }
                            } else {
                                doc = content.encrypt(encryption, null, monitor);
                            }

                            if (doc != null) {
                                document.set(Utils.docToString(doc, true));
                            }

                            monitor.done();
                        } catch (final Exception ex) {
                            getShell().getDisplay().asyncExec(new Runnable() {
                                public void run() {
                                    showErrorDialog(Messages.error, Messages.encryptingError, ex);
                                    log(ERROR_TEXT, ex);
                                }
                            });
                        }
                    }
                };
                try {
                    PlatformUI.getWorkbench().getProgressService().runInUI(
                            XmlSecurityPlugin.getActiveWorkbenchWindow(), op,
                            XmlSecurityPlugin.getWorkspace().getRoot());
                } catch (InvocationTargetException ite) {
                    log(ERROR_TEXT, ite);
                } catch (InterruptedException ie) {
                    log(ERROR_TEXT, ie);
                }
            } else {
                showInfo(Messages.quickEncryptionImpossible, NLS.bind(Messages.protectedDoc, ACTION));
            }
        } else if (file != null && file.isAccessible() && !file.isReadOnly()) { // call in view
            IProject project = file.getProject();
            if ("selection".equals(resource)) { //$NON-NLS-1$
                showInfo(Messages.quickEncryptionImpossible, Messages.quickEncryptionImpossibleText);
            } else {
                final String filename = file.getLocation().toString();
                encryption.setFile(filename);

                IRunnableWithProgress op = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask(Messages.encryptionTaskInfo, 3);

                            CreateEncryption content = new CreateEncryption();
                            Document doc = content.encrypt(encryption, null, monitor);

                            if (doc != null) {
                                FileWriter fw = new FileWriter(filename);
                                fw.write(Utils.docToString(doc, true));
                                fw.flush();
                                fw.close();
                            }

                            monitor.done();
                        } catch (final Exception ex) {
                            getShell().getDisplay().asyncExec(new Runnable() {
                                public void run() {
                                    showErrorDialog(Messages.error, Messages.encryptingError, ex);
                                    log(ERROR_TEXT, ex);
                                }
                            });
                        }
                    }
                };
                try {
                    PlatformUI.getWorkbench().getProgressService().runInUI(
                            XmlSecurityPlugin.getActiveWorkbenchWindow(), op,
                            XmlSecurityPlugin.getWorkspace().getRoot());
                } catch (InvocationTargetException ite) {
                    log(ERROR_TEXT, ite);
                } catch (InterruptedException ie) {
                    log(ERROR_TEXT, ie);
                }
            }

            project.refreshLocal(IProject.DEPTH_INFINITE, null);
        } else {
            showInfo(Messages.quickEncryptionImpossible, NLS.bind(Messages.protectedDoc, ACTION));
        }
    }

    /**
     * Checks if the preferences contain all necessary information. Shows a dialog with a warning
     * message and a link to the preference page.<br/> If the preference dialog was closed with the
     * OK button the necessary preference settings are automatically verified again.
     *
     * @return Preferences OK or not
     */
    private boolean checkPreferences() {
        final String prefId = "org.eclipse.wst.xml.security.core.preferences.Encryption";
        int result = 2;

        if (resource == null || "".equals(resource)) { //$NON-NLS-1$
            result = showMissingParameterDialog(Messages.quickEncryptionTitle, NLS.bind(Messages.missingParameter,
                    Messages.missingResource), prefId);
        } else if (resource != null && "xpath".equals(resource) //$NON-NLS-2$
                && (xpath == null || xpath.equals(""))) { //$NON-NLS-1$
            result = showMissingParameterDialog(Messages.quickEncryptionTitle, NLS.bind(Messages.missingParameter,
                    Messages.missingXPathExpression), prefId);
        } else if (keyFile == null || "".equals(keyFile)) {
            result = showMissingParameterDialog(Messages.quickEncryptionTitle, NLS.bind(Messages.missingParameter,
                    Messages.missingKeystore), prefId);
        } else {
            completePrefs = true;
        }

        if (result == MissingPreferenceDialog.OK) {
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
            showInfo(Messages.quickSignatureTitle, Messages.missingKeystorePassword);
            return false;
        } else if (keyPassword == null || keyPassword.length == 0) {
            showInfo(Messages.quickSignatureTitle, Messages.missingKeyPassword);
            return false;
        }

        return true;
    }
}
