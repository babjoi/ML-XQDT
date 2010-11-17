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
package org.eclipse.wst.xquery.set.internal.debug.ui.interpreters;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.IAddInterpreterDialogRequestor;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.set.debug.ui.SETDebugUIPlugin;

public class SETAddCoreSDKDialog extends AddScriptInterpreterDialog {

    private IInterpreterInstall fEditedInterpreter;

    public SETAddCoreSDKDialog(IAddInterpreterDialogRequestor requestor, Shell shell,
            IInterpreterInstallType[] interpreterInstallTypes, IInterpreterInstall editedInterpreter) {
        super(requestor, shell, interpreterInstallTypes, editedInterpreter);
        fEditedInterpreter = editedInterpreter;
    }

    protected AbstractInterpreterLibraryBlock createLibraryBlock(AddScriptInterpreterDialog dialog) {
        return new SETCoreSDKLibraryBlock(dialog);
    }

    @Override
    protected IStatus validateInterpreterLocation() {
        if (fEditedInterpreter != null && fEditedInterpreter.getId().startsWith("default")) {
            if (!fEditedInterpreter.getInstallLocation().toOSString().equals(getInterpreterPath())) {
                return new Status(IStatus.ERROR, SETDebugUIPlugin.PLUGIN_ID,
                        "It is not allowed to change the path of the CoreSDK that is shipped with Sausalito Tools.");
            }

        }
        return super.validateInterpreterLocation();
    }

}
