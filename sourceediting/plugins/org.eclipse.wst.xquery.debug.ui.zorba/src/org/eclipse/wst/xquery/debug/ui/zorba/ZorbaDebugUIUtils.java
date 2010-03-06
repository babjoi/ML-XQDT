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
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.InterpreterStandin;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.launching.ScriptRuntime.DefaultInterpreterEntry;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.internal.launching.zorba.ZorbaInstallType;
import org.osgi.framework.Bundle;
import org.osgi.service.prefs.BackingStoreException;

public class ZorbaDebugUIUtils {

    public static void searchAndAddSausalitoZorba() {
        try {
            String os = Platform.getOS();
            if (os.equals(Platform.OS_WIN32) || os.equals(Platform.OS_MACOSX)) {
//              // return if the default CoreSDK was already installed
//              if (getPluginPreferences().getBoolean(ISETPreferenceConstants.SET_CORESDK_INITIALIZED)) {
//                  //return;
//              }

                // this is only a piece of code to make sure the CoreSDK installation
                // type is loaded form the plugin configuration in the DLTK registry
                DefaultInterpreterEntry entry = new DefaultInterpreterEntry(XQDTNature.NATURE_ID, EnvironmentManager
                        .getLocalEnvironment().getId());
                ScriptRuntime.getDefaultInterpreterInstall(entry);

                String archPart = "";
                if (os.equals(Platform.OS_MACOSX)) {
                    archPart = "." + Platform.getOSArch();
                }
                String osPart = "." + os;
                String fragment = "org.eclipse.wst.xquery.set.launching" + osPart + archPart;

                ZorbaDebugUIPlugin.getDefault().getLog().log(
                        new Status(IStatus.INFO, ZorbaDebugUIPlugin.PLUGIN_ID, "Searching CoreSDK fragment: "
                                + fragment));

                Bundle[] bundles = Platform.getBundles(fragment, null);
                if (bundles == null || bundles.length == 0) {
                    return;
                }

                Bundle bundle = bundles[0];
                String sausalitoScript = "/coresdk/bin/zorba";
                if (os.equals(Platform.OS_WIN32)) {
                    sausalitoScript += ".exe";
                }
                URL coreSdkURL = bundle.getEntry(sausalitoScript);
                String id = String.valueOf(System.currentTimeMillis());
                IFileHandle installLocation = new LazyFileHandle(EnvironmentManager.getLocalEnvironment().getId(),
                        new Path(FileLocator.toFileURL(coreSdkURL).getFile()));

                IInterpreterInstallType interpreterType = new ZorbaInstallType();
                final InterpreterStandin zorbaInstall = new InterpreterStandin(interpreterType, id);
                zorbaInstall.setInstallLocation(installLocation);
                zorbaInstall.setName("Zorba 1.0.1");
                zorbaInstall.setInterpreterArgs(null);
                LibraryLocation lib = new LibraryLocation(installLocation.getFullPath().removeLastSegments(2).append(
                        "modules"));
                zorbaInstall.setLibraryLocations(new LibraryLocation[] { lib });

                // Save the interpreter configuration and set the preference that
                // this was done. This will not be done next time.
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
                    error(IStatus.ERROR, "Could update the XQuery interpreter settings", null);
                } else {
                    IEclipsePreferences node = new InstanceScope().getNode(ZorbaDebugUIPlugin.PLUGIN_ID);
//                    node.putBoolean(ISETPreferenceConstants.SET_CORESDK_INITIALIZED, true);
                    node.flush();
                }
            }

            if (Platform.getOS().equals(Platform.OS_MACOSX) || Platform.getOS().equals(Platform.OS_LINUX)) {
                // on Unix-based platforms search the default installation location
                // /opt/sausalito
//              IFileHandle installLocation = new LazyFileHandle(EnvironmentManager.getLocalEnvironment().getId(), new Path(FileLocator.toFileURL("").getFile()));              
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
