/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Gabriel Petrovay (28msec) - extracted interface from XQDTLineStyleProvider
 *     
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.style;

/**
 * This interface contains the color keys used color highlighting in the XQuery editor.
 */
public interface IXQDTColorKeys {

    /** Undefined color key */
    final public static String CK_UNDEFINED = "Undefined";
    /** Keywords color key */
    final public static String CK_KEYWORD = "Keyword";
    /** Operator color key */
    final public static String CK_OPERATOR = "Operator";
    /** String Literal color key */
    final public static String CK_STRING_LITERAL = "StringLiteral";
    /** Variable reference color key */
    final public static String CK_DOLLAR_EXPR = "DollarExpression";
    /** Sequence type color key */
    final public static String CK_TYPE = "Type";
    /** Comment color key */
    final public static String CK_COMMENT = "Comment";
    /** Pragma */
    final public static String CK_PRAGMA = "Pragma";
    /** Function call */
    final public static String CK_FUNCTION_NAME = "FunctionName";
    /** XML Tag Delimiters (< > /> </) */
    final public static String CK_XML_TAG_DELIMITER = "XMLTDelim";
    /** Attribute Equal Sign */
    final public static String CK_XML_ATTR_EQUAL = "XMLAttrEqual";
    /** Attribute Name */
    final public static String CK_XML_ATTR_NAME = "XMLAttrName";
    /** Attribute Values */
    final public static String CK_XML_ATTR_VALUE = "XMLAttrValue";
    /** CData Content */
    final public static String CK_XML_CDATA_CONTENT = "XMLCDataContent";
    /** CData Delimiters (Not available) */
    // final public static String CK_XML_CDATA_DELIM = "XMLCDataDelim";
    /** Content */
    final public static String CK_XML_CONTENT = "XMLContent";
    /** Entity references */
    final public static String CK_XML_ENTITY_REFERENCE = "XMLEntityRef";
    /** PI Content */
    final public static String CK_XML_PI_CONTENT = "XMLPIContent";
    /** PI Delim (Not available) */
    // final public static String CK_XML_PI_DELIM = "XMLPIDelim";
    /** Tag Name */
    final public static String CK_XML_TAG_NAME = "XMLTagName";
    /** XML Comment */
    final public static String CK_XML_COMMENT = "XMLComment";

}