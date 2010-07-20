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

import java.io.IOException;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.ProjectCreator;
import org.eclipse.dltk.ui.wizards.ProjectWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.wst.xquery.core.IXQDTCorePreferences;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.internal.ui.util.SETPluginImages;

public class SETNewProjectWizard extends ProjectWizard {

    public static final String WIZARD_ID = "org.eclipse.wst.xquery.set.ui.wizards.newproject"; //$NON-NLS-1$

    private ProjectWizardFirstPage fFirstPage;
    private SETNewProjectWizardSecondPage fSecondPage;

    public SETNewProjectWizard() {
        setDefaultPageImageDescriptor(SETPluginImages.DESC_WIZBAN_NEW_PROJECT);
        setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
        setWindowTitle("New Sausalito Project");
    }

    @Override
    public void addPages() {
        // First page
        fFirstPage = new SETNewProjectWizardFirstPage();
        fFirstPage.setTitle("Create a Sausalito project");
        fFirstPage.setDescription("Create a Sausalito project in the workspace or in an external location");
        addPage(fFirstPage);

        // Second page
        fSecondPage = new SETNewProjectWizardSecondPage(fFirstPage);
        addPage(fSecondPage);
    }

    public boolean performFinish() {
        boolean res = super.performFinish();

        ScopedPreferenceStore store = new ScopedPreferenceStore(new ProjectScope(fSecondPage.getScriptProject()
                .getProject()), XQDTCorePlugin.PLUGIN_ID);
        store.putValue(IXQDTCorePreferences.LANGUAGE_LEVEL, IXQDTCorePreferences.LANGUAGE_NAME_XQUERY_SCRIPTING);

        try {
            store.save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public String getScriptNature() {
        return SETNature.NATURE_ID;
    }

    @Override
    protected ProjectCreator createProjectCreator() {
        return new SETProjectCreator(this, getFirstPage());
    }
}
