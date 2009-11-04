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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.eclipse.wst.xquery.marklogic.xcc.types.ItemType;
import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem;


abstract class AbstractItem implements XdmItem {
    private final ItemType type;

    public AbstractItem(ItemType type) {
        this.type = type;
    }

    // -----------------------------------------------

    // defined in XdmItem interface and thus implicitly abstract
//	abstract public Reader asReader();
//	abstract public InputStream asInputStream();

    // -----------------------------------------------

    public void writeTo(Writer writer) throws IOException {
        Reader reader = asReader();
        char[] buffer = new char[64 * 1024];
        int rc;

        while ((rc = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, rc);
        }

        writer.flush();
        reader.close();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        InputStream inputStream = asInputStream();
        byte[] buffer = new byte[64 * 1024];
        int rc;

        while ((rc = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, rc);
        }

        outputStream.flush();
        inputStream.close();
    }

    // -----------------------------------------------

    public ValueType getValueType() {
        return type;
    }

    public ItemType getItemType() {
        return type;
    }

    // -----------------------------------------------
}
