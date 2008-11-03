/*******************************************************************************
 * Copyright (c) 2008 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.utils;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;


/**
 * <p>This ImageRegistry contains all images used in the XML Security Tools. The image registry maintains a mapping
 * between symbolic image names and SWT image objects which defer the creation of SWT image objects until they are
 * needed.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public final class XmlSecurityImageRegistry {
    /** The ImageRegistry. */
    private static ImageRegistry imageRegistry;

    /**
     * Private constructor to avoid instantiation.
     */
    private XmlSecurityImageRegistry() {
    }

    /**
     * Returns the ImageRegistry with all used images in the XML Security Tools.
     *
     * @return The complete ImageRegistry
     */
    public static synchronized ImageRegistry getImageRegistry() {
        final String pluginId = XmlSecurityPlugin.getId();
        if (imageRegistry == null) {
            imageRegistry = new ImageRegistry();
            imageRegistry.put("sig_valid_small", AbstractUIPlugin.imageDescriptorFromPlugin(pluginId,
                    "icons/sig_valid_small.gif"));
            imageRegistry.put("sig_invalid_small", AbstractUIPlugin.imageDescriptorFromPlugin(pluginId,
                    "icons/sig_invalid_small.gif"));
            imageRegistry.put("sig_unknown_small", AbstractUIPlugin.imageDescriptorFromPlugin(pluginId,
                    "icons/sig_unknown_small.gif"));
            imageRegistry.put("sig_valid_large", AbstractUIPlugin.imageDescriptorFromPlugin(pluginId,
                    "icons/sig_valid_large.gif"));
            imageRegistry.put("sig_invalid_large", AbstractUIPlugin.imageDescriptorFromPlugin(pluginId,
                    "icons/sig_invalid_large.gif"));
            imageRegistry.put("sig_unknown_large", AbstractUIPlugin.imageDescriptorFromPlugin(pluginId,
                    "icons/sig_unknown_large.gif"));
            imageRegistry.put("echo_password", AbstractUIPlugin.imageDescriptorFromPlugin(pluginId,
                    "icons/but_echo_password.gif"));
        }
        return imageRegistry;
    }
}
