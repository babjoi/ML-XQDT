package org.eclipse.wst.xquery.set.internal.ui.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETCreateDocJob;

public class SETGenerateXQDocAction implements IViewActionDelegate, IWorkbenchWindowActionDelegate {

    private ISelection fSelection;
    private Shell fCurrentShell;

    public void init(IViewPart view) {
        fCurrentShell = view.getSite().getShell();
    }

    public void init(IWorkbenchWindow window) {
        fCurrentShell = window.getShell();
    }

    public void run(IAction action) {
        IStructuredSelection selection = null;
        if (fSelection instanceof IStructuredSelection) {
            selection = (IStructuredSelection)fSelection;
        } else {
            selection = new StructuredSelection();
        }

        IScriptProject project = null;

        Object obj = selection.getFirstElement();
        if (obj instanceof IScriptProject) {
            project = (IScriptProject)obj;
        } else if (obj instanceof IProject) {
            project = DLTKCore.create((IProject)obj);
        } else {
            return;
        }

        String projectName = project.getElementName();
        IProject prj = (IProject)project.getResource();
        IFolder folder = prj.getFolder("doc/xqdoc");
        if (folder != null && folder.exists()) {
            MessageDialog dialog = new MessageDialog(fCurrentShell, "Delete Current Documentation ?", null,
                    "Do you really want to delete the project documentation at: " + projectName + "/doc/xqdoc",
                    MessageDialog.QUESTION, new String[] { "Yes", "No" }, 2);
            int answer = dialog.open();
            if (answer == 1) {
                return;
            }
        }
        Job job = new SETCreateDocJob("create doc", prj, System.err);
        job.schedule();
    }

    public void selectionChanged(IAction action, ISelection selection) {
        fSelection = selection;
    }

    public void dispose() {
    }
}
