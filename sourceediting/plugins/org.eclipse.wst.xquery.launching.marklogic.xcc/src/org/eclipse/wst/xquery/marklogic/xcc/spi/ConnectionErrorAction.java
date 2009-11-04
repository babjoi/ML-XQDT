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
package org.eclipse.wst.xquery.marklogic.xcc.spi;

/**
 * This is a type-safe enumeration that defines the possible return values of
 * {@link ConnectionProvider#returnErrorConnection(ServerConnection, Throwable, java.util.logging.Logger)}
 * .
 */
public class ConnectionErrorAction {
    private String name;

    private ConnectionErrorAction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (name);
    }

    public static final ConnectionErrorAction FAIL = new ConnectionErrorAction("fail");
    public static final ConnectionErrorAction RETRY = new ConnectionErrorAction("retry");
}
