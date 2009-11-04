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
 * XDM type: xs:double.
 */
public interface XSDouble extends XdmAtomic {
    /**
     * The item's value as a {@link java.lang.Double}. Some precision may be lost by casting to the
     * Java type.
     * 
     * @see #asBigDecimal()
     * @return The value of this item as a Java Double object.
     */
    java.lang.Double asDouble();

    /**
     * The item's value as a primitive double value. Some precision may be lost by casting to the
     * Java type.
     * 
     * @see #asBigDecimal()
     * @return The value of this item as a primitive Java double.
     */
    double asPrimitiveDouble();

    /**
     * The item's value as a {@link java.math.BigDecimal}. This value may preserve more precision
     * than {@link #asDouble()} or {@link #asPrimitiveDouble()}.
     * 
     * @return The value of this item as a Java BigDecimal object.
     */
    BigDecimal asBigDecimal();
}
