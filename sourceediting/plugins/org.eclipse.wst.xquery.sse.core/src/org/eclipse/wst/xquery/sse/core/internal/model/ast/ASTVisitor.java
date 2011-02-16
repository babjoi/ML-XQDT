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
 * A visitor for abstract syntax trees.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public abstract class ASTVisitor {

    /**
     * Creates a new AST visitor instance.
     */
    public ASTVisitor() {
    }

    /**
     * Visits the given AST node prior to the type-specific visit (before <code>visit</code>).
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if <code>visit(node)</code> should be called, and
     *         <code>false</code> otherwise.
     */
    public boolean preVisit(IASTNode node) {
        return true;
    }

    /**
     * Visits the given AST node following the type-specific visit (after <code>endVisit</code>).
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void postVisit(IASTNode node) {
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTModule node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTModule node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTVarRef node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTVarRef node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTApply node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTApply node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTBindingClause node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTBindingClause node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTCompAttrConstructor node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTCompAttrConstructor node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTCompCommentConstructor node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTCompCommentConstructor node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTCompDocConstructor node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTCompDocConstructor node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTCompElemConstructor node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTCompElemConstructor node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTCompPIConstructor node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTCompPIConstructor node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTCompTextConstructor node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTCompTextConstructor node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTContextItem node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTContextItem node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTDelete node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTDelete node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTDirAttribute node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTDirAttribute node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTDirComment node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTDirComment node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTDirElement node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTDirElement node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTDirPI node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTDirPI node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTExtension node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTExtension node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTFLWOR node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTFLWOR node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTFunctionDecl node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTFunctionDecl node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTIf node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTIf node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTInsert node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTInsert node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTKindTest node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTKindTest node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTLiteral node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTLiteral node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTNamespaceDecl node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTNamespaceDecl node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTNameTest node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTNameTest node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTOperator node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTOperator node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTOrderByClause node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTOrderByClause node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTParentherized node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTParentherized node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTPath node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTPath node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTQuantified node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTQuantified node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTRename node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTRename node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTReplace node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTReplace node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTSequenceType node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTSequenceType node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTSingleType node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTSingleType node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTStep node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTStep node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTTransform node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTTransform node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTTypeSwitch node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTTypeSwitch node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTValidate node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTValidate node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTVarDecl node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTVarDecl node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTExprSingleClause node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTExprSingleClause node) {
        // default implementation: do nothing
    }

    /**
     * Visits the given type-specific AST node.
     * <p>
     * The default implementation does nothing and return true. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     * @return <code>true</code> if the children of this node should be visited, and
     *         <code>false</code> if the children of this node should be skipped
     */
    public boolean visit(IASTFunctionCall node) {
        return true;
    }

    /**
     * End of visit the given type-specific AST node.
     * <p>
     * The default implementation does nothing. Subclasses may reimplement.
     * </p>
     * 
     * @param node
     *            the node to visit
     */
    public void endVisit(IASTFunctionCall node) {
        // default implementation: do nothing
    }

}
