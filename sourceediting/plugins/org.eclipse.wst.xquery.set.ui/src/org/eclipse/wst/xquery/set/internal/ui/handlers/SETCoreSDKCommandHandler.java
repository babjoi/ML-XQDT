/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.ui.handlers;

import java.io.OutputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

public abstract class SETCoreSDKCommandHandler extends AbstractHandler {

    private IProject fProject;

    public Object execute(ExecutionEvent event) throws ExecutionException {
        fProject = getProjectFromEvent(event);
        if (fProject == null) {
            showError();
            return null;
        }

        MessageConsole console = activateConsole();
        Job job = createHandlerJob(console.newMessageStream());
        if (job == null) {
            return null;
        }
        addJobChangeListeners(job);
        job.schedule();

        return null;
    }

    //
    // abstract methods
    //

    abstract protected String getLabel();

    abstract protected Job createHandlerJob(OutputStream output);

    //
    // implementation
    //

    private IProject getProjectFromEvent(ExecutionEvent event) {
        ISelection sel = HandlerUtil.getCurrentSelection(event);
        if (sel instanceof StructuredSelection) {
            StructuredSelection ssel = (StructuredSelection)sel;
            Object elem = ssel.getFirstElement();
            if (elem instanceof IAdaptable) {
                Object obj = ((IAdaptable)elem).getAdapter(IProject.class);
                if (obj != null) {
                    fProject = (IProject)obj;
                }
            }
        }
        return fProject;
    }

    private MessageConsole activateConsole() {
        String consoleName = getProjectActionLabel();
        MessageConsole console = findLastConsole();
        if (console == null) {
            console = new MessageConsole(consoleName, null);
            ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { console });
        } else {
            console.clearConsole();
        }
        Display.getCurrent().syncExec(new Runnable() {
            public void run() {
                try {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                            .showView(IConsoleConstants.ID_CONSOLE_VIEW);
                } catch (PartInitException pie) {
                    // Don't fail if we can't show the console
                }
            }
        });

        return console;
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

    private void showError() {
        Shell shell = Display.getCurrent().getActiveShell();
        ErrorDialog.openError(shell, "action.getText()" + " Error", "An error occurred while performing action \""
                + "action.getText()" + "\"", new Status(IStatus.ERROR, SETUIPlugin.PLUGIN_ID,
                "The Sausalito project could not be determined"));
    }

    protected void addJobChangeListeners(Job job) {
        job.addJobChangeListener(createSuccessListener());
        job.addJobChangeListener(createWarningListener());
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
        return getLabel() + " for project \"" + getProject().getName() + "\" finished succesfully.";
    }

    protected String getWarningMessage() {
        return getLabel() + " for project \"" + getProject().getName() + "\" finished with warnings.";
    }

    private String getProjectActionLabel() {
        return getLabel() + " (Project: " + getProject().getName() + ")";
    }

    protected IProject getProject() {
        return fProject;
    }

}
