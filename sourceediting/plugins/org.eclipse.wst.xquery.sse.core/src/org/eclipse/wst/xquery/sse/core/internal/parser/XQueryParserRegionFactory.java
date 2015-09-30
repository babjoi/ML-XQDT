/*******************************************************************************
 * Copyright (c) 2001, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.parser;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegion;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * Factory for XQuery regions
 *
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQueryParserRegionFactory {

    // Constants

    /** XQuery 1.0 keywords */
    final static public Set<String> XQUERY10_KEYWORDS = new HashSet<String>();

    /** XQuery 1.1 keywords */
    final static public Set<String> XQUERY11_KEYWORDS = new HashSet<String>();

    /** XQuery Update Facility keywords */
    final static public Set<String> XQUERYUPDATE10_KEYWORDS = new HashSet<String>();

    /** XQuery Scripting Facility keywords */
    final static public Set<String> XQUERYSCRIPTING10_KEYWORDS = new HashSet<String>();

    static {
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_BOUNDARY_SPACE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_DECLARE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_DEFAULT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_ELEMENT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_EMPTY);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_ENCODING);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_FUNCTION);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_GREATEST);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_LEAST);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_MODULE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_NAMESPACE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_OPTION);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_ORDER);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_ORDERED);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_ORDERING);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_PRESERVE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_STRIP);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_UNORDERED);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_VERSION);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_XQUERY);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_LET);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_RETURN);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_SCHEMA);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_AT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_NOPRESERVE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_IMPORT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_COPYNAMESPACES);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_BASEURI);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_COLLATION);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_INHERIT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_NOINHERIT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_VARIABLE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_AS);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_EXTERNAL);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_FOR);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_CONSTRUCTION);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_IN);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_STABLE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_DESCENDING);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_ASCENDING);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_BY);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_WHERE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_LETASSIGN);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_SOME);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_EVERY);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_SATIFIES);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_IF);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_THEN);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_ELSE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_TYPESWITCH);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_CASE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_VALIDATE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_LAX);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_STRICT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_PI);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_ATTRIBUTE);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_COMMENT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_TEXT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_DOCUMENT);
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_DEFAULTFUNCTION);

        // Include MarkLogic Keywords
        XQUERY10_KEYWORDS.add(XQueryRegions.KW_PRIVATE);

        // Include operators which are not symbols
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_TO);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_TREATAS);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_AS);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_OF);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_INSTANCEOF);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_CASTABLEAS);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_CASTAS);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_UNION);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_NEQ);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_EQ);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_LT);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_LTE);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_GT);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_GTE);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_IS);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_OR);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_AND);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_DIV);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_IDIV);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_MOD);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_INTERSECT);
        XQUERY10_KEYWORDS.add(XQueryRegions.OP_EXCEPT);

        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_REVALIDATION);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_UPDATING);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_INSERT);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_NODE);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_NODES);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_BEFORE);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_AFTER);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_FIRST);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_LAST);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_DELETE);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_VALUE);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_REPLACE);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_WITH);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_OF);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_RENAME);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_COPY);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_MODIFY);
        XQUERYUPDATE10_KEYWORDS.add(XQueryRegions.KW_INTO);

        XQUERYSCRIPTING10_KEYWORDS.add(XQueryRegions.KW_ASSIGNABLE);
        XQUERYSCRIPTING10_KEYWORDS.add(XQueryRegions.KW_UNASSIGNABLE);
        XQUERYSCRIPTING10_KEYWORDS.add(XQueryRegions.KW_SIMPLE);
        XQUERYSCRIPTING10_KEYWORDS.add(XQueryRegions.KW_SEQUENTIAL);
        XQUERYSCRIPTING10_KEYWORDS.add(XQueryRegions.KW_BLOCK);
        XQUERYSCRIPTING10_KEYWORDS.add(XQueryRegions.KW_EXIT);
        XQUERYSCRIPTING10_KEYWORDS.add(XQueryRegions.KW_RETURNING);
        XQUERYSCRIPTING10_KEYWORDS.add(XQueryRegions.KW_WHILE);

        XQUERY11_KEYWORDS.add(XQueryRegions.KW_GROUP);
    }

    // Constructors

    public XQueryParserRegionFactory() {
        super();
    }

    // Methods

    public ITextRegion createToken(String type, int start, int textLength, int length, int lexicalState) {
        return new XQueryRegion(type, start, textLength, length, lexicalState, isKeyword(type));
    }

    // Helpers

    protected boolean isKeyword(String type) {
        return XQUERY10_KEYWORDS.contains(type) || XQUERYUPDATE10_KEYWORDS.contains(type)
                || XQUERYSCRIPTING10_KEYWORDS.contains(type) || XQUERY11_KEYWORDS.contains(type);
    }
}
