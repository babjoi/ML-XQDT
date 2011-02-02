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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;

/**
 * Clause with multiple bindings of the form <tt>"$" VarName TypeDeclaration? "XXX" ExprSingle</tt>
 * 
 * <p>
 * Includes for binding clause, let binding clause, quantified binding clause, etc..
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface IASTBindingClause extends IASTClause {

    /**
     * @param index
     * @return
     */
    public IASTNode getTypeDeclaration(int index);

    /**
     * @param index
     * @param newTypeDecl
     */
    public void setTypeDeclaration(int index, IASTNode newTypeDecl);

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

    /**
     * @param index
     * @param currentSDRegion
     */
    public void setBindingVariable(int index, IStructuredDocumentRegion region);

    /**
     * 
     * @param index
     * @return
     */
    public IStructuredDocumentRegion getBindingVariable(int index);

    /**
     * @param index
     * @param currentSDRegion
     */
    public void setPositionalVar(int index, IStructuredDocumentRegion region);

    /**
     * 
     * @param index
     * @return
     */
    public IStructuredDocumentRegion getPositionalVar(int index);

}