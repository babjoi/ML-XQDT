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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.xquery.set.internal.ui.util.SETPluginImages;

public class SETImportProjectWizard extends Wizard implements IImportWizard {

    private SETImportProjectWizardPage fPage;

    public boolean performFinish() {
        return fPage.finishPage();
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setWindowTitle("Import Sausalito Project Wizard");
        setDefaultPageImageDescriptor(SETPluginImages.DESC_WIZBAN_IMP_PROJECT);
        setNeedsProgressMonitor(true);
        fPage = new SETImportProjectWizardPage("Import Sausalito Project");
    }

    @Override
    public void addPages() {
        addPage(fPage);
    }
}
