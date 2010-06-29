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
package org.eclipse.wst.xquery.internal.launching.zorba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.wst.xquery.core.semantic.ISemanticValidator;
import org.eclipse.wst.xquery.launching.ISemanticValidatingInterpreterInstall;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstall;

public class ZorbaInstall extends XQDTInterpreterInstall implements ISemanticValidatingInterpreterInstall {

    public ZorbaInstall(IInterpreterInstallType type, String id) {
        super(type, id);
    }

    @Override
    public IInterpreterRunner getInterpreterRunner(String mode) {
        // the super class will handle the "debug" mode
        IInterpreterRunner runner = super.getInterpreterRunner(mode);
        if (runner != null) {
            return runner;
        }

        // if "run" mode, we create the normal runner
        if (mode.equals(ILaunchManager.RUN_MODE)) {
            return new ZorbaRunner(this);
        }

        return null;
    }

    public ISemanticValidator getSemanticValidator() {
        return new ZorbaSemanticValidator(this);
    }

    @Override
    public String[] getBuiltinModules() {
        return null;
    }

    @Override
    public void setEnvironmentVariables(EnvironmentVariable[] variables) {
        IPath zorbaHome = getInstallLocation().getPath().removeLastSegments(2);

        List<EnvironmentVariable> newVars = new ArrayList<EnvironmentVariable>(2);
        if (variables != null) {
            newVars.addAll(Arrays.asList(variables));
        }

        if (!Platform.getOS().equals(Platform.OS_WIN32)) {
            String ldLibPath = "LD_LIBRARY_PATH";
            if (Platform.getOS().equals(Platform.OS_MACOSX)) {
                ldLibPath = "DY" + ldLibPath;
            }

            String value = zorbaHome.append("lib").toOSString();
            addVariableIfNotProvided(newVars, ldLibPath, value);
        }

        if (getInstallLocation().getPath().lastSegment().contains("sausastore")) {
            String value = zorbaHome.toOSString();
            addVariableIfNotProvided(newVars, "SAUSALITO_HOME", value);
        }

        super.setEnvironmentVariables(newVars.toArray(new EnvironmentVariable[newVars.size()]));
    }

    private void addVariableIfNotProvided(List<EnvironmentVariable> vars, String name, String value) {
        boolean hasVar = false;
        for (EnvironmentVariable variable : vars) {
            if (variable.getName().equals(name)) {
                hasVar = true;
            }
        }
        if (!hasVar) {
            vars.add(new EnvironmentVariable(name, value));
        }
    }
}
