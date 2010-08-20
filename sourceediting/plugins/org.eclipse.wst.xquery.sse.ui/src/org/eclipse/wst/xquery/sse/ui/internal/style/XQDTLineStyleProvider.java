/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Lionel Villard (IBM) - initial API and implementation
 *     Gabriel Petrovay (28msec) - extracted the IXQDTColorKeys interface
 *     
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.style;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionCollection;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;
import org.eclipse.wst.sse.core.internal.util.Debug;
import org.eclipse.wst.sse.ui.internal.provisional.style.AbstractLineStyleProvider;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegion;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;
import org.eclipse.wst.xquery.sse.ui.XQDTSSEUIPlugin;
import org.eclipse.wst.xquery.sse.ui.style.IXQDTColorKeys;

/**
 * Provides style for XQuery regions
 * 
 * @author villardl
 */
@SuppressWarnings({ "restriction", "rawtypes" })
public class XQDTLineStyleProvider extends AbstractLineStyleProvider implements LineStyleProvider, IXQDTColorKeys {

    // Constants

    /** Maps region type into color key */
    final private static Map<String, String> COLOR_KEYS = new HashMap<String, String>();

    static {
        // String literal
        COLOR_KEYS.put(XQueryRegions.STRINGLITERAL, CK_STRING_LITERAL);
        COLOR_KEYS.put(XQueryRegions.URILITERAL, CK_STRING_LITERAL);

        // Comment
        COLOR_KEYS.put(XQueryRegions.XQUERY_COMMENT, CK_COMMENT);

        // Var ref
        COLOR_KEYS.put(XQueryRegions.VARREF, CK_DOLLAR_EXPR);

        // Function call
        COLOR_KEYS.put(XQueryRegions.FUNCTIONNAME, CK_FUNCTION_NAME);

        // Undefined region
        COLOR_KEYS.put(XQueryRegions.UNDEFINED, CK_UNDEFINED);

        // Types
        COLOR_KEYS.put(XQueryRegions.ST_ATOMICTYPE, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.ST_EMPTY, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.ST_ITEM, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_ANYKIND, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_ATTRIBUTE, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_COMMENT, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_DOCUMENTNODE, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_ELEMENT, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_PI, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_SCHEMAATTRIBUTE, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_SCHEMAELEMENT, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_WILDCARD, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_QNAME, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_NCNAME, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.KT_TEXT, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.ST_LPAR, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.ST_RPAR, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.OCC_ONEORMORE, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.OCC_OPTIONAL, CK_TYPE);
        COLOR_KEYS.put(XQueryRegions.OCC_ZEROORMORE, CK_TYPE);

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
        addTextAttribute(CK_DOLLAR_EXPR);
        addTextAttribute(CK_TYPE);
        addTextAttribute(CK_COMMENT);
        addTextAttribute(CK_OPERATOR);
        addTextAttribute(CK_PRAGMA);
        addTextAttribute(CK_FUNCTION_NAME);
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

    // Temporary override methods to work around white space refresh problem.

    public boolean prepareRegions(ITypedRegion typedRegion, int lineRequestStart, int lineRequestLength,
            Collection holdResults) {
        final int partitionStartOffset = typedRegion.getOffset();
        final int partitionLength = typedRegion.getLength();
        IStructuredDocumentRegion structuredDocumentRegion = getDocument().getRegionAtCharacterOffset(
                partitionStartOffset);
        boolean handled = false;

        handled = prepareTextRegions(structuredDocumentRegion, partitionStartOffset, partitionLength, holdResults);

        return handled;
    }

    /**
     * this version does "trim" regions to match request
     */
    private StyleRange createStyleRange(ITextRegionCollection flatNode, ITextRegion region, TextAttribute attr,
            int startOffset, int length) {
        int start = flatNode.getStartOffset(region);
        if (start < startOffset) {
            start = startOffset;
        }

        // Base the text end offset off of the, possibly adjusted, start
        int textEnd = start + region.getLength();
        int maxOffset = startOffset + length;

        int end = flatNode.getEndOffset(region);
        // Use the end of the text in the region to avoid applying background color to trailing whitespace
        if (textEnd < end) {
            end = textEnd;
        }
        // instead of end-start?
        if (end > maxOffset) {
            end = maxOffset;
        }
        StyleRange result = new StyleRange(start, end - start, attr.getForeground(), attr.getBackground(),
                attr.getStyle());
        if ((attr.getStyle() & TextAttribute.STRIKETHROUGH) != 0) {
            result.strikeout = true;
        }
        if ((attr.getStyle() & TextAttribute.UNDERLINE) != 0) {
            result.underline = true;
        }
        return result;

    }

    private boolean prepareTextRegions(IStructuredDocumentRegion structuredDocumentRegion, int partitionStartOffset,
            int partitionLength, Collection holdResults) {
        boolean handled = false;
        final int partitionEndOffset = partitionStartOffset + partitionLength - 1;
        while (structuredDocumentRegion != null && structuredDocumentRegion.getStartOffset() <= partitionEndOffset) {
            ITextRegion region = null;
            ITextRegionList regions = structuredDocumentRegion.getRegions();
            int nRegions = regions.size();
            StyleRange styleRange = null;
            for (int i = 0; i < nRegions; i++) {
                region = regions.get(i);
                TextAttribute attr = null;
                TextAttribute previousAttr = null;
                if (structuredDocumentRegion.getStartOffset(region) > partitionEndOffset) {
                    break;
                }
                if (structuredDocumentRegion.getEndOffset(region) <= partitionStartOffset) {
                    continue;
                }

                if (region instanceof ITextRegionCollection) {
                    boolean handledCollection = (prepareTextRegion((ITextRegionCollection)region, partitionStartOffset,
                            partitionLength, holdResults));
                    handled = (!handled) ? handledCollection : handled;
                } else {

                    attr = getAttributeFor(structuredDocumentRegion, region);
                    if (attr != null) {
                        handled = true;
                        // if this region's attr is the same as previous one,
                        // then just adjust the previous style range
                        // instead of creating a new instance of one
                        // note: to use 'equals' in this case is important,
                        // since sometimes
                        // different instances of attributes are associated
                        // with a region, even the
                        // the attribute has the same values.
                        // TODO: this needs to be improved to handle readonly
                        // regions correctly
                        if ((styleRange != null) && (previousAttr != null) && (previousAttr.equals(attr))) {
                            styleRange.length += region.getLength();
                        } else {
                            styleRange = createStyleRange(structuredDocumentRegion, region, attr, partitionStartOffset,
                                    partitionLength);
                            holdResults.add(styleRange);
                            // technically speaking, we don't need to update
                            // previousAttr
                            // in the other case, because the other case is
                            // when it hasn't changed
                            previousAttr = attr;
                        }
                    } else {
                        previousAttr = null;
                    }
                }

                if (Debug.syntaxHighlighting && !handled) {
                    System.out.println("not handled in prepareRegions"); //$NON-NLS-1$
                }
            }
            structuredDocumentRegion = structuredDocumentRegion.getNext();
        }
        return handled;
    }

    /**
     * @param region
     * @param start
     * @param length
     * @param holdResults
     * @return
     */
    private boolean prepareTextRegion(ITextRegionCollection blockedRegion, int partitionStartOffset,
            int partitionLength, Collection holdResults) {
        boolean handled = false;
        final int partitionEndOffset = partitionStartOffset + partitionLength - 1;
        ITextRegion region = null;
        ITextRegionList regions = blockedRegion.getRegions();
        int nRegions = regions.size();
        StyleRange styleRange = null;
        for (int i = 0; i < nRegions; i++) {
            region = regions.get(i);
            TextAttribute attr = null;
            TextAttribute previousAttr = null;
            if (blockedRegion.getStartOffset(region) > partitionEndOffset) {
                break;
            }
            if (blockedRegion.getEndOffset(region) <= partitionStartOffset) {
                continue;
            }

            if (region instanceof ITextRegionCollection) {
                handled = prepareTextRegion((ITextRegionCollection)region, partitionStartOffset, partitionLength,
                        holdResults);
            } else {

                attr = getAttributeFor(blockedRegion, region);
                if (attr != null) {
                    handled = true;
                    // if this region's attr is the same as previous one, then
                    // just adjust the previous style range
                    // instead of creating a new instance of one
                    // note: to use 'equals' in this case is important, since
                    // sometimes
                    // different instances of attributes are associated with a
                    // region, even the
                    // the attribute has the same values.
                    // TODO: this needs to be improved to handle readonly
                    // regions correctly
                    if ((styleRange != null) && (previousAttr != null) && (previousAttr.equals(attr))) {
                        styleRange.length += region.getLength();
                    } else {
                        styleRange = createStyleRange(blockedRegion, region, attr, partitionStartOffset,
                                partitionLength);
                        holdResults.add(styleRange);
                        // technically speaking, we don't need to update
                        // previousAttr
                        // in the other case, because the other case is when
                        // it hasn't changed
                        previousAttr = attr;
                    }
                } else {
                    previousAttr = null;
                }
            }
        }
        return handled;
    }

}
