package org.eclipse.wst.xquery.ui.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xquery.internal.ui.wizards.XQDocWizard;
import org.eclipse.wst.xquery.launching.xqdoc.AbstractXQDocRuntime;

public abstract class AbstractXQDTWizardXQDocHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        IEvaluationContext context = (IEvaluationContext)event.getApplicationContext();
        Object defVar = context.getDefaultVariable();
        if (!(defVar instanceof List<?>)) {
            return null;
        }
        StructuredSelection selection = new StructuredSelection(((List<?>)defVar).toArray());
        Object o = selection.getFirstElement();
        IScriptProject project = null;
        if (o instanceof IResource) {
            project = DLTKCore.create(((IResource)o).getProject());
        } else if (o instanceof IModelElement) {
            project = ((IModelElement)o).getScriptProject();
        }

        if (project == null) {
            return null;
        }

        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        XQDocWizard.openXQDocWizard(shell, getRuntime(project), selection);
        return null;
    }

    abstract protected AbstractXQDocRuntime getRuntime(IScriptProject project);

}
