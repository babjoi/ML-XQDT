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
package org.eclipse.wst.xquery.set.launching;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.launching.ScriptRuntime.DefaultInterpreterEntry;
import org.eclipse.wst.xquery.set.core.SETNature;

public class CoreSdkUtil {

    public static IPath getCoreSDKScriptPath(IProject project) throws CoreException {
        IInterpreterInstall install = ScriptRuntime.getInterpreterInstall(DLTKCore.create(project));
        return install.getInstallLocation().getPath();
    }

    public static IPath getProjectCoreSDKInstallationPath(IProject project) throws CoreException {
        return getCoreSDKScriptPath(project).removeLastSegments(2);
    }

    public static IPath getKillCommandPath(IProject project) {
        if (Platform.getOS().equals(Platform.OS_WIN32)) {
            try {
                return getProjectCoreSDKInstallationPath(project).append("bin").append("term.exe");
            } catch (CoreException e) {
                return null;
            }
        }
        return new Path("kill");
    }

    public static IPath getDefaultCoreSDKInstallationPath() throws CoreException {
        DefaultInterpreterEntry[] entries = ScriptRuntime.getDefaultInterpreterIDs();
        for (DefaultInterpreterEntry entry : entries) {
            if (entry.getNature().equals(SETNature.NATURE_ID)) {
                IInterpreterInstall install = ScriptRuntime.getDefaultInterpreterInstall(entry);
                if (install == null)
                    return null;
                return install.getInstallLocation().getPath().removeLastSegments(2);
            }
        }
        return null;
    }

}