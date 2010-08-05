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
package org.eclipse.wst.xquery.internal.core.parser.antlr;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.jface.text.Position;
import org.eclipse.wst.xquery.compiler.problem.SyntaxProblem;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;

public class XQDTParser extends Parser implements IXQDTLanguageConstants {

    private final NewLazyTokenStream fStream;
    private ANTLRStringStream fSource;
    private ArrayList<XQDTLexer> fLexerStack;
    private IProblemReporter fReporter;
    private String fFileName;
    protected int fLanguage;
    private List<Position> fKeywords = new ArrayList<Position>(50);

    public XQDTParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public XQDTParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        fLexerStack = new ArrayList<XQDTLexer>();
        fStream = (NewLazyTokenStream)input;
    }

    public void setProblemReporter(IProblemReporter reporter) {
        fReporter = reporter;
    }

    public void setFileName(String fileName) {
        fFileName = fileName;
    }

    public void setCharSource(ANTLRStringStream source) {
        this.fSource = source;
    }

    public void setLanguageLevel(int language) {
        fLanguage = language;
    }

    public int getLanguageLevel() {
        return fLanguage;
    }

    public void pushXMLLexer() {
        XMLLexer xmlLexer = new XMLLexer(fSource);
        xmlLexer.setReporter(fReporter);
        xmlLexer.setFileName(fFileName);
        xmlLexer.setIsWsExplicit(true);
        pushLexer(xmlLexer);
    }

    public void pushXQueryLexer() {
        XQueryLexer xqueryLexer = new XQueryLexer(fSource);
        xqueryLexer.setReporter(fReporter);
        xqueryLexer.setFileName(fFileName);
        pushLexer(xqueryLexer);
    }

    public void pushStringLexer(boolean isAposStr) {
        StringLexer stringLexer = new StringLexer(fSource, isAposStr);
        stringLexer.setReporter(fReporter);
        stringLexer.setFileName(fFileName);
        pushLexer(stringLexer);
    }

    public void pushLexer(XQDTLexer lexer) {
        XQDTLexer oldLexer = (XQDTLexer)fStream.getTokenSource();
        oldLexer.addToStack(fLexerStack);
        fStream.setTokenSource(lexer);
        oldLexer.postErrors();
    }

    public void popLexer() {
        XQDTLexer oldLexer = (XQDTLexer)fStream.getTokenSource();
        XQDTLexer newLexer = fLexerStack.remove(fLexerStack.size() - 1);
        fStream.setTokenSource(newLexer);
        oldLexer.postErrors();
    }

    public void setWsExplicit(boolean isExplicit) {
        fStream.setWsExplicit(isExplicit);
    }

    @Override
    public void reportError(RecognitionException e) {
        if (fReporter != null) {
            if (input.size() != 0) {
                DefaultProblem problem = null;
                CommonToken t = (CommonToken)input.get(e.index);
                if (t.getType() != XQueryParser.EOF) {
                    String errorMessage = getErrorMessage(e, getTokenNames());
                    problem = new SyntaxProblem(fFileName, errorMessage, t.getStartIndex(), t.getStopIndex() + 1,
                            t.getLine() - 1);
                } else {
                    CommonToken last = (CommonToken)input.get(input.size() - 1);
                    String errorMessage = getErrorMessage(e, getTokenNames());
                    problem = new SyntaxProblem(fFileName, errorMessage, last.getStopIndex() + 1,
                            last.getStopIndex() + 1, last.getLine() - 1);
                }

                if (problem != null) {
                    fReporter.reportProblem(problem);
                }
            }
        }
        super.reportError(e);
    }

    @SuppressWarnings("rawtypes")
    protected void ak(List list) {
        if (list == null) {
            return;
        }
        for (Object object : list) {
            CommonToken token = (CommonToken)object;
            fKeywords.add(new Position(token.getStartIndex(), token.getStopIndex() - token.getStartIndex() + 1));
        }
    }

    public List<Position> getKeywords() {
        return fKeywords;
    }

    // The following methods are used form the generated parser
    // The short names help keeping the grammar source file smaller and readable

    /**
     * Add a new keywords to the keyword list
     */
    protected void ak(CommonToken token) {
        fKeywords.add(new Position(token.getStartIndex(), token.getStopIndex() - token.getStartIndex() + 1));
    }

    /**
     * Sets the current language level given an XQuery version (called from version prolog rule).
     */
    public void setLanguageVersion(String languageVersion) {
        if ("1.0-ml".equals(languageVersion)) {
            fLanguage = IXQDTLanguageConstants.LANGUAGE_XQUERY_MARK_LOGIC;
        }
    }

    /**
     * Tests if the current language level is compliant with the language level specified in the
     * languageMask
     */
    protected boolean lc(int languageMask) {
        return (fLanguage & languageMask) == languageMask;
    }

    @Override
    public void reset() {
        super.reset();
        if (fLexerStack != null) {
            fLexerStack.clear();
            fKeywords.clear();
            fStream.setWsExplicit(false);
        }
    }

    public void postErrors() {
        XQDTLexer lexer = (XQDTLexer)fStream.getTokenSource();
        lexer.postErrors();
    }
}
