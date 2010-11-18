/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.ui.quickfixes;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.editor.IScriptAnnotation;
import org.eclipse.dltk.ui.editor.ScriptMarkerAnnotation;
import org.eclipse.dltk.ui.text.completion.IScriptCompletionProposal;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wst.xquery.set.core.SETProjectConfig;
import org.eclipse.wst.xquery.set.core.utils.SETProjectConfigUtil;
import org.eclipse.wst.xquery.set.internal.ui.util.SETPluginImages;

public class ModuleHandlerURICorrection implements IScriptCompletionProposal {

    private String fNewUri;
    private int fStart;
    private int fLength;

    public ModuleHandlerURICorrection(IScriptAnnotation annotation) throws CoreException {
        if (annotation instanceof ScriptMarkerAnnotation) {
            IMarker marker = ((ScriptMarkerAnnotation)annotation).getMarker();
            ISourceModule module = annotation.getSourceModule();
            IFile file = (IFile)module.getResource();
            IPath location = file.getProjectRelativePath();
            String parentDir = location.segment(0);

            IProject project = file.getProject();
            SETProjectConfig projectConfig = SETProjectConfigUtil.readProjectConfig(project);
            URI logicalURI = projectConfig.getLogicalUri();
            String name = module.getResource().getName();
            String moduleName = name.substring(0, name.indexOf('.'));
            String lURI = logicalURI.toString();
            if (lURI.charAt(lURI.length() - 1) != '/') {
                lURI += '/';
            }

            if ("handlers".equals(parentDir)) {
                fNewUri = lURI + moduleName;
            } else {
                location = location.removeLastSegments(1).append(moduleName);
                fNewUri = lURI + location.toPortableString();
            }

            fStart = (Integer)marker.getAttribute("charStart");
            fLength = (Integer)marker.getAttribute("charEnd") - fStart;
            assert fLength >= 0;
        }
    }

    public void apply(IDocument document) {
        try {
            document.replace(fStart, fLength, fNewUri);
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Point getSelection(IDocument document) {
        return null;
    }

    public String getAdditionalProposalInfo() {
        return getDisplayString() + " to " + fNewUri + '.';
    }

    public String getDisplayString() {
        return "Change Module Handler URI";
    }

    public Image getImage() {
        return SETPluginImages.OBJ16_CHANGE_NAMESPACE.createImage();
    }

    public IContextInformation getContextInformation() {
        return null;
    }

    public int getRelevance() {
        return 10;
    }

}
