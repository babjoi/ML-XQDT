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
package org.eclipse.wst.xquery.internal.launching.zorba;

import java.net.URI;

import org.eclipse.dltk.core.IExternalSourceModule;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.wst.xquery.core.XQDTUriResolver;

public class ZorbaUriResolver extends XQDTUriResolver {

    @Override
    public ISourceModule locateSourceModule(URI uri, IScriptProject project) {
        if (uri.toString().startsWith(IZorbaConstants.ZORBA_NEW_MODULE_PREFIX)
                || uri.toString().startsWith(IZorbaConstants.ZORBA_OLD_MODULE_PREFIX)) {
            return findBuiltinModule(project, uri);
        }
        if (uri.toString().startsWith(IZorbaConstants.ZORBA_EXPATH_MODULE_PREFIX)) {
            ISourceModule module = findBuiltinModule(project, uri);
            if (module != null) {
                return module;
            }
        }

        return super.locateSourceModule(uri, project);
    }

    private ISourceModule findBuiltinModule(IScriptProject project, URI uri) {
        try {
            IScriptFolder[] folders = project.getScriptFolders();
            for (IScriptFolder folder : folders) {
                if (folder.isReadOnly()) {
                    ISourceModule[] modules = folder.getSourceModules();
                    for (ISourceModule module : modules) {
                        if (module instanceof IExternalSourceModule && module.getElementName().equals(uri.toString())) {
                            return module;
                        }
                    }
                }
            }
        } catch (ModelException e) {
        }

        return null;
    }

}
