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
package org.eclipse.wst.xquery.marklogic.xcc.exceptions;

import org.eclipse.wst.xquery.marklogic.xcc.Request;

/**
 * This exception indicates a problem with the connection to the server. It most often is a wrapper
 * for an {@link java.io.IOException}.
 * 
 * @see #getCause()
 */
public class ServerConnectionException extends RequestException {
    private static final long serialVersionUID = 924777809560136542L;

    public ServerConnectionException(String message, Request request) {
        super(message, request);
    }

    public ServerConnectionException(String message, Request request, Throwable cause) {
        super(message, request, cause);
    }
}
