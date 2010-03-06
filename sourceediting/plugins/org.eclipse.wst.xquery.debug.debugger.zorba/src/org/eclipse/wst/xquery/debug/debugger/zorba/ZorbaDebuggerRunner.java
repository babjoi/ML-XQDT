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
package org.eclipse.wst.xquery.debug.debugger.zorba;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpConnectionConfig;
import org.eclipse.wst.xquery.debug.core.XQDTDebugCorePlugin;
import org.eclipse.wst.xquery.debug.dbgp.client.IDbgpTranslator;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.ZorbaDbgpTranslator;
import org.eclipse.wst.xquery.launching.TranslatableDebuggingEngineRunner;

public class ZorbaDebuggerRunner extends TranslatableDebuggingEngineRunner {

    private static List<String> DEFAULT_ARGS = Arrays.asList(new String[] { "-f", "-q" });

    private static final String NO_LOGO_KEY = "--no-logo";
    private static final String DEBUG_SERVER_KEY = "--debug-server";
    private static final String PORTS_KEY = "-p";

    public ZorbaDebuggerRunner(IInterpreterInstall install) {
        super(install);
    }

    protected String getDebugPreferenceQualifier() {
        return XQDTDebugCorePlugin.PLUGIN_ID;
    }

    protected String getDebuggingEngineId() {
        return ZorbaDebuggerConstants.ENGINE_ID;
    }

    protected String getDebuggingEnginePreferenceQualifier() {
        return ZorbaDebuggerPlugin.PLUGIN_ID;
    }

    protected String getLogFileNamePreferenceKey() {
        return ZorbaDebuggerConstants.LOG_FILE_NAME;
    }

    @Override
    protected String[] renderCommandLine(InterpreterConfig config) {
        String[] cmdLine = super.renderCommandLine(config);
        List<String> newCmdLine = new ArrayList<String>(Arrays.asList(cmdLine));

        int index = newCmdLine.size() - config.getScriptArgs().size() - 1;
        newCmdLine.addAll(index, DEFAULT_ARGS);

        return newCmdLine.toArray(new String[newCmdLine.size()]);
    }

    protected InterpreterConfig addEngineConfig(InterpreterConfig config, PreferencesLookupDelegate delegate,
            ILaunch launch) throws CoreException {
        InterpreterConfig newConfig = (InterpreterConfig)config.clone();

        newConfig.addInterpreterArg(NO_LOGO_KEY);
        newConfig.addInterpreterArg(DEBUG_SERVER_KEY);
        newConfig.addInterpreterArg(PORTS_KEY);
        String ports = delegate.getString(getDebuggingEnginePreferenceQualifier(),
                ZorbaDebuggerConstants.DEBUGGING_ENGINE_SERVER_PORTS);
        newConfig.addInterpreterArg(ports);

        config.setProperty(ZorbaDebuggerConstants.DEBUGGING_ENGINE_SERVER_PORTS, ports);

        return newConfig;
    }

    protected boolean needsDbgpTranslator(PreferencesLookupDelegate delegate) {
        return delegate.getBoolean(getDebuggingEnginePreferenceQualifier(),
                ZorbaDebuggerConstants.DEBUGGING_ENGINE_NEEDS_DBGP_TRANSLATOR);
    }

    protected IDbgpTranslator getDbgpTranslator(InterpreterConfig config, IScriptProject project) {
        DbgpConnectionConfig dbgpConfig = DbgpConnectionConfig.load(config);
        File file = new File(config.getScriptFilePath().toOSString());
        String ports = (String)config.getProperty(ZorbaDebuggerConstants.DEBUGGING_ENGINE_SERVER_PORTS);

        try {
            return new ZorbaDbgpTranslator(project, InetAddress.getByName(dbgpConfig.getHost()), dbgpConfig.getPort(),
                    dbgpConfig.getSessionId(), file.toURI(), ports);
        } catch (Exception e) {
            ZorbaDebuggerPlugin.getDefault().getLog().log(
                    new Status(IStatus.ERROR, ZorbaDebuggerPlugin.PLUGIN_ID, e.getMessage(), e));
            return null;
        }
    }

}