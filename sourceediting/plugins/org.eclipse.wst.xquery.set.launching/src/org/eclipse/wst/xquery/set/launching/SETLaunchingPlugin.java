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
import org.eclipse.wst.xquery.internal.launching.zorba.ZorbaInstall;
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
     * Flag to mark the initialisation/correction of the default Sausalito CoreSDK. This operation
     * is only performed at start-up.
     */
    private boolean fDefaultCoreSDKUpdated = false;

    /**
     * Flag to mark the initialisation/correction of the default Zorba. This operation is only
     * performed at start-up.
     */
    private boolean fDefaultZorbaUpdated = false;

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

    //
    // IInterpreterInstallChangedListener implementation
    //

    public void interpreterAdded(IInterpreterInstall interpreter) {
        if (!fDefaultCoreSDKUpdated) {
            checkCoreSdk(interpreter);
        }
        if (!fDefaultZorbaUpdated) {
            checkZorba(interpreter);
        }
    }

    public void defaultInterpreterInstallChanged(IInterpreterInstall previous, IInterpreterInstall current) {
    }

    public void interpreterChanged(PropertyChangeEvent event) {
    }

    public void interpreterRemoved(IInterpreterInstall interpreter) {
    }

    //
    // Private methods
    //

    private void checkCoreSdk(IInterpreterInstall interpreter) {
        // only do this logic at startup to correct a default modified CoreSDK
        if (interpreter instanceof CoreSDKInstall
                && interpreter.getId().equals(ISETLaunchingConstants.DEFAULT_CORE_SDK_ID)) {
            // stop future calls during the same session  
            fDefaultCoreSDKUpdated = true;

            String xml = getInterpreterPreferenceXMLString();
            if (xml.equals("")) {
                getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "Sausalito CoreSDK first time configuration."));
                return;
            }

            getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "Checking the default Sausalito CoreSDK."));

            IFileHandle handle = interpreter.getInstallLocation();
            Path installPath = new Path(handle.toOSString());
            String name = interpreter.getName();

            // try to find the CoreSDK and recover if necessary
            // by replacing the old values with new resolved ones
            String newLocation = CoreSdkLocationResolver.resolve();
            String newVersion = CoreSdkVersionResolver.resolve();
            String newExec = CoreSdkExecNameResolver.resolve(CoreSdkExecNameResolver.SAUSALITO_SCRIPT_VARIABLE_NAME);

            // no installed CoreSDK was found, so we delete the false entry
            if (newLocation == null || newLocation.equals("")) {
                String envId = LocalEnvironment.ENVIRONMENT_ID;
                xml = xml.replace(
                        "<interpreter environmentId=\"" + envId + "\" id=\""
                                + ISETLaunchingConstants.DEFAULT_CORE_SDK_ID + "\" name=\""
                                + ISETLaunchingConstants.DEFAULT_CORE_SDK_NAME_PREFIX + newVersion + "\" path=\""
                                + installPath.toPortableString() + "\"/>", "");
                log(new Status(IStatus.ERROR, PLUGIN_ID, "Could not find a valid Sausalito CoreSDK installation."));
                saveInterpreterPreferenceXMLString(xml.toString());
                return;
            }

            String newName = ISETLaunchingConstants.DEFAULT_CORE_SDK_NAME_PREFIX + newVersion;
            IPath newInstallPath = new Path(newLocation).append(ISETLaunchingConstants.SAUSALITO_EXECUTABLE_DIRECTORY)
                    .append(newExec);

            // if the interpreter has the same values as the default one, abandon
            if (name.equals(newName) && installPath.equals(newInstallPath)) {
                getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "The default Sausalito CoreSDK is correct."));
                return;
            }

            getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "The default Sausalito CoreSDK must be updated."));

            // now we have to update the default CoreSDK entry 
            StringBuilder newXml = new StringBuilder();
            String[] lines = xml.split("\n");
            String id = interpreter.getId();

            for (String line : lines) {
                String modifiedLine = line;

                if (id != null && line.contains("<interpreter") && line.contains(id)) {
                    // replace the name with the new one having the updated version
                    modifiedLine = modifiedLine.replace("name=\"" + name + "\"", "name=\"" + newName + "\"");

                    // replace the old path with the new found (shipped) one
                    modifiedLine = modifiedLine.replace("path=\"" + installPath.toPortableString() + "\"", "path=\""
                            + newInstallPath.toPortableString() + "\"");
                }
                newXml.append(modifiedLine + "\n");
            }

            // now save to disk the new constructed interpreter preferences
            saveInterpreterPreferenceXMLString(newXml.toString());
        }
    }

    private void checkZorba(IInterpreterInstall interpreter) {
        // only do this logic at startup to correct a default modified CoreSDK
        if (interpreter instanceof ZorbaInstall && interpreter.getId().equals(ISETLaunchingConstants.DEFAULT_ZORBA_ID)) {
            // stop future calls during the same session  
            fDefaultZorbaUpdated = true;

            String xml = getInterpreterPreferenceXMLString();
            if (xml.equals("")) {
                getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "Zorba first time configuration."));
                return;
            }

            getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "Checking the default Zorba."));

            IFileHandle handle = interpreter.getInstallLocation();
            Path installPath = new Path(handle.toOSString());
            String name = interpreter.getName();

            // try to find the Zorba and recover if necessary
            // by replacing the old values with new resolved ones
            String newLocation = CoreSdkLocationResolver.resolve();
            String newExec = CoreSdkExecNameResolver.resolve(CoreSdkExecNameResolver.SAUSALITO_ZORBA_VARIABLE_NAME);

            // no installed CoreSDK was found, so we delete the false entry
            if (newLocation == null || newLocation.equals("")) {
                String envId = LocalEnvironment.ENVIRONMENT_ID;
                xml = xml.replace("<interpreter environmentId=\"" + envId + "\" id=\""
                        + ISETLaunchingConstants.DEFAULT_ZORBA_ID + "\" name=\""
                        + ISETLaunchingConstants.DEFAULT_ZORBA_NAME + "\" path=\"" + installPath.toPortableString()
                        + "\"/>", "");
                log(new Status(IStatus.ERROR, PLUGIN_ID, "Could not find a valid Zorba installation."));
                saveInterpreterPreferenceXMLString(xml.toString());
                return;
            }

            String newName = ISETLaunchingConstants.DEFAULT_ZORBA_NAME;
            IPath newInstallPath = new Path(newLocation).append(ISETLaunchingConstants.SAUSALITO_EXECUTABLE_DIRECTORY)
                    .append(newExec);

            // if the interpreter has the same values as the default one, abandon
            if (name.equals(newName) && installPath.equals(newInstallPath)) {
                getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "The default Zorba is correct."));
                return;
            }

            getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "The default Zorba must be updated."));

            // now we have to update the default Zorba entry 
            StringBuilder newXml = new StringBuilder();
            String[] lines = xml.split("\n");
            String id = interpreter.getId();

            for (String line : lines) {
                String modifiedLine = line;

                if (id != null && line.contains("<interpreter") && line.contains(id)) {
                    // replace the name with the new one having the updated version
                    modifiedLine = modifiedLine.replace("name=\"" + name + "\"", "name=\"" + newName + "\"");

                    // replace the old path with the new found (shipped) one
                    modifiedLine = modifiedLine.replace("path=\"" + installPath.toPortableString() + "\"", "path=\""
                            + newInstallPath.toPortableString() + "\"");
                }
                newXml.append(modifiedLine + "\n");
            }

            // now save to disk the new constructed interpreter preferences
            saveInterpreterPreferenceXMLString(newXml.toString());
        }
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

}
