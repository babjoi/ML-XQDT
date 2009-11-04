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

public class XQueryModuleDecl extends Declaration implements IChildProcessor {

    private String fNamespacePrefix;
    private XQueryStringLiteral fNamespaceUri;

    public XQueryModuleDecl(int start, int end) {
        super(start, end);
    }

    public String getNamespacePrefix() {
        return fNamespacePrefix;
    }

    public void setNamespacePrefix(String namespacePrefix) {
        fNamespacePrefix = namespacePrefix;
    }

    public XQueryStringLiteral getNamespaceUri() {
        return fNamespaceUri;
    }

    public void setNamespaceUri(XQueryStringLiteral namespaceUri) {
        fNamespaceUri = namespaceUri;
    }

    public void processChild(ASTNode child) {
        if (child instanceof XQueryStringLiteral) {
            setNamespaceUri((XQueryStringLiteral)child);
        }
    }

    @Override
    public int getKind() {
        return XQDTExpressionConstants.XQDT_MODULE_DECL;
    }

    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        if (visitor.visit(this))
            visitor.endvisit(this);
    }
}