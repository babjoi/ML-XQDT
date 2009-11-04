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
package org.eclipse.wst.xquery.internal.debug.ui.interpreters;

import org.eclipse.dltk.debug.ui.launchConfigurations.IMainLaunchConfigurationTabListenerManager;
import org.eclipse.dltk.debug.ui.launchConfigurations.InterpreterTab;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xquery.core.XQDTNature;

public class XQDTInterpreterTab extends InterpreterTab {

    public XQDTInterpreterTab(IMainLaunchConfigurationTabListenerManager listenerManager) {
        super(listenerManager);
    }

    protected AbstractInterpreterComboBlock getInterpreterBlock() {
        return new XQDTInterpreterComboBlock(getMainTab());
    }

    protected String getNature() {
        return XQDTNature.NATURE_ID;
    }

    @Override
    public void createControl(Composite parent) {
        super.createControl(parent);

        Composite comp = (Composite)getControl();
        createVerticalSpacer(comp, 1);

    }
}
