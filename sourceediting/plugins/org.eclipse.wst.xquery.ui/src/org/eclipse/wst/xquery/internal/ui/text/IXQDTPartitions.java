/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.ui.text;

import org.eclipse.jface.text.IDocument;

public interface IXQDTPartitions {

    public final static String CONTENT_TYPES_CATEGORY = "__content_types_category";

    public final static String XQDT_PARTITIONING = "__xqdt_partitioning";

    public final static String XQDT_COMMENT = "__xqdt_comment";
    public final static String XQDT_XML_COMMENT = "__xqdt_xml_coment";
    public final static String XQDT_XML_CDATA = "__xqdt_xml_cdata";

    public final static String XQDT_STRING = "__xqdt_string";

    public final static String XQDT_XML_ELEMENT_CONTENT = "__xqdt_xml_element_content";
    public final static String XQDT_XML_ATTRIBUTE_VALUE = "__xqdt_xml_attribute_value";

    public final static String[] XQDT_LEGAL_PARTITION_TYPES = new String[] { XQDT_COMMENT, XQDT_XML_COMMENT,
            XQDT_XML_CDATA, XQDT_STRING, XQDT_XML_ELEMENT_CONTENT, XQDT_XML_ATTRIBUTE_VALUE, };

    public final static String[] XQDT_PARTITION_TYPES = new String[] { XQDT_COMMENT, XQDT_XML_COMMENT, XQDT_XML_CDATA,
            XQDT_STRING, XQDT_XML_ATTRIBUTE_VALUE, XQDT_XML_ELEMENT_CONTENT, IDocument.DEFAULT_CONTENT_TYPE };

    public final static String[] XQDT_SEMANTIC_PARTITION_TYPES = new String[] { IXQDTPartitions.XQDT_STRING,
            IXQDTPartitions.XQDT_XML_ATTRIBUTE_VALUE, IXQDTPartitions.XQDT_XML_ELEMENT_CONTENT };

}
