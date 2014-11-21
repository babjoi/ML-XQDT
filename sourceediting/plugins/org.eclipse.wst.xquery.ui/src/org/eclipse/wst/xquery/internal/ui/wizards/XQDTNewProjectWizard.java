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
package org.eclipse.wst.xquery.internal.ui.wizards;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.ProjectWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.wst.xquery.core.XQDTNature;

public class XQDTNewProjectWizard extends ProjectWizard {

    public static final String WIZARD_ID = "org.eclipse.wst.xquery.ui.wizards.newproject"; //$NON-NLS-1$

    public XQDTNewProjectWizard() {
        // setDefaultPageImageDescriptor(SETPluginImages.DESC_WIZBAN_NEW_PROJECT);
        setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
        setWindowTitle("New XQuery Project");
    }

    @Override
    public void addPages() {
        // First page
        ProjectWizardFirstPage firstPage = new ProjectWizardFirstPage() {};
        firstPage.setTitle("Create a XQuery project");
        firstPage.setDescription("Create a XQuery project in the workspace or in an external location");
        addPage(firstPage);

        // Second page
        ProjectWizardSecondPage secondPage = new ProjectWizardSecondPage(firstPage) {};
        addPage(secondPage);
    }

    public String getScriptNature() {
        return XQDTNature.NATURE_ID;
    }

}
