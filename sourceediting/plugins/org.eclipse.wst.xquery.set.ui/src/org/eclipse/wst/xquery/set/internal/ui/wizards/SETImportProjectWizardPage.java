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

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.ComboDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.InterpreterStandin;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.launching.ScriptRuntime.DefaultInterpreterEntry;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.ui.preferences.PreferenceConstants;
import org.eclipse.wst.xquery.set.ui.ISETUIConstants;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

@SuppressWarnings("restriction")
public class SETImportProjectWizardPage extends WizardPage implements IDialogFieldListener, SelectionListener {

    protected StringButtonDialogField fDirectoryField;
    protected StringDialogField fProjectNameField;

    protected SelectionButtonDialogField fUseDefaultInterpreterField;
    protected SelectionButtonDialogField fUseProjectInterpreterField;
    protected ComboDialogField fInterpreterCombo;
    protected Link fPreferenceLink;

    protected Button fCopyButton;

    private String fInitialProjectName;

    private IInterpreterInstall[] fInstalledInterpreters;
    private String[] fComplianceLabels;
    private final IProject[] fProjects;

    protected SETImportProjectWizardPage(String pageName) {
        super(pageName);
        setTitle(pageName);
        setDescription("Import a Sausalito project from the local file system into the workspace");

        fProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    }

    public void createControl(Composite parent) {
        initializeDialogUnits(parent);

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL));
        composite.setLayout(new GridLayout(3, false));

        fDirectoryField = new StringButtonDialogField(new IStringButtonAdapter() {
            public void changeControlPressed(DialogField field) {
                File dir = browseForDirectory();
                if (dir != null) {
                    fDirectoryField.setText(dir.getPath());
                }
            }
        });
        fDirectoryField.setLabelText("Select Directory:");
        fDirectoryField.setButtonLabel("Browse...");
        fDirectoryField.doFillIntoGrid(composite, 3);
        fDirectoryField.getTextControl(null).setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        fProjectNameField = new StringDialogField();
        fProjectNameField.setLabelText("Project name:");
        fProjectNameField.doFillIntoGrid(composite, 3);

        createVerticalSpacer(composite);

        Group group = new Group(composite, SWT.NONE);
        group.setLayout(new GridLayout(3, false));
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 3;
        group.setLayoutData(gd);
        group.setText("Sausalito CoreSDK");

        createVerticalSpacer(group);

        fUseDefaultInterpreterField = new SelectionButtonDialogField(SWT.RADIO);
        fUseDefaultInterpreterField.setLabelText(getDefaultInterpreterLabel());
        fUseDefaultInterpreterField.doFillIntoGrid(group, 2);

        fPreferenceLink = new Link(group, SWT.NONE);
        fPreferenceLink.setFont(group.getFont());
        fPreferenceLink
                .setText(NewWizardMessages.ScriptProjectWizardFirstPage_InterpreterEnvironmentGroup_link_description);
        fPreferenceLink.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

        createVerticalSpacer(group);

        fUseProjectInterpreterField = new SelectionButtonDialogField(SWT.RADIO);
        fUseProjectInterpreterField
                .setLabelText(NewWizardMessages.ScriptProjectWizardFirstPage_InterpreterEnvironmentGroup_specific_compliance);
        fUseProjectInterpreterField.doFillIntoGrid(group, 1);

        fInterpreterCombo = new ComboDialogField(SWT.READ_ONLY);
        fillInstalledInterpreters(fInterpreterCombo);

        Combo comboControl = fInterpreterCombo.getComboControl(group);
        GridData gridData = new GridData(GridData.BEGINNING, GridData.CENTER, true, false);
        gridData.minimumWidth = 100;
        comboControl.setLayoutData(gridData);
        comboControl.setVisibleItemCount(20);

        createVerticalSpacer(composite);

        fCopyButton = new Button(composite, SWT.CHECK);
        fCopyButton.setText("Copy project into workspace");
        gd = new GridData();
        gd.horizontalSpan = 3;
        fCopyButton.setLayoutData(gd);

        fUseDefaultInterpreterField.setSelection(true);
        fInterpreterCombo.setEnabled(fUseProjectInterpreterField.isSelected());

        addFieldListeners();

        setPageComplete(isValid());

        setControl(composite);
    }

    protected void createVerticalSpacer(Composite parent) {
        GridData gd = new GridData(SWT.NONE);
        gd.horizontalSpan = 3;
        gd.heightHint = 0;
        Label l = new Label(parent, SWT.LEFT);
        l.setLayoutData(gd);
    }

    private File browseForDirectory() {
        String startDirectory = fDirectoryField.getText().trim();
        DirectoryDialog fileDialog = new DirectoryDialog(getShell(), SWT.OPEN);
        if (startDirectory != null) {
            fileDialog.setFilterPath(startDirectory);
        }
        String dir = fileDialog.open();
        if (dir != null) {
            dir = dir.trim();
            if (dir.length() > 0) {
                return new File(dir);
            }
        }

        return null;
    }

    private String getDefaultInterpreterLabel() {
        String name = null;
        IInterpreterInstall inst = ScriptRuntime.getDefaultInterpreterInstall(new DefaultInterpreterEntry(
                SETNature.NATURE_ID, EnvironmentManager.getLocalEnvironment().getId()));
        if (inst != null) {
            name = inst.getName();
        } else {
            name = "undefined";
        }
        return "Use def&ault interpreter (Currently ''" + name + "'')";
    }

    private void addFieldListeners() {
        fDirectoryField.setDialogFieldListener(this);
        fProjectNameField.setDialogFieldListener(this);

        fUseDefaultInterpreterField.setDialogFieldListener(this);
        fUseProjectInterpreterField.setDialogFieldListener(this);
        fInterpreterCombo.setDialogFieldListener(this);

        fPreferenceLink.addSelectionListener(this);
    }

    public void setProjectName(String value) {
        if (fInitialProjectName == null) {
            fInitialProjectName = value;
        }
    }

    public void dialogFieldChanged(DialogField field) {
        if (field == fDirectoryField) {
            IPath path = getImportPath();
            if (path.segmentCount() == 0) {
                fProjectNameField.setText("");
            } else {
                if (path.removeLastSegments(1).equals(ResourcesPlugin.getWorkspace().getRoot().getLocation())) {
                    fCopyButton.setEnabled(false);
                } else {
                    fCopyButton.setEnabled(true);
                }
                fProjectNameField.setText(path.lastSegment());
            }
            return;
        }
        if (field == fUseProjectInterpreterField) {
            fInterpreterCombo.setEnabled(fUseProjectInterpreterField.isSelected());
        }
        setPageComplete(isValid());
    }

    private boolean isValid() {
        IPath dir = getImportPath();
        String projectName = getProjectName();

        if (!fInterpretersPresent) {
            setErrorMessage("At least one Sausalito CoreSDK installation must be configured");
            return false;
        }
        String importDir = fDirectoryField.getText().trim();
        if (importDir.length() == 0) {
            setErrorMessage(null);
            setMessage("Select a project directory from your local file system to import into the workspace");
            return false;
        }
        File file = new File(importDir);
        if (!file.exists() || !file.isDirectory()) {
            setErrorMessage("Invalid directory: " + importDir);
            return false;
        }
        for (IProject project : fProjects) {
            if (project.getLocation().equals(dir)) {
                setErrorMessage("The given path corresponds to a project that is already in the workspace");
                return false;
            }
            if (project.getName().equals(projectName)) {
                setErrorMessage("A project with this name already exists in the workspace");
                return false;
            }
        }
        file = new File(importDir + File.separator + ".project");
        if (file.exists() && !file.isDirectory()) {
            setErrorMessage("The indicated path already contains an eclipse project. Use the \"Existing Projects into Workspace\" import wizard.");
            return false;
        }
        file = new File(importDir + File.separator + ".config" + File.separator + "sausalito.xml");
        if (!file.exists() || file.isDirectory()) {
            setErrorMessage("\"" + importDir + "\" is not a valid Sausalito project");
            return false;
        }
        if (projectName.length() == 0) {
            setErrorMessage("Project name cannot be empty");
            return false;
        }

        setErrorMessage(null);
        return true;
    }

    private String getProjectName() {
        return fProjectNameField.getText().trim();
    }

    private IPath getImportPath() {
        return new Path(fDirectoryField.getText());
    }

    private boolean isCopyProject() {
        return fCopyButton.isEnabled() && fCopyButton.getSelection();
    }

    public IInterpreterInstall getSelectedInterpreter() {
        if (fUseProjectInterpreterField.isSelected()) {
            int index = fInterpreterCombo.getSelectionIndex();
            if (index >= 0 && index < fComplianceLabels.length) { // paranoia
                return fInstalledInterpreters[index];
            }
        }
        return null;
    }

    private static class ImportProjectRecord {
        protected boolean isCopy;
        protected String projectName;
        protected IPath importPath;
        protected IInterpreterInstall interpreterInstall;

        public ImportProjectRecord(String name, IPath path, boolean copy, IInterpreterInstall install) {
            projectName = name;
            importPath = path;
            isCopy = copy;
            interpreterInstall = install;
        }
    }

    public boolean finishPage() {
        final ImportProjectRecord projectRecord = new ImportProjectRecord(getProjectName(), getImportPath(),
                isCopyProject(), getSelectedInterpreter());

        WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
                    InterruptedException {
                try {
                    int ticks = projectRecord.isCopy ? 4000 : 900;
                    monitor.beginTask("", ticks);
                    if (!createProject(projectRecord, monitor)) {
                        rollbackCreateProject(projectRecord);
                    }
                } catch (OperationCanceledException oce) {
                    monitor.setTaskName("Canceling project import...");
                    rollbackCreateProject(projectRecord);
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            getContainer().run(true, true, op);
        } catch (InterruptedException ie) {
            return false;
        } catch (InvocationTargetException ite) {
            // one of the steps resulted in a core exception
            Throwable t = ite.getTargetException();
            reportError(t);
            return false;
        }

        return true;
    }

    private boolean createProject(ImportProjectRecord projectRecord, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException, CoreException {

        // the monitor.beginTask() was already called by the invoking function 
        checkMonitor(monitor);

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        IProject project = root.getProject(projectRecord.projectName);

        File importDir = projectRecord.importPath.toFile();
        URI location = null;
        if (projectRecord.isCopy) {
            location = root.getLocationURI();
        } else {
            location = importDir.toURI();
        }

        // create the project object
        monitor.setTaskName("Creating the project...");
        BuildpathsBlock.createProject(project, location, new SubProgressMonitor(monitor, 300));

        checkMonitor(monitor);

        // add the Sausalito nature to the project
        monitor.setTaskName("Configuring Sausalito project...");
        BuildpathsBlock.addScriptNature(project, new SubProgressMonitor(monitor, 300), SETNature.NATURE_ID);

        checkMonitor(monitor);

        final IPath projectPath = project.getFullPath();
        IPreferenceStore store = SETUIPlugin.getDefault().getPreferenceStore();

        // get the default source directory names
        String handlerSrcPath = store.getString(PreferenceConstants.BUILD_PATH_HANDLER_DIR);
        String libSrcPath = store.getString(PreferenceConstants.BUILD_PATH_LIBRARY_DIR);
        String externalSrcPath = store.getString(PreferenceConstants.BUILD_PATH_EXTERNAL_DIR);

        String[] srcDirs = new String[] { externalSrcPath, handlerSrcPath, libSrcPath };
        List<IBuildpathEntry> entries = new ArrayList<IBuildpathEntry>();

        // search which of the default source paths exist in this project
        File[] children = importDir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        for (File child : children) {
            if (Arrays.binarySearch(srcDirs, child.getName()) >= 0) {
                entries.add(DLTKCore.newSourceEntry(projectPath.append(child.getName())));
            }
        }

        // add the interpreter to the buildpath
        IInterpreterInstall install = projectRecord.interpreterInstall;
        IBuildpathEntry interpreterEntry = null;
        if (install == null) {
            interpreterEntry = ScriptRuntime.getDefaultInterpreterContainerEntry();
        } else {
            interpreterEntry = getInterpreterBuildpathEntry(install);
        }
        if (interpreterEntry != null) {
            entries.add(interpreterEntry);
        }

        // copy the project contents if the copy option is set
        if (projectRecord.isCopy) {
            monitor.setTaskName("Copying project contents...");
            CoreSDKTemplateUtility.createProjectContent(project, projectRecord.importPath, new SubProgressMonitor(
                    monitor, 3000));
            checkMonitor(monitor);
        }

        IScriptProject scriptProject = DLTKCore.create(project);

        // write the .buildpath file
        monitor.setTaskName("Setting project interpreter...");
        BuildpathsBlock
                .flush(getBPEntries(scriptProject, entries), scriptProject, new SubProgressMonitor(monitor, 300));

        return true;
    }

    private void rollbackCreateProject(ImportProjectRecord projectRecord) throws CoreException {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectRecord.projectName);
        if (project.exists()) {
            project.delete(true, true, null);
        }

    }

    private void checkMonitor(IProgressMonitor monitor) {
        if (monitor.isCanceled()) {
            throw new OperationCanceledException();
        }
    }

    private ArrayList<BPListElement> getBPEntries(IScriptProject project, List<IBuildpathEntry> buildpathEntries) {
        ArrayList<BPListElement> newBuildpath = new ArrayList<BPListElement>();
        for (IBuildpathEntry entry : buildpathEntries) {
            newBuildpath.add(BPListElement.createFromExisting(entry, project));
        }
        return newBuildpath;
    }

    private void reportError(Throwable t) {
        String message = "Import problems";
        IStatus status;
        if (t instanceof CoreException) {
            status = ((CoreException)t).getStatus();
        } else {
            status = new Status(IStatus.ERROR, SETUIPlugin.PLUGIN_ID, 1, message, t);
        }
        ErrorDialog.openError(getShell(), message, null, status);
    }

    public void widgetDefaultSelected(SelectionEvent e) {
        showInterpreterPreferencePage();
        refreshInterpretes();
        updateEnableState();
    }

    public void widgetSelected(SelectionEvent e) {
        widgetDefaultSelected(e);
    }

    private void showInterpreterPreferencePage() {
        final String pageId = ISETUIConstants.ID_INTEREPRTERS_PREFERENCE_PAGE;
        PreferencesUtil.createPreferenceDialogOn(getShell(), pageId, new String[] { pageId }, null).open();
    }

    private void refreshInterpretes() {
        fUseDefaultInterpreterField.setLabelText(getDefaultInterpreterLabel());
        fillInstalledInterpreters(fInterpreterCombo);
    }

    private boolean fInterpretersPresent;

    private void updateEnableState() {
        fInterpreterCombo.setEnabled(fUseProjectInterpreterField.isSelected());
    }

    private void fillInstalledInterpreters(ComboDialogField comboField) {
        String selectedItem = null;
        int selectionIndex = -1;
        if (fUseProjectInterpreterField.isSelected()) {
            selectionIndex = comboField.getSelectionIndex();
            if (selectionIndex != -1) {// paranoia
                selectedItem = comboField.getItems()[selectionIndex];
            }
        }

        fInstalledInterpreters = getWorkspaceInterpeters();

        selectionIndex = -1;// find new index
        fComplianceLabels = new String[fInstalledInterpreters.length];
        for (int i = 0; i < fInstalledInterpreters.length; i++) {
            fComplianceLabels[i] = fInstalledInterpreters[i].getName();
            if (selectedItem != null && fComplianceLabels[i].equals(selectedItem)) {
                selectionIndex = i;
            }
        }
        comboField.setItems(fComplianceLabels);
        if (selectionIndex == -1) {
            fInterpreterCombo.selectItem(getDefaultInterpreterName());
        } else {
            fInterpreterCombo.selectItem(selectedItem);
        }
        fInterpretersPresent = (fInstalledInterpreters.length > 0);

        setPageComplete(isValid());
    }

    private IInterpreterInstall[] getWorkspaceInterpeters() {
        List<IInterpreterInstall> standins = new ArrayList<IInterpreterInstall>();
        IInterpreterInstallType[] types = ScriptRuntime.getInterpreterInstallTypes(SETNature.NATURE_ID);
        for (int i = 0; i < types.length; i++) {
            IInterpreterInstallType type = types[i];
            IInterpreterInstall[] installs = type.getInterpreterInstalls();
            for (int j = 0; j < installs.length; j++) {
                standins.add(new InterpreterStandin(installs[j]));
            }
        }
        return standins.toArray(new IInterpreterInstall[standins.size()]);
    }

    private String getDefaultInterpreterName() {
        IInterpreterInstall inst = ScriptRuntime.getDefaultInterpreterInstall(new DefaultInterpreterEntry(
                SETNature.NATURE_ID, EnvironmentManager.getLocalEnvironment().getId()));
        if (inst != null) {
            return inst.getName();
        } else {
            return "undefined";
        }
    }

    private IBuildpathEntry getInterpreterBuildpathEntry(IInterpreterInstall install) {
        IBuildpathEntry defaultPath = ScriptRuntime.getDefaultInterpreterContainerEntry();

        IPath InterpreterEnvironmentContainerPath = new Path(ScriptRuntime.INTERPRETER_CONTAINER);

        if (install != null) {
            IPath newPath = InterpreterEnvironmentContainerPath.append(install.getInterpreterInstallType().getId())
                    .append(install.getName());
            return DLTKCore.newContainerEntry(newPath);
        }
        return defaultPath;
    }

}
