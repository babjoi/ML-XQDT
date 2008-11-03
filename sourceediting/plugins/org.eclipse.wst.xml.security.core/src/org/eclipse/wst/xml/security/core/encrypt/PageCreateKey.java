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
package org.eclipse.wst.xml.security.core.encrypt;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.security.core.utils.Algorithms;
import org.eclipse.wst.xml.security.core.utils.Globals;
import org.eclipse.wst.xml.security.core.utils.Keystore;
import org.eclipse.wst.xml.security.core.utils.XmlSecurityImageRegistry;

/**
 * <p>Second alternative page of the Encryption Wizard. Lets the user create a new <i>Private Key</i> in an
 * existing <i>Java KeyStore</i>.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class PageCreateKey extends WizardPage implements Listener {
    /** Wizard page name. */
    public static final String PAGE_NAME = "EncryptPageCreateKey"; //$NON-NLS-1$
    /** Path of the opened project. */
    private String project;
    /** Key generation successful. */
    private boolean generatedKey = false;
    /** Open keyStore button. */
    private Button bOpen = null;
    /** Create key button. */
    private Button bCreateKey = null;
    /** Button <i>Echo KeyStore Password</i>. */
    private Button bEchoKeyStorePassword = null;
    /** Button <i>Echo Key Password</i>. */
    private Button bEchoKeyPassword = null;
    /** Combo box <i>Key Algorithm</i>. */
    private Combo cKeyAlgorithm = null;
    /** Combo box <i>Key Algorithm Size</i>. */
    private Combo cKeyAlgorithmSize = null;
    /** Key generation result label. */
    private Label lResult = null;
    /** Public key alias name. */
    private Text tKeyName = null;
    /** KeyStore. */
    private Text tKeyStore = null;
    /** KeyStore password. */
    private Text tKeyStorePassword = null;
    /** Key password. */
    private Text tKeyPassword = null;
    /** Default label width. */
    private static final int LABELWIDTH = 120;
    /** Default preview textfield height. */
    private static final int TEXTHEIGHT = 40;
    /** Model for the XML Encryption Wizard. */
    private Encryption encryption = null;
    /** The Keystore containing all required key information. */
    private Keystore keyStore = null;

    /**
     * Constructor for PageCreateKey.
     *
     * @param encryption The encryption wizard model
     * @param project The path of the opened project
     */
    public PageCreateKey(final Encryption encryption, final String project) {
        super(PAGE_NAME);
        setTitle(Messages.encryptionTitle);
        setDescription(Messages.createKeyDescription);

        this.encryption = encryption;
        this.project = project;
    }

    /**
     * Creates the wizard page with the layout settings.
     *
     * @param parent Parent composite
     */
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        FormLayout formLayout = new FormLayout();
        container.setLayout(formLayout);

        createPageContent(container);
        addListeners();
        setControl(container);
        setPageComplete(false);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "org.eclipse.wst.xml.security.doc.encryptcreatekey");
    }

    /**
     * Fills this wizard page with content. Two groups (<i>Key</i> and <i>Keystore</i>) and all their widgets are
     * inserted.
     *
     * @param parent Parent composite
     */
    private void createPageContent(final Composite parent) {
        FormLayout layout = new FormLayout();
        layout.marginTop = Globals.MARGIN / 2;
        layout.marginBottom = Globals.MARGIN / 2;
        layout.marginLeft = Globals.MARGIN / 2;
        layout.marginRight = Globals.MARGIN / 2;
        parent.setLayout(layout);

        // Two groups
        Group gKey = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gKey.setLayout(layout);
        gKey.setText(Messages.key);
        FormData data = new FormData();
        data.top = new FormAttachment(0, Globals.MARGIN, SWT.DEFAULT);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gKey.setLayoutData(data);

        Group gKeyStore = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gKeyStore.setLayout(layout);
        gKeyStore.setText(Messages.keyStore);
        data = new FormData();
        data.top = new FormAttachment(gKey, Globals.MARGIN, SWT.DEFAULT);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gKeyStore.setLayoutData(data);

        // Elements for group "Key"
        Label lKeyAlgorithm = new Label(gKey, SWT.SHADOW_IN);
        lKeyAlgorithm.setText(Messages.keyAlgorithm);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(gKey);
        data.left = new FormAttachment(gKey);
        lKeyAlgorithm.setLayoutData(data);

        cKeyAlgorithm = new Combo(gKey, SWT.READ_ONLY);
        cKeyAlgorithm.setItems(Algorithms.KEY_FILE_ALOGRITHMS);
        cKeyAlgorithm.setText(Algorithms.KEY_FILE_ALOGRITHMS[0]);
        data = new FormData();
        data.top = new FormAttachment(lKeyAlgorithm, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyAlgorithm);
        data.width = Globals.COMBO_WIDTH;
        cKeyAlgorithm.setLayoutData(data);

        Label lKeyAlgorithmSize = new Label(gKey, SWT.SHADOW_IN);
        lKeyAlgorithmSize.setText(Messages.keyAlgorithmSize);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lKeyAlgorithm, Globals.MARGIN);
        data.left = new FormAttachment(gKey);
        lKeyAlgorithmSize.setLayoutData(data);

        cKeyAlgorithmSize = new Combo(gKey, SWT.READ_ONLY);
        cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_AES);
        cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_AES[0]);
        data = new FormData();
        data.top = new FormAttachment(lKeyAlgorithmSize, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyAlgorithmSize);
        data.width = Globals.COMBO_WIDTH;
        cKeyAlgorithmSize.setLayoutData(data);

        Label lKeyName = new Label(gKey, SWT.SHADOW_IN);
        lKeyName.setText(Messages.name);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lKeyAlgorithmSize, Globals.MARGIN);
        data.left = new FormAttachment(gKey);
        lKeyName.setLayoutData(data);

        tKeyName = new Text(gKey, SWT.SINGLE);
        tKeyName.setTextLimit(Globals.KEY_ALIAS_MAX_SIZE);
        data = new FormData();
        data.width = Globals.SHORT_TEXT_WIDTH;
        data.top = new FormAttachment(lKeyName, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyName);
        tKeyName.setLayoutData(data);

        Label lKeyPassword = new Label(gKey, SWT.SHADOW_IN);
        lKeyPassword.setText(Messages.password);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lKeyName, Globals.MARGIN);
        data.left = new FormAttachment(gKey);
        lKeyPassword.setLayoutData(data);

        tKeyPassword = new Text(gKey, SWT.SINGLE);
        tKeyPassword.setTextLimit(Globals.KEYSTORE_PASSWORD_MAX_SIZE);
        data = new FormData();
        data.width = Globals.SHORT_TEXT_WIDTH;
        data.top = new FormAttachment(lKeyPassword, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyPassword);
        tKeyPassword.setEchoChar('*');
        tKeyPassword.setLayoutData(data);

        bEchoKeyPassword = new Button(gKey, SWT.PUSH);
        bEchoKeyPassword.setImage(XmlSecurityImageRegistry.getImageRegistry().get("echo_password"));
        bEchoKeyPassword.setToolTipText(Messages.echoPassword);
        data = new FormData();
        data.top = new FormAttachment(tKeyPassword, 0, SWT.CENTER);
        data.left = new FormAttachment(tKeyPassword, Globals.MARGIN);
        bEchoKeyPassword.setLayoutData(data);

        // Elements for group "KeyStore"
        Label lKeyStore = new Label(gKeyStore, SWT.SHADOW_IN);
        lKeyStore.setText(Messages.name);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(gKeyStore);
        data.left = new FormAttachment(gKeyStore);
        lKeyStore.setLayoutData(data);

        tKeyStore = new Text(gKeyStore, SWT.SINGLE);
        data = new FormData();
        data.top = new FormAttachment(lKeyStore, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyStore);
        data.width = Globals.SHORT_TEXT_WIDTH;
        tKeyStore.setLayoutData(data);

        bOpen = new Button(gKeyStore, SWT.PUSH);
        bOpen.setText(Messages.open);
        data = new FormData();
        data.top = new FormAttachment(lKeyStore, 0, SWT.CENTER);
        data.left = new FormAttachment(tKeyStore, Globals.MARGIN);
        bOpen.setLayoutData(data);

        Label lKeyStorePassword = new Label(gKeyStore, SWT.SHADOW_IN);
        lKeyStorePassword.setText(Messages.password);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lKeyStore, Globals.MARGIN);
        data.left = new FormAttachment(gKeyStore);
        lKeyStorePassword.setLayoutData(data);

        tKeyStorePassword = new Text(gKeyStore, SWT.SINGLE);
        tKeyStorePassword.setTextLimit(Globals.KEYSTORE_PASSWORD_MAX_SIZE);
        data = new FormData();
        data.width = Globals.SHORT_TEXT_WIDTH;
        data.top = new FormAttachment(lKeyStorePassword, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyStorePassword);
        tKeyStorePassword.setEchoChar('*');
        tKeyStorePassword.setLayoutData(data);

        bEchoKeyStorePassword = new Button(gKeyStore, SWT.PUSH);
        bEchoKeyStorePassword.setImage(XmlSecurityImageRegistry.getImageRegistry().get("echo_password"));
        bEchoKeyStorePassword.setToolTipText(Messages.echoPassword);
        data = new FormData();
        data.top = new FormAttachment(tKeyStorePassword, 0, SWT.CENTER);
        data.left = new FormAttachment(tKeyStorePassword, Globals.MARGIN);
        bEchoKeyStorePassword.setLayoutData(data);

        bCreateKey = new Button(gKeyStore, SWT.PUSH);
        bCreateKey.setText(Messages.createKeyButton);
        bCreateKey.setEnabled(false);
        data = new FormData();
        data.top = new FormAttachment(lKeyStorePassword, Globals.MARGIN * 2);
        data.left = new FormAttachment(gKeyStore);
        bCreateKey.setLayoutData(data);

        lResult = new Label(gKeyStore, SWT.WRAP);
        data = new FormData();
        data.height = TEXTHEIGHT;
        data.top = new FormAttachment(bCreateKey, Globals.MARGIN * 2);
        data.left = new FormAttachment(gKeyStore);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        lResult.setLayoutData(data);
    }

    /**
     * Adds all listeners for the current wizard page.
     */
    private void addListeners() {
        bOpen.addListener(SWT.Selection, this);
        bCreateKey.addListener(SWT.MouseDown, this);
        bEchoKeyPassword.addListener(SWT.Selection, this);
        bEchoKeyStorePassword.addListener(SWT.Selection, this);
        cKeyAlgorithm.addListener(SWT.Selection, this);
        tKeyName.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tKeyPassword.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        cKeyAlgorithm.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        cKeyAlgorithmSize.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tKeyStorePassword.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tKeyStore.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
    }

    /**
     * Determines the (error) message for the missing field.
     */
    private void dialogChanged() {
        if (cKeyAlgorithm.getText().equals("")) {
            updateStatus(Messages.selectKeyAlgorithm);
            return;
        }
        if (cKeyAlgorithmSize.getText().equals("")) {
            updateStatus(Messages.selectKeyAlgorithmSize);
            return;
        }
        if (tKeyName.getText().length() < Globals.KEY_ALIAS_MIN_SIZE) {
            updateStatus(Messages.enterNewKeyAlias);
            return;
        }
        if (tKeyPassword.getText().length() == 0) {
            updateStatus(Messages.enterKeyPassword);
            return;
        }
        if (tKeyStore.getText().length() == 0) {
            updateStatus(Messages.selectKeystoreFileToExtend);
            return;
        }
        if (tKeyStorePassword.getText().length() == 0) {
            updateStatus(Messages.enterKeystorePassword);
            return;
        }
        if (tKeyStore.getText().length() > 0 && tKeyStorePassword.getText().length() > 0 && tKeyName.getText().length() > 0) {
            try {
                keyStore = new Keystore(tKeyStore.getText(), tKeyStorePassword.getText(), "JCEKS");

                if (keyStore.containsKey(tKeyName.getText())) {
                	setErrorMessage(Messages.verifyKeyAlias);
                    return;
                }
            } catch (Exception ex) {
                setErrorMessage(Messages.verifyAll);
                return;
            }
        }

        setErrorMessage(null);
        updateStatus(null);
    }

    /**
     * Shows a message to the user to complete the fields on this page.
     *
     * @param message The message for the user
     */
    private void updateStatus(final String message) {
        setMessage(message);
        if (!generatedKey && message == null) {
            bCreateKey.setEnabled(true);
        } else {
            bCreateKey.setEnabled(false);
        }
        if (cKeyAlgorithm.getText().equals("")) {
            updateStatus(Messages.selectKeyAlgorithm);
            return;
        }
        if (cKeyAlgorithmSize.getText().equals("")) {
            updateStatus(Messages.selectKeyAlgorithmSize);
            return;
        }
        setPageComplete(generatedKey);
    }

    /**
     * Handles the events from this wizard page.
     *
     * @param e The triggered event
     */
    public void handleEvent(final Event e) {
        if (e.widget == bOpen) {
            openKeystore();
        } else if (e.widget == bCreateKey) {
            try {
                createSecretKey();
                updateStatus(null);
            } catch (Exception ex) {
                updateStatus(Messages.keyGenerationFailed);
            }
        } else if (e.widget == bEchoKeyStorePassword) {
            echoPassword(e);
        } else if (e.widget == bEchoKeyPassword) {
            echoPassword(e);
        } else if (e.widget == cKeyAlgorithm) { // Combo Private Key Algorithm
            if (cKeyAlgorithm.getText().equalsIgnoreCase("AES")) { //$NON-NLS-1$
                cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_AES);
                cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_AES[0]);
            } else if (cKeyAlgorithm.getText().equalsIgnoreCase("Blowfish")) { //$NON-NLS-1$
                cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_BLOWFISH);
                cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_BLOWFISH[0]);
            } else if (cKeyAlgorithm.getText().equalsIgnoreCase("DES")) { //$NON-NLS-1$
                cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_DES);
                cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_DES[0]);
            } else if (cKeyAlgorithm.getText().equalsIgnoreCase("Triple DES")) { //$NON-NLS-1$
                cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_DESEDE);
                cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_DESEDE[0]);
            } else {
                cKeyAlgorithmSize.setItems(Algorithms.KEY_FILE_ALGORITHMS_SIZES);
                cKeyAlgorithmSize.setText(Algorithms.KEY_FILE_ALGORITHMS_SIZES[0]);
            }
        }
    }

    /**
     * Shows plain text or cipher text in the password field.
     *
     * @param e The triggered event
     */
    private void echoPassword(final Event e) {
        if (e.widget == bEchoKeyStorePassword) {
            if (tKeyStorePassword.getEchoChar() == '*') {
                // show plain text
                tKeyStorePassword.setEchoChar('\0');
            } else {
                // show cipher text
                tKeyStorePassword.setEchoChar('*');
            }
        } else if (e.widget == bEchoKeyPassword) {
            if (tKeyPassword.getEchoChar() == '*') {
                // show plain text
                tKeyPassword.setEchoChar('\0');
            } else {
                // show cipher text
                tKeyPassword.setEchoChar('*');
            }
        }
    }

    /**
     * Opens a FileDialog to select the .jks Keystore file to use in this encrypting session.
     */
    private void openKeystore() {
        FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
        dialog.setFilterNames(Globals.KEY_STORE_EXTENSION_NAME);
        dialog.setFilterExtensions(Globals.KEY_STORE_EXTENSION);
        dialog.setFilterPath(project);
        String file = dialog.open();
        if (file != null && file.length() > 0) {
            tKeyStore.setText(file);
        }
    }

    /**
     * Generates the secret key based on the entered data and shows the user an information text about the result.
     *
     * @throws Exception to indicate any exceptional condition
     */
    private void createSecretKey() throws Exception {
        try {
            keyStore = new Keystore(tKeyStore.getText(), tKeyStorePassword.getText(), "JCEKS");
            generatedKey = keyStore.generateSecretKey(cKeyAlgorithm.getText(),
            		Integer.parseInt(cKeyAlgorithmSize.getText()), tKeyName.getText(),
            		tKeyPassword.getText());
        } catch (Exception ex) {
        	generatedKey = false;
        }

        if (generatedKey) {
        	lResult.setText(NLS.bind(Messages.keyGenerated, new Object[] {tKeyName.getText(), tKeyStore.getText()}));
            updateStatus(null);
        } else {
            lResult.setText(Messages.keyGenerationFailed);
        }
    }

    /**
     * Returns the next wizard page after all the necessary data is entered correctly.
     *
     * @return IWizardPage The next wizard page
     */
    public IWizardPage getNextPage() {
        saveDataToModel();
        PageAlgorithms page = ((NewEncryptionWizard) getWizard()).getPageAlgorithms();
        page.onEnterPage();
        return page;
    }

    /**
     * Saves the selections on this wizard page to the model. Called on exit of the page.
     */
    private void saveDataToModel() {
        encryption.setKeyStore(keyStore);
        encryption.setKeyStorePassword(tKeyStorePassword.getText());
        encryption.setKeyName(tKeyName.getText());
        encryption.setKeyPassword(tKeyPassword.getText().toCharArray());
    }
}
