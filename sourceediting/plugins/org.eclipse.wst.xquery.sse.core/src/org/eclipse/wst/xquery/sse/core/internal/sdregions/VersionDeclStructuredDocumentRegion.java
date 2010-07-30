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
package org.eclipse.wst.xquery.sse.core.internal.sdregions;

import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * Region for
 * 
 * <pre>
 * VersionDecl	   ::=   	"xquery" "version" StringLiteral ("encoding" StringLiteral)? Separator
 * </pre>
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 * 
 */
@SuppressWarnings("restriction")
public class VersionDeclStructuredDocumentRegion extends XQueryStructuredDocumentRegion {
	// Factory

	final public static class Factory implements StructuredDocumentRegionFactory {

		final public static Factory INSTANCE = new Factory();

		private Factory() {
		}

		// Implements StructuredDocumentRegionFactory

		public XQueryStructuredDocumentRegion create() {
			return new VersionDeclStructuredDocumentRegion();
		}

	}

	// Constructors

	private VersionDeclStructuredDocumentRegion() {
		super();
	}

	// Methods

	public ITextRegion getVersion() {
		final int index = search(getRegions(), 1, XQueryRegions.STRINGLITERAL);
		return index == -1 ? null : getRegions().get(index);
	}

	public ITextRegion getEncoding() {
		int index = search(getRegions(), 1, XQueryRegions.KW_ENCODING);
		if (index != -1) {
			index = search(getRegions(), index, XQueryRegions.STRINGLITERAL);
			if (index != -1)
				return getRegions().get(index);
		}

		return null;
	}

}
