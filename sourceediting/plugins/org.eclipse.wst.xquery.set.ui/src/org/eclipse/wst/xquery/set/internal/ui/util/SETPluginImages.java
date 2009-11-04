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
package org.eclipse.wst.xquery.set.internal.ui.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.wst.xquery.set.ui.SETUIPlugin;

public class SETPluginImages {

    private static URL fgIconBaseURL = null;

    // Determine display depth. If depth > 4 then we use high color images. Otherwise low color
    // images are used
    static {
        fgIconBaseURL = SETUIPlugin.getDefault().getBundle().getEntry("/icons/"); //$NON-NLS-1$
    }

    private static final String T_WIZBAN = "wizban"; //$NON-NLS-1$

    public static final ImageDescriptor DESC_WIZBAN_NEW_MODULE = create(T_WIZBAN, "newsmod_wiz.png"); //$NON-NLS-1$;
    public static final ImageDescriptor DESC_WIZBAN_NEW_PROJECT = create(T_WIZBAN, "newsprj_wiz.png"); //$NON-NLS-1$;
    public static final ImageDescriptor DESC_WIZBAN_IMP_MODULE = create(T_WIZBAN, "impsmod_wiz.png"); //$NON-NLS-1$;
    public static final ImageDescriptor DESC_WIZBAN_IMP_PROJECT = create(T_WIZBAN, "impsprj_wiz.png"); //$NON-NLS-1$;
    public static final ImageDescriptor DESC_WIZBAN_DEPLOY_PROJECT = create(T_WIZBAN, "depsprj_wiz.png"); //$NON-NLS-1$;

    private static ImageDescriptor create(String prefix, String name) {
        try {
            return ImageDescriptor.createFromURL(makeIconFileURL(prefix, name));
        } catch (MalformedURLException e) {
            return ImageDescriptor.getMissingImageDescriptor();
        }
    }

    private static URL makeIconFileURL(String prefix, String name) throws MalformedURLException {
        if (fgIconBaseURL == null) {
            throw new MalformedURLException();
        }

        StringBuffer buffer = new StringBuffer(prefix);
        buffer.append('/');
        buffer.append(name);
        return new URL(fgIconBaseURL, buffer.toString());
    }

}
