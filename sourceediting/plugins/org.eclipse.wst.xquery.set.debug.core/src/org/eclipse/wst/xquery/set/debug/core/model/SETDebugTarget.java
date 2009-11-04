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
package org.eclipse.wst.xquery.set.debug.core.model;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.wst.xquery.set.debug.core.ISETLaunchConfigurationConstants;

public class SETDebugTarget implements IDebugTarget {

    private IProcess fProcess;
    private ILaunch fLaunch;
    private IProject fProject;
    private String fName;

    public SETDebugTarget(ILaunch launch, IProcess process, IProject project) {
        fProcess = process;
        fLaunch = launch;
        fProject = project;

        init(launch, project);
    }

    private void init(ILaunch launch, IProject project) {
        ILaunchConfiguration config = fLaunch.getLaunchConfiguration();
        try {
            String host = config.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_HOST, "localhost");
            int port = config.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_PORT, 8080);
            fName = "Project \"" + fProject.getName() + "\" running at http://" + host + ":" + port;
        } catch (CoreException e) {
            fName = "Project \"" + fProject.getName() + "\" (unknown host and port)";
        }
    }

    public String getName() throws DebugException {
        return fName;
    }

    public IProcess getProcess() {
        return fProcess;
    }

    public IThread[] getThreads() throws DebugException {
        return new IThread[0];
    }

    public boolean hasThreads() throws DebugException {
        return false;
    }

    public boolean supportsBreakpoint(IBreakpoint breakpoint) {
        return false;
    }

    public IDebugTarget getDebugTarget() {
        return this;
    }

    public ILaunch getLaunch() {
        return fLaunch;
    }

    public String getModelIdentifier() {
        return "id";
    }

    @SuppressWarnings("unchecked")
    public Object getAdapter(Class adapter) {
        return null;
    }

    public boolean canTerminate() {
        return !isTerminated();
    }

    public boolean isTerminated() {
        return fProcess.isTerminated();
    }

    public void terminate() throws DebugException {
//        BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
//
//            public void run() {
//                ServerManager.getInstance().stopServer(fProject);
//            }
//        });
        fProcess.terminate();
    }

    public boolean canResume() {
        return false;
    }

    public boolean canSuspend() {
        return false;
    }

    public boolean isSuspended() {
        return false;
    }

    public void resume() throws DebugException {
    }

    public void suspend() throws DebugException {
    }

    public void breakpointAdded(IBreakpoint breakpoint) {
    }

    public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
    }

    public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
    }

    public boolean canDisconnect() {
        return false;
    }

    public void disconnect() throws DebugException {
    }

    public boolean isDisconnected() {
        return false;
    }

    public IMemoryBlock getMemoryBlock(long startAddress, long length) throws DebugException {
        return null;
    }

    public boolean supportsStorageRetrieval() {
        return false;
    }
}
