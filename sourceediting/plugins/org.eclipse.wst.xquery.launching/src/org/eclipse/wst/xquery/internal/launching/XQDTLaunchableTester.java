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
package org.eclipse.wst.xquery.internal.launching;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryMainModule;

public class XQDTLaunchableTester extends PropertyTester {

    private static final String PROPERTY_IS_MAIN_MODULE = "isMainModule";
    private static final String PROPERTY_IS_LIBRARY_MODULE = "isLibraryModule";

    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IAdaptable)) {
            throw new IllegalArgumentException(
                    "Element must be of type 'IAdaptable', is " + receiver == null ? "null" : receiver.getClass().getName()); //$NON-NLS-1$ //$NON-NLS-2$
        }

        IModelElement element;
        if (receiver instanceof IModelElement) {
            element = (IModelElement)receiver;
        } else if (receiver instanceof IResource) {
            element = DLTKCore.create((IResource)receiver);
            if (element == null) {
                return false;
            }
        } else { // is IAdaptable
            element = (IModelElement)((IAdaptable)receiver).getAdapter(IModelElement.class);
            if (element == null) {
                IResource resource = (IResource)((IAdaptable)receiver).getAdapter(IResource.class);
                element = DLTKCore.create(resource);
                if (element == null) {
                    return false;
                }
                return false;
            }
        }
        if (PROPERTY_IS_MAIN_MODULE.equals(property)) {
            return isMainModule(element);
        } else if (PROPERTY_IS_LIBRARY_MODULE.equals(property)) {
            return isLibraryModule(element);
        }
        throw new IllegalArgumentException("Unknown test property '" + property + "'"); //$NON-NLS-1$ //$NON-NLS-2$		
    }

    private boolean isMainModule(IModelElement element) {
        if (element instanceof ISourceModule) {
            ISourceModule module = (ISourceModule)element;
            ModuleDeclaration modDecl = SourceParserUtil.getModuleDeclaration(module);
            if (modDecl instanceof XQueryMainModule) {
                return true;
            }
        }
        return false;
    }

    private boolean isLibraryModule(IModelElement element) {
        if (element instanceof ISourceModule) {
            ISourceModule module = (ISourceModule)element;
            ModuleDeclaration modDecl = SourceParserUtil.getModuleDeclaration(module);
            if (modDecl instanceof XQueryLibraryModule) {
                return true;
            }
        }
        return false;
    }

}
