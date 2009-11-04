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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.ComboDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.InterpreterStandin;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.launching.ScriptRuntime.DefaultInterpreterEntry;
import org.eclipse.dltk.ui.dialogs.StatusInfo;
import org.eclipse.dltk.ui.environment.IEnvironmentUI;
import org.eclipse.dltk.ui.wizards.NewElementWizardPage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.internal.ide.misc.CheckboxTreeAndListGroup;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.internal.core.XQDTContentType;
import org.eclipse.wst.xquery.internal.ui.XQDTImages;

@SuppressWarnings("restriction")
public class XQDocWizardPage extends NewElementWizardPage implements IDialogFieldListener {

    // constants
    private final static int SIZING_SELECTION_WIDGET_WIDTH = 400;

    private final static int SIZING_SELECTION_WIDGET_HEIGHT = 300;

    private final static Object[] NO_CHILDREN = new Object[0];

    private ComboDialogField fInterpreterCombo;

    private IInterpreterInstall[] fInstalledInterpreters;

    private String[] fComplianceLabels;

    private CheckboxTreeAndListGroup fSelectionGroup;

    private StringButtonDialogField fDestinationLocationField;

    private ComboDialogField fStylesheetCombo;

    private IStructuredSelection fSelection;

    private String[] fStylesheets = new String[] { "zorba.css", "28msec.css", "mark_logic.css" };

    protected XQDocWizardPage(IStructuredSelection selection) {
        super("XQDoc Generation");
        setTitle("XQDoc Generation");
        setDescription("Selection the XQuery files to generate documentation from");
        setImageDescriptor(XQDTImages.WIZBAN_NEW_XQDOC);
        fSelection = selection;
    }

    public void createControl(Composite parent) {
        parent.setLayout(new GridLayout(1, false));

        // add the interpreter selection

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(3, false));

        (new Label(composite, SWT.NULL)).setText("Select an XQuery processor:");
        fInterpreterCombo = new ComboDialogField(SWT.READ_ONLY);

        Combo comboControl = fInterpreterCombo.getComboControl(composite);
        GridData gridData = new GridData(GridData.BEGINNING, GridData.CENTER, true, false);
        gridData.minimumWidth = 100;
        comboControl.setLayoutData(gridData); // make sure column 2 is grabing (but no fill)
        comboControl.setVisibleItemCount(20);
        fillInstalledInterpreters(fInterpreterCombo);

        // add the tree file selection

        composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, true));
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 3;
        composite.setLayoutData(gd);

        fSelectionGroup = new CheckboxTreeAndListGroup(composite, getXQDTProjects(), getXQDocResourceProvider(),
                WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(), getXQueryResourceProvider(),
                WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(), SWT.NONE, SIZING_SELECTION_WIDGET_WIDTH,
                SIZING_SELECTION_WIDGET_HEIGHT);
        setTreeSelection();

        // add the other controls

        composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(3, false));
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 3;
        composite.setLayoutData(gd);

        IStringButtonAdapter adapter = new IStringButtonAdapter() {
            public void changeControlPressed(DialogField field) {
                browseForDestination();
            }
        };

        fDestinationLocationField = new StringButtonDialogField(adapter);
        fDestinationLocationField.setLabelText("Destination:");
        fDestinationLocationField.setButtonLabel("Browse...");
        fDestinationLocationField.doFillIntoGrid(composite, 3);
        fDestinationLocationField.getTextControl(null).setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        (new Label(composite, SWT.NULL)).setText("Stylesheet:");
        fStylesheetCombo = new ComboDialogField(SWT.READ_ONLY);
        fStylesheetCombo.setItems(fStylesheets);
        fStylesheetCombo.getComboControl(composite);
        fStylesheetCombo.selectItem(0);

        addControlListeners();

        updateStatus(new StatusInfo(Status.ERROR, ""));

        setControl(parent);
    }

    private void addControlListeners() {
        fDestinationLocationField.setDialogFieldListener(this);
        fInterpreterCombo.setDialogFieldListener(this);
        fSelectionGroup.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                setPageComplete(isValid());
            }
        });
    }

    private ArrayList<IProject> getXQDTProjects() {
        ArrayList<IProject> input = new ArrayList<IProject>();
        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        for (IProject project : projects) {
            try {
                if (project.hasNature(XQDTNature.NATURE_ID)) {
                    input.add(project);
                }
            } catch (CoreException e) {
                // do nothing...
            }
        }
        return input;
    }

    private void setTreeSelection() {
        Object[] elements = fSelection.toArray();
        for (Object element : elements) {
            if (element instanceof IProject) {
                IProject project = (IProject)element;
                try {
                    if (project.hasNature(XQDTNature.NATURE_ID)) {
                        fSelectionGroup.initialCheckTreeItem(project);
                    }
                } catch (CoreException e) {
                    // do nothing...
                }
            }
        }
    }

    private void browseForDestination() {
        IEnvironment environment = EnvironmentManager.getLocalEnvironment();
        IEnvironmentUI environmentUI = (IEnvironmentUI)environment.getAdapter(IEnvironmentUI.class);
        if (environmentUI != null) {
            String newPath = environmentUI.selectFolder(getShell(), fDestinationLocationField.getText().trim());
            if (newPath != null) {
                fDestinationLocationField.setText(newPath);
            }
        }
    }

    private ITreeContentProvider getXQueryResourceProvider() {
        return new WorkbenchContentProvider() {
            @Override
            public Object[] getChildren(Object o) {
                if (o instanceof IContainer) {
                    IResource[] members = null;
                    try {
                        members = ((IContainer)o).members();
                    } catch (CoreException e) {
                        return NO_CHILDREN;
                    }

                    // filter out the desired resource types
                    ArrayList<Object> results = new ArrayList<Object>();
                    for (int i = 0; i < members.length; i++) {
                        if (XQDTContentType.isXQueryFile(members[i])) {
                            results.add(members[i]);
                        }
                    }
                    return results.toArray();
                } else if (o instanceof ArrayList<?>) {
                    return ((ArrayList<?>)o).toArray();
                }
                return NO_CHILDREN;
            }
        };
    }

    /**
     * Returns a content provider for <code>IResource</code>s that returns only children of the
     * given resource type.
     */
    private ITreeContentProvider getXQDocResourceProvider() {
        return new WorkbenchContentProvider() {

            private static final int TYPE = IResource.FOLDER | IResource.PROJECT | IResource.ROOT;

            @Override
            public Object[] getChildren(Object o) {
                if (o instanceof IContainer) {
                    IResource[] members = null;
                    try {
                        members = ((IContainer)o).members();
                    } catch (CoreException e) {
                        return NO_CHILDREN;
                    }

                    // filter out the desired resource types
                    ArrayList<Object> results = new ArrayList<Object>();
                    for (int i = 0; i < members.length; i++) {
                        // And the test bits with the resource types to see if they are what we want
                        if ((members[i].getType() & TYPE) > 0) {
                            results.add(members[i]);
                        }
                    }
                    return results.toArray();
                } else if (o instanceof ArrayList<?>) {
                    return ((ArrayList<?>)o).toArray();
                }
                return NO_CHILDREN;
            }
        };
    }

    public void dialogFieldChanged(DialogField field) {
        setPageComplete(isValid());
    }

    private void fillInstalledInterpreters(ComboDialogField comboField) {
        String selectedItem = null;
        int selectionIndex = -1;
        selectionIndex = comboField.getSelectionIndex();
        if (selectionIndex != -1) {// paranoia
            selectedItem = comboField.getItems()[selectionIndex];
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
    }

    private IInterpreterInstall[] getWorkspaceInterpeters() {
        List<IInterpreterInstall> standins = new ArrayList<IInterpreterInstall>();
        IInterpreterInstallType[] types = ScriptRuntime.getInterpreterInstallTypes(XQDTNature.NATURE_ID);
        IEnvironment environment = EnvironmentManager.getLocalEnvironment();
        for (int i = 0; i < types.length; i++) {
            IInterpreterInstallType type = types[i];
            IInterpreterInstall[] installs = type.getInterpreterInstalls();
            for (int j = 0; j < installs.length; j++) {
                IInterpreterInstall install = installs[j];
                String envId = install.getEnvironmentId();
                if (envId != null && envId.equals(environment.getId())
// TODO: correct this code!!!
//                        && install instanceof ZorbaInstall
                ) {
                    standins.add(new InterpreterStandin(install));
                }
            }
        }
        return standins.toArray(new IInterpreterInstall[standins.size()]);
    }

    private String getDefaultInterpreterName() {
        IInterpreterInstall inst = ScriptRuntime.getDefaultInterpreterInstall(new DefaultInterpreterEntry(
                XQDTNature.NATURE_ID, EnvironmentManager.getLocalEnvironment().getId()));
        if (inst != null) {
            return inst.getName();
        } else {
            return "undefined"; //$NON-NLS-1$
        }
    }

    public String getStyleSheet() {
        return fStylesheetCombo.getItems()[fStylesheetCombo.getSelectionIndex()];
    }

    public IInterpreterInstall getInterpreter() {
        return fInstalledInterpreters[fInterpreterCombo.getSelectionIndex()];
    }

    public Collection<IPath> getQueries() {
        List<IPath> queries = new ArrayList<IPath>();
        Iterator<?> it = fSelectionGroup.getAllCheckedListItems();
        while (it.hasNext()) {
            IResource resource = (IResource)it.next();
            queries.add(resource.getLocation());
        }
        return queries;
    }

    public IPath getOutputDir() {
        return new Path(fDestinationLocationField.getText().trim());
    }

    private boolean isValid() {
        String destDir = fDestinationLocationField.getText().trim();
        File dir = null;
        if (destDir.length() == 0) {
            setErrorMessage("The destination directory cannot e empty");
            return false;
        } else if (!fSelectionGroup.getAllCheckedListItems().hasNext()) {
            setErrorMessage("At least one XQuery file must be selected");
            return false;
        } else if (!(dir = new File(destDir)).exists() || !dir.isDirectory()) {
            setErrorMessage("\"" + destDir + "\" is not a valid directory");
            return false;
        }

        setErrorMessage(null);
        return true;
    }
}
