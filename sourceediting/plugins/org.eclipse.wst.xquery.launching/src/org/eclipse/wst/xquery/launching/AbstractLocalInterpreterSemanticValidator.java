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
package org.eclipse.wst.xquery.launching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.core.semantic.ISemanticValidator;
import org.eclipse.wst.xquery.core.semantic.SemanticCheckError;
import org.eclipse.wst.xquery.core.semantic.SemanticCheckErrorReportReader;

public abstract class AbstractLocalInterpreterSemanticValidator implements ISemanticValidator {

    private IInterpreterInstall fInterpreterInstall;

    public AbstractLocalInterpreterSemanticValidator(IInterpreterInstall install) {
        fInterpreterInstall = install;
    }

    protected IInterpreterInstall getInterpreterInstall() {
        return fInterpreterInstall;
    }

    public List<SemanticCheckError> check(ISourceModule module) throws CoreException {
        IExecutionEnvironment exeEnv = fInterpreterInstall.getExecEnvironment();

        String[] cmdLine = buildCommandLine(module);
        EnvironmentVariable[] environmentVariables = fInterpreterInstall.getEnvironmentVariables();
        String[] vars = null;
        if (environmentVariables != null && environmentVariables.length > 0) {
            vars = new String[environmentVariables.length];
            for (int i = 0; i < environmentVariables.length; i++) {
                vars[i] = environmentVariables[i].toString();
            }
        }

        final Process p = exeEnv.exec(cmdLine, null, vars);
        final StringBuffer[] buffer = new StringBuffer[2];

        if (p == null) {
            abort("Could not invoke the Semantic Validator");
        }
        try {
            Thread t = new Thread(new Runnable() {

                public void run() {
                    try {
                        BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        StringBuffer outputReport = new StringBuffer();
                        for (String line; (line = output.readLine()) != null;) {
                            outputReport.append(line + "\n");
                        }
                        buffer[1] = outputReport;

                        BufferedReader errstr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        StringBuffer errorReport = new StringBuffer();

                        for (String line; (line = errstr.readLine()) != null;) {
                            errorReport.append(line + "\n");
                        }
                        buffer[0] = errorReport;
                    } catch (IOException e) {
                        // abort("Exception while reading the the Semantic Validator output streams");
                    }
                }
            });
            t.start();

            int errorCode = p.waitFor();
            if (errorCode == 0) {
                return null;
            }

            t.join();
            if (buffer[0] == null || buffer[0].toString().trim().length() == 0) {
                if (buffer[1] == null || buffer[1].toString().trim().length() == 0) {
                    abort("An unknown error occured while executing the Semantic Validator command");
                } else {
                    abort("An error occured while executing the Semantic Validator command:\n"
                            + buffer[1].toString().trim());
                }
            }

            SemanticCheckErrorReportReader builder = new SemanticCheckErrorReportReader(module, buffer[0].toString());
            List<SemanticCheckError> errors = builder.getErrors();
            if (errors == null || errors.size() == 0) {
                abort("An error occured while executing the Semantic Validator command:\n"
                        + buffer[0].toString().trim());
            }
            return errors;

        } catch (InterruptedException e) {
            abort("Exception while waiting for the Semantic Validator to terminate");
        }

        return null;
    }

    public abstract String[] buildCommandLine(ISourceModule module);

    private void abort(String message) throws CoreException {
        throw new CoreException(new Status(IStatus.ERROR, XQDTCorePlugin.PLUGIN_ID, message));
    }
}