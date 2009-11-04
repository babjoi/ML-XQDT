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

/**
 * A typesafe enumeration class defining permission capability values.
 */
public final class ContentCapability {
    /** Reads are allowed */
    public static final ContentCapability READ = new ContentCapability("read", "R");

    /** Inserts are allowed */
    public static final ContentCapability INSERT = new ContentCapability("insert", "I");

    /** Updates are allowed */
    public static final ContentCapability UPDATE = new ContentCapability("update", "U");

    /** Execution is allowed */
    public static final ContentCapability EXECUTE = new ContentCapability("execute", "E");

    private String name;
    private String symbol;

    private ContentCapability(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Used internally.
     * 
     * @return A single-char encoding symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * A human-readable name.
     * 
     * @return A String name for this capability.
     */
    @Override
    public String toString() {
        return name;
    }
}
