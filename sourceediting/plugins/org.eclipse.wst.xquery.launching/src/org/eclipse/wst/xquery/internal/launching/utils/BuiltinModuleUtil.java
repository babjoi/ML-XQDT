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
package org.eclipse.wst.xquery.internal.launching.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.wst.xquery.launching.IXQDTLaunchingConstants;
import org.eclipse.wst.xquery.launching.ModuleSearchPath;
import org.eclipse.wst.xquery.launching.XQDTLaunchingPlugin;

public class BuiltinModuleUtil {

    public static List<ModuleSearchPath> readBuiltinModuleSearchPaths(IInterpreterInstallType interpreterInstallType) {
        List<ModuleSearchPath> paths = new ArrayList<ModuleSearchPath>();
        IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(XQDTLaunchingPlugin.PLUGIN_ID,
                IXQDTLaunchingConstants.BUILTIN_MODULES_EXTENSION_ID);

        IConfigurationElement[] searchPaths = extPoint.getConfigurationElements();
        for (IConfigurationElement searchPath : searchPaths) {
            String installTypeId = searchPath
                    .getAttribute(IXQDTLaunchingConstants.BUILTIN_INTERPRETER_INSTALL_TYPE_ID_ATTRIBUTE);
            if (installTypeId.equals(interpreterInstallType.getId())) {
                String path = searchPath.getAttribute(IXQDTLaunchingConstants.BUILTIN_PATH_ATTRIBUTE);
                boolean relative = Boolean.parseBoolean(searchPath
                        .getAttribute(IXQDTLaunchingConstants.BUILTIN_RELATIVE_ATTRIBUTE));
                paths.add(new ModuleSearchPath(path, relative));
            }
        }
        return paths;
    }
}
