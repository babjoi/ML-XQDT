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
package org.eclipse.wst.xquery.set.internal.core.codeassist;

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.wst.xquery.core.IUriResolver;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.internal.core.codeassist.XQDTCompletionEngine;
import org.eclipse.wst.xquery.set.core.SETCorePlugin;
import org.eclipse.wst.xquery.set.internal.core.SETProjectConfig;
import org.eclipse.wst.xquery.set.internal.core.SETProjectConfigUtil;

public class SETCompletionEngine extends XQDTCompletionEngine {

    private URI fBaseUri;

    @Override
    protected IUriResolver getModuleResolver() {
        return SETCorePlugin.getDefault().getModuleResolver();
    }

    protected int getLanuageLevel(ISourceModule module) {
        return IXQDTLanguageConstants.LANGUAGE_XQUERY_SCRIPTING;
    }

//    protected List<ISourceModule> locateSourceModules(List<URI> resolvedUris) {
//        IScriptProject project = fSourceModule.getScriptProject();
//
//        for (int i = 0; i < resolvedUris.size(); i++) {
//            if (resolved.get(i) == null) {
//                URI uri = resolvedUris.get(i);
//                String uriString = uri.toString();
//
//                if (uriString.startsWith(fBaseUri.toString())) {
//                    String relativePath = uriString.substring(fBaseUri.toString().length());
//                    IPath path = new Path(relativePath);
//
//                    if (path.segmentCount() == 0) {
//                        continue;
//                    }
//
//                    String moduleName = path.lastSegment() + ".xq";
//
//                    try {
//                        // if handler module
//                        if (path.segmentCount() == 1) {
//                            IPath handlersPath = project.getPath().append(ISETPreferenceConstants.DIR_NAME_HANDLER);
//                            IScriptFolder folder = project.findScriptFolder(handlersPath);
//                            if (folder == null) {
//                                continue;
//                            }
//                            ISourceModule module = folder.getSourceModule(moduleName);
//                            if (module == null || !module.exists()) {
//                                resolved.set(i, module);
//                            }
//                        }
//                        // if library module
//                        else if (path.segmentCount() > 1) {
//                            String dir = path.segment(0);
//
//                            if (dir.equals(ISETPreferenceConstants.DIR_NAME_LIBRARY)
//                                    || dir.equals(ISETPreferenceConstants.DIR_NAME_EXTERNAL)) {
//                                IPath relLibPath = path.removeLastSegments(1);
//                                IPath libPath = project.getPath().append(relLibPath);
//                                IScriptFolder folder = project.findScriptFolder(libPath);
//                                if (folder == null) {
//                                    continue;
//                                }
//                                ISourceModule module = folder.getSourceModule(moduleName);
//                                if (module != null && module.exists()) {
//                                    resolved.set(i, module);
//                                }
//
//                            }
//                        }
//                    } catch (ModelException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return resolved;
//    }

    protected URI getBaseUri() {
        IProject project = fSourceModule.getResource().getProject();
        SETProjectConfig config = SETProjectConfigUtil.readProjectConfig(project);
        fBaseUri = config.getLogicalUri();
        return fBaseUri;
    }

}