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
package org.eclipse.wst.xquery.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.wst.xquery.core.XQDTNature;

public class XQDTMainLaunchConfigurationTab extends MainLaunchConfigurationTab {

    public XQDTMainLaunchConfigurationTab(String mode) {
        super(mode);
    }

    @Override
    protected void performApplyConnectionTimeout(ILaunchConfigurationWorkingCopy config) {
        super.performApplyConnectionTimeout(config);
        config.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DEBUG_CONSOLE, false);
    }

    public String getNatureID() {
        return XQDTNature.NATURE_ID;
    }
}
