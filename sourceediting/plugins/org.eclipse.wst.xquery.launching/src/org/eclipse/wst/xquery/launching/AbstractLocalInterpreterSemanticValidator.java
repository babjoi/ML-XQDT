/*******************************************************************************
 * Copyright (c) 2008 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *     William Candillon (28msec) - extensions for quick fix support
 *******************************************************************************/
package org.eclipse.wst.xquery.launching;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.core.semantic.CheckErrorReportReaderFactory;
import org.eclipse.wst.xquery.core.semantic.ISemanticValidator;
import org.eclipse.wst.xquery.core.semantic.SemanticCheckError;
import org.eclipse.wst.xquery.core.semantic.SemanticCheckErrorReportReader;
import org.eclipse.wst.xquery.core.utils.ProcessStreamConsumer;

public abstract class AbstractLocalInterpreterSemanticValidator implements ISemanticValidator {

    private IInterpreterInstall fInterpreterInstall;
    private CheckErrorReportReaderFactory fErrorReportReaderFactory;

    public AbstractLocalInterpreterSemanticValidator(IInterpreterInstall install) {
        fInterpreterInstall = install;
    }

    protected IInterpreterInstall getInterpreterInstall() {
        return fInterpreterInstall;
    }

    public AbstractLocalInterpreterSemanticValidator setReportReaderFactory(CheckErrorReportReaderFactory factory) {
        fErrorReportReaderFactory = factory;
        return this;
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

        if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
            StringBuilder sb = new StringBuilder("Semantic checker command: ");
            for (String par : cmdLine) {
                sb.append("\"" + par + "\"");
            }
            log(IStatus.INFO, sb.toString(), null);
        }

        Process p = exeEnv.exec(cmdLine, null, vars);

        if (p == null) {
            abort("Could not invoke the Semantic Validator");
        }
        try {
            ProcessStreamConsumer psc = new ProcessStreamConsumer(p);

            if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
                log(IStatus.INFO, "Starting the process stream consumer", null);
            }
            psc.start();

            if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
                log(IStatus.INFO, "Waiting for termination", null);
            }
            int errorCode = p.waitFor();
            if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
                log(IStatus.INFO, "Exited with error code: " + errorCode, null);
            }
            if (errorCode == 0) {
                if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
                    log(IStatus.INFO, "No problem detected. Abort semantic checking.", null);
                }
                return null;
            }
            psc.join();
            String error = psc.getError();
            String output = psc.getOutput();

            if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error: " + (error != null ? error : "null") + "\n");
                sb.append("Output: " + (output != null ? output : "null"));
                log(IStatus.INFO, sb.toString(), null);
            }

            if (error == null || error.trim().length() == 0) {
                if (output == null || output.trim().length() == 0) {
                    abort("An unknown error occurred while executing the Semantic Validator command");
                } else {
                    abort("An error occurred while executing the Semantic Validator command:\n" + output.trim());
                }
            }

            if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
                log(IStatus.INFO, "Building error document", null);
            }

            SemanticCheckErrorReportReader docReader = null;
            if (fErrorReportReaderFactory != null) {
                docReader = fErrorReportReaderFactory.make(module, error);
            } else {
                docReader = new SemanticCheckErrorReportReader(module, error);
            }
            List<SemanticCheckError> errors = docReader.getErrors();
            if (errors == null || errors.size() == 0) {
                abort("An error occurred while executing the Semantic Validator command:\n" + error.trim());
            }
            if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
                log(IStatus.INFO, "", null);
                StringBuilder sb = new StringBuilder("Errors read. First error details:");
                sb.append("Message: " + errors.get(0).getMessage());
                sb.append("Start: " + errors.get(0).getSourceStart());
                sb.append("End: " + errors.get(0).getSourceEnd());
                sb.append("File: " + errors.get(0).getOriginatingFileName());
                sb.append("Line: " + errors.get(0).getSourceLineNumber());
            }
            return errors;

        } catch (InterruptedException e) {
            abort("Exception while waiting for the Semantic Validator to terminate");
        }

        return null;
    }

    public abstract String[] buildCommandLine(ISourceModule module);

    protected void abort(String message) throws CoreException {
        CoreException exception = new CoreException(new Status(IStatus.ERROR, XQDTCorePlugin.PLUGIN_ID, message));
        if (XQDTLaunchingPlugin.DEBUG_SEMANTIC_CHECK) {
            log(IStatus.ERROR, message, exception);
        }
        throw exception;
    }

    public static void log(int severity, String message, Throwable t) {
        XQDTLaunchingPlugin.log(new Status(severity, XQDTLaunchingPlugin.PLUGIN_ID, message, t));
    }

}