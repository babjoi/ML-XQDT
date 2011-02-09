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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTHelper;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTBindingClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;

/**
 * Clause with multiple bindings of the form <tt>"$" VarName TypeDeclaration? "XXX" ExprSingle</tt>
 * 
 * <p>
 * Includes for binding clause, let binding clause, quantified binding clause, etc..
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTBindingClause extends ASTClause implements IASTBindingClause {

    // State

    /** Binding variables */
    protected List<IStructuredDocumentRegion> bindingVars;

    /** Binding positional variables (only for 'for' clause) */
    protected List<IStructuredDocumentRegion> posVars;

    /** Binding expressions */
    protected List<IASTNode> bindingExprs;

    // Constructor

    ASTBindingClause() {
        bindingExprs = new ArrayList<IASTNode>(1);
        bindingVars = new ArrayList<IStructuredDocumentRegion>(1);
        posVars = new ArrayList<IStructuredDocumentRegion>(1);
    }

    // Methods

    /**
     * @param index
     * @return
     */
    public IASTNode getTypeDeclaration(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param index
     * @param newTypeDecl
     */
    public void setTypeDeclaration(int index, IASTNode newTypeDecl) {
        // TODO Auto-generated method stub

    }

    /**
     * @param index
     * @return
     */
    public IASTNode getBindingExpr(int index) {
        return bindingExprs.size() > index ? bindingExprs.get(index) : null;
    }

    /**
     * Gets the number of binding expressions
     * 
     * @return
     */
    public int getBindingExprCount() {
        return bindingExprs.size();
    }

    /**
     * @param index
     * @param newExpr
     */
    public void setBindingExpr(int index, IASTNode expr) {
        ASTHelper.ensureCapacity(index, bindingExprs);
        if (expr != null) {
            expr.setASTParent(this);
        }

        bindingExprs.set(index, expr);

    }

    /**
     * @param index
     * @param currentSDRegion
     */
    public void setBindingVariable(int index, IStructuredDocumentRegion region) {
        ASTHelper.ensureCapacity(index, bindingVars);
        bindingVars.set(index, region);
    }

    /**
     * 
     * @param index
     * @return
     */
    public IStructuredDocumentRegion getBindingVariable(int index) {
        return bindingVars.size() > index ? bindingVars.get(index) : null;
    }

    /**
     * @param index
     * @param currentSDRegion
     */
    public void setPositionalVar(int index, IStructuredDocumentRegion region) {
        ASTHelper.ensureCapacity(index, posVars);
        posVars.set(index, region);
    }

    /**
     * 
     * @param index
     * @return
     */
    public IStructuredDocumentRegion getPositionalVar(int index) {
        return posVars.size() > index ? posVars.get(index) : null;
    }

    // Overrides

    @Override
    protected void getInScopeVariables(List<String> vars, IASTNode child) {
        // Compute the last binding index to include
        int lastBindingIndex = 0;

        // Includes only the previous bindings
        while (lastBindingIndex < getChildASTNodesCount()) {
            if (getBindingExpr(lastBindingIndex) == child) {
                break;
            }
            lastBindingIndex++;
        }

        lastBindingIndex--;

        while (lastBindingIndex >= 0) {
            IStructuredDocumentRegion var = getBindingVariable(lastBindingIndex);

            if (var != null) {
                vars.add(ASTHelper.variableNameAsString(var));
            }

            var = getPositionalVar(lastBindingIndex);

            if (var != null) {
                vars.add(ASTHelper.variableNameAsString(var));
            }
            lastBindingIndex--;
        }

        super.getInScopeVariables(vars, child);
    }

    @Override
    public void staticCheck(IStructuredDocument document, IValidator validator, IReporter reporter) {

        for (int i = getBindingExprCount() - 1; i >= 0; i--) {
            IASTNode expr = getBindingExpr(i);
            if (expr != null) {
                expr.staticCheck(document, validator, reporter);
            }
        }

        super.staticCheck(document, validator, reporter);
    }

}
