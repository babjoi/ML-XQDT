/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *     Gabriel Petrovay (28msec)
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.ui.wizards;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;
import org.eclipse.wst.xquery.internal.ui.util.XQDTPluginImages;

public class XQDTNewModuleWizard extends NewSourceModuleWizard {

    public final static String WIZARD_ID = "org.eclipse.wst.xquery.ui.wizards.newmodule";

    public XQDTNewModuleWizard() {
        setDefaultPageImageDescriptor(XQDTPluginImages.DESC_WIZBAN_NEW_MODULE);
        setWindowTitle("New XQuery Module");
    }

    protected NewSourceModulePage createNewSourceModulePage() {
        return new XQDTNewModuleWizardPage();
    }
}
