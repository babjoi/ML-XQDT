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

import java.io.OutputStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

public abstract class SETCoreSDKCommandAction implements IObjectActionDelegate {

    private IProject fProject;
    private Shell fCurrentShell;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        fCurrentShell = targetPart.getSite().getShell();
    }

    //
    // abstract methods
    //

    abstract protected String getActionLabel();

    abstract protected Job createActionJob(OutputStream output);

    //
    // implementation
    //

    public void run(IAction action) {
        if (fProject == null) {
            ErrorDialog.openError(fCurrentShell, action.getText() + " Error",
                    "An error occurred while performing action \"" + action.getText() + "\"", new Status(IStatus.ERROR,
                            SETUIPlugin.PLUGIN_ID, "The Sausalito project could not be determined"));
            return;
        }

        String consoleName = getProjectActionLabel();
        MessageConsole console = findLastConsole();
        if (console == null) {
            console = new MessageConsole(consoleName, null);
            ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { console });
        } else {
            console.clearConsole();
        }
        fCurrentShell.getDisplay().syncExec(new Runnable() {
            public void run() {
                try {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                            .showView(IConsoleConstants.ID_CONSOLE_VIEW);
                } catch (PartInitException pie) {
                    // Don't fail if we can't show the console
                }
            }
        });

        Job job = createActionJob(console.newMessageStream());
        job.addJobChangeListener(createSuccessListener());
        job.addJobChangeListener(createWarningListener());
        job.schedule();
    }

    private MessageConsole findLastConsole() {
        String stringToFind = "Project: " + getProject().getName();
        IConsole[] consoles = ConsolePlugin.getDefault().getConsoleManager().getConsoles();
        for (IConsole console : consoles) {
            if (console.getName().contains(stringToFind) && console instanceof MessageConsole) {
                return (MessageConsole)console;
            }
        }
        return null;
    }

    protected IJobChangeListener createSuccessListener() {
        JobChangeAdapter successListener = new JobChangeAdapter() {
            @Override
            public void done(IJobChangeEvent event) {
                IStatus result = event.getResult();
                if (result.isOK()) {
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            MessageDialog md = new MessageDialog(Display.getDefault().getActiveShell(),
                                    getProjectActionLabel(), null, getSuccessMessage(), MessageDialog.INFORMATION,
                                    new String[] { IDialogConstants.OK_LABEL }, 0);
                            md.open();
                        }
                    });
                }
            }
        };
        return successListener;
    }

    protected IJobChangeListener createWarningListener() {
        JobChangeAdapter successListener = new JobChangeAdapter() {
            @Override
            public void done(IJobChangeEvent event) {
                final IStatus result = event.getResult();
                if (result.getSeverity() == IStatus.WARNING) {
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            StringBuilder message = new StringBuilder();
                            message.append(getWarningMessage());
                            // SETCoreSDKCommandJob sends warnings only when the message is non-empty
                            message.append("\n\n");
                            message.append(result.getMessage());

                            MessageDialog md = new MessageDialog(Display.getDefault().getActiveShell(),
                                    getProjectActionLabel(), null, message.toString(), MessageDialog.WARNING,
                                    new String[] { IDialogConstants.OK_LABEL }, 0);
                            md.open();
                        }
                    });
                }
            }
        };
        return successListener;
    }

    protected String getSuccessMessage() {
        return getActionLabel() + " for project \"" + getProject().getName() + "\" finished succesfully.";
    }

    protected String getWarningMessage() {
        return getActionLabel() + " for project \"" + getProject().getName() + "\" finished with warnings.";
    }

    private String getProjectActionLabel() {
        return getActionLabel() + " (Project: " + getProject().getName() + ")";
    }

    protected IProject getProject() {
        return fProject;
    }

    protected Shell getShell() {
        return fCurrentShell;
    }

    //
    // implementation IObjectActionDelegate
    //

    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection ss = (IStructuredSelection)selection;
            if (ss.getFirstElement() instanceof IProject) {
                fProject = (IProject)ss.getFirstElement();
            }
        }
    }

}
