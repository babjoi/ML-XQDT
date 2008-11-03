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
package org.eclipse.wst.xml.security.core.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.xml.security.keys.keyresolver.KeyResolverException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;
import org.eclipse.wst.xml.security.core.verify.SignatureView;
import org.eclipse.wst.xml.security.core.verify.VerificationResult;
import org.eclipse.wst.xml.security.core.verify.VerifyDocument;

/**
 * <p>Action class used to refresh all digital signatures in the <b>XML Signatures</b> view.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class RefreshSignatures extends XmlSecurityActionAdapter implements IViewActionDelegate {
    /** Action type. */
    private static final String ACTION = "verify";

    /**
     * Called when the selection in the active workbench part changes.
     *
     * @param action The executed action
     * @param selection The selection
     */
    public void selectionChanged(final IAction action, final ISelection selection) {
    }

    /**
     * Refreshes the XML Digital Signature view and verifies all available
     * digital signatures again.
     *
     * @param action The causing action
     */
    public void run(final IAction action) {
        IWorkbenchWindow workbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IWorkbenchPage page = workbench.getActivePage();
        final IEditorPart editor = page.getActiveEditor();

        if (editor != null) {
            if (editor.isDirty()) {
                if (null != editor.getTitle() && editor.getTitle().length() > 0) {
                    IRunnableWithProgress op = new IRunnableWithProgress() {
                        public void run(final IProgressMonitor monitor) {
                            editor.doSave(monitor);
                        }
                    };
                    try {
                        PlatformUI.getWorkbench().getProgressService().runInUI(
                                XmlSecurityPlugin.getActiveWorkbenchWindow(), op,
                                XmlSecurityPlugin.getWorkspace().getRoot());
                    } catch (InvocationTargetException ite) {
                        log("Error while saving editor content", ite); //$NON-NLS-1$
                    } catch (InterruptedException ie) {
                        log("Error while saving editor content", ie); //$NON-NLS-1$
                    }
                } else {
                    editor.doSaveAs();
                }
            }

            try {
                ArrayList<VerificationResult> results = new ArrayList<VerificationResult>();
                VerifyDocument verify = new VerifyDocument();
                IFile file = (IFile) editor.getEditorInput().getAdapter(IFile.class);
                if (file != null) {
                    results = verify.verify(file.getLocation().toString());
                } else {
                    showInfo(Messages.refreshImpossible, NLS.bind(Messages.protectedDoc, ACTION));
                }

                if (results.size() == 0) {
                    showInfo(Messages.refreshImpossible, Messages.noSignaturesInDocument);
                }

                // show results
                IViewPart vp = page.showView(SignatureView.ID); //$NON-NLS-1$
                if (vp instanceof SignatureView) {
                    ((SignatureView) vp).setInput(results);
                }
            } catch (XMLSignatureException xmlse) {
                showError(Messages.invalidXml, xmlse.getLocalizedMessage());
            } catch (KeyResolverException kre) {
                showError(Messages.invalidCertificate, kre.getLocalizedMessage());
            } catch (Exception ex) {
                showError(Messages.verificationError, ex.getLocalizedMessage());
                log("Error during verification of XML signature", ex); //$NON-NLS-1$
            }
        } else {
            showInfo(Messages.refreshImpossible, Messages.noDocument);
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
