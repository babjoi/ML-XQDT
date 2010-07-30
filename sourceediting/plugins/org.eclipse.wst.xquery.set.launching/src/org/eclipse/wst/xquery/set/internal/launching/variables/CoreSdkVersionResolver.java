package org.eclipse.wst.xquery.set.internal.launching.variables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.wst.xquery.set.launching.ISETLaunchingConstants;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSdkVersionResolver implements IDynamicVariableResolver {

    private static final String VERSION_PREFIX = "Sausalito Core SDK, (ver. ";

    public static final String SDK_VERSION_VARIABLE_NAME = "sdk_version";

    private static final String SDK_VERSION_VARIABLE = "${" + SDK_VERSION_VARIABLE_NAME + "}";

    public String resolveValue(IDynamicVariable variable, String argument) throws CoreException {
        String result = doResolveValue(variable, argument);

        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, SDK_VERSION_VARIABLE + " was resolved to: " + result, null);
        }

        return result;
    }

    private String doResolveValue(IDynamicVariable variable, String argument) throws CoreException {
        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, "Starting resolving of variable " + SDK_VERSION_VARIABLE
                    + " with default start value: \"\"", null);
        }

        String result = "";
        String coreSdk = CoreSdkLocationResolver.resolve();
        if (coreSdk == null || coreSdk.equals("")) {
            if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                CoreSdkLocationResolver.log(IStatus.INFO, "Could not find a valid Sausalito CoreSDK.", null);
            }
            return "";
        }

        String executable = CoreSdkExecNameResolver.resolve(CoreSdkExecNameResolver.SAUSALITO_SCRIPT_VARIABLE_NAME);
        if (executable == null || executable.equals("")) {
            if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                CoreSdkLocationResolver.log(IStatus.INFO, "Could not find a valid Sausalito CoreSDK.", null);
            }
            return "";
        }

        IPath scriptPath = new Path(coreSdk).append(ISETLaunchingConstants.SAUSALITO_EXECUTABLE_DIRECTORY).append(
                executable);

        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver
                    .log(IStatus.INFO, "Using Sausalito executable at: " + scriptPath.toOSString(), null);
        }

        ProcessBuilder pb = new ProcessBuilder(scriptPath.toOSString());
        try {
            final Process p = pb.start();
            final List<String> lines = new ArrayList<String>();
            Thread reader = new Thread(new Runnable() {
                public void run() {
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    try {
                        String line = br.readLine();
                        while (line != null) {
                            lines.add(line + "\n");
                            line = br.readLine();
                        }
                    } catch (IOException e) {
                    }
                }
            });

            if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                CoreSdkLocationResolver.log(IStatus.INFO, "Executing command for retrieving the version", null);
            }
            reader.start();

            int code = p.waitFor();
            if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                CoreSdkLocationResolver.log(IStatus.INFO, "Version retrieving command exited with status: " + code,
                        null);
            }

            String version = null;
            for (String line : lines) {
                if (line.startsWith(VERSION_PREFIX)) {
                    if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                        CoreSdkLocationResolver.log(IStatus.INFO, "Found version line: " + line, null);
                    }
                    version = line.substring(VERSION_PREFIX.length(), line.lastIndexOf(')'));
                    break;
                }
            }
            if (version != null) {
                if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                    CoreSdkLocationResolver.log(IStatus.INFO,
                            "Did not find a version number by executing the Sausalito script", null);
                }
                result = version;
            }
            CoreSdkLocationResolver.log(IStatus.INFO, "Found version: " + result, null);

        } catch (Exception e) {
            CoreSdkLocationResolver.log(IStatus.ERROR,
                    "An exception was thrown while trying to retrieve the Sausalito CoreSDK version", e);
        }

        return result;
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
