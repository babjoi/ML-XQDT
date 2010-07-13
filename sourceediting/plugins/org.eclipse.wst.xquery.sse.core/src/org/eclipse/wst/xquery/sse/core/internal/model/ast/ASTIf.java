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
 * If expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTIf extends ASTParentNode {

	// Methods
	
	/**
	 * Set condition expression
	 */
	public void setConditionExpr(IASTNode node)
	{
		setChildASTNodeAt(0, node);
	}
	
	/**
	 * @return
	 */
	public IASTNode getConditionExpr() {
		return getChildASTNodeAt(0);
	}
	
	/**
	 * Set then expression
	 */
	public void setThenExpr(IASTNode node)
	{
		setChildASTNodeAt(1, node);
	}
	
	/**
	 * @return
	 */
	public IASTNode getThenExpr() {
		return getChildASTNodeAt(1);
	}
	
	/**
	 * Set else expression
	 */
	public void setElseExpr(IASTNode node)
	{
		setChildASTNodeAt(2, node);
	}
	
	/**
	 * @return
	 */
	public IASTNode getElseExpr() {
		return getChildASTNodeAt(2);
	}
	
	// Overrides

	@Override
	public int getType() {
		return IF;
	}

	

}
