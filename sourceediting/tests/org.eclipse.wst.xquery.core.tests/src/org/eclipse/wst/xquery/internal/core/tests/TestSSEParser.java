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
 * SSE parser/tokenizer tests
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class TestSSEParser implements XQueryRegions {

    final static String[] G_DOLLARVAR = new String[] { DOLLAR, VARREF };
    final static String[] G_DECLAREVAR = new String[] { KW_DECLARE, KW_VARIABLE };
    final static String[] G_FUNCALL = new String[] { FUNCTIONNAME, LPAR };

    @Test
    public void testDirectContruction() {
        IStructuredDocument document = load("<a></a>");
        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { new String[] { XML_TAG_OPEN, XML_TAG_NAME }, XML_TAG_CLOSE,
                XML_END_TAG_OPEN, XML_TAG_NAME, XML_TAG_CLOSE });

        document = load("<a><b/><c a=\"a\"></c></a>");
        regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { new String[] { XML_TAG_OPEN, XML_TAG_NAME }, XML_TAG_CLOSE,
                new String[] { XML_TAG_OPEN, XML_TAG_NAME }, XML_EMPTY_TAG_CLOSE,
                new String[] { XML_TAG_OPEN, XML_TAG_NAME }, XML_TAG_ATTRIBUTE_NAME, XML_TAG_ATTRIBUTE_EQUALS,
                XML_ATTR_QUOT, XML_ATTR_CHAR, XML_END_ATTR_VALUE, XML_TAG_CLOSE, XML_END_TAG_OPEN, XML_TAG_NAME,
                XML_TAG_CLOSE, XML_END_TAG_OPEN, XML_TAG_NAME, XML_TAG_CLOSE });

    }

    @Test
    public void testFunctionCall1() {
        IStructuredDocument document = load("toto(())");
        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { G_FUNCALL, LPAR, RPAR, RPAR });
    }

    @Test
    public void testFunctionCall2() {
        IStructuredDocument document = load("toto(1,2)");
        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { G_FUNCALL, NUMERICLITERAL, COMMA, NUMERICLITERAL, RPAR });
    }

    @Test
    public void testBug327492() {
        IStructuredDocument document = load("declare variable $r := for $i in 1 return $i; declare variable $r external; 4");
        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { G_DECLAREVAR, G_DOLLARVAR, ASSIGN, KW_FOR, G_DOLLARVAR, KW_IN,
                NUMERICLITERAL, KW_RETURN, G_DOLLARVAR, SEPARATOR, G_DECLAREVAR, G_DOLLARVAR, KW_EXTERNAL, SEPARATOR,
                NUMERICLITERAL });
    }

    @Test
    public void testBug334825() {
        IStructuredDocument document = load("a[1][1]");
        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { PATH_NAMETEST, LSQUARE, NUMERICLITERAL, RSQUARE, LSQUARE,
                NUMERICLITERAL, RSQUARE });
    }

    @Test
    public void testBug334830() {
        IStructuredDocument document = load("..");
        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { PATH_ABBREVPARENT });
    }

    @Test
    public void testUnionOperator() {
        IStructuredDocument document = load(".|@*");
        IStructuredDocumentRegion[] regions = document.getStructuredDocumentRegions();

        checkRegionTypes(regions, new Object[] { PATH_CONTEXTITEM, OP_PIPE, PATH_ABBREVATTRIBUTE, PATH_NAMETEST });
    }

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
