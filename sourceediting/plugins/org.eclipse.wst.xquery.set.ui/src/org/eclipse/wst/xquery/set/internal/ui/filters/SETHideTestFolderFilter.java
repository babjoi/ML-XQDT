package org.eclipse.wst.xquery.set.internal.ui.filters;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.wst.xquery.set.core.ISETCoreConstants;
import org.eclipse.wst.xquery.set.core.SETNature;

public class SETHideTestFolderFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof IFolder
                && ((IFolder)element).getName().equals(ISETCoreConstants.PROJECT_DIRECTORY_TEST)) {
            IProject project = null;
            if (parentElement instanceof IScriptProject) {
                project = ((IScriptProject)parentElement).getProject();
            } else if (parentElement instanceof IProject) {
                project = (IProject)parentElement;
            }
            try {
                if (project != null && project.hasNature(SETNature.NATURE_ID)) {
                    return false;
                }
            } catch (CoreException e) {
            }
        }
        return true;
    }
}
