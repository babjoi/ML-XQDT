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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;

/**
 * AST node wrapping a {@link IStructuredDocumentRegion}
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public abstract class ASTSDRNode extends ASTNode {

	// State

	/** The structured document region */
	protected IStructuredDocumentRegion region;

	// Methods

	/**
	 * Get the region matching this node
	 */
	public IStructuredDocumentRegion getStructuredDocumentRegion() {
		return region;
	}

	/**
	 * @param region
	 */
	public void setStructuredDocumentRegion(IStructuredDocumentRegion region) {
		this.region = region;

		if (region instanceof XQueryStructuredDocumentRegion)
			((XQueryStructuredDocumentRegion) region).setASTNode(this);
	}

}
