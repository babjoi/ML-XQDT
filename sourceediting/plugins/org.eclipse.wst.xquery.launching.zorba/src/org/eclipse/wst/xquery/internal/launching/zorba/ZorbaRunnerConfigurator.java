package org.eclipse.wst.xquery.internal.launching.zorba;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;

public class ZorbaRunnerConfigurator {

    private static List<String> DEFAULT_ARGS = Arrays.asList(new String[] { "-f", "-q" });

    private final static String ZORBA_MODULE_PATH = "ZORBA_MODULE_PATH";
    private final static String LD_LIBRARY_PATH = "LD_LIBRARY_PATH";

    private IInterpreterInstall fInstall;

    public ZorbaRunnerConfigurator(IInterpreterInstall install) {
        fInstall = install;
    }

    public String[] renderCommandLine(InterpreterConfig config, String[] cmdLine) {
        List<String> newCmdLine = new ArrayList<String>(Arrays.asList(cmdLine));

        int index = newCmdLine.size() - config.getScriptArgs().size() - 1;
        newCmdLine.addAll(index, DEFAULT_ARGS);

        return newCmdLine.toArray(new String[newCmdLine.size()]);
    }

    public String[] addInternalVarsToRunnerEnv(InterpreterConfig config, String[] env) {
        IPath zorbaHome = fInstall.getInstallLocation().getPath().removeLastSegments(2);
        IPath modulesPath = zorbaHome.append("modules");
        IPath libPath = zorbaHome.append("lib");
        boolean isSausalitoZorba = fInstall.getInstallLocation().getPath().lastSegment().contains("sausastore");

        boolean foundZMP = false;
        boolean foundLLP = false;

        String ldLibPath = "";
        if (Platform.getOS().equals(Platform.OS_MACOSX)) {
            ldLibPath = "DY" + LD_LIBRARY_PATH;
        } else if (Platform.getOS().equals(Platform.OS_LINUX)) {
            ldLibPath = LD_LIBRARY_PATH;
        }

        List<String> envList = new ArrayList<String>(env.length);
        for (String var : env) {
            if (isSausalitoZorba && var.startsWith(ZORBA_MODULE_PATH + "=")) {
                foundZMP = true;
                String oldValue = var.substring(ZORBA_MODULE_PATH.length() + 1);
                var = ZORBA_MODULE_PATH + "=" + modulesPath.toOSString();
                if (!oldValue.equals("")) {
                    var += File.pathSeparator + oldValue;
                }
            } else if (var.startsWith(ldLibPath + "=")) {
                foundLLP = true;
                String oldValue = var.substring(ldLibPath.length() + 1);
                var = ldLibPath + "=" + libPath.toOSString();
                if (!oldValue.equals("")) {
                    var += File.pathSeparator + oldValue;
                }
            }
            envList.add(var);
        }

        if (isSausalitoZorba && !foundZMP) {
            String var = ZORBA_MODULE_PATH + "=" + modulesPath.toOSString();
            envList.add(var);
        }
        if (!Platform.getOS().equals(Platform.OS_WIN32) && !foundLLP) {
            String var = ldLibPath + "=" + libPath.toOSString();
            envList.add(var);
        }

        env = envList.toArray(new String[envList.size()]);

        return null;
    }

}
