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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;

public class RemoteFileHandle implements IFileHandle {

    public static final String ELIPSE = "^^^";

    private final IEnvironment fEnvironment;
    private final URI fUri;
    private final IPath fPath;

    public RemoteFileHandle(IEnvironment environment, URI uri) {
        fEnvironment = environment;
        fUri = uri;

        IPath path = new Path("[URI]", "/");
        String scheme = fUri.getScheme();
        path = path.append(scheme == null ? ELIPSE : scheme);
        String host = fUri.getHost();
        path = path.append(host == null ? ELIPSE : host);
        int port = fUri.getPort();
        path = path.append(port == -1 ? ELIPSE : "" + port);
        path = path.append(fUri.getPath());

        fPath = path;
    }

    public boolean exists() {
        return true;
    }

    public String getCanonicalPath() {
        return fUri.toString();
    }

    public IFileHandle getChild(String path) {
        return new RemoteFileHandle(fEnvironment, fUri.resolve(path));
    }

    public IFileHandle[] getChildren() {
        return null;
    }

    public IEnvironment getEnvironment() {
        return fEnvironment;
    }

    public String getEnvironmentId() {
        return fEnvironment.getId();
    }

    public IPath getFullPath() {
        return fPath;
    }

    public String getName() {
        return fPath.lastSegment();
    }

    public IFileHandle getParent() {
        return new RemoteFileHandle(fEnvironment, fUri.resolve(fPath.removeLastSegments(1).toPortableString()));
    }

    public IPath getPath() {
        return fPath;
    }

    public boolean isDirectory() {
        return false;
    }

    public boolean isFile() {
        return true;
    }

    public boolean isSymlink() {
        return false;
    }

    public long lastModified() {
        return 0;
    }

    public long length() {
        return 0;
    }

    public InputStream openInputStream(IProgressMonitor monitor) throws IOException {
        return null;
    }

    public OutputStream openOutputStream(IProgressMonitor monitor) throws IOException {
        return null;
    }

    public String toOSString() {
        return fUri.toString();
    }

    public URI toURI() {
        return fUri;
    }

    public void move(IFileHandle destination) throws CoreException {
        throw new CoreException(new Status(IStatus.ERROR, XQDTCorePlugin.PLUGIN_ID, "Method not implemented"));
    }
}
