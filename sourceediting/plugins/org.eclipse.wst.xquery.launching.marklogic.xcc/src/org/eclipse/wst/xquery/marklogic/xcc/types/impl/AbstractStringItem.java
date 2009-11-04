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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.eclipse.wst.xquery.marklogic.xcc.types.ItemType;


abstract class AbstractStringItem extends AbstractItem {
    private final String value;

    protected AbstractStringItem(ItemType type, String value) {
        super(type);

        this.value = value;
    }

    public boolean isCached() {
        return (true);
    }

    public String asString() {
        return value;
    }

    public Reader asReader() {
        return new StringReader(value);
    }

    public InputStream asInputStream() {
        try {
            return new ByteArrayInputStream(value.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // This is unlikely to happen, UTF-8 is a required encoding
            return new ByteArrayInputStream(value.getBytes());
        }
    }

    // -------------------------------------------------

    protected String scrubbedFloatValue(String rawValue) {
        if (rawValue.equalsIgnoreCase("-INF"))
            return "-Infinity";
        if (rawValue.equalsIgnoreCase("+INF"))
            return "+Infinity";
        if (rawValue.equalsIgnoreCase("INF"))
            return "Infinity";

        return rawValue;
    }

    // -------------------------------------------------

    @Override
    public String toString() {
        return asString();
    }
}
