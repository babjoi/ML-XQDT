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
package org.eclipse.wst.xquery.internal.debug.ui.zorba.interpreters;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.wst.xquery.core.utils.ProcessStreamConsumer;
import org.eclipse.wst.xquery.debug.ui.XQDTDebugUIPlugin;
import org.eclipse.wst.xquery.debug.ui.interpreters.AbstractInterpreterEnvironmentVariablesBlock;
import org.eclipse.wst.xquery.debug.ui.interpreters.AddLocalInterpreterDialogBlock;
import org.eclipse.wst.xquery.debug.ui.interpreters.LocalInterpreterEnvironmentVariablesBlock;
import org.eclipse.wst.xquery.debug.ui.zorba.ZorbaDebugUIPlugin;

public class ZorbaAddInterpreterDialogBlock extends AddLocalInterpreterDialogBlock {

    private static final String DEFAULT_CHANGE_ERROR_MESSAGE = "The name and the path of the default Zorba configuration cannot be changed.";

    @Override
    public String generateInterpreterName(IFileHandle interpreterFile) {
        String genName = null;

        ProcessBuilder pb = new ProcessBuilder(interpreterFile.toOSString(), "--version");
        try {
            Process p = pb.start();
            ProcessStreamConsumer psc = new ProcessStreamConsumer(p);
            psc.start();
            p.waitFor();

            String[] lines = psc.getOutput().split("\n");
            for (String line : lines) {
                if (line.matches("Zorba XQuery Engine, Version: .*")) {
                    line = line.replace("Zorba XQuery Engine, Version: ", "Zorba ");
                    genName = line;
                    break;
                }
            }
        } catch (Exception e) {
            if (ZorbaDebugUIPlugin.DEBUG) {
                e.printStackTrace();
            }
            Status status = new Status(
                    IStatus.ERROR,
                    XQDTDebugUIPlugin.PLUGIN_ID,
                    "Could not determine Zorba version for the interpreter name generation. Using the executable name as default.",
                    e);
            XQDTDebugUIPlugin.getDefault().getLog().log(status);
        }

        return genName;
    }

    @Override
    protected AbstractInterpreterEnvironmentVariablesBlock createEnvironmentVariablesBlock() {
        return new LocalInterpreterEnvironmentVariablesBlock(fAddInterpreterDialog);
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
}
