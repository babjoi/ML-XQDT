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
package org.eclipse.wst.xquery.sse.core.internal.model.ast.xml;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xml.core.internal.document.AttrImpl;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.ASTHelper;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.w3c.dom.Node;

/**
 * Direct attribute constructor
 * 
 * <p>
 * Might contains XQuery expressions as child AST nodes.
 * </p>
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTDirAttribute extends AttrImpl implements IASTNode {

    // State

    /** List of inner expressions */
    protected List<IASTNode> children;

    // Constructor

    public ASTDirAttribute() {
        children = new ArrayList<IASTNode>(1);
    }

    // Overrides

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    // Implements IASTNode

    public void setASTParent(IASTNode parent) {
        setParentNode((Node)parent); // Should be the element.
    }

    public IASTNode getASTParent() {
        return (IASTNode)getParentNode();
    }

    public int getType() {
        return DIRATTRIBUTE;
    }

    public void removeChildASTNodesAfter(int index) {
        ASTHelper.removeAfter(children, index);
    }

    public void setChildASTNodeAt(int index, IASTNode newChild) {
        ASTHelper.setChildASTNodeAt(children, this, index, newChild);
    }

    public IASTNode getChildASTNodeAt(int i) {
        return ASTHelper.getChildASTNodeAt(children, i);
    }

    public int getChildASTNodesCount() {
        return ASTHelper.getChildASTNodesCount(children);
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

}
