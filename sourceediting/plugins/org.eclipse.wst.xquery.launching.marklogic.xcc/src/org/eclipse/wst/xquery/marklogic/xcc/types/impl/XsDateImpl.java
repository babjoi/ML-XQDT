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

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSDate;


public class XsDateImpl extends AbstractDateItem implements XSDate {
    public XsDateImpl(String bodyString, TimeZone tz, Locale locale) {
        super(ValueType.XS_DATE, bodyString, tz, locale);

        dateFromDateString(bodyString); // will throw if string is not valid
    }

    public Date asDate() {
        return dateFromDateString(asString());
    }
}
