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
 * Expression with multiples clauses of the form
 * <tt>"$" VarName TypeDeclaration? "XXX" ExprSingle</tt>
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public abstract class ASTClauses extends ASTParentNode {

	// State

	/** Binding variables */
	protected List<IStructuredDocumentRegion> bindingVars;

	/** Binding expressions */
	protected List<IASTNode> bindingExprs;

	// Constructor

	ASTClauses() {

		bindingExprs = new ArrayList<IASTNode>(1);
		bindingVars = new ArrayList<IStructuredDocumentRegion>(1);
	}

	// Methods

	/**
	 * @param index
	 * @return
	 */
	public IASTNode getTypeDeclaration(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param index
	 * @param newTypeDecl
	 */
	public void setTypeDeclaration(int index, IASTNode newTypeDecl) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param index
	 * @return
	 */
	public IASTNode getBindingExpr(int index) {
		return bindingExprs.size() > index ? bindingExprs.get(index) : null;
	}

	/**
	 * Gets the number of binding expressions
	 * 
	 * @return
	 */
	public int getBindingExprCount() {
		return bindingExprs.size();
	}

	/**
	 * @param index
	 * @param newExpr
	 */
	public void setBindingExpr(int index, IASTNode expr) {
		ASTHelper.ensureCapacity(index, bindingExprs);
		if (expr != null)
			expr.setASTParent(this);
		
		bindingExprs.set(index, expr);

	}

	/**
	 * @param index
	 * @param currentSDRegion
	 */
	public void setBindingVariable(int index, IStructuredDocumentRegion region) {
		ASTHelper.ensureCapacity(index, bindingVars);
		bindingVars.set(index, region);
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public IStructuredDocumentRegion getBindingVariable(int index) {
		return bindingVars.size() > index ? bindingVars.get(index) : null;
	}

}
