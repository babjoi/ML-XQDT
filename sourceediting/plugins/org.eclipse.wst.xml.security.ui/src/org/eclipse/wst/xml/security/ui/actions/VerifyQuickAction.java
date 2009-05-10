/*******************************************************************************
 * Copyright (c) 2009 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.ui.actions;

import java.util.ArrayList;

import org.apache.xml.security.keys.keyresolver.KeyResolverException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.verify.VerificationResult;
import org.eclipse.wst.xml.security.core.verify.VerifySignature;
import org.eclipse.wst.xml.security.ui.XSTUIPlugin;
import org.eclipse.wst.xml.security.ui.dialogs.MissingPreferenceDialog;
import org.eclipse.wst.xml.security.ui.preferences.PreferenceConstants;
import org.eclipse.wst.xml.security.ui.verify.Verification;

/**
 * <p>Action class used to start the verification of the given <b>XML Signature</b> with predefined
 * settings defined in the preferences (<b>Quick Verification</b>).</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class VerifyQuickAction extends XmlSecurityActionAdapter {
    /** Active editor. */
    private ITextEditor editor = null;
    /** The file to verify. */
    private IFile file = null;
    /** Signature ID. */
    private String signatureId;
    /** Action type. */
    private static final String ACTION = "verify";
    /** All required preferences are available. */
    private boolean preferencesComplete = false;

    /**
     * Called when the selection in the active workbench part changes.
     *
     * @param action The executed action
     * @param selection The selection
     */
    public void selectionChanged(final IAction action, final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            file = (IFile) ((IStructuredSelection) selection).getFirstElement();
        }
    }

    /**
     * Takes the selected file and verifies the predefined XML Signature. The selected XML
     * document is not changed at all.<br/>
     * Before any operation on the XML, the preference store is searched for the necessary
     * settings. Then these settings are verified. If the settings are valid the verify
     * operation begins. If not the user has the possibility to provide valid information.
     *
     * @param action The IAction
     */
    public void run(final IAction action) {
        getPreferenceValues();

        if (checkPreferences()) {
            try {
                doVerification();
            } catch (XMLSignatureException xmlse) {
                showError(Messages.error, Messages.invalidValueElement
                        + xmlse.getLocalizedMessage());
            } catch (KeyResolverException kre) {
                showError(Messages.error, Messages.invalidCertificate + kre.getLocalizedMessage());
            } catch (Exception e) {
                showError(Messages.error, Messages.verificationError + e.getLocalizedMessage());
                log("An error occured during quick verification", e); //$NON-NLS-1$
            }
        }
    }

    /**
     * Determines the preference values for <i>Quick Verification</i>.
     */
    private void getPreferenceValues() {
        IPreferenceStore store = XSTUIPlugin.getDefault().getPreferenceStore();
        signatureId = store.getString(PreferenceConstants.SIGN_ID);
    }

    /**
     * Verifies the XML document with the stored settings.
     *
     * @throws Exception to indicate any exceptional condition
     */
    private void doVerification() throws Exception {
        VerifySignature verify = new VerifySignature();
        ArrayList<VerificationResult> results = new ArrayList<VerificationResult>();

        IWorkbenchPart workbenchPart = getWorkbenchPart();

        if (workbenchPart != null && workbenchPart instanceof ITextEditor) {
            editor = (ITextEditor) workbenchPart;

            if (editor.isDirty()) {
                saveEditorContent(editor);
            }

            file = (IFile) editor.getEditorInput().getAdapter(IFile.class);
        } else {
            editor = null;
        }

        if (file != null && file.isAccessible()) {
            results = verify.verify(file.getLocation().toString(), signatureId);
        } else {
            showInfo(Messages.quickVerificationImpossible, NLS.bind(Messages.protectedDoc, ACTION));
        }

        if (results.size() == 1) {
            VerificationResult result = (VerificationResult) results.get(0);
            if (result.getSignature() != null) {
                Verification.showVerificationResult(result, getShell());
            } else {
                showError(Messages.quickVerificationImpossible, NLS.bind(
                        Messages.signatureNotFound, signatureId));
            }
        } else {
            showError(Messages.quickVerificationImpossible, NLS.bind(Messages.signatureNotFound,
                    signatureId));
        }
    }

    /**
     * Checks if the preferences contain all necessary information. Shows a dialog with a warning
     * message and a link to the preference page.<br/> If the preference dialog was closed with the
     * OK button the necessary preference informations are automatically verified again.
     *
     * @return Preferences OK or not
     */
    private boolean checkPreferences() {
        final String prefId = "org.eclipse.wst.xml.security.ui.preferences.Signatures";
        int result = 2;

        if (signatureId == null || "".equals(signatureId)) {
            result = showMissingParameterDialog(Messages.quickVerificationTitle,
                    NLS.bind(Messages.missingParameter, Messages.missingSignatureId), prefId);
        } else {
            preferencesComplete = true;
        }

        if (result == MissingPreferenceDialog.OK) {
            preferencesComplete = false;
            getPreferenceValues();
            checkPreferences();
        }

        return preferencesComplete;
    }
}
