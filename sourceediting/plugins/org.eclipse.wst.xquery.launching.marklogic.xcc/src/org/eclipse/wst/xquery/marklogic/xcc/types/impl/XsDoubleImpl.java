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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSDouble;


public class XsDoubleImpl extends AbstractStringItem implements XSDouble {
    private final Double value;
    private final BigDecimal bigDecimalValue;
    private final NumberFormatException formatException;

    public XsDoubleImpl(String bodyString) {
        super(ValueType.XS_DOUBLE, bodyString);

        this.value = Double.valueOf(scrubbedFloatValue(bodyString));

        BigDecimal tmpBigDecimal = null;
        NumberFormatException tmpEx = null;

        try {
            tmpBigDecimal = new BigDecimal(bodyString);
        } catch (NumberFormatException e) {
            // Double value parsed, must be NaN or +-INF, leave as null
            tmpEx = e;
        }

        this.bigDecimalValue = tmpBigDecimal;
        this.formatException = tmpEx;
    }

    public XsDoubleImpl(Object value) {
        super(ValueType.XS_DOUBLE, value.toString());

        if ((value instanceof Integer) || (value instanceof Long) || (value instanceof Double)
                || (value instanceof Float) || (value instanceof BigDecimal) || (value instanceof BigInteger)
                || (value instanceof String)) {
            this.value = new Double(value.toString());

            BigDecimal tmpBigDecimal = null;
            NumberFormatException tmpEx = null;

            try {
                tmpBigDecimal = new BigDecimal(value.toString());
            } catch (NumberFormatException e) {
                tmpEx = e;
            }

            this.bigDecimalValue = tmpBigDecimal;
            this.formatException = tmpEx;
        } else {
            throw new IllegalArgumentException("Cannot construct XSDouble from " + value.getClass().getName());
        }
    }

    public Double asDouble() {
        return (value);
    }

    public double asPrimitiveDouble() {
        return value.doubleValue();
    }

    public BigDecimal asBigDecimal() {
        if (bigDecimalValue == null) {
            throw formatException;
        }

        return bigDecimalValue;
    }
}
