/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.ui.propertytesters;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IEditorInput;
import org.eclipse.wst.xquery.internal.ui.editor.XQDTEditor;
import org.eclipse.wst.xquery.set.core.SETNature;

public class SETSausalitoProjectTester extends PropertyTester {

    private static final String PROPERTY_IS_IN_SAUSALITO_PROJECT = "isInSausalitoProject";

    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (PROPERTY_IS_IN_SAUSALITO_PROJECT.equals(property)) {
            return isInSausalitoProject(receiver);
        }
        throw new IllegalArgumentException("Unknown test property '" + property + "'"); //$NON-NLS-1$ //$NON-NLS-2$		
    }

    private boolean isInSausalitoProject(Object receiver) {
        // or is the receiver an IResource or smth similar?
        if (receiver instanceof IAdaptable) {
            // if this is a resource
            Object obj = ((IAdaptable)receiver).getAdapter(IResource.class);
            if (obj != null) {
                return isInSausalitoProject((IResource)obj);
            }
        }

        // or is the receiver an editor?
        if (receiver instanceof XQDTEditor) {
            XQDTEditor editor = (XQDTEditor)receiver;
            IEditorInput input = editor.getEditorInput();
            Object obj = input.getAdapter(IResource.class);
            if (obj != null) {
                return isInSausalitoProject((IResource)obj);
            }
        }

        return false;
    }

    private boolean isInSausalitoProject(IResource resource) {
        if (resource == null) {
            return false;
        }

        try {
            return (resource.getProject().hasNature(SETNature.NATURE_ID));
        } catch (CoreException e) {
            return false;
        }
    }

}
