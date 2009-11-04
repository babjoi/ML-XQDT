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
package org.eclipse.wst.xquery.internal.core.modules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.ISourceModule;

public class XQDTModuleCollector implements IModelElementVisitor {

    private List<ISourceModule> fModules = new ArrayList<ISourceModule>();

    public boolean visit(IModelElement element) {
        if (element.getElementType() == IModelElement.SOURCE_MODULE) {
            fModules.add((ISourceModule)element);
            return false;
        }
        return true;
    }

    public List<ISourceModule> getModules() {
        return fModules;
    }
}