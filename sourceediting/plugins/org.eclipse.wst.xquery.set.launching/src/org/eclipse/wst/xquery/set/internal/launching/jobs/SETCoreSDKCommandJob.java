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
import java.util.Map;

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
import org.eclipse.wst.xquery.core.utils.ProcessStreamConsumer;
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

            // start the command process
            IExecutionEnvironment exeEnv = install.getExecEnvironment();
            String[] envp = getEnvironmentVariables(install);
            Process p = exeEnv.exec(cmdLine.toArray(new String[cmdLine.size()]), null, envp);

            // read the process output and error streams 
            ProcessStreamConsumer psc = new ProcessStreamConsumer(p, getOutputStream());
            psc.start();

            // wait for the process to terminate
            int err = p.waitFor();

            // wait for the consumer to read the entire contents of the streams
            psc.join();

            if (err == 0) {
                return reportWarning(psc.getError());
            }
            return reportError(psc.getError());

        } catch (Exception e) {
            return reportError(e);
        } finally {
            monitor.done();
        }
    }

    protected String[] getEnvironmentVariables(IInterpreterInstall install) {
        List<String> vars = new ArrayList<String>();
        IExecutionEnvironment exeEnv = install.getExecEnvironment();
        Map<String, String> envp = exeEnv.getEnvironmentVariables(true);
        for (String var : envp.keySet()) {
            vars.add(var + "=" + envp.get(var));
        }
        EnvironmentVariable[] environmentVariables = install.getEnvironmentVariables();
        if (environmentVariables != null) {
            for (EnvironmentVariable variable : environmentVariables) {
                vars.add(variable.getName() + "=" + variable.getValue());
            }
        }

        return vars.toArray(new String[0]);
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
        return new Status(IStatus.ERROR, SETCorePlugin.PLUGIN_ID, "See details for more information.", new Exception(
                message));
    }

    protected IStatus reportWarning(String message) {
        if (message != null) {
            StringBuffer sb = new StringBuffer();

            String[] lines = message.split("\n");
            for (String line : lines) {
                if (line.startsWith(getWarningLinePrefix())) {
                    sb.append(line + "\n");
                }
            }
            if (sb.length() > 0) {
                return new Status(IStatus.WARNING, SETCorePlugin.PLUGIN_ID, sb.toString());
            }
        }

        return Status.OK_STATUS;
    }

    protected String getWarningLinePrefix() {
        return "[WARNING]";
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
