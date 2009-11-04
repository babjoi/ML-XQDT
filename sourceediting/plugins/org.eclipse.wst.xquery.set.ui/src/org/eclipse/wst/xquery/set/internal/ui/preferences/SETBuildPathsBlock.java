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

import org.eclipse.wst.xquery.set.ui.SETUIPlugin;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class SETBuildPathsBlock extends BuildpathsBlock {

    public SETBuildPathsBlock(IRunnableContext runnableContext, IStatusChangeListener context, int pageToShow,
            boolean useNewPage, IWorkbenchPreferenceContainer pageContainer) {
        super(runnableContext, context, pageToShow, useNewPage, pageContainer);
    }

    protected IPreferenceStore getPreferenceStore() {
        return SETUIPlugin.getDefault().getPreferenceStore();
    }

    protected boolean supportZips() {
        return false;
    }
}
