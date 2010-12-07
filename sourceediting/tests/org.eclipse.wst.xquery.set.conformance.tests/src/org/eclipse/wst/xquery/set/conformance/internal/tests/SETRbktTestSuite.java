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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.internal.core.parser.antlr.NewLazyTokenStream;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQDTCommonTreeAdaptor;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQueryLexer;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQueryParser;
import org.eclipse.wst.xquery.zorba.conformance.tests.LabeledParameterized;
import org.eclipse.wst.xquery.zorba.conformance.tests.LabeledParameterized.LabeledParameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(LabeledParameterized.class)
public class SETRbktTestSuite implements IXQDTLanguageConstants {

    private String fXqFile, fSpecFile;
    private static String QUERY_DIR_PATH = ".." + File.separator + ".." + File.separator + "development"
            + File.separator + "org.eclipse.wst.xquery.releng" + File.separator + "downloads" + File.separator
            + "sausalito_test_queries";

    public SETRbktTestSuite(String fXqFile, String specFile) {
        this.fXqFile = fXqFile;
        this.fSpecFile = specFile;
    }

    @Test
    public void parseQuery() {
        File xqFile = new File(fXqFile);
        assertTrue("The input query file was not found: " + fXqFile, xqFile.exists());

        boolean valid = true;
        File spFile = null;
        if (fSpecFile != null) {
            spFile = new File(fSpecFile);
            assertTrue("The input query file was not found: " + fXqFile, spFile.exists());
            String spec = readFile(spFile);
            if (spec.contains("Error:") && fSpecFile.contains("syntax-error")) {
                valid = false;
            }
        }

        String query = readFile(xqFile);
        assertNotNull("Could not read the query file: " + xqFile.getAbsolutePath(), query);

        testQuery(query, valid, LANGUAGE_XQUERY_SCRIPTING | LANGUAGE_XQUERY_FULLTEXT | LANGUAGE_XQUERY_ZORBA);
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

        assertTrue("Can not find the Zorba test queries under: \"" + dir.getAbsolutePath()
                + "\". Are you sure that the following file provides an Ant build step on Hudson: "
                + "sourceediting/development/org.eclipse.wst.xquery.releng/buildZorba.xml ?", map.size() > 0);

        return map;
    }

    private static void recursive(File testDir, Map<String, Object[]> map, String relativeName) {
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

    private static boolean isIgnored(String name) {
        if (".svn".equals(name)) {
            return true;
        }
        return false;
    }

    // *****************************************************
    // *****************************************************

    private String readFile(File file) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
        } catch (IOException ioe) {
            return null;
        }
        return sb.toString();
    }

    private void testQuery(String query, boolean valid, int languageLevel) {
        XQueryParser parser = prepareParser("parser_unit_tests.xq", query.toCharArray());
        parser.setLanguageLevel(languageLevel);
        try {
            parser.p_Module();
            if (valid) {
                assertEquals("Expected no syntax errors", 0, parser.getNumberOfSyntaxErrors());
            } else {
                assertNotSame("Expected syntax errors", 0, parser.getNumberOfSyntaxErrors());
            }

        } catch (RecognitionException e) {
            assertTrue("Parse Error: " + e.getMessage(), false);
        }
    }

    private XQueryParser prepareParser(String fileName, char[] source) {
        ANTLRStringStream inputStream = new ANTLRStringStream(source, source.length);
        XQueryLexer lexer = new XQueryLexer(inputStream);
        NewLazyTokenStream tokenStream = new NewLazyTokenStream(lexer);
        tokenStream.jumpToFirstValidToken();
        XQueryParser parser = new XQueryParser(tokenStream);
        parser.setCharSource(inputStream);
        parser.setTreeAdaptor(new XQDTCommonTreeAdaptor(null));
        parser.setFileName(fileName);
        return parser;
    }

}
