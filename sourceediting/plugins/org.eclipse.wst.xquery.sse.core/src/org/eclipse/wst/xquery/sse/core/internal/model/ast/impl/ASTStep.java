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

import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTStep;

/**
 * XQuery 1.0 step expression.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTStep extends ASTParentNode implements IASTStep {

	// Methods

	/**
	 * @return
	 */
	public IASTNode getPrimaryExpr() {
		return getChildASTNodeAt(0);
	}

	/**
	 * @param primary
	 */
	public void setPrimaryExpr(IASTNode node) {
		setChildASTNodeAt(0, node);

	}

	/**
	 * @return
	 */
	public IASTNode getNodeTest() {
		return getChildASTNodeAt(0);
	}

	/**
	 * @param node
	 */
	public void setNodeTest(IASTNode node) {
		setChildASTNodeAt(0, node);
	}

	// Overrides

	@Override
	public int getType() {
		return STEP;
	}

}
