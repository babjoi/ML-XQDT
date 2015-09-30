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
package org.eclipse.wst.xquery.internal.core.tests;

import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.document.XQueryDocumentLoader;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;
import org.junit.Assert;
import org.junit.Test;

/**
 * SSE tokenizer tests
 *
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class TestSSEMarkLogicParser implements XQueryRegions {

    final static String[] G_DOLLARVAR = new String[] { DOLLAR, VARREF };
    final static String[] G_DECLAREVAR = new String[] { KW_DECLARE, KW_VARIABLE };
    final static String[] G_DECLAREFUN = new String[] { KW_DECLARE, KW_FUNCTION };
    final static String[] G_FUNCALL = new String[] { FUNCTIONNAME, LPAR };

    @Test
    public void testFunction() {
        IStructuredDocument document = load("declare function local:foo() { () };");
        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { G_DECLAREFUN, G_FUNCALL, RPAR, LCURLY, LPAR, RPAR, RCURLY, SEPARATOR });
    }

//    @Test
//    public void testPrivateFunction() {
//        IStructuredDocument document = load("declare private function local:foo() { () };");
//        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();
//
//        checkRegionTypes(regions, new Object[] { G_FUNCALL, LPAR, RPAR, RPAR });
//    }

    // Helpers

    /**
     * Checks region types
     *
     * @param regions
     * @param objects
     */
    private void checkRegionTypes(IStructuredDocumentRegion[] regions, Object[] types) {
        Assert.assertEquals("Wrong number of structured document regions", types.length, regions.length);
        for (int i = 0; i < regions.length; i++) {
            IStructuredDocumentRegion sdregion = regions[i];
            if (types[i] instanceof String[]) {
                String[] subtypes = (String[])types[i];
                Assert.assertEquals("Wrong number of text regions", subtypes.length, sdregion.getNumberOfRegions());
                if (subtypes.length == sdregion.getNumberOfRegions()) {
                    for (int j = 0; j < subtypes.length; j++) {

                        Assert.assertEquals("Wrong text region type", subtypes[j], sdregion.getRegions().get(j)
                                .getType());

                    }
                }
            } else {
                Assert.assertEquals("Wrong number of text regions", 1, sdregion.getNumberOfRegions());
                Assert.assertEquals("Wrong text region type", types[i], sdregion.getFirstRegion().getType());

            }
        }

    }

    /** Load given query */
    protected IStructuredDocument load(String query) {
        XQueryDocumentLoader loader = new XQueryDocumentLoader();
        IStructuredDocument document = (IStructuredDocument)loader.createNewStructuredDocument();
        document.set(query);

        if (!query.equals("")) {
            Assert.assertNotNull("No document regions created", document.getStructuredDocumentRegions());
        }

        return document;
    }
}
