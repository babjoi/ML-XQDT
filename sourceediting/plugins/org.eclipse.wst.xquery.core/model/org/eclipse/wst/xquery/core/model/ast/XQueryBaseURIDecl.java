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

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;

public class XQueryBaseURIDecl extends Declaration implements IChildProcessor {

    private XQueryStringLiteral fUri;

    public XQueryBaseURIDecl(int start, int end) {
        super(start, end);
    }

    public XQueryStringLiteral getUri() {
        return fUri;
    }

    public void setUri(XQueryStringLiteral namespaceUri) {
        fUri = namespaceUri;
    }

    public void processChild(ASTNode child) {
        if (child instanceof XQueryStringLiteral) {
            setUri((XQueryStringLiteral)child);
        }
    }

    @Override
    public int getKind() {
        return XQDTExpressionConstants.XQDT_BASE_URI_DECL;
    }

    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        if (visitor.visit(this))
            visitor.endvisit(this);
    }
}