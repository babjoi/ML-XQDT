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

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IExternalSourceModule;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.wst.xquery.internal.core.XQDTUriResolver;

public class MarkLogicUriResolver extends XQDTUriResolver {

    @Override
    public ISourceModule locateSourceModule(URI uri, IScriptProject project) {
        ISourceModule module = super.locateSourceModule(uri, project);
        if (module == null) {
            try {
                IModelElement element = project.findElement(new Path(uri.getPath()));
                if (element instanceof IExternalSourceModule) {
                    module = (ISourceModule)element;
                }
            } catch (ModelException e) {
                e.printStackTrace();
            }
        }
        return module;
    }
}
