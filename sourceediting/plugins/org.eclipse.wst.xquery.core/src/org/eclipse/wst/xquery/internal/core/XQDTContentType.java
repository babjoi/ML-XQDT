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
package org.eclipse.wst.xquery.internal.core;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.content.IContentDescription;

public class XQDTContentType {

    public static final String XQUERY_CONTENT_TYPE = "org.eclipse.wst.xquery.XQueryContentType";

    public static boolean isXQueryFile(IResource resource) {
        IFile file = null;
        if (resource.getAdapter(IFile.class) != null) {
            file = (IFile)resource.getAdapter(IFile.class);
        }
        if (file == null) {
            return false;
        }
        try {
            IContentDescription description = file.getContentDescription();
            if (description != null) {

                return description.getContentType().getId().equals(XQUERY_CONTENT_TYPE);
            }
        } catch (CoreException e) {
            // do nothing...
        }
        return false;
    }
}
