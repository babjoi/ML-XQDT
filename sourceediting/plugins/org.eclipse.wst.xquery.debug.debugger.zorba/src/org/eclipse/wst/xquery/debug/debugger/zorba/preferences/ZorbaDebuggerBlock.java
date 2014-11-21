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
import org.eclipse.dltk.debug.ui.preferences.DebuggingEngineConfigOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class ZorbaDebuggerBlock extends DebuggingEngineConfigOptionsBlock {

    public ZorbaDebuggerBlock(IStatusChangeListener context, IProject project, PreferenceKey[] allKeys,
            IWorkbenchPreferenceContainer container) {
        super(context, project, allKeys, container);
    }

    protected PreferenceKey getLogFileNamePreferenceKey() {
        return ZorbaDebuggerPreferencePage.LOG_FILE_NAME;
    }

    protected void createEngineBlock(Composite parent) {
    }

}