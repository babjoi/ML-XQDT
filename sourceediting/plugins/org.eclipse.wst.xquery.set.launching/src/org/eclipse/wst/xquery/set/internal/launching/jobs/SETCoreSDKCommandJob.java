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
package org.eclipse.wst.xquery.set.internal.launching.jobs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.wst.xquery.set.core.SETCorePlugin;

abstract public class SETCoreSDKCommandJob extends Job {

    protected IProject fProject;
    private IProgressMonitor fMonitor;
    private OutputStream fOutputStream;

    public SETCoreSDKCommandJob(String name, IProject project, OutputStream output) {
        super(name);
        setUser(true);

        fProject = project;
        fOutputStream = output;
    }

    protected IStatus run(IProgressMonitor monitor) {

        doActionsBeforeRun();

        fMonitor = monitor;

        int ticks = getJobTaskSize();
        if (ticks == 0) {
            monitor.done();
            return handleNoTicks();
        }

        String name = getJobTaskName();

        monitor.beginTask(name, ticks);

        try {
            IScriptProject project = DLTKCore.create(fProject);
            IInterpreterInstall install = ScriptRuntime.getInterpreterInstall(project);

            List<String> cmdLine = new ArrayList<String>();
            cmdLine.add(install.getInstallLocation().getPath().toOSString());
            cmdLine.addAll(getCommandParameters());

            IExecutionEnvironment exeEnv = install.getExecEnvironment();

            EnvironmentVariable[] environmentVariables = install.getEnvironmentVariables();
            String[] vars = null;
            if (environmentVariables != null) {
                vars = new String[environmentVariables.length];
                for (int j = 0; j < environmentVariables.length; j++) {
                    vars[j] = environmentVariables[j].toString();
                }
            }
            final Process p = exeEnv.exec(cmdLine.toArray(new String[cmdLine.size()]), null, vars);

            InputStream input = p.getInputStream();
            readCommandOutput(input);
            int err = p.waitFor();
            if (err != 0) {
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                return reportError(sb.toString());
            }

        } catch (Exception ce) {
            return reportError(ce);
        } finally {
            monitor.done();
        }

        return Status.OK_STATUS;
    }

    protected IStatus handleNoTicks() {
        return Status.OK_STATUS;
    }

    protected void doActionsBeforeRun() {
    }

    protected void readCommandOutput(InputStream inputStream) throws IOException {
        OutputStream output = getOutputStream();

        BufferedReader ir = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = ir.readLine()) != null) {
            if (output != null) {
                output.write((line + "\n").getBytes());
            }
        }
    }

    abstract protected List<String> getCommandParameters();

    protected int getJobTaskSize() {
        return IProgressMonitor.UNKNOWN;
    }

    protected String getJobTaskName() {
        return "";
    }

    protected IStatus reportError(Throwable t) {
        return new Status(IStatus.ERROR, SETCorePlugin.PLUGIN_ID, t.getMessage(), t);
    }

    protected IStatus reportError(String message) {
        return new Status(IStatus.ERROR, SETCorePlugin.PLUGIN_ID, message);
    }

    protected IStatus reportWarning(String message) {
        return new Status(IStatus.WARNING, SETCorePlugin.PLUGIN_ID, message);
    }

    protected void updateMonitorTaskName(String taskName) {
        if (fMonitor != null) {
            fMonitor.setTaskName(taskName);
        }
    }

    protected void updateMonitorWork(int ammount) {
        if (fMonitor != null) {
            fMonitor.worked(ammount);
        }
    }

    protected OutputStream getOutputStream() {
        return fOutputStream;
    }

    public IProject getProject() {
        return fProject;
    }
}
