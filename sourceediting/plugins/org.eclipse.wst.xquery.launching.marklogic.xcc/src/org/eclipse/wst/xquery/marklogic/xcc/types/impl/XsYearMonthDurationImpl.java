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

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSYearMonthDuration;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmDuration;


public class XsYearMonthDurationImpl extends AbstractDurationItem implements XSYearMonthDuration {
    public XsYearMonthDurationImpl(String bodyString) {
        super(ValueType.XS_YEAR_MONTH_DURATION, bodyString);

        XdmDuration duration = asDuration();

        if ((duration.getDays() != 0) || (duration.getHours() != 0) || (duration.getMinutes() != 0)
                || (!duration.getSeconds().equals(new BigDecimal("0.0")))) {
            throw new IllegalArgumentException("Only Year and Month values are allowed");
        }
    }
}
