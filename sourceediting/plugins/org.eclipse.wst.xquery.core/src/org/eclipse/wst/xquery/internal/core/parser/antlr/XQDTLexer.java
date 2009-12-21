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
    private List<DefaultProblem> fProblems = new ArrayList<DefaultProblem>();

    public XQDTLexer() {
    }

    public XQDTLexer(CharStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public void rewindToIndex(int index) {
        ANTLRStringStream stream = (ANTLRStringStream)input;
        stream.seek(index);
        for (int i = fProblems.size() - 1; i >= 0; i--) {
            DefaultProblem problem = fProblems.get(i);
            if (problem.getSourceStart() >= index) {
                fProblems.remove(i);
            } else {
                break;
            }
        }
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

    public void postErrors() {
        for (DefaultProblem problem : fProblems) {
            if (problem.getSourceStart() > input.index()) {
                break;
            }
            fReporter.reportProblem(problem);
        }
        fProblems.clear();
    }

    @Override
    public void reportError(RecognitionException e) {
        if (fReporter != null) {
            if (input.size() != 0) {
                String errorMessage = getErrorMessage(e, getTokenNames());
                DefaultProblem problem = new SyntaxProblem(fFileName, errorMessage, e.index, e.index + 1, e.line);
                fProblems.add(problem);
            }
        }
        super.reportError(e);
    }

}
