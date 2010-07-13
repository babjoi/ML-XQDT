/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.parser;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.text.StructuredDocumentReParser;

/**
 * XQuery Reparser
 * 
 * Identify which document regions should be reparsed after a document change.
 * 
 * For now reparse everything.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQueryReParser extends StructuredDocumentReParser {

	// Overrides

	@Override
	protected void findDirtyStart(int start) {
		dirtyStart = fStructuredDocument.getRegionAtCharacterOffset(0);
	}

	@Override
	protected IStructuredDocumentRegion findDirtyEnd(int end) {
		IStructuredDocumentRegion result = fStructuredDocument.getRegionAtCharacterOffset(end);
		while (result.getNext() != null)
			result = result.getNext();

		dirtyEnd = result;
		return dirtyEnd;
	}

}
