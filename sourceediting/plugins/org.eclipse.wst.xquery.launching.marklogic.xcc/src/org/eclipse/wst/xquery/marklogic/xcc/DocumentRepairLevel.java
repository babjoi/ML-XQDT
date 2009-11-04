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
 * <p>
 * A typesafe enumeration class defining load-time document repair levels.
 * </p>
 */
public final class DocumentRepairLevel {
    /** No automatic repair, insert as-is or error out if not well formed. */
    public static final DocumentRepairLevel NONE = new DocumentRepairLevel("none");

    /** Repair document to make it well formed, if possible. */
    public static final DocumentRepairLevel FULL = new DocumentRepairLevel("full");

    /**
     * Use server's default repair mode, which depends on the App Server default XQuery version
     * setting: 'none' for 1.0-ml or 1.0, 'full' for 0.9-ml or for MarkLogic Server releases prior
     * to 4.0.
     */
    public static final DocumentRepairLevel DEFAULT = new DocumentRepairLevel("default");

    private String name;

    private DocumentRepairLevel(String name) {
        this.name = name;
    }

    /**
     * The name of this repair level: "none" or "full".
     * 
     * @return The name of this document repair level, for diagnostic purposes.
     */
    @Override
    public String toString() {
        return (name);
    }
}
