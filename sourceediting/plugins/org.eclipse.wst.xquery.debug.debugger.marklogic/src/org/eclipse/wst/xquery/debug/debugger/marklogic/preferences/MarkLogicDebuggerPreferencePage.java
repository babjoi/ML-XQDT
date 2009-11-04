/*******************************************************************************
 * Copyright (c) 2009 Mark Logic Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Neth (Mark Logic) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.debug.debugger.marklogic.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.wst.xquery.debug.engines.marklogic.MarkLogicDebuggerConstants;
import org.eclipse.wst.xquery.debug.engines.marklogic.MarkLogicDebuggerPlugin;

public class MarkLogicDebuggerPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {

    static final PreferenceKey LOG_ENABLE = new PreferenceKey(MarkLogicDebuggerPlugin.PLUGIN_ID,
            MarkLogicDebuggerConstants.LOG_ENABLE_KEY);

    static final PreferenceKey LOG_FILE_NAME = new PreferenceKey(MarkLogicDebuggerPlugin.PLUGIN_ID,
            MarkLogicDebuggerConstants.LOG_FILE_NAME);

    static final PreferenceKey ENGINE_NEEDS_DBGP_TRANSLATOR = new PreferenceKey(MarkLogicDebuggerPlugin.PLUGIN_ID,
            MarkLogicDebuggerConstants.DEBUGGING_ENGINE_NEEDS_DBGP_TRANSLATOR);

    static final PreferenceKey ENGINE_SERVER_PORTS = new PreferenceKey(MarkLogicDebuggerPlugin.PLUGIN_ID,
            MarkLogicDebuggerConstants.DEBUGGING_ENGINE_SERVER_PORTS);

    private static final String PREFERENCE_PAGE_ID = "org.eclipse.wst.xquery.preferences.debug.engines.marklogic";
    private static final String PROPERTY_PAGE_ID = "org.eclipse.wst.xquery.properties.debug.engines.marklogic";

    public MarkLogicDebuggerPreferencePage() {
        noDefaultAndApplyButton();
    }

    protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener, IProject project,
            IWorkbenchPreferenceContainer container) {
        return new MarkLogicDebuggerBlock(newStatusChangedListener, project, getKeys(), container);
    }

    protected String getHelpId() {
        return null;
    }

    protected String getProjectHelpId() {
        return null;
    }

    protected void setDescription() {
        setDescription("MarkLogic debugger settings");
    }

    protected void setPreferenceStore() {
        setPreferenceStore(MarkLogicDebuggerPlugin.getDefault().getPreferenceStore());
    }

    protected String getPreferencePageId() {
        return PREFERENCE_PAGE_ID;
    }

    protected String getPropertyPageId() {
        return PROPERTY_PAGE_ID;
    }

    private PreferenceKey[] getKeys() {
        return new PreferenceKey[] { LOG_ENABLE, LOG_FILE_NAME, ENGINE_NEEDS_DBGP_TRANSLATOR, ENGINE_SERVER_PORTS };
    }
}
