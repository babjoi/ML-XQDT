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
package org.eclipse.wst.xquery.set.internal.core;

import java.net.URI;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IExternalSourceModule;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.wst.xquery.core.XQDTUriResolver;

public class SETResolver extends XQDTUriResolver {

    public ISourceModule locateSourceModule(URI uri, IScriptProject project) {
        String uriString = uri.toString();
        if (uriString.startsWith(ISETConstants.ZORBA_MODULE_PREFIX)
                || uriString.startsWith(ISETConstants.SAUSALITO_MODULE_PREFIX)) {

            IPath modulePath = composePath(uri);
            return findExternalSourceModule(project, modulePath);
        }

        return super.locateSourceModule(uri, project);
    }

    private ISourceModule findExternalSourceModule(IScriptProject project, IPath path) {
        try {
            for (IProjectFragment fragment : project.getProjectFragments()) {
                if (fragment.isExternal()) {
                    IPath folderPath = path.removeLastSegments(1);
                    IScriptFolder folder = fragment.getScriptFolder(folderPath);
                    if (folder != null) {
                        ISourceModule module = folder.getSourceModule(path.lastSegment());
                        if (module instanceof IExternalSourceModule) {
                            return module;
                        }
                    }
                }
            }
        } catch (ModelException e) {
        }

        return null;
    }

    private IPath composePath(URI uri) {
        String[] splits = uri.getAuthority().split("\\.");

        // add the authority in reverse order
        IPath result = new Path("");
        for (int i = splits.length - 1; i >= 0; i--) {
            result = result.append(splits[i]);
        }

        // add the rest of the path
        result = result.append(uri.getPath()).addFileExtension("xq");

        return result;
    }
}