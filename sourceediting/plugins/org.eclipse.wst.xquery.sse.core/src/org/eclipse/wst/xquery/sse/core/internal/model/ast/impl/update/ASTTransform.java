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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTTransform;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTHelper;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTParentNode;

/**
 * XQuery Update 1.0 transform expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTTransform extends ASTParentNode implements IASTTransform {

	// State

	/** Binding variable names */
	protected List<String> bindingVars;

	// Constructors

	public ASTTransform() {
		bindingVars = new ArrayList<String>(1);
	}

	// Methods

	/**
	 * @return modify expression
	 */
	public IASTNode getModifyExpr() {
		return getChildASTNodeAt(0);
	}

	/**
	 * Set target expression
	 */
	public void setModifyExpr(IASTNode node) {
		setChildASTNodeAt(0, node);
	}

	/**
	 * @return return expression
	 */
	public IASTNode getReturnExpr() {
		return getChildASTNodeAt(1);
	}

	/**
	 * Set expression
	 */
	public void setReturnExpr(IASTNode node) {
		setChildASTNodeAt(1, node);
	}

	/**
	 * @return binding variable at given index
	 */
	public String getBindingVariable(int index) {
		return bindingVars.size() > index ? bindingVars.get(index) : null;
	}

	/**
	 * Set binding variable name at given index
	 */
	public void setBindingVariable(int index, String name) {
		ASTHelper.ensureCapacity(index, bindingVars);
		bindingVars.set(index, name);
	}

	/**
	 * @param index
	 * @return
	 */
	public IASTNode getBindingExpr(int index) {
		return getChildASTNodeAt(index + 2);
	}

	/**
	 * Gets the number of binding expressions
	 * 
	 * @return
	 */
	public int getBindingExprCount() {
		return getChildASTNodesCount() - 2;
	}

	/**
	 * @param index
	 * @param newExpr
	 */
	public void setBindingExpr(int index, IASTNode expr) {
		setChildASTNodeAt(index + 2, expr);

	}

	// Overrides

	@Override
	public int getType() {
		return XUTRANSFORM;
	}

	@Override
	protected void getInScopeVariables(List<String> vars, IASTNode child) {
		// Compute the last binding index to include
		int lastBindingIndex = 0;

		if (child == getChildASTNodeAt(0) || child == getChildASTNodeAt(1)) {
			lastBindingIndex = getChildASTNodesCount() - 1; // Includes all
		} else {
			// Includes only the previous binding

			while (lastBindingIndex < getChildASTNodesCount()) {
				if (getBindingExpr(lastBindingIndex) == child) {
					break;
				}
				lastBindingIndex++;
			}

			lastBindingIndex--;
		}

		for (int i = 0; i <= lastBindingIndex - 2; i++) {
			vars.add(getBindingVariable(i));
		}

		super.getInScopeVariables(vars, child);
	}

}
