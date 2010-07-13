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

import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;

/**
 * Quantified expression
 *
 * @author <a href="villard@us.ibm.com">Lionel Villard</a> 
 */
public class ASTQuantified extends ASTClauses {


	// Methods
	
	
	/**
	 * @param expr
	 */
	public void setSatisfiesExpr(IASTNode expr) {
		setChildASTNodeAt(0, expr);
	}

	/**
	 * @param returnExpr
	 */
	public IASTNode getSatisfiesExpr() {
		return getChildASTNodeAt(0);
	}

	// Overrides
	
	@Override
	public int getType() {
		return QUANTIFIED;
	}

}
