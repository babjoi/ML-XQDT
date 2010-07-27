/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.ui.wizards;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.ComboDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

@SuppressWarnings({ "restriction" })
public class SETNewProjectWizardFirstPage extends ProjectWizardFirstPage {

//    private final class SETCoreSDKGroup extends AbstractInterpreterGroup {
//
//        public SETCoreSDKGroup(Composite composite) {
//            super(composite);
//        }
//
//        protected String getIntereprtersPreferencePageId() {
//            return ISETUIConstants.ID_INTEREPRTERS_PREFERENCE_PAGE;
//        }
//
//        public boolean hasWorkspaceCoreSDK() {
//            IInterpreterInstallType[] types = ScriptRuntime.getInterpreterInstallTypes(getCurrentLanguageNature());
//            IEnvironment environment = fLocationGroup.getEnvironment();
//            for (int i = 0; i < types.length; i++) {
//                IInterpreterInstallType type = types[i];
//                IInterpreterInstall[] installs = type.getInterpreterInstalls();
//                for (int j = 0; j < installs.length; j++) {
//                    IInterpreterInstall install = installs[j];
//                    String envId = install.getEnvironmentId();
//                    if (envId != null && envId.equals(environment.getId())) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        @Override
//        public void dialogFieldChanged(DialogField field) {
//            super.dialogFieldChanged(field);
//            if (!(field == fInterpreterCombo) || field.isEnabled()) {
//                if (fSausalitoGroup != null) {
//                    fSausalitoGroup.initializeTemplates();
//                }
//            }
//        }
//    };

    private final class SETProjectSettingsGroup extends Observable implements Observer, IDialogFieldListener {

        protected final SelectionButtonDialogField fNewRadioButton;
        protected final StringDialogField fProjectUriText;
        protected final SelectionButtonDialogField fTemplateRadioButton;
        protected final ComboDialogField fProjectTemplateCombo;
        private String[] templates;

        /**
         * Initialize a grid layout with the default Dialog settings.
         */
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

        public SETProjectSettingsGroup(Composite composite) {
            final int numColumns = 3;
            final Group group = new Group(composite, SWT.NONE);
            group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            group.setLayout(initGridLayout(new GridLayout(numColumns, false), true));
            group.setText("Sausalito settings");
            fNewRadioButton = new SelectionButtonDialogField(SWT.RADIO);
            fNewRadioButton.setDialogFieldListener(this);
            fNewRadioButton.setLabelText("Create an empty project");
            fNewRadioButton.doFillIntoGrid(group, numColumns);

            fProjectUriText = new StringDialogField();
            fProjectUriText.setDialogFieldListener(this);
            fProjectUriText.setLabelText("Project logical URI:");
            fProjectUriText.doFillIntoGrid(group, numColumns);
            LayoutUtil.setHorizontalGrabbing(fProjectUriText.getTextControl(null));
            fNewRadioButton.attachDialogFields(new DialogField[] { fProjectUriText });

            fTemplateRadioButton = new SelectionButtonDialogField(SWT.RADIO);
            fTemplateRadioButton.setDialogFieldListener(this);
            fTemplateRadioButton.setLabelText("Create project from a template");
            fTemplateRadioButton.doFillIntoGrid(group, numColumns);

            fProjectTemplateCombo = new ComboDialogField(SWT.DROP_DOWN | SWT.READ_ONLY);
            fProjectTemplateCombo.setLabelText("Project template:");
            fProjectTemplateCombo.setDialogFieldListener(this);
            fProjectTemplateCombo.doFillIntoGrid(group, numColumns);
            LayoutUtil.setHorizontalGrabbing(fProjectTemplateCombo.getComboControl(null));
            fTemplateRadioButton.attachDialogFields(new DialogField[] { fProjectTemplateCombo });

            initializeControls();

        }

        private void initializeControls() {
            fNewRadioButton.setSelection(true);
            fTemplateRadioButton.setSelection(false);
            initializeTemplates();
        }

        private void initializeTemplates() {
            try {
                if (getInterpreterGroup().getSelectedInterpreter() == null) {
                    templates = CoreSDKTemplateUtility.getTemplateProjectNames();
                } else {
                    templates = CoreSDKTemplateUtility.getTemplateProjectNames(getInterpreterGroup()
                            .getSelectedInterpreter());
                }
                fProjectTemplateCombo.setItems(templates);
                if (templates.length > 0) {
                    fProjectTemplateCombo.setText(templates[0]);
                    fTemplateRadioButton.setEnabled(true);
                } else {
                    fTemplateRadioButton.setEnabled(false);
                }
            } catch (CoreException e) {
            }
        }

        protected void fireEvent(boolean hasChanged) {
            if (hasChanged) {
                setChanged();
            }
            notifyObservers();
        }

        public void update(Observable o, Object arg) {
            if (o == getInterpreterGroup()) {
                initializeTemplates();
            }
            if (isNewProject()) {
                fProjectUriText.setText(getSampleProjectUri());
            }
            fireEvent(true);
        }

        private String getSampleProjectUri() {
            if (fNewRadioButton.isSelected()) {
                String encodedName = "";
                try {
                    encodedName = URLEncoder.encode(fNameGroup.getName(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }

                if (encodedName.length() > 0) {
                    return "http://www.example.com/" + encodedName + "/";
                }
            }
            return "";
        }

        public void dialogFieldChanged(DialogField field) {
            boolean hasChanged = false;
            if (field == fNewRadioButton) {
                fProjectUriText.setText(getSampleProjectUri());
                hasChanged = true;
            } else if (field == fProjectUriText) {
                hasChanged = true;
            }
            fireEvent(hasChanged);
        }

        public boolean isTemplateProject() {
            return fTemplateRadioButton.isSelected() && !fNewRadioButton.isSelected();
        }

        public boolean isNewProject() {
            return !fTemplateRadioButton.isSelected() && fNewRadioButton.isSelected();
        }

        public String getProjectUriString() {
            return fProjectUriText.getText().trim();
        }

        public String getTemplateName() {
            return fProjectTemplateCombo.getText();
        }
    }

    private final class Validator implements Observer {

        public void update(Observable o, Object arg) {

//            if (supportInterpreter() && interpeterRequired()) {
//                if (getInterpreter() == null && !fCoreSDKGroup.hasWorkspaceCoreSDK()) {
//                    setErrorMessage("At least one Sausalito CoreSDK installation must be configured");
//                    setPageComplete(false);
//                    return;
//                }
//            }
            final IWorkspace workspace = DLTKUIPlugin.getWorkspace();
            final String name = fNameGroup.getName();
            // check whether the project name field is empty
            if (name.length() == 0) {
                setErrorMessage(null);
                setMessage(NewWizardMessages.ScriptProjectWizardFirstPage_Message_enterProjectName);
                setPageComplete(false);
                return;
            }
            // check whether the project name is valid
            final IStatus nameStatus = workspace.validateName(name, IResource.PROJECT);
            if (!nameStatus.isOK()) {
                setErrorMessage(nameStatus.getMessage());
                setPageComplete(false);
                return;
            }
            // check whether project already exists
            final IProject handle = getProjectHandle();
            if (handle.exists()) {
                setErrorMessage(NewWizardMessages.ScriptProjectWizardFirstPage_Message_projectAlreadyExists);
                setPageComplete(false);
                return;
            }
            final String location = fLocationGroup.getLocation().toOSString();
            // check whether location is empty
            if (location.length() == 0) {
                setErrorMessage(null);
                setMessage(NewWizardMessages.ScriptProjectWizardFirstPage_Message_enterLocation);
                setPageComplete(false);
                return;
            }
            // check whether the location is a syntactically correct path
            if (!Path.EMPTY.isValidPath(location)) {
                setErrorMessage(NewWizardMessages.ScriptProjectWizardFirstPage_Message_invalidDirectory);
                setPageComplete(false);
                return;
            }
            // check whether the location has the workspace as prefix
            IPath projectPath = Path.fromOSString(location);
            if (!fLocationGroup.isInWorkspace() && Platform.getLocation().isPrefixOf(projectPath)) {
                setErrorMessage(NewWizardMessages.ScriptProjectWizardFirstPage_Message_cannotCreateInWorkspace);
                setPageComplete(false);
                return;
            }
            // If we do not place the contents in the workspace validate the
            // location.
            if (!fLocationGroup.isInWorkspace()) {
                IEnvironment environment = getEnvironment();
                if (EnvironmentManager.isLocal(environment)) {
                    final IStatus locationStatus = workspace.validateProjectLocation(handle, projectPath);
                    if (!locationStatus.isOK()) {
                        setErrorMessage(locationStatus.getMessage());
                        setPageComplete(false);
                        return;
                    }
                }
            }

            final boolean isNewPrj = fSausalitoGroup.isNewProject();
            if (isNewPrj) {
                String projectUriStr = fSausalitoGroup.getProjectUriString();
                if (projectUriStr.length() == 0) {
                    setErrorMessage("The project logical URI must be a syntactically correct absolute URI.");
                    setPageComplete(false);
                    return;
                }

                URI projectUri = null;
                try {
                    projectUri = new URI(fSausalitoGroup.getProjectUriString());
                } catch (URISyntaxException use) {
                    setErrorMessage("The provided URI has an invalid syntax.");
                    setPageComplete(false);
                    return;
                }
                if (!projectUri.isAbsolute()) {
                    setErrorMessage("The project URI must be an absolute one.");
                    setPageComplete(false);
                    return;
                }
                if (!fSausalitoGroup.getProjectUriString().endsWith("/")) {
                    setMessage(
                            "URIs without a trailing slash have special resolving rules. Make sure your module namespaces are correct when manually creating XQuery modules.",
                            DialogPage.WARNING);
                    setPageComplete(true);
                    return;
                }
            }
            setPageComplete(true);
            setErrorMessage(null);
            setMessage(null);
        }
    }

//    protected SETCoreSDKGroup fCoreSDKGroup;
    protected SETProjectSettingsGroup fSausalitoGroup;
    private Validator fValidator;

//    public void createControl(Composite parent) {
//        initializeDialogUnits(parent);
//        final Composite composite = new Composite(parent, SWT.NULL);
//        composite.setFont(parent.getFont());
//        composite.setLayout(initGridLayout(new GridLayout(1, false), true));
//        composite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
//        // create UI elements
//        fNameGroup = new NameGroup(composite, fInitialName);
//        fLocationGroup = createLocationGroup();
//        fLocationGroup.createControls(composite);
//
//        // create and connect validator
//        fValidator = new Validator();
//
//        createInterpreterGroup(composite);
//        createCustomGroups(composite);
//
//        fCoreSDKGroup.addObserver(fValidator);
//        fCoreSDKGroup.addObserver(fSausalitoGroup);
//
//        fDetectGroup = new DetectGroup(composite);
//        // fCoreSDKGroup.addObserver(fDetectGroup);
//        // fLocationGroup.addObserver(fDetectGroup);
//
//        // initialize all elements
//        fNameGroup.notifyObservers();
//
//        fNameGroup.addObserver(fValidator);
//        fLocationGroup.addObserver(fValidator);
//
//        setControl(composite);
//        Dialog.applyDialogFont(composite);
//        if (DLTKCore.DEBUG) {
//            System.err.println("Add help support here..."); //$NON-NLS-1$
//        }
//    }

    protected void createCustomGroups(Composite composite) {
        fValidator = new Validator();

        fSausalitoGroup = new SETProjectSettingsGroup(composite);
        fSausalitoGroup.addObserver(fValidator);
        fNameGroup.addObserver(fSausalitoGroup);

        // update the available templates when changing interpreters
        getInterpreterGroupObservable().addObserver(fSausalitoGroup);
    }

    @Override
    public boolean canFlipToNextPage() {
        return false;
    }

    public boolean isTemplate() {
        return fSausalitoGroup.isTemplateProject();
    }

    public String getProjectUriString() {
        return fSausalitoGroup.getProjectUriString();
    }

    public String getTemplateName() {
        return fSausalitoGroup.getTemplateName();
    }

//    protected IInterpreterGroup createInterpreterGroup(Composite parent) {
//        if (fCoreSDKGroup == null) {
//            fCoreSDKGroup = new SETCoreSDKGroup(parent);
//        }
//        return fCoreSDKGroup;
//    }

    protected boolean interpeterRequired() {
        return true;
    }

    @Override
    public void dispose() {
        try {
            super.dispose();
        } catch (NullPointerException e) {
            System.err.println("This is an exception that is triggered in DLTK");
        }
    }
}
