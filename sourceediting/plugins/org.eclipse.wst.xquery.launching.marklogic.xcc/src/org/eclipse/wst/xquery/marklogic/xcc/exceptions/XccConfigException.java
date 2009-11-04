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
 * This exception indicates a configuration problem.
 */
public class XccConfigException extends XccException {
    private static final long serialVersionUID = 8668085092026412433L;

    public XccConfigException(String message) {
        super(message);
    }

    public XccConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
