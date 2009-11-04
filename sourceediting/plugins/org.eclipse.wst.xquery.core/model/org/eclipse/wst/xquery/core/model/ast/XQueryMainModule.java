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

public class XQueryMainModule extends XQueryModule {

    private XQueryQueryBody fBody;

    public XQueryMainModule(int length) {
        super(length);
    }

    public XQueryQueryBody getBody() {
        return fBody;
    }

    public String getNamespacePrefix() {
        return "local";
    }

    public int getNodeType() {
        return IXQDTASTNode.XQUERY_MAIN_MODULE;
    }

    @Override
    public void processChild(ASTNode child) {
        if (child instanceof Statement) {
            Statement statement = (Statement)child;
            switch (statement.getKind()) {
            case XQDTExpressionConstants.XQDT_QUERY_BODY:
                addStatement(statement);
                fBody = (XQueryQueryBody)child;
                break;
            default:
                super.processChild(child);
            }
        }
    }
}