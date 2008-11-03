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
import java.lang.reflect.InvocationTargetException;

import org.apache.xml.security.utils.XMLUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;
import org.eclipse.wst.xml.security.core.encrypt.CreateEncryption;
import org.eclipse.wst.xml.security.core.encrypt.Encryption;
import org.eclipse.wst.xml.security.core.preferences.PreferenceConstants;
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
    /** Key file algorithm. */
    private String keyFileAlgorithm;
    /** Key file algorithm size. */
    private String keyFileAlgorithmSize;
    /** Key file password. */
    private String keyFilePassword;
    /** Key file. */
    private String keyFile;
    /** Encryption ID. */
    private String encryptionID;
    /** All necessary preferences are available. */
    private boolean completePrefs = false;
    /** Error message for the logfile. */
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
            try {
                quickEncrypt();
            } catch (Exception ex) {
                showErrorDialog(Messages.error, Messages.encryptingError, ex);
                log(ERROR_TEXT, ex);
            }
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
        keyFile = store.getString(PreferenceConstants.ENCRYPT_KEY_STORE);
        // TODO InputDialog
        keyFilePassword = " ";
        encryptionID = store.getString(PreferenceConstants.ENCRYPT_ID);

        if (resource != null && resource.equals("xpath")) {
            xpath = store.getString(PreferenceConstants.ENCRYPT_XPATH);
        }
    }

    /**
     * Encrypts the XML document with the stored settings.
     *
     * @throws Exception to indicate any exceptional condition
     */
    private void quickEncrypt() throws Exception {
        final Encryption encryption = new Encryption();
        encryption.setResource(resource);
        encryption.setEncryptionType(encryptionType);
        encryption.setXpath(xpath);
        encryption.setContent(false);
        encryption.setBsp(false);
        encryption.setKeyCipherAlgorithm(keyWrapAlgorithm);
        encryption.setKeyAlgorithm(keyFileAlgorithm);
        encryption.setKeyAlgorithmSize(keyFileAlgorithmSize);
//        encryption.setKeystore(keyFile);
        encryption.setKeyStorePassword(keyFilePassword);
        encryption.setEncryptionId(encryptionID);

        if (file != null && file.isAccessible() && !file.isReadOnly()) { // call in view
            IProject project = file.getProject();
            if (resource.equals("selection")) { //$NON-NLS-1$
                showInfo(Messages.quickEncryptionImpossible, Messages.quickEncryptionImpossibleText);
            } else {
                final String filename = file.getLocation().toString();
                encryption.setFile(file.getLocation().toString());
                IRunnableWithProgress op = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask(Messages.encryptionTaskInfo, 3);
                            CreateEncryption content = new CreateEncryption();
                            Document doc = content.encrypt(encryption, null, monitor);
                            FileOutputStream fos = new FileOutputStream(filename);

                            if (doc != null) {
                                XMLUtils.outputDOM(doc, fos);
                            }
                            fos.flush();
                            fos.close();
                        } catch (Exception ex) {
                            showErrorDialog(Messages.error, Messages.encryptingError, ex);
                            log(ERROR_TEXT, ex);
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
            }

            project.refreshLocal(IProject.DEPTH_INFINITE, null);
        } else if (file == null && editor != null && editor.isEditable()) { // call in editor
            boolean validSelection = false;

            if (editor.isDirty()) {
                saveEditorContent(editor);
            }

            IEditorInput input = editor.getEditorInput();
            final IDocument document = editor.getDocumentProvider().getDocument(input);
            file = (IFile) input.getAdapter(IFile.class);
            final ITextSelection textSelection = (ITextSelection) editor.getSelectionProvider()
                    .getSelection();

            if (resource.equals("selection") && textSelection != null && !textSelection.isEmpty()
                    && textSelection.getLength() > 0 && file != null) {
                validSelection = parseSelection(textSelection.getText());
            }

            if (file != null && resource.equals("selection") && validSelection) { // with text selection
                encryption.setFile(file.getLocation().toString());
                IRunnableWithProgress op = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask(Messages.encryptionTaskInfo, 3);
                            CreateEncryption content = new CreateEncryption();
                            Document doc = content.encrypt(encryption, textSelection
                                    .getText(), monitor);

                            if (doc != null) {
                                document.set(Utils.docToString(doc, true));
                            }
                        } catch (Exception ex) {
                            showErrorDialog(Messages.error, Messages.decryptingError, ex);
                            log(ERROR_TEXT, ex);
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
            } else if (file != null && !resource.equals("selection")) { // without text selection
                encryption.setFile(file.getLocation().toString());
                IRunnableWithProgress op = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask(Messages.encryptionTaskInfo, 3);
                            CreateEncryption content = new CreateEncryption();
                            Document doc = content.encrypt(encryption, null, monitor);

                            if (doc != null) {
                                document.set(Utils.docToString(doc, true));
                            }
                        } catch (Exception ex) {
                            showErrorDialog(Messages.error, Messages.encryptingError, ex);
                            log(ERROR_TEXT, ex);
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
            } else if (resource.equals("selection") && !validSelection) {
                showInfo(Messages.invalidTextSelection, Messages.invalidTextSelectionText);
            } else {
                showInfo(Messages.quickEncryptionImpossible, NLS
                        .bind(Messages.protectedDoc, ACTION));
            }
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
        final String title = Messages.quickEncryptionTitle;
        final String prefId = "org.eclipse.wst.xml.security.core.preferences.Encryption";
        int result = 2;

        if (resource == null || resource.equals("")) { //$NON-NLS-1$
            result = showMissingParameterDialog(title, NLS.bind(Messages.missingParameter,
                    Messages.missingResource), prefId);
        } else if (resource != null && resource.equals("xpath") //$NON-NLS-2$
                && (xpath == null || xpath.equals(""))) { //$NON-NLS-1$
            result = showMissingParameterDialog(title, NLS.bind(Messages.missingParameter,
                    Messages.missingXPathExpression), prefId);
        } else if (keyFile == null || keyFile.equals("")) {
            result = showMissingParameterDialog(title, NLS.bind(Messages.missingParameter,
                    Messages.missingKeyStore), prefId);
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
