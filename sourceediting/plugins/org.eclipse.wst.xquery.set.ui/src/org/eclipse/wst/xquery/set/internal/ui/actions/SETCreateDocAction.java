package org.eclipse.wst.xquery.set.internal.ui.actions;

import java.io.OutputStream;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.wst.xquery.set.internal.core.ISETConstants;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETCreateDocJob;

public class SETCreateDocAction extends SETCoreSDKCommandAction {

    protected Job createActionJob(OutputStream output) {
        return new SETCreateDocJob(getProject(), output);
    }

    protected String getActionLabel() {
        return ISETConstants.SAUSALITO_COMMAND_CREATE_DOC;
    }

    @Override
    public void run(IAction action) {
        IProject project = getProject();
        IFolder folder = project.getFolder("doc/xqdoc");
        if (folder != null && folder.exists()) {
            MessageDialog dialog = new MessageDialog(getShell(), "Delete Current Documentation ?", null,
                    "Do you really want to delete the project documentation at: " + project.getName() + "/doc/xqdoc",
                    MessageDialog.QUESTION, new String[] { "Yes", "No" }, 2);
            int answer = dialog.open();
            if (answer == 1) {
                return;
            }
        }
        super.run(action);
    }

}
