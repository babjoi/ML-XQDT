/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lionel Villard (IBM) - initial API and implementation
 *     Gabriel Petrovay (28msec) - added XQuery comment and string partitions
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.document;

import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.text.rules.StructuredTextPartitioner;
import org.eclipse.wst.xquery.sse.core.text.IXQDTPartitions;

/**
 * XQuery document partitioner.
 */
public class XQDTDocumentPartitioner extends StructuredTextPartitioner implements IXQDTPartitions {

    private final static String[] CONFIGURED_CONTENT_TYPES = new String[] { XQUERY_DEFAULT, XQUERY_COMMENT,
            XQUERY_STRING };

    // Overrides

    @Override
    protected void initLegalContentTypes() {
        fSupportedTypes = CONFIGURED_CONTENT_TYPES;
    }

    @Override
    public String getDefaultPartitionType() {
        return XQUERY_DEFAULT;
    }

    @Override
    public String getPartitionType(ITextRegion region, int offset) {
        String result = null;

        // Disable partitionning until figure out how to clear annotations

//        if (region.getType().equals(XQueryRegions.STRINGLITERAL)) {
//            result = XQUERY_STRING;
//        } else if (region.getType().equals(XQueryRegions.XQUERY_COMMENT)) {
//            result = XQUERY_COMMENT;
//        } else {
        result = super.getPartitionType(region, offset);
        // }

        return result;
    }

}
