/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.core.tests;

import junit.framework.TestSuite;

import org.eclipse.wst.xquery.internal.core.tests.TestParser;

public class XQDTCoreTestSuite extends TestSuite {

    public static TestSuite suite() {
        TestSuite suite = new XQDTCoreTestSuite();
        return suite;
    }

    public XQDTCoreTestSuite() {
        super("org.eclipse.wst.xquery.tests: All XQDT Core plugin tests");

        addTestSuite(TestParser.class);
    }

}
