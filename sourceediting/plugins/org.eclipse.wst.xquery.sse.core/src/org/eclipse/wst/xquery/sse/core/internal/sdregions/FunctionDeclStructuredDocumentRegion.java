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

import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * Region for
 * 
 * <pre>
 * 	FunctionDecl ::= ("declare" ("simple"? | "updating") "function" QName "(" ParamList? ")" ("as" SequenceType)? (EnclosedExpr | "external"))
 *                 | ("declare" "sequential" "function" QName "(" ParamList? ")" ("as" SequenceType)? (Block | "external"))
 * </pre>
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 * 
 */
public class FunctionDeclStructuredDocumentRegion extends XQueryStructuredDocumentRegion {
    // Factory

    final public static class Factory implements StructuredDocumentRegionFactory {

        final public static Factory INSTANCE = new Factory();

        private Factory() {
        }

        // Implements StructuredDocumentRegionFactory

        public XQueryStructuredDocumentRegion create() {
            return new FunctionDeclStructuredDocumentRegion();
        }

    }

    // Constructors

    public FunctionDeclStructuredDocumentRegion() {
        super();
    }

    // Methods

    /** Is "sequential" specified? */
    public boolean isSequential() {
        return SDRegionUtils.search(getRegions(), 1, XQueryRegions.KW_SEQUENTIAL) != -1;
    }

}
