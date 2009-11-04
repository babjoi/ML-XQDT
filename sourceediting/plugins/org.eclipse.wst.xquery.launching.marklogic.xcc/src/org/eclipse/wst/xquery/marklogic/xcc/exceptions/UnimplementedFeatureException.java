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
 * This exception indicates either that some planned feature is not yet implemented in the current
 * version of XCC, or that the server to which it is connecting does not support some the operation.
 */
public class UnimplementedFeatureException extends RuntimeException {
    private static final long serialVersionUID = -5553034179449649216L;

    public UnimplementedFeatureException(String message) {
        super(message);
    }
}
