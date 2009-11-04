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
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WebBrowserPreference;
import org.eclipse.wst.xquery.set.internal.launching.server.Server;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerLaunchJob;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerManager;

@SuppressWarnings("restriction")
public class SETLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

    public void launch(ILaunchConfiguration configuration, String mode, final ILaunch launch, IProgressMonitor monitor)
            throws CoreException {

        IProject project = getLaunchProject(launch);
        if (project == null) {
            DebugPlugin.getDefault().getLaunchManager().removeLaunch(launch);
            return;
        }

        final ServerLaunchJob serverJob = ServerManager.getInstance().createServerJob(launch);
        serverJob.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
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
                    if (startPage[0] != null)
                        urlStr += startPage[0];

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

}
