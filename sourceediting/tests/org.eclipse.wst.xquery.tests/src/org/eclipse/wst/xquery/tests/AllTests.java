package org.eclipse.wst.xquery.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.wst.xquery.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(SamplePluginTest.class);
		//$JUnit-END$
		return suite;
	}

}
