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
package org.eclipse.wst.xquery.set.internal.ui.wizards;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETDeployDataJob;
import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;
import org.eclipse.wst.xquery.set.launching.deploy.DeployManager;
import org.eclipse.wst.xquery.set.launching.deploy.Deployer;

@SuppressWarnings("restriction")
public class SETDeployDataWizard extends Wizard {

    private IScriptProject fProject;

    private SETDeployDataWizardPage fDeployPage;

    public SETDeployDataWizard(IScriptProject project) {
        //setDefaultPageImageDescriptor(SETPluginImages.DESC_WIZBAN_DEPLOY_PROJECT);
        fProject = project;
    }

    @Override
    public void addPages() {
        fDeployPage = new SETDeployDataWizardPage(fProject);
        addPage(fDeployPage);

        setWindowTitle("Deploy Sausalito Project Data");
    }

    @Override
    public boolean performFinish() {
        final DeployInfo info = fDeployPage.getDeployInfo();
        boolean useCache = fDeployPage.cacheCredentials();

        final Deployer deployer = DeployManager.getInstance().getDeployer(info, useCache);

        deployer.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                Job job = event.getJob();
                IStatus status = job.getResult();
                if (job instanceof SETDeployDataJob) {
                    if (status.isOK()) {
                        displayMessageBox("The data for project \"" + info.getProject().getElementName()
                                + "\" was succesfully deployed into the application \"" + info.getApplicationName()
                                + "\"", true);
                    }
                }
            }

            private void displayMessageBox(final String message, final boolean isSuccess) {
                Display.getDefault().syncExec(new Runnable() {

                    public void run() {
                        MessageBox mb = new MessageBox(new Shell(Display.getDefault().getActiveShell()),
                                (isSuccess ? SWT.ICON_INFORMATION : SWT.ICON_ERROR) | SWT.OK);
                        mb.setMessage(message);
                        mb.open();
                    }
                });

            }
        });
        deployer.execute();

        return true;
    }
}
