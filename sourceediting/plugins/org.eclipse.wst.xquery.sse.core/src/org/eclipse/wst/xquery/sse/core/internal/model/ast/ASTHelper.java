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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.validation.internal.operations.LocalizedMessage;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

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
		if (newChild != null)
			newChild.setASTParent(parent);

		ensureCapacity(index, children);

		IASTNode oldChild = children.get(index);
		if (oldChild != null)
			oldChild.setASTParent(null);

		children.set(index, newChild);
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

	/**
	 * Report error
	 * 
	 * @param validator
	 */
	public static void reportError(IStructuredDocumentRegion sdregion,
			ITextRegion region, String text, IValidator validator,
			IReporter reporter) {
		IMessage message = new LocalizedMessage(IMessage.HIGH_SEVERITY, text);

		if (region != null) {
			message.setOffset(sdregion.getStartOffset() + region.getStart());
			message.setLength(region.getTextLength());
			message.setLineNo(sdregion.getParentDocument().getLineOfOffset(
					sdregion.getStartOffset() + region.getStart()));
		} else {
			message.setOffset(sdregion.getStartOffset());
			message.setLength(sdregion.getTextLength());
			message.setLineNo(sdregion.getParentDocument().getLineOfOffset(
					sdregion.getStartOffset()));

		}
		reporter.addMessage(validator, message);
	}

}
