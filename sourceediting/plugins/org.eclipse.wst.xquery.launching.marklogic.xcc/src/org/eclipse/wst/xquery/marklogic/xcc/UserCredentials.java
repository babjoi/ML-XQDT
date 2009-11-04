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
package org.eclipse.wst.xquery.marklogic.xcc;

/**
 * This interface encapsulates a user identity. It is intended primarily for internal use.
 */
public interface UserCredentials {
    /**
     * The user name associated with this credential object.
     * 
     * @return The user name as a String.
     */
    String getUserName();

    /**
     * Returns an HTTP basic authentication string, suitable for use as the value of an HTTP
     * Authentication header.
     * 
     * @return An HTTP basic authentication header value.
     */
    String toHttpBasicAuth();
}
