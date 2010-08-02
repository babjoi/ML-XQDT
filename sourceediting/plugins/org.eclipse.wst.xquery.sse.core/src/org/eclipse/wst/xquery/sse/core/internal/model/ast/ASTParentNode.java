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

/**
 * AST Node accepting child nodes
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */ 
public abstract class ASTParentNode extends ASTNode {

	// State

	/** List of child nodes */
	protected List<IASTNode> children;

	// Constructor

	protected ASTParentNode() {
		children = new ArrayList<IASTNode>(3);
	}

	// Overrides

	@Override
	public void removeChildASTNodesAfter(int index) {
		ASTHelper.removeAfter(children, index);
	}

	@Override
	public void setChildASTNodeAt(int index, IASTNode newChild) {
		ASTHelper.setChildASTNodeAt(children, this, index, newChild);
	}

	@Override
	public IASTNode getChildASTNodeAt(int i) {
		return ASTHelper.getChildASTNodeAt(children, i);
	}

	@Override
	public int getChildASTNodesCount() {
		return ASTHelper.getChildASTNodesCount(children);
	} 

	
}
