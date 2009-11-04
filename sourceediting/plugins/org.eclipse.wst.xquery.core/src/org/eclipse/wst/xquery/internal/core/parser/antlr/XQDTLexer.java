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

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.wst.xquery.compiler.problem.SyntaxProblem;

public abstract class XQDTLexer extends Lexer {

    private boolean fIsWsExplicit = false;
    private IProblemReporter fReporter;
    private String fFileName;

    public XQDTLexer() {
    }

    public XQDTLexer(CharStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public void rewindToIndex(int index) {
        ANTLRStringStream stream = (ANTLRStringStream)input;
        stream.seek(index);
    }

    public boolean isWsExplicit() {
        return fIsWsExplicit;
    }

    public void setIsWsExplicit(boolean wsExplicit) {
        fIsWsExplicit = wsExplicit;
    }

    public void addToStack(List<XQDTLexer> stack) {
        stack.add(this);
    }

    public void setReporter(IProblemReporter reporter) {
        fReporter = reporter;
    }

    public void setFileName(String fileName) {
        fFileName = fileName;
    }

    @Override
    public void reportError(RecognitionException e) {
        if (fReporter != null) {
            if (input.size() != 0) {
                String errorMessage = getErrorMessage(e, getTokenNames());
                DefaultProblem problem = new SyntaxProblem(fFileName, errorMessage, e.index, e.index + 1, e.line);
                fReporter.reportProblem(problem);
            }
        }
        super.reportError(e);
    }

}
