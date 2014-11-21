/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.model.ast.impl;

import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVisitor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTBindingClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTQuantified;

/**
 * Quantified expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTQuantified extends ASTParentNode implements IASTQuantified {

    // Methods

    /**
     * Set binding clause
     * 
     * @param index
     * @param region
     */
    public void setBindingClause(IASTBindingClause clause) {
        setChildASTNodeAt(0, clause);
    }

    /**
     * Get binding clause
     * 
     * @param index
     */
    public ASTBindingClause getBindingClause() {
        return (ASTBindingClause)getChildASTNodeAt(0);
    }

    /**
     * @param expr
     */
    public void setSatisfiesExpr(IASTNode expr) {
        setChildASTNodeAt(1, expr);
    }

    /**
     * @return satisfies expression
     */
    public IASTNode getSatisfiesExpr() {
        return getChildASTNodeAt(1);
    }

    // Overrides

    @Override
    public int getType() {
        return QUANTIFIED;
    }

    @Override
    protected void accept0(ASTVisitor visitor) {
        boolean children = visitor.visit(this);
        if (children) {
            acceptChildren(visitor);
        }
        visitor.endVisit(this);
    }

    @Override
    protected void getInScopeVariables(List<String> vars, IASTNode child) {
        if (child == getSatisfiesExpr()) {

            IASTBindingClause bindings = getBindingClause();
            if (bindings != null) {
                for (int i = bindings.getBindingExprCount() - 1; i >= 0; i--) {

                    IStructuredDocumentRegion var = bindings.getBindingVariable(i);
                    if (var != null) {
                        vars.add(var.getFullText().trim());
                    }
                }
            }
        }

        super.getInScopeVariables(vars, child);
    }

}
