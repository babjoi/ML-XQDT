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
package org.eclipse.wst.xquery.set.core.utils;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.xquery.set.core.ISETCoreConstants;
import org.eclipse.wst.xquery.set.core.SETProjectConfig;

public class SETProjectUtil {

    public static IFile getModuleFileForNamespace(IProject project, URI namespace) {
        return getModuleFileForNamespace(project, namespace.toString());
    }

    public static IFile getModuleFileForNamespace(IProject project, String namespace) {
        SETProjectConfig config = SETProjectConfigUtil.readProjectConfig(project.getProject());
        if (config == null) {
            System.err.println("Could not determine the project logical URI.");
            return null;
        }

        URI uri = config.getLogicalUri();
        String uriStr = uri.toString();
        if (!namespace.startsWith(uriStr)) {
            return null;
        }
        IPath path = new Path(namespace.substring(uriStr.length()));
        if (path.segmentCount() == 1) {
            path = new Path(ISETCoreConstants.PROJECT_DIRECTORY_HANDLER).append(path).addFileExtension(
                    ISETCoreConstants.XQUERY_FILE_EXTENSION);

        }

        IFolder folder = project.getFolder(new Path(path.segment(0)));
        IResource resource = folder.findMember(path.removeFirstSegments(1));
        if (resource instanceof IFile) {
            return (IFile)resource;
        }

        return null;
    }
}
