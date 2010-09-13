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
 * "declare" "default" ("element" | "function") "namespace" URILiteral
 * </pre>
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 * 
 */
public class DefaultNSDeclStructuredDocumentRegion extends XQueryStructuredDocumentRegion {

    // Constructors

    public DefaultNSDeclStructuredDocumentRegion() {
        super();
    }

    // Methods

    /**
     * @return the namespace (URILiteral)
     */
    public ITextRegion getNamespace() {
        int index = SDRegionUtils.search(getRegions(), 0, XQueryRegions.URILITERAL);
        if (index != -1) {
            return getRegions().get(index);
        }
        return null;
    }

}
