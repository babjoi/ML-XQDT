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
package org.eclipse.wst.xquery.internal.ui;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.PluginImagesHelper;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTImages {

    private static final PluginImagesHelper helper = new PluginImagesHelper(XQDTUIPlugin.getDefault().getBundle(),
            new Path("/icons"));
    public static final ImageDescriptor WIZBAN_NEW_XQDOC = helper.createUnManaged(PluginImagesHelper.T_WIZBAN,
            "newxqdoc_wiz.png");
    public static final ImageDescriptor DESC_ELCL_FN_PROPOSAL = helper.createUnManaged(PluginImagesHelper.T_ELCL,
            "fn_proposal.gif");

}