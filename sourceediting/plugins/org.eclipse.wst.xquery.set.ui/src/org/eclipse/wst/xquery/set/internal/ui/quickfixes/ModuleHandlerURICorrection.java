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
import org.eclipse.wst.xquery.set.core.SETProjectConfigUtil;
import org.eclipse.wst.xquery.set.internal.ui.util.SETPluginImages;

public class ModuleHandlerURICorrection implements IScriptCompletionProposal {

    private String newURI;
    private int start;
    private int length;

    public ModuleHandlerURICorrection(IScriptAnnotation annotation) throws CoreException {
        if (annotation instanceof ScriptMarkerAnnotation) {
            IMarker marker = ((ScriptMarkerAnnotation)annotation).getMarker();
            ISourceModule module = annotation.getSourceModule();
            IFile file = (IFile)module.getResource();
            IPath location = file.getLocation();
            String parentDir = location.segment(location.segmentCount() - 2);

            IProject project = file.getProject();
            SETProjectConfig projectConfig = SETProjectConfigUtil.readProjectConfig(project);
            URI logicalURI = projectConfig.getLogicalUri();
            String name = module.getResource().getName();
            String moduleName = name.substring(0, name.indexOf('.'));
            String lURI = logicalURI.toString();
            if (lURI.charAt(lURI.length() - 1) != '/') {
                lURI += '/';
            }
            if ("lib".equals(parentDir)) {
                lURI += "lib/";
            }
            newURI = lURI + moduleName;
            start = (Integer)marker.getAttribute("charStart");
            length = (Integer)marker.getAttribute("charEnd") - start;
            assert length >= 0;
        }
    }

    public void apply(IDocument document) {
        try {
            //the +1 comes from a bug in zorba query location
            document.replace(start + 1, length - 1, newURI);
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Point getSelection(IDocument document) {
        return null;
    }

    public String getAdditionalProposalInfo() {
        return getDisplayString() + " to " + newURI + '.';
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
