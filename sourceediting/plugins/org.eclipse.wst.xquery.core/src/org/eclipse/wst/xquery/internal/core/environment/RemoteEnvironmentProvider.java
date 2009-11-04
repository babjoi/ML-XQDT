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
package org.eclipse.wst.xquery.internal.core.environment;

import java.io.File;
import java.net.URI;
import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.core.environment.IEnvironmentProvider;

public class RemoteEnvironmentProvider implements IEnvironmentProvider {

    public static final String FILE_SCHEMES[] = new String[] { "http", "https" };

    public static boolean supportsScheme(String scheme) {
        return scheme != null && Arrays.binarySearch(FILE_SCHEMES, scheme) >= 0;
    }

    public RemoteEnvironmentProvider() {
    }

    public IEnvironment getEnvironment(String envId) {
        if (RemoteEnvironment.ENVIRONMENT_ID.equals(envId)) {
            return RemoteEnvironment.getInstance();
        }
        return null;
    }

    public IEnvironment getEnvironment(URI locationURI) {
        if (RemoteEnvironmentProvider.supportsScheme(locationURI.getScheme())) {
            return RemoteEnvironment.getInstance();
        } else {
            return null;
        }
    }

    public IEnvironment[] getEnvironments() {
        return new IEnvironment[] { RemoteEnvironment.getInstance() };
    }

    public IEnvironment getProjectEnvironment(IProject project) {
        if (project.isAccessible()) {
            IPath location = project.getLocation();
            if (location != null) {
                File file = new File(location.makeAbsolute().toOSString());
                if (file.exists()) {
                    return RemoteEnvironment.getInstance();
                }
            }
        }
        return null;
    }

    public String getProviderName() {
        return "Remote";
    }

    public boolean isInitialized() {
        return true;
    }

    public void waitInitialized() {
    }

}
