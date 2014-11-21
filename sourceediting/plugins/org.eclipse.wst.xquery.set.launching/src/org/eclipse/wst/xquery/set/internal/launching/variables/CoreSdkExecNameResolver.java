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
package org.eclipse.wst.xquery.set.internal.launching.variables;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.wst.xquery.set.launching.ISETLaunchingConstants;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSdkExecNameResolver implements IDynamicVariableResolver {

    public static final String SAUSALITO_SCRIPT_VARIABLE_NAME = "sausalito_script_exec";
    public static final String SAUSALITO_ZORBA_VARIABLE_NAME = "sausalito_zorba_exec";

    public String resolveValue(IDynamicVariable variable, String argument) throws CoreException {
        String result = null;
        String varName = variable.getName();

        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, "Starting resolving of variable ${" + varName
                    + "} with default start value: null", null);
        }

        if (varName.equals(SAUSALITO_SCRIPT_VARIABLE_NAME)) {
            if (Platform.getOS().equals(Platform.OS_WIN32)) {
                result = ISETLaunchingConstants.SAUSALITO_SCRIPT_BATCH;
            } else {
                result = ISETLaunchingConstants.SAUSALITO_SCRIPT_BASH;
            }
        } else if (varName.equals(SAUSALITO_ZORBA_VARIABLE_NAME)) {
            if (Platform.getOS().equals(Platform.OS_WIN32)) {
                result = ISETLaunchingConstants.SAUSALITO_ZORBA_EXECUTABLE_WIN;
            } else {
                result = ISETLaunchingConstants.SAUSALITO_ZORBA_EXECUTABLE_NON_WIN;
            }
        }

        if (SETLaunchingPlugin.DEBUG_VARIABLE_RESOLVING) {
            CoreSdkLocationResolver.log(IStatus.INFO, "${" + varName + "} was resolved to: " + result, null);
        }

        return result;
    }

    public static String resolve(String varName) {
        String executable = null;

        try {
            executable = VariablesPlugin.getDefault().getStringVariableManager()
                    .performStringSubstitution("${" + varName + "}", false);
        } catch (CoreException ce) {
            SETLaunchingPlugin.log(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                    "An error occured while trying to determine the Sausalito CoreSDK script executable.", ce));
            return null;
        }

        return executable;
    }
}
