package org.eclipse.wst.xquery.set.debug.debugger.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.ui.preferences.DebuggingEngineConfigOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class SETDebuggerBlock extends DebuggingEngineConfigOptionsBlock {

    public SETDebuggerBlock(IStatusChangeListener context, IProject project, PreferenceKey[] allKeys,
            IWorkbenchPreferenceContainer container) {
        super(context, project, allKeys, container);
    }

    protected void createEngineBlock(Composite parent) {
    }

    protected PreferenceKey getLogFileNamePreferenceKey() {
        return SETDebuggerPreferencePage.LOG_FILE_NAME;
    }
}
