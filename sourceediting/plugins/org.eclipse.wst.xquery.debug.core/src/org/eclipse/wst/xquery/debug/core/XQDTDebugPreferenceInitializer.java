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
package org.eclipse.wst.xquery.debug.core;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;

public class XQDTDebugPreferenceInitializer extends AbstractPreferenceInitializer {

    public void initializeDefaultPreferences() {
        IEclipsePreferences store = new DefaultScope().getNode(XQDTDebugCorePlugin.PLUGIN_ID);

        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE, false);
        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING, false);

        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL, true);
        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS, false);
        store.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL, true);
    }
}
