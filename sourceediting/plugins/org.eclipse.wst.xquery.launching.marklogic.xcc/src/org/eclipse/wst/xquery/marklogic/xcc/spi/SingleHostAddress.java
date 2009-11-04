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

import java.net.InetSocketAddress;

/**
 * This interface should be implemented by {@link org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider}
 * implementations that make socket connections to a single host and port. The value returned by
 * {@link #getAddress()} will be used to reconstruct the connection {@link java.net.URI} when
 * needed. Custom {@link org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider} implementations that are not
 * point-to-point oriented should not implement this interface.
 */
public interface SingleHostAddress {
    /**
     * The address to which connections are made.
     * 
     * @return An instance of {@link java.net.InetSocketAddress} that represents host/port to which
     *         connections are made.
     */
    InetSocketAddress getAddress();
}
