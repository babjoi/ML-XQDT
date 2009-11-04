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
package org.eclipse.wst.xquery.set.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

public class SETGlobalPreferencesPage extends AbstractConfigurationBlockPreferencePage {

    public SETGlobalPreferencesPage() {
    }

    @Override
    protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore overlayPreferenceStore) {
        return new AbstractConfigurationBlock(overlayPreferenceStore, this) {

            public Control createControl(Composite parent) {
                Composite composite = SWTFactory.createComposite(parent, parent.getFont(), 1, 1,
                        GridData.FILL_HORIZONTAL);
                return composite;
            }
        };
    }

    @Override
    protected String getHelpId() {
        return null;
    }

    @Override
    protected void setDescription() {
        setDescription("General Sausalito preferences");
    }

    @Override
    protected void setPreferenceStore() {
        setPreferenceStore(SETUIPlugin.getDefault().getPreferenceStore());
    }

}
