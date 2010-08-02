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
package org.eclipse.wst.xquery.sse.core.internal.model.ast.update;

import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTParentNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;

/**
 * XQuery Update 1.0 rename expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTRename extends ASTParentNode {

	// Overrides

	@Override
	public int getType() {
		return XURENAME;
	}

	// Methods

	/**
	 * @return target expression
	 */
	public IASTNode getTargetExpr() {
		return getChildASTNodeAt(0);
	}

	/**
	 * Set target expression
	 */
	public void setTargetExpr(IASTNode node) {
		setChildASTNodeAt(0, node);
	}
	
	/**
	 * @return new name expression
	 */
	public IASTNode getNewNameExpr() {
		return getChildASTNodeAt(1);
	}

	/**
	 * Set new name expression
	 */
	public void setNewNameExpr(IASTNode node) {
		setChildASTNodeAt(1, node);
	}

}
