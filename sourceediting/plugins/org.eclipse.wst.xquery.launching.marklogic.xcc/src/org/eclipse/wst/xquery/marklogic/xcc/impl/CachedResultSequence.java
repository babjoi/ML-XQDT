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
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.wst.xquery.marklogic.http.MultipartBuffer;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.ResultChannelName;
import org.eclipse.wst.xquery.marklogic.xcc.ResultItem;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.SequenceImpl;


public class CachedResultSequence extends AbstractResultSequence {
    private final ArrayList<ResultItem> items = new ArrayList<ResultItem>();
    private boolean closed = false;
    private int cursor = -1;
    private final ResultSequence primary;

    // ------------------------------------------------------

    protected CachedResultSequence(ResultSequence primary) {
        // empty sequence constructor used to create empty ResultSequences

        this.primary = primary;
    }

    public CachedResultSequence(MultipartBuffer multipartBuffer, RequestOptions options) throws IOException {
        primary = this;

        int index = 0;

        while (multipartBuffer.hasNext()) {
            ResultItem item = instantiateResultItem(multipartBuffer, index, options);

            item.cache();
            items.add(item);
            index++;
        }
    }

    // ------------------------------------------------------

    public int size() {
        return items.size();
    }

    public boolean isCached() {
        return !closed;
    }

    public void close() {
        items.clear(); // let them be GC'ed
        cursor = -1;
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean hasNext() {
        if (closed) {
            return false;
        }

        if (size() == 0) {
            return false;
        }

        return (cursor + 1) < size();
    }

    public ResultItem next() {
        assertNotClosed();

        if (cursor >= (size() - 1)) {
            cursor = size();
            return null;
        }

        cursor++;

        return items.get(cursor);
    }

    public ResultItem current() {
        assertNotClosed();

        if ((cursor == -1) || (cursor == size())) {
            throw new IllegalStateException("Cursor is not valid");
        }

        return items.get(cursor);
    }

    public ResultItem resultItemAt(int index) {
        assertNotClosed();

        if ((index < 0) || (index >= size())) {
            throw new IllegalArgumentException("Index out of range: size=" + size() + ", requested=" + index);
        }

        return items.get(index);
    }

    public void rewind() {
        assertNotClosed();

        cursor = -1;
    }

    public Iterator<ResultItem> iterator() {
        assertNotClosed();

        return Collections.unmodifiableList(items).iterator();
    }

    public ResultSequence toCached() {
        assertNotClosed();

        return this;
    }

    public ResultSequence getChannel(ResultChannelName channel) {
        assertNotClosed();

        if (channel == ResultChannelName.PRIMARY) {
            return (primary);
        }

        // Nothing else defined yet
        return new EmptyResultSequence(this);
    }

    public boolean isEmpty() {
        return (items.size() == 0);
    }

    public ResultItem[] toResultItemArray() {
        assertNotClosed();

        ResultItem[] array = new ResultItem[size()];

        items.toArray(array);

        return array;
    }

    public XdmItem[] toArray() {
        ResultItem[] resultItems = toResultItemArray();
        XdmItem[] array = new XdmItem[resultItems.length];

        for (int i = 0; i < resultItems.length; i++) {
            array[i] = resultItems[i].getItem();
        }

        return array;
    }

    public XdmItem itemAt(int index) {
        return (resultItemAt(index).getItem());
    }

    public ValueType getValueType() {
        return ValueType.SEQUENCE;
    }

    public String asString(String separator) {
        return SequenceImpl.asStringConcatenation(this, separator);
    }

    public String asString() {
        return (asString("\n"));
    }

    public String[] asStrings() {
        return SequenceImpl.asStringArray(this);
    }

    // -----------------------------------------------------------

    @Override
    public String toString() {
        return "CachedResultSequence: size=" + size() + ", closed=" + isClosed() + ", cursor=" + cursor;
    }

    // -----------------------------------------------------------

    private void assertNotClosed() {
        if (closed) {
            throw new IllegalStateException("ResultSequence is closed");
        }
    }
}
