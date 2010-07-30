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

import org.eclipse.wst.xquery.sse.core.internal.model.ast.xml.ASTDirAttribute;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.xml.ASTDirElement;

/**
 * XQuery AST node factory
 * 
 * TODO: eclipse extension point.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTNodeFactory {

	/**
	 * @return
	 */
	public ASTModule newModule() {
		return new ASTModule();
	}

	/**
	 * @return
	 */
	public ASTLiteral newLiteral() {
		return new ASTLiteral();
	}

	/**
	 * @return
	 */
	public ASTVarRef newVarRef() {
		return new ASTVarRef();
	}

	/**
	 * @return
	 */
	public ASTFLWOR newFLOWR() {
		return new ASTFLWOR();
	}

	/**
	 * @return
	 */
	public ASTSequenceType newSequenceType() {
		return new ASTSequenceType();
	}

	/**
	 * @return
	 */
	public ASTFunctionCall newFunctionCall() {
		return new ASTFunctionCall();
	}

	/**
	 * @return
	 */
	public ASTFunctionDecl newFunctionDecl() {
		return new ASTFunctionDecl();
	}

	/**
	 * @return
	 */
	public ASTDirElement newDirElement() {
		return new ASTDirElement();
	}

	/**
	 * @return
	 */
	public ASTDirAttribute newDirAttribute() {
		return new ASTDirAttribute();
	}

	/**
	 * @return
	 */
	public ASTVarDecl newVariableDecl() {
		return new ASTVarDecl();
	}

	/**
	 * @return
	 */
	public ASTIf newIf() {
		return new ASTIf();
	}

	/**
	 * @return
	 */
	public ASTTypeswitch newTypeswitch() {
		return new ASTTypeswitch();
	}

	/**
	 * @return
	 */
	public ASTQuantified newQuantified() {
		return new ASTQuantified();
	}

	/**
	 * @return
	 */
	public ASTNamespaceDecl newNamespaceDecl() {
		return new ASTNamespaceDecl();
	}

	/**
	 * @return
	 */
	public ASTApply newApply() {
		return new ASTApply();
	}

	/**
	 * @return
	 */
	public ASTBindingClause newBindingClause() {
		return new ASTBindingClause();
	}

	/**
	 * @return
	 */
	public ASTExprSingleClause newExprSingleClause() {
		return new ASTExprSingleClause();
	}

	/**
	 * @return
	 */
	public ASTOrderByClause newOrderByClause() {
		return new ASTOrderByClause();
	}

}
