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

import org.eclipse.dltk.ui.preferences.CodeAssistConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;

public class XQDTContentAssistConfigurationBlock extends CodeAssistConfigurationBlock {

    public XQDTContentAssistConfigurationBlock(PreferencePage mainPreferencePage, OverlayPreferenceStore store) {
        super(mainPreferencePage, store);
    }

}
