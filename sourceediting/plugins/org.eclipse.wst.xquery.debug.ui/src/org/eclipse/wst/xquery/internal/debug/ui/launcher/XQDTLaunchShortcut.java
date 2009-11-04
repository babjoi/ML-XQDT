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
package org.eclipse.wst.xquery.internal.debug.ui.launcher;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.dltk.internal.debug.ui.launcher.AbstractScriptLaunchShortcut;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.wst.xquery.core.XQDTNature;

public class XQDTLaunchShortcut extends AbstractScriptLaunchShortcut {

    protected ILaunchConfigurationType getConfigurationType() {
        return getLaunchManager().getLaunchConfigurationType("org.eclipse.wst.xquery.launching.XQDTLaunchConfigurationType");
    }

    protected String getNatureId() {
        return XQDTNature.NATURE_ID;
    }

    @Override
    protected String getScriptSelectionTitle() {
        return "Select an XQuery main module to launch";
    }

    @Override
    protected String getSelectionEmptyMessage() {
        return "The project contains no XQuery main modules";
    }

    @Override
    protected ILaunchConfiguration createConfiguration(IResource script) {
        ILaunchConfiguration config = super.createConfiguration(script);

        ILaunchConfigurationWorkingCopy wc = null;
        try {
            wc = config.getWorkingCopy();
            wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DEBUG_CONSOLE, false);
            config = wc.doSave();
        } catch (CoreException exception) {
            exception.printStackTrace();
        }
        return config;
    }

}