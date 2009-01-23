/*******************************************************************************
 * Copyright (c) 2009 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.tests;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;

public class XMLSecurityToolsTestPlugin extends Plugin {
    /** The shared plug-in instance. */
    private static XMLSecurityToolsTestPlugin plugin;

    /**
     * The constructor.
     */
    public XMLSecurityToolsTestPlugin() {
        super();

        plugin = this;
    }

    /**
     * Returns the shared instance.
     */
    public static XMLSecurityToolsTestPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     */
    public static String getResourceString(String key) {
        return key;
    }

    /**
     * Returns the plugin's resource bundle,
     */
    public ResourceBundle getResourceBundle() {
        return null;
    }

    public static URL getInstallLocation() {
        URL installLocation = Platform.getBundle("org.eclipse.wst.xml.security.core.tests").getEntry("/");
        URL resolvedLocation = null;
        try {
            resolvedLocation = FileLocator.resolve(installLocation);
        } catch (IOException e) {
            // impossible
            throw new Error(e);
        }
        return resolvedLocation;
    }

    public static String getTestFileLocation(String filepath) {
        URL installURL = getInstallLocation();
        String path = installURL.getPath();
        String location = path + filepath;

        return location;
    }
}
