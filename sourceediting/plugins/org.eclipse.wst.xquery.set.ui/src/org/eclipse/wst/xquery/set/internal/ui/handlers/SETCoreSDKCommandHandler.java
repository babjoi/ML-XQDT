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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

public abstract class SETCoreSDKCommandHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        IProject project = getProjectFromEvent(event);
        if (project == null) {
            showError();
            return null;
        }

        Job job = createHandlerJob(project);
        if (job == null) {
            return null;
        }
        addJobChangeListeners(project, job);
        job.schedule();

        return null;
    }

    @Override
    public void setEnabled(Object evaluationContext) {
        if (evaluationContext instanceof EvaluationContext) {
            setBaseEnabled(shouldEnable((EvaluationContext)evaluationContext));
            return;
        }

        setBaseEnabled(false);
    }

    private boolean shouldEnable(EvaluationContext evaluationContext) {
        Object obj = (evaluationContext).getVariable("selection");
        if (obj instanceof IStructuredSelection) {
            IStructuredSelection sel = (IStructuredSelection)obj;
            Object element = sel.getFirstElement();
            if (element instanceof IAdaptable) {
                Object res = ((IAdaptable)element).getAdapter(IResource.class);
                if (res != null) {
                    try {
                        IProject project = ((IResource)res).getProject();
                        return project.hasNature(SETNature.NATURE_ID) && isProjectCapable(project);
                    } catch (CoreException e) {
                        // do nothing, will return false
                    }
                }
            }
        }

        return false;
    }

    protected boolean isProjectCapable(IProject project) {
        return true;
    }

    //
    // abstract methods
    //

    abstract protected String getLabel(IProject project);

    abstract protected Job createHandlerJob(IProject project);

    //
    // implementation
    //

    private IProject getProjectFromEvent(ExecutionEvent event) {
        IProject project = null;
        ISelection sel = HandlerUtil.getCurrentSelection(event);
        if (sel instanceof StructuredSelection) {
            StructuredSelection ssel = (StructuredSelection)sel;
            Object elem = ssel.getFirstElement();
            if (elem instanceof IAdaptable) {
                Object obj = ((IAdaptable)elem).getAdapter(IResource.class);
                if (obj != null) {
                    project = ((IResource)obj).getProject();
                }
            }
        } else if (sel instanceof TextSelection) {
            try {
                IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .getActiveEditor();
                Object obj = editor.getEditorInput().getAdapter(IResource.class);
                project = ((IResource)obj).getProject();
            } catch (NullPointerException npe) {
                // nothing to do
            }
        }
        return project;
    }

    private void showError() {
        Shell shell = Display.getCurrent().getActiveShell();
        ErrorDialog.openError(shell, "action.getText()" + " Error", "An error occurred while performing action \""
                + "action.getText()" + "\"", new Status(IStatus.ERROR, SETUIPlugin.PLUGIN_ID,
                "The Sausalito project could not be determined"));
    }

    protected void addJobChangeListeners(IProject project, Job job) {
        job.addJobChangeListener(createSuccessListener(project));
        job.addJobChangeListener(createWarningListener(project));
    }

    protected IJobChangeListener createSuccessListener(final IProject project) {
        JobChangeAdapter successListener = new JobChangeAdapter() {
            @Override
            public void done(IJobChangeEvent event) {
                IStatus result = event.getResult();
                if (result.isOK()) {
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            MessageDialog md = new MessageDialog(Display.getDefault().getActiveShell(),
                                    getProjectActionLabel(project), null, getSuccessMessage(project),
                                    MessageDialog.INFORMATION, new String[] { IDialogConstants.OK_LABEL }, 0);
                            md.open();
                        }
                    });
                }
            }
        };
        return successListener;
    }

    protected IJobChangeListener createWarningListener(final IProject project) {
        JobChangeAdapter successListener = new JobChangeAdapter() {
            @Override
            public void done(IJobChangeEvent event) {
                final IStatus result = event.getResult();
                if (result.getSeverity() == IStatus.WARNING) {
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            StringBuilder message = new StringBuilder();
                            message.append(getWarningMessage(project));
                            // SETCoreSDKCommandJob sends warnings only when the message is non-empty
                            message.append("\n\n");
                            message.append(result.getMessage());

                            MessageDialog md = new MessageDialog(Display.getDefault().getActiveShell(),
                                    getProjectActionLabel(project), null, message.toString(), MessageDialog.WARNING,
                                    new String[] { IDialogConstants.OK_LABEL }, 0);
                            md.open();
                        }
                    });
                }
            }
        };
        return successListener;
    }

    protected String getSuccessMessage(IProject project) {
        return getLabel(project) + " for project \"" + project.getName() + "\" finished succesfully.";
    }

    protected String getWarningMessage(IProject project) {
        return getLabel(project) + " for project \"" + project.getName() + "\" finished with warnings.";
    }

    private String getProjectActionLabel(IProject project) {
        return getLabel(project) + " (Project: " + project.getName() + ")";
    }

}
