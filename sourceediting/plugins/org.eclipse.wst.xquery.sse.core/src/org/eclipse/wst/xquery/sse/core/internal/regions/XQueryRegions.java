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
package org.eclipse.wst.xquery.sse.core.internal.regions;

import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;

/**
 * Enumeration of XQuery regions
 *
 * @author <a href="mailto:villard@us.ibm.com">Lionel Villard</a>
 */
public interface XQueryRegions extends DOMRegionContext {

    public static final String UNDEFINED = "UNDEFINED"; //$NON-NLS-1$
    public static final String WHITE_SPACE = "WHITE_SPACE"; //$NON-NLS-1$
    public static final String XQUERY_COMMENT = "XQUERY_COMMENT"; //$NON-NLS-1$
    public static final String QNAME = "QNAME"; //$NON-NLS-1$
    public static final String NCNAME = "NCNAME"; //$NON-NLS-1$
    public static final String STRINGLITERAL = "STRINGLITERAL"; //$NON-NLS-1$
    public static final String URILITERAL = "URILITERAL"; //$NON-NLS-1$
    public static final String SEPARATOR = "SEPARATOR"; //$NON-NLS-1$
    public static final String COLONCOLON = "COLONCOLON"; //$NON-NLS-1$
    public static final String COMMA = "COMMA"; //$NON-NLS-1$
    public static final String EQUALS = "EQUALS"; //$NON-NLS-1$
    public static final String DOLLAR = "DOLLAR"; //$NON-NLS-1$
    public static final String ASSIGN = "ASSIGN"; //$NON-NLS-1$
    public static final String LPAR = "LPAR"; //$NON-NLS-1$
    public static final String RPAR = "RPAR"; //$NON-NLS-1$
    public static final String LCURLY = "LCURLY"; //$NON-NLS-1$
    public static final String RCURLY = "RCURLY"; //$NON-NLS-1$
    public static final String RSQUARE = "RSQUARE"; //$NON-NLS-1$
    public static final String LSQUARE = "LSQUARE"; //$NON-NLS-1$
    public static final String LPRAGMA = "LPRAGMA"; //$NON-NLS-1$
    public static final String RPRAGMA = "RPRAGMA"; //$NON-NLS-1$
    public static final String PRAGMACONTENT = "PRAGMACONTENT"; //$NON-NLS-1$
    public static final String PRAGMAQNAME = "PRAGMAQNAME"; //$NON-NLS-1$

    public static final String FUNCTIONNAME = "FUNCTIONNAME"; //$NON-NLS-1$
    public static final String NUMERICLITERAL = "NUMERICLITERAL"; //$NON-NLS-1$

    public static final String KW_NAMESPACE = "KW_NAMESPACE"; //$NON-NLS-1$
    public static final String KW_STRIP = "KW_STRIP"; //$NON-NLS-1$
    public static final String KW_GREATEST = "KW_GREATEST"; //$NON-NLS-1$
    public static final String KW_PRESERVE = "KW_PRESERVE"; //$NON-NLS-1$
    public static final String KW_ORDER = "KW_ORDER"; //$NON-NLS-1$
    public static final String KW_ELEMENT = "KW_ELEMENT"; //$NON-NLS-1$
    public static final String KW_XQUERY = "KW_XQUERY"; //$NON-NLS-1$
    public static final String KW_DECLARE = "KW_DECLARE"; //$NON-NLS-1$
    public static final String KW_EMPTY = "KW_EMPTY"; //$NON-NLS-1$
    public static final String KW_FUNCTION = "KW_FUNCTION"; //$NON-NLS-1$
    public static final String KW_DEFAULTFUNCTION = "KW_DEFAULTFUNCTION"; //$NON-NLS-1$
    public static final String KW_OPTION = "KW_OPTION"; //$NON-NLS-1$
    public static final String KW_DEFAULT = "KW_DEFAULT"; //$NON-NLS-1$
    public static final String KW_ENCODING = "KW_ENCODING"; //$NON-NLS-1$
    public static final String KW_ORDERING = "KW_ORDERING"; //$NON-NLS-1$
    public static final String KW_MODULE = "KW_MODULE"; //$NON-NLS-1$
    public static final String KW_ORDERED = "KW_ORDERED"; //$NON-NLS-1$
    public static final String KW_UNORDERED = "KW_UNORDERED"; //$NON-NLS-1$
    public static final String KW_LEAST = "KW_LEAST"; //$NON-NLS-1$
    public static final String KW_VERSION = "KW_VERSION"; //$NON-NLS-1$
    public static final String KW_BOUNDARY_SPACE = "KW_BOUNDARY_SPACE"; //$NON-NLS-1$
    public static final String KW_LET = "KW_LET"; //$NON-NLS-1$
    public static final String KW_RETURN = "KW_RETURN"; //$NON-NLS-1$
    public static final String KW_SCHEMA = "KW_SCHEMA"; //$NON-NLS-1$
    public static final String KW_AT = "KW_AT"; //$NON-NLS-1$
    public static final String KW_NOPRESERVE = "KW_NOPRESERVE"; //$NON-NLS-1$
    public static final String KW_IMPORT = "KW_IMPORT"; //$NON-NLS-1$
    public static final String KW_COPYNAMESPACES = "KW_COPYNAMESPACES"; //$NON-NLS-1$
    public static final String KW_BASEURI = "KW_BASEURI"; //$NON-NLS-1$
    public static final String KW_COLLATION = "KW_COLLATION"; //$NON-NLS-1$
    public static final String KW_INHERIT = "KW_INHERIT"; //$NON-NLS-1$
    public static final String KW_NOINHERIT = "KW_NOINHERIT"; //$NON-NLS-1$
    public static final String KW_VARIABLE = "KW_VARIABLE"; //$NON-NLS-1$
    public static final String KW_AS = "KW_AS"; //$NON-NLS-1$
    public static final String KW_EXTERNAL = "KW_EXTERNAL"; //$NON-NLS-1$
    public static final String KW_CONSTRUCTION = "KW_CONSTRUCTION"; //$NON-NLS-1$
    public static final String KW_IN = "KW_IN"; //$NON-NLS-1$
    public static final String KW_FOR = "KW_FOR"; //$NON-NLS-1$
    public static final String KW_STABLE = "KW_STABLE"; //$NON-NLS-1$
    public static final String KW_DESCENDING = "KW_DESCENDING"; //$NON-NLS-1$
    public static final String KW_ASCENDING = "KW_ASCENDING"; //$NON-NLS-1$
    public static final String KW_BY = "KW_BY"; //$NON-NLS-1$
    public static final String KW_WHERE = "KW_WHERE"; //$NON-NLS-1$
    public static final String KW_LETASSIGN = "KW_LETASSIGN"; //$NON-NLS-1$
    public static final String KW_SOME = "KW_SOME"; //$NON-NLS-1$
    public static final String KW_EVERY = "KW_EVERY"; //$NON-NLS-1$
    public static final String KW_SATIFIES = "KW_SATIFIES"; //$NON-NLS-1$
    public static final String KW_IF = "KW_IF"; //$NON-NLS-1$
    public static final String KW_THEN = "KW_THEN"; //$NON-NLS-1$
    public static final String KW_ELSE = "KW_ELSE"; //$NON-NLS-1$
    public static final String KW_TYPESWITCH = "KW_TYPESWITCH"; //$NON-NLS-1$
    public static final String KW_CASE = "KW_CASE"; //$NON-NLS-1$
    public static final String KW_VALIDATE = "KW_VALIDATE"; //$NON-NLS-1$
    public static final String KW_LAX = "KW_LAX"; //$NON-NLS-1$
    public static final String KW_STRICT = "KW_STRICT"; //$NON-NLS-1$
    public static final String KW_PI = "KW_PI"; //$NON-NLS-1$
    public static final String KW_DOCUMENT = "KW_DOCUMENT"; //$NON-NLS-1$
    public static final String KW_ATTRIBUTE = "KW_ATTRIBUTE"; //$NON-NLS-1$
    public static final String KW_COMMENT = "KW_COMMENT"; //$NON-NLS-1$
    public static final String KW_TEXT = "KW_TEXT"; //$NON-NLS-1$

    public static final String OP_CASTAS = "OP_CASTAS"; //$NON-NLS-1$
    public static final String OP_OR = "OP_OR"; //$NON-NLS-1$
    public static final String OP_IDIV = "OP_IDIV"; //$NON-NLS-1$
    public static final String OP_DIV = "OP_DIV"; //$NON-NLS-1$
    public static final String OP_PLUS = "OP_PLUS"; //$NON-NLS-1$
    public static final String OP_MINUS = "OP_MINUS"; //$NON-NLS-1$
    public static final String OP_MULTIPLY = "OP_MULTIPLY"; //$NON-NLS-1$
    public static final String OP_GLT = "OP_GLT"; //$NON-NLS-1$
    public static final String OP_GLTE = "OP_GLTE"; //$NON-NLS-1$
    public static final String OP_GGT = "OP_GGT"; //$NON-NLS-1$
    public static final String OP_GGTE = "OP_GGTE"; //$NON-NLS-1$
    public static final String OP_GEQ = "OP_GEQ"; //$NON-NLS-1$
    public static final String OP_GNEQ = "OP_GNEQ"; //$NON-NLS-1$
    public static final String OP_LT = "OP_LT"; //$NON-NLS-1$
    public static final String OP_LTE = "OP_LTE"; //$NON-NLS-1$
    public static final String OP_GT = "OP_GT"; //$NON-NLS-1$
    public static final String OP_GTE = "OP_GTE"; //$NON-NLS-1$
    public static final String OP_EQ = "OP_EQ"; //$NON-NLS-1$
    public static final String OP_NEQ = "OP_NEQ"; //$NON-NLS-1$
    public static final String OP_IS = "OP_IS"; //$NON-NLS-1$
    public static final String OP_BEFORE = "OP_BEFORE"; //$NON-NLS-1$
    public static final String OP_AFTER = "OP_AFTER"; //$NON-NLS-1$
    public static final String OP_TREATAS = "OP_TREATAS"; //$NON-NLS-1$
    public static final String OP_TO = "OP_TO"; //$NON-NLS-1$
    public static final String OP_EXCEPT = "OP_EXCEPT"; //$NON-NLS-1$
    public static final String OP_UNION = "OP_UNION"; //$NON-NLS-1$
    public static final String OP_PIPE = "OP_PIPE"; //$NON-NLS-1$
    public static final String OP_AS = "OP_AS"; //$NON-NLS-1$
    public static final String OP_OF = "OP_OF"; //$NON-NLS-1$
    public static final String OP_MOD = "OP_MOD"; //$NON-NLS-1$
    public static final String OP_INSTANCEOF = "OP_INSTANCEOF"; //$NON-NLS-1$
    public static final String OP_AND = "OP_AND"; //$NON-NLS-1$
    public static final String OP_INTERSECT = "OP_INTERSECT"; //$NON-NLS-1$
    public static final String OP_CASTABLEAS = "OP_CASTABLEAS"; //$NON-NLS-1$

    public static final String VARREF = "VARREF"; //$NON-NLS-1$

    public static final String ST_ITEM = "ST_ITEM"; //$NON-NLS-1$
    public static final String ST_EMPTY = "ST_EMPTY"; //$NON-NLS-1$
    public static final String ST_ATOMICTYPE = "ST_ATOMICTYPE"; //$NON-NLS-1$
    public static final String ST_LPAR = "ST_LPAR"; //$NON-NLS-1$
    public static final String ST_RPAR = "ST_RPAR"; //$NON-NLS-1$

    public static final String KT_DOCUMENTNODE = "KT_DOCUMENTNODE"; //$NON-NLS-1$
    public static final String KT_ELEMENT = "KT_ELEMENT"; //$NON-NLS-1$
    public static final String KT_ATTRIBUTE = "KT_ATTRIBUTE"; //$NON-NLS-1$
    public static final String KT_SCHEMAELEMENT = "KT_SCHEMAELEMENT"; //$NON-NLS-1$
    public static final String KT_SCHEMAATTRIBUTE = "KT_SCHEMAATTRIBUTE"; //$NON-NLS-1$
    public static final String KT_TEXT = "KT_TEXT"; //$NON-NLS-1$
    public static final String KT_PI = "KT_PI"; //$NON-NLS-1$
    public static final String KT_COMMENT = "KT_COMMENT"; //$NON-NLS-1$
    public static final String KT_ANYKIND = "KT_ANYKIND"; //$NON-NLS-1$
    public static final String KT_WILDCARD = "KT_WILDCARD"; //$NON-NLS-1$
    public static final String KT_QNAME = "KT_QNAME"; //$NON-NLS-1$
    public static final String KT_NCNAME = "KT_NCNAME"; //$NON-NLS-1$
    public static final String KT_COMMA = "KT_COMMA"; //$NON-NLS-1$

    public static final String OCC_OPTIONAL = "OCC_OPTIONAL"; //$NON-NLS-1$
    public static final String OCC_ONEORMORE = "OCC_ONEORMORE"; //$NON-NLS-1$
    public static final String OCC_ZEROORMORE = "OCC_ZEROORMORE"; //$NON-NLS-1$

    public static final String PATH_SLASH = "PATH_SLASH"; //$NON-NLS-1$
    public static final String PATH_SLASHSLASH = "PATH_SLASHSLASH"; //$NON-NLS-1$
    public static final String PATH_CONTEXTITEM = "PATH_CONTEXTITEM"; //$NON-NLS-1$
    public static final String PATH_CHILD = "PATH_CHILD"; //$NON-NLS-1$
    public static final String PATH_DESCENDANT = "PATH_DESCENDANT"; //$NON-NLS-1$
    public static final String PATH_ATTRIBUTE = "PATH_ATTRIBUTE"; //$NON-NLS-1$
    public static final String PATH_SELF = "PATH_SELF"; //$NON-NLS-1$
    public static final String PATH_DESCENDANT_OR_SELF = "PATH_DESCENDANT_OR_SELF"; //$NON-NLS-1$
    public static final String PATH_FOLLOWING_SIBLING = "PATH_FOLLOWING_SIBLING"; //$NON-NLS-1$
    public static final String PATH_FOLLOWING = "PATH_FOLLOWING"; //$NON-NLS-1$
    public static final String PATH_PARENT = "PATH_PARENT"; //$NON-NLS-1$
    public static final String PATH_ANCESTOR = "PATH_ANCESTOR"; //$NON-NLS-1$
    public static final String PATH_PRECEDING_SIBLING = "PATH_PRECEDING_SIBLING"; //$NON-NLS-1$
    public static final String PATH_PRECEDING = "PATH_PRECEDING"; //$NON-NLS-1$
    public static final String PATH_ANCESTOR_OR_SELF = "PATH_ANCESTOR_OR_SELF"; //$NON-NLS-1$
    public static final String PATH_NAMETEST = "PATH_NAMETEST"; //$NON-NLS-1$
    public static final String PATH_ABBREVATTRIBUTE = "PATH_ABBREVATTRIBUTE"; //$NON-NLS-1$
    public static final String PATH_ABBREVPARENT = "PATH_ABBREVPARENT"; //$NON-NLS-1$

    public static final String XML_PI = "XML_PI"; //$NON-NLS-1$
    public static final String XML_ESCAPE_CLOSE_EXPR = "XML_ESCAPE_CLOSE_EXPR"; //$NON-NLS-1$
//	public static final String XML_ENTITY_REF = "XML_ENTITY_REF"; //$NON-NLS-1$
//	public static final String XML_TAG_ATTRIBUTE_NAME = "XML_TAG_ATTRIBUTE_NAME"; //$NON-NLS-1$
    public static final String XML_ATTR_CHAR = "XML_ATTR_CHAR"; //$NON-NLS-1$
    public static final String XML_CDATA = "XML_CDATA"; //$NON-NLS-1$
//	public static final String XML_EMPTY_TAG_CLOSE = "XML_EMPTY_TAG_CLOSE"; //$NON-NLS-1$
    public static final String XML_ESCAPE_START_EXPR = "XML_ESCAPE_START_EXPR"; //$NON-NLS-1$
    public static final String XML_START_ATTR_EXPR = "XML_START_ATTR_EXPR"; //$NON-NLS-1$
    public static final String XML_END_ATTR_VALUE = "XML_END_ATTR_VALUE"; //$NON-NLS-1$
    public static final String XML_ATTR_QUOT = "XML_ATTR_QUOT"; //$NON-NLS-1$
    public static final String XML_ATTR_APOS = "XML_ATTR_APOS"; //$NON-NLS-1$
    public static final String XML_COMMENT = "XML_COMMENT"; //$NON-NLS-1$
    public static final String XML_ESCAPE_APOS = "XML_ESCAPE_APOS"; //$NON-NLS-1$
    public static final String XML_CHAR_REF = "XML_CHAR_REF"; //$NON-NLS-1$
//	public static final String XML_TAG_CLOSE = "XML_TAG_CLOSE"; //$NON-NLS-1$
//	public static final String XML_TAG_OPEN = "XML_TAG_OPEN"; //$NON-NLS-1$
    public static final String XML_ESCAPE_QUOT = "XML_ESCAPE_QUOT"; //$NON-NLS-1$
//	public static final String XML_TAG_ATTRIBUTE_VALUE = "XML_TAG_ATTRIBUTE_VALUE"; //$NON-NLS-1$
//	public static final String XML_TAG_NAME = "XML_TAG_NAME"; //$NON-NLS-1$
//	public static final String XML_END_TAG_OPEN = "XML_END_TAG_OPEN"; //$NON-NLS-1$
    public static final String XML_ELEM_CONTENT_CHAR = "XML_ELEM_CONTENT_CHAR"; //$NON-NLS-1$
    public static final String XML_START_EXPR = "XML_START_EXPR";//$NON-NLS-1$
//	public static final String XML_ATTR_EQUAL = "XML_ATTR_EQUAL";//$NON-NLS-1$

    // XQuery Update Facility 1.0

    public static final String KW_REVALIDATION = "KW_REVALIDATION"; //$NON-NLS-1$
    public static final String KW_UPDATING = "KW_UPDATING"; //$NON-NLS-1$
    public static final String KW_INSERT = "KW_INSERT"; //$NON-NLS-1$
    public static final String KW_NODE = "KN_NODE"; //$NON-NLS-1$
    public static final String KW_NODES = "KN_NODES"; //$NON-NLS-1$
    public static final String KW_BEFORE = "KW_BEFORE"; //$NON-NLS-1$
    public static final String KW_AFTER = "KW_AFTER"; //$NON-NLS-1$
    public static final String KW_FIRST = "KW_FIRST"; //$NON-NLS-1$
    public static final String KW_LAST = "KW_LAST"; //$NON-NLS-1$
    public static final String KW_DELETE = "KW_DELETE"; //$NON-NLS-1$
    public static final String KW_REPLACE = "KW_REPLACE"; //$NON-NLS-1$
    public static final String KW_VALUE = "KW_VALUE"; //$NON-NLS-1$
    public static final String KW_WITH = "KW_WITH"; //$NON-NLS-1$
    public static final String KW_OF = "KW_OF"; //$NON-NLS-1$
    public static final String KW_COPY = "KW_COPY"; //$NON-NLS-1$
    public static final String KW_RENAME = "KW_RENAME"; //$NON-NLS-1$
    public static final String KW_MODIFY = "KW_MODIFY"; //$NON-NLS-1$
    public static final String KW_INTO = "KW_INTO"; //$NON-NLS-1$

    // XQuery Scripting Extension (W3C Working Draft 8 April 2010)
    public static final String KW_ASSIGNABLE = "KW_ASSIGNABLE"; //$NON-NLS-1$
    public static final String KW_UNASSIGNABLE = "KW_UNASSIGNABLE"; //$NON-NLS-1$
    public static final String KW_SIMPLE = "KW_SIMPLE"; //$NON-NLS-1$
    public static final String KW_SEQUENTIAL = "KW_SEQUENTIAL"; //$NON-NLS-1$
    public static final String KW_BLOCK = "KW_BLOCK"; //$NON-NLS-1$
    public static final String KW_EXIT = "KW_EXIT"; //$NON-NLS-1$
    public static final String KW_RETURNING = "KW_RETURNING"; //$NON-NLS-1$
    public static final String KW_WHILE = "KW_WHILE"; //$NON-NLS-1$

    // MarkLogic
    public static final String KW_PRIVATE = "KW_PRIVATE"; //$NON-NLS-1$

    // XQuery 1.1

    public static final String KW_GROUP = "KW_GROUP"; //$NON-NLS-1$

}
