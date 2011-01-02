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
package org.eclipse.wst.xquery.set.conformance.internal.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.wst.xquery.set.conformance.tests.SETConformanceTestPlugin;
import org.eclipse.wst.xquery.zorba.conformance.internal.tests.ZorbaRbktTestSuite;
import org.eclipse.wst.xquery.zorba.conformance.tests.LabeledParameterized;
import org.eclipse.wst.xquery.zorba.conformance.tests.LabeledParameterized.LabeledParameters;
import org.junit.runner.RunWith;

@RunWith(LabeledParameterized.class)
public class SETRbktTestSuite extends ZorbaRbktTestSuite {

    private static final String QUERY_DIR_PATH = "downloads" + File.separator + "sausalito_test_queries";

    public SETRbktTestSuite(String fXqFile, String specFile) {
        super(fXqFile, specFile);
    }

    @LabeledParameters
    public static Map<String, Object[]> files() {
        Map<String, Object[]> map = new TreeMap<String, Object[]>(String.CASE_INSENSITIVE_ORDER);

        File dir = new File(QUERY_DIR_PATH);
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

}
