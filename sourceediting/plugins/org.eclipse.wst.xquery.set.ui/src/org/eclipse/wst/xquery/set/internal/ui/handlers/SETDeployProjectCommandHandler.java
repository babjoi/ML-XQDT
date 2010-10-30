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

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETCoreSDKMultipleCommandJob;
import org.eclipse.wst.xquery.set.internal.ui.wizards.SETDeployProjectWizard;
import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;

public class SETDeployProjectCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel() {
        return "Deploy project \"" + getProject().getName() + "\"";
    }

    @Override
    protected String getSuccessMessage() {
        return "Project \"" + getProject().getName() + "\" has been deployed successfully.";
    }

    @Override
    protected String getWarningMessage() {
        return "Project \"" + getProject().getName() + "\" has been deployed with warnings.";
    }

    protected Job createHandlerJob(OutputStream output) {
        Shell shell = Display.getCurrent().getActiveShell();
        SETDeployProjectWizard wizard = new SETDeployProjectWizard(getProject());
        WizardDialog dialog = new WizardDialog(shell, wizard);
        if (dialog.open() == Dialog.OK) {
            DeployInfo info = wizard.getDeployInfo();
            return new SETCoreSDKMultipleCommandJob(info, output);
        }
        return null;
    }

    @Override
    protected void addJobChangeListeners(Job job) {
        if (job instanceof SETCoreSDKMultipleCommandJob) {
            SETCoreSDKMultipleCommandJob djob = (SETCoreSDKMultipleCommandJob)job;
            djob.addJobChangeListenerToChildren(createSuccessListener());
            djob.addJobChangeListenerToChildren(createWarningListener());
        }
    }

}
