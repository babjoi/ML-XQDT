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

/**
 * Superclass of all exceptions specific to XCC. This is an abstract class and will never be thrown
 * directly. Only subclasses will be ever be thrown. You can name this class in a try/catch clause
 * to catch all checked XCC exceptions.
 */
public abstract class XccException extends Exception {
    private static final long serialVersionUID = 6808111270701351078L;

    protected XccException(String message) {
        super(message);
    }

    protected XccException(String message, Throwable cause) {
        super(message, cause);
    }
}
