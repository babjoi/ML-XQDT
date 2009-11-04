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

import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class SETBuildPathPropertyPage extends BuildPathsPropertyPage {

    protected BuildpathsBlock createBuildPathBlock(IWorkbenchPreferenceContainer pageContainer) {
        return new SETBuildPathsBlock(new BusyIndicatorRunnableContext(), this, getSettings().getInt(INDEX), false,
                pageContainer);
    }

}
