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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.xquery.debug.debugger.zorba.ZorbaDebuggerConstants;
import org.eclipse.wst.xquery.debug.debugger.zorba.ZorbaDebuggerPlugin;

public class ZorbaDebuggerPreferenceInitializer extends AbstractPreferenceInitializer {

    public void initializeDefaultPreferences() {
        IPreferenceStore store = ZorbaDebuggerPlugin.getDefault().getPreferenceStore();

        ZorbaDebuggerConstants.initalizeDefaults(store);
    }
}
