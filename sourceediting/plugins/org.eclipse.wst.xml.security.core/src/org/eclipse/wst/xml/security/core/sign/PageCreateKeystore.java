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
package org.eclipse.wst.xml.security.core.sign;

import java.io.File;
import java.util.HashMap;

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
import org.eclipse.wst.xml.security.core.utils.Algorithms;
import org.eclipse.wst.xml.security.core.utils.Certificate;
import org.eclipse.wst.xml.security.core.utils.Globals;
import org.eclipse.wst.xml.security.core.utils.XmlSecurityImageRegistry;

/**
 * <p>Second alternative page of the Digital Signature Wizard. Lets the user create a new <i>X.509
 * key</i> in a new <i>Java Keystore</i>. The created key/ keystore are
 * automatically used to create this XML signature.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class PageCreateKeystore extends WizardPage implements Listener {
    /** Wizard page name. */
    public static final String PAGE_NAME = "SignPageCreateKeystore"; //$NON-NLS-1$
    /** KeyStore. */
    private String keystore;
    /** KeyStore name. */
    private String keystoreName;
    /** Path of the opened project. */
    private String project;
    /** Name of the opened project. */
    private String name;
    /** Keystore and key generation successful. */
    private boolean generatedKeystore = false;
    /** Create keystore button. */
    private Button bCreateKeyStore = null;
    /** Button <i>Echo Keystore Password</i>. */
    private Button bEchoKeyStorePassword = null;
    /** Button <i>Echo Key Password</i>. */
    private Button bEchoKeyPassword = null;
    /** Algorithm combo. */
    private Combo cKeyAlgorithm = null;
    /** Key preview label. */
    private Label lPreview = null;
    /** Key generation result label. */
    private Label lResult = null;
    /** Common Name information. */
    private Text tCommonName = null;
    /** Organizational Unit information. */
    private Text tOrganizationalUnit = null;
    /** Organization information. */
    private Text tOrganization = null;
    /** Location information. */
    private Text tLocation = null;
    /** State information. */
    private Text tState = null;
    /** Country information. */
    private Text tCountry = null;
    /** Keystore name. */
    private Text tKeyStoreName = null;
    /** Keystore password. */
    private Text tKeyStorePassword = null;
    /** Key alias. */
    private Text tKeyName = null;
    /** Key password. */
    private Text tKeyPassword = null;
    /** Default label width. */
    private static final int LABELWIDTH = 160;
    /** Default preview textfield height. */
    private static final int TEXTHEIGHT = 40;
    /** Model for the XML Digital Signature Wizard. */
    private Signature signature = null;

    /**
     * Constructor for PageCreateKeystore.
     *
     * @param signature The signature wizard model
     * @param project The path of the opened project
     * @param name The name of the opened project
     */
    public PageCreateKeystore(final Signature signature, final String project, final String name) {
        super(PAGE_NAME);
        setTitle(Messages.signatureTitle);
        setDescription(Messages.createKeystoreDescription);

        this.signature = signature;
        this.project = project;
        this.name = name;
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

        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "org.eclipse.wst.xml.security.doc.signcreatekeystore");
    }

    /**
     * Fills this wizard page with content. Three groups (<i>Distinguished Name</i>,
     * <i>Certificate</i> and <i>Keystore</i>) and all their widgets are inserted.
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

        // Three groups
        Group gDN = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gDN.setLayout(layout);
        gDN.setText(Messages.distinguishedName);
        FormData data = new FormData();
        data.top = new FormAttachment(0, 0);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gDN.setLayoutData(data);

        Group gKey = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gKey.setLayout(layout);
        gKey.setText(Messages.key);
        data = new FormData();
        data.top = new FormAttachment(gDN, Globals.MARGIN, SWT.DEFAULT);
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

        // Elements for group "Distinguished Name"
        Label lCommonName = new Label(gDN, SWT.SHADOW_IN);
        lCommonName.setText(Messages.commonName);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(gDN);
        data.left = new FormAttachment(gDN);
        lCommonName.setLayoutData(data);

        tCommonName = new Text(gDN, SWT.SINGLE);
        tCommonName.setTextLimit(Globals.KEY_DATA_LIMIT);
        data = new FormData();
        data.width = Globals.MEDIUM_TEXT_WIDTH;
        data.top = new FormAttachment(lCommonName, 0, SWT.CENTER);
        data.left = new FormAttachment(lCommonName);
        tCommonName.setLayoutData(data);

        Label lOrganizationalUnit = new Label(gDN, SWT.SHADOW_IN);
        lOrganizationalUnit.setText(Messages.organizationalUnit);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lCommonName, Globals.MARGIN);
        data.left = new FormAttachment(gDN);
        lOrganizationalUnit.setLayoutData(data);

        tOrganizationalUnit = new Text(gDN, SWT.SINGLE);
        tOrganizationalUnit.setTextLimit(Globals.KEY_DATA_LIMIT);
        data = new FormData();
        data.width = Globals.MEDIUM_TEXT_WIDTH;
        data.top = new FormAttachment(lOrganizationalUnit, 0, SWT.CENTER);
        data.left = new FormAttachment(lOrganizationalUnit);
        tOrganizationalUnit.setLayoutData(data);

        Label lOrganization = new Label(gDN, SWT.SHADOW_IN);
        lOrganization.setText(Messages.organization);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lOrganizationalUnit, Globals.MARGIN);
        data.left = new FormAttachment(gDN);
        lOrganization.setLayoutData(data);

        tOrganization = new Text(gDN, SWT.SINGLE);
        tOrganization.setTextLimit(Globals.KEY_DATA_LIMIT);
        data = new FormData();
        data.width = Globals.MEDIUM_TEXT_WIDTH;
        data.top = new FormAttachment(lOrganization, 0, SWT.CENTER);
        data.left = new FormAttachment(lOrganization);
        tOrganization.setLayoutData(data);

        Label lLocation = new Label(gDN, SWT.SHADOW_IN);
        lLocation.setText(Messages.location);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lOrganization, Globals.MARGIN);
        data.left = new FormAttachment(gDN);
        lLocation.setLayoutData(data);

        tLocation = new Text(gDN, SWT.SINGLE);
        tLocation.setTextLimit(Globals.KEY_DATA_LIMIT);
        data = new FormData();
        data.width = Globals.MEDIUM_TEXT_WIDTH;
        data.top = new FormAttachment(lLocation, 0, SWT.CENTER);
        data.left = new FormAttachment(lLocation);
        tLocation.setLayoutData(data);

        Label lState = new Label(gDN, SWT.SHADOW_IN);
        lState.setText(Messages.state);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lLocation, Globals.MARGIN);
        data.left = new FormAttachment(gDN);
        lState.setLayoutData(data);

        tState = new Text(gDN, SWT.SINGLE);
        tState.setTextLimit(Globals.KEY_DATA_LIMIT);
        data = new FormData();
        data.width = Globals.MEDIUM_TEXT_WIDTH;
        data.top = new FormAttachment(lState, 0, SWT.CENTER);
        data.left = new FormAttachment(lState);
        tState.setLayoutData(data);

        Label lCountry = new Label(gDN, SWT.SHADOW_IN);
        lCountry.setText(Messages.country);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lState, Globals.MARGIN);
        data.left = new FormAttachment(gDN);
        lCountry.setLayoutData(data);

        tCountry = new Text(gDN, SWT.SINGLE);
        tCountry.setTextLimit(Globals.KEY_DATA_LIMIT);
        data = new FormData();
        data.width = Globals.MEDIUM_TEXT_WIDTH;
        data.top = new FormAttachment(lCountry, 0, SWT.CENTER);
        data.left = new FormAttachment(lCountry);
        tCountry.setLayoutData(data);

        lPreview = new Label(gDN, SWT.WRAP);
        data = new FormData();
        data.height = TEXTHEIGHT;
        data.top = new FormAttachment(lCountry, Globals.MARGIN);
        data.left = new FormAttachment(gDN);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        lPreview.setLayoutData(data);

        // Elements for group "Key"
        Label lKeyName = new Label(gKey, SWT.SHADOW_IN);
        lKeyName.setText(Messages.name);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(gKey);
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
        tKeyPassword.setTextLimit(Globals.KEY_PASSWORD_MAX_SIZE);
        data = new FormData();
        data.width = Globals.SHORT_TEXT_WIDTH;
        data.top = new FormAttachment(lKeyPassword, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyPassword);
        tKeyPassword.setEchoChar('*');
        tKeyPassword.setLayoutData(data);

        Label lKeyAlgorithm = new Label(gKey, SWT.SHADOW_IN);
        lKeyAlgorithm.setText(Messages.keyAlgorithm);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lKeyPassword, Globals.MARGIN);
        data.left = new FormAttachment(gKey);
        lKeyAlgorithm.setLayoutData(data);

        cKeyAlgorithm = new Combo(gKey, SWT.READ_ONLY);
        data = new FormData();
        data.top = new FormAttachment(lKeyAlgorithm, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyAlgorithm);
        data.width = Globals.COMBO_WIDTH;
        cKeyAlgorithm.setLayoutData(data);

        bEchoKeyPassword = new Button(gKey, SWT.PUSH);
        bEchoKeyPassword.setImage(XmlSecurityImageRegistry.getImageRegistry().get("echo_password"));
        data = new FormData();
        data.top = new FormAttachment(tKeyPassword, 0, SWT.CENTER);
        data.left = new FormAttachment(tKeyPassword, Globals.MARGIN);
        bEchoKeyPassword.setLayoutData(data);

        // Elements for group "KeyStore"
        Label lKeyStoreName = new Label(gKeyStore, SWT.SHADOW_IN);
        lKeyStoreName.setText(Messages.name);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(gKeyStore);
        data.left = new FormAttachment(gKeyStore);
        lKeyStoreName.setLayoutData(data);

        tKeyStoreName = new Text(gKeyStore, SWT.SINGLE);
        data = new FormData();
        data.width = Globals.SHORT_TEXT_WIDTH;
        data.top = new FormAttachment(lKeyStoreName, 0, SWT.CENTER);
        data.left = new FormAttachment(lKeyStoreName);
        tKeyStoreName.setLayoutData(data);

        Label lKeyStorePassword = new Label(gKeyStore, SWT.SHADOW_IN);
        lKeyStorePassword.setText(Messages.password);
        data = new FormData();
        data.width = LABELWIDTH;
        data.top = new FormAttachment(lKeyStoreName, Globals.MARGIN);
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
        data = new FormData();
        data.top = new FormAttachment(tKeyStorePassword, 0, SWT.CENTER);
        data.left = new FormAttachment(tKeyStorePassword, Globals.MARGIN);
        bEchoKeyStorePassword.setLayoutData(data);

        bCreateKeyStore = new Button(gKeyStore, SWT.PUSH);
        bCreateKeyStore.setText(Messages.createKeyStoreButton);
        bCreateKeyStore.setEnabled(false);
        data = new FormData();
        data.top = new FormAttachment(lKeyStorePassword, Globals.MARGIN * 2);
        data.left = new FormAttachment(gKeyStore);
        bCreateKeyStore.setLayoutData(data);

        lResult = new Label(gKeyStore, SWT.WRAP);
        data = new FormData();
        data.height = TEXTHEIGHT;
        data.top = new FormAttachment(bCreateKeyStore, Globals.MARGIN * 2);
        data.left = new FormAttachment(gKeyStore);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        lResult.setLayoutData(data);
    }

    /**
     * Adds all listeners for the current wizard page.
     */
    private void addListeners() {
        bCreateKeyStore.addListener(SWT.MouseDown, this);
        bEchoKeyStorePassword.addListener(SWT.Selection, this);
        bEchoKeyPassword.addListener(SWT.Selection, this);
        tCommonName.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tOrganizationalUnit.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tOrganization.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tLocation.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tState.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tCountry.addModifyListener(new ModifyListener() {
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
        cKeyAlgorithm.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tKeyStoreName.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tKeyStorePassword.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
    }

    /**
     * Determines the (error) message for the missing field.
     */
    private void dialogChanged() {
        if (tCommonName.getText().length() > 0) {
            lPreview.setText("CN=" + tCommonName.getText()); //$NON-NLS-1$
        } else {
            lPreview.setText(""); //$NON-NLS-1$
            updateStatus(Messages.enterCommonName);
            return;
        }
        if (tOrganizationalUnit.getText().length() > 0) {
            lPreview.setText("CN=" + tCommonName.getText() //$NON-NLS-1$
                    + ", OU=" + tOrganizationalUnit.getText()); //$NON-NLS-2$
        }
        if (tOrganization.getText().length() > 0) {
            lPreview.setText("CN=" + tCommonName.getText() //$NON-NLS-1$
                    + ", OU=" + tOrganizationalUnit.getText() //$NON-NLS-2$
                    + ", O=" + tOrganization.getText()); //$NON-NLS-1$
        }
        if (tLocation.getText().length() > 0) {
            lPreview.setText("CN=" + tCommonName.getText() //$NON-NLS-1$
                    + ", OU=" + tOrganizationalUnit.getText() //$NON-NLS-2$
                    + ", O=" + tOrganization.getText() //$NON-NLS-1$
                    + ", L=" + tLocation.getText()); //$NON-NLS-2$
        }
        if (tState.getText().length() > 0) {
            lPreview.setText("CN=" + tCommonName.getText() //$NON-NLS-1$
                    + ", OU=" + tOrganizationalUnit.getText() //$NON-NLS-1$
                    + ", O=" + tOrganization.getText() //$NON-NLS-1$
                    + ", L=" + tLocation.getText() //$NON-NLS-1$
                    + ", ST=" + tState.getText()); //$NON-NLS-1$
        }
        if (tCountry.getText().length() > 0) {
            lPreview.setText("CN=" + tCommonName.getText() //$NON-NLS-1$
                    + ", OU=" + tOrganizationalUnit.getText() //$NON-NLS-1$
                    + ", O=" + tOrganization.getText() //$NON-NLS-1$
                    + ", L=" + tLocation.getText() //$NON-NLS-1$
                    + ", ST=" + tState.getText() //$NON-NLS-1$
                    + ", C=" + tCountry.getText()); //$NON-NLS-1$
        }
        if (tKeyName.getText().length() < Globals.KEY_ALIAS_MIN_SIZE) {
            updateStatus(Messages.enterNewKeyAlias);
            return;
        }
        if (tKeyPassword.getText().length() < Globals.KEY_PASSWORD_MIN_SIZE) {
            updateStatus(Messages.enterNewKeyPassword);
            return;
        }
        if (cKeyAlgorithm.getSelectionIndex() < 0) {
            updateStatus(Messages.selectKeyAlgorithm);
            return;
        }
        if (tKeyStoreName.getText().length() > 0) {
            keystoreName = tKeyStoreName.getText() + ".jks"; //$NON-NLS-1$
            keystore = project + System.getProperty("file.separator") //$NON-NLS-1$
                + keystoreName;
            File tempFile = new File(keystore);
            if (tempFile.exists()) {
                setErrorMessage(Messages.keystoreAlreadyExists);
                return;
            } else {
                setErrorMessage(null);
            }
            generatedKeystore = false;
        } else {
            updateStatus(Messages.enterNewKeystoreName);
            return;
        }
        if (tKeyStorePassword.getText().length() < Globals.KEYSTORE_PASSWORD_MIN_SIZE) {
            updateStatus(Messages.enterNewKeystorePassword);
            return;
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
        if (!generatedKeystore && message == null) {
            bCreateKeyStore.setEnabled(true);
        } else {
            bCreateKeyStore.setEnabled(false);
        }
        setPageComplete(generatedKeystore);
    }

    /**
     * Handles the events from this wizard page.
     *
     * @param e The triggered event
     */
    public void handleEvent(final Event e) {
        if (e.widget == bCreateKeyStore) {
            try {
                generateKeyAndCertificate();
                updateStatus(null);
            } catch (Exception e1) {
                updateStatus(Messages.keystoreGenerationFailed);
            }
        } else if (e.widget == bEchoKeyStorePassword || e.widget == bEchoKeyPassword) {
            echoPassword(e);
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
     * Generates the keystore and the certificate based on the entered data and shows the user an
     * information text about the result.
     *
     * @throws Exception to indicate any exceptional condition
     */
    private void generateKeyAndCertificate() throws Exception {
        HashMap<String, String> certificateData = new HashMap<String, String>();
        certificateData.put("keystore", keystore); //$NON-NLS-1$
        certificateData.put("alias", tKeyName.getText()); //$NON-NLS-1$
        certificateData.put("keyalg", cKeyAlgorithm.getText()); //$NON-NLS-1$
        certificateData.put("CN", tCommonName.getText()); //$NON-NLS-1$
        certificateData.put("OU", tOrganizationalUnit.getText()); //$NON-NLS-1$
        certificateData.put("O", tOrganization.getText()); //$NON-NLS-1$
        certificateData.put("L", tLocation.getText()); //$NON-NLS-1$
        certificateData.put("ST", tState.getText()); //$NON-NLS-1$
        certificateData.put("C", tCountry.getText()); //$NON-NLS-1$
        certificateData.put("keypass", tKeyPassword.getText()); //$NON-NLS-1$
        certificateData.put("storepass", tKeyStorePassword.getText()); //$NON-NLS-1$

        Certificate cert = new Certificate();
        generatedKeystore = cert.generateKeystoreAndCertificate(certificateData);

        if (generatedKeystore) {
            lResult.setText(NLS.bind(Messages.keystoreGenerated, new Object[] {keystoreName, name}));
            updateStatus(null);
        } else {
            lResult.setText(Messages.keystoreGenerationFailed);
        }
    }

    /**
     * Called on enter of the page to fill the certificate type combo box based on the Basic
     * Security Profile selection on the first wizard page. Preselects a default value in the combo
     * box.
     */
    public void onEnterPage() {
        if (signature.getBsp()) { // BSP selected
            cKeyAlgorithm.setItems(Algorithms.CERTIFICATE_ALGORITHMS_RSA);
            cKeyAlgorithm.select(0);
        } else { // BSP not selected
            cKeyAlgorithm.setItems(Algorithms.CERTIFICATE_ALGORITHMS);
            cKeyAlgorithm.select(0);
        }
        setMessage(null);
    }

    /**
     * Returns the next wizard page after all the necessary data is entered correctly.
     *
     * @return IWizardPage The next wizard page
     */
    public IWizardPage getNextPage() {
        saveDataToModel();
        PageAlgorithms page = ((NewSignatureWizard) getWizard()).getPageAlgorithms();
        page.onEnterPage();
        return page;
    }

    /**
     * Saves the selections on this wizard page to the model. Called on exit of the page.
     */
    private void saveDataToModel() {
        signature.setKeystore(keystore);
        signature.setKeystorePassword(tKeyStorePassword.getText().toCharArray());
        signature.setKeyPassword(tKeyPassword.getText().toCharArray());
        signature.setKeyAlias(tKeyName.getText());
        if (cKeyAlgorithm.getText().equals("DSA")) {
            signature.setKeyAlgorithm("SHA1withDSA");
        } else if (cKeyAlgorithm.getText().equals("EC")) {
            signature.setKeyAlgorithm("SHA1withECDSA");
        } else if (cKeyAlgorithm.getText().equals("RSA")) {
            signature.setKeyAlgorithm("SHA1withRSA");
        }
    }
}
