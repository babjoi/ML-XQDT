/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse public abstract License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.model.ast;

import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.IASTFunctionCall;

/**
 * Factory for XQuery AST nodes.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public abstract class ASTNodeFactory {

    /**
     * @return
     */
    public abstract IASTModule newModule();

    /**
     * @return
     */
    public abstract IASTLiteral newLiteral();

    /**
     * @return
     */
    public abstract IASTVarRef newVarRef();

    /**
     * @return
     */
    public abstract IASTFLWOR newFLOWR();

    /**
     * @return
     */
    public abstract IASTSequenceType newSequenceType();

    /**
     * @return
     */
    public abstract IASTFunctionCall newFunctionCall();

    /**
     * @return
     */
    public abstract IASTFunctionDecl newFunctionDecl();

    /**
     * @return
     */
    public abstract IASTDirElement newDirElement();

    /**
     * @return
     */
    public abstract IASTDirAttribute newDirAttribute();

    /**
     * @return
     */
    public abstract IASTVarDecl newVariableDecl();

    /**
     * @return
     */
    public abstract IASTIf newIf();

    /**
     * @return
     */
    public abstract IASTTypeSwitch newTypeswitch();

    /**
     * @return
     */
    public abstract IASTQuantified newQuantified();

    /**
     * @return
     */
    public abstract IASTNamespaceDecl newNamespaceDecl();

    /**
     * @return
     */
    public abstract IASTApply newApply();

    /**
     * @return
     */
    public abstract IASTBindingClause newBindingClause();

    /**
     * @return
     */
    public abstract IASTExprSingleClause newExprSingleClause();

    /**
     * @return
     */
    public abstract IASTOrderByClause newOrderByClause();

    /**
     * @return
     */
    public abstract IASTInsert newInsertExpr();

    /**
     * @return
     */
    public abstract IASTDelete newDeleteExpr();

    /**
     * @return
     */
    public abstract IASTReplace newReplaceExpr();

    /**
     * @return
     */
    public abstract IASTRename newRenameExpr();

    /**
     * @return
     */
    public abstract IASTTransform newTransformExpr();

    /**
     * @return
     */
    public abstract IASTNameTest newNameTest();

    /**
     * @return
     */
    public abstract IASTPath newPath();

    /**
     * @return
     */
    public abstract IASTStep newStep();

    /**
     * @return
     */
    public abstract IASTKindTest newKindTest();

    /**
     * @return
     */
    public abstract IASTParentherized newParentherized();

    /**
     * @return
     */
    public abstract IASTContextItem newContextItem();

    /**
     * @return
     */
    public abstract IASTSingleType newSingleType();

    /**
     * @return
     */
    public abstract IASTCompPIConstructor newCompPIConstructor();

    /**
     * @return
     */
    public abstract IASTCompDocConstructor newCompDocConstructor();

    /**
     * @return
     */
    public abstract IASTCompElemConstructor newCompElemConstructor();

    /**
     * @return
     */
    public abstract IASTCompTextConstructor newCompTextConstructor();

    /**
     * @return
     */
    public abstract IASTCompCommentConstructor newCompCommentConstructor();

    /**
     * @return
     */
    public abstract IASTCompAttrConstructor newCompAttrConstructor();

    /**
     * @return
     */
    public abstract IASTExtension newExtension();

    /**
     * @return
     */
    public abstract IASTValidate newValidate();

}