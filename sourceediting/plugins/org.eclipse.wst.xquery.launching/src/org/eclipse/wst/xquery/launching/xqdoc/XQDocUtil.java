package org.eclipse.wst.xquery.launching.xqdoc;

import org.eclipse.wst.xquery.internal.launching.xqdoc.XQDocJob;

public class XQDocUtil {

    public static XQDocJob createXQDocJob(AbstractXQDocRuntime runtime) {
        return new XQDocJob(runtime);
    }
}
