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
 * Conditional expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTIf extends IASTNode {

    /**
     * Set condition expression
     */
    public void setConditionExpr(IASTNode node);

    /**
     * @return
     */
    public IASTNode getConditionExpr();

    /**
     * Set then expression
     */
    public void setThenExpr(IASTNode node);

    /**
     * @return
     */
    public IASTNode getThenExpr();

    /**
     * Set else expression
     */
    public void setElseExpr(IASTNode node);

    /**
     * @return
     */
    public IASTNode getElseExpr();

}