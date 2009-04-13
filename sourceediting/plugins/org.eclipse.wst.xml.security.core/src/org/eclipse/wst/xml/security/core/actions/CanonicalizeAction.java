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
package org.eclipse.wst.xml.security.core.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.xml.security.c14n.Canonicalizer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;
import org.eclipse.wst.xml.security.core.canonicalize.Canonicalization;
import org.eclipse.wst.xml.security.core.preferences.PreferenceConstants;

/**
 * <p>Action class used to generate the canonical XML form with or without comments of the selected XML
 * document. Exclusive or inclusive type of canonicalization and the target document (same or new)
 * are determined via the preferences. The decision to maintain or remove comments is based on the
 * selected menu item in the context menu.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class CanonicalizeAction extends XmlSecurityActionAdapter {
    /** Active editor. */
    private ITextEditor editor = null;
    /** The file to canonicalize. */
    private IFile file = null;
    /** Canonicalization version (exclusive or inclusive). */
    private String canonVersion;
    /** Canonicalization type (remove or maintain comments). */
    private String canonType;
    /** Canonicalization target (same or new document). */
    private String canonTarget;
    /** Action type. */
    private static final String ACTION = "canonicalize";

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
     * Called when clicked on one of the <i>Canonicalization</i> entries in the plug-ins context
     * menu.
     *
     * @param action The IAction
     */
    public void run(final IAction action) {
        canonType = action.getId();

        createCanonicalization();
    }

    /**
     * Takes the resource (selected file or editor content) and starts the XML Canonicalization.
     */
    private void createCanonicalization() {
        try {
            getPreferenceValues();

            IWorkbenchPart workbenchPart = getWorkbenchPart();

            if (workbenchPart != null && workbenchPart instanceof ITextEditor) {
                editor = (ITextEditor) workbenchPart;
            } else {
                editor = null;
            }

            if (editor != null && editor.isEditable()) { // call in editor
                if (editor.isDirty()) {
                    saveEditorContent(editor);
                }

                IEditorInput input = editor.getEditorInput();
                IDocument document = editor.getDocumentProvider().getDocument(input);
                file = (IFile) input.getAdapter(IFile.class);

                if (file != null) {
                    byte[] outputBytes = canonicalize(file.getContents());

                    if (canonTarget.equals("internal")) {
                        document.set(new String(outputBytes, "UTF8"));
                    } else {
                        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

                        if (page != null) {
                            IFile newFile = saveCanonicalizedFile(getCanonicalizedPath(), outputBytes);
                            IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
                                    .getDefaultEditor(newFile.getName());
                            page.openEditor(new FileEditorInput(newFile), desc.getId());
                        }
                    }
                } else {
                    showInfo(Messages.canonicalizationImpossible, NLS.bind(Messages.protectedDoc, ACTION));
                }
            } else if (file != null && file.isAccessible() && !file.isReadOnly()) { // call in view
                byte[] outputBytes = canonicalize(file.getContents());

                if (canonTarget.equals("internal")) {
                    saveCanonicalizedFile(file.getLocation(), outputBytes);
                } else {
                    saveCanonicalizedFile(getCanonicalizedPath(), outputBytes);
                }

            } else {
                showInfo(Messages.canonicalizationImpossible, NLS.bind(Messages.protectedDoc, ACTION));
            }
        } catch (Exception ex) {
            showErrorDialog(Messages.error, Messages.canonicalizationException, ex);
            log("An error occured during canonicalization", ex); //$NON-NLS-1$
        }
    }

    /**
     * Returns the path (with filename) for the canonicalized XML document. The new filename consists
     * of the old filename with an added <i>_canon</i> and the file extension <i>xml</i>. If the <i>_canon</i>
     * is already added the new filename consists of <i>_canon[x]</i> with a raising number starting
     * with 2.
     *
     * @return The path of the new file
     */
    private IPath getCanonicalizedPath() {
        IPath path = file.getLocation().removeFileExtension();
        String filename = path.lastSegment();
        path = path.removeLastSegments(1);

        if (filename.endsWith("_canon")) {
            filename += "[2].xml";
        } else if (filename.contains("_canon[")) {
            int canonNumber = Integer.parseInt(filename.substring(filename.indexOf("[") + 1,
                    filename.indexOf("]")));
            filename = filename.substring(0, filename.indexOf("[") + 1)
                    + (canonNumber + 1) + "].xml";
        } else {
            filename += "_canon.xml";
        }

        path = path.append(filename);

        return path;
    }

    /**
     * Saves the canonicalized XML document in the active folder with the given file name.
     *
     * @param newFilePath The path and filename of the new canonicalized XML document
     * @param outputBytes The canonicalized data
     * @return The new file
     * @throws Exception to indicate any exceptional condition
     */
    private IFile saveCanonicalizedFile(final IPath newFilePath, final byte[] outputBytes) throws Exception {
        IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(newFilePath);

        if (newFile.exists()) {
            newFile.setContents(new ByteArrayInputStream(outputBytes), true, true, null);
        } else {
            newFile.create(new ByteArrayInputStream(outputBytes), true, null);
        }

        return newFile;
    }

    /**
     * Determines the preference values for canonicalization.
     */
    private void getPreferenceValues() {
        IPreferenceStore store = XmlSecurityPlugin.getDefault().getPreferenceStore();

        canonVersion = store.getString(PreferenceConstants.CANON_TYPE);
        canonTarget = store.getString(PreferenceConstants.CANON_TARGET);
    }

    /**
     * Calls the canonicalization method of the Apache XML Security API and executes the
     * canonicalization.
     *
     * @param stream The XML document to canonicalize as InputStream
     * @return The canonicalized XML
     * @throws Exception Exception during canonicalization
     */
    private byte[] canonicalize(final InputStream stream) throws Exception {
        Canonicalization canonicalization = new Canonicalization();
        byte[] outputBytes = canonicalization.canonicalize(stream, getCanonicalizationAlgorithm());

        return outputBytes;
    }

    /**
     * Determines the canonicalization algorithm (exclusive or inclusive) based on the preference
     * selection and the called action in the context menu (maintain or remove comments).
     *
     * @return The canonicalization algorithm to use
     */
    private String getCanonicalizationAlgorithm() {
        String algorithm = "";
        if (canonType.equals("org.eclipse.wst.xml.security.core.actions.CanonicalizationRemoveComments")) {
            if (canonVersion.equals("exclusive")) {
                algorithm = Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS;
            } else {
                algorithm = Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS;
            }
        } else {
            if (canonVersion.equals("exclusive")) {
                algorithm = Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS;
            } else {
                algorithm = Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS;
            }
        }

        return algorithm;
    }
}
