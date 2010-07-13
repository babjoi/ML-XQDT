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

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * Collection of static methods related to {@link IStructuredDocumentRegion}
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class SDRegionUtils {

	/** Tells whether the given region is a namespace declaration */
	final public static boolean isNamespaceDecl(XQueryStructuredDocumentRegion region) {
		return region.getNumberOfRegions() >= 2 && region.getFirstRegion().getType() == XQueryRegions.KW_DECLARE
				&& region.getRegions().get(1).getType() == XQueryRegions.KW_NAMESPACE;
	}

	/** Gets the namespace prefix of the given namespace declaration */
	final public static String getNSDeclPrefix(XQueryStructuredDocumentRegion region) {
		return region.getNumberOfRegions() >= 3 ? region.getText(region.getRegions().get(2)) : null;
	}
	
	/** Gets the namespace  of the given namespace declaration */
	final public static String getNSDeclNamespace(XQueryStructuredDocumentRegion region) {
		return region.getNumberOfRegions() >= 5 ? region.getText(region.getRegions().get(4)) : null;
	}

}
