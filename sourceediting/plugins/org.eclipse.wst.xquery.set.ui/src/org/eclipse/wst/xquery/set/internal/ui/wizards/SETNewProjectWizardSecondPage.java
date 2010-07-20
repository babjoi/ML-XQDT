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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.ui.wizards.ProjectCreator;
import org.eclipse.dltk.ui.wizards.ProjectCreator.IProjectCreateStep;
import org.eclipse.dltk.ui.wizards.ProjectCreator.ProjectCreateStep;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.xquery.set.internal.ui.preferences.PreferenceConstants;
import org.eclipse.wst.xquery.set.ui.SETUILanguageToolkit;

public class SETNewProjectWizardSecondPage extends ProjectWizardSecondPage {

    private final IProjectCreateStep configureSausalitoStep = new ProjectCreateStep() {
        public void execute(IProject project, IProgressMonitor monitor) throws CoreException, InterruptedException {
            createProjectFromTemplate(new SubProgressMonitor(monitor, 50));
        }
    };

    public SETNewProjectWizardSecondPage(ProjectWizardFirstPage firstPage) {
        super(firstPage);
    }

    @Override
    public void initProjectWizardPage() {
        super.initProjectWizardPage();
        final ProjectCreator creator = getCreator();
        creator.addStep(IProjectCreateStep.KIND_FINISH, 10, configureSausalitoStep, this);
    }

    private void createProjectFromTemplate(SubProgressMonitor monitor) throws CoreException {
        try {
            IPreferenceStore store = SETUILanguageToolkit.getInstance().getPreferenceStore();
            String templateName = null;

            SETNewProjectWizardFirstPage firstPage = (SETNewProjectWizardFirstPage)getPreviousPage();
            if (firstPage.isTemplate()) {
                templateName = firstPage.getTemplateName();
            } else {
                templateName = store.getString(PreferenceConstants.TEMPLATES_DEFAULT_PROJECT_DIR);
            }
            monitor.beginTask("Creating project directory structure", 50);
            CoreSDKTemplateUtility.createProjectContent(getCreator().getProject(), templateName,
                    firstPage.getProjectUriString());
        } finally {
            monitor.done();
        }
    }

}
