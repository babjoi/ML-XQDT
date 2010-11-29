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
package org.eclipse.wst.xquery.zorba.internal.core.tests;

import static org.junit.Assert.assertTrue;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.internal.core.parser.antlr.NewLazyTokenStream;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQDTCommonTreeAdaptor;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQueryLexer;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQueryParser;
import org.junit.Test;

@SuppressWarnings("restriction")
public class TestParser implements IXQDTLanguageConstants {

    // *****************************************************
    // ****************** TESTS ****************************
    // *****************************************************

    // https://bugs.eclipse.org/bugs/show_bug.cgi?id=297366
    @Test
    public void testFailingOnPurpose() {
        assertTrue(true);
        testQuery("declare private variable $bar := ''; ()", false, LANGUAGE_XQUERY);
    }

    // *****************************************************
    // *****************************************************

    private void testQuery(String query, boolean valid, int languageLevel) {
        XQueryParser parser = prepareParser("parser_unit_tests.xq", query.toCharArray());
        parser.setLanguageLevel(languageLevel);
        try {
            parser.p_Module();
            assert valid == (parser.getNumberOfSyntaxErrors() == 0);
        } catch (RecognitionException e) {
            // test for RecognitionException 
            assert false;
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
