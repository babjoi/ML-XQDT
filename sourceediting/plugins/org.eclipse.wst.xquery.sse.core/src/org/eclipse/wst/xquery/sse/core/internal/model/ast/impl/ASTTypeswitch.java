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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTTypeSwitch;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * Typeswitch expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTTypeswitch extends ASTParentNode implements IASTTypeSwitch {

    // State
    /**
     * Variable names (includes '$'). The first value represents the default case variable name
     */
    protected List<XQueryStructuredDocumentRegion> varnames;

    // Constructors

    public ASTTypeswitch() {
        varnames = new ArrayList<XQueryStructuredDocumentRegion>(5);
        ASTHelper.ensureCapacity(1, varnames);
    }

    // Methods

    /**
     * Gets the operand expression
     * 
     * @return
     */
    public IASTNode getOperandExpr() {
        return getChildASTNodeAt(0);
    }

    /**
     * @param expr
     */
    public void setOperandExpr(IASTNode expr) {
        setChildASTNodeAt(0, expr);
    }

    /**
     * Gets the return expression of the default case
     */
    public IASTNode getDefaultCaseExpr() {
        return getChildASTNodeAt(1);
    }

    /**
     * @param expr
     */
    public void setDefaultCaseExpr(IASTNode expr) {
        setChildASTNodeAt(1, expr);
    }

    /**
     * @param region
     */
    public void setDefaultCaseVarname(XQueryStructuredDocumentRegion region) {
        setCaseVarname(-1, region);
    }

    /**
     * @return
     */
    public XQueryStructuredDocumentRegion getDefaultCaseVarname() {
        return getCaseVarname(-1);
    }

    /**
     * @param i
     * @return
     */
    public XQueryStructuredDocumentRegion getCaseVarname(int i) {
        if (varnames.size() > i + 1) {
            return varnames.get(i + 1);
        }

        return null;
    }

    /**
     * @param currentSDRegion
     */
    public void setCaseVarname(int index, XQueryStructuredDocumentRegion region) {
        ASTHelper.ensureCapacity(index + 1, varnames);
        varnames.set(index + 1, region);

    }

    /**
     * @return The number of cases (not including the default case)
     */
    public int getCaseCount() {
        return getChildASTNodesCount() - 2;
    }

    /**
     * @param index
     * @return
     */
    public IASTNode getCaseExpr(int index) {
        return getChildASTNodeAt(index + 2); // skipping operand and default
                                             // case expressions
    }

    /**
     * @param index
     * @param expr
     */
    public void setCaseExpr(int index, IASTNode expr) {
        setChildASTNodeAt(index + 2, expr);

    }

    // Overrides

    @Override
    public int getType() {
        return TYPESWITCH;
    }

    @Override
    protected void getInScopeVariables(List<String> vars, IASTNode child) {
        // Identify which case
        if (getDefaultCaseExpr() == child) {
            IStructuredDocumentRegion var = getDefaultCaseVarname();
            if (var != null) {
                vars.add(ASTHelper.variableNameAsString(var));
            }
        } else {
            for (int i = getCaseCount() - 1; i >= 0; i--) {
                if (getCaseExpr(i) == child) {
                    IStructuredDocumentRegion var = getCaseVarname(i);
                    if (var != null) {
                        vars.add(ASTHelper.variableNameAsString(var));
                    }
                    break;
                }
            }
        }

        super.getInScopeVariables(vars, child);
    }

}
