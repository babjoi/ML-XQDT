/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.debug.debugger.zorba.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.wst.xquery.debug.debugger.zorba.ZorbaDebuggerConstants;
import org.eclipse.wst.xquery.debug.debugger.zorba.ZorbaDebuggerPlugin;

public class ZorbaDebuggerPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {

    static final PreferenceKey LOG_ENABLE = new PreferenceKey(ZorbaDebuggerPlugin.PLUGIN_ID,
            ZorbaDebuggerConstants.LOG_ENABLE_KEY);

    static final PreferenceKey LOG_FILE_NAME = new PreferenceKey(ZorbaDebuggerPlugin.PLUGIN_ID,
            ZorbaDebuggerConstants.LOG_FILE_NAME);

    private static final String PREFERENCE_PAGE_ID = "org.eclipse.wst.xquery.preferences.debug.engines.zorba";
    private static final String PROPERTY_PAGE_ID = "org.eclipse.wst.xquery.properties.debug.engines.zorba";

    public ZorbaDebuggerPreferencePage() {
        noDefaultAndApplyButton();
    }

    protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener, IProject project,
            IWorkbenchPreferenceContainer container) {
        return new ZorbaDebuggerBlock(newStatusChangedListener, project, getKeys(), container);
    }

    protected String getHelpId() {
        return null;
    }

    protected String getProjectHelpId() {
        return null;
    }

    protected void setDescription() {
        setDescription("Zorba debugger settings");
    }

    protected void setPreferenceStore() {
        setPreferenceStore(ZorbaDebuggerPlugin.getDefault().getPreferenceStore());
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
