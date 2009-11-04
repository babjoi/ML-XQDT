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
package org.eclipse.wst.xquery.marklogic.xcc.types;

/**
 * An Expanded Name, consisting of a (optional) namespace name and a local name. XName instances are
 * immutable and may be shared.
 */
public class XName {
    private final String namespace;
    private final String localname;
    private final String hashString;

    /**
     * Construct an XName with the given namespace name and local name.
     * 
     * @param namespace
     *            A namespace name as a String. A value of null indicate the default namespace.
     * @param localname
     *            The local name as a String.
     */
    public XName(String namespace, String localname) {
        this.namespace = namespace;
        this.localname = localname;

        hashString = (namespace == null) ? localname : (namespace + "#" + localname);
    }

    /**
     * Construct an XName with the given local name in the default namespace. This is equivalent to
     * <code>new XName (null, "somename")</code>
     * 
     * @param localname
     */
    public XName(String localname) {
        this(null, localname);
    }

    /**
     * This XName's namespace name, if defined.
     * 
     * @return The namespace name of this XName, or null for the default namespace.
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * This XName's local name.
     * 
     * @return The local name of this XName.
     */
    public String getLocalname() {
        return localname;
    }

    // -----------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof XName) {
            XName xname = (XName)obj;

            return (hashString.equals(xname.hashString));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return hashString.hashCode();
    }

    @Override
    public String toString() {
        return hashString;
    }
}
