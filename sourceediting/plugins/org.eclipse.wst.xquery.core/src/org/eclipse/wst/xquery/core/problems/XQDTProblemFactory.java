package org.eclipse.wst.xquery.core.problems;

import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.DefaultProblemFactory;

public class XQDTProblemFactory extends DefaultProblemFactory {

    @Override
    protected String getProblemMarkerType() {
        return DefaultProblem.MARKER_TYPE_PROBLEM;
    }
}
