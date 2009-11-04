/*******************************************************************************
 * Copyright (c) 2003-2009 Mark Logic, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 *******************************************************************************/
package org.eclipse.wst.xquery.marklogic.xcc;

public class Version {
    private static final int MAJOR = 1;
    private static final int MINOR = 0;
    private static final int PATCH = 0;

    private static final String VERSION_STR = "XQDT-" + MAJOR + "." + MINOR + "-" + PATCH;

    private Version() {
    }

    public static String getVersionString() {
        return (VERSION_STR);
    }

    public static int getVersionMajor() {
        return (MAJOR);
    }

    public static int getVersionMinor() {
        return (MINOR);
    }

    public static int getVersionPatch() {
        return (PATCH);
    }
}
