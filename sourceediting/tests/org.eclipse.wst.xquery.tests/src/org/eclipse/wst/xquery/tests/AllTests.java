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
package org.eclipse.wst.xquery.tests;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("org.eclipse.wst.xquery.tests: All XQDT plugin tests");
        //$JUnit-BEGIN$

//        suite.addTest(XQDTCoreTestSuite.suite());
        suite.addTest(new MyTest());

        //$JUnit-END$
        return suite;
    }
}

class MyTest implements Test {

    public int countTestCases() {
        return 0;
    }

    public void run(TestResult result) {
    }

}