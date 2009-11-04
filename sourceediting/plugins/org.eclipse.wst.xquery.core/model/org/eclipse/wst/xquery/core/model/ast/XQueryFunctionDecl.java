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
package org.eclipse.wst.xquery.core.model.ast;

import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;

public class XQueryFunctionDecl extends MethodDeclaration {

    private String fReturnType;
    private boolean fExternal = false;

    public XQueryFunctionDecl(String namespace, String localName, int nameStart, int nameEnd, int declStart,
            int declEnd, String returnType, boolean external) {
        super(namespace + ":" + localName, nameStart, nameEnd, declStart, declEnd);
        fExternal = external;
        fReturnType = returnType;
    }

    public boolean isExternal() {
        return fExternal;
    }

    public void setExternal(boolean external) {
        fExternal = external;
    }

    @Override
    public String getName() {
        String s = super.getName();
        return s;
    }

    @Override
    public String toString() {
        String s = super.getName();
        return s;
    }

    public String getReturnType() {
        return fReturnType;
    }

    @Override
    public int getKind() {
        return Declaration.D_METHOD;
    }

    public String getUniqueName() {
        return getName() + getArguments().size();
    }
}
