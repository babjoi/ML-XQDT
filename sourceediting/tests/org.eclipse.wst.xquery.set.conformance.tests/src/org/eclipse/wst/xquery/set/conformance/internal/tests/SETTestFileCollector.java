package org.eclipse.wst.xquery.set.conformance.internal.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.wst.xquery.internal.core.tests.CoreTestFileCollector;
import org.eclipse.wst.xquery.set.conformance.tests.SETConformanceTestPlugin;

public class SETTestFileCollector extends CoreTestFileCollector {

    @Override
    public Map<String, Object[]> collectTestFiles(String path) {
        Map<String, Object[]> map = new TreeMap<String, Object[]>(String.CASE_INSENSITIVE_ORDER);

        File dir = new File(path);
        try {
            recursive(dir, map, "");
        } catch (Exception e) {
            assertTrue("Caught " + e.getClass().getCanonicalName()
                    + " exception while traversing the Zorba rbkt test directory structure: " + e.getMessage(), false);
        }

        assertTrue("Can not find the test queries under: \"" + dir.getAbsolutePath()
                + "\". Are you sure that the following file provides an Ant build step on Hudson: "
                + "sourceediting/tests/" + SETConformanceTestPlugin.getDefault().getBundle().getBundleId()
                + "/ant/conformanceTestDownload.xml ?", map.size() > 0);

        return map;
    }

    @Override
    protected boolean isIgnored(String name) {
        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696713
        if ("fulltext".equals(name)) {
            return true;
        }
        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696716
        if ("HigherOrder".equals(name)) {
            return true;
        }
        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696717
        if ("gflwor04.xq".equals(name) || "gflwor05.xq".equals(name)) {
            return true;
        }

        return false;
    }
}
