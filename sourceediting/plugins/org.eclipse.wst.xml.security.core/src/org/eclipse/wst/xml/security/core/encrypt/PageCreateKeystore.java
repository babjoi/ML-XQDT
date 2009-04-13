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
package org.eclipse.wst.xml.security.core.encrypt;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

import org.eclipse.jface.dialogs.DialogPage;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.security.core.cryptography.Keystore;
import org.eclipse.wst.xml.security.core.utils.Algorithms;
import org.eclipse.wst.xml.security.core.utils.Globals;
import org.eclipse.wst.xml.security.core.utils.IContextHelpIds;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.eclipse.wst.xml.security.core.utils.XmlSecurityImageRegistry;

/**
 * <p>Third alternative page of the <b>XML Encryption Wizard</b>. Lets the user create a new <i>Key</i>
 * and inserts the generated key in a new <i>Java KeyStore</i> (type <i>JCEKS</i>). The created key is
 * automatically used to create this XML Encryption.</p></p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class PageCreateKeystore extends WizardPage implements Listener {
    /** Wizard page name. */
    public static final String PAGE_NAME = "EncryptPageCreateKeystore"; //$NON-NLS-1$
    /** Keystore. */
    private String keystorePath;
    /** Keystore name. */
    private String keystoreName;
    /** The active folder. */
    private String name;
    /** The path of the selected file. */
    private String path;
    /** Keystore and key generation successful. */
    private boolean generated = false;
    /** Generate button. */
    private Button bGenerate = null;
    /** Button <i>Echo Key Password</i>. */
    private Button bEchoKeyPassword = null;
    /** Button <i>Echo Keystore Password</i>. */
    private Button bEchoKeystorePassword = null;
    /** Drop down box <i>Key Algorithm</i>. */
    private Combo cKeyAlgorithm = null;
    /** Drop down box <i>Key Algorithm Size</i>. */
    private Combo cKeyAlgorithmSize = null;
    /** Key name. */
    private Text tKeyName = null;
    /** Key password. */
    private Text tKeyPassword = null;
    /** Keystore name. */
    private Text tKeystore = null;
    /** Keystore password. */
    private Text tKeystorePassword = null;
    /** Keystore and key generation result label. */
    private Label lResult = null;
    /** Default label width. */
    private static final int LABELWIDTH = 120;
    /** Default preview textfield height. */
    private static final int TEXTHEIGHT = 40;
    /** Model for the XML Encryption Wizard. */
    private Encryption encryption = null;
    /** The Keystore containing all required key information. */
    private Keystore keystore = null;

    /**
     * Constructor for PageCreateKeystore.
     *
     * @param encryption The encryption wizard model
     * @param path The path of the selected file
     * @param name The name of the selected file
     */
    public PageCreateKeystore(final Encryption encryption, final String path, final String name) {
        super(PAGE_NAME);
        setTitle(Messages.encryptionTitle);
        setDescription(Messages.createKeystoreDescription);

        this.encryption = encryption;
        this.path = path;
        this.name = name;
    }

    /**
     * Creates the wizard page with the layout settings.
     *
     * @param parent Parent composite
     */
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new FormLayout());

        createPageContent(container);
        addListeners();
        setControl(container);
        setPageComplete(false);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IContextHelpIds.WIZARD_ENCRYPTION_CREATE_KEYSTORE);
    }

    /**
     * Fills this wizard page with content. Two groups (<i>Key</i> and <i>Keystore</i>) and all
     * their widgets are inserted.
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
        Group gKeystore = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gKeystore.setLayout(layout);
        gKeystore.setText(Messages.keystore);
        FormData data = new FormData();
        data.top = new FormAttachment(0, Globals.MARGIN, SWT.DEFAULT);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gKeystore.setLayoutData(data);

        Group gKey = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gKey.setLayout(layout);
        gKey.setText(Messages.key);
        data = new FormData();
        data.top = new FormAttachment(gKeystore, Globals.MARGIN, SWT.DEFAULT);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gKey.setLayoutData(data);

        // Elements for group "Keystore"
        Label lKeystoreName = new Label(gKeystore, SWT.SHADOW_IN);
        lKeystoreName.setText(Messages.name);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(gKeystore);
        data.left = new FormAttachment(gKeystore);
        lKeystoreName.setLayoutData(data);

        tKeystore = new Text(gKeystore, SWT.SINGLE);
        data = new FormData();
        data.width = Globals.SHORT_TEXT_WIDTH;
        data.top = new FormAttachment(lKeystoreName, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeystoreName);
        tKeystore.setLayoutData(data);

        Label lKeystorePassword = new Label(gKeystore, SWT.SHADOW_IN);
        lKeystorePassword.setText(Messages.password);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lKeystoreName, Globals.MARGIN);
        data.left = new FormAttachment(gKeystore);
        lKeystorePassword.setLayoutData(data);

        tKeystorePassword = new Text(gKeystore, SWT.SINGLE);
        tKeystorePassword.setTextLimit(Globals.KEYSTORE_PASSWORD_MAX_SIZE);
        data = new FormData();
        data.width = Globals.SHORT_TEXT_WIDTH;
        data.top = new FormAttachment(lKeystorePassword, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeystorePassword);
        tKeystorePassword.setEchoChar('*');
        tKeystorePassword.setLayoutData(data);

        bEchoKeystorePassword = new Button(gKeystore, SWT.PUSH);
        bEchoKeystorePassword.setImage(XmlSecurityImageRegistry.getImageRegistry().get("echo_password"));
        bEchoKeystorePassword.setToolTipText(Messages.echoPassword);
        data = new FormData();
        data.top = new FormAttachment(tKeystorePassword, 0, SWT.CENTER);
        data.left = new FormAttachment(tKeystorePassword, Globals.MARGIN);
        bEchoKeystorePassword.setLayoutData(data);

        // Elements for group "Key"
        Label lKeyAlgorithm = new Label(gKey, SWT.SHADOW_IN);
        lKeyAlgorithm.setText(Messages.keyAlgorithm);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(gKey);
        data.left = new FormAttachment(gKey);
        lKeyAlgorithm.setLayoutData(data);

        cKeyAlgorithm = new Combo(gKey, SWT.READ_ONLY);
        cKeyAlgorithm.setItems(Algorithms.ENCRYPTION_KEY_ALOGRITHMS);
        cKeyAlgorithm.setText(Algorithms.ENCRYPTION_KEY_ALOGRITHMS[0]);
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
        tKeyName.setTextLimit(Globals.KEY_NAME_MAX_SIZE);
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

        bGenerate = new Button(gKey, SWT.PUSH);
        bGenerate.setText(Messages.generateButton);
        bGenerate.setEnabled(false);
        data = new FormData();
        data.top = new FormAttachment(lKeyPassword, Globals.MARGIN * 2);
        data.left = new FormAttachment(gKey);
        bGenerate.setLayoutData(data);

        lResult = new Label(gKey, SWT.WRAP);
        data = new FormData();
        data.height = TEXTHEIGHT;
        data.top = new FormAttachment(bGenerate, Globals.MARGIN * 2);
        data.left = new FormAttachment(gKey);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        lResult.setLayoutData(data);
    }

    /**
     * Adds all listeners for the current wizard page.
     */
    private void addListeners() {
        bGenerate.addListener(SWT.MouseDown, this);
        bEchoKeyPassword.addListener(SWT.Selection, this);
        bEchoKeystorePassword.addListener(SWT.Selection, this);
        cKeyAlgorithm.addListener(SWT.Selection, this);
        tKeystore.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tKeystorePassword.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
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
    }

    /**
     * Determines the (error) message for the missing field.
     */
    private void dialogChanged() {
        if (tKeystore.getText().length() > 0) {
            keystoreName = tKeystore.getText() + Globals.KEYSTORE_EXTENSION;
            keystorePath = path + System.getProperty("file.separator") + keystoreName; //$NON-NLS-1$

            File tempFile = new File(keystorePath);
            if (tempFile.exists()) {
                updateStatus(Messages.keystoreAlreadyExists, DialogPage.ERROR);
                return;
            }
        } else {
            updateStatus(Messages.enterNewKeystoreName, DialogPage.INFORMATION);
            return;
        }
        if (tKeystorePassword.getText().length() < Globals.KEYSTORE_PASSWORD_MIN_SIZE) {
            updateStatus(Messages.enterNewKeystorePassword, DialogPage.INFORMATION);
            return;
        }
        if (tKeyName.getText().length() < Globals.KEY_NAME_MIN_SIZE
            || tKeyName.getText().length() > Globals.KEY_NAME_MAX_SIZE) {
            updateStatus(Messages.enterNewKeyName, DialogPage.INFORMATION);
            return;
        }
        if (tKeyPassword.getText().length() < Globals.KEY_PASSWORD_MIN_SIZE
            || tKeyPassword.getText().length() > Globals.KEY_PASSWORD_MAX_SIZE) {
            updateStatus(Messages.enterNewKeyPassword, DialogPage.INFORMATION);
            return;
        }

        updateStatus(null, DialogPage.NONE);
    }

    /**
     * Shows a message to the user to complete the fields on this page.
     *
     * @param message The message for the user
     * @param status The status type of the message
     */
    private void updateStatus(final String message, final int status) {
        setMessage(message, status);
        if (!generated && message == null) {
            bGenerate.setEnabled(true);
        } else {
            bGenerate.setEnabled(false);
        }
        setPageComplete(generated);
    }

    /**
     * Handles the events from this wizard page.
     *
     * @param e The triggered event
     */
    public void handleEvent(final Event e) {
        if (e.widget == bGenerate) {
            createKeystore();
            updateStatus(null, DialogPage.NONE);
        } else if (e.widget == bEchoKeystorePassword) {
            echoPassword(e);
        } else if (e.widget == bEchoKeyPassword) {
            echoPassword(e);
        } else if (e.widget == cKeyAlgorithm) { // Combo Key Algorithm
            if (cKeyAlgorithm.getText().equalsIgnoreCase("AES")) { //$NON-NLS-1$
                cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_AES);
                cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_AES[0]);
            } else if (cKeyAlgorithm.getText().equalsIgnoreCase("Blowfish")) { //$NON-NLS-1$
                cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_BLOWFISH);
                cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_BLOWFISH[0]);
            } else if (cKeyAlgorithm.getText().equalsIgnoreCase("DES")) { //$NON-NLS-1$
                cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_DES);
                cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_DES[0]);
            } else if (cKeyAlgorithm.getText().equalsIgnoreCase("DESede")) { //$NON-NLS-1$
                cKeyAlgorithmSize.setItems(Algorithms.KEY_SIZES_DESEDE);
                cKeyAlgorithmSize.setText(Algorithms.KEY_SIZES_DESEDE[0]);
            } else {
                cKeyAlgorithmSize.setItems(Algorithms.ENCRYPTION_KEY_ALGORITHMS_SIZES);
                cKeyAlgorithmSize.setText(Algorithms.ENCRYPTION_KEY_ALGORITHMS_SIZES[0]);
            }
        }
    }

    /**
     * Shows plain text (\0) or cipher text (*) in the password field.
     *
     * @param e The triggered event
     */
    private void echoPassword(final Event e) {
        if (e.widget == bEchoKeystorePassword) {
            if (tKeystorePassword.getEchoChar() == '*') {
                tKeystorePassword.setEchoChar('\0');
            } else {
                tKeystorePassword.setEchoChar('*');
            }
            tKeystorePassword.redraw();
        } else if (e.widget == bEchoKeyPassword) {
            if (tKeyPassword.getEchoChar() == '*') {
                tKeyPassword.setEchoChar('\0');
            } else {
                tKeyPassword.setEchoChar('*');
            }
            tKeyPassword.redraw();
        }
    }

    /**
     * Generates the keystore and the key based on the entered data and shows the user an
     * information notice about the result.
     */
    private void createKeystore() {
        try {
            keystore = new Keystore(keystorePath, tKeystorePassword.getText(), Globals.KEYSTORE_TYPE);

            SecretKey key = keystore.generateSecretKey(cKeyAlgorithm.getText(), Integer.parseInt(cKeyAlgorithmSize.getText()));

            keystore.store();
            keystore.load();

            generated = keystore.insertSecretKey(tKeyName.getText(), tKeyPassword.getText().toCharArray(), key);

            keystore.store();
        } catch (NoSuchAlgorithmException ex) {
            generated = false;

            lResult.setText(Messages.keyGenerationFailed);
        } catch (Exception ex) {
            Utils.logError(ex, "Encryption keystore generation failed"); //$NON-NLS-1$

            generated = false;

            lResult.setText(Messages.keystoreGenerationFailed);
        }

        if (generated) {
        	lResult.setText(NLS.bind(Messages.keystoreGenerated, new Object[] {tKeyName.getText(), keystoreName, name}));
            updateStatus(null, DialogPage.NONE);
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
        encryption.setKeyName(tKeyName.getText());
        encryption.setKeyPassword(tKeyPassword.getText().toCharArray());
        encryption.setKeystore(keystore);
        encryption.setKeystorePassword(tKeystorePassword.getText().toCharArray());
    }
}
