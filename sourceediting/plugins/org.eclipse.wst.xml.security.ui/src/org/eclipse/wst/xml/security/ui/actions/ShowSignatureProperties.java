/*******************************************************************************
 * Copyright (c) 2008 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.wst.xml.security.core.verify.VerificationResult;
import org.eclipse.wst.xml.security.ui.verify.Verification;

/**
 * <p>Action class used to show the properties of the selected XML Signature in the
 * XML Signatures view.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class ShowSignatureProperties extends XmlSecurityActionAdapter implements IViewActionDelegate {
    /** The selected verification result row in the view. */
    private VerificationResult result;

    /**
     * Shows the verification result dialog for the selected entry.
     *
     * @param action The causing action
     */
    public void run(final IAction action) {
        Verification.showVerificationResult(result, getShell());
    }

    /**
     * Shows the verification result dialog for the selected entry.
     *
     * @param selection The selected verification result in the view
     */
    public void run(final VerificationResult selection) {
        Verification.showVerificationResult(selection, getShell());
    }

    /**
     * Determines the selected verification result.
     *
     * @param action The causing action
     * @param selection The selected verification result entry
     */
    @Override
    public void selectionChanged(final IAction action, final ISelection selection) {
        result = null;

        if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
            Object o = ((IStructuredSelection) selection).getFirstElement();

            if (o instanceof VerificationResult) {
                result = (VerificationResult) o;
            }
            action.setEnabled(result != null);
        }
    }

    /**
     * Initializes the action for the view.
     *
     * @param view The view part
     */
    public void init(final IViewPart view) {
    }
}
