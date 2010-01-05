/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *     Gabriel Petrovay (28msec)
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.ui.wizards;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xquery.internal.ui.XQDTImages;
import org.eclipse.wst.xquery.launching.xqdoc.AbstractXQDocRuntime;
import org.eclipse.wst.xquery.launching.xqdoc.XQDocUtil;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDocWizard extends Wizard implements IExportWizard {

    public static void openXQDocWizard(Shell shell, AbstractXQDocRuntime runtime, IStructuredSelection selection) {
        XQDocWizard wizard = new XQDocWizard(runtime);
        wizard.init(PlatformUI.getWorkbench(), selection);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
    }

    private AbstractXQDocRuntime fRuntime;
    private XQDocWizardPage fPage;

    public XQDocWizard(AbstractXQDocRuntime runtime) {
        fRuntime = runtime;
    }

    @Override
    public void addPages() {
        super.addPage(fPage);
    }

    public boolean performFinish() {
        IPath outDir = fPage.getOutputDir().append("xqdoc");
        fRuntime.init(fPage.getQueries(), outDir, fPage.getStyleSheet());
        Job job = XQDocUtil.createXQDocJob(fRuntime);
        job.schedule();
        return true;
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setDefaultPageImageDescriptor(XQDTImages.WIZBAN_NEW_XQDOC);
        setWindowTitle("New XQDoc Documentation");
        setDialogSettings(XQDTUIPlugin.getDefault().getDialogSettings());
        fPage = new XQDocWizardPage(selection);
    }
}
