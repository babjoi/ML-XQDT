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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.wst.xquery.set.debug.core.SETDebugCorePlugin;
import org.eclipse.wst.xquery.set.launching.CoreSdkUtil;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class ServerManager {

    private static ServerManager instance;

    /**
     * A socket to project mapping.
     */
    private Map<String, IProject> fStartedProjects = new HashMap<String, IProject>();
    private Map<IProject, Server> fProjectServers = new HashMap<IProject, Server>();

    private List<IServerLaunchListener> fServerLaunchListeners = new ArrayList<IServerLaunchListener>();

    private ServerManager() {
    }

    public static ServerManager getInstance() {
        if (instance == null) {
            instance = new ServerManager();
        }
        return instance;
    }

    public void addServerLaunchListener(IServerLaunchListener listener) {
        if (fServerLaunchListeners.contains(listener)) {
            return;
        }
        fServerLaunchListeners.add(listener);
    }

    public void removeServerLaunchListener(IJobChangeListener listener) {
        fServerLaunchListeners.remove(listener);
    }

    public synchronized void addStartedProject(String socket, IProject project) {
        fStartedProjects.put(socket, project);
    }

    public synchronized void removeStartedServer(String socket) {
        fStartedProjects.remove(socket);
    }

    public synchronized Server createServer(ILaunch launch, IProject project) throws CoreException {
        return new Server(launch, project);
    }

    public void stopServer(final IProject project) {
        final Server server = getProjectServer(project);
        final String socket = server.getSocketString();

        Job job = new Job("Stopping server: " + socket) {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                try {
                    int pid = getServerPid(project);
                    String killCmdName = CoreSdkUtil.getKillCommandPath(project).toOSString();
                    List<String> commandList = new ArrayList<String>(3);
                    commandList.add(killCmdName);
                    commandList.add("" + pid);
                    ProcessBuilder pb = new ProcessBuilder(commandList);
                    Process p = pb.start();

                    int ec;
                    while (true) {
                        try {
                            ec = p.exitValue();
                            break;
                        } catch (IllegalThreadStateException itse) {
                            Thread.sleep(250);
                        }
                    }

                    synchronized (project) {
                        // TODO: must resolve the term.exe error code problem
                        // such that the following applies also on Windows
                        if (!Platform.getOS().equals(Platform.OS_WIN32) && ec != 0) {
                            handleKillProcessError(p, commandList);
                        } else {
                            if (SETLaunchingPlugin.DEBUG_SERVER) {
                                System.out.println("Terminate command exited succesfully");
                            }
                        }

                        while (server.isListening()) {
                            Thread.sleep(250);
                        }
                    }

                } catch (IOException ioe) {
                    return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                            "Could not properly stop the server for project \"" + project.getName()
                                    + "\" using socket \"" + socket + "\"", ioe);
                } catch (CoreException ce) {
                    ce.printStackTrace();
                    return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                            "Error while stopping server for project \"" + project.getName() + "\" using socket \""
                                    + socket + "\"", ce);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    return new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                            "Could not properly stop the server for project \"" + project.getName()
                                    + "\" using socket \"" + socket + "\"", ie);
                } finally {
                    removeStartedServer(server.getSocketString());
                }
                return new Status(IStatus.OK, SETDebugCorePlugin.PLUGIN_ID, "Server " + server.getPort() + " stopped");
            }

            private void handleKillProcessError(Process p, List<String> commandList) throws CoreException {
                StringBuilder sb = new StringBuilder();
                for (String s : commandList) {
                    sb.append(s + " ");
                }
                StringBuilder sbe = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                try {
                    String line;
                    while ((line = br.readLine()) != null) {
                        sbe.append(line + "\n");
                    }
                } catch (IOException ioe) {
                    sbe.append("Could not read the procees error stream: " + ioe.getMessage());
                }
                String message = "Terminate command did not exit properly (" + p.exitValue() + "): " + sb.toString()
                        + "\nError: " + sbe.toString();
                throw new CoreException(new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID, message));
            }

        };
        job.setSystem(false);
        job.schedule();
    }

    public Server getServer(String socket) {
        IProject p = getProject(socket);
        return p == null ? null : fProjectServers.get(p);
    }

    public IProject getProject(String socket) {
        return fStartedProjects.get(socket);
    }

    public boolean isSocketFree(String host, int port) {
        return !fStartedProjects.containsKey(host + ":" + port);
    }

    public boolean isProjectStarted(IProject project) {
        return fStartedProjects.containsValue(project);
    }

    public void removeStartedProject(IProject project) {
        if (isProjectStarted(project)) {
            Iterator<Entry<String, IProject>> it = fStartedProjects.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, IProject> e = it.next();
                if (e.getValue().equals(project)) {
                    it.remove();
                    break;
                }
            }
        }
    }

    public static int getServerPid(IProject project) throws DebugException {
        try {
            File pidFile = new File(project.getLocation().toOSString(), "/test/log/httpd.pid");
            if (!pidFile.exists()) {
                throw new FileNotFoundException();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pidFile)));
            String pidStr = br.readLine().trim();
            int pid = Integer.parseInt(pidStr);
            return pid;
        } catch (Exception e) {
            throw new DebugException(new Status(IStatus.ERROR, SETDebugCorePlugin.PLUGIN_ID,
                    "An exception occurred while retrieving the web server process ID.", e));
        }
    }

    public Server getProjectServer(IProject project) {
        return fProjectServers.get(project);
    }

    public void addProjectServer(IProject project, Server server) {
        fProjectServers.put(project, server);
    }

    public boolean isServerRunning(Server server) {
        if (server == null) {
            return false;
        }

        IProcess p = server.getProcess();
        if (p == null) {
            return false;
        }

        try {
            p.getExitValue();
        } catch (DebugException de) {
            // thrown when the process still runs
            // this means zombie process
            return true;
        }

        return false;
    }
}
