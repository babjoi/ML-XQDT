package org.eclipse.wst.xquery.debug.ui.zorba;

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
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterStandin;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.wst.xquery.core.IXQDTCorePreferences;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.internal.launching.zorba.ZorbaInstallType;
import org.eclipse.wst.xquery.launching.XQDTLaunchingPlugin;
import org.osgi.framework.Bundle;
import org.osgi.service.prefs.BackingStoreException;

public class ZorbaDebugUIUtils {

    public static void searchAndAddSausalitoZorba() {
        try {
            String os = Platform.getOS();
//              // return if the default CoreSDK was already installed
//              if (getPluginPreferences().getBoolean(ISETPreferenceConstants.SET_CORESDK_INITIALIZED)) {
//                  //return;
//              }

            // this is only a piece of code to make sure the CoreSDK installation
            // type is loaded form the plugin configuration in the DLTK registry
//            DefaultInterpreterEntry entry = new DefaultInterpreterEntry(XQDTNature.NATURE_ID, EnvironmentManager
//                    .getLocalEnvironment().getId());
//            ScriptRuntime.getDefaultInterpreterInstall(entry);

            String absoluteZorbaExec = null;
            if (os.equals(Platform.OS_WIN32) || os.equals(Platform.OS_MACOSX)) {
                String osPart = "." + os;
                String archPart = "";

                // in case of MacOSX we make have two versions of the Zorba
                if (os.equals(Platform.OS_MACOSX)) {
                    archPart = "." + Platform.getOSArch();
                }
                String fragment = "org.eclipse.wst.xquery.set.launching" + osPart + archPart;

                Bundle[] bundles = Platform.getBundles(fragment, null);
                if (bundles == null || bundles.length == 0) {
                    if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION) {
                        error(IStatus.INFO, "Could not find plug-in fragment: " + fragment
                                + ". No default Zorba XQuery processor will be configured.", null);
                    }
                    return;
                }
                if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION) {
                    error(IStatus.INFO, "Found plug-in fragment: " + fragment, null);
                }

                Bundle bundle = bundles[0];
                String relativeZorbaExec = "/coresdk/bin/zorba";
                if (os.equals(Platform.OS_WIN32)) {
                    relativeZorbaExec += ".exe";
                }
                URL zorbaURL = bundle.getEntry(relativeZorbaExec);
                if (zorbaURL == null) {
                    if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION) {
                        error(IStatus.INFO, "Cound not find executable \"" + relativeZorbaExec
                                + "\" in plug-in fragment: " + fragment, null);
                    }
                }
                absoluteZorbaExec = FileLocator.toFileURL(zorbaURL).getFile();
            } else if (os.equals(Platform.OS_LINUX)) {
                absoluteZorbaExec = "/opt/saualito/bin/zorba";
            } else {
                if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION) {
                    error(IStatus.INFO, "Automatic Zorba XQuery processor detection is not enabled on \"" + os
                            + "\" pltforms.", null);
                }
                return;
            }

            if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION) {
                error(IStatus.INFO, "Confiuguring Zorba XQuery processor from: " + absoluteZorbaExec, null);
            }

            IFileHandle installLocation = new LazyFileHandle(EnvironmentManager.getLocalEnvironment().getId(),
                    new Path(absoluteZorbaExec));

            // using as interpreter ID the current time in milliseconds
            String id = String.valueOf(System.currentTimeMillis());
            final InterpreterStandin zorbaInstall = new InterpreterStandin(new ZorbaInstallType(), id);
            zorbaInstall.setInstallLocation(installLocation);
            zorbaInstall.setName("Zorba 1.0.0");
            zorbaInstall.setInterpreterArgs(null);
            LibraryLocation moduleLoc = new LibraryLocation(installLocation.getFullPath().removeLastSegments(2).append(
                    "modules"));
            zorbaInstall.setLibraryLocations(new LibraryLocation[] { moduleLoc });

            if (os.equals(Platform.OS_MACOSX) || os.equals(Platform.OS_LINUX)) {
                String libraryPath = "LD_LIBRARY_PATH";
                if (os.equals(Platform.OS_MACOSX)) {
                    libraryPath = "DY" + libraryPath;
                }
                String libDir = installLocation.getParent().getParent().getFullPath().append("lib").toOSString();
                EnvironmentVariable libPathVar = new EnvironmentVariable(libraryPath, libDir);

                if (XQDTLaunchingPlugin.DEBUG_AUTOMATIC_PROCESSOR_DETECTION) {
                    error(IStatus.INFO, "Setting the " + libPathVar.getName() + " variable to: "
                            + libPathVar.getValue(), null);
                }

                zorbaInstall.setEnvironmentVariables(new EnvironmentVariable[] { libPathVar });
            }

            final boolean[] canceled = new boolean[] { false };
//              BusyIndicator.showWhile(null, new Runnable() {
//                  public void run() {
            InterpretersUpdater updater = new InterpretersUpdater();
            if (!updater.updateInterpreterSettings(XQDTNature.NATURE_ID, new IInterpreterInstall[] { zorbaInstall },
                    new IInterpreterInstall[] { zorbaInstall })) {
                canceled[0] = true;
            }
//                  }
//              });

            if (canceled[0]) {
                error(IStatus.ERROR, "Automatic Zorba XQuery processor detection canceled", null);
            } else {
                IEclipsePreferences node = new InstanceScope().getNode(ZorbaDebugUIPlugin.PLUGIN_ID);
                node.putBoolean(IXQDTCorePreferences.DEFAULT_INTERPRETER_FOUND, true);
                node.flush();
            }
        } catch (BackingStoreException bse) {
            error(IStatus.ERROR, "Could not enable the default Zorba XQuery interpreter", null);
        } catch (IOException ioe) {
            error(IStatus.ERROR, "Could not find the Zorba executable", null);
        }
    }

    public static void error(int severity, String message, Throwable t) {
        ZorbaDebugUIPlugin.log(new Status(severity, ZorbaDebugUIPlugin.PLUGIN_ID, message, t));
    }

}
