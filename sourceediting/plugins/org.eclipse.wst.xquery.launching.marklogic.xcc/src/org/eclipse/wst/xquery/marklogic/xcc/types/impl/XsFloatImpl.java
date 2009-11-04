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
import org.eclipse.wst.xquery.marklogic.xcc.types.XSFloat;


public class XsFloatImpl extends AbstractStringItem implements XSFloat {
    private final Float value;
    private final BigDecimal bigDecimalValue;
    private final NumberFormatException formatException;

    public XsFloatImpl(String bodyString) {
        super(ValueType.XS_FLOAT, bodyString);

        this.value = Float.valueOf(scrubbedFloatValue(bodyString));

        BigDecimal tmpBigDecimal = null;
        NumberFormatException tmpEx = null;

        try {
            tmpBigDecimal = new BigDecimal(bodyString);
        } catch (NumberFormatException e) {
            // Float value parsed, must be NaN or +-INF, leave as null
            tmpEx = e;
        }

        this.bigDecimalValue = tmpBigDecimal;
        this.formatException = tmpEx;
    }

    public XsFloatImpl(Object value) {
        super(ValueType.XS_FLOAT, value.toString());

        if ((value instanceof Integer) || (value instanceof Long) || (value instanceof Double)
                || (value instanceof Float) || (value instanceof BigDecimal) || (value instanceof BigInteger)
                || (value instanceof String)) {
            this.value = new Float(value.toString());

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
            throw new IllegalArgumentException("Cannot construct XSFloat from " + value.getClass().getName());
        }
    }

    public Float asFloat() {
        return value;
    }

    public float asPrimitiveFloat() {
        return value.floatValue();
    }

    public BigDecimal asBigDecimal() {
        if (bigDecimalValue == null) {
            throw formatException;
        }

        return bigDecimalValue;
    }
}
