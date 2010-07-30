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
package org.eclipse.wst.xquery.set.internal.launching.variables;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.wst.xquery.launching.XQDTLaunchingPlugin;
import org.eclipse.wst.xquery.set.launching.ISETLaunchingConstants;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;
import org.osgi.framework.Bundle;

public class CoreSdkLocationResolver implements IDynamicVariableResolver {

    public static final String SDK_LOCATION_VARIABLE_NAME = "sdk_location";

    private static final String SDK_LOCATION_VARIABLE = "${" + SDK_LOCATION_VARIABLE_NAME + "}";

    private static final String WIN_DIR_NAME_PREFIX = "Sausalito-CoreSDK";

    public String resolveValue(IDynamicVariable variable, String argument) throws CoreException {
        String result = findStrategies();

        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            log(IStatus.INFO, SDK_LOCATION_VARIABLE + " was resolved to: " + result, null);
        }

        return result;
    }

    private String findStrategies() {
        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            log(IStatus.INFO, "Starting resolving of variable " + SDK_LOCATION_VARIABLE
                    + " with default start value: null", null);
        }
        String result = null;

        // I. first search for the shipped Sausalito CoreSDK
        // this case happens in the 28msec distribution of the plugins
        result = findShippedCoreSDK();
        if (result != null) {
            return result;
        }
        if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            log(IStatus.INFO, "No shipped Sausalito CoreSDK was found.", null);
        }

        // II. if no CoreSDK is shipped (for some platforms)
        // go and search in some predefined install locations
        result = findInstalledCoreSDK();
        if (result != null) {
            return result;
        }
        if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            log(IStatus.INFO, "No installed Sausalito CoreSDK was found.", null);
        }

        return result;
    }

    private String findShippedCoreSDK() {
        String os = Platform.getOS();

        String osPart = "." + os;
        String archPart = "";

        // in case of non-Windows platforms we make have more versions of the CoreSDK
        if (!os.equals(Platform.OS_WIN32)) {
            archPart = "." + Platform.getOSArch();
        }

        String pluginID = "com.28msec.sausalito";
        String fragment = pluginID + osPart + archPart;

        Bundle[] bundles = Platform.getBundles(fragment, null);
        if (bundles == null || bundles.length == 0) {
            if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                log(IStatus.INFO, "Could not find plug-in fragment: " + fragment
                        + ". No default Sausalito CoreSDK will be configured.", null);
            }
            return null;
        }
        if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            log(IStatus.INFO, "Found Sausalito CoreSDK plug-in fragment: " + fragment, null);
        }

        Bundle bundle = bundles[0];
        URL coreSdkUrl = FileLocator.find(bundle, new Path("coresdk"), null);
        if (coreSdkUrl == null) {
            if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                log(IStatus.INFO, "Could not find the \"coresdk\" directory in plug-in fragment: " + fragment
                        + ". No default Sausalito CoreSDK will be configured.", null);
            }
            return null;
        }
        try {
            coreSdkUrl = FileLocator.toFileURL(coreSdkUrl);
        } catch (IOException ioe) {
            log(IStatus.ERROR, "Cound not retrieve the Eclipse bundle location: " + fragment, ioe);
            return null;
        }

        IPath coreSdkPath = new Path(coreSdkUrl.getPath());

        if (isScriptIn(coreSdkPath)) {
            return coreSdkPath.toOSString();
        }

        return null;
    }

    private String findInstalledCoreSDK() {
        if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            log(IStatus.INFO, "Trying to find an installed CoreSDK version", null);
        }
        String os = Platform.getOS();
        IPath possiblePath = null;
        if (os.equals(Platform.OS_WIN32)) {
            String programFiles = System.getenv("ProgramFiles");
            if (programFiles == null) {
                return null;
            }
            possiblePath = getLatestFromProgramFiles(new File(programFiles));
        } else {
            possiblePath = new Path("/opt/sausalito");
        }

        if (isScriptIn(possiblePath)) {
            return possiblePath.toOSString();
        }

        return null;
    }

    private IPath getLatestFromProgramFiles(File programFiles) {
        if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            log(IStatus.INFO,
                    "Searching for the latest installed CoreSDK version in Program Files. The installation must start with: "
                            + WIN_DIR_NAME_PREFIX, null);
        }
        IPath result = null;

        try {
            String[] programs = programFiles.list();

            List<String> candidates = new ArrayList<String>();
            for (String program : programs) {
                if (program.startsWith(WIN_DIR_NAME_PREFIX)) {
                    candidates.add(program);
                }
            }
            if (candidates.size() == 0) {
                if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION
                        || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                    log(IStatus.INFO, "No candidate installs found.", null);
                }
                result = null;
            } else if (candidates.size() == 1) {
                if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION
                        || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                    log(IStatus.INFO, "Found one install candidate: " + candidates.get(0), null);
                }
                result = new Path(programFiles.getPath()).append(candidates.get(0));
            } else {
                if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION
                        || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                    log(IStatus.INFO, "Found " + candidates.size() + " install candidate.", null);
                }
                String candidate = candidates.get(0);
                for (String newCandidate : candidates) {
                    if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION
                            || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                        log(IStatus.INFO, "Found candidate install: " + newCandidate, null);
                    }
                    if (candidate.compareTo(newCandidate) < 0) {
                        candidate = newCandidate;
                    }
                }
                result = new Path(programFiles.getAbsolutePath()).append(candidate);
                if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION
                        || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
                    log(IStatus.INFO, "Chose install: " + result.toString(), null);
                }
            }

        } catch (Exception e) {
        }

        return result;
    }

    private boolean isScriptIn(IPath coreSdkPath) {
        if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION || SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            log(IStatus.INFO, "Checking path: " + coreSdkPath.toOSString(), null);
        }

        if (coreSdkPath == null) {
            NullPointerException npe = new NullPointerException();
            log(IStatus.ERROR, "Could not locate the Sausalito script.", npe);
            return false;
        }

        String executable = CoreSdkExecNameResolver.resolve(CoreSdkExecNameResolver.SAUSALITO_SCRIPT_VARIABLE_NAME);
        IPath scriptPath = coreSdkPath.append(ISETLaunchingConstants.SAUSALITO_EXECUTABLE_DIRECTORY).append(executable);

        File scrptFile = scriptPath.toFile();
        if (!scrptFile.exists()) {
            log(IStatus.ERROR, "Could not find the Sausalito script at location: " + coreSdkPath.toOSString(), null);
            return false;
        }

        return true;
    }

    public static String resolve() {
        String executable = null;

        try {
            executable = VariablesPlugin.getDefault().getStringVariableManager()
                    .performStringSubstitution(SDK_LOCATION_VARIABLE, false);
        } catch (CoreException ce) {
            SETLaunchingPlugin.log(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                    "An error occured while searching for an installed Sausalito CoreSDK.", ce));
            return null;
        }

        return executable;
    }

    public static IStatus log(int severity, String message, Throwable t) {
        Status status = new Status(severity, SETLaunchingPlugin.PLUGIN_ID, message, t);
        SETLaunchingPlugin.log(status);
        return status;
    }

}
