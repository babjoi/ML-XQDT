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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/**
 * Represent an XQuery AST node.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public interface IASTNode {

	// AST node types

	public static final int MODULE = 1;
	public static final int OPERATOR = 2;
	public static final int LITERAL = 3;
	public static final int VARREF = 4;
	public static final int FLWOR = 5;
	public static final int SEQUENCETYPE = 6;
	public static final int FUNCTIONCALL = 7;
	public static final int FUNCTIONDECL = 8;
	public static final int DIRELEMENT = 9;
	public static final int DIRATTRIBUTE = 10;
	public static final int VARDECL = 11;
	public static final int IF = 12;
	public static final int TYPESWITCH = 13;
	public static final int QUANTIFIED = 14;

	/** Gets the AST node type */
	public int getType();

	/**
	 * Gets parent AST node
	 * 
	 * @return the parent AST node or null if this is the root node.
	 */
	public IASTNode getASTParent();
	
	/**
	 * Append the given child node after the last child node
	 * @param newChild
	 */
	public void appendChildASTNodeAt(IASTNode newChild);


	/**
	 * @param index
	 * @param newChild
	 */
	public void setChildASTNodeAt(int index, IASTNode newChild);

	/**
	 * @param i
	 * @return
	 */
	public IASTNode getChildASTNodeAt(int i);
	
	/**
	 * @param index
	 */
	public void removeChildASTNodesAfter(int index);

	/** Gets number of child nodes */
	public int getChildASTNodesCount();

	/**
	 * Set the parent node
	 */
	public void setASTParent(IASTNode parent);
	
	/**
	 * Gets the previous sibling
	 *
	 */
	public IASTNode getPreviousASTNodeSibling();
	
	/**
	 * Gets the following sibling
	 *
	 */
	public IASTNode getFollowingASTNodeSibling();
	
	/** 
	 * Perform static checking of this node only (not recursive).
	 * 
	 * @param document  
	 * @param validator  
	 * @param reporter for errors
	 * @return true if this node is valid, false otherwise.
	 */ 
	public boolean staticCheck(IStructuredDocument document, IValidator validator, IReporter reporter);
}
