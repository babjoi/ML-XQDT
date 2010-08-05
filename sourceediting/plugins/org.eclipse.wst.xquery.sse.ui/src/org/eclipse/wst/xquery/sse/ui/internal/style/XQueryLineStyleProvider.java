/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.style;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.ui.internal.provisional.style.AbstractLineStyleProvider;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegion;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;
import org.eclipse.wst.xquery.sse.ui.XQDTSSEUIPlugin;

/**
 * Provides style for XQuery regions
 * 
 * @author villardl
 */
@SuppressWarnings("restriction")
public class XQueryLineStyleProvider extends AbstractLineStyleProvider implements LineStyleProvider {

    // Constants

    /** Undefined color key */
    final public static String CK_UNDEFINED = "Undefined";
    /** Keywords color key */
    final public static String CK_KEYWORD = "Keyword";
    /** Operator color key */
    final public static String CK_OPERATOR = "Operator";
    /** String Literal color key */
    final public static String CK_STRING_LITERAL = "StringLiteral";
    /** Variable reference color key */
    final public static String CK_VAR_REF = "VarRef";
    /** Sequence type color key */
    final public static String CK_TYPE = "Type";
    /** Comment color key */
    final public static String CK_COMMENT = "Comment";
    /** Pragma */
    final public static String CK_PRAGMA = "Pragma";
    /** Function call */
    final public static String CK_FUNCTIONCALL = "FunctionCall";

    // Allow configuring colors the same way the XML plugin does

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

    /** Maps region type into color key */
    final private static Map<String, String> COLOR_KEYS = new HashMap<String, String>();

    static {
        // String literal
        COLOR_KEYS.put(XQueryRegions.STRINGLITERAL, CK_STRING_LITERAL);
        COLOR_KEYS.put(XQueryRegions.URILITERAL, CK_STRING_LITERAL);

        // Comment
        COLOR_KEYS.put(XQueryRegions.XQUERY_COMMENT, CK_COMMENT);

        // Var ref
        COLOR_KEYS.put(XQueryRegions.VARREF, CK_VAR_REF);

        // Function call
        COLOR_KEYS.put(XQueryRegions.FUNCTIONNAME, CK_FUNCTIONCALL);

        // Undefined region
        COLOR_KEYS.put(XQueryRegions.UNDEFINED, CK_UNDEFINED);

        // Types
        COLOR_KEYS.put(XQueryRegions.ST_ATOMICTYPE, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.ST_EMPTY, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.ST_ITEM, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_ANYKINDTEST, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_ATTRIBUTETEST, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_COMMENTTEST, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_DOCUMENTTEST, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_ELEMENTTEST, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_PITEST, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_SCHEMAATTRIBUTETEST, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_SCHEMAELEMENTTEST, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_TEXTTEST, CK_TYPE);

        // Operators

        COLOR_KEYS.put(XQueryRegions.OP_CASTAS, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_OR, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_IDIV, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_DIV, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_PLUS, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_MINUS, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_MULTIPLY, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_GLT, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_GLTE, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_GGT, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_GGTE, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_GEQ, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_GNEQ, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_LT, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_LTE, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_GT, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_GTE, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_EQ, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_NEQ, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_IS, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_BEFORE, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_AFTER, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_TREATAS, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_TO, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_EXCEPT, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_UNION, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_AS, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_OF, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_MOD, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_INSTANCEOF, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_AND, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_INTERSECT, CK_OPERATOR);
        COLOR_KEYS.put(XQueryRegions.OP_CASTABLEAS, CK_OPERATOR);

        // XML
        COLOR_KEYS.put(XQueryRegions.XML_TAG_OPEN, CK_XML_TAG_DELIMITER);
        COLOR_KEYS.put(XQueryRegions.XML_TAG_CLOSE, CK_XML_TAG_DELIMITER);
        COLOR_KEYS.put(XQueryRegions.XML_EMPTY_TAG_CLOSE, CK_XML_TAG_DELIMITER);
        COLOR_KEYS.put(XQueryRegions.XML_EMPTY_TAG_CLOSE, CK_XML_TAG_DELIMITER);
        COLOR_KEYS.put(XQueryRegions.XML_END_TAG_OPEN, CK_XML_TAG_DELIMITER);

        COLOR_KEYS.put(XQueryRegions.XML_PE_REFERENCE, CK_XML_ENTITY_REFERENCE);

        COLOR_KEYS.put(XQueryRegions.XML_TAG_ATTRIBUTE_EQUALS, CK_XML_ATTR_EQUAL);

        COLOR_KEYS.put(XQueryRegions.XML_ATTR_APOS, CK_XML_ATTR_VALUE);
        COLOR_KEYS.put(XQueryRegions.XML_ATTR_QUOT, CK_XML_ATTR_VALUE);
        COLOR_KEYS.put(XQueryRegions.XML_ESCAPE_QUOT, CK_XML_ATTR_VALUE);
        COLOR_KEYS.put(XQueryRegions.XML_ESCAPE_APOS, CK_XML_ATTR_VALUE);
        COLOR_KEYS.put(XQueryRegions.XML_ATTR_CHAR, CK_XML_ATTR_VALUE);
        COLOR_KEYS.put(XQueryRegions.XML_END_ATTR_VALUE, CK_XML_ATTR_VALUE);

        COLOR_KEYS.put(XQueryRegions.XML_TAG_ATTRIBUTE_NAME, CK_XML_ATTR_NAME);

        COLOR_KEYS.put(XQueryRegions.XML_CDATA, CK_XML_CDATA_CONTENT);

        COLOR_KEYS.put(XQueryRegions.XML_PI, CK_XML_PI_CONTENT);

        COLOR_KEYS.put(XQueryRegions.XML_COMMENT, CK_XML_COMMENT);

        COLOR_KEYS.put(XQueryRegions.XML_TAG_NAME, CK_XML_TAG_NAME);

        COLOR_KEYS.put(XQueryRegions.XML_ELEM_CONTENT_CHAR, CK_XML_CONTENT);
        COLOR_KEYS.put(XQueryRegions.XML_CHAR_REF, CK_XML_CONTENT);

        // Pragma
        COLOR_KEYS.put(XQueryRegions.PRAGMACONTENT, CK_PRAGMA);
        COLOR_KEYS.put(XQueryRegions.PRAGMAQNAME, CK_PRAGMA);
        COLOR_KEYS.put(XQueryRegions.LPRAGMA, CK_PRAGMA);
        COLOR_KEYS.put(XQueryRegions.RPRAGMA, CK_PRAGMA);
    }

    // Constructors

    // Overrides

    @Override
    protected TextAttribute createTextAttribute(RGB foreground, RGB background, boolean bold) {
        return super.createTextAttribute(foreground, null, bold);
    }

    @Override
    protected TextAttribute createTextAttribute(RGB foreground, RGB background, int style) {
        return super.createTextAttribute(foreground, null, style);
    }

    // Implementation

    protected TextAttribute getAttributeFor(ITextRegion region) {
        final XQueryRegion xregion = (XQueryRegion)region;
        String colorKey;
        if (xregion.isKeyword()) {
            colorKey = CK_KEYWORD;
        } else {
            colorKey = COLOR_KEYS.get(region.getType());
        }

        if (colorKey != null) {
            return (TextAttribute)getTextAttributes().get(colorKey);
        }

        return (TextAttribute)getTextAttributes().get(CK_UNDEFINED);
    }

    protected IPreferenceStore getColorPreferences() {
        return XQDTSSEUIPlugin.getDefault().getPreferenceStore();
    }

    @SuppressWarnings("unchecked")
    protected void loadColors() {
        addTextAttribute(CK_STRING_LITERAL);
        addTextAttribute(CK_KEYWORD);
        addTextAttribute(CK_VAR_REF);
        addTextAttribute(CK_TYPE);
        addTextAttribute(CK_COMMENT);
        addTextAttribute(CK_OPERATOR);
        addTextAttribute(CK_PRAGMA);
        addTextAttribute(CK_FUNCTIONCALL);
        addTextAttribute(CK_XML_TAG_DELIMITER);
        addTextAttribute(CK_XML_ATTR_EQUAL);
        addTextAttribute(CK_XML_ATTR_NAME);
        addTextAttribute(CK_XML_ATTR_VALUE);
        addTextAttribute(CK_XML_CDATA_CONTENT);
        addTextAttribute(CK_XML_CONTENT);
        addTextAttribute(CK_XML_ENTITY_REFERENCE);
        addTextAttribute(CK_XML_PI_CONTENT);
        addTextAttribute(CK_XML_TAG_NAME);
        addTextAttribute(CK_XML_COMMENT);

        getTextAttributes().put(CK_UNDEFINED, createTextAttribute(new RGB(0, 0, 0), new RGB(255, 255, 255), false));
    }

}
