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
import org.eclipse.wst.xquery.marklogic.xcc.types.XSDecimal;


public class XsDecimalImpl extends AbstractStringItem implements XSDecimal {
    private final BigDecimal value;

    public XsDecimalImpl(String bodyString) {
        super(ValueType.XS_DECIMAL, bodyString);

        this.value = new BigDecimal(bodyString);
    }

    public XsDecimalImpl(Object value) {
        super(ValueType.XS_DECIMAL, value.toString());

        if ((value instanceof Integer) || (value instanceof Long) || (value instanceof Double)
                || (value instanceof Float) || (value instanceof BigDecimal) || (value instanceof BigInteger)
                || (value instanceof String)) {
            this.value = new BigDecimal(value.toString());
        } else {
            throw new IllegalArgumentException("Cannot construct XSDecimal from " + value.getClass().getName());
        }
    }

    public BigDecimal asBigDecimal() {
        return value;
    }
}
