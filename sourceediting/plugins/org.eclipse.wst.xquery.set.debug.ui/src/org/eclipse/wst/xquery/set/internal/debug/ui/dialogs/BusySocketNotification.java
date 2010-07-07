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
package org.eclipse.wst.xquery.set.internal.debug.ui.dialogs;

import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.set.internal.launching.server.Server;

public class BusySocketNotification {

    public void fail() {
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                Shell shell = Display.getDefault().getActiveShell();
                Server server = null;
                BusySocketDialog dialog = new BusySocketDialog(shell, server);
                if (dialog.open() == TrayDialog.OK) {
                    try {
                        server.setHost(dialog.getHost());
                        server.setPort(dialog.getPort());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
