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
package org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.xml;

import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xml.core.internal.document.ProcessingInstructionImpl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTVisitor;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTDirPI;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.impl.ASTHelper;
import org.w3c.dom.Node;

/**
 * Direct PI constructor
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTDirPI extends ProcessingInstructionImpl implements IASTNode, IASTDirPI {

    // State

    /** AST parent node (which could be a non-xml parent) */
    protected IASTNode parentAST;

    // Constructors

    public ASTDirPI() {
    }

    /**
     * @param astDirDocument
     */
    public ASTDirPI(ASTDirDocument owner) {
        setOwnerDocument(owner);
    }

    // Overrides

    // Implements IASTNode

    public IASTNode getChildASTNodeAt(int i) {
        return null;
    }

    public int getChildASTNodesCount() {
        return 0;
    }

    public IASTNode getASTParent() {
        return parentAST;
    }

    public void setASTParent(IASTNode parent) {
        parentAST = parent;
        if (parent instanceof Node) {
            setParentNode((Node)parent);
        }
    }

    public int getType() {
        return DIRPI;
    }

    public void removeChildASTNodesAfter(int index) {

    }

    public void setChildASTNodeAt(int index, IASTNode newChild) {

    }

    public IASTNode getPreviousASTNodeSibling() {
        return ASTHelper.getPreviousASTNodeSibling(this);
    }

    public IASTNode getFollowingASTNodeSibling() {
        return ASTHelper.getFollowingASTNodeSibling(this);
    }

    public void setFirstStructuredDocumentRegion(IStructuredDocumentRegion region) {
        // TODO: XML SSE is really too restrictive. Need to submit a bug entry. 
    }

    public void setLastStructuredDocumentRegion(IStructuredDocumentRegion region) {
        // TODO: XML SSE is really too restrictive. Need to submit a bug entry. 
    }

    public void staticCheck(IStructuredDocument document, IValidator validator, IReporter reporter) {
        ASTHelper.staticCheck(this, document, validator, reporter);
    }

    public List<IMessage> getErrorMessages() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<String> getInScopeVariables() {
        return getASTParent() == null ? null : getASTParent().getInScopeVariables();
    }

    public void toString(int indent, StringBuilder builder) {
        builder.append(toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode#accept(org.eclipse.wst.xquery
     * .sse.core.internal.model.ast.ASTVisitor)
     */
    public void accept(ASTVisitor visitor) {
        // TODO Auto-generated method stub

    }

}
