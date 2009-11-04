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
 * A named XDM variable (an XName / XdmValue pair).
 */
public interface XdmVariable {
    /**
     * The name of this variable.
     * 
     * @return The {@link XName} instance that represents the name of this value.
     */
    XName getName();

    /**
     * The value of this variable.
     * 
     * @return The {@link XdmValue} instance that represents the value of this variable.
     */
    XdmValue getValue();
}
