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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.launching.server.Server;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerManager;
import org.eclipse.wst.xquery.set.launching.SETLaunchUtil;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class SETLaunchConfigurationDelegate extends AbstractScriptLaunchConfigurationDelegate {

    private final static Object fLock = new Object();

    public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
            IProgressMonitor monitor) throws CoreException {

        try {
            if (mode.equals(ILaunchManager.DEBUG_MODE)) {
                throw new CoreException(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                        "The Sausalito Debugger is not available in this version of Sausalito Eclipse Tools."));
            }

            monitor.beginTask("Starting the Sausalito CoreSDK Server", 10);
            if (monitor.isCanceled()) {
                return;
            }

            Server server = ServerManager.getInstance().createServer(launch, getProject(configuration));

            // make sure we can start the server
            checkLaunchPreconditions(launch, server, new SubProgressMonitor(monitor, 2));
            // after this point the socket, server and project are registered in the server manager
            if (monitor.isCanceled()) {
                return;
            }
            // from now on cancelling monitors must also unregister the
            // socket, server and project from the server manager 

//            Thread thread = new Thread(new Runnable() {
//                public void run() {
//                    // start the server
//                    try {
            SETLaunchConfigurationDelegate.super.launch(configuration, mode, launch, new NullProgressMonitor());
//                    } catch (CoreException e) {
//                        e.printStackTrace();
//                    }
////                            monitor, 3));
////                    if (monitor.isCanceled()) {
////                        return;
////                    }
//                }
//            }, "");
//            thread.start();

            // make sure we started the server (setver is listening)
            checkLaunchPostconditions(launch, server, new SubProgressMonitor(monitor, 2));
            if (monitor.isCanceled()) {
                return;
            }

            // open the browser
            monitor.subTask("Opening the browser");
            SETLaunchUtil.openBrowser(launch);
            if (monitor.isCanceled()) {
                return;
            }
            monitor.worked(3);
        } finally {
            monitor.done();
        }
    }

    private void checkLaunchPreconditions(ILaunch launch, Server server, IProgressMonitor monitor) throws CoreException {
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
                    return;
                }
                monitor.worked(10);

                sm.addStartedProject(server.getSocketString(), project);
                sm.addProjectServer(project, server);
            }

        } finally {
            monitor.done();
        }
    }

//    private boolean tryOtherSocket(Server server) {
//        Shell shell = Display.getDefault().getActiveShell();
//        BusySocketDialog dialog = new BusySocketDialog(shell, server);
//        if (dialog.open() == TrayDialog.OK) {
//            server.setHost(dialog.getHost());
//            server.setPort(dialog.getPort());
//            return true;
//        }
//
//        return false;
//    }

    private void checkLaunchPostconditions(ILaunch launch, Server server, IProgressMonitor monitor)
            throws CoreException {
        try {
            monitor.beginTask("Checking postconditions for the Sausalito CoreSDK server launch", 10);

            final IProject project = server.getProject();

//            try {
//                // give the other thread a chance to start the process
//                Thread.sleep(3000);
//
//            } catch (InterruptedException ie) {
//            }

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
