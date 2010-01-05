package org.eclipse.wst.xquery.ui.handlers;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.wst.xquery.launching.xqdoc.AbstractXQDocRuntime;
import org.eclipse.wst.xquery.launching.zorba.ZorbaXQDocRuntime;

public class ZorbaXQDocHandler extends AbstractXQDTWizardXQDocHandler {

    @Override
    protected AbstractXQDocRuntime getRuntime(IScriptProject project) {
        return new ZorbaXQDocRuntime(project, new NullProgressMonitor());
    }

}
