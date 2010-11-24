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
package org.eclipse.wst.xquery.set.internal.debug.ui.interpreters;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.wst.xquery.debug.ui.XQDTDebugUIPlugin;
import org.eclipse.wst.xquery.debug.ui.interpreters.AddLocalInterpreterDialogBlock;
import org.eclipse.wst.xquery.set.internal.launching.variables.CoreSdkVersionResolver;

public class CoreSDKAddInterpreterDialogBlock extends AddLocalInterpreterDialogBlock {

    private static final String DEFAULT_CHANGE_ERROR_MESSAGE = "The name and the path of the default Sausalito CoreSDK configuration cannot be changed.";

    @Override
    public String generateInterpreterName(IFileHandle interpreterFile) {
        String version = CoreSdkVersionResolver.resolve();
        String genName = getDefaultInterpreterName(interpreterFile) + " " + version;
        return genName.trim();
    }

    @Override
    protected String getDefaultInterpreterName(IFileHandle interpreterFil) {
        return "Sausalito CoreSDK";
    }

    @Override
    protected IStatus validateInterpreterLocation() {
        if (fEditedInterpreter != null && fEditedInterpreter.getId().startsWith("default")) {
            if (!fEditedInterpreter.getInstallLocation().toOSString().equals(getInterpreterLocation())
                    || !fEditedInterpreter.getName().equals(getInterpreterName())) {
                return new Status(IStatus.ERROR, XQDTDebugUIPlugin.PLUGIN_ID, DEFAULT_CHANGE_ERROR_MESSAGE);
            }
        }

        return super.validateInterpreterLocation();
    }

    @Override
    protected IStatus validateInterpreterName() {
        if (fEditedInterpreter != null && fEditedInterpreter.getId().startsWith("default")) {
            if (!fEditedInterpreter.getName().equals(getInterpreterName())) {
                return new Status(IStatus.ERROR, XQDTDebugUIPlugin.PLUGIN_ID, DEFAULT_CHANGE_ERROR_MESSAGE);
            }
        }

        return super.validateInterpreterName();
    }

    @Override
    protected boolean needsArguments() {
        return false;
    }

    @Override
    protected String getInterpreterNameLabelText() {
        return "&Name:";
    }

    @Override
    protected String getInterpreterLocationLabelText() {
        return "&Sausalito script:";
    }
}
