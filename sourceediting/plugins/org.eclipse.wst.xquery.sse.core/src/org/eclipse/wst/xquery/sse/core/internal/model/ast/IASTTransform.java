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
package org.eclipse.wst.xquery.sse.core.internal.model.ast;


/**
 * XQuery Update 1.0 transform expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTTransform extends IASTNode {

    /**
     * @return modify expression
     */
    public IASTNode getModifyExpr();

    /**
     * Set target expression
     */
    public void setModifyExpr(IASTNode node);

    /**
     * @return return expression
     */
    public IASTNode getReturnExpr();

    /**
     * Set expression
     */
    public void setReturnExpr(IASTNode node);

    /**
     * @return binding variable at given index
     */
    public String getBindingVariable(int index);

    /**
     * Set binding variable name at given index
     */
    public void setBindingVariable(int index, String name);

    /**
     * @param index
     * @return
     */
    public IASTNode getBindingExpr(int index);

    /**
     * Gets the number of binding expressions
     * 
     * @return
     */
    public int getBindingExprCount();

    /**
     * @param index
     * @param newExpr
     */
    public void setBindingExpr(int index, IASTNode expr);

}