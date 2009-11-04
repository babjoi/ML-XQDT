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
 * XDM type: xs:boolean.
 */
public interface XSBoolean extends XdmAtomic {
    /**
     * @return The value of this item as a Boolean object.
     */
    java.lang.Boolean asBoolean();

    /**
     * @return The value of this item as a Java primitive boolean value.
     */
    boolean asPrimitiveBoolean();
}
