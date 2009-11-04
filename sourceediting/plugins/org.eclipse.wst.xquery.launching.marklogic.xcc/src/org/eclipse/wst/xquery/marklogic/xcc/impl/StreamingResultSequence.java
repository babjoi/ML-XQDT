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
package org.eclipse.wst.xquery.marklogic.xcc.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.http.MultipartBuffer;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.ResultChannelName;
import org.eclipse.wst.xquery.marklogic.xcc.ResultItem;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.StreamingResultException;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;
import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.SequenceImpl;


public class StreamingResultSequence extends AbstractResultSequence {
    private final SessionImpl session;
    private final MultipartBuffer mbuf;
    private final RequestOptions options;
    private final Logger logger;
    private final long startTime;
    private ServerConnection connection;
    private boolean closed = false;
    private int cursor = -1;
    private ResultItem currentItem = null;
    private IteratorAdapter currentIterator = null;

    public StreamingResultSequence(SessionImpl session, ServerConnection connection, MultipartBuffer mbuf,
            RequestOptions options, Logger logger) {
        this.session = session;
        this.connection = connection;
        this.mbuf = mbuf;
        this.options = options;
        this.logger = logger;

        startTime = System.currentTimeMillis();

        session.registerResultSequence(this);
    }

    // ----------------------------------------------------------
    // ResultSequence interface

    public int size() {
        return -1;
    }

    public boolean isCached() {
        return false;
    }

    public void close() {
        if (closed) {
            return;
        }

        closed = true;

        invalidateCurrentIterator();

        session.deRegisterResultSequence(this);

        long now = System.currentTimeMillis();

        try {
            mbuf.close();
        } catch (IOException e) {
            String msg = "IOException closing streaming ResultSequence: " + e.getMessage();

            logger.log(Level.WARNING, msg, e);

            throw new StreamingResultException(msg, this, e);
        }

        connection.setTimeoutTime(connection.getTimeoutMillis() - (now - startTime));
        connection.provider().returnConnection(connection, logger);

        connection = null;
    }

    public boolean isClosed() {
        return closed;
    }

    private boolean hasNext(IteratorAdapter it) {
        if (closed) {
            return false;
        }

        if (it != currentIterator) {
            invalidateCurrentIterator();
        }

        try {
            return (mbuf.hasNext());
        } catch (IOException e) {
            String msg = "IOException in streaming ResultSequence hasNext(): " + e.getMessage();

            logger.log(Level.SEVERE, msg, e);

            throw new StreamingResultException(msg, this, e);
        }
    }

    public boolean hasNext() {
        return (hasNext(null));
    }

    private ResultItem next(IteratorAdapter it) {
        assertNotClosed();

        if (it != currentIterator) {
            invalidateCurrentIterator();
        }

        cursor++;

        try {
            if (mbuf.hasNext()) {
                currentItem = instantiateResultItem(mbuf, cursor, options);
            } else {
                currentItem = null;
                cursor = -1;
            }
        } catch (IOException e) {
            String msg = "IOException instantiating ResultItem " + cursor + ": " + e.getMessage();

            logger.log(Level.SEVERE, msg, e);

            throw new StreamingResultException(msg, this, e);
        }

        return currentItem;
    }

    public ResultItem next() {
        return (next(null));
    }

    public ResultItem current() {
        assertNotClosed();

        if (currentItem == null) {
            throw new IllegalStateException("No current item");
        }

        return currentItem;
    }

    public ResultItem resultItemAt(int index) {
        assertNotClosed();

        if ((cursor == -1) || (index != cursor)) {
            throw new IllegalArgumentException("Index out of range or not current, index=" + index);
        }

        return currentItem;
    }

    public void rewind() {
        assertNotClosed();

        throw new IllegalStateException("Cannot rewind streaming result sequences");
    }

    public ResultSequence toCached() {
        assertNotClosed();

        if ((currentItem != null) && (!currentItem.isFetchable())) {
            next();
        }

        try {
            ResultSequence rs = new CachedResultSequence(mbuf, options);

            close();

            return rs;
        } catch (IOException e) {
            String msg = "IOException while caching streaming ResultSequence: " + e.getMessage();

            logger.log(Level.SEVERE, msg, e);

            throw new StreamingResultException(msg + e.getMessage(), this, e);
        }
    }

    public ResultItem[] toResultItemArray() {
        List<ResultItem> list = new ArrayList<ResultItem>();

        while (hasNext()) {
            ResultItem item = next();

            item.cache();

            list.add(item);
        }

        ResultItem[] array = new ResultItem[list.size()];

        list.toArray(array);

        close();

        return array;
    }

    public ResultSequence getChannel(ResultChannelName channel) {
        assertNotClosed();

        if (channel == ResultChannelName.PRIMARY) {
            return (this);
        }

        return new EmptyResultSequence(this);
    }

    // -----------------------------------------------------------------
    // XdmSequence interface

    public Iterator<ResultItem> iterator() {
        assertNotClosed();

        invalidateCurrentIterator();

        currentIterator = new IteratorAdapter(this);

        return (currentIterator);
    }

    public XdmItem itemAt(int index) {
        return (resultItemAt(index).getItem());
    }

    public boolean isEmpty() {
        return closed;
    }

    public XdmItem[] toArray() {
        ResultItem[] resultItems = toResultItemArray();
        XdmItem[] array = new XdmItem[resultItems.length];

        for (int i = 0; i < resultItems.length; i++) {
            array[i] = resultItems[i].getItem();
        }

        return array;
    }

    public String asString(String separator) {
        return toCached().asString(separator);
    }

    public String asString() {
        return (asString("\n"));
    }

    public String[] asStrings() {
        return SequenceImpl.asStringArray(this);
    }

    // -----------------------------------------------------------------
    // XdmValue interface

    public ValueType getValueType() {
        return (ValueType.SEQUENCE);
    }

    // -----------------------------------------------------------

    @Override
    public String toString() {
        return "StreamingResultSequence: closed=" + closed;
    }

    // -----------------------------------------------------------

    private void assertNotClosed() {
        if (closed) {
            throw new IllegalStateException("ResultSequence is closed");
        }
    }

    // -----------------------------------------------------------

    private void invalidateCurrentIterator() {
        if (currentIterator != null) {
            currentIterator.invalidate();
        }
    }

    private static class IteratorAdapter implements Iterator<ResultItem> {
        private StreamingResultSequence parent;
        private volatile boolean invalidated = false;

        public IteratorAdapter(StreamingResultSequence parent) {
            this.parent = parent;
        }

        // ------------------------------------------------------------
        // Iterator interface

        public boolean hasNext() {
            assertValid();

            return parent.hasNext(this);
        }

        public ResultItem next() {
            assertValid();

            ResultItem obj = parent.next(this);

            if (obj == null) {
                throw new NoSuchElementException("No more items in ResultSequence");
            }

            return obj;
        }

        public void remove() {
            throw new UnsupportedOperationException("ResultSequences are not mutable");
        }

        // ------------------------------------------------------------

        private void invalidate() {
            invalidated = true;
        }

        private void assertValid() {
            if (invalidated) {
                throw new ConcurrentModificationException("This Iterator has been invalidated");
            }
        }
    }
}
