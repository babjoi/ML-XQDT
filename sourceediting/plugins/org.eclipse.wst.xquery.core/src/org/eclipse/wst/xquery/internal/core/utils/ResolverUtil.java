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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.wst.xquery.core.IUriResolver;
import org.eclipse.wst.xquery.core.IXQDTCorePreferences;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.internal.core.IXQDTConstants;

public class ResolverUtil {

    public static IUriResolver getProjectUriResolver(IProject project) {
        IUriResolver resolver = null;

        IEclipsePreferences preferences = new ProjectScope(project).getNode(XQDTCorePlugin.PLUGIN_ID);
        String resolverId = preferences.get(IXQDTCorePreferences.URI_RESOLVER_PREFERENCE_KEY, null);

        if (resolverId != null) {
            IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(
                    IXQDTConstants.URI_RESOLVER_EXTENSION_ID);

            IExtension[] extensions = extPoint.getExtensions();
            for (IExtension extension : extensions) {
                if (extension.getUniqueIdentifier().equals(resolverId)) {
                    try {
                        resolver = (IUriResolver)extension.getConfigurationElements()[0]
                                .createExecutableExtension("class");
                    } catch (InvalidRegistryObjectException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (CoreException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }

        if (resolver == null) {
            resolver = XQDTCorePlugin.getDefault().getUriResolver();
        }

        return resolver;
    }
}
