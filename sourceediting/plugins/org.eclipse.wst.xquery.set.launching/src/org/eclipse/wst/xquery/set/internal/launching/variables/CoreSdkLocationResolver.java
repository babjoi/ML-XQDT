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
package org.eclipse.wst.xquery.set.internal.launching.variables;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;
import org.osgi.framework.Bundle;
import org.eclipse.wst.xquery.set.core.SETCorePlugin;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSdkLocationResolver implements IDynamicVariableResolver {

    public String resolveValue(IDynamicVariable variable, String argument) throws CoreException {
        String os = Platform.getOS();
        if (os.equals(Platform.OS_WIN32)) {
            Bundle[] bundles = Platform.getBundles(SETCorePlugin.PLUGIN_ID + "sdk.win32", null);
            if (bundles == null || bundles.length == 0)
                throwException("No CoreSDK plugin fragment was found");

            Bundle bundle = bundles[0];
            URL coreSdkURL = bundle.getEntry("/coresdk");
            try {
                String coreSdkHome = new Path(FileLocator.toFileURL(coreSdkURL).getFile()).toOSString();
                return coreSdkHome;
            } catch (IOException e) {
                throwException("Error while resolving the Sausalito CoreSDK installation location in the bundle: "
                        + SETCorePlugin.PLUGIN_ID + "sdk.win32");
            }
        } else if (os.equals(Platform.OS_MACOSX) || os.equals(Platform.OS_LINUX)) {
            File dir = new File("/opt/sausalito");
            if (dir.isDirectory()) {
                return dir.toString();
            }
            throwException("Could not find a Sausalito CoreSDK installation at location: /opt/sausalito");
        }

        throwException("No default interpreters are supported on this platform: " + os);
        return "";
    }

    private void throwException(String message) throws CoreException {
        throw new CoreException(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID, message));
    }

}
