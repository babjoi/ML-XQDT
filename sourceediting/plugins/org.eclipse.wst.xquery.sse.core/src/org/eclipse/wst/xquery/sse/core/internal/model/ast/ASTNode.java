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
 * Represent an XQuery AST node.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public abstract class ASTNode implements IASTNode {

	// State

	/** Parent node */
	protected IASTNode parent;

	// Implements IASTNode

	public abstract int getType();

	public IASTNode getASTParent() {
		return parent;
	}

	public void setASTParent(IASTNode parent) {
		this.parent = parent;
	}
 

	public void setChildASTNodeAt(int index, IASTNode newChild) {
		throw new IllegalStateException("Illegal call to setChildNodeAt on a leaf node");
	}

	public IASTNode getChildASTNodeAt(int i) {
		throw new IllegalStateException("Illegal call to setChildNodeAt on a leaf node");
	}

	public void removeChildASTNodesAfter(int index) {
		throw new IllegalStateException("Illegal call to removeChildNodesAfter on a leaf node");
	}

	public int getChildASTNodesCount() {
		return 0;
	}

	// Debugging

	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(0, builder);
		return builder.toString();
	}

	protected void toString(int indent, StringBuilder builder) {
		builder.append("\n");
		for (int i = 0; i < indent; i++)
			builder.append(' ');
		builder.append("[").append(getClass().getSimpleName());
		if (getChildASTNodesCount() > 0) {
			for (int i = 0; i < getChildASTNodesCount(); i++) {
				if (getChildASTNodeAt(i) != null) {
					((ASTNode) getChildASTNodeAt(i)).toString(indent + 2, builder);
				}
			}
			builder.append("\n");
		}
		builder.append("]");
	}

}
