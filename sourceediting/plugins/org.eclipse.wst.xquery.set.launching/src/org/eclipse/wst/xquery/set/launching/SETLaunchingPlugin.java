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
package org.eclipse.wst.xquery.set.launching;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.core.internal.environment.LocalEnvironment;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallChangedListener;
import org.eclipse.dltk.launching.PropertyChangeEvent;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.launching.CoreSDKInstall;
import org.eclipse.wst.xquery.set.internal.launching.variables.CoreSdkExecNameResolver;
import org.eclipse.wst.xquery.set.internal.launching.variables.CoreSdkLocationResolver;
import org.eclipse.wst.xquery.set.internal.launching.variables.CoreSdkVersionResolver;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.BackingStoreException;

/**
 * The activator class controls the plug-in life cycle
 */
public class SETLaunchingPlugin extends Plugin implements IInterpreterInstallChangedListener {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.wst.xquery.set.launching";

    // The shared instance
    private static SETLaunchingPlugin plugin;

    public static final boolean DEBUG_SERVER = Boolean.valueOf(Platform.getDebugOption(PLUGIN_ID + "/debug/server"))
            .booleanValue();
    public static final boolean DEBUG_VARIABLE_RESOLVING = Boolean.valueOf(
            Platform.getDebugOption(PLUGIN_ID + "/debug/variableResolving")).booleanValue();

    /**
     * The constructor
     */
    public SETLaunchingPlugin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        ScriptRuntime.addInterpreterInstallChangedListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        ScriptRuntime.removeInterpreterInstallChangedListener(this);

        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static SETLaunchingPlugin getDefault() {
        return plugin;
    }

    public static void log(IStatus status) {
        getDefault().getLog().log(status);
    }

    public void defaultInterpreterInstallChanged(IInterpreterInstall previous, IInterpreterInstall current) {
        // TODO Auto-generated method stub

    }

    private final String CORE_SDK_NAME_PREFIX = "Sausalito CoreSDK ";
    private boolean fCoreSDKUpdated = false;

    private boolean isDefaultInterpreter(IInterpreterInstall interpreter, String nature) {
        String xml = getInterpreterPreferenceXMLString();

        String[] lines = xml.split("\n");
        String id = null;
        for (String line : lines) {
            // if we find the default CoreSDK interpreter reference
            if (line.contains("<defaultInterpreter") && line.contains("nature=\"" + nature + "\"")) {
                // get the interpreter ID
                int searchIndex = line.indexOf("id=\"") + 4;
                if (searchIndex > 0) {
                    int endIndex = line.indexOf("\"", searchIndex);
                    if (endIndex > 0) {
                        String compositeID = line.substring(searchIndex, endIndex);
                        String[] parts = compositeID.split(",");
                        if (parts.length == 3) {
                            id = parts[2];
                            break;
                        }
                    }
                }
            }
        }

        if (interpreter.getId().equals(id)) {
            return true;
        }
        return false;
    }

    private String getInterpreterPreferenceXMLString() {
        IScopeContext scope = new InstanceScope();
        IEclipsePreferences pref = scope.getNode(DLTKLaunchingPlugin.PLUGIN_ID);
        return pref.get(ScriptRuntime.PREF_INTERPRETER_XML, "");
    }

    private void saveInterpreterPreferenceXMLString(String xml) {
        IScopeContext scope = new InstanceScope();
        IEclipsePreferences pref = scope.getNode(DLTKLaunchingPlugin.PLUGIN_ID);
        pref.put(ScriptRuntime.PREF_INTERPRETER_XML, xml);
        try {
            pref.flush();
        } catch (BackingStoreException e) {
            IStatus status = new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID, IStatus.ERROR,
                    "Problem saving interpreter XML preferences", e);
            SETLaunchingPlugin.log(status);
        }
    }

    public void interpreterAdded(IInterpreterInstall interpreter) {
        // only do this logic if a CoreSDK is added and this happens to be the
        // default one perform this only once, the first time the default CoreSDK
        // is added
        if (interpreter instanceof CoreSDKInstall && !fCoreSDKUpdated
                && isDefaultInterpreter(interpreter, SETNature.NATURE_ID)
                && interpreter.getName().startsWith(CORE_SDK_NAME_PREFIX)) {
            System.out.println("configuring sausalito...");
            IFileHandle handle = interpreter.getInstallLocation();

            Path path = new Path(handle.toOSString());
            String pathStr = path.toPortableString();
            String name = interpreter.getName();

            String xml = getInterpreterPreferenceXMLString();

            // first try to find an installed CoreSDK and recover
            // by replacing the old values with new resolved ones
            String newValue = CoreSdkLocationResolver.resolve();
            String version = CoreSdkVersionResolver.resolve();

            // no installed CoreSDK was found, so we delete the false entry
            if (newValue == null || newValue.equals("")) {
                String envId = LocalEnvironment.ENVIRONMENT_ID;
                xml = xml.replace("<interpreter environmentId=\"" + envId
                        + "\" id=\"defaultSausalitoCoreSDK\" name=\"Sausalito CoreSDK " + version + "\" path=\""
                        + pathStr + "\"/>", "");

                log(new Status(IStatus.ERROR, PLUGIN_ID, "Could not find a valid Sausalito CoreSDK installation."));
            } else {
                // in this case we can recover and adjust the wrong CoreSDK entry 

                String newName = name;
                if (version != null && name.startsWith(CORE_SDK_NAME_PREFIX)) {
                    int replaceIndex = name.indexOf(CORE_SDK_NAME_PREFIX) + CORE_SDK_NAME_PREFIX.length();
                    newName = name.replace(name.substring(replaceIndex), version);
                }

                StringBuilder newXml = new StringBuilder();
                String[] lines = xml.split("\n");
                String id = interpreter.getId();
                for (String line : lines) {
                    String modifiedLine = line;

                    if (id != null && line.contains("<interpreter") && line.contains(id)) {
                        // replace the name with the new one having the updated version
                        modifiedLine = modifiedLine.replace("name=\"" + name + "\"", "name=\"" + newName + "\"");

                        // replace the path with the new found (shipped) one
                        IPath newPath = new Path(newValue);
                        String executable = CoreSdkExecNameResolver
                                .resolve(CoreSdkExecNameResolver.SAUSALITO_SCRIPT_VARIABLE_NAME);
                        if (executable != null && executable != "") {
                            newPath = newPath.append(ISETLaunchingConstants.SAUSALITO_EXECUTABLE_DIRECTORY).append(
                                    executable);
                            modifiedLine = modifiedLine.replace("path=\"" + pathStr + "\"",
                                    "path=\"" + newPath.toPortableString() + "\"");
                        }
                    }
                    newXml.append(modifiedLine + "\n");
                }
                saveInterpreterPreferenceXMLString(newXml.toString());
            }
        }
    }

    public void interpreterChanged(PropertyChangeEvent event) {
    }

    public void interpreterRemoved(IInterpreterInstall interpreter) {
    }

}
