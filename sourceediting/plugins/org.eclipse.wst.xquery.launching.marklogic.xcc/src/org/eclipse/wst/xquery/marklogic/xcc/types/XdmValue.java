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

/**
 * Base interface for XML Data Model (XDM) values represented as Java objects.
 */
public interface XdmValue {
    /**
     * The XQuery type specification instance for this value.
     * 
     * @return An instance of {@link ValueType} that represents the XQuery Schema type of this
     *         value.
     */
    ValueType getValueType();

    /**
     * Return a String representation of this item, if possible.
     * 
     * @return A Java String representation of this value. Note that this is similar but not exactly
     *         equivalent to toString(). The toString() method will always return a printable
     *         String. This method may throw an exception for some types (such as binary()).
     * @throws UnsupportedOperationException
     *             If the underlying value object cannot be represented as a String.
     */
    String asString();
}
