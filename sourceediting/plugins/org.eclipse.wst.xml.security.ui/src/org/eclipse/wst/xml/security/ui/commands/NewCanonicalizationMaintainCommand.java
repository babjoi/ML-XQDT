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
package org.eclipse.wst.xml.security.ui.commands;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.apache.xml.security.c14n.Canonicalizer;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wst.xml.security.core.canonicalize.Canonicalization;
import org.eclipse.wst.xml.security.ui.XSTUIPlugin;
import org.eclipse.wst.xml.security.ui.actions.Messages;
import org.eclipse.wst.xml.security.ui.preferences.PreferenceConstants;

/**
 * 
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class NewCanonicalizationMaintainCommand extends AbstractHandler {
    /** Canonicalization version (exclusive or inclusive). */
    private String canonVersion;
    /** Canonicalization target (same or new document). */
    private String canonTarget;

    public Object execute(ExecutionEvent event) throws ExecutionException {
        getPreferenceValues();
        
        if (HandlerUtil.getActiveEditor(event) != null) {
            final IEditorPart editorPart = HandlerUtil.getActiveEditor(event);

            if (editorPart.isDirty()) {
                if (null != editorPart.getTitle() && editorPart.getTitle().length() > 0) {
                    IRunnableWithProgress op = new IRunnableWithProgress() {
                        public void run(final IProgressMonitor monitor) {
                            editorPart.doSave(monitor);
                        }
                    };
                    try {
                        PlatformUI.getWorkbench().getProgressService().runInUI(XSTUIPlugin.getActiveWorkbenchWindow(), op, ResourcesPlugin.getWorkspace().getRoot());
                    } catch (InvocationTargetException ite) {
                        log("Error while saving editor content", ite); //$NON-NLS-1$
                    } catch (InterruptedException ie) {
                        log("Error while saving editor content", ie); //$NON-NLS-1$
                    }
                } else {
                    editorPart.doSaveAs();
                }
            }
            
//            IEditorInput input = editorPart.getEditorInput();
//            IDocument document = editorPart.getDocumentProvider().getDocument(input);
//            file = (IFile) input.getAdapter(IFile.class);
//
//            if (file != null) {
//                byte[] outputBytes = canonicalize(file.getContents());
//
//                if (canonTarget.equals("internal")) {
//                    document.set(new String(outputBytes, "UTF8"));
//                } else {
//                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//
//                    if (page != null) {
//                        IFile newFile = saveCanonicalizedFile(getCanonicalizedPath(), outputBytes);
//                        IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
//                                .getDefaultEditor(newFile.getName());
//                        page.openEditor(new FileEditorInput(newFile), desc.getId());
//                    }
//                }
//            } else {
//                showInfo(Messages.canonicalizationImpossible, NLS.bind(Messages.protectedDoc, ACTION));
//            }
        } else if (HandlerUtil.getCurrentSelection(event) != null) {
            System.out.println("juhu");
            ISelection selection = HandlerUtil.getCurrentSelection(event);
            if (selection instanceof IStructuredSelection) {
                IFile file = (IFile) ((IStructuredSelection) selection).getFirstElement();
            }
        }
        
        return null;
    }

    private void log(final String message, final Exception ex) {
        IStatus status = new Status(IStatus.ERROR, XSTUIPlugin.getDefault().getBundle().getSymbolicName(), 0, message, ex);
        XSTUIPlugin.getDefault().getLog().log(status);
    }

    /**
     * Determines the preference values for canonicalization.
     */
    private void getPreferenceValues() {
        IPreferenceStore store = XSTUIPlugin.getDefault().getPreferenceStore();

        canonVersion = store.getString(PreferenceConstants.CANON_TYPE);
        canonTarget = store.getString(PreferenceConstants.CANON_TARGET);
    }

    /**
     * Returns the path (with filename) for the canonicalized XML document. The new filename consists
     * of the old filename with an added <i>_canon</i> and the file extension <i>xml</i>. If the <i>_canon</i>
     * is already added the new filename consists of <i>_canon[x]</i> with a raising number starting
     * with 2.
     *
     * @return The path of the new file
     */
//    private IPath getCanonicalizedPath() {
//        IPath path = file.getLocation().removeFileExtension();
//        String filename = path.lastSegment();
//        path = path.removeLastSegments(1);
//
//        if (filename.endsWith("_canon")) {
//            filename += "[2].xml";
//        } else if (filename.contains("_canon[")) {
//            int canonNumber = Integer.parseInt(filename.substring(filename.indexOf("[") + 1,
//                    filename.indexOf("]")));
//            filename = filename.substring(0, filename.indexOf("[") + 1)
//                    + (canonNumber + 1) + "].xml";
//        } else {
//            filename += "_canon.xml";
//        }
//
//        path = path.append(filename);
//
//        return path;
//    }

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
        
        if (canonVersion.equals("exclusive")) {
            algorithm = Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS;
        } else {
            algorithm = Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS;
        }

        return algorithm;
    }

}
