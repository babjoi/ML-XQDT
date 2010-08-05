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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
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
	public static final int NAMESPACEDECL = 15;
	public static final int APPLY = 16;
	public static final int FORCLAUSE = 17;
	public static final int LETCLAUSE = 18;
	public static final int WHERECLAUSE = 19;
	public static final int ORDERBYCLAUSE = 20;
	public static final int QUANTIFIEDCLAUSE = 21;
	public static final int XUINSERT = 22;
	public static final int XUDELETE = 23;
	public static final int XUREPLACE = 24;
	public static final int XUTRANSFORM = 25;
	public static final int XURENAME = 26;
	public static final int NAMETEST = 27;
	public static final int PATH = 28;
	public static final int STEP = 29;
	public static final int KINDTEST = 30;
	public static final int PARENTHERIZED = 31;
	public static final int CONTEXTITEM = 32;
	public static final int SINGLETYPE = 33;
	public static final int COMPDOCCONSTRUCTOR = 34;
	public static final int COMPELEMCONSTRUCTOR = 35;
	public static final int COMPATTRCONSTRUCTOR = 36;
	public static final int COMPTEXTCONSTRUCTOR = 37;
	public static final int COMPCOMMENTCONSTRUCTOR = 38;
	public static final int COMPPICONSTRUCTOR = 39;
	

	/** Gets the AST node type */
	public int getType();

	/**
	 * Gets parent AST node
	 * 
	 * @return the parent AST node or null if this is the root node.
	 */
	public IASTNode getASTParent();

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
	 * Gets the list of error messages attached to this node
	 */
	public List<IMessage> getErrorMessages();

	/**
	 * Recursively perform static checking 
	 * 
	 * @param document
	 * @param validator
	 * @param reporter
	 *            for errors
	 */
	public void staticCheck(IStructuredDocument document,
			IValidator validator, IReporter reporter);
	
	/**
	 * Adds the list of in-scope variables
	 * @return
	 */
	public List<String> getInScopeVariables();
	
	// Debugging support
	
	/** Print the AST node to the given string builder */
	public void toString(int indent, StringBuilder builder);

	
		
}
