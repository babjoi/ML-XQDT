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
 * Quantified expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTQuantified extends IASTNode {

    /**
     * Set binding clause
     * 
     * @param index
     * @param region
     */
    public void setBindingClause(IASTBindingClause clause);

    /**
     * Get binding clause
     * 
     * @param index
     */
    public IASTBindingClause getBindingClause();

    /**
     * @param expr
     */
    public void setSatisfiesExpr(IASTNode expr);

    /**
     * @return satisfies expression
     */
    public IASTNode getSatisfiesExpr();

}