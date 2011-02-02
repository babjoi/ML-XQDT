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
package org.eclipse.wst.xquery.set.internal.debug.ui.launcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.debug.ui.launcher.AbstractScriptLaunchShortcut;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.wst.xquery.set.core.ISETCoreConstants;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.core.SETProjectConfig;
import org.eclipse.wst.xquery.set.core.utils.SETProjectConfigUtil;
import org.eclipse.wst.xquery.set.debug.core.ISETLaunchConfigurationConstants;
import org.eclipse.wst.xquery.set.internal.ui.SETEditProjectConfigDialog;
import org.eclipse.wst.xquery.set.launching.SETRuntimeProcessFactory;

public class SETLaunchShortcut extends AbstractScriptLaunchShortcut {

    protected ILaunchConfigurationType getConfigurationType() {
        return getLaunchManager().getLaunchConfigurationType(
                "org.eclipse.wst.xquery.set.launching.SETLaunchConfigurationType");
    }

    protected String getNatureId() {
        return SETNature.NATURE_ID;
    }

    @Override
    protected String getScriptSelectionTitle() {
        return "Select a handler function to launch";
    }

    @Override
    protected String getSelectionEmptyMessage() {
        return "The project contains no handler modules";
    }

    @Override
    protected IResource[] findScripts(Object[] elements, IRunnableContext context) throws InterruptedException,
            CoreException {
        IResource r = null;
        if (elements[0] instanceof IModelElement) {
            IModelElement me = (IModelElement)elements[0];
            r = me.getUnderlyingResource();
            if (r == null) {
                r = me.getScriptProject().getUnderlyingResource();
            }
        } else if (elements[0] instanceof IResource) {
            r = (IResource)elements[0];
        } else {
            return new IResource[0];
        }

        IProject p = r.getProject();
        IResource hd = p.getFolder(ISETCoreConstants.PROJECT_DIRECTORY_HANDLER);
        if (!hd.exists()) {
            return new IResource[0];
        }

        IScriptProject sp = DLTKCore.create(p);
        return super.findScripts(new Object[] { sp.getProjectFragment(hd) }, context);
    }

    @SuppressWarnings("deprecation")
    protected ILaunchConfiguration createConfiguration(IProject project, String startPage) {
        ILaunchConfiguration config = null;
        ILaunchConfigurationWorkingCopy wc = null;
        try {
            ILaunchConfigurationType configType = getConfigurationType();

            String name = project.getName();

            wc = configType.newInstance(null, getLaunchManager().generateUniqueLaunchConfigurationNameFrom(name));
            wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_NATURE, getNatureId());
            wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getName());
            wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME, startPage);

            wc.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_HOST, "127.0.0.1");
            wc.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_PORT, 8080);
            wc.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_INDENT, true);
            wc.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_CLEAR_COLLECTIONS, false);
            wc.setAttribute(DebugPlugin.ATTR_PROCESS_FACTORY_ID, SETRuntimeProcessFactory.PROCESS_FACTORY_ID);

            wc.setMappedResources(new IResource[] { project });

            config = wc.doSave();
        } catch (CoreException exception) {
            exception.printStackTrace();
        }

        return config;
    }

    @Override
    public void launch(IEditorPart editor, String mode) {
        IEditorInput editorInput = editor.getEditorInput();
        if (editorInput == null) {
            return;
        }
        IResource script = ((IFileEditorInput)editorInput).getFile();
        if (script != null) {
            launch(script, mode);
        }
    }

    @Override
    protected void launch(IResource resource, String mode) {
        searchAndLaunch(new Object[] { resource }, mode, getScriptSelectionTitle(), getSelectionEmptyMessage());
    }

    @Override
    public void searchAndLaunch(Object[] search, String mode, String selectMessage, String emptyMessage) {
        if (search == null || search.length != 1) {
            return;
        }

        IProject project = null;

        if (search[0] instanceof IModelElement) {
            IModelElement me = (IModelElement)search[0];
            project = me.getScriptProject().getProject();
        } else if (search[0] instanceof IResource) {
            IResource res = (IResource)search[0];
            project = res.getProject();
            try {
                if (!project.hasNature(SETNature.NATURE_ID)) {
                    return;
                }
            } catch (CoreException e) {
                e.printStackTrace();
                return;
            }
        } else {
            return;
        }

        SETProjectConfig config = SETProjectConfigUtil.readProjectConfig(project);
        if (config == null) {
            return;
        }
        String startPage = config.getStartPage();
        if (startPage == null) {
            startPage = SETEditProjectConfigDialog.getHandlerFunctionStartPage(project, getShell());
        }

        launch(project, startPage, mode);
    }

    protected void launch(IProject project, String startPage, String mode) {
        ILaunchConfiguration config = findLaunchConfiguration(project, startPage, getConfigurationType());
        if (config != null) {
            DebugUITools.launch(config, mode);
        }
    }

    protected ILaunchConfiguration findLaunchConfiguration(IProject project, String startPage,
            ILaunchConfigurationType configType) {
        @SuppressWarnings("unchecked")
        List<ILaunchConfiguration> candidateConfigs = Collections.EMPTY_LIST;
        try {
            ILaunchConfiguration[] configs = DebugPlugin.getDefault().getLaunchManager()
                    .getLaunchConfigurations(configType);
            candidateConfigs = new ArrayList<ILaunchConfiguration>(configs.length);
            for (int i = 0; i < configs.length; i++) {
                ILaunchConfiguration config = configs[i];
                if (config.getAttribute(ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME, Util.EMPTY_STRING)
                        .equals(startPage)
                        && config.getAttribute(ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME, Util.EMPTY_STRING)
                                .equals(project.getName())) {
                    candidateConfigs.add(config);
                }
            }
        } catch (CoreException e) {
            DLTKLaunchingPlugin.log(e);
        }

        int candidateCount = candidateConfigs.size();
        if (candidateCount < 1) {
            return createConfiguration(project, startPage);
        } else if (candidateCount == 1) {
            return candidateConfigs.get(0);
        } else {
            // Prompt the user to choose a config. A null result means the user
            // cancelled the dialog, in which case this method returns null,
            // since cancelling the dialog should also cancel launching
            // anything.
            ILaunchConfiguration config = chooseConfiguration(candidateConfigs);
            if (config != null) {
                return config;
            }
        }

        return null;
    }

}