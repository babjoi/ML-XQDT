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
package org.eclipse.wst.xquery.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.eclipse.wst.xquery.internal.ui.text.XQDTTextTools;

/**
 * The activator class controls the plug-in life cycle
 */
public class XQDTUIPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.wst.xquery.ui";

    // The shared instance
    private static XQDTUIPlugin plugin;

    private XQDTTextTools fTextTools;

    // The identifiers for the preferences
    public static final String NAMESPACE_PREFERENCE = "namespaces";
    // The default value for the namespace preference
    private static final String DEFAULT_NAMESPACES = "http://www.example.com/module";

    /**
     * The constructor
     */
    public XQDTUIPlugin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        getPreferenceStore().setDefault(NAMESPACE_PREFERENCE, DEFAULT_NAMESPACES);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static XQDTUIPlugin getDefault() {
        return plugin;
    }

    public synchronized XQDTTextTools getTextTools() {
        if (fTextTools == null)
            fTextTools = new XQDTTextTools(true);
        return fTextTools;
    }

}
