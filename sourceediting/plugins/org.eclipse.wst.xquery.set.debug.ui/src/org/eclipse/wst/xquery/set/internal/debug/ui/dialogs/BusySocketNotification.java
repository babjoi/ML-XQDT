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

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.set.internal.launching.server.IServerLaunchListener;
import org.eclipse.wst.xquery.set.internal.launching.server.Server;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerLaunchJob;

public class BusySocketNotification implements IServerLaunchListener {

    public void fail(final ServerLaunchJob serverJob) {
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                DebugPlugin.getDefault().getLaunchManager().removeLaunch(serverJob.getLaunch());
                Shell shell = Display.getDefault().getActiveShell();
                BusySocketDialog dialog = new BusySocketDialog(shell, serverJob.getServer());
                if (dialog.open() == TrayDialog.OK) {
                    try {
                        Server server = serverJob.getServer();
                        server.setHost(dialog.getHost());
                        server.setPort(dialog.getPort());
                        serverJob.setName("Launch Web Server: " + server.getSocketString());
                        serverJob.schedule();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void sucess(ServerLaunchJob serverJob) {
    }
}
