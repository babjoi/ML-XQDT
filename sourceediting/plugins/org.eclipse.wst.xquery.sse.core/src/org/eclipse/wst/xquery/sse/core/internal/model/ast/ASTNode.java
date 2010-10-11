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
import java.util.List;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/**
 * Represent an XQuery AST node.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public abstract class ASTNode implements IASTNode {

    // State

    /** Parent node */
    protected IASTNode parent;

    /** List of error messages attached to this node */
    protected List<IMessage> messages;

    /** First sdr */
    protected IStructuredDocumentRegion firstRegion;

    /** Last sdr */
    protected IStructuredDocumentRegion lastRegion;

    // Methods

    /**
     * Recursively computed the list of in-scope variables
     */
    protected void getInScopeVariables(List<String> vars, IASTNode child) {
        if (parent instanceof ASTNode) {
            ((ASTNode)parent).getInScopeVariables(vars, this);
        } else if (parent != null) {
            vars.addAll(parent.getInScopeVariables());
        }
    }

    // Implements IASTNode

    public abstract int getType();

    public IASTNode getASTParent() {
        return parent;
    }

    public void setASTParent(IASTNode parent) {
        this.parent = parent;
    }

    public void setChildASTNodeAt(int index, IASTNode newChild) {
        throw new IllegalStateException("Illegal call to setChildNodeAt on a leaf node");
    }

    public IASTNode getChildASTNodeAt(int i) {
        throw new IllegalStateException("Illegal call to setChildNodeAt on a leaf node");
    }

    public void removeChildASTNodesAfter(int index) {
        throw new IllegalStateException("Illegal call to removeChildNodesAfter on a leaf node");
    }

    public int getChildASTNodesCount() {
        return 0;
    }

    public IASTNode getPreviousASTNodeSibling() {
        return ASTHelper.getPreviousASTNodeSibling(this);
    }

    public IASTNode getFollowingASTNodeSibling() {
        return ASTHelper.getFollowingASTNodeSibling(this);
    }

    public IStructuredDocumentRegion getFirstStructuredDocumentRegion() {
        return firstRegion;
    }

    public void setFirstStructuredDocumentRegion(IStructuredDocumentRegion region) {
        firstRegion = region;
    }

    public IStructuredDocumentRegion getLastStructuredDocumentRegion() {
        return lastRegion;
    }

    public void setLastStructuredDocumentRegion(IStructuredDocumentRegion region) {
        lastRegion = region;
    }

    public List<IMessage> getErrorMessages() {
        return messages;
    }

    public void staticCheck(IStructuredDocument document, IValidator validator, IReporter reporter) {
        ASTHelper.staticCheck(this, document, validator, reporter);
    }

    public List<String> getInScopeVariables() {
        List<String> vars = new ArrayList<String>();
        getInScopeVariables(vars, null);
        return vars;

    }

    // Debugging

    public String toString() {
        StringBuilder builder = new StringBuilder();
        toString(0, builder);
        return builder.toString();
    }

    public void toString(int indent, StringBuilder builder) {
        builder.append("\n");
        for (int i = 0; i < indent; i++) {
            builder.append(' ');
        }
        builder.append("[").append(getClass().getSimpleName());
        if (getChildASTNodesCount() > 0) {
            for (int i = 0; i < getChildASTNodesCount(); i++) {
                if (getChildASTNodeAt(i) != null) {
                    getChildASTNodeAt(i).toString(indent + 2, builder);
                }
            }
            builder.append("\n");
        }
        builder.append("]");
    }

}
