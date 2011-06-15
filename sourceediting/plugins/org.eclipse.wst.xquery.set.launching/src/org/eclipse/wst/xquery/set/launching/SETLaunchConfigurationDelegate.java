/*******************************************************************************
 * Copyright (c) 2008 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.launching;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.launching.server.Server;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerManager;

public class SETLaunchConfigurationDelegate extends AbstractScriptLaunchConfigurationDelegate {

    private final static Object fLock = new Object();

    @Override
    public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
            IProgressMonitor monitor) throws CoreException {

        try {
//            if (mode.equals(ILaunchManager.DEBUG_MODE)) {
//                throw new CoreException(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
//                        "The Sausalito Debugger is not available in this version of Sausalito Tools for Eclipse."));
//            }

            monitor.beginTask("Starting the Sausalito CoreSDK Server", 10);
            if (monitor.isCanceled()) {
                return;
            }

            Server server = ServerManager.getInstance().createServer(launch, getProject(configuration));

            ISETLaunchRetryHandler handler = getRetryHandler();
            boolean retry = false;

            do {
                // make sure we can start the server
                IStatus status = checkLaunchPreconditions(launch, server, new SubProgressMonitor(monitor, 2));
                // after this point the socket, server and project are registered in the server manager
                if (monitor.isCanceled()) {
                    return;
                }

                if (!status.isOK() && handler != null) {
                    retry = handler.retry(status, server);
                    if (!retry) {
                        throw new CoreException(status);
                    }
                } else {
                    retry = false;
                }
            } while (retry);

            doPreLaunchActions(configuration, mode, launch, monitor);

            // from now on cancelling monitors must also unregister the
            // socket, server and project from the server manager 

            try {
                SETLaunchConfigurationDelegate.super.launch(configuration, mode, launch, new NullProgressMonitor());
            } catch (CoreException ce) {
                abortLaunch(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                        "An error occured while trying to start the Sausalito CoreSDK server.", ce),
                        getProject(configuration), server);
            }

            // make sure we started the server (server is listening)
            checkLaunchPostconditions(launch, server, new SubProgressMonitor(monitor, 2));
            if (monitor.isCanceled()) {
                return;
            }

            // open the browser
            monitor.subTask("Opening the browser");

//            IWebBrowser browser = SETLaunchUtil.openBrowser(launch);
//            server.setBrowser(browser);

            if (monitor.isCanceled()) {
                return;
            }
            monitor.worked(3);
        } finally {
            monitor.done();
        }
    }

    private void doPreLaunchActions(ILaunchConfiguration configuration, String mode, ILaunch launch,
            IProgressMonitor monitor) {
//        try {
//            boolean bulkload = configuration.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_BULKLOAD_DATA,
//                    false);
//            if (bulkload) {
//                SETImportDataJob job = new SETImportDataJob(getProject(configuration));
//                job.addJobChangeListener(new JobChangeAdapter() {
//                    @Override
//                    public void done(IJobChangeEvent event) {
//                        synchronized (SETLaunchConfigurationDelegate.this) {
//                            SETLaunchConfigurationDelegate.this.notify();
//                        }
//                    }
//                });
//                job.setSystem(true);
//                job.setUser(false);
//                synchronized (this) {
//                    this.wait();
//                }
//                System.out.println("unlocked");
//            }
//        } catch (CoreException ce) {
//            Status status = new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
//                    "Error while bulkloading project during launch.", ce);
//            SETLaunchingPlugin.log(status);
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }
    }

    private ISETLaunchRetryHandler getRetryHandler() {
        IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(
                "org.eclipse.wst.xquery.set.launching.retryHandler");

        if (extPoint != null) {
            IExtension[] extensions = extPoint.getExtensions();
            if (extensions.length > 0) {
                IExtension extension = extensions[0];
                IConfigurationElement[] handlerElements = extension.getConfigurationElements();
                if (handlerElements.length > 0) {
                    try {
                        ISETLaunchRetryHandler handler = (ISETLaunchRetryHandler)handlerElements[0]
                                .createExecutableExtension("class");
                        return handler;
                    } catch (CoreException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    private IStatus checkLaunchPreconditions(ILaunch launch, Server server, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Checking preconditions for the Sausalito CoreSDK server launch", 10);

            IProject project = getProject(launch.getLaunchConfiguration());
            ServerManager sm = ServerManager.getInstance();

            monitor.subTask("Checking if the project is not started already and the socket is available.");

            synchronized (fLock) {
                // 1. do not launch started projects
                if (sm.isProjectStarted(project)) {
                    throw new CoreException(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                            "Project already running. It is not allowed to simultaneously run more instances of the same project (Project: "
                                    + project.getName() + ")"));
                }

                // 2. check if the socket is not already used by other project
                if (!sm.isSocketFree(server.getHost(), server.getPort())) {
                    IProject p = sm.getProject(server.getSocketString());

                    // p should not be null at this point
                    Assert.isNotNull(p);

//                    if (!tryOtherSocket(server)) {
                    throw new CoreException(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID, "Socket "
                            + server.getSocketString() + " is already in use by project: " + p.getName()
                            + ". Change the interface or the port and try again."));
//                    }
                }

                // 3. check if a HTTP connection can be established on the server socket
                // if yes, we cannot start the project on a used socket
                if (server.isListening()) {
                    throw new CoreException(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID, "Socket "
                            + server.getSocketString()
                            + " is already in use. Change the interface or the port and try again."));
                }
                if (monitor.isCanceled()) {
                    return Status.OK_STATUS;
                }
                monitor.worked(10);

                sm.addStartedProject(server.getSocketString(), project);
                sm.addProjectServer(project, server);
            }
        } catch (Exception e) {
            return new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                    "Preconditions were not met for launching the Sausalito CoreSDK web server.", e);
        } finally {
            monitor.done();
        }

        return Status.OK_STATUS;
    }

    private void checkLaunchPostconditions(ILaunch launch, Server server, IProgressMonitor monitor)
            throws CoreException {
        try {
            monitor.beginTask("Checking postconditions for the Sausalito CoreSDK server launch", 10);

            final IProject project = server.getProject();

            // make sure there is only one process in this lunch
            final IProcess process = server.getProcess();
            if (process == null) {
                throw new CoreException(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                        "The Sausalito CoreSDK server did not start properly."));
            }

            monitor.subTask("Waiting for the Sausalito CoreSDK server to start...");

            // wait until the server listens on the socket
            while (!server.isListening()) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ie) {
                    // should not happen
                }
                if (process.isTerminated()) {
                    abortLaunch(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                            "The Sausalito CoreSDK server exited abnormally."), project, server);
                }
//                if (monitor.isCanceled()) {
//                    abortLaunch(null, project, server);
//                }
            }

//            if (monitor.isCanceled()) {
//                return;
//            }
            monitor.worked(10);

            // configuring the server
            DebugPlugin.getDefault().addDebugEventListener(new IDebugEventSetListener() {
                public void handleDebugEvents(DebugEvent[] events) {
                    System.out.println(events.length + " " + events[0]);
                    System.out.println(events[0].getSource());
                    if (events.length > 0 && events[0].getKind() == DebugEvent.TERMINATE
                            && events[0].getSource() == process) {
                        final IDebugEventSetListener listener = this;
                        BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
                            public void run() {
                                ServerManager.getInstance().stopServer(project);
                                DebugPlugin.getDefault().removeDebugEventListener(listener);
                            }
                        });
                    }
                }
            });

//        } catch (Exception e) {
//            IStatus status = new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
//                    "An Exception was thrown while checking postconditions for Sausalito CoreSDK server launching.", e);
//            throw new CoreException(status);
        } finally {
            monitor.done();
        }
    }

    private void abortLaunch(IStatus status, IProject project, Server server) throws CoreException {
        ServerManager.getInstance().removeStartedProject(project);
        if (status != null) {
            throw new CoreException(status);
        }
        return;
    }

    public String getLanguageId() {
        return SETNature.NATURE_ID;
    }

    @Override
    protected String getScriptLaunchPath(ILaunchConfiguration configuration, IEnvironment scriptEnvironment)
            throws CoreException {
        // Sausalito is not based on a script that will be launched.
        // A start page will be read from the project configuration.
        return "";
    };
}
