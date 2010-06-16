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

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.dltk.ui.wizards.CapabilityConfigurationPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.ui.preferences.PreferenceConstants;
import org.eclipse.wst.xquery.set.internal.ui.preferences.SETBuildPathsBlock;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

@SuppressWarnings("restriction")
public class SETNewProjectWizardSecondPage extends CapabilityConfigurationPage {

    private final SETNewProjectWizardFirstPage fFirstPage;

    private URI fCurrProjectLocation; // null if location is platform location
    private IProject fCurrProject;

    private Boolean fIsAutobuild;

    /**
     * Constructor for ScriptProjectWizardSecondPage.
     */
    public SETNewProjectWizardSecondPage(SETNewProjectWizardFirstPage mainPage) {
        fFirstPage = mainPage;
        fCurrProjectLocation = null;
        fCurrProject = null;

        fIsAutobuild = null;
    }

    public ProjectWizardFirstPage getFirstPage() {
        return fFirstPage;
    }

    protected boolean useNewSourcePage() {
        return true;
    }

    protected String getScriptNature() {
        return SETNature.NATURE_ID;
    }

    protected BuildpathsBlock createBuildpathBlock(IStatusChangeListener listener) {
        return new SETBuildPathsBlock(new BusyIndicatorRunnableContext(), listener, 0, useNewSourcePage(), null);
    }

    final void updateProject(IProgressMonitor monitor) throws CoreException, InterruptedException {

        fCurrProject = fFirstPage.getProjectHandle();
        fCurrProjectLocation = getProjectLocationURI();

        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        try {
            monitor.beginTask(NewWizardMessages.ScriptProjectWizardSecondPage_operation_initialize, 50);
            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }

            createProject(fCurrProject, fCurrProjectLocation, new SubProgressMonitor(monitor, 25));

            IBuildpathEntry[] entries = null;
            IPreferenceStore store = getPreferenceStore();

            // create project build paths
            IPath handlerSrcPath = new Path(store.getString(PreferenceConstants.BUILD_PATH_HANDLER_DIR));
            IPath libSrcPath = new Path(store.getString(PreferenceConstants.BUILD_PATH_LIBRARY_DIR));

            final IPath projectPath = fCurrProject.getFullPath();

            // configure the buildpath entries, including the default
            // InterpreterEnvironment library.
            List<IBuildpathEntry> cpEntries = new ArrayList<IBuildpathEntry>();
            cpEntries.add(DLTKCore.newSourceEntry(projectPath.append(handlerSrcPath)));
            cpEntries.add(DLTKCore.newSourceEntry(projectPath.append(libSrcPath)));
            IBuildpathEntry[] entrys = getDefaultBuildpathEntry();
            if (entrys != null) {
                cpEntries.addAll(Arrays.asList(entrys));
            }
            entries = cpEntries.toArray(new IBuildpathEntry[cpEntries.size()]);

            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }

            init(DLTKCore.create(fCurrProject), entries, false);
            // ------------------------------------------------
            // TODO: The bug that the .buildpath cannot be read
            // Thread.sleep(200);
            // ------------------------------------------------

            // add the Sausalito nature
            // BuildpathsBlock.addScriptNature(fCurrProject, monitor, XQDTNature.NATURE_ID);

            // this will add the XQDT nature
            configureScriptProject(new SubProgressMonitor(monitor, 25)); // create

        } finally {
            monitor.done();
        }
    }

    protected IPreferenceStore getPreferenceStore() {
        return SETUIPlugin.getDefault().getPreferenceStore();
    }

    private URI getProjectLocationURI() throws CoreException {
        if (fFirstPage.isInWorkspace()) {
            return null;
        }
        return fFirstPage.getLocationURI();
    }

    private IBuildpathEntry[] getDefaultBuildpathEntry() {
        IBuildpathEntry defaultPath = ScriptRuntime.getDefaultInterpreterContainerEntry();

        IPath InterpreterEnvironmentContainerPath = new Path(ScriptRuntime.INTERPRETER_CONTAINER);

        IInterpreterInstall inst = fFirstPage.getInterpreter();
        if (inst != null) {
            IPath newPath = InterpreterEnvironmentContainerPath.append(inst.getInterpreterInstallType().getId())
                    .append(inst.getName());
            return new IBuildpathEntry[] { DLTKCore.newContainerEntry(newPath) };
        }

        if (defaultPath != null) {
            return new IBuildpathEntry[] { defaultPath };
        }

        return null;
    }

    /**
     * Called from the wizard on finish.
     */
    public void performFinish(IProgressMonitor monitor) throws CoreException, InterruptedException {
        try {
            monitor.beginTask(NewWizardMessages.ScriptProjectWizardSecondPage_operation_create, 100);

            updateProject(new SubProgressMonitor(monitor, 50));

            createProjectFromTemplate(new SubProgressMonitor(monitor, 50));
        } finally {
            monitor.done();
            fCurrProject = null;
            if (fIsAutobuild != null) {
                CoreUtility.enableAutoBuild(fIsAutobuild.booleanValue());
                fIsAutobuild = null;
            }
        }
    }

    private void createProjectFromTemplate(SubProgressMonitor monitor) throws CoreException {
        try {
            IPreferenceStore store = getPreferenceStore();
            String templateName = null;

            if (fFirstPage.isTemplate()) {
                templateName = fFirstPage.getTemplateName();
            } else {
                templateName = store.getString(PreferenceConstants.TEMPLATES_DEFAULT_PROJECT_DIR);
            }
            monitor.beginTask("Creating project directory structure", 50);
            CoreSDKTemplateUtility.createProjectContent(fCurrProject, templateName, fFirstPage.getProjectUriString());
        } finally {
            monitor.done();
        }
    }

}
