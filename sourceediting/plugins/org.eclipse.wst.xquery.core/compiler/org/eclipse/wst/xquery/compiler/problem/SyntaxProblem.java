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
package org.eclipse.wst.xquery.compiler.problem;

import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;

public class SyntaxProblem extends DefaultProblem {

    public SyntaxProblem(String message, int startPosition, int endPosition, int line) {
        this("", message, startPosition, endPosition, line);
    }

    public SyntaxProblem(String originatingFileName, String message, int startPosition, int endPosition, int line) {
        this(originatingFileName, message, startPosition, endPosition, line, 0);
    }

    public SyntaxProblem(String originatingFileName, String message, int startPosition, int endPosition, int line,
            int column) {
        super(originatingFileName, message, IProblemIdentifier.NULL, new String[0], ProblemSeverities.Error,
                startPosition, endPosition, line, column);
    }
}
