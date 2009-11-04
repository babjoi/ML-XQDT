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
 * XDM type: xs:float.
 */
public interface XSFloat extends XdmAtomic {
    /**
     * The item's value as a {@link java.lang.Float}. Some precision may be lost by casting to the
     * Java type.
     * 
     * @see #asBigDecimal()
     * @return The value of this item as a Java Float object.
     */
    Float asFloat();

    /**
     * The item's value as a primitive float value. Some precision may be lost by casting to the
     * Java type.
     * 
     * @see #asBigDecimal()
     * @return The value of this item as a primitive Java float.
     */
    float asPrimitiveFloat();

    /**
     * The item's value as a {@link java.math.BigDecimal}. This value may preserve more precision
     * than {@link #asFloat()} or {@link #asPrimitiveFloat()}.
     * 
     * @return The value of this item as a Java BigDecimal object.
     */
    BigDecimal asBigDecimal();
}
