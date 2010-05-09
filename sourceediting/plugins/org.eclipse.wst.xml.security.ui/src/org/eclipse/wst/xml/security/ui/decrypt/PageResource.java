/*******************************************************************************
 * Copyright (c) 2010 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.ui.decrypt;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.security.core.decrypt.Decryption;
import org.eclipse.wst.xml.security.core.utils.Globals;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.eclipse.wst.xml.security.ui.utils.IContextHelpIds;

/**
 * <p>First page of the <strong>XML Decryption Wizard</strong>. Lets the user
 * select the encryption id for the encrypted document part to decrypt.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class PageResource extends WizardPage implements Listener {
    /** Wizard page name. */
    public static final String PAGE_NAME = "DecryptPageResource"; //$NON-NLS-1$
    /** Combo box <i>Encryption ID</i>. */
    private Combo cEncryptionId = null;
    /** The XML document. */
    private IFile file;
    /** Model for the XML Decryption Wizard. */
    private Decryption decryption = null;

    /**
     * Constructor for the algorithms page with three parameters.
     *
     * @param decryption The decryption wizard model
     * @param file The selected file
     */
    public PageResource(final Decryption decryption, final IFile file) {
        super(PAGE_NAME);
        setTitle(Messages.decryptionTitle);
        setDescription(Messages.resourceDescription);

        this.decryption = decryption;
        this.file = file;
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
        determineIds();
        addListeners();
        setControl(container);
        loadSettings();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IContextHelpIds.WIZARD_DECRYPTION_RESOURCE);
    }

    /**
     * Fills this wizard page with content. Two groups (<i>Type</i> and
     * <i>Encryption ID</i>) and all their widgets are inserted.
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
        Group gEncryptionId = new Group(parent, SWT.SHADOW_ETCHED_IN);
        gEncryptionId.setLayout(layout);
        gEncryptionId.setText(Messages.encryptionId);
        FormData data = new FormData();
        data.top = new FormAttachment(0, 0);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(Globals.GROUP_NUMERATOR);
        gEncryptionId.setLayoutData(data);

        // Elements for group "Encryption ID"
        cEncryptionId = new Combo(gEncryptionId, SWT.READ_ONLY);
        data = new FormData();
        data.top = new FormAttachment(gEncryptionId);
        data.left = new FormAttachment(gEncryptionId);
        data.width = Globals.COMBO_LARGE_WIDTH;
        cEncryptionId.setLayoutData(data);
    }

    /**
     * Determines all available encryption IDs in the XML document.<br>
     * The entry <i>Use first encryption ID</i> is always available and
     * selected per default.
     */
    private void determineIds() {
        String[] ids = null;

        try {
            ids = Utils.getIds(file.getContents(), "encryption");
        } catch (Exception ex) {
            ids = new String[] {};
        }

        cEncryptionId.setItems(ids);
        cEncryptionId.select(0);
    }

    /**
     * Adds all listeners for the current wizard page.
     */
    private void addListeners() {
        cEncryptionId.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });
    }

    /**
     * Shows a message to the user to complete the fields on this page.
     *
     * @param message The message for the user
     * @param status The status type of the message
     */
    private void updateStatus(final String message, final int status) {
        setMessage(message, status);
        if (message == null && getErrorMessage() == null) {
            setPageComplete(true);
            saveDataToModel();
        } else {
            setPageComplete(false);
        }
    }

    /**
     * Determines the (error) message for the missing field.
     */
    private void dialogChanged() {
    	if ("".equals(cEncryptionId.getText())) {
            updateStatus(Messages.missingEncryptionId, DialogPage.INFORMATION);
            return;
        }

        updateStatus(null, DialogPage.NONE);
    }

    /**
     * Handles the events from this wizard page.
     *
     * @param e The triggered event
     */
    public void handleEvent(final Event e) {
    }

    /**
     * Sets the completed field on the wizard class when all the data is entered and
     * the wizard can be finished.
     *
     * @return Page is completed or not
     */
    public boolean isPageComplete() {
        saveDataToModel();
        if (getMessage() == null && getErrorMessage() == null) {
            return true;
        }
        return false;
    }

    /**
     * Saves the selections on this wizard page to the model. Called on exit of the page.
     */
    private void saveDataToModel() {
        decryption.setEncryptionId(cEncryptionId.getText());

        storeSettings();
    }

    /**
     * Loads the stored settings for this wizard page.
     */
    private void loadSettings() {
    }

    /**
     * Stores some settings of this wizard page in the current workspace.
     */
    private void storeSettings() {
    }
}
