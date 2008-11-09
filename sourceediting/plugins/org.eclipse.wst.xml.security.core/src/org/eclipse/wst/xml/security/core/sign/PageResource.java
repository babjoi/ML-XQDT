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

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.security.core.utils.Globals;
import org.eclipse.wst.xml.security.core.utils.IContextHelpIds;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.eclipse.wst.xml.security.core.utils.XpathDialog;
import org.w3c.dom.Document;

/**
 * <p>First page of the wizard to create an XML signature. Lets the user select the resource to sign
 * (<i>document</i>, <i>selection</i>, <i>XPath</i>) and the signature type (<i>enveloping</i>,
 * <i>enveloped</i>, <i>detached</i>).<br>
 * The option to create a <i>Basic Security Profile</i> compliant signature can be activated on this
 * page too.<br>
 * Also lets the user select to open an existing <i>X.509 certificate</i> or to create a new
 * certificate. The next wizard page is based on this decision.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
// TODO detached signature with file list to sign (from one to many)
// TODO checkbox for manifest generation (only active if detached selected?)
public class PageResource extends WizardPage implements Listener {
    /** Wizard page name. */
    public static final String PAGE_NAME = "SignPageResource"; //$NON-NLS-1$
    /** Path of the opened project. */
    private String project;
    /** Select detached file button. */
    private Button bSelectDetachedFile = null;
    /** Browse XPath button. */
    private Button bBrowseXpath = null;
    /** Activate BSP checkbox. */
    private Button bBsp = null;
    /** Radio to sign the complete document. */
    private Button bDocument = null;
    /** Radio to create a detached signature. */
    private Button bDetached = null;
    /** Radio to create an enveloped signature. */
    private Button bEnveloped = null;
    /** Radio to create an enveloping signature. */
    private Button bEnveloping = null;
    /** Radio to sign the selected document fragment. */
    private Button bSelection = null;
    /** Radio to sign with the XPath expression. */
    private Button bXpath = null;
    /** Radio to create a new certificate and a new keystore. */
    private Button bCreateKeystore = null;
    /** Radio to create a new certificate in an existing keystore. */
    private Button bCreateCertificate = null;
    /** Radio to open an existing certificate in an existing keystore. */
    private Button bOpenCertificate = null;
    /** Textfield for the detached file to sign. */
    private Text tDetachedFile = null;
    /** Textfield for the XPath expression to sign. */
    private Text tXpath = null;
    /** Selected file to sign. */
    private IFile file = null;
    /** Text selection in document flag. */
    private boolean textSelection;
    /** Selected XML document to sign. */
    private Document document = null;
    /** Empty text field. */
    private static final String EMPTY = "";
    /** Stored setting for the BSP signature selection. */
    private static final String SETTING_BSP_COMPLIANT_SIGNATURE = "sign_bsp_compliant";
    /** Model for the XML Digital Signature Wizard. */
    private Signature signature = null;

    /**
     * Constructor for the selection page of the wizard. Takes two parameters.
     *
     * @param signature The signature wizard model
     * @param file The selected file
     * @param project The path of the opened project
     * @param textSelection Status of text selection in editor
     */
    public PageResource(final Signature signature, final IFile file,
        final String project, final boolean textSelection) {
        super(PAGE_NAME);
        setTitle(Messages.signatureTitle);
        setDescription(Messages.resourceDescription);

        this.signature = signature;
        this.file = file;
        this.project = project;
        this.textSelection = textSelection;

        try {
            document = Utils.parse(file);
        } catch (Exception ex) {
            updateStatus(Messages.documentInvalid);
        }
    }

    /**
     * Creates the wizard page with the layout settings.
     *
     * @param parent The parent Composite
     */
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        FormLayout formLayout = new FormLayout();
        container.setLayout(formLayout);

        createPageContent(container);
        addListeners();
        setControl(container);
        loadSettings();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IContextHelpIds.WIZARD_SIGNATURE_RESOURCE);
    }

    /**
     * Fills this wizard page with content. Four groups (<i>Resource</i>, <i>Signature Type</i>,
     * <i>Key</i> and <i>Basic Security Profile</i>) and all their widgets are inserted.
     *
     * @param parent The parent Composite
     */
    private void createPageContent(final Composite parent) {
        FormLayout layout = new FormLayout();
        layout.marginTop = Globals.MARGIN / 2;
        layout.marginBottom = Globals.MARGIN / 2;
        layout.marginLeft = Globals.MARGIN / 2;
        layout.marginRight = Globals.MARGIN / 2;
        parent.setLayout(layout);

        // Four groups
        Group gResource = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gResource.setLayout(layout);
        gResource.setText(Messages.resource);
        FormData data = new FormData();
        data.top = new FormAttachment(0, 0);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gResource.setLayoutData(data);

        Group gType = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gType.setLayout(layout);
        gType.setText(Messages.signatureType);
        data = new FormData();
        data.top = new FormAttachment(gResource, Globals.MARGIN, SWT.DEFAULT);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gType.setLayoutData(data);

        Group gKey = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gKey.setLayout(layout);
        gKey.setText(Messages.key);
        data = new FormData();
        data.top = new FormAttachment(gType, Globals.MARGIN, SWT.DEFAULT);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gKey.setLayoutData(data);

        Group gBsp = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gBsp.setLayout(layout);
        gBsp.setText(Messages.basicSecurityProfile);
        data = new FormData();
        data.top = new FormAttachment(gKey, Globals.MARGIN, SWT.DEFAULT);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gBsp.setLayoutData(data);

        // Elements for group "Resource"
        bDocument = new Button(gResource, SWT.RADIO);
        bDocument.setText(Messages.document);
        bDocument.setSelection(true);
        data = new FormData();
        data.top = new FormAttachment(gResource);
        data.left = new FormAttachment(gResource);
        bDocument.setLayoutData(data);

        bSelection = new Button(gResource, SWT.RADIO);
        bSelection.setText(Messages.selection);
        bSelection.setEnabled(textSelection);
        data = new FormData();
        data.top = new FormAttachment(bDocument, Globals.MARGIN);
        data.left = new FormAttachment(gResource);
        bSelection.setLayoutData(data);

        bXpath = new Button(gResource, SWT.RADIO);
        bXpath.setText(Messages.xpath);
        data = new FormData();
        data.top = new FormAttachment(bSelection, Globals.MARGIN);
        data.left = new FormAttachment(gResource);
        bXpath.setLayoutData(data);

        tXpath = new Text(gResource, SWT.SINGLE);
        tXpath.setEnabled(false);
        data = new FormData();
        data.top = new FormAttachment(bXpath, 0, SWT.CENTER);
        data.left = new FormAttachment(gResource, 100);
        data.width = Globals.MEDIUM_TEXT_WIDTH;
        tXpath.setLayoutData(data);

        bBrowseXpath = new Button(gResource, SWT.PUSH);
        bBrowseXpath.setText(Messages.browse);
        bBrowseXpath.setEnabled(false);
        data = new FormData();
        data.top = new FormAttachment(bXpath, 0, SWT.CENTER);
        data.left = new FormAttachment(tXpath, Globals.MARGIN);
        bBrowseXpath.setLayoutData(data);

        // Elements for group "Signature Type"
        bEnveloping = new Button(gType, SWT.RADIO);
        bEnveloping.setText(Messages.enveloping);
        bEnveloping.setSelection(true);
        data = new FormData();
        data.top = new FormAttachment(gType);
        data.left = new FormAttachment(gType);
        bEnveloping.setLayoutData(data);

        bEnveloped = new Button(gType, SWT.RADIO);
        bEnveloped.setText(Messages.enveloped);
        data = new FormData();
        data.top = new FormAttachment(bEnveloping, Globals.MARGIN);
        data.left = new FormAttachment(gType);
        bEnveloped.setLayoutData(data);

        bDetached = new Button(gType, SWT.RADIO);
        bDetached.setText(Messages.detached);
        data = new FormData();
        data.top = new FormAttachment(bEnveloped, Globals.MARGIN);
        data.left = new FormAttachment(gType);
        bDetached.setLayoutData(data);

        tDetachedFile = new Text(gType, SWT.SINGLE);
        tDetachedFile.setEnabled(false);
        data = new FormData();
        data.top = new FormAttachment(bDetached, 0, SWT.CENTER);
        data.left = new FormAttachment(gType, 100);
        data.width = Globals.MEDIUM_TEXT_WIDTH;
        tDetachedFile.setLayoutData(data);

        bSelectDetachedFile = new Button(gType, SWT.PUSH);
        bSelectDetachedFile.setText(Messages.select);
        bSelectDetachedFile.setEnabled(false);
        data = new FormData();
        data.top = new FormAttachment(bDetached, 0, SWT.CENTER);
        data.left = new FormAttachment(tDetachedFile, Globals.MARGIN);
        bSelectDetachedFile.setLayoutData(data);

        // Elements for group "Certificate"
        bOpenCertificate = new Button(gKey, SWT.RADIO);
        bOpenCertificate.setText(Messages.useKey);
        bOpenCertificate.setSelection(true);
        data = new FormData();
        data.top = new FormAttachment(gKey);
        data.left = new FormAttachment(gKey);
        bOpenCertificate.setLayoutData(data);

        bCreateCertificate = new Button(gKey, SWT.RADIO);
        bCreateCertificate.setText(Messages.createKey);
        data = new FormData();
        data.top = new FormAttachment(bOpenCertificate, Globals.MARGIN);
        data.left = new FormAttachment(gKey);
        bCreateCertificate.setLayoutData(data);

        bCreateKeystore = new Button(gKey, SWT.RADIO);
        bCreateKeystore.setText(Messages.createKeystoreAndKey);
        data = new FormData();
        data.top = new FormAttachment(bCreateCertificate, Globals.MARGIN);
        data.left = new FormAttachment(gKey);
        bCreateKeystore.setLayoutData(data);

        // Elements for group "Basic Security Profile"
        bBsp = new Button(gBsp, SWT.CHECK);
        bBsp.setText(Messages.bspCompliant);
        data = new FormData();
        data.top = new FormAttachment(gBsp);
        data.left = new FormAttachment(gBsp);
        bBsp.setLayoutData(data);
    }

    /**
     * Adds all listeners for the current wizard page.
     */
    private void addListeners() {
        bSelectDetachedFile.addListener(SWT.Selection, this);
        bBrowseXpath.addListener(SWT.Selection, this);
        bBsp.addListener(SWT.Selection, this);
        bDocument.addListener(SWT.Selection, this);
        bDetached.addListener(SWT.Selection, this);
        bEnveloped.addListener(SWT.Selection, this);
        bEnveloping.addListener(SWT.Selection, this);
        bSelection.addListener(SWT.Selection, this);
        bXpath.addListener(SWT.Selection, this);
        tDetachedFile.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
        tXpath.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
    }

    /**
     * Determines the (error) message for the missing field.
     */
    private void dialogChanged() {
        if (bXpath.getSelection() && tXpath.getText().length() == 0) {
            updateStatus(Messages.enterXPath);
            return;
        } else if (bXpath.getSelection() && tXpath.getText().length() > 0) {
            String xpathValidator = Utils.validateXPath(document, tXpath.getText());
            if (xpathValidator.equals("none")) { //$NON-NLS-1$
                setErrorMessage(Messages.xpathNoElement);
                return;
            } else if (xpathValidator.equals("multiple")) { //$NON-NLS-1$
                setErrorMessage(Messages.xpathMultipleElements);
                return;
            } else if (xpathValidator.equals("attribute")) { //$NON-NLS-1$
                setErrorMessage(Messages.xpathAttribute);
                return;
            }
        }
        if (bDetached.getSelection() && tDetachedFile.getText().length() == 0) {
            updateStatus(Messages.detachedFile);
            return;
        } else if (bDetached.getSelection() && tDetachedFile.getText().length() > 0) {
            File tempFile = new File(tDetachedFile.getText());
            if (!tempFile.exists()) {
                setErrorMessage(Messages.verifyDetachedFile);
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
        if (message == null && getErrorMessage() == null) {
            setPageComplete(true);
            saveDataToModel();
        } else {
            setPageComplete(false);
        }
    }

    /**
     * Selection dialog to select the XPath for the element to sign.
     */
    private void openXPathDialog() {
        XpathDialog dialog = new XpathDialog(getShell(), new LabelProvider(), file,
                Messages.xpathPopup);
        if (dialog.getReturnCode() == 0) {
            Object[] selected = dialog.getResult();
            if (selected.length == 1) {
                tXpath.setText(selected[0].toString());
            }
        }
    }

    /**
     * Opens a standard file dialog to select the detached file to sign.
     */
    private void selectDetachedFile() {
        FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
        dialog.setFilterPath(project);
        dialog.setFilterNames(Globals.DETACHED_FILE_EXTENSION_NAME);
        dialog.setFilterExtensions(Globals.DETACHED_FILE_EXTENSION);
        String detachedFile = dialog.open();
        if (detachedFile != null && detachedFile.length() > 0) {
            tDetachedFile.setText(detachedFile);
        }
    }

    /**
     * Handles the events from this wizard page. Affects mainly the (de-) activation state of radio
     * buttons.
     *
     * @param e The triggered event
     */
    public void handleEvent(final Event e) {
        if (e.widget == bDocument || e.widget == bSelection) { // Radios Complete document or Selection
            tXpath.setEnabled(false);
            tXpath.setText(EMPTY);
            bBrowseXpath.setEnabled(false);
        } else if (e.widget == bXpath) { // Radio XPath
            tXpath.setEnabled(true);
            bBrowseXpath.setEnabled(true);
        } else if (e.widget == bBrowseXpath) { // Button Browse XPath
            openXPathDialog();
        } else if (e.widget == bEnveloping || e.widget == bEnveloped) { // Radio Enveloping and Enveloped
            bSelectDetachedFile.setEnabled(false);
            tDetachedFile.setEnabled(false);
            tDetachedFile.setText(EMPTY);
            bXpath.setEnabled(true);

            if (textSelection) {
                bSelection.setEnabled(true);
            }
        } else if (e.widget == bDetached) { // Radio Detached
            bDocument.setSelection(true);
            bSelection.setSelection(false);
            bXpath.setSelection(false);
            bSelection.setEnabled(false);
            bXpath.setEnabled(false);
            tXpath.setText(EMPTY);
            tXpath.setEnabled(false);
            bBrowseXpath.setEnabled(false);
            tDetachedFile.setEnabled(true);
            bSelectDetachedFile.setEnabled(true);
        } else if (e.widget == bSelectDetachedFile) { // Button Select detached file
            selectDetachedFile();
        } else if (e.widget == bBsp) { // Checkbox BSP
            if (bBsp.getSelection()) {
                bDocument.setSelection(true);
                bSelection.setSelection(false);
                bXpath.setSelection(false);
                bSelection.setEnabled(false);
                bXpath.setEnabled(false);
                tXpath.setText(EMPTY);
                tXpath.setEnabled(false);
                tDetachedFile.setEnabled(true);
                bSelectDetachedFile.setEnabled(true);
                bEnveloping.setEnabled(false);
                bEnveloping.setSelection(false);
                bEnveloped.setSelection(false);
                bDetached.setSelection(true);
                dialogChanged();
            } else {
                if (textSelection && !bDetached.getSelection()) {
                    bSelection.setEnabled(true);
                }
                if (!bDetached.getSelection()) {
                    bXpath.setEnabled(true);
                }
                bEnveloping.setEnabled(true);
            }
        }
    }

    /**
     * Saves the data to the model first. Then returns the next wizard page after all the necessary
     * data is entered correctly. The next page is by default <i>PageOpenCertificate</i>. If the
     * user chooses to create a new certificate and a new keystore the next wizard page is
     * <i>PageCreateKeystore</i>. If the user chosses to create a new certificate in an existing
     * keystore the next wizard page is <i>PageCreateCertificate</i>.
     *
     * @return IWizardPage The next wizard page
     */
    public IWizardPage getNextPage() {
        saveDataToModel();

        if (bCreateCertificate.getSelection()) {
            PageCreateKey page = ((NewSignatureWizard) getWizard()).getPageCreateKey();
            page.onEnterPage();
            return page;
        } else if (bCreateKeystore.getSelection()) {
            PageCreateKeystore page = ((NewSignatureWizard) getWizard()).getPageCreateKeystore();
            page.onEnterPage();
            return page;
        } else {
            PageOpenKey page = ((NewSignatureWizard) getWizard()).getPageOpenKey();
            page.onEnterPage();
            return page;
        }
    }

    /**
     * Saves the selections on this wizard page to the model. Called on exit of the page.
     */
    private void saveDataToModel() {
        if (bDocument.getSelection()) {
            signature.setResource("document"); //$NON-NLS-1$
        } else if (bSelection.getSelection()) {
            signature.setResource("selection"); //$NON-NLS-1$
        } else if (bXpath.getSelection()) {
            signature.setResource("xpath"); //$NON-NLS-1$
            signature.setXpath(tXpath.getText());
        }
        if (bEnveloping.getSelection()) {
            signature.setSignatureType("enveloping"); //$NON-NLS-1$
        } else if (bEnveloped.getSelection()) {
            signature.setSignatureType("enveloped"); //$NON-NLS-1$
        } else if (bDetached.getSelection()) {
            signature.setSignatureType("detached"); //$NON-NLS-1$
            signature.setDetachedFile(new File(tDetachedFile.getText()));
        }
        signature.setBsp(bBsp.getSelection());
        signature.setFile(file.getLocation().toString());
    }

    /**
     * Loads the stored settings for this wizard page.
     */
    private void loadSettings() {
        String bspCompliantSignature = getDialogSettings().get(SETTING_BSP_COMPLIANT_SIGNATURE);
        boolean doBSPCompliantSignature = false;
        if (bspCompliantSignature != null) {
            doBSPCompliantSignature = getDialogSettings().getBoolean(
                    SETTING_BSP_COMPLIANT_SIGNATURE);
        }
        bBsp.setSelection(doBSPCompliantSignature);
    }

    /**
     * Stores some settings of this wizard page in the current workspace.
     */
    protected void storeSettings() {
        IDialogSettings settings = getDialogSettings();
        settings.put(SETTING_BSP_COMPLIANT_SIGNATURE, bBsp.getSelection());
    }
}
