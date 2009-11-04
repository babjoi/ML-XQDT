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
package org.eclipse.wst.xquery.marklogic.xcc.types.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmSequence;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmValue;


public class SequenceImpl implements XdmSequence<XdmItem> {
    private List<XdmItem> items = new ArrayList<XdmItem>();

    // -------------------------------------------------------
    // XdmSequence interface

    public SequenceImpl(XdmValue[] values) {
        flattenValues(items, values);
    }

    private void flattenValues(List<XdmItem> items, XdmValue[] values) {
        for (int i = 0; i < values.length; i++) {
            XdmValue value = values[i];

            if (value instanceof XdmSequence<?>) {
                XdmSequence<?> seq = (XdmSequence<?>)value;

                flattenValues(items, seq.toArray());
            } else {
                items.add((XdmItem)value);
            }
        }
    }

    // -------------------------------------------------------

    public int size() {
        return (items.size());
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public XdmItem[] toArray() {
        XdmItem[] array = new XdmItem[items.size()];

        items.toArray(array);

        return (array);
    }

    public XdmItem itemAt(int index) {
        return items.get(index);
    }

    public Iterator<XdmItem> iterator() {
        return (Collections.unmodifiableList(items).iterator());
    }

    public String asString(String separator) {
        return asStringConcatenation(this, separator);
    }

    public String asString() {
        return asString("\n");
    }

    public String[] asStrings() {
        return asStringArray(this);
    }

    // ------------------------------------------------------------
    // Helper functions for stringifying XdmSequences, used by ResultSequences

    public static String asStringConcatenation(XdmSequence<?> sequence, String separator) {
        StringBuffer sb = new StringBuffer(512);
        String[] strings = sequence.asStrings();

        for (int i = 0; i < strings.length; i++) {
            if (i != 0) {
                sb.append(separator);
            }

            sb.append(strings[i]);
        }

        return sb.substring(0);
    }

    public static String[] asStringArray(XdmSequence<?> sequence) {
        XdmItem[] items = sequence.toArray();
        String[] strings = new String[items.length];

        for (int i = 0; i < items.length; i++) {
            strings[i] = items[i].asString();
        }

        return (strings);
    }

    // -------------------------------------------------------
    // XdmValue interface

    public ValueType getValueType() {
        return (ValueType.SEQUENCE);
    }

    // -------------------------------------------------------

    @Override
    public String toString() {
        return "XdmSequence: size=" + size();
    }
}
