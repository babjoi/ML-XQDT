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

    private IResource resource;
    private String markerType;
    private String newURI;
    private int start;
    private int length;

    public ChangeProjectLogicalURICorrection(IScriptAnnotation annotation) throws CoreException {
        assert annotation instanceof ScriptMarkerAnnotation;
        IMarker marker = ((ScriptMarkerAnnotation)annotation).getMarker();
        resource = annotation.getSourceModule().getResource();
        start = (Integer)marker.getAttribute("charStart");
        length = ((Integer)marker.getAttribute("charEnd")) - start;
        markerType = marker.getType();
    }

    public void apply(IDocument document) {
        try {
            IProject project = resource.getProject();
            String moduleNS = document.get(start + 1, length - 1);
            URI newLogicalURI = new URI(moduleNS.substring(0, moduleNS.lastIndexOf('/')));
            SETProjectConfig config = SETProjectConfigUtil.readProjectConfig(project);
            config.setLogicalUri(newLogicalURI);
            SETProjectConfigUtil.writeProjectConfig(project, config);
            resource.deleteMarkers(markerType, false, IResource.DEPTH_INFINITE);
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
        return getDisplayString() + " to " + newURI;
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
