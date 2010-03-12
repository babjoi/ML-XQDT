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
package org.eclipse.wst.xquery.set.internal.launching;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WebBrowserPreference;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.launching.server.Server;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerLaunchJob;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerManager;

@SuppressWarnings("restriction")
public class SETLaunchConfigurationDelegate extends AbstractScriptLaunchConfigurationDelegate {

    public void launch(ILaunchConfiguration configuration, String mode, final ILaunch launch, IProgressMonitor monitor)
            throws CoreException {

        // for "debug" mode use the parent implementation 
        // which uses will start the DBGP server
        if (mode.equals(ILaunchManager.DEBUG_MODE)) {
            super.launch(configuration, mode, launch, monitor);
            return;
        }

        // for "run" mode the logic is overwritten
        IProject project = getLaunchProject(launch);
        if (project == null) {
            DebugPlugin.getDefault().getLaunchManager().removeLaunch(launch);
            return;
        }

        final ServerLaunchJob serverJob = ServerManager.getInstance().createServerJob(launch);
        serverJob.addJobChangeListener(new JobChangeAdapter() {

            public void done(IJobChangeEvent event) {
                System.out.println("done");
                if (event.getResult() == Status.OK_STATUS) {
                    openBrowser(launch);
                }
            }

        });

        // start Apache
        serverJob.schedule();
    }

    public static IProject getLaunchProject(final ILaunch launch) throws CoreException {
        ILaunchConfiguration config = launch.getLaunchConfiguration();
        String projectName = config.getAttribute(ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME, "");
        return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    }

    private boolean openExternal() {
        return (WebBrowserPreference.EXTERNAL == WebBrowserPreference.getBrowserChoice());
    }

    private void openBrowser(ILaunch launch) {
        final String[] startPage = new String[1];
        IProject project = null;
        try {
            startPage[0] = launch.getLaunchConfiguration().getAttribute(
                    ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME, "");
            project = SETLaunchConfigurationDelegate.getLaunchProject(launch);
        } catch (CoreException ce) {
            // TODO Auto-generated catch block
            ce.printStackTrace();
            return;
        }
        final Server server = ServerManager.getInstance().getProjectServer(project);

        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                try {
                    String urlStr = "http://" + server.getHost();
                    if (server.getPort() != 80) {
                        urlStr += ":" + server.getPort();
                    }
                    if (startPage[0] != null) {
                        urlStr += startPage[0];
                    }

                    URL url = new URL(urlStr);
                    IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
                    IWebBrowser browser = null;
                    if (openExternal()) {
                        browser = PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser();
                    } else {
                        int browserStyle = IWorkbenchBrowserSupport.LOCATION_BAR
                                | IWorkbenchBrowserSupport.NAVIGATION_BAR | IWorkbenchBrowserSupport.STATUS;
                        String browserTitle = "Sausalito: " + url.toString();
                        browser = browserSupport.createBrowser(browserStyle, "SausalitoBrowser", browserTitle
                                .toString(), browserTitle.toString());
                    }
                    browser.openURL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String getLanguageId() {
        return SETNature.NATURE_ID;
    }

//    @Override
//    protected InterpreterConfig createInterpreterConfig(ILaunchConfiguration configuration, ILaunch launch)
//            throws CoreException {
//        IScriptProject scriptProject = AbstractScriptLaunchConfigurationDelegate.getScriptProject(configuration);
//        IEnvironment scriptEnvironment = EnvironmentManager.getEnvironment(scriptProject);
//
//        InterpreterConfig config = new InterpreterConfig();
//        config.setEnvironment(scriptEnvironment);
//
//        // Script arguments
//        String[] scriptArgs = getScriptArguments(configuration);
//        config.addScriptArgs(scriptArgs);
//
//        // Interpreter argument
//        String[] interpreterArgs = getInterpreterArguments(configuration);
//        config.addInterpreterArgs(interpreterArgs);
//
//        // Environment
//        // config.addEnvVars(DebugPlugin.getDefault().getLaunchManager()
//        // .getNativeEnvironmentCasePreserved());
//        final boolean append = configuration.getAttribute(ILaunchManager.ATTR_APPEND_ENVIRONMENT_VARIABLES, true);
//        @SuppressWarnings("unchecked")
//        final Map<String, String> configEnv = configuration.getAttribute(ILaunchManager.ATTR_ENVIRONMENT_VARIABLES,
//                (Map)null);
//        // build base environment
//        final Map<String, String> env = new HashMap<String, String>();
//        if (append || configEnv == null) {
//            Map<String, String> envVars = scriptEnvironment.getEnvironmentVariables(false);
//            if (envVars != null) {
//                env.putAll(envVars);
//            }
//        }
//        if (configEnv != null) {
//            for (Map.Entry<String, String> entry : configEnv.entrySet()) {
//                final String key = entry.getKey();
//                String value = entry.getValue();
//                if (value != null) {
//                    value = VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(value);
//                }
//                env.put(key, value);
//            }
//            /*
//             * TODO for win32 override values in case-insensitive way like in
//             * org.eclipse.debug.internal.core.LaunchManager#getEnvironment(...)
//             */
//        }
//        config.addEnvVars(env);
//
//        return config;
//    }

    protected String getScriptLaunchPath(ILaunchConfiguration configuration, IEnvironment scriptEnvironment)
            throws CoreException {
        // Sausalito is not based on a script that will be launched.
        // A start page will be read from the project configuration.
        return "";
    };
}
