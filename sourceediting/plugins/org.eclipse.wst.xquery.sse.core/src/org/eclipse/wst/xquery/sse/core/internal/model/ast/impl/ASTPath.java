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

import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTPath;

/**
 * Path expression
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class ASTPath extends ASTParentNode implements IASTPath {

	// Overrides

	@Override
	public int getType() {
		return PATH;
	}

	// Methods

	/**
	 * @return
	 */
	public IASTNode getRelativePath() {
		return getChildASTNodeAt(0);
	}

	/**
	 * @param node
	 */
	public void setRelativePath(IASTNode node) {
		setChildASTNodeAt(0, node);
	}

}
