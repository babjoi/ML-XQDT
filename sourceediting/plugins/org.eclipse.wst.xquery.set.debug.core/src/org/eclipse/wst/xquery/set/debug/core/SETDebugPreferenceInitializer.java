package org.eclipse.wst.xquery.set.debug.core;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;

public class SETDebugPreferenceInitializer extends AbstractPreferenceInitializer {

    public void initializeDefaultPreferences() {
        IEclipsePreferences store = new DefaultScope().getNode(SETDebugCorePlugin.PLUGIN_ID);

        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE, false);
        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING, false);

        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL, true);
        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS, false);
        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL, true);
    }

}
