package org.eclipse.wst.xquery.set.debug.debugger;

import java.io.File;
import java.net.InetAddress;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpConnectionConfig;
import org.eclipse.wst.xquery.debug.dbgp.client.IDbgpTranslator;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.ZorbaDbgpTranslator;
import org.eclipse.wst.xquery.launching.TranslatableDebuggingEngineRunner;
import org.eclipse.wst.xquery.set.debug.core.SETDebugCorePlugin;

public class CoreSdkDebuggerRunner extends TranslatableDebuggingEngineRunner {

    private static final String DEBUG_SERVER_KEY = "-ds";
    private static final String PORTS_KEY = "-p";

    public CoreSdkDebuggerRunner(IInterpreterInstall install) {
        super(install);
    }

    protected String getDebugPreferenceQualifier() {
        return SETDebugCorePlugin.PLUGIN_ID;
    }

    protected String getDebuggingEngineId() {
        return SETDebuggerConstants.ENGINE_ID;
    }

    protected String getDebuggingEnginePreferenceQualifier() {
        return SETDebuggerPlugin.PLUGIN_ID;
    }

    protected String getLogFileNamePreferenceKey() {
        return SETDebuggerConstants.LOG_FILE_NAME;
    }

    protected IDbgpTranslator getDbgpTranslator(InterpreterConfig config, IScriptProject project) {
        DbgpConnectionConfig dbgpConfig = DbgpConnectionConfig.load(config);
        File file = new File(config.getScriptFilePath().toOSString());
        String ports = (String)config.getProperty(SETDebuggerConstants.DEBUGGING_ENGINE_SERVER_PORTS);

        try {
            return new ZorbaDbgpTranslator(project, InetAddress.getByName(dbgpConfig.getHost()), dbgpConfig.getPort(),
                    dbgpConfig.getSessionId(), file.toURI(), ports);
        } catch (Exception e) {
            SETDebuggerPlugin.getDefault().getLog().log(
                    new Status(IStatus.ERROR, SETDebuggerPlugin.PLUGIN_ID, e.getMessage(), e));
            return null;
        }
    }

    protected boolean needsDbgpTranslator(PreferencesLookupDelegate delegate) {
        return delegate.getBoolean(getDebuggingEnginePreferenceQualifier(),
                SETDebuggerConstants.DEBUGGING_ENGINE_NEEDS_DBGP_TRANSLATOR);
    }

    protected InterpreterConfig addEngineConfig(InterpreterConfig config, PreferencesLookupDelegate delegate,
            ILaunch launch) throws CoreException {
        InterpreterConfig newConfig = (InterpreterConfig)config.clone();

        newConfig.addInterpreterArg("test");
        newConfig.addInterpreterArg("project");

        String path = config.getWorkingDirectoryPath().toOSString();
        newConfig.addInterpreterArg("-d");
        newConfig.addInterpreterArg(path);

        newConfig.addInterpreterArg(DEBUG_SERVER_KEY);

        String ports = delegate.getString(getDebuggingEnginePreferenceQualifier(),
                SETDebuggerConstants.DEBUGGING_ENGINE_SERVER_PORTS);
        if (!ports.equals("")) {
            newConfig.addInterpreterArg(PORTS_KEY);
            newConfig.addInterpreterArg(ports);
            config.setProperty(SETDebuggerConstants.DEBUGGING_ENGINE_SERVER_PORTS, ports);
        }

        return newConfig;
    }

}
