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

import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETCoreSDKMultipleCommandJob;
import org.eclipse.wst.xquery.set.internal.ui.wizards.SETDeployProjectWizard;
import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;

public class SETDeployProjectCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel(IProject project) {
        return "Deploy project \"" + project.getName() + "\"";
    }

    @Override
    protected String getSuccessMessage(IProject project) {
        return "Project \"" + project.getName() + "\" has been deployed successfully.";
    }

    @Override
    protected String getWarningMessage(IProject project) {
        return "Project \"" + project.getName() + "\" has been deployed with warnings.";
    }

    protected Job createHandlerJob(IProject project) {
        Shell shell = Display.getCurrent().getActiveShell();
        SETDeployProjectWizard wizard = new SETDeployProjectWizard(project);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        if (dialog.open() == Dialog.OK) {
            DeployInfo info = wizard.getDeployInfo();
            return new SETCoreSDKMultipleCommandJob(info);
        }
        return null;
    }

    @Override
    protected void addJobChangeListeners(IProject project, Job job) {
        if (job instanceof SETCoreSDKMultipleCommandJob) {
            SETCoreSDKMultipleCommandJob djob = (SETCoreSDKMultipleCommandJob)job;
            djob.addJobChangeListenerToChildren(createSuccessListener(project));
            djob.addJobChangeListenerToChildren(createWarningListener(project));
        }
    }

    @Override
    public void setEnabled(Object evaluationContext) {
        if (evaluationContext instanceof EvaluationContext) {
            setBaseEnabled(shouldEnable((EvaluationContext)evaluationContext));
            return;
        }

        setBaseEnabled(false);
        return;
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
                        return ((IResource)res).getProject().hasNature(SETNature.NATURE_ID);
                    } catch (CoreException e) {
                        // do nothing, will return false
                    }
                }
            }
        }

        return false;
    }

}
