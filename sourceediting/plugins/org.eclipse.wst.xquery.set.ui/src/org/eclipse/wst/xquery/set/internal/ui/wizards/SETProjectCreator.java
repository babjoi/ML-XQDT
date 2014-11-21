package org.eclipse.wst.xquery.set.internal.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.ui.wizards.ILocationGroup;
import org.eclipse.dltk.ui.wizards.IProjectWizard;
import org.eclipse.dltk.ui.wizards.ProjectCreator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.xquery.set.internal.ui.preferences.PreferenceConstants;

public class SETProjectCreator extends ProjectCreator {

    public SETProjectCreator(IProjectWizard owner, ILocationGroup locationGroup) {
        super(owner, locationGroup);
    }

    @Override
    protected IBuildpathEntry[] initBuildpath(IProgressMonitor monitor) throws CoreException {
        IBuildpathEntry[] cpEntries = super.initBuildpath(monitor);

        IPreferenceStore store = getPreferenceStore();
        String handlerSrc = store.getString(PreferenceConstants.BUILD_PATH_HANDLER_DIR);
        String libSrc = store.getString(PreferenceConstants.BUILD_PATH_LIBRARY_DIR);

        IProject project = getProject();
        List<IBuildpathEntry> cpEntriesList = new ArrayList<IBuildpathEntry>();
        cpEntriesList.add(DLTKCore.newSourceEntry(project.getFullPath().append(handlerSrc)));
        cpEntriesList.add(DLTKCore.newSourceEntry(project.getFullPath().append(libSrc)));
        if (cpEntries != null && cpEntries.length > 0) {
            cpEntriesList.add(cpEntries[cpEntries.length - 1]);
        }

        return cpEntriesList.toArray(new IBuildpathEntry[cpEntriesList.size()]);
    }

    protected IPreferenceStore getPreferenceStore() {
        return getUILanguageToolkit().getPreferenceStore();
    }

}
