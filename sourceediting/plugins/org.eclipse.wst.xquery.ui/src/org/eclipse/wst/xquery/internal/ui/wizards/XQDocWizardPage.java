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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.ComboDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.eclipse.dltk.ui.environment.IEnvironmentUI;
import org.eclipse.dltk.ui.wizards.NewElementWizardPage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.internal.ide.misc.CheckboxTreeAndListGroup;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.internal.ui.XQDTImages;

@SuppressWarnings("restriction")
public class XQDocWizardPage extends NewElementWizardPage implements IDialogFieldListener {

    // constants
    private final static int SIZING_SELECTION_WIDGET_WIDTH = 400;

    private final static int SIZING_SELECTION_WIDGET_HEIGHT = 300;

    private final static Object[] NO_CHILDREN = new Object[0];

    private CheckboxTreeAndListGroup fSelectionGroup;

    private StringButtonDialogField fDestinationLocationField;

    private ComboDialogField fStylesheetCombo;

    private IStructuredSelection fSelection;

    private String[] fStylesheets = new String[] { "zorba.css", "28msec.css", "mark_logic.css" };

    private IScriptProject fProject;

    protected XQDocWizardPage(IStructuredSelection selection) {
        super("XQDoc Generation");
        setTitle("XQDoc Generation");
        setDescription("Selection the XQuery files to generate documentation from");
        setImageDescriptor(XQDTImages.WIZBAN_NEW_XQDOC);
        fSelection = selection;
    }

    public void createControl(Composite parent) {
        parent.setLayout(new GridLayout(1, false));

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(3, false));

        // add the tree file selection

        composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, true));
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 3;
        composite.setLayoutData(gd);

        List<IScriptProject> projects = new ArrayList<IScriptProject>(1);
        IScriptProject project = getProject();
        if (project != null) {
            projects.add(project);
        }

        fSelectionGroup = new CheckboxTreeAndListGroup(composite, projects, getTreeResourceProvider(),
                WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(), getListResourceProvider(),
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
        if (project != null) {
            fDestinationLocationField.setText(project.getResource().getLocation().toOSString());
        }

        (new Label(composite, SWT.NULL)).setText("Stylesheet:");
        fStylesheetCombo = new ComboDialogField(SWT.READ_ONLY);
        fStylesheetCombo.setItems(fStylesheets);
        fStylesheetCombo.getComboControl(composite);
        fStylesheetCombo.selectItem(0);

        addControlListeners();

//        if (isValid()) {
//            updateStatus(new StatusInfo(Status.ERROR, ""));
//            setPageComplete(true);
//        }

        setControl(parent);
    }

    private void addControlListeners() {
        fDestinationLocationField.setDialogFieldListener(this);
        fSelectionGroup.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                setPageComplete(isValid());
            }
        });
    }

    public IScriptProject getProject() {
        if (fProject != null) {
            return fProject;
        }
        if (fSelection == null || fSelection.size() == 0) {
            return null;
        }

        Object element = fSelection.getFirstElement();
        IProject project = null;
        if (element instanceof IContainer) {
            project = ((IContainer)element).getProject();
        } else if (element instanceof IModelElement) {
            project = ((IModelElement)element).getScriptProject().getProject();
        } else if (element instanceof IResource) {
            project = ((IResource)element).getProject();
        }

        if (project != null) {
            try {
                if (project.hasNature(XQDTNature.NATURE_ID)) {
                    fProject = DLTKCore.create(project);
                }
            } catch (CoreException e) {
                // do nothing...
            }
        }

        return fProject;
    }

    private void setTreeSelection() {
        if (fSelection == null || fSelection.size() == 0) {
            return;
        }

        Object selected = fSelection.getFirstElement();

        if (selected instanceof IResource) {
            selected = DLTKCore.create((IResource)selected);
        }

        if (selected instanceof IScriptFolder) {
            fSelectionGroup.initialCheckTreeItem(selected);
        } else if (selected instanceof IScriptProject) {
            fSelectionGroup.initialCheckTreeItem(selected);
            fSelectionGroup.setAllSelections(true);
        } else if (selected instanceof ISourceModule) {
            ISourceModule module = (ISourceModule)selected;
            IModelElement parent = module.getParent();

            Map<Object, List<Object>> items = new HashMap<Object, List<Object>>(1);
            List<Object> list = new ArrayList<Object>(1);
            list.add(module.getResource());
            items.put(parent, list);
            fSelectionGroup.initialCheckTreeItem(parent);
            fSelectionGroup.updateSelections(items);
        }

        fSelectionGroup.expandAll();
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

    private ITreeContentProvider getListResourceProvider() {
        return new WorkbenchContentProvider() {
            @Override
            public Object[] getChildren(Object o) {
                if (o instanceof IScriptFolder) {
                    ArrayList<IResource> results = new ArrayList<IResource>();
                    IScriptFolder folder = (IScriptFolder)o;

                    try {
                        ISourceModule[] modules = folder.getSourceModules();
                        for (ISourceModule module : modules) {
                            ModuleDeclaration modDecl = SourceParserUtil.getModuleDeclaration(module);
                            if (modDecl instanceof XQueryLibraryModule) {
                                results.add(module.getResource());
                            }
                        }
                    } catch (ModelException e) {
                        return NO_CHILDREN;
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
    private ITreeContentProvider getTreeResourceProvider() {
        return new WorkbenchContentProvider() {

            @Override
            public Object[] getChildren(Object o) {
                if (o instanceof IScriptProject) {
                    ArrayList<IScriptFolder> results = new ArrayList<IScriptFolder>();
                    IScriptProject project = (IScriptProject)o;
                    try {
                        IScriptFolder[] folders = project.getScriptFolders();
                        for (IScriptFolder folder : folders) {
                            if (folder.containsScriptResources() && !folder.isReadOnly()) {
                                results.add(folder);
                            }
                        }
                    } catch (ModelException e) {
                        return NO_CHILDREN;
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

    public String getStyleSheet() {
        return fStylesheetCombo.getItems()[fStylesheetCombo.getSelectionIndex()];
    }

    public Collection<IPath> getQueries() {
        List<IPath> queries = new ArrayList<IPath>();
        Iterator<?> it = fSelectionGroup.getAllCheckedListItems();
        while (it.hasNext()) {
            Object o = it.next();
            if (o instanceof IFile) {
                queries.add(((IFile)o).getLocation());
            }
        }
        return queries;
    }

    public IPath getOutputDir() {
        return new Path(fDestinationLocationField.getText().trim());
    }

    @Override
    public boolean isPageComplete() {
        if (!isValid()) {
            return false;
        }
        return super.isPageComplete();
    }

    private boolean isValid() {
        String destDir = fDestinationLocationField.getText().trim();
        if (destDir.length() == 0) {
            setErrorMessage("The destination directory cannot be empty");
            return false;
        } else if (!hasSelectedQuery()) {
            setErrorMessage("At least one XQuery file must be selected");
            return false;
        } else {
            File dir = new File(destDir);
            if (!dir.exists() || !dir.isDirectory()) {
                setErrorMessage("\"" + destDir + "\" is not a valid directory");
                return false;
            }
        }

        setErrorMessage(null);
        return true;
    }

    private boolean hasSelectedQuery() {
        Iterator<?> iter = fSelectionGroup.getAllCheckedListItems();
        while (iter.hasNext()) {
            if (iter.next() instanceof IFile) {
                return true;
            }
        }

        return false;
    }
}
