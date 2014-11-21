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
package org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.update;

import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTInsert;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTParentNode;

/**
 * XQuery Update 1.0 insert expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTInsert extends ASTParentNode implements IASTInsert {

	@Override
	public int getType() {
		return XUINSERT;
	}

	// Methods
	
	/**
	 * Get source expression
	 */
	public IASTNode getSourceExpr() {
		return getChildASTNodeAt(0);
	}
	
	
	/**
	 * Set source expression
	 */
	public void setSourceExpr(IASTNode node) {
		setChildASTNodeAt(0, node);
	}
	
	/**
	 * @return target expression
	 */
	public IASTNode getTargetExpr() {
		return getChildASTNodeAt(1);
	}
	
	
	/**
	 * Set target expression
	 */
	public void setTargetExpr(IASTNode node) {
		setChildASTNodeAt(1, node);
	}

}
