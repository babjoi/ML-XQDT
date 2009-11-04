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

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.Tree;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;

public class XQDTCommonTreeAdaptor extends CommonTreeAdaptor {

    private IProblemReporter fReporter;

    public XQDTCommonTreeAdaptor(IProblemReporter reporter) {
        fReporter = reporter;
    }

    public Object create(Token payload) {
        return new XQDTCommonTree(payload);
    }

    @Override
    public void setTokenBoundaries(Object t, Token startToken, Token stopToken) {
        if (t == null)
            return;
        int startTI = 0;
        int stopTI = 0;
        int startTS = 0;
        int stopTS = 0;
        if (startToken != null) {
            startTI = startToken.getTokenIndex();
            startTS = ((CommonToken)startToken).getStartIndex();
        }
        if (stopToken != null) {
            stopTI = stopToken.getTokenIndex();
            stopTS = ((CommonToken)stopToken).getStopIndex();
        }
        ((Tree)t).setTokenStartIndex(startTI);
        ((Tree)t).setTokenStopIndex(stopTI);
        if (t instanceof XQDTCommonTree) {
            XQDTCommonTree xct = (XQDTCommonTree)t;
            xct.setStart(startTS);
            xct.setStop(stopTS);
        }
    }

    @Override
    public Object errorNode(TokenStream input, Token start, Token stop, RecognitionException e) {
        try {
            @SuppressWarnings("unused")
            CommonToken ctStart = null, ctStop = null;
            boolean reverse = start.getTokenIndex() > stop.getTokenIndex();
            if (reverse) {
                ctStart = (CommonToken)stop;
                ctStop = (CommonToken)start;
            } else {
                ctStart = (CommonToken)start;
                ctStop = (CommonToken)stop;
            }

            // reportSyntaxError(
            // "error node",
            // ctStart.getStartIndex(),
            // ctStop.getStopIndex(),
            // ctStart.getLine() - 1);

        } catch (Exception e2) {
            // e.printStackTrace();
        }

        return new XQDTCommonErrorNode(input, start, stop, e);
    }

    public void reportSyntaxError(String message, int startIndex, int stopIndex, int line) {
        if (fReporter != null) {
            DefaultProblem problem = new DefaultProblem(null, "Syntax error: " + message, IProblem.Unclassified,
                    new String[0], ProblemSeverities.Error, startIndex, stopIndex, line);
            fReporter.reportProblem(problem);
        }

    }
}
