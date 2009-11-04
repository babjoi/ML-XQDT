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

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.wst.xquery.core.model.ast.XQueryModuleImport;

public class XQDTModuleImportCollector extends ASTVisitor {

    private final List<XQueryModuleImport> fImportedModules = new ArrayList<XQueryModuleImport>(3);

    @Override
    public boolean visit(Statement s) throws Exception {
        if (s instanceof XQueryModuleImport) {
            fImportedModules.add((XQueryModuleImport)s);
            return false;
        }
        return super.visit(s);
    }

    public List<XQueryModuleImport> getModulesImported() {
        return fImportedModules;
    }
}
