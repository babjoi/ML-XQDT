/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.xcc.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import org.eclipse.wst.xquery.marklogic.http.MultipartBuffer;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.ResultItem;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmNode;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.BinaryImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.ElementImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.TextImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsAnyUriImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsBase64BinaryImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsBooleanImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDateImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDateTimeImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDayTimeDurationImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDecimalImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDoubleImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDurationImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsFloatImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGDayImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGMonthDayImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGMonthImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGYearImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGYearMonthImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsHexBinaryImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsIntegerImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsQNameImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsStringImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsTimeImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsUntypedAtomicImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsYearMonthDurationImpl;


abstract class AbstractResultSequence implements ResultSequence {
    protected static final Set<String> stringConstructedTypes = new HashSet<String>();

    static {
        stringConstructedTypes.add("string");
        stringConstructedTypes.add("integer");
        stringConstructedTypes.add("anyURI");
        stringConstructedTypes.add("QName");
        stringConstructedTypes.add("boolean");
        stringConstructedTypes.add("decimal");
        stringConstructedTypes.add("double");
        stringConstructedTypes.add("float");
        stringConstructedTypes.add("untypedAtomic");
        stringConstructedTypes.add("anySimpleType");
        stringConstructedTypes.add("date");
        stringConstructedTypes.add("dateTime");
        stringConstructedTypes.add("time");
        stringConstructedTypes.add("gDay");
        stringConstructedTypes.add("gMonth");
        stringConstructedTypes.add("gMonthDay");
        stringConstructedTypes.add("gYear");
        stringConstructedTypes.add("gYearMonth");
        stringConstructedTypes.add("duration");
        stringConstructedTypes.add("dayTimeDuration");
        stringConstructedTypes.add("yearMonthDuration");
        stringConstructedTypes.add("base64Binary");
        stringConstructedTypes.add("hexBinary");
    }

    protected AbstractResultSequence() {
    }

    // ----------------------------------------------------------------

    protected ResultItem instantiateResultItem(MultipartBuffer mbuf, int index, RequestOptions options)
            throws IOException {
        XdmItem item = instantiateXdmItem(mbuf, options);

        return new ResultItemImpl(item, index);
    }

    private XdmItem instantiateXdmItem(MultipartBuffer mbuf, RequestOptions options) throws IOException {
        TimeZone timezone = options.getTimeZone();
        Locale locale = options.getLocale();
        boolean cache = options.getCacheResult();
        mbuf.next();

        // Is Content-Type used for anything anymore?
        String contentType = mbuf.getHeader("content-type");

        if (contentType == null) {
            throw new IllegalStateException("No content-type header in part");
        }

        String primitive = mbuf.getHeader("x-primitive");

        if ((primitive == null) || (primitive.length() == 0)) {
            throw new IllegalStateException("Result item has no x-primitive header value");
        }

        if (stringConstructedTypes.contains(primitive)) {
            return (instantiateTypeFromString(primitive, mbuf.getBodyAsString(), timezone, locale));
        }

        return nodeFactory(primitive, mbuf, cache);
    }

    private XdmNode nodeFactory(String type, MultipartBuffer mbuf, boolean cache) throws IOException {
        if (cache) {
            if (type.equals("text()"))
                return (new TextImpl(mbuf.getBodyAsString()));
            if (type.equals("binary()"))
                return (new BinaryImpl(mbuf.getBodyStream(), true));

            // TODO: need finer granularity here (attributes, comments, etc), depends on server
            if (type.equals("node()"))
                return new ElementImpl(mbuf.getBodyAsString());
        } else {
            if (type.equals("text()"))
                return (new TextImpl(mbuf.getBodyStream()));
            if (type.equals("binary()"))
                return (new BinaryImpl(mbuf.getBodyStream(), false));

            if (type.equals("node()"))
                return new ElementImpl(mbuf.getBodyStream());
        }

        throw new IOException("Nodes of type '" + type + "' are not supported in XCC result sequences");
    }

    private XdmItem instantiateTypeFromString(String typeName, String bodyString, TimeZone timezone, Locale locale)
            throws IOException {
        if (typeName.equals("string")) {
            return (new XsStringImpl(bodyString));
        }
        if (typeName.equals("integer")) {
            return (new XsIntegerImpl(bodyString));
        }
        if (typeName.equals("anyURI")) {
            return (new XsAnyUriImpl(bodyString));
        }
        if (typeName.equals("QName")) {
            return (new XsQNameImpl(bodyString));
        }
        if (typeName.equals("boolean")) {
            return (new XsBooleanImpl(bodyString));
        }
        if (typeName.equals("decimal")) {
            return (new XsDecimalImpl(bodyString));
        }
        if (typeName.equals("double")) {
            return (new XsDoubleImpl(bodyString));
        }
        if (typeName.equals("float")) {
            return (new XsFloatImpl(bodyString));
        }
        if (typeName.equals("base64Binary")) {
            return (new XsBase64BinaryImpl(bodyString));
        }
        if (typeName.equals("hexBinary")) {
            return (new XsHexBinaryImpl(bodyString));
        }
        if (typeName.equals("untypedAtomic")) {
            return (new XsUntypedAtomicImpl(bodyString));
        }
        if (typeName.equals("anySimpleType")) {
            return (new XsUntypedAtomicImpl(bodyString)); // note: treated as xs:untypedAtomic
        }
        if (typeName.equals("date")) {
            return (new XsDateImpl(bodyString, timezone, locale));
        }
        if (typeName.equals("dateTime")) {
            return (new XsDateTimeImpl(bodyString, timezone, locale));
        }
        if (typeName.equals("time")) {
            return (new XsTimeImpl(bodyString, timezone, locale));
        }
        if (typeName.equals("gDay")) {
            return (new XsGDayImpl(bodyString, timezone, locale));
        }
        if (typeName.equals("gMonth")) {
            return (new XsGMonthImpl(bodyString, timezone, locale));
        }
        if (typeName.equals("gMonthDay")) {
            return (new XsGMonthDayImpl(bodyString, timezone, locale));
        }
        if (typeName.equals("gYear")) {
            return (new XsGYearImpl(bodyString, timezone, locale));
        }
        if (typeName.equals("gYearMonth")) {
            return (new XsGYearMonthImpl(bodyString, timezone, locale));
        }
        if (typeName.equals("duration")) {
            return (new XsDurationImpl(bodyString));
        }
        if (typeName.equals("dayTimeDuration")) {
            return (new XsDayTimeDurationImpl(bodyString));
        }
        if (typeName.equals("yearMonthDuration")) {
            return (new XsYearMonthDurationImpl(bodyString));
        }

        throw new IOException("Unrecognized atomic item type: " + typeName);
    }
}
