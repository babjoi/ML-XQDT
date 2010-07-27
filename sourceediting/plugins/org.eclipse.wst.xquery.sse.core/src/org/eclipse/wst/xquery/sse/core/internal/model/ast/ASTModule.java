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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xquery.sse.core.internal.XQueryMessages;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.ModuleDeclStructuredDocumentRegion;

/**
 * XQuery module declaration
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class ASTModule extends ASTParentNode {

	// State

	/** Version text region */
	protected ITextRegion versionTextRegion;

	/** Encoding text region */
	protected ITextRegion encodingTextRegion;

	/** Module namespace prefix (library only) */
	protected ITextRegion namespacePrefixTextRegion;

	/** Module declaration (library only) */
	protected ModuleDeclStructuredDocumentRegion moduleSDRegion;

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

	public void setModuleDeclStructuredDocumentRegion(
			ModuleDeclStructuredDocumentRegion region) {
		moduleSDRegion = region;
	}

	public void setNamespacePrefixRegion(ITextRegion version) {
		this.namespacePrefixTextRegion = version;
	}

	public String getModuleNamespace() {
		if (moduleSDRegion != null) {
			try {
				ITextRegion region = moduleSDRegion.getNamespace();
				if (region != null) {
					// Return namespace without surrounding ""

					return moduleSDRegion.getParentDocument().get(
							region.getStart() + region.getStart() + 1,
							region.getLength() - 2);
				}
			} catch (BadLocationException e) {
				// Ignore..
				return null;
			}
		}

		return null;
	}

	/**
	 * Gets the module prefix or null is none
	 * @return
	 */
	public String getModulePrefix() {
		if (moduleSDRegion != null) { 
			ITextRegion region = moduleSDRegion.getNamespacePrefix();
			if (region != null) {
				return moduleSDRegion.getFullText(region).trim();
			}
		}

		return null;
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
		appendChildASTNodeAt(decl);
		functionDecls.put(name, decl);
	}

	/**
	 * Add a new variable declaration
	 * 
	 * @param region
	 */
	public void addVariableDecl(String name, ASTVarDecl decl) {
		appendChildASTNodeAt(decl);
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
		return getChildASTNodeAt(getChildASTNodesCount());
	}

	/**
	 * @param queryBody
	 */
	public void setQueryBody(IASTNode expr) {
		setChildASTNodeAt(getChildASTNodesCount(), expr);
	}

	// Overrides

	@Override
	public boolean staticCheck(IStructuredDocument document,
			IValidator validator, IReporter reporter) {
		// ModuleDecl: The URILiteral must be of nonzero length [err:XQST0088]
		String moduleNS = getModuleNamespace();

		if (moduleNS != null && moduleNS.length() == 0) {
			ASTHelper.reportError(moduleSDRegion,
					moduleSDRegion.getNamespace(),
					XQueryMessages.errorXQST0088_UI_, validator, reporter);
		}
		
		// ModuleDecl: The namespace prefix specified in a module declaration must not be xml or xmlns [err:XQST0070]
		final String modulePrefix = getModulePrefix();
		if (modulePrefix != null && (modulePrefix.equals("xml") || modulePrefix.equals("xmlns")))
			 
				ASTHelper.reportError(moduleSDRegion,
						moduleSDRegion.getNamespacePrefix(),
						XQueryMessages.errorXQST0070_MD_UI_, validator, reporter);

		return super.staticCheck(document, validator, reporter);
	}

	@Override
	public int getType() {
		return MODULE;
	}

}
