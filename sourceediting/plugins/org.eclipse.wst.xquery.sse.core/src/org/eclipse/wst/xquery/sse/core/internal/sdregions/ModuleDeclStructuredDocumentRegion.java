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
import org.eclipse.wst.xquery.sse.core.internal.parser.StatementTypes;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * Region for
 * 
 * <pre>
 * ModuleDecl	 ::=   "module" "namespace" NCName "=" URILiteral Separator
 * </pre>
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 * 
 */
public class ModuleDeclStructuredDocumentRegion extends XQueryStructuredDocumentRegion {

	// Factory

	final public static class Factory implements StructuredDocumentRegionFactory {

		final public static Factory INSTANCE = new Factory();

		private Factory() {
		}

		// Implements StructuredDocumentRegionFactory

		public XQueryStructuredDocumentRegion create() {
			return new ModuleDeclStructuredDocumentRegion();
		}

	}

	// Constructors

	public ModuleDeclStructuredDocumentRegion() {
		super();
	}

	// Methods

	/**
	 * @return the namespace prefix (NCName)
	 */
	public ITextRegion getNamespacePrefix() {
		int index = search(getRegions(), 0, XQueryRegions.NCNAME);
		return index == -1 ? null : getRegions().get(index);
	}

	/**
	 * @return the namespace (URILiteral)
	 */
	public ITextRegion getNamespace() {
		int index = search(getRegions(), 0, XQueryRegions.EQUALS);
		if (index != -1) {
			index = search(getRegions(), index, XQueryRegions.URILITERAL);
			return index == -1 ? null : getRegions().get(index);
		}
		return null;
	}

	// Overrides

	@Override
	public int getStatementType() {
		return StatementTypes.STMT_MODULEDECL;
	}

}
