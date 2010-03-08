package org.eclipse.wst.xquery.set.debug.ui;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.debug.ui.interpreters.InterpretersUpdater;
import org.eclipse.dltk.internal.launching.LazyFileHandle;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterStandin;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.wst.xquery.core.IXQDTCorePreferences;
import org.eclipse.wst.xquery.debug.ui.XQDTDebugUIPlugin;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.launching.CoreSDKInstallType;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;
import org.osgi.framework.Bundle;
import org.osgi.service.prefs.BackingStoreException;

public class SETDebugUIUtils {

    public static void searchAndAddDefaultCoreSdk() {
        try {
            String os = Platform.getOS();
//              // return if the default CoreSDK was already installed
//              if (getPluginPreferences().getBoolean(ISETPreferenceConstants.SET_CORESDK_INITIALIZED)) {
//                  //return;
//              }

            // this is only a piece of code to make sure the CoreSDK installation
            // type is loaded form the plugin configuration in the DLTK registry
//                DefaultInterpreterEntry entry = new DefaultInterpreterEntry(SETNature.NATURE_ID, EnvironmentManager
//                        .getLocalEnvironment().getId());
//                ScriptRuntime.getDefaultInterpreterInstall(entry);

            String absoluteCoreSDKScript = null;
            if (os.equals(Platform.OS_WIN32) || os.equals(Platform.OS_MACOSX)) {
                String osPart = "." + os;
                String archPart = "";

                // in case of MacOSX we make have two versions of the CoreSDK
                if (os.equals(Platform.OS_MACOSX)) {
                    archPart = "." + Platform.getOSArch();
                }
                String fragment = SETLaunchingPlugin.PLUGIN_ID + osPart + archPart;

                Bundle[] bundles = Platform.getBundles(fragment, null);
                if (bundles == null || bundles.length == 0) {
                    if (XQDTDebugUIPlugin.TRACE_AUTOMATIC_PROCESSOR_DETECTION) {
                        error(IStatus.INFO, "Could not find plug-in fragment: " + fragment
                                + ". No default Sausalito CoreSDK will be configured.", null);
                    }
                    return;
                }
                if (XQDTDebugUIPlugin.TRACE_AUTOMATIC_PROCESSOR_DETECTION) {
                    error(IStatus.INFO, "Found Sausalito CoreSDK plug-in fragment: " + fragment, null);
                }

                Bundle bundle = bundles[0];
                String sausalitoScript = "/coresdk/bin/sausalito";
                if (os.equals(Platform.OS_WIN32)) {
                    sausalitoScript += ".bat";
                }
                URL sausalitoURL = bundle.getEntry(sausalitoScript);
                if (sausalitoURL == null) {
                    if (XQDTDebugUIPlugin.TRACE_AUTOMATIC_PROCESSOR_DETECTION) {
                        error(IStatus.INFO, "Cound not find script \"" + sausalitoScript + "\" in plug-in fragment: "
                                + fragment, null);
                    }
                }
                absoluteCoreSDKScript = FileLocator.toFileURL(sausalitoURL).getFile();
            } else if (os.equals(Platform.OS_LINUX)) {
                absoluteCoreSDKScript = "/opt/saualito/bin/sausalito";
            } else {
                if (XQDTDebugUIPlugin.TRACE_AUTOMATIC_PROCESSOR_DETECTION) {
                    error(IStatus.INFO, "Automatic Sausalito CoreSDK detection is not enabled on \"" + os
                            + "\" pltforms.", null);
                }
                return;
            }

            if (XQDTDebugUIPlugin.TRACE_AUTOMATIC_PROCESSOR_DETECTION) {
                error(IStatus.INFO, "Confiuguring Sausalito CoreSDK script from: " + absoluteCoreSDKScript, null);
            }

            IFileHandle installLocation = new LazyFileHandle(EnvironmentManager.getLocalEnvironment().getId(),
                    new Path(absoluteCoreSDKScript));

            String id = String.valueOf(System.currentTimeMillis());
            final InterpreterStandin coreSdk = new InterpreterStandin(new CoreSDKInstallType(), id);
            coreSdk.setInstallLocation(installLocation);
            coreSdk.setName("Sausalito CoreSDK 1.0");
            coreSdk.setInterpreterArgs(null);
            LibraryLocation lib = new LibraryLocation(installLocation.getFullPath().removeLastSegments(2).append(
                    "modules"));
            coreSdk.setLibraryLocations(new LibraryLocation[] { lib });

            final boolean[] canceled = new boolean[] { false };
//              BusyIndicator.showWhile(null, new Runnable() {
//                  public void run() {
            InterpretersUpdater updater = new InterpretersUpdater();
            if (!updater.updateInterpreterSettings(SETNature.NATURE_ID, new IInterpreterInstall[] { coreSdk },
                    new IInterpreterInstall[] { coreSdk })) {
                canceled[0] = true;
            }
//                  }
//              });

            if (canceled[0]) {
                error(IStatus.ERROR, "Automatic Sausalito CoreSDK detection canceled", null);
            } else {
                IEclipsePreferences node = new InstanceScope().getNode(SETDebugUIPlugin.PLUGIN_ID);
                node.putBoolean(IXQDTCorePreferences.DEFAULT_INTERPRETER_FOUND, true);
                node.flush();
            }
        } catch (BackingStoreException bse) {
            error(IStatus.ERROR, "Could not enable the default Sausalito CoreSDK", null);
        } catch (IOException ioe) {
            error(IStatus.ERROR, "Could not find the default CoreSDK Sausalito script", null);
        }
    }

    public static void error(int severity, String message, Throwable t) {
        SETDebugUIPlugin.log(new Status(severity, SETDebugUIPlugin.PLUGIN_ID, message, t));
    }

}
