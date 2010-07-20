package org.eclipse.wst.xquery.set.internal.launching;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.wst.xquery.set.debug.core.ISETLaunchConfigurationConstants;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSdkRunner extends AbstractInterpreterRunner {

    public CoreSdkRunner(IInterpreterInstall install) {
        super(install);
    }

    @Override
    protected String[] renderCommandLine(InterpreterConfig config) {
        List<String> args = new ArrayList<String>(8);

        IInterpreterInstall install = getInstall();
        String path = install.getInstallLocation().toOSString();

        args.add(path);
        args.add("test");
        args.add("project");
        args.add("-d");
        args.add(config.getWorkingDirectoryPath().toOSString());
        args.addAll(config.getScriptArgs());

        return args.toArray(new String[args.size()]);
    }

    @Override
    protected void alterConfig(ILaunch launch, InterpreterConfig config) {
        ILaunchConfiguration configuration = launch.getLaunchConfiguration();

        String host = "127.0.0.1";
        int port = 8080;
        boolean clear = false;
        boolean indent = false;

        try {
            host = configuration.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_HOST, "127.0.0.1");
            port = configuration.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_PORT, 8080);
            clear = configuration.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_CLEAR_COLLECTIONS, false);
            indent = configuration.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_INDENT, false);
        } catch (CoreException ce) {
            // bad if this happens
            SETLaunchingPlugin.log(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                    "Could not retrieve the launch configuration properties. The default options will be used:\n"
                            + "Socket:                127.0.0.1:8080\n" + "Indent result:         false\n"
                            + "Clear collection data: false\n"));
        }

        config.clearScriptArgs();
        config.addScriptArg("-s");
        config.addScriptArg(host + ":" + port);
        if (indent) {
            config.addScriptArg("-i");
        }
        if (clear) {
            config.addScriptArg("-c");
        }
    }

}
