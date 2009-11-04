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
 * A type-safe enumeration of {@link ResultSequence} channel names.
 * </p>
 * <p>
 * As of the 3.1 release, only the {@link #PRIMARY} channel will contain meaningful information. The
 * {@link #WARNINGS} channel is defiend but will always be empty. Additional channels may become
 * available in future releases.
 * </p>
 */
public class ResultChannelName {
    /** The primary result channel (the actual result). */
    public static final ResultChannelName PRIMARY = new ResultChannelName("primary");

    /**
     * Any warnings related to the result.
     */
    public static final ResultChannelName WARNINGS = new ResultChannelName("warnings");

//	/** Any log messages generated during execution of the query. */
//	public static final ResultChannelName LOG_OUTPUT = new ResultChannelName ("log output");
//
//	/** Any profiling information related to execution of the query. */
//	public static final ResultChannelName PROFILE_DATA = new ResultChannelName ("profile data");

    private String name;

    private ResultChannelName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (name);
    }
}
