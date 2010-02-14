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
package org.eclipse.wst.xquery.set.internal.launching;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstallType;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSDKInstallType extends XQDTInterpreterInstallType {

    private static final String INSTALL_TYPE_NAME = "Sausalito CoreSDK";

    protected IInterpreterInstall doCreateInterpreterInstall(String id) {
        return new CoreSDKInstall(this, id);
    }

    public String getName() {
        return INSTALL_TYPE_NAME;
    }

    public String getNatureId() {
        return SETNature.NATURE_ID;
    }

    protected String getPluginId() {
        return SETLaunchingPlugin.PLUGIN_ID;
    }

}
