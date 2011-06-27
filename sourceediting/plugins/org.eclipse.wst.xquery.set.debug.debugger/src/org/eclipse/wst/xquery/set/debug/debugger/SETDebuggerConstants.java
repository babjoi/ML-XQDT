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
package org.eclipse.wst.xquery.set.debug.debugger;

import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

public final class SETDebuggerConstants {

    public static final String ENGINE_ID = "org.eclipse.wst.xquery.set.debug.engine";

    public static final String LOG_ENABLE_KEY = "debugging_engine_log_enable";
    public static final String LOG_FILE_NAME = "log_file_name";

    public static void initalizeDefaults(IPreferenceStore store) {
        store.setDefault(LOG_ENABLE_KEY, Util.EMPTY_STRING);
        store.setDefault(LOG_FILE_NAME, Util.EMPTY_STRING);

        store.setDefault(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL, true);
        store.setDefault(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS, false);
        store.setDefault(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL, true);

    }

    private SETDebuggerConstants() {
        // private constructor
    }
}
