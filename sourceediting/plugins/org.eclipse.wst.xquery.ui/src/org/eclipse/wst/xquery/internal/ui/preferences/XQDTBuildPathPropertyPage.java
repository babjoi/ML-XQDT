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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class XQDTBuildPathPropertyPage extends BuildPathsPropertyPage implements IWorkbenchPropertyPage {

    protected BuildpathsBlock createBuildPathBlock(IWorkbenchPreferenceContainer pageContainer) {
        return new XQDTBuildPathsBlock(new BusyIndicatorRunnableContext(), this, getSettings().getInt(INDEX), false,
                pageContainer);
    }

    protected IScriptProject getScriptProject() {
        IAdaptable adaptable = getElement();
        if (adaptable != null) {
            IModelElement elem = (IModelElement)adaptable.getAdapter(IModelElement.class);
            if (elem instanceof IScriptProject) {
                return (IScriptProject)elem;
            }
        }
        return null;
    }

}
