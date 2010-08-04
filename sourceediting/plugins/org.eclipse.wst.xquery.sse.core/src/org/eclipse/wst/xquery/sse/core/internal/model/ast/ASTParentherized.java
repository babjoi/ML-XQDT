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
 * XQuery 1.0 parentherized expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTParentherized extends ASTParentNode {

	// Methods

	/**
	 * @return parentherized expression
	 */
	public IASTNode getExpr() {
		return getChildASTNodeAt(0);
	}

	/**
	 * Sets parentherized expression
	 */
	public void setExpr(IASTNode node) {
		setChildASTNodeAt(0, node);
	}

	// Overrides

	@Override
	public int getType() {
		return PARENTHERIZED;
	}

}
