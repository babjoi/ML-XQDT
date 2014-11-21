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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.environment.EnvironmentPathUtils;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstallType;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSDKInstallType extends XQDTInterpreterInstallType {

    private static final String INSTALL_TYPE_NAME = "Sausalito CoreSDK";
    public static final String INSTALL_TYPE_ID = "org.eclipse.wst.xquery.set.launching.CoreSDKInstallType";

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

    @Override
    protected String[] getPossibleInterpreterNames() {
        return new String[] { "sausalito" };
    }

    @Override
    public synchronized LibraryLocation[] getDefaultLibraryLocations(IFileHandle installLocation,
            EnvironmentVariable[] variables, IProgressMonitor monitor) {
        IPath sausalitoRoot = new Path(installLocation.getParent().getParent().getCanonicalPath()).append("share/zorba/uris");
        LibraryLocation loc = new LibraryLocation(EnvironmentPathUtils.getFullPath(getEnvironment(), sausalitoRoot));
        return new LibraryLocation[] { loc };
    }

}
