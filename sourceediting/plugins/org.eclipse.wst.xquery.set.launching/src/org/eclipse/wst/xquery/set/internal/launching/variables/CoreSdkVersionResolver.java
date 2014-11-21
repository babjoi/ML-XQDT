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
package org.eclipse.wst.xquery.set.internal.launching.variables;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.wst.xquery.core.utils.ProcessStreamConsumer;
import org.eclipse.wst.xquery.set.launching.ISETLaunchingConstants;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSdkVersionResolver implements IDynamicVariableResolver {

    private static final String VERSION_LINE_PREFIX = "Sausalito Core SDK";

    public static final String SDK_VERSION_VARIABLE_NAME = "sdk_version";

    private static final String SDK_VERSION_VARIABLE = "${" + SDK_VERSION_VARIABLE_NAME + "}";

    private String fCachedValue = null;

    public String resolveValue(IDynamicVariable variable, String argument) throws CoreException {
        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, "Starting resolving of variable " + SDK_VERSION_VARIABLE
                    + " with default start value: null");
        }

        boolean isCached = fCachedValue != null;

        if (!isCached) {
            fCachedValue = doResolveValue(variable, argument);
        }

        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, SDK_VERSION_VARIABLE + " was resolved to"
                    + (isCached ? " (cached value)" : "") + ": " + fCachedValue);
        }

        return fCachedValue;
    }

    private String doResolveValue(IDynamicVariable variable, String argument) throws CoreException {
        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, "Starting resolving of variable " + SDK_VERSION_VARIABLE
                    + " with default start value: \"\"");
        }

        String coreSdk = CoreSdkLocationResolver.resolve();
        if (coreSdk == null || coreSdk.equals("")) {
            if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                CoreSdkLocationResolver.log(IStatus.INFO, "Could not find a valid Sausalito CoreSDK.");
            }
            return "";
        }

        String executable = CoreSdkExecNameResolver.resolve(CoreSdkExecNameResolver.SAUSALITO_SCRIPT_VARIABLE_NAME);
        if (executable == null || executable.equals("")) {
            if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                CoreSdkLocationResolver.log(IStatus.INFO, "Could not find a valid Sausalito CoreSDK.");
            }
            return "";
        }

        IPath scriptPath = new Path(coreSdk).append(ISETLaunchingConstants.SAUSALITO_EXECUTABLE_DIRECTORY).append(
                executable);

        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, "Using Sausalito executable at: " + scriptPath.toOSString());
        }

        String version = readVersionFromProcess(scriptPath.toOSString());
        if (version == null) {
            version = "";
        }
        return version;
    }

    private String readVersionFromProcess(String execPath) {
        String version = null;
        try {
            ProcessBuilder pb = new ProcessBuilder(execPath);
            if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                CoreSdkLocationResolver.log(IStatus.INFO, "Executing '" + execPath
                        + "' for retrieving the CoreSDK version");
            }
            Process p = pb.start();
            ProcessStreamConsumer psc = new ProcessStreamConsumer(p);
            psc.start();
            int code = p.waitFor();
            if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                CoreSdkLocationResolver.log(IStatus.INFO, "Version retrieving command exited with status: " + code);
            }
            String[] lines = psc.getOutput().split("\n");
            for (String line : lines) {
                if (line.startsWith(VERSION_LINE_PREFIX)) {
                    if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                        CoreSdkLocationResolver.log(IStatus.INFO, "Found version line: " + line);
                    }
                    line = line.replaceAll(VERSION_LINE_PREFIX + ".*ver. ", "");
                    line = line.replace(")", "");
                    version = line;
                    break;
                }
            }
        } catch (Exception e) {
            Status status = new Status(
                    IStatus.ERROR,
                    SETLaunchingPlugin.PLUGIN_ID,
                    "Could not determine Sausalito version for the CoreSDK name generation. Using the script name as default.",
                    e);
            SETLaunchingPlugin.log(status);
        }

        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, "Found version: " + version);
        }

        return version;
    }

    public static String resolve() {
        String executable = null;

        try {
            executable = VariablesPlugin.getDefault().getStringVariableManager()
                    .performStringSubstitution("${" + SDK_VERSION_VARIABLE_NAME + "}", false);
        } catch (CoreException ce) {
            SETLaunchingPlugin.log(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                    "An error occured while determining the Sausalito CoreSDK version.", ce));
            return null;
        }

        return executable;
    }

}
