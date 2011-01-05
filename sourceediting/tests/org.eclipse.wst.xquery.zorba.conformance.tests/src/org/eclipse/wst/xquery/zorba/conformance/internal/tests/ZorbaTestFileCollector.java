package org.eclipse.wst.xquery.zorba.conformance.internal.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.wst.xquery.zorba.conformance.tests.ZorbaConformanceTestPlugin;

public class ZorbaTestFileCollector extends TestFileCollector {

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
                + "sourceediting/tests/" + ZorbaConformanceTestPlugin.getDefault().getBundle().getBundleId()
                + "/ant/conformanceTestDownload.xml ?", map.size() > 0);

        return map;
    }

    protected void recursive(File testDir, Map<String, Object[]> map, String relativeName) {
        File[] files = testDir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (isIgnored(file.getName())) {
                continue;
            }
            if (file.isDirectory()) {
                recursive(file, map, relativeName + file.getName() + "/");
            } else {
                if (file.getName().endsWith(".xq")) {
                    String name = relativeName + file.getName();
                    String path = file.getAbsolutePath();
                    String spec = path.substring(0, path.length() - 3) + ".spec";
                    if (!(new File(spec).exists())) {
                        spec = null;
                    }
                    map.put(name, new Object[] { path, spec });
                }
            }
        }
    }

    protected boolean isIgnored(String name) {
        // first, ignore the .svn directories
        if (".svn".equals(name)) {
            return true;
        }

        // now ignore tests with problems; all have a TODO link to the corresponding bug

        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/697024
        if ("syntax-error-01.xq".equals(name)) {
            return true;
        }
        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/697021
        if ("annotations".equals(name) || "introsp-fn-annot-1.xq".equals(name)) {
            return true;
        }
        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696713
        if ("fulltext".equals(name)) {
            return true;
        }
        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696717
        if ("allowing-empty".equals(name) || "gflwor04.xq".equals(name) || "gflwor05.xq".equals(name)) {
            return true;
        }
        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696716
        if ("HigherOrder".equals(name)) {
            return true;
        }

        return false;
    }
}
