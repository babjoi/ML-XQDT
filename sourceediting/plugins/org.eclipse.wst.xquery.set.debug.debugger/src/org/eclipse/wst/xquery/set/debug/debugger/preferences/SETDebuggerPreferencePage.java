package org.eclipse.wst.xquery.set.debug.debugger.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.wst.xquery.set.debug.debugger.SETDebuggerConstants;
import org.eclipse.wst.xquery.set.debug.debugger.SETDebuggerPlugin;

public class SETDebuggerPreferencePage extends
        org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage {

    static final PreferenceKey LOG_ENABLE = new PreferenceKey(SETDebuggerPlugin.PLUGIN_ID,
            SETDebuggerConstants.LOG_ENABLE_KEY);

    static final PreferenceKey LOG_FILE_NAME = new PreferenceKey(SETDebuggerPlugin.PLUGIN_ID,
            SETDebuggerConstants.LOG_FILE_NAME);

    private static final String PREFERENCE_PAGE_ID = "org.eclipse.wst.xquery.set.preferences.debug.engines.sausalito";
    private static final String PROPERTY_PAGE_ID = "org.eclipse.wst.xquery.set.properties.debug.engines.sausalito";

    protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener, IProject project,
            IWorkbenchPreferenceContainer container) {
        return new SETDebuggerBlock(newStatusChangedListener, project, getKeys(), container);
    }

    protected String getHelpId() {
        return null;
    }

    protected String getProjectHelpId() {
        return null;
    }

    protected void setDescription() {
        setDescription("Sausalito debugger settings");
    }

    protected void setPreferenceStore() {
        setPreferenceStore(SETDebuggerPlugin.getDefault().getPreferenceStore());
    }

    protected String getPreferencePageId() {
        return PREFERENCE_PAGE_ID;
    }

    protected String getPropertyPageId() {
        return PROPERTY_PAGE_ID;
    }

    private PreferenceKey[] getKeys() {
        return new PreferenceKey[] { LOG_ENABLE, LOG_FILE_NAME };
    }

}
