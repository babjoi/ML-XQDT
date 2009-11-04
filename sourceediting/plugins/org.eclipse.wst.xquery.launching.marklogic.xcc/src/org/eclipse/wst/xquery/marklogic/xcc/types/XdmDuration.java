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

import java.math.BigDecimal;

/**
 * A Java representation of xs:duration.
 */
public interface XdmDuration {
    boolean isPositive();

    boolean isNegative();

    int getYears();

    int getMonths();

    int getDays();

    int getHours();

    int getMinutes();

    long getWholeSeconds();

    BigDecimal getSeconds();
}
