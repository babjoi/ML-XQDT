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

import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * Typeswitch expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTTypeSwitch extends IASTNode {

    /**
     * Gets the operand expression
     * 
     * @return
     */
    public IASTNode getOperandExpr();

    /**
     * @param expr
     */
    public void setOperandExpr(IASTNode expr);

    /**
     * Gets the return expression of the default case
     */
    public IASTNode getDefaultCaseExpr();

    /**
     * @param expr
     */
    public void setDefaultCaseExpr(IASTNode expr);

    /**
     * @param region
     */
    public void setDefaultCaseVarname(XQueryStructuredDocumentRegion region);

    /**
     * @return
     */
    public XQueryStructuredDocumentRegion getDefaultCaseVarname();

    /**
     * @param i
     * @return
     */
    public XQueryStructuredDocumentRegion getCaseVarname(int i);

    /**
     * @param currentSDRegion
     */
    public void setCaseVarname(int index, XQueryStructuredDocumentRegion region);

    /**
     * @return The number of cases (not including the default case)
     */
    public int getCaseCount();

    /**
     * @param index
     * @return
     */
    public IASTNode getCaseExpr(int index);

    /**
     * @param index
     * @param expr
     */
    public void setCaseExpr(int index, IASTNode expr);

}