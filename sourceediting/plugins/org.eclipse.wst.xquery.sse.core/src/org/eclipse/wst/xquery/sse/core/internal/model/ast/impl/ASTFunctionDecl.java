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
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTHelper;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTFunctionDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;

/**
 * Function declaration
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTFunctionDecl extends ASTNode implements IASTFunctionDecl {

    // State

    /** Function raw name */
    private String name;

    /** Function Body */
    private IASTNode body;

    /** Parameter name */
    private List<IStructuredDocumentRegion> paramNames;

    // Constructors

    public ASTFunctionDecl() {
        paramNames = new ArrayList<IStructuredDocumentRegion>(2);
    }

    // Methods

    /**
     * Gets the function raw name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the function raw name as a {@link IStructuredDocumentRegion}
     * 
     * <p>
     * Valid only when the model is syntactically correct.
     */
    public IStructuredDocumentRegion getNameStructuredDocumentRegion() {
        return ASTHelper.skipXQueryComment(firstRegion);
    }

    /**
     * Sets the function name
     * 
     * @param object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the function body expression
     * 
     * @return
     */
    public IASTNode getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(IASTNode body) {
        if (this.body != null) {
            this.body.setASTParent(null);
        }

        this.body = body;
        if (this.body != null) {
            this.body.setASTParent(this);
        }
    }

    /**
     * @param index
     * @param region
     */
    public void setParamName(int index, IStructuredDocumentRegion region) {
        ASTHelper.ensureCapacity(index, paramNames);
        paramNames.set(index, region);
    }

    /**
     * @param index
     * @param region
     */
    public int getParamCount() {
        return paramNames.size();
    }

    /**
     * 
     * @param index
     * @return
     */
    public IStructuredDocumentRegion getParamNameAt(int index) {
        if (paramNames.size() > index) {
            return paramNames.get(index);
        }
        return null;
    }

    /**
     * @param i
     */
    public void removeParamNamesAfter(int i) {
        ASTHelper.removeAfter(paramNames, i);
    }

    // Overrides

    @Override
    public int getType() {
        return FUNCTIONDECL;
    }

    @Override
    protected void getInScopeVariables(List<String> vars, IASTNode child) {
        // Include parameters..
        for (int i = getParamCount() - 1; i >= 0; i--) {
            IStructuredDocumentRegion var = getParamNameAt(i);
            if (var != null) {
                vars.add(ASTHelper.variableNameAsString(var));
            }
        }

        super.getInScopeVariables(vars, child);
    }

}
