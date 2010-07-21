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
 * Global variable declaration
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class ASTVarDecl extends ASTNode {

	// State

	/** Variable expression (if any) */
	private IASTNode expr;

	/** Variable name */
	private IStructuredDocumentRegion name;

	// Constructors

	public ASTVarDecl() {
	}

	// Methods

	/**
	 * Return the variable initialization expression
	 * 
	 * @return
	 */
	public IASTNode getExpr() {
		return expr;
	}

	/**
	 * @param expr
	 */
	public void setExpr(IASTNode expr) {
		if (this.expr != null)
			this.expr.setASTParent(null);

		this.expr = expr;
		if (this.expr != null)
			this.expr.setASTParent(this);
	}

	/**
	 * @return
	 */
	public void setName(IStructuredDocumentRegion name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public IStructuredDocumentRegion getName() {
		return name;
	}

	// Overrides

	@Override
	public int getType() {
		return VARDECL;
	}

}
