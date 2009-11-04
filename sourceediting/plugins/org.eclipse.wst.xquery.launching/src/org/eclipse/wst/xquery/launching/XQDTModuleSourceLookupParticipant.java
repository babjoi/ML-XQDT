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
package org.eclipse.wst.xquery.launching;

import java.net.URI;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryModuleImport;

public class XQDTModuleSourceLookupParticipant extends AbstractSourceLookupParticipant {

    public String getSourceName(Object object) throws CoreException {
//        if (object instanceof ScriptStackFrame) {
//            final ScriptStackFrame frame = (ScriptStackFrame)object;
//            final URI uri = frame.getSourceURI();
//
//            return uri.getPath();
//        }
        return null;
    }

    @Override
    public Object[] findSourceElements(Object object) throws CoreException {
//        if (object instanceof ScriptStackFrame) {
//            ScriptStackFrame frame = (ScriptStackFrame)object;
//            final URI uri = frame.getSourceURI();
//
//            if (DLTKDebugConstants.UNKNOWN_SCHEME.equalsIgnoreCase(uri.getScheme())
//                    || DLTKDebugConstants.DBGP_SCHEME.equalsIgnoreCase(uri.getScheme())) {
//                return null;
//            }
//
//            ILaunchConfiguration launchConfiguration = this.getDirector().getLaunchConfiguration();
//            @SuppressWarnings("unchecked")
//            List<String> resources = (List<String>)launchConfiguration.getAttribute(
//                    LaunchConfiguration.ATTR_MAPPED_RESOURCE_PATHS, Collections.EMPTY_LIST);
//            if (resources.size() != 1) {
//                return null;
//            }
//
//            IPath mainModulePath = new Path(resources.get(0));
//            if (mainModulePath.isEmpty()) {
//                return null;
//            }
//
//            IProject project = LaunchConfigurationUtils.getProject(launchConfiguration);
//            IScriptProject scriptProject = (IScriptProject)DLTKCore.create(project);
//
//            IModelElement element = scriptProject.findElement(mainModulePath.removeFirstSegments(1));
//            if (element instanceof ISourceModule) {
//                System.err.println("TODO: add the imported module resolving");
//
//                // return the main module file in case the special URI is sent
//                if (uri.getScheme().equals("xquery") && uri.getAuthority().equals("main_module"))
//                    return new Object[] { ((ISourceModule)element).getResource() };
//
//                // search in the imported module hierarchy
//                ModuleDeclaration modDecl = SourceParserUtil.getModuleDeclaration((ISourceModule)element);
//                if (modDecl instanceof XQueryModule) {
//                    XQueryModule module = (XQueryModule)modDecl;
//                    @SuppressWarnings("unused")
//                    URI baseUri = module.getBaseUri();
//                    @SuppressWarnings("unused")
//                    List<XQueryModuleImport> imports = module.getImports();
//                }
//            }
//        }
        return null;
    }

    @SuppressWarnings("unused")
    private List<ISourceModule> resolveImports(URI baseUri, List<XQueryModuleImport> importedModules) {
//        List<URI> resolvedUris = new ArrayList<URI>(importedModules.size());
//        IResolver resolver = XQDTCorePlugin.getDefault().getModuleResolver();
//
//        for (XQueryModuleImport imp : importedModules) {
//            String uri = imp.getNamespaceUri().getValue();
//            List<XQueryStringLiteral> hintList = imp.getHints();
//            String[] hints = new String[hintList.size()];
//            for (int i = 0; i < hintList.size(); i++) {
//                hints[i] = hintList.get(i).getValue();
//            }
//
//            // this will add either a resolved URI to the list or a null
//            URI resolvedUri = resolver.resolveModuleImport(baseUri, uri, hints);
//            resolvedUris.add(resolvedUri);
//        }

        return null;// locateSourceModules(resolvedUris);
    }

}
