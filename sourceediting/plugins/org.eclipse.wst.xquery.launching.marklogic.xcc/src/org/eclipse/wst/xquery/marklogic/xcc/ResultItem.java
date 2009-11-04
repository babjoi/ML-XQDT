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

import org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem;

/**
 * <p>
 * An {@link org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem} that is a member of a {@link ResultSequence}. The
 * values associated with ResultItem instances may be transient. ResultItem wraps an {@link XdmItem}
 * instance and also implements the {@link XdmItem} interface. Invoking the methods of the
 * {@link XdmItem} interface are passed through to the contained instance.
 * </p>
 * <p>
 * Because a {@link ResultSequence} may be streaming, the contained {@link XdmItem} may be have been
 * partially consumed. Use the {@link #isFetchable()} method to determine if it is safe to access
 * the value.
 * </p>
 * 
 * @see org.eclipse.wst.xquery.marklogic.xcc.types
 */
public interface ResultItem extends XdmItem {
    /**
     * Returns the actual {@link XdmItem} value wrapped by this ResultItem. The instance returned
     * may be tested with <code>instanceof</code>.
     * 
     * @return an instance of {@link XdmItem}.
     */
    XdmItem getItem();

    /**
     * The position (zero-based) of this ResultItem in its containing {@link ResultSequence}.
     * 
     * @return This ResultItem's positional index.
     */
    int getIndex();

    /**
     * Indicates if the value of this ResultItem may be fetched. For large values that are streamed,
     * this method will return false after {@link #asInputStream()} or {@link #asReader()} is
     * called.
     * 
     * @return true if the XdmItem may be fetched, false if not. This will always be true if
     *         isCached() is true. It will also always be true immediately after
     *         {@link org.eclipse.wst.xquery.marklogic.xcc.ResultSequence#next()} is called and returns this
     *         {@link XdmItem}.
     */
    boolean isFetchable();

    /**
     * If this item is not already cached, read it fully from the result stream so that it is
     * buffered in memory. If the item is already cached, this is a no-op.
     * 
     * @throws org.eclipse.wst.xquery.marklogic.xcc.exceptions.StreamingResultException
     *             If an IOException ocurrs reading the result data.
     */
    void cache();
}
