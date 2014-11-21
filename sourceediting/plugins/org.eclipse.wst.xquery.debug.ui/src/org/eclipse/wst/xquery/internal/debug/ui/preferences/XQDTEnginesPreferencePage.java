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
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.dltk.debug.ui.preferences.AbstractDebuggingEngineOptionsBlock;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.debug.core.IXQDTDebugConstants;
import org.eclipse.wst.xquery.debug.core.XQDTDebugCorePlugin;

public class XQDTEnginesPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {

    static PreferenceKey DEBUGGING_ENGINE = new PreferenceKey(XQDTDebugCorePlugin.PLUGIN_ID,
            IXQDTDebugConstants.DEBUGGING_ENGINE_ID_KEY);

    private static final String PREFERENCE_PAGE_ID = "org.eclipse.wst.xquery.preferences.debug.engines";
    private static final String PROPERTY_PAGE_ID = "org.eclipse.wst.xquery.properties.debug.engines";

    protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener, IProject project,
            IWorkbenchPreferenceContainer container) {
        return new AbstractDebuggingEngineOptionsBlock(newStatusChangedListener, project, getKeys(), container) {

            protected PreferenceKey getSavedContributionKey() {
                return DEBUGGING_ENGINE;
            }

            protected String getNatureId() {
                return XQDTNature.NATURE_ID;
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
        setDescription("XQuery Debugging Engine Selection");
    }

    protected void setPreferenceStore() {
        setPreferenceStore(new ScopedPreferenceStore(new InstanceScope(), XQDTDebugCorePlugin.PLUGIN_ID));
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

    private PreferenceKey[] getKeys() {
        return new PreferenceKey[] { DEBUGGING_ENGINE };
    }

}