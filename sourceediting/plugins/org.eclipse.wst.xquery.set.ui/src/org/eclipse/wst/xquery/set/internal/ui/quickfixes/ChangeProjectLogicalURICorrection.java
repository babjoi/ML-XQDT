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
import java.net.URISyntaxException;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ui.editor.IScriptAnnotation;
import org.eclipse.dltk.ui.editor.ScriptMarkerAnnotation;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wst.xquery.set.core.SETProjectConfig;
import org.eclipse.wst.xquery.set.core.SETProjectConfigUtil;
import org.eclipse.wst.xquery.set.internal.ui.util.SETPluginImages;

public class ChangeProjectLogicalURICorrection implements ICompletionProposal {

    private IResource fResource;
    private String fMarkerType;
    private String fNewUri;
    private int fStart;
    private int fLength;

    public ChangeProjectLogicalURICorrection(IScriptAnnotation annotation) throws CoreException {
        assert annotation instanceof ScriptMarkerAnnotation;
        IMarker marker = ((ScriptMarkerAnnotation)annotation).getMarker();
        fResource = annotation.getSourceModule().getResource();
        fStart = (Integer)marker.getAttribute("charStart");
        fLength = ((Integer)marker.getAttribute("charEnd")) - fStart;
        fMarkerType = marker.getType();
    }

    public void apply(IDocument document) {
        try {
            IProject project = fResource.getProject();
            String moduleNS = document.get(fStart, fLength);
            URI newLogicalURI = new URI(moduleNS.substring(0, moduleNS.lastIndexOf('/')));
            SETProjectConfig config = SETProjectConfigUtil.readProjectConfig(project);
            config.setLogicalUri(newLogicalURI);
            SETProjectConfigUtil.writeProjectConfig(project, config);
            fResource.deleteMarkers(fMarkerType, false, IResource.DEPTH_INFINITE);
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Point getSelection(IDocument document) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getAdditionalProposalInfo() {
        // TODO Auto-generated method stub
        return getDisplayString() + " to " + fNewUri;
    }

    public String getDisplayString() {
        return "Change project logical URI";
    }

    public Image getImage() {
        return SETPluginImages.OBJ16_RENAME_NAMESPACE.createImage();
    }

    public IContextInformation getContextInformation() {
        return null;
    }

}
