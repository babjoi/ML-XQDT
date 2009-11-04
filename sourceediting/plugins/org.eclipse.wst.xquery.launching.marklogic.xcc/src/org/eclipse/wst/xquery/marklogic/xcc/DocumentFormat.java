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
 * Typesafe enumeration of allowable formats for document insertion.
 */
public final class DocumentFormat {
    /** Document format = XML node() */
    public static final DocumentFormat XML = new DocumentFormat("xml");

    /** Document format = text() */
    public static final DocumentFormat TEXT = new DocumentFormat("text()");

    /** Document format = binary() */
    public static final DocumentFormat BINARY = new DocumentFormat("binary()");

    /** Document format = none (use server default) */
    public static final DocumentFormat NONE = new DocumentFormat("(none)");

    private String name;

    private DocumentFormat(String name) {
        this.name = name;
    }

    /**
     * The name of this format: "xml", "text" or "binary".
     * 
     * @return The name of this format as a String, for diagnostic purposes.
     */
    @Override
    public String toString() {
        return (name);
    }
}
