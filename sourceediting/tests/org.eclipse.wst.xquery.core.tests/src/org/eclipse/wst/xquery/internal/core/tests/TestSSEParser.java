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
