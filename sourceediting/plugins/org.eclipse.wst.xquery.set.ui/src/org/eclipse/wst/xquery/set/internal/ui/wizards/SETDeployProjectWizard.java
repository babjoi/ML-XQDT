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
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETDeployProjectJob;
import org.eclipse.wst.xquery.set.internal.ui.util.SETPluginImages;
import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;
import org.eclipse.wst.xquery.set.launching.deploy.DeployManager;
import org.eclipse.wst.xquery.set.launching.deploy.Deployer;

public class SETDeployProjectWizard extends Wizard {

    private IScriptProject fProject;

    private SETDeployProjectFirstWizardPage fFirstPage;
    private SETDeployProjectSecondWizardPage fSecondPage;

    public SETDeployProjectWizard(IScriptProject project) {
        setDefaultPageImageDescriptor(SETPluginImages.DESC_WIZBAN_DEPLOY_PROJECT);
        fProject = project;
    }

    @Override
    public void addPages() {
        fFirstPage = new SETDeployProjectFirstWizardPage(fProject);
        addPage(fFirstPage);
        fSecondPage = new SETDeployProjectSecondWizardPage(fProject);
        addPage(fSecondPage);

        setWindowTitle("Deploy Sausalito Project");
    }

    @Override
    public boolean performFinish() {
        DeployInfo tmpInfo = fFirstPage.getDeployInfo();
        tmpInfo = fSecondPage.configureDeployInfo(tmpInfo);

        boolean useCache = fFirstPage.cacheCredentials();

        final DeployInfo info = tmpInfo;
        final Deployer deployer = DeployManager.getInstance().getDeployer(info, useCache);

        deployer.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                Job job = event.getJob();
                IStatus status = job.getResult();
                if (job instanceof SETDeployProjectJob) {
                    if (status.isOK()) {
                        displayMessageBox("The project \"" + info.getProject().getElementName()
                                + "\" was succesfully deployed as application \"" + info.getApplicationName() + "\"",
                                SWT.ICON_INFORMATION);
                    } else if (status.matches(IStatus.WARNING)) {
                        displayMessageBox("The project \"" + info.getProject().getElementName()
                                + "\" was deployed as application \"" + info.getApplicationName()
                                + "\" with the following warnings:\n\n" + status.getMessage(), SWT.ICON_WARNING);
                    }
                } else if (job instanceof SETDeployDataJob) {
                    if (status.isOK()) {
                        displayMessageBox("The data for project \"" + info.getProject().getElementName()
                                + "\" was succesfully deployed into the application \"" + info.getApplicationName()
                                + "\"", SWT.ICON_INFORMATION);
                    }
                }

            }

            /**
             * Displays a message box dialog containing the given image and icon type.
             * 
             * @param message
             *            The message to display in the message dialog.
             * @param type
             *            One of <code>SWT.ICON_INFORMATION</code>, <code>SWT.ICON_WARNING</code>,
             *            <code>SWT.ICON_ERROR</code>.
             */
            private void displayMessageBox(final String message, final int type) {
                Display.getDefault().syncExec(new Runnable() {

                    public void run() {
                        int iconType = SWT.ICON_INFORMATION;

                        switch (type) {
                        case SWT.ICON_INFORMATION:
                        case SWT.ICON_WARNING:
                        case SWT.ICON_ERROR:
                            iconType = type;
                        }

                        MessageBox mb = new MessageBox(new Shell(Display.getDefault().getActiveShell()), (iconType)
                                | SWT.OK);
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
