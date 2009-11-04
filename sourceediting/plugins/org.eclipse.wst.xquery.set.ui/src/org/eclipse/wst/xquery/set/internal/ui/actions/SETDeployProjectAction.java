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
package org.eclipse.wst.xquery.set.internal.ui.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.wst.xquery.set.internal.ui.wizards.SETDeployProjectWizard;

public class SETDeployProjectAction implements IViewActionDelegate, IWorkbenchWindowActionDelegate {

    private ISelection fSelection;
    private Shell fCurrentShell;

    public void init(IViewPart view) {
        fCurrentShell = view.getSite().getShell();
    }

    public void init(IWorkbenchWindow window) {
        fCurrentShell = window.getShell();
    }

    public void run(IAction action) {
        IStructuredSelection selection = null;
        if (fSelection instanceof IStructuredSelection) {
            selection = (IStructuredSelection)fSelection;
        } else {
            selection = new StructuredSelection();
        }

        IScriptProject project = null;

        Object obj = selection.getFirstElement();
        if (obj instanceof IScriptProject) {
            project = (IScriptProject)obj;
        } else if (obj instanceof IProject) {
            project = DLTKCore.create((IProject)obj);
        } else
            return;

        WizardDialog dialog = new WizardDialog(fCurrentShell, new SETDeployProjectWizard(project));
        dialog.open();

    }

    public void selectionChanged(IAction action, ISelection selection) {
        fSelection = selection;
    }

    public void dispose() {
    }
}
