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
package org.eclipse.wst.xquery.set.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.eclipse.wst.xquery.core.IXQDTUriResolver;
import org.eclipse.wst.xquery.set.internal.core.SETResolver;

/**
 * The activator class controls the plug-in life cycle
 */
public class SETCorePlugin extends Plugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.wst.xquery.set.core";

    // The shared instance
    private static SETCorePlugin plugin;

    private IXQDTUriResolver fModuleResolver = null;

    /**
     * The constructor
     */
    public SETCorePlugin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
    public static SETCorePlugin getDefault() {
        return plugin;
    }

    public IXQDTUriResolver getModuleResolver() {
        if (fModuleResolver == null) {
            fModuleResolver = new SETResolver();
        }
        return fModuleResolver;
    }

}
