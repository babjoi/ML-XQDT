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
import org.eclipse.dltk.ast.declarations.FieldDeclaration;

public class XQueryVarDecl extends FieldDeclaration {

    private String fType;

    private boolean fExternal = false;
    private boolean fConstant = false;

    public XQueryVarDecl(String name, int nameStart, int nameEnd, int declStart, int declEnd) {
        this(name, nameStart, nameEnd, declStart, declEnd, false, false);
    }

    public XQueryVarDecl(String name, int nameStart, int nameEnd, int declStart, int declEnd, boolean external) {
        this(name, nameStart, nameEnd, declStart, declEnd, external, false);
    }

    public XQueryVarDecl(String name, int nameStart, int nameEnd, int declStart, int declEnd, boolean external,
            boolean constant) {
        super(name, nameStart, nameEnd, declStart, declEnd);
        fExternal = external;
        fConstant = constant;
    }

    public void setType(String type) {
        this.fType = type;
    }

    public String getType() {
        return fType;
    }

    public boolean isExternal() {
        return fExternal;
    }

    public void setExternal(boolean external) {
        fExternal = external;
    }

    public boolean isConstant() {
        return fConstant;
    }

    public void setConstant(boolean constant) {
        fConstant = constant;
    }

    @Override
    public int getKind() {
        return Declaration.D_VAR_DECL;
    }

}
