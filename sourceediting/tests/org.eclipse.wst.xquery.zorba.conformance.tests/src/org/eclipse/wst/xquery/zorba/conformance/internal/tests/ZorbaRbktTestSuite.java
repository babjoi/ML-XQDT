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
package org.eclipse.wst.xquery.zorba.conformance.internal.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

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
public class ZorbaRbktTestSuite implements IXQDTLanguageConstants {

    protected String fXqFile, fSpecFile;

    private static final String QUERY_DIR_PATH = "downloads" + File.separator + "zorba_test_queries";

    public ZorbaRbktTestSuite(String fXqFile, String specFile) {
        this.fXqFile = fXqFile;
        this.fSpecFile = specFile;
    }

    @Test
    public void parseQuery() {
        assumeTrue(isIgnored(fXqFile));

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
        TestFileCollector collector = new ZorbaTestFileCollector();
        return collector.collectTestFiles(QUERY_DIR_PATH);
    }

    // *****************************************************

    protected boolean isIgnored(String file) {
//        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/697024
//        if (file.endsWith("syntax-error-01.xq")) {
//            return false;
//        }
//        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/697021
//        if (file.contains("annotations") || file.endsWith("introsp-fn-annot-1.xq")) {
//            return false;
//        }
//        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696713
//        if (file.contains("fulltext")) {
//            return false;
//        }
//        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696717
//        if (file.contains("allowing-empty") || file.endsWith("gflwor04.xq") || file.endsWith("gflwor05.xq")) {
//            return false;
//        }
//        // TODO: Tracked by: https://bugs.launchpad.net/sausalito/+bug/696716
//        if (file.contains("HigherOrder")) {
//            return false;
//        }

        return true;
    }

    protected String readFile(File file) {
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

    protected void testQuery(String query, boolean valid, int languageLevel) {
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

    protected XQueryParser prepareParser(String fileName, char[] source) {
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
