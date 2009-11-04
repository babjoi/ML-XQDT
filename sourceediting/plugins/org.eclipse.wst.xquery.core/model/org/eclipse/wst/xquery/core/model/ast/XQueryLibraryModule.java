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
import org.eclipse.dltk.ast.statements.Statement;

public class XQueryLibraryModule extends XQueryModule implements ICollectible {

    private XQueryModuleDecl fModuleDecl;

    public XQueryLibraryModule(int length) {
        super(length);
    }

    public XQueryModuleDecl getModuleDeclaration() {
        return fModuleDecl;
    }

    public void setModuleDeclaration(XQueryModuleDecl moduleDecl) {
        fModuleDecl = moduleDecl;
    }

    public String getNamespacePrefix() {
        return fModuleDecl.getNamespacePrefix();
    }

    public void setNamespacePrefix(String namespacePrefix) {
        fModuleDecl.setNamespacePrefix(namespacePrefix);
    }

    public XQueryStringLiteral getNamespaceUri() {
        return fModuleDecl.getNamespaceUri();
    }

    public void setNamespaceUri(XQueryStringLiteral namespaceUri) {
        fModuleDecl.setNamespaceUri(namespaceUri);
    }

    public int getNodeType() {
        return IXQDTASTNode.XQUERY_LIBRARY_MODULE;
    }

    public void processChild(ASTNode child) {
        super.processChild(child);

        if (child instanceof Statement) {
            Statement statement = (Statement)child;
            switch (statement.getKind()) {
            case XQDTExpressionConstants.XQDT_MODULE_DECL:
                addStatement(statement);
                setModuleDeclaration(((XQueryModuleDecl)child));
                break;
            }
        }

    }
}