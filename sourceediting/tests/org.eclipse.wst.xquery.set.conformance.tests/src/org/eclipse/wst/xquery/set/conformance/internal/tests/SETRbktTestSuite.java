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

import java.io.File;
import java.util.Map;

import org.eclipse.wst.xquery.core.tests.LabeledParameterized;
import org.eclipse.wst.xquery.core.tests.LabeledParameterized.LabeledParameters;
import org.eclipse.wst.xquery.internal.core.tests.CoreRbktTestSuite;
import org.eclipse.wst.xquery.internal.core.tests.TestFileCollector;
import org.junit.runner.RunWith;

@RunWith(LabeledParameterized.class)
public class SETRbktTestSuite extends CoreRbktTestSuite {

    private static final String QUERY_DIR_PATH = "downloads" + File.separator + "sausalito_test_queries";

    public SETRbktTestSuite(String fXqFile, String specFile) {
        super(fXqFile, specFile);
    }

    @LabeledParameters
    public static Map<String, Object[]> files() {
        TestFileCollector collector = new SETTestFileCollector();
        return collector.collectTestFiles(QUERY_DIR_PATH);
    }

}
