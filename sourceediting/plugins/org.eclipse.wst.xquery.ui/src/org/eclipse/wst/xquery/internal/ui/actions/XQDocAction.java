/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xquery.internal.ui.wizards.XQDocWizard;

public class XQDocAction implements IObjectActionDelegate {

    private IStructuredSelection fSelection;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        // TODO Auto-generated method stub

    }

    public void run(IAction action) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        XQDocWizard.openXQDocWizard(new XQDocWizard(), shell, fSelection);
    }

    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            this.fSelection = (IStructuredSelection)selection;
        }
    }
}
