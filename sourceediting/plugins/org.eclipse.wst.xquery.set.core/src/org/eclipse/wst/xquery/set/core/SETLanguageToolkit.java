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
package org.eclipse.wst.xquery.set.core;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.wst.xquery.internal.core.XQDTContentType;

public class SETLanguageToolkit extends AbstractLanguageToolkit {

    private static SETLanguageToolkit sToolkit = new SETLanguageToolkit();

    public String getLanguageContentType() {
        return XQDTContentType.XQUERY_CONTENT_TYPE;
    }

    public String getLanguageName() {
        return "XQuery"; //$NON-NLS-1$
    }

    public String getNatureId() {
        return SETNature.NATURE_ID;
    }

    public static IDLTKLanguageToolkit getDefault() {
        return sToolkit;
    }

    public String getPreferenceQualifier() {
        return SETCorePlugin.PLUGIN_ID;
    }

    @Override
    public boolean canValidateContent(IResource resource) {
        if (resource instanceof IFile && isEmptyExtension(resource.getName())) {
            IFile file = (IFile)resource;
            return file.getProjectRelativePath().segment(0).equals(ISETCoreConstants.PROJECT_DIRECTORY_TEST);
//            return false;
        }

        return super.canValidateContent(resource);
    }
}
