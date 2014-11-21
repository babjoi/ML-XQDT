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
package org.eclipse.wst.xquery.sse.core.internal.model.ast.impl;

import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTNodeFactory;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTDelete;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTDirAttribute;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTDirElement;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTFunctionCall;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTInsert;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNameTest;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNamespaceDecl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTOperator;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTOrderByClause;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTRename;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTReplace;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTSequenceType;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTSingleType;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTTransform;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTValidate;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.update.ASTDelete;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.update.ASTInsert;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.update.ASTRename;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.update.ASTReplace;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.update.ASTTransform;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.xml.ASTDirAttribute;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.xml.ASTDirComment;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.xml.ASTDirElement;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.xml.ASTDirPI;

/**
 * XQuery AST node factory
 * 
 * TODO: eclipse extension point.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ImplASTNodeFactory extends ASTNodeFactory {

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
    public IASTSequenceType newSequenceType() {
        return new ASTSequenceType();
    }

    /**
     * @return
     */
    public IASTFunctionCall newFunctionCall() {
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
    public IASTDirElement newDirElement() {
        return new ASTDirElement();
    }

    /**
     * @return
     */
    public IASTDirAttribute newDirAttribute() {
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
    public IASTNamespaceDecl newNamespaceDecl() {
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
    public IASTOrderByClause newOrderByClause() {
        return new ASTOrderByClause();
    }

    /**
     * @return
     */
    public IASTInsert newInsertExpr() {
        return new ASTInsert();
    }

    /**
     * @return
     */
    public IASTDelete newDeleteExpr() {
        return new ASTDelete();
    }

    /**
     * @return
     */
    public IASTReplace newReplaceExpr() {
        return new ASTReplace();
    }

    /**
     * @return
     */
    public IASTRename newRenameExpr() {
        return new ASTRename();
    }

    /**
     * @return
     */
    public IASTTransform newTransformExpr() {
        return new ASTTransform();
    }

    /**
     * @return
     */
    public IASTNameTest newNameTest() {
        return new ASTNameTest();
    }

    /**
     * @return
     */
    public ASTPath newPath() {
        return new ASTPath();
    }

    /**
     * @return
     */
    public ASTStep newStep() {
        return new ASTStep();
    }

    /**
     * @return
     */
    public ASTKindTest newKindTest() {
        return new ASTKindTest();
    }

    /**
     * @return
     */
    public ASTParentherized newParentherized() {
        return new ASTParentherized();
    }

    /**
     * @return
     */
    public ASTContextItem newContextItem() {
        return new ASTContextItem();
    }

    /**
     * @return
     */
    public IASTSingleType newSingleType() {
        return new ASTSingleType();
    }

    /**
     * @return
     */
    public ASTCompPIConstructor newCompPIConstructor() {
        return new ASTCompPIConstructor();
    }

    /**
     * @return
     */
    public ASTCompDocConstructor newCompDocConstructor() {
        return new ASTCompDocConstructor();
    }

    /**
     * @return
     */
    public ASTCompElemConstructor newCompElemConstructor() {
        return new ASTCompElemConstructor();
    }

    /**
     * @return
     */
    public ASTCompTextConstructor newCompTextConstructor() {
        return new ASTCompTextConstructor();
    }

    /**
     * @return
     */
    public ASTCompCommentConstructor newCompCommentConstructor() {
        return new ASTCompCommentConstructor();
    }

    /**
     * @return
     */
    public ASTCompAttrConstructor newCompAttrConstructor() {
        return new ASTCompAttrConstructor();
    }

    /**
     * @return
     */
    public ASTExtension newExtension() {
        return new ASTExtension();
    }

    /**
     * @return
     */
    public IASTValidate newValidate() {
        return new ASTValidate();
    }

    @Override
    public IASTOperator newOperator(int operatorType) {
        return new ASTOperator(operatorType);
    }

    @Override
    public IASTNode newDirPI() {
        return new ASTDirPI();
    }

    @Override
    public IASTNode newDirComment() {
        return new ASTDirComment();
    }

}
