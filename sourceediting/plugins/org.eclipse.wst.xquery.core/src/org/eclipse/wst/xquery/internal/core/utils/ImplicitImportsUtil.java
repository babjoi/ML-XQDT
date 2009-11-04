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
package org.eclipse.wst.xquery.internal.core.utils;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.internal.core.IXQDTConstants;
import org.eclipse.wst.xquery.internal.core.codeassist.IImplicitImportActivator;

public class ImplicitImportsUtil {

    public static Map<String, IPath> getImplicitImportPrefixes(ISourceModule module) {
        IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(
                IXQDTConstants.IMPLICIT_IMPORTS_EXTENSION_ID);

        Map<String, IPath> result = new TreeMap<String, IPath>();

        IConfigurationElement[] groups = extPoint.getConfigurationElements();
        for (IConfigurationElement group : groups) {
            try {
                // TODO: an activator should NEVER activate for modules outside of it scope
                // (e.g.for other project/modules other than the one it was build for) 
                IImplicitImportActivator activator = (IImplicitImportActivator)group
                        .createExecutableExtension(IXQDTConstants.ACTIVATOR_ATTRIBUTE);
                if (!activator.activateForModule(module)) {
                    continue;
                }
            } catch (CoreException e) {
                if (XQDTCorePlugin.DEBUG) {
                    System.out.println("Could not create the activator for an implicit import group");
                }
            }

            if (true) {
                IConfigurationElement[] prefixes = group
                        .getChildren(IXQDTConstants.BUILTIN_PREFIX_CONFIGURATION_ELEMENT);
                for (IConfigurationElement prefix : prefixes) {
                    String name = prefix.getAttribute(IXQDTConstants.NAME_ATTRIBUTE);
                    if (name == null) {
                        continue;
                    }
                    String path = prefix.getAttribute(IXQDTConstants.BUILTIN_MODULE_PATH_ATTRIBUTE);
                    if (name == null) {
                        continue;
                    }
                    IPath p = new Path(path);
                    result.put(name, p);
                }
            }
        }

        return result;
    }
}
