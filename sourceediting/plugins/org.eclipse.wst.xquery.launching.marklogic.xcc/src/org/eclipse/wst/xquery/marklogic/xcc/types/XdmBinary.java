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
 * An XDM-like binary() value (binary() is a Mark Logic extension).
 */
public interface XdmBinary extends XdmNode {
    /**
     * Buffers the binary() item from the server and converts it to a Java byte array. This method
     * may be invoked repeatedly. On subsequent invocations it will return the same array
     * constructed by the first invocation.<br>
     * <strong>NOTE:</strong> If the binary() item is large, it is possible that an OutOfMemory
     * error could result when invoking this method, which could result in your program crashing. If
     * you need to handle arbitrarily large binary() items, use the {@link #asInputStream()} method.
     * 
     * @return This item as a Java byte array.
     * @throws IllegalStateException
     *             If called after the InputStream has already been consumed.
     * @see #asInputStream()
     * @see #isCached()
     */
    byte[] asBinaryData();
}
