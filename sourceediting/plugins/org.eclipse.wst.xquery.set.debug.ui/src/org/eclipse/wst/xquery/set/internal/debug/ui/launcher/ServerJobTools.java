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
package org.eclipse.wst.xquery.set.internal.debug.ui.launcher;

import org.eclipse.wst.xquery.set.internal.debug.ui.dialogs.BusySocketNotification;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerManager;

public class ServerJobTools {

    private static ServerJobTools fInstance;

    private ServerJobTools() {
        init();
    }

    private void init() {
        ServerManager.getInstance().addServerLaunchListener(new BusySocketNotification());
    }

    public static ServerJobTools getInstance() {
        if (fInstance == null) {
            fInstance = new ServerJobTools();
        }
        return fInstance;
    }

}
