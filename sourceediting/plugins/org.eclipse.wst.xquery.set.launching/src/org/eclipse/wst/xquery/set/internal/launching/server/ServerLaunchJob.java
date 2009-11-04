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
package org.eclipse.wst.xquery.set.internal.launching.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wst.xquery.set.debug.core.ISETLaunchConfigurationConstants;
import org.eclipse.wst.xquery.set.debug.core.SETDebugCorePlugin;
import org.eclipse.wst.xquery.set.debug.core.model.SETDebugTarget;
import org.eclipse.wst.xquery.set.internal.launching.SETLaunchConfigurationDelegate;

public class ServerLaunchJob extends Job {

    private List<IServerLaunchListener> fListeners = new ArrayList<IServerLaunchListener>(1);

    private Server fServer;
    private ILaunch fLaunch;

    public ServerLaunchJob(ILaunch launch, Server server) {
        super("Launch Web Server: " + server.getSocketString());
        fServer = server;
        fLaunch = launch;

        setUser(true);
    }

    protected IStatus run(IProgressMonitor monitor) {
        final IProject project;
        try {
            project = SETLaunchConfigurationDelegate.getLaunchProject(fLaunch);
        } catch (CoreException ce) {
            return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                    "Could not retreive the project for this launch configuration", ce);
        }

        ServerManager sm = ServerManager.getInstance();
        Server s = sm.getProjectServer(project);

        // do not launch started projects
        if (sm.isProjectStarted(project)) {
            // check if the process of this server is still running
            if (sm.isServerRunning(s)) {
                // this means we cannot launch one more time this project
                DebugPlugin.getDefault().getLaunchManager().removeLaunch(fLaunch);
                return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                        "It is not allowed to simultaneously run more instances of the same project (Project: "
                                + project.getName() + ")");
            }
            sm.removeStartedProject(project);
        }

        // check for zombie server processes
        if (sm.isServerRunning(s)) {
            return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                    "An old server process is still running for this project (Project: " + project.getName() + ")");
        }

        // check only internally is the same socket is already in use by other project
        if (!sm.isSocketFree(fServer.getHost(), fServer.getPort())) {
            Server srv = sm.getServer(fServer.getSocketString());
            if (sm.isServerRunning(srv)) {
                notifyListeners();
                return Status.CANCEL_STATUS;
            }
            sm.removeStartedServer(fServer.getHost() + ":" + fServer.getPort());
        }

        // everything is ok we can proceed

        // save the new port in the launch configuration
        try {
            ILaunchConfigurationWorkingCopy config = fLaunch.getLaunchConfiguration().getWorkingCopy();
            config.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_PORT, fServer.getPort());
            config.doSave();
        } catch (CoreException ce) {
            return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                    "An exception occured while updating the project launch configuration for project: "
                            + project.getName(), ce);
        }

        // update the server for this project in the ServerManager
        sm.addProjectServer(project, fServer);

        deleteOldServerPidFile(project);
        Process p;
        try {
            p = fServer.run();
        } catch (CoreException ce) {
            ce.printStackTrace();
            return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                    "An exception occured while starting the server: " + fServer.getSocketString(), ce);
        }

        ServerManager.getInstance().addStartedServer(fServer.getSocketString(), project);

        final IProcess process = DebugPlugin.newProcess(fLaunch, p, "Sausalito Development Web Server");
        IStreamsProxy proxy = process.getStreamsProxy();
        proxy.getErrorStreamMonitor().addListener(fServer);

        SETDebugTarget target = new SETDebugTarget(fLaunch, process, project);
        fLaunch.addDebugTarget(target);

        DebugPlugin.getDefault().addDebugEventListener(new IDebugEventSetListener() {

            public void handleDebugEvents(DebugEvent[] events) {
                if (events.length > 0 && events[0].getKind() == DebugEvent.TERMINATE
                        && events[0].getSource() == process) {
                    BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {

                        public void run() {
                            ServerManager.getInstance().stopServer(project);
                        }
                    });
                }
            }
        });

        if (!DebugPlugin.getDefault().getLaunchManager().isRegistered(fLaunch)) {
            DebugPlugin.getDefault().getLaunchManager().addLaunch(fLaunch);
        }

        long startTime = Calendar.getInstance().getTimeInMillis();
        try {
            Thread.sleep(250);
            while (!fServer.isListening() && startTime + 10000 > Calendar.getInstance().getTimeInMillis()) {
                Thread.sleep(250);
            }
            // in case we timed out and the server is still not listening
            if (!fServer.isListening()) {
                return cancelJob(target, process, false, null);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ServerNotStartedException snse) {
            boolean notify = (snse.getErrorCode() == ServerNotStartedException.SERVER_ERROR_SOCKET_IN_USE);
            return cancelJob(target, process, notify, snse);
        }

        return Status.OK_STATUS;
    }

    private IStatus cancelJob(IDebugTarget target, IProcess process, boolean notify, Throwable t) {
        ServerManager.getInstance().removeStartedServer(fServer.getSocketString());
        // fLaunch.removeDebugTarget(target);
        // fLaunch.removeProcess(process);
        try {
            fLaunch.terminate();
            process.terminate();
        } catch (DebugException e) {
            e.printStackTrace();
        }
        if (notify) {
            notifyListeners();
            return Status.CANCEL_STATUS;
        }
        return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID, "An error ocured while starting the server.", t);
    }

    private void notifyListeners() {
        for (IServerLaunchListener listener : fListeners) {
            listener.fail(this);
        }
    }

    public void addServerLaunchListener(IServerLaunchListener listener) {
        if (!fListeners.contains(listener)) {
            fListeners.add(listener);
        }
    }

    public void removeServerLaunchListener(IServerLaunchListener listener) {
        fListeners.remove(listener);
    }

    public Server getServer() {
        return fServer;
    }

    public void setServer(Server server) {
        fServer = server;
    }

    public ILaunch getLaunch() {
        return fLaunch;
    }

    private void deleteOldServerPidFile(IProject project) {
        File pidFile = new File(project.getLocation().toOSString(), "/test/log/httpd.pid");
        if (!pidFile.exists()) {
            return;
        }
        pidFile.delete();
    }

}
