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
package org.eclipse.wst.xquery.marklogic.xcc.types.impl;

import java.math.BigInteger;

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSInteger;


public class XsIntegerImpl extends AbstractStringItem implements XSInteger {
    private BigInteger value;

    public XsIntegerImpl(String value) {
        super(ValueType.XS_INTEGER, value);

        this.value = new BigInteger(value);
    }

    public XsIntegerImpl(Object value) {
        super(ValueType.XS_INTEGER, value.toString());

        if ((value instanceof Integer) || (value instanceof Long) || (value instanceof BigInteger)
                || (value instanceof String)) {
            this.value = new BigInteger(value.toString());
        } else {
            throw new IllegalArgumentException("Cannot construct XSInteger from " + value.getClass().getName());
        }
    }

    public BigInteger asBigInteger() {
        return (value);
    }

    public Long asLong() {
        return new Long(value.longValue());
    }

    public long asPrimitiveLong() {
        return value.longValue();
    }

    public Integer asInteger() {
        return Integer.valueOf(asString());
    }

    public int asPrimitiveInt() {
        return (int)asPrimitiveLong();
    }
}
