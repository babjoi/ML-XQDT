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

import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;

/**
 * This unchecked (runtime) exception may be thrown by some methods of streaming
 * {@link org.eclipse.wst.xquery.marklogic.xcc.ResultSequence} objects if an {@link java.io.IOException} occurs while
 * processing the streaming data.
 */
public class StreamingResultException extends RuntimeException {
    private static final long serialVersionUID = -8539650156181242034L;
    private final transient ResultSequence resultSequence;

    public StreamingResultException(String message, ResultSequence resultSequence, Throwable cause) {
        super(message, cause);

        this.resultSequence = resultSequence;
    }

    public StreamingResultException(ResultSequence resultSequence, Throwable cause) {
        super(cause);

        this.resultSequence = resultSequence;
    }

    public ResultSequence getResultSequence() {
        return resultSequence;
    }
}
