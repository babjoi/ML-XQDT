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
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xquery.sse.core.internal.ValidationHelper;

/**
 * Collection of static utility methods
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class ASTHelper {

	/**
	 * Removes all elements after the given index
	 * 
	 * @param children
	 * @param index
	 */
	public static void removeAfter(List<?> children, int index) {
		while (children.size() > index + 1)
			children.remove(children.size() - 1);
	}

	/**
	 * Sets the value at the given index.
	 * 
	 * <p>
	 * Set the gaps (if any) to null.
	 * 
	 * @param children
	 * @param index
	 * @param newChild
	 */
	public static void setChildASTNodeAt(List<IASTNode> children,
			IASTNode parent, int index, IASTNode newChild) {
		ensureCapacity(index, children);
		IASTNode oldChild = children.get(index);
		if (oldChild != newChild) {
			if (newChild != null)
				newChild.setASTParent(parent);

			if (oldChild != null)
				oldChild.setASTParent(null);

			children.set(index, newChild);
		}
	}

	/**
	 * Gets the child node at the given index.
	 * 
	 * @param children
	 * @param i
	 * @return null if there is no child at the given index.
	 */
	public static IASTNode getChildASTNodeAt(List<IASTNode> children, int i) {
		if (children.size() > i)
			return children.get(i);

		return null;
	}

	/**
	 * Gets the number of child nodes
	 * 
	 * @return
	 */
	public static int getChildASTNodesCount(List<IASTNode> children) {
		return children.size();
	}

	/** Make sure that there is enough space in the given array */
	public static void ensureCapacity(int index, List<?> list) {
		while (list.size() <= index)
			list.add(null);
	}

	/** Returns the previous sibling of the given node */
	public static IASTNode getPreviousASTNodeSibling(IASTNode node) {
		if (node.getASTParent() != null) {
			final IASTNode parent = node.getASTParent();
			int count = parent.getChildASTNodesCount();
			for (int i = 0; i < count; i++) {
				if (parent.getChildASTNodeAt(i) == node) {
					return i > 0 ? parent.getChildASTNodeAt(i - 1) : null;
				}
			}
		}

		return null;
	}

	/** Returns the following sibling of the given node */
	public static IASTNode getFollowingASTNodeSibling(IASTNode node) {
		if (node.getASTParent() != null) {
			final IASTNode parent = node.getASTParent();
			int count = parent.getChildASTNodesCount();
			for (int i = 0; i < count - 1; i++) {
				if (parent.getChildASTNodeAt(i) == node)
					return parent.getChildASTNodeAt(i + 1);
			}
		}

		return null;
	}

	/**
	 * Report error
	 * 
	 * @param validator
	 */
	public static void reportError(IStructuredDocumentRegion sdregion,
			ITextRegion region, String text, IValidator validator,
			IReporter reporter) {
		reporter.addMessage(validator,
				ValidationHelper.createErrorMessage(sdregion, region, text));
	}

	/**
	 * Perform static checking
	 * 
	 * @param astParentNode
	 * @param document
	 * @param validator
	 * @param reporter
	 * @return
	 */
	public static void staticCheck(IASTNode node,
			IStructuredDocument document, IValidator validator,
			IReporter reporter) {
		for (int i = node.getChildASTNodesCount() - 1; i >= 0; i--) {
			IASTNode child = node.getChildASTNodeAt(i);
			if (child != null)
				child.staticCheck(document, validator, reporter);
		}
	}

}
