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

import java.math.BigInteger;

/**
 * XDM type: xs:integer.
 */
public interface XSInteger extends XdmAtomic {
    /**
     * @return The value of this item as a Biginteger object.
     */
    BigInteger asBigInteger();

    /**
     * @return The value of this item as a Long object. <strong>Warning:</strong> This method may
     *         truncate the XQuery integer value.
     */
    java.lang.Long asLong();

    /**
     * @return The value of this item as a Java primitive long value (64 bits).
     *         <strong>Warning:</strong> This method may truncate the XQuery integer value.
     */
    long asPrimitiveLong();

    /**
     * @return The value of this item as an Integer object. <strong>Warning:</strong> This method
     *         may truncate the XQuery integer value.
     */
    java.lang.Integer asInteger();

    /**
     * @return The value of this item as a Java primitive int value (32 bits).
     *         <strong>Warning:</strong> This method may truncate the XQuery integer value.
     */
    int asPrimitiveInt();
}
