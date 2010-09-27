package org.eclipse.wst.xquery.debug.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.dltk.core.DLTKPriorityContributionSelector;
import org.eclipse.dltk.core.IDLTKContributedExtension;

public class XQDTDebugEngineSelector extends DLTKPriorityContributionSelector {

    @Override
    public IDLTKContributedExtension select(IDLTKContributedExtension[] contributions, IProject project) {
        if (contributions != null) {
            IEclipsePreferences preferences = (new InstanceScope()).getNode(XQDTDebugCorePlugin.PLUGIN_ID);
            if (preferences != null) {
                String debugEngineID = preferences.get(IXQDTDebugConstants.DEBUGGING_ENGINE_ID_KEY, null);
                if (debugEngineID != null) {
                    for (int i = 0; i < contributions.length; i++) {
                        if (contributions[i].getId().equals(debugEngineID)) {
                            return contributions[i];
                        }
                    }
                }
            }
        }

        // Fallback
        return super.select(contributions, project);
    }
}
