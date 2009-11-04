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
package org.eclipse.wst.xquery.set.internal.ui.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETImportDataJob;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

public class SETImportDataAction implements IObjectActionDelegate {

    private IProject fProject;
    private Shell fCurrentShell;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        fCurrentShell = targetPart.getSite().getShell();
    }

    public void run(IAction action) {
        if (fProject == null) {
            ErrorDialog.openError(fCurrentShell, "Import Data Error",
                    "An error occured while initiating the data import", new Status(IStatus.ERROR,
                            SETUIPlugin.PLUGIN_ID, "The Sausalito project could not be determined"));
            return;
        }

        MessageConsole console = new MessageConsole("Sausalito Command", null);
        ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { console });

        fCurrentShell.getDisplay().syncExec(new Runnable() {
            public void run() {
                try {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(
                            IConsoleConstants.ID_CONSOLE_VIEW);
                } catch (PartInitException pie) {
                    // Don't fail if we can't show the console
                }
            }
        });

        Job job = new SETImportDataJob(fProject, console.newMessageStream());
        job.schedule();
    }

    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection ss = (IStructuredSelection)selection;
            if (ss.getFirstElement() instanceof IProject) {
                fProject = (IProject)ss.getFirstElement();
            }
        }
    }

}
