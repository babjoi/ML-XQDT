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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTBindingClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTFLWOR;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;

/**
 * FLWOR expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTFLWOR extends ASTParentNode implements IASTFLWOR {

    // State

    /** Return expression */
    protected IASTNode returnExpr;

    // Constructors

    public ASTFLWOR() {
    }

    // Methods

    /**
     * Set clause at given index
     * 
     * @param index
     * @param region
     */
    public void setClause(int index, IASTClause clause) {
        setChildASTNodeAt(index, clause);
    }

    /**
     * Gets clause at given index
     * 
     * @param index
     */
    public ASTClause getClause(int index) {
        return (ASTClause)getChildASTNodeAt(index);
    }

    /**
     * Gets the number of clauses.
     * 
     * @return
     */
    public int getClauseCount() {
        return getChildASTNodesCount();
    }

    /**
     * Gets return expression
     */
    public IASTNode getReturnExpr() {
        return this.returnExpr;
    }

    /**
     * @param returnExpr
     */
    public void setReturnExpr(IASTNode expr) {
        if (expr != null) {
            expr.setASTParent(this);
        }
        this.returnExpr = expr;
    }

    // Overrides

    @Override
    public int getType() {
        return FLWOR;
    }

    @Override
    protected void getInScopeVariables(List<String> vars, IASTNode child) {
        // Compute the last binding index to include
        int lastBindingIndex = 0;

        if (child == this.returnExpr) {
            lastBindingIndex = getChildASTNodesCount() - 1; // Includes all
        } else {
            // Includes only the previous binding

            while (lastBindingIndex < getChildASTNodesCount()) {
                if (getClause(lastBindingIndex) == child) {
                    break;
                }
                lastBindingIndex++;
            }

            lastBindingIndex--;
        }

        while (lastBindingIndex >= 0) {
            IASTClause clause = getClause(lastBindingIndex);
            if (clause instanceof ASTBindingClause) {
                IASTBindingClause bindings = (IASTBindingClause)clause;
                for (int i = bindings.getBindingExprCount() - 1; i >= 0; i--) {
                    IStructuredDocumentRegion var = bindings.getBindingVariable(i);
                    if (var != null) {
                        vars.add(ASTHelper.variableNameAsString(var));
                    }
                }
            }
            lastBindingIndex--;
        }

        super.getInScopeVariables(vars, child);
    }

    @Override
    public void staticCheck(IStructuredDocument document, IValidator validator, IReporter reporter) {
        if (returnExpr != null) {
            returnExpr.staticCheck(document, validator, reporter);
        }

        super.staticCheck(document, validator, reporter);
    }

}
