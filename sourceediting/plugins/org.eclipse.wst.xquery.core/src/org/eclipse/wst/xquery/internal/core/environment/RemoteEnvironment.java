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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.environment.EnvironmentPathUtils;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.core.internal.environment.EFSFileHandle;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;

public class RemoteEnvironment implements IEnvironment, IAdaptable {

    public static final String ENVIRONMENT_ID = XQDTCorePlugin.PLUGIN_ID + ".environment.remoteEnvironment";

    private static IEnvironment instance = new RemoteEnvironment();
    private IFileSystem fs;

    private RemoteEnvironment() {
        this.fs = EFS.getLocalFileSystem();
    }

    /*
     * @see org.eclipse.dltk.core.environment.IEnvironment#isLocal()
     */
    public boolean isLocal() {
        return false;
    }

    public IFileHandle getFile(IPath path) {
        if (path == null) {
            return null;
        }

        if (path.segment(0).equals("[URI]")) {
            if (path.segmentCount() < 4) {
                return null;
            }
            try {
                IPath filePath = path.removeFirstSegments(4);
                String scheme = path.segment(1);
                String auth = path.segment(2);
                String port = path.segment(3);

                StringBuffer sb = new StringBuffer();
                sb.append(RemoteFileHandle.ELIPSE.equals(scheme) ? "" : scheme + "://");
                sb.append(RemoteFileHandle.ELIPSE.equals(auth) ? "" : auth);
                sb.append(RemoteFileHandle.ELIPSE.equals(port) ? "" : ":" + port);
                if (filePath.segmentCount() != 0) {
                    sb.append("/" + filePath.toPortableString());
                }

                URI uri = new URI(sb.toString());

                return new RemoteFileHandle(this, uri);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            IFileStore store = fs.getStore(path);
            EFSFileHandle fileHandle = new EFSFileHandle(this, store);
            if (!fileHandle.exists()) {
                // Try to resolve file from resources
                IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(path.toFile().toURI());
                if (files.length == 1) {
                    store = fs.getStore(files[0].getLocation());
                    fileHandle = new EFSFileHandle(this, store);
                }
            }
            return fileHandle;

        }
        return null;
    }

    public String getId() {
        return ENVIRONMENT_ID;
    }

    public static IEnvironment getInstance() {
        return instance;
    }

    public String getSeparator() {
        return "/";
    }

    public char getSeparatorChar() {
        return '/';
    }

    public String getName() {
        return "Remote"; //$NON-NLS-1$
    }

    public String convertPathToString(IPath path) {
        return EnvironmentPathUtils.getLocalPath(path).toOSString();
    }

    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class adapter) {
        return Platform.getAdapterManager().loadAdapter(this, adapter.getName());
    }

    public URI getURI(IPath location) {
        return URIUtil.toURI(location);
    }

    public IFileHandle getFile(URI locationURI) {
        return new EFSFileHandle(this, fs.getStore(locationURI));
    }

    public String getPathsSeparator() {
        return Character.toString(getPathsSeparatorChar());
    }

    public char getPathsSeparatorChar() {
        return Platform.getOS().equals(Platform.OS_WIN32) ? ';' : ':';
    }

    public String getCanonicalPath(IPath path) {
        try {
            return path.toFile().getCanonicalFile().toString();
        } catch (IOException e) {
            if (DLTKCore.DEBUG) {
                e.printStackTrace();
            }
            return path.toOSString();
        }
    }

    /**
     * @since 2.0
     */
    public boolean isConnected() {
        return true;
    }

    /**
     * @since 2.0
     */
    public boolean connect() {
        return true;
    }

}
