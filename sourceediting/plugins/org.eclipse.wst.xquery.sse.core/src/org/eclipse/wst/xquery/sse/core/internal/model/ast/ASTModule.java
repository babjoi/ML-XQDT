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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;

/**
 * XQuery module declaration
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTModule extends ASTParentNode {

	// State

	/** Version text region */
	protected ITextRegion versionTextRegion;

	/** Encoding text region */
	protected ITextRegion encodingTextRegion;

	/** Module namespace prefix (library only) */
	protected ITextRegion namespacePrefixTextRegion;

	/** Module namespace (library only) */
	protected ITextRegion namespaceTextRegion;

	/** List of function declaration, indexed by their name */
	// TODO: namespaces.. 
	protected Map<String, ASTFunctionDecl> functionDecls;
	
	/** List of variable declarations, indexed by their name */
	protected Map<String, ASTVarDecl> variableDecls;

	// Constructors

	protected ASTModule() {
		functionDecls = new HashMap<String, ASTFunctionDecl>();
		variableDecls = new HashMap<String, ASTVarDecl>();
	}

	// Methods

	public void setVersionRegion(ITextRegion version) {
		this.versionTextRegion = version;
	}

	public void setEncodingRegion(ITextRegion encoding) {
		this.encodingTextRegion = encoding;
	}

	public void setNamespacePrefixRegion(ITextRegion version) {
		this.namespacePrefixTextRegion = version;
	}

	public void setNamespaceRegion(ITextRegion encoding) {
		this.namespaceTextRegion = encoding;
	}

	/**
	 * Get the function of the given name, or null if none exist
	 * 
	 * @param name
	 * @return
	 */
	public ASTFunctionDecl getFunctionDecl(String name) {
		return functionDecls.get(name);
	}

	/**
	 * Add new function declaration of the given name. Silently replace any
	 * existing declarations of the same name
	 * 
	 * @param name
	 * @param decl
	 */
	public void addFunctionDecl(String name, ASTFunctionDecl decl) {
		functionDecls.put(name, decl);
	}
	
	/**
	 * Add a new variable declaration
	 * @param region
	 */
	public void addVariableDecl(String name, ASTVarDecl decl)
	{
		variableDecls.put(name, decl);
	}
	
	/**
	 * Get the variable of the given name, or null if none exist
	 * 
	 * @param name
	 * @return
	 */
	public ASTVarDecl getVariableDecl(String name) {
		return variableDecls.get(name);
	}

	/**
	 * @return Query body
	 */
	public IASTNode getQueryBody() {
		return getChildASTNodeAt(0);
	}

	/**
	 * @param queryBody
	 */
	public void setQueryBody(IASTNode expr) {
		setChildASTNodeAt(0, expr);

	}

	// Overrides

	@Override
	public int getType() {
		return MODULE;
	}

}
