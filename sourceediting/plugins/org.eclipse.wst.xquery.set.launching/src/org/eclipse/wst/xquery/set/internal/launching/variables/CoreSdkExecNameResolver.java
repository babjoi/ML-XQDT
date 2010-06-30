package org.eclipse.wst.xquery.set.internal.launching.variables;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;
import org.eclipse.wst.xquery.set.launching.ISETLaunchingConstants;

public class CoreSdkExecNameResolver implements IDynamicVariableResolver {

    public static final String VARIABLE_SAUSALITO_SCRIPT = "sausalito_script_exec";
    public static final String VARIABLE_SAUSALITO_ZORBA = "sausalito_zorba_exec";

    public String resolveValue(IDynamicVariable variable, String argument) throws CoreException {
        if (variable.getName().equals(VARIABLE_SAUSALITO_SCRIPT)) {
            if (Platform.getOS().equals(Platform.OS_WIN32)) {
                return ISETLaunchingConstants.SAUSALITO_SCRIPT_BATCH;
            } else {
                return ISETLaunchingConstants.SAUSALITO_SCRIPT_BASH;
            }
        } else if (variable.getName().equals(VARIABLE_SAUSALITO_ZORBA)) {
            if (Platform.getOS().equals(Platform.OS_WIN32)) {
                return ISETLaunchingConstants.SAUSALITO_ZORBA_EXECUTABLE_WIN;
            } else {
                return ISETLaunchingConstants.SAUSALITO_ZORBA_EXECUTABLE_NON_WIN;
            }
        }

        return null;
    }
}
