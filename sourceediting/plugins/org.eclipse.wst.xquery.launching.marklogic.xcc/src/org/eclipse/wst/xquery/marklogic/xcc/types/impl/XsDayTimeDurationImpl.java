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

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSDayTimeDuration;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmDuration;

public class XsDayTimeDurationImpl extends AbstractDurationItem implements XSDayTimeDuration {
    public XsDayTimeDurationImpl(String bodyString) {
        super(ValueType.XS_DAY_TIME_DURATION, bodyString);

        XdmDuration duration = asDuration();

        if ((duration.getYears() != 0) || (duration.getMonths() != 0)) {
            throw new IllegalArgumentException("Only Day and Time values are allowed");
        }

    }
}
