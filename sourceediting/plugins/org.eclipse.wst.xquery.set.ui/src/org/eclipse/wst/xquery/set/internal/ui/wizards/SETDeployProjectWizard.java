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

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.wst.xquery.set.internal.ui.util.SETPluginImages;
import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;
import org.eclipse.wst.xquery.set.launching.deploy.DeployManager;

public class SETDeployProjectWizard extends Wizard {

    private IScriptProject fProject;
    private DeployInfo fDeployInfo;

    private SETDeployProjectFirstWizardPage fFirstPage;
    private SETDeployProjectSecondWizardPage fSecondPage;

    public SETDeployProjectWizard(IProject project) {
        setDefaultPageImageDescriptor(SETPluginImages.DESC_WIZBAN_DEPLOY_PROJECT);
        fProject = DLTKCore.create(project);
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
        fDeployInfo = fFirstPage.getDeployInfo();
        fDeployInfo = fSecondPage.configureDeployInfo(fDeployInfo);

        if (fFirstPage.cacheCredentials()) {
            DeployManager.getInstance().cacheDeployInfo(fDeployInfo);
        }

        return true;
    }

    public DeployInfo getDeployInfo() {
        return fDeployInfo;
    }
}
