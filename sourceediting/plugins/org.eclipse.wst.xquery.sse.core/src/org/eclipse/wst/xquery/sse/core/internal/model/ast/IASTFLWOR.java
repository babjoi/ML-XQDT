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

import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTClause;

/**
 * FLWOR expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTFLWOR extends IASTNode {

    /**
     * Set clause at given index
     * 
     * @param index
     * @param region
     */
    public void setClause(int index, IASTClause clause);

    /**
     * Gets clause at given index
     * 
     * @param index
     */
    public ASTClause getClause(int index);

    /**
     * Gets the number of clauses.
     * 
     * @return
     */
    public int getClauseCount();

    /**
     * Gets return expression
     */
    public IASTNode getReturnExpr();

    /**
     * @param returnExpr
     */
    public void setReturnExpr(IASTNode expr);

}