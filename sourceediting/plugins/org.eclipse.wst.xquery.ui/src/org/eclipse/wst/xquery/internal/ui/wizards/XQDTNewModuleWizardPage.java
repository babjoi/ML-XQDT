/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *     Gabriel Petrovay (28msec)
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.ui.wizards;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.ui.wizards.Messages;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wst.xquery.core.XQDTNature;

@SuppressWarnings("restriction")
public class XQDTNewModuleWizardPage extends NewSourceModulePage {

    private static final String FILE = "NewSourceModulePage.file";

    private SelectionButtonDialogField fMainModuleField;
    private SelectionButtonDialogField fLibraryModuleField;
    private StringDialogField fPrefixField;
    private StringDialogField fNamespaceField;

    private IDialogFieldListener fListener = new XQueryModuleFieldModule();

    private boolean fHasError = false;
    private boolean fUserChanged = false;

    private class XQueryModuleFieldModule implements IDialogFieldListener {

        public void dialogFieldChanged(DialogField field) {
            if (field == fMainModuleField) {
                return;
            } else if (field == fLibraryModuleField) {
                if (fLibraryModuleField.isSelected()) {
                    enableLibraryModule();
                } else {
                    disableLibraryModule();
                }
            }

            if (XQDTNewModuleWizardPage.this.getErrorMessage() != null && !fHasError) {
                return;
            }

            // Extra file name validation
            String fileName = getFileText().trim();
            if (fileName.equals("")) {
                reportError(Messages.NewSourceModulePage_InvalidFileName);
                return;
            }

            // Prefix validation
            String aPrefix = fPrefixField.getText().trim();
            if (fPrefixField.isEnabled()) {
                if (aPrefix.length() == 0) {
                    reportError("A prefix must be set.");
                    return;
                }
                if (!validatePrefix(aPrefix)) {
                    reportError("Invalid prefix.");
                    return;
                }
            }

            // Namespace validation
            String uriStr = fNamespaceField.getText().trim();
            if (fNamespaceField.isEnabled()) {
                try {
                    new URI(uriStr);
                } catch (URISyntaxException e) {
                    reportError("Invalid URI: " + e.getMessage());
                    return;
                }
            }
            setPageComplete(true);
            handleFieldChanged(getFileName());
            fHasError = false;
        }

        private void reportError(String message) {
            fHasError = true;
            setErrorMessage(message);
            setPageComplete(false);
        }
    }

    public XQDTNewModuleWizardPage() {
        super();
    }

    @Override
    public void setErrorMessage(String newMessage) {
        super.setErrorMessage(newMessage);
        if (isCurrentPage()) {
            getContainer().updateMessage();
        }
    }

    protected String getPageDescription() {
        return "Create a new XQuery module";
    }

    protected String getPageTitle() {
        return "XQuery Module";
    }

    protected String getRequiredNature() {
        return XQDTNature.NATURE_ID;
    }

    private XQDTNewModuleWizardPage enableLibraryModule() {
        fPrefixField.setEnabled(true);
        fNamespaceField.setEnabled(true);
        return this;
    }

    private XQDTNewModuleWizardPage disableLibraryModule() {
        fPrefixField.setEnabled(false);
        fNamespaceField.setEnabled(false);
        fUserChanged = false;
        return this;
    }

    @Override
    protected void createContentControls(Composite composite, final int nColumns) {
        composite = new Composite(composite, SWT.PUSH);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Composite subComposite = new Composite(composite, SWT.PUSH);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        subComposite.setLayout(layout);
        subComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        super.createContentControls(subComposite, nColumns);

        final Group group = new Group(composite, SWT.PUSH);
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setLayout(initGridLayout(new GridLayout(3, false), true));
        group.setText("Module settings");

        fMainModuleField = new SelectionButtonDialogField(SWT.RADIO);
        fMainModuleField.setLabelText("Main module");
        fMainModuleField.setSelection(true);
        fMainModuleField.setDialogFieldListener(fListener);
        fMainModuleField.doFillIntoGrid(group, 3);

        fLibraryModuleField = new SelectionButtonDialogField(SWT.RADIO);
        fLibraryModuleField.setLabelText("Library module");
        fLibraryModuleField.setDialogFieldListener(fListener);
        fLibraryModuleField.doFillIntoGrid(group, 3);

        fPrefixField = new StringDialogField();
        fPrefixField.setLabelText("Prefix:");
        fPrefixField.setDialogFieldListener(fListener);
        fPrefixField.doFillIntoGrid(group, 3);
        LayoutUtil.setHorizontalGrabbing(fPrefixField.getTextControl(null));
        fPrefixField.setEnabled(false);

        fNamespaceField = new StringDialogField();
        fNamespaceField.setDialogFieldListener(fListener);
        fNamespaceField.setLabelText("Namespace:");
        fNamespaceField.doFillIntoGrid(group, 3);
        LayoutUtil.setHorizontalGrabbing(fNamespaceField.getTextControl(null));
        fNamespaceField.setEnabled(false);

        // stop automatic completion when the
        // user focuses on one of these fields
        FocusAdapter fa = new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                fUserChanged = true;
            }
        };
        fPrefixField.getTextControl(null).addFocusListener(fa);
        fNamespaceField.getTextControl(null).addFocusListener(fa);
    }

    @Override
    protected String getFileContent() {
        String content = "";//$NON-NLS-2$
        if (fMainModuleField.isSelected()) {
            content = "(: XQuery main module :)\n\n.";
        } else {
            String prefix = this.fPrefixField.getText();
            String namespace = this.fNamespaceField.getText();
            content = "module namespace " + prefix + " = '" + namespace + "';\n\n";
        }
        return content;
    }

    protected GridLayout initGridLayout(GridLayout layout, boolean margins) {
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        if (margins) {
            layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
            layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        } else {
            layout.marginWidth = 0;
            layout.marginHeight = 0;
        }
        return layout;
    }

    @Override
    protected void handleFieldChanged(String fieldName) {
        super.handleFieldChanged(fieldName);

        if (fieldName.equals(FILE)) {
            String fileName = getFileText().trim();
            if (!fUserChanged) {
                fPrefixField.setText(generatePrefix(fileName));
                fNamespaceField.setText(generateNamespace(fileName));
            }
        }
    }

    // these could come from a preference page
    private static final String NAMESPACE_PREFIX = "http://www.example.com/";
    private static final int PREFIX_LENGTH = 4;

    private String generatePrefix(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return "";
        }

        String prefix = "";
        if (!Character.isJavaIdentifierStart(fileName.charAt(0))) {
            prefix = "_" + fileName.substring(0, Math.min(fileName.length(), PREFIX_LENGTH - 1));
        } else {
            prefix = fileName.substring(0, Math.min(fileName.length(), PREFIX_LENGTH));
        }

        prefix = prefix.trim().toLowerCase();
        return validatePrefix(prefix) ? prefix : "";
    }

    private String generateNamespace(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return NAMESPACE_PREFIX;
        }

        String encodedName = "";
        try {
            encodedName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        return NAMESPACE_PREFIX + encodedName;
    }

    private boolean validatePrefix(String prefix) {
        return prefix.matches("[a-zA-Z_][0-9a-zA-Z_\\.\\-]*");
    }

}
