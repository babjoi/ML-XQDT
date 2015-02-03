package org.eclipse.wst.xquery.internal.core.tests;

import java.util.Map;

public abstract class TestFileCollector {

    public abstract Map<String, Object[]> collectTestFiles(String queryDirPath);

}