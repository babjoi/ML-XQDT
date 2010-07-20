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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;

/**
 * For/Let expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class ASTFLWOR extends ASTClauses {

	// State

	/** Positional variables */
	protected List<IStructuredDocumentRegion> posVars; 

	// Constructors

	public ASTFLWOR() {
		posVars = new ArrayList<IStructuredDocumentRegion>(1);
	}

	// Methods
	 
	/**
	 * @param index
	 * @param region
	 */
	public void setPositionalVar(int index, IStructuredDocumentRegion region) {
		ASTHelper.ensureCapacity(index, posVars);
		posVars.set(index, region);
	}
  
	/**
	 * @param expr
	 */
	public void setReturnExpr(IASTNode expr) {
		setChildASTNodeAt(1, expr);
	}

	/**
	 * @return the return expression
	 */
	public IASTNode getReturnExpr() {
		return getChildASTNodeAt(1);
	}
	
	/**
	 * @return the where expression
	 */ 
	public IASTNode getWhereExpr() {
		return getChildASTNodeAt(0);
	}

	/**
	 * @param expr
	 */
	public void setWhereExpr(IASTNode expr) {
		setChildASTNodeAt(0, expr);
	}
	
	/**
	 * @param index
	 * @return
	 */
	public IASTNode getOrderSpecExpr(int index) {
		return getChildASTNodeAt(index + 2);
	}


	/**
	 * @param expr
	 */
	public void setOrderSpecExpr(int index, IASTNode expr) {
		setChildASTNodeAt(2 + index, expr);
	}
	
 
	// Overrides

	@Override
	public int getType() {
		return FLWOR;
	}


	

}
