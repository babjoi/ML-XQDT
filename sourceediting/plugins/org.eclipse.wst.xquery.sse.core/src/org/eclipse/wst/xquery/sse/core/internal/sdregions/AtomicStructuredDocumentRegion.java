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
package org.eclipse.wst.xquery.sse.core.internal.sdregions;

/**
 * Structured document region consisting of an atomic XQuery construct, such as
 * StringLiteral, AtomicType, etc...
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class AtomicStructuredDocumentRegion extends XQueryStructuredDocumentRegion {

	// State

	/** Statement type */
	protected int statementType;

	// Constructors

	public AtomicStructuredDocumentRegion(int statementType) {
		this.statementType = statementType;
	}

	// Overrides

	@Override
	public int getStatementType() {
		return statementType;
	}

}
