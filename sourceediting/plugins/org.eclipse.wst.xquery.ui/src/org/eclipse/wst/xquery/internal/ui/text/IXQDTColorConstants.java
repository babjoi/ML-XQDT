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

import org.eclipse.dltk.ui.text.DLTKColorConstants;

public interface IXQDTColorConstants {

    public static final String XQDT_DEFAULT = DLTKColorConstants.DLTK_DEFAULT; //$NON-NLS-1$

    public static final String XQDT_COMMENT = DLTKColorConstants.DLTK_MULTI_LINE_COMMENT;
    // public static final String XQDT_XML_COMMENT = DLTKColorConstants.DLTK_DOC;
    public static final String XQDT_XML_COMMENT = "XQDT_xml_comment";
    public static final String XQDT_XML_CDATA = "XQDT_xml_cdata"; //$NON-NLS-1$	
    public static final String XQDT_STRING = DLTKColorConstants.DLTK_STRING; //$NON-NLS-1$
    //	public static final String XQDT_STRING = "XQDT_string"; //$NON-NLS-1$
    public static final String XQDT_XML_ATTRIBUTE_VALUE = "XQDT_xml_attr_value"; //$NON-NLS-1$
    public static final String XQDT_XML_ELEMENT_CONTENT = "XQDT_xml_elem_content"; //$NON-NLS-1$

    public static final String XQDT_KEYWORD = DLTKColorConstants.DLTK_KEYWORD; //$NON-NLS-1$
    public static final String XQDT_VARIABLE = "XQDT_variable"; //$NON-NLS-1$
    public static final String XQDT_FUNCTION = "XQDT_function"; //$NON-NLS-1$
    public static final String XQDT_ITEM_TYPE = "XQDT_item_type"; //$NON-NLS-1$
    public static final String XQDT_XML_TAG = "XQDT_xml_tag"; //$NON-NLS-1$

}
