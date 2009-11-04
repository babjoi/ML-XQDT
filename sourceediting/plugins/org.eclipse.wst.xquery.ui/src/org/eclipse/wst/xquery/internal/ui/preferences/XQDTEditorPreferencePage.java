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
package org.eclipse.wst.xquery.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.EditorConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTEditorPreferencePage extends AbstractConfigurationBlockPreferencePage {

    protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore overlayPreferenceStore) {
        return new EditorConfigurationBlock(this, overlayPreferenceStore);
    }

    protected String getHelpId() {
        return null;
    }

    protected void setDescription() {
        setDescription("XQuery Editor");
    }

    protected void setPreferenceStore() {
        setPreferenceStore(XQDTUIPlugin.getDefault().getPreferenceStore());
    }

}
