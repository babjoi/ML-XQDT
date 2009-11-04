/*******************************************************************************
 * Copyright (c) 2009 Mark Logic Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Neth (Mark Logic) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.debug.engines.marklogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.ExternalDebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.wst.xquery.debug.core.XQDTDebugCorePlugin;

public class MarkLogicDebuggerRunner extends ExternalDebuggingEngineRunner {

    private static List<String> DEFAULT_ARGS = Arrays.asList(new String[] { "-f", "-q" });

    private static final String DEBUG_SERVER_KEY = "-s";
    private static final String PORTS_KEY = "-p";

    public MarkLogicDebuggerRunner(IInterpreterInstall install) {
        super(install);
    }

    protected String getDebuggingEnginePreferenceKey() {
        return MarkLogicDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY;
    }

    protected String getDebugPreferenceQualifier() {
        return XQDTDebugCorePlugin.PLUGIN_ID;
    }

    protected String getDebuggingEngineId() {
        return MarkLogicDebuggerConstants.ENGINE_ID;
    }

    protected String getDebuggingEnginePreferenceQualifier() {
        return MarkLogicDebuggerPlugin.PLUGIN_ID;
    }

    protected String getLogFileNamePreferenceKey() {
        return MarkLogicDebuggerConstants.LOG_FILE_NAME;
    }

    protected InterpreterConfig alterConfig(InterpreterConfig config, PreferencesLookupDelegate delegate)
            throws CoreException {
        // DbgpConnectionConfig dbgpConfig = DbgpConnectionConfig.load(config);

        InterpreterConfig newConfig = (InterpreterConfig)config.clone();
        // newConfig.setProperty(OVERRIDE_EXE, getDebuggingEnginePath(delegate).toOSString());

        newConfig.addInterpreterArg(DEBUG_SERVER_KEY);
        newConfig.addInterpreterArg(PORTS_KEY);
        newConfig.addInterpreterArg("11111:22222");

        // client params
        // newConfig.addInterpreterArg(dbgpConfig.getHost());
        // newConfig.addInterpreterArg(dbgpConfig.getPort() + "");
        // newConfig.addInterpreterArg(dbgpConfig.getSessionId());

        return newConfig;
    }

    protected String[] renderCommandLine(InterpreterConfig config) {
        String[] cmdLine = super.renderCommandLine(config);
        List<String> newCmdLine = new ArrayList<String>(Arrays.asList(cmdLine));

        int index = newCmdLine.size() - config.getScriptArgs().size() - 1;
        newCmdLine.addAll(index, DEFAULT_ARGS);

        return newCmdLine.toArray(new String[newCmdLine.size()]);
    }

}