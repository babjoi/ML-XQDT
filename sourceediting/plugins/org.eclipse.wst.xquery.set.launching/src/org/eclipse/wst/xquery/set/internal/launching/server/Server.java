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
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.wst.xquery.set.launching.CoreSdkUtil;

public class Server implements IStreamListener {

    private IProject fProject;

    private StringBuilder fErrors;

    private String fHost;
    private int fPort;
    private boolean fIndentResults;
    private boolean fClearCollections;

    private Process fProcess;

    public Server(IProject project, String host, int port, boolean indent, boolean clear) {
        fProject = project;

        fHost = host;
        fPort = port;
        fIndentResults = indent;
        fClearCollections = clear;
    }

    public String getHost() {
        return fHost;
    }

    public void setHost(String host) {
        fHost = host;
    }

    public String getSocketString() {
        return fHost + ":" + fPort;
    }

    public int getPort() {
        return fPort;
    }

    public void setPort(int port) {
        fPort = port;
    }

    public Process getProcess() {
        return fProcess;
    }

    public boolean isListening() throws ServerNotStartedException {
        if (fProcess == null) {
            return false;
        }

        // check is the (batch/bash) process is already terminated
        // and report an exception if it is so
        try {
            if (hasErrors()) {
                // just wait a little more in case some more errors are appended to the error buffer
                Thread.sleep(300);
                throwSNSE();
            }

            System.out.println("checking exit");
            int error = fProcess.exitValue();
            System.out.println("exited with error code: " + error);
            throwSNSE();
        } catch (IllegalThreadStateException itse) {
            // OK: this is what we want
            System.out.println("did not exit");
        } catch (InterruptedException e) {
            throwSNSE();
        }

        // check if apache created the pid file (someone
        // else might be listening on this server's port)
        try {
            System.out.println("check pid");
            int pid = ServerManager.getServerPid(fProject);
            System.out.println("pid found: " + pid);
        } catch (DebugException de) {
            System.out.println("pid error");
            return false;
        }

        // check if a HTTP connection to this server's port is possible
        URL testUrl;
        try {
            testUrl = new URL("http", getHost(), getPort(), "/");
            HttpURLConnection conn = (HttpURLConnection)testUrl.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getResponseCode();
        } catch (ConnectException ce) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private boolean hasErrors() {
        return fErrors != null;
    }

    protected Process run() throws CoreException {
        fErrors = null;

        String scriptPath = CoreSdkUtil.getCoreSDKScriptPath(fProject).toOSString();

        final List<String> commandLine = new ArrayList<String>(5);
        commandLine.add(scriptPath);
        commandLine.add("test");
        commandLine.add("project");

        String path = fProject.getLocation().toOSString();
        commandLine.add("-d");
        commandLine.add(path);

        // add the listening interface parameter
        commandLine.add("-s");
        commandLine.add(fHost + ":" + fPort);

        if (fIndentResults) {
            commandLine.add("-i");
        }
        if (fClearCollections) {
            commandLine.add("-c");
        }

        fProcess = DebugPlugin.exec(commandLine.toArray(new String[commandLine.size()]), new File(path));

        return fProcess;
    }

    private void throwSNSE() throws ServerNotStartedException {
        int errorCode = ServerNotStartedException.SERVER_ERROR_START_FAILED;
        String errors = (hasErrors() ? fErrors.toString() : "");
        String start = errors.substring(0, Math.min(30, errors.length()));
        if (start.startsWith("(OS 10048)") || start.startsWith("(48)") || start.contains("Address already in use")) {
            errorCode = ServerNotStartedException.SERVER_ERROR_SOCKET_IN_USE;
        }
        throw new ServerNotStartedException(errors, errorCode);
    }

    public void streamAppended(String text, IStreamMonitor monitor) {
        if (!hasErrors()) {
            fErrors = new StringBuilder();
        }
        fErrors.append(text);
    }
}

class ServerNotStartedException extends Exception {

    private static final long serialVersionUID = 6900278879555009638L;

    public static final int SERVER_ERROR_SOCKET_IN_USE = 1;
    public static final int SERVER_ERROR_START_FAILED = 2;

    private int fErrorCode;

    public ServerNotStartedException(int errorCode) {
        this("Unknown error", errorCode);
    }

    public ServerNotStartedException(String message, int errorCode) {
        super(message);
        fErrorCode = errorCode;
    }

    public int getErrorCode() {
        return fErrorCode;
    }
}
