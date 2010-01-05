/*******************************************************************************
 * Copyright (c) 2009 Mark Logic Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Neth (Mark Logic) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.launching.marklogic;

import java.net.URI;

import org.eclipse.dltk.core.IExternalSourceModule;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.wst.xquery.core.XQDTUriResolver;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryModule;

public class MarkLogicUriResolver extends XQDTUriResolver {

    @Override
    public ISourceModule locateSourceModule(URI uri, IScriptProject project) {
        if (uri.toString().startsWith(IMarkLogicConstants.MARKLOGIC_MODULE_PREFIX)) {
            return findBuiltinModule(project, uri);
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
                        if (module instanceof IExternalSourceModule) {
                            XQueryModule xqModule = (XQueryModule)SourceParserUtil.getModuleDeclaration(module);
                            if (xqModule instanceof XQueryLibraryModule) {
                                if (((XQueryLibraryModule)xqModule).getNamespaceUri().getValue().equals(uri.toString())) {
                                    return module;
                                }
                            }
                        }
                    }
                }
            }
        } catch (ModelException e) {
        }

        return null;
    }
}
