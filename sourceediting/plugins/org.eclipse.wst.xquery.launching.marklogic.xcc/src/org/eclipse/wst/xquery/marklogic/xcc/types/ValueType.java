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
package org.eclipse.wst.xquery.marklogic.xcc.types;

/**
 * Base class for the typesafe enumeration objects that represent XML Schema types.
 */
public abstract class ValueType {
    private String name;

    ValueType(String name) {
        this.name = name;
    }

    /**
     * Indicates whether this value type represents a sequence or single value.
     * 
     * @return true if the type represented is a sequence, false otherwise.
     */
    public abstract boolean isSequence();

    /**
     * The name of this XQuery type, as a string.
     * 
     * @return A string represntation of this type.
     */
    @Override
    public String toString() {
        return (name);
    }

    // -----------------------------------------------------

    public static final ValueType SEQUENCE = new SequenceType("(sequence)");

    public static final NodeType NODE = new NodeType("node()");
    public static final NodeType ELEMENT = new NodeType("element()");
    public static final NodeType TEXT = new NodeType("text()");
    public static final NodeType BINARY = new NodeType("binary()");

    public static final AtomicType XS_UNTYPED_ATOMIC = new AtomicType("xs:untypedAtomic");
    public static final AtomicType XS_STRING = new AtomicType("xs:string");
    public static final AtomicType XS_BOOLEAN = new AtomicType("xs:boolean");
    public static final AtomicType XS_INTEGER = new AtomicType("xs:integer");
    public static final AtomicType XS_DECIMAL = new AtomicType("xs:decimal");
    public static final AtomicType XS_FLOAT = new AtomicType("xs:float");
    public static final AtomicType XS_DOUBLE = new AtomicType("xs:double");
    public static final AtomicType XS_DURATION = new AtomicType("xs:duration");
    public static final AtomicType XS_DAY_TIME_DURATION = new AtomicType("xs:dayTimeDuration");
    public static final AtomicType XS_YEAR_MONTH_DURATION = new AtomicType("xs:yearMonthDuration");
    public static final AtomicType XS_DATE_TIME = new AtomicType("xs:dateTime");
    public static final AtomicType XS_TIME = new AtomicType("xs:time");
    public static final AtomicType XS_DATE = new AtomicType("xs:date");
    public static final AtomicType XS_ANY_URI = new AtomicType("xs:anyURI");
    public static final AtomicType XS_QNAME = new AtomicType("xs:QName");
    public static final AtomicType XS_GDAY = new AtomicType("xs:gDay");
    public static final AtomicType XS_GMONTH = new AtomicType("xs:gMonth");
    public static final AtomicType XS_GMONTH_DAY = new AtomicType("xs:gMonthDay");
    public static final AtomicType XS_GYEAR = new AtomicType("xs:gYear");
    public static final AtomicType XS_GYEAR_MONTH = new AtomicType("xs:gYearMonth");
    public static final AtomicType XS_HEX_BINARY = new AtomicType("xs:hexBinary");
    public static final AtomicType XS_BASE64_BINARY = new AtomicType("xs:base64Binary");

    /** @deprecated To be phased out */
    @Deprecated
    public static final AtomicType XDT_UNTYPED_ATOMIC = new AtomicType("xdt:untypedAtomic");
    /** @deprecated To be phased out */
    @Deprecated
    public static final AtomicType XDT_DAY_TIME_DURATION = new AtomicType("xdt:dayTimeDuration");
    /** @deprecated To be phased out */
    @Deprecated
    public static final AtomicType XDT_YEAR_MONTH_DURATION = new AtomicType("xdt:yearMonthDuration");
}
