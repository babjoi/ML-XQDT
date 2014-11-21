package org.eclipse.wst.xquery.zorba.conformance.internal.tests;

import java.util.Map;

public abstract class TestFileCollector {

    public abstract Map<String, Object[]> collectTestFiles(String queryDirPath);

}
