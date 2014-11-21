package org.eclipse.wst.xquery.internal.launching.debug;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKPriorityContributionSelector;
import org.eclipse.dltk.core.IDLTKContributedExtension;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;
import org.eclipse.wst.xquery.debug.core.IXQDTDebugConstants;
import org.eclipse.wst.xquery.debug.core.XQDTDebugCorePlugin;

public class XQDTDebugEngineSelector extends DLTKPriorityContributionSelector {

    @Override
    public IDLTKContributedExtension select(IDLTKContributedExtension[] contributions, IProject project) {
        if (contributions != null) {
            try {
                IInterpreterInstall interpreter = ScriptRuntime.getInterpreterInstall(DLTKCore.create(project));
                //interpreter.getInterpreterInstallType().getgetId();

                for (IDLTKContributedExtension extension : contributions) {
                    if (extension instanceof IDebuggingEngine) {
                        IDebuggingEngine engine = (IDebuggingEngine)extension;
                        if (engine.getRunner(interpreter) != null) {
                            return extension;
                        }
                    }
                }
            } catch (CoreException ce) {
                // nothing to do
            }

            // if no success above, try to use the selected debugging engine in the preferences 
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

        // as fall back, go to the default priority based implementation 
        return super.select(contributions, project);
    }
}
