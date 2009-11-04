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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.eclipse.wst.xquery.marklogic.xcc.ResultItem;
import org.eclipse.wst.xquery.marklogic.xcc.types.ItemType;
import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.StreamableItem;


public class ResultItemImpl implements ResultItem {
    private final XdmItem value;
    private final int index;

    // -------------------------------------------------

    public ResultItemImpl(XdmItem value, int index) {
        this.value = value;
        this.index = index;
    }

    // -------------------------------------------------
    // ResultItem interface methods

    public XdmItem getItem() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public boolean isFetchable() {
        if (value.isCached()) {
            return true;
        }

        if (value instanceof StreamableItem) {
            StreamableItem sitem = (StreamableItem)value;

            return (sitem.isFetchable());
        }

        return (false);
    }

    public void cache() {
        if (!value.isCached()) {
            asString();
        }
    }

    // -------------------------------------------------
    // XdmItem interface adapter

    public ItemType getItemType() {
        return value.getItemType();
    }

    public Reader asReader() {
        return value.asReader();
    }

    public InputStream asInputStream() {
        return value.asInputStream();
    }

    public String asString() {
        return value.asString();
    }

    public boolean isCached() {
        return value.isCached();
    }

    public void writeTo(Writer writer) throws IOException {
        value.writeTo(writer);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        value.writeTo(outputStream);
    }

    // -------------------------------------------------
    // XdmValue interface adapter

    public ValueType getValueType() {
        return value.getValueType();
    }
}
