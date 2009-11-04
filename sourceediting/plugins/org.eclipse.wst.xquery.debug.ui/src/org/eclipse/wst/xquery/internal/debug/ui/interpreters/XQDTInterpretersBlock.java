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
package org.eclipse.wst.xquery.internal.debug.ui.interpreters;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.debug.ui.interpreters.IScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.debug.ui.XQDTDebugUIPlugin;
import org.eclipse.wst.xquery.launching.IEnvironmentAwareInterpreterInstallType;

public class XQDTInterpretersBlock extends InterpretersBlock {

    @Override
    protected IScriptInterpreterDialog createInterpreterDialog(IEnvironment environment, IInterpreterInstall standin) {
        IInterpreterInstallType[] types = ScriptRuntime.getInterpreterInstallTypes(getCurrentNature());

        ArrayList<IInterpreterInstallType> goodTypes = new ArrayList<IInterpreterInstallType>();
        for (IInterpreterInstallType type : types) {
            if (type instanceof IEnvironmentAwareInterpreterInstallType) {
                if (((IEnvironmentAwareInterpreterInstallType)type).getEnvironment() == environment) {
                    goodTypes.add(type);
                }
            } else if (environment == EnvironmentManager.getLocalEnvironment()) {
                goodTypes.add(type);
            }
        }

        if (goodTypes.size() == 0) {
            XQDTDebugUIPlugin.getDefault().getLog().log(
                    new Status(IStatus.ERROR, XQDTDebugUIPlugin.PLUGIN_ID,
                            "No IInterpreterInstallType definition can be associated with this environment: "
                                    + environment.getClass().getName() + ". In order to associate an "
                                    + IInterpreterInstallType.class.getName() + " with a particular "
                                    + IEnvironment.class.getName()
                                    + ", let the IInterpreterInstallType implementation also implement the "
                                    + IEnvironmentAwareInterpreterInstallType.class.getName()));
            return null;
        }

        GenericAddInterpreterDialog dialog = new GenericAddInterpreterDialog(this, getShell(), goodTypes
                .toArray(new IInterpreterInstallType[goodTypes.size()]), standin);
        dialog.setEnvironment(environment);
        return dialog;
    }

    protected String getCurrentNature() {
        return XQDTNature.NATURE_ID;
    }
}
