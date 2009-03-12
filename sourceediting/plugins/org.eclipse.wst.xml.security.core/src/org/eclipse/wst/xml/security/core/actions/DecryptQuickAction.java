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

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.xml.security.utils.XMLUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;
import org.eclipse.wst.xml.security.core.decrypt.CreateDecryption;
import org.eclipse.wst.xml.security.core.decrypt.Decryption;
import org.eclipse.wst.xml.security.core.preferences.PreferenceConstants;
import org.eclipse.wst.xml.security.core.utils.PasswordDialog;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.w3c.dom.Document;

/**
 * <p>Action class used to start the decryption of the selected XML document (fragment) with predefined
 * settings defined in the preferences (Quick Decryption).</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class DecryptQuickAction extends XmlSecurityActionAdapter {
    /** Active editor. */
    private ITextEditor editor = null;
    /** The file to decrypt. */
    private IFile file = null;
    /** Keystore. */
    private String keystore;
    /** Keystore password. */
    private String keystorePassword;
    /** Key name (alias). */
    private String keyName;
    /** Key password. */
    private char[] keyPassword;
    /** Encryption ID. */
    private String encryptionID;
    /** All necessary preferences are available. */
    private boolean completePrefs = false;
    /** Error message for the log. */
    private static final String ERROR_TEXT = "An error occured during quick decryption"; //$NON-NLS-1$
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
     * Takes the selected file or editor content and decrypts it. The returned decrypted XML
     * document is pretty printed before the editor or the file is updated.<br/>
     * Before any operation on the XML, the preference store is searched for the necessary settings.
     * Then these settings are verified. If the settings are valid the decrypt operation begins.
     * If not the user has the possibility to provide valid information.
     *
     * @param action The IAction
     */
    public void run(final IAction action) {
        getPreferenceValues();

        if (checkPreferences()) {
        	// Ask the user for the passwords
            PasswordDialog keystorePasswordDialog = new PasswordDialog(getShell(),
                    Messages.keystorePassword, Messages.enterKeystorePassword, ""); //$NON-NLS-3$
            if (keystorePasswordDialog.open() == Window.OK) {
            	keystorePassword = keystorePasswordDialog.getValue();
            } else {
                return;
            }

            PasswordDialog keyPasswordDialog = new PasswordDialog(getShell(),
                    Messages.keyPassword, Messages.enterKeyPassword, ""); //$NON-NLS-3$
            if (keyPasswordDialog.open() == Window.OK) {
            	keyPassword = keyPasswordDialog.getValue().toCharArray();
            } else {
                return;
            }

            try {
                quickDecrypt();
            } catch (Exception ex) {
                showErrorDialog(Messages.error, Messages.decryptingError, ex);
                log(ERROR_TEXT, ex);
            }
        }
    }

    /**
     * Determines the preference values for <i>Quick Decryption</i>.
     */
    private void getPreferenceValues() {
        IPreferenceStore store = XmlSecurityPlugin.getDefault().getPreferenceStore();

        keystore = store.getString(PreferenceConstants.ENCRYPT_KEY_STORE);
        keyName = store.getString(PreferenceConstants.ENCRYPT_KEY_NAME);
        encryptionID = store.getString(PreferenceConstants.ENCRYPT_ID);
    }

    /**
     * Decrypts the XML document with the stored settings.
     *
     * @throws Exception to indicate any exceptional condition
     */
    private void quickDecrypt() throws Exception {
        final Decryption wizard = new Decryption();
        wizard.setKeystore(keystore);
        wizard.setKeystorePassword(keystorePassword);
        wizard.setKeyName(keyName);
        wizard.setKeyPassword(keyPassword);
        wizard.setEncryptionId(encryptionID);

        IWorkbenchPart workbenchPart = getWorkbenchPart();

        if (workbenchPart != null && workbenchPart instanceof ITextEditor) {
            editor = (ITextEditor) workbenchPart;
        }

        if (file != null && file.isAccessible() && !file.isReadOnly()) { // call in view
            IProject project = file.getProject();
            final String filename = file.getLocation().toString();
            wizard.setFile(file.getLocation().toString());

            IRunnableWithProgress op = new IRunnableWithProgress() {
                public void run(final IProgressMonitor monitor) {
                    FileOutputStream fos = null;

                    try {
                        monitor.beginTask(Messages.decryptionTaskInfo, 6);
                        CreateDecryption decryption = new CreateDecryption();
                        Document doc = decryption.decrypt(wizard, monitor);
                        fos = new FileOutputStream(filename);
                        if (doc != null) {
                            XMLUtils.outputDOM(doc, fos);
                        }
                    } catch (final Exception ex) {
                        getShell().getDisplay().asyncExec(new Runnable() {
                            public void run() {
                                showErrorDialog(Messages.error, Messages.decryptingError, ex);
                                log(ERROR_TEXT, ex);
                            }
                        });
                    } finally {
                        if (fos != null) {
                            try {
                                fos.flush();
                                fos.close();
                            } catch (IOException ex) {
                                log(ERROR_TEXT, ex);
                            }
                        }
                        monitor.done();
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

            project.refreshLocal(IProject.DEPTH_INFINITE, null);
        } else if (editor != null && editor.isEditable()) { // call in editor
            if (editor.isDirty()) {
                saveEditorContent(editor);
            }

            IEditorInput input = editor.getEditorInput();
            final IDocument document = editor.getDocumentProvider().getDocument(input);
            file = (IFile) input.getAdapter(IFile.class);

            if (file != null) {
                wizard.setFile(file.getLocation().toString());

                IRunnableWithProgress op = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask(Messages.decryptionTaskInfo, 6);
                            CreateDecryption decryption = new CreateDecryption();
                            Document doc = decryption.decrypt(wizard, monitor);
                            if (doc != null) {
                                document.set(Utils.docToString(doc, false));
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
                showInfo(Messages.quickDecryptionImpossible, NLS.bind(Messages.protectedDoc, ACTION));
            }
        } else {
            showInfo(Messages.quickDecryptionImpossible, NLS.bind(Messages.protectedDoc, ACTION));
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
        final String title = Messages.quickDecryptionTitle;
        final String prefId = "org.eclipse.wst.xml.security.core.preferences.Encryption";
        int result = 2;

        if (keystore == null || keystore.equals("")) {
            result = showMissingParameterDialog(title, NLS.bind(Messages.missingParameter,
                    Messages.missingKeystore), prefId);
        } else if (keyName == null || keyName.equals("")) {
            result = showMissingParameterDialog(title, NLS.bind(Messages.missingParameter,
                    Messages.missingKeyName), prefId);
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
}
