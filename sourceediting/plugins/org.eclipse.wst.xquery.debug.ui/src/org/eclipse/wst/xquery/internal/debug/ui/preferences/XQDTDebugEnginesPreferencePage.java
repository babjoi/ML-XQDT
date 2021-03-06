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
package org.eclipse.wst.xquery.internal.debug.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.preferences.AbstractDebuggingOptionsBlock;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.debug.core.XQDTDebugCorePlugin;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTDebugEnginesPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {

    private static final PreferenceKey BREAK_ON_FIRST_LINE = new PreferenceKey(XQDTDebugCorePlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);

    private static final PreferenceKey ENABLE_DBGP_LOGGING = new PreferenceKey(XQDTDebugCorePlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);

    private static final String PREFERENCE_PAGE_ID = "org.eclipse.wst.xquery.preferences.debug";
    private static final String PROPERTY_PAGE_ID = "org.eclipse.wst.xquery.properties.debug";

    protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener, IProject project,
            IWorkbenchPreferenceContainer container) {
        return new AbstractDebuggingOptionsBlock(newStatusChangedListener, project, getKeys(), container) {

            protected PreferenceKey getBreakOnFirstLineKey() {
                return BREAK_ON_FIRST_LINE;
            }

            protected PreferenceKey getDbgpLoggingEnabledKey() {
                return ENABLE_DBGP_LOGGING;
            }
        };
    }

    protected String getHelpId() {
        return null;
    }

    protected String getProjectHelpId() {
        return null;
    }

    protected void setDescription() {
        setDescription("General settings for XQuery debugging");
    }

    protected void setPreferenceStore() {
        setPreferenceStore(XQDTUIPlugin.getDefault().getPreferenceStore());
    }

    protected String getPreferencePageId() {
        return PREFERENCE_PAGE_ID;
    }

    protected String getPropertyPageId() {
        return PROPERTY_PAGE_ID;
    }

    @Override
    protected String getNatureId() {
        return XQDTNature.NATURE_ID;
    }

    protected PreferenceKey[] getKeys() {
        return new PreferenceKey[] { BREAK_ON_FIRST_LINE, ENABLE_DBGP_LOGGING };
    }

}