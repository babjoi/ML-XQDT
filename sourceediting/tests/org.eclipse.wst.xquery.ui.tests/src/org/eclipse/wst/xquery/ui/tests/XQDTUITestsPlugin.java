/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lionel Villard (IBM) - initial API and implementation 
 *******************************************************************************/
package org.eclipse.wst.xquery.ui.tests;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class XQDTUITestsPlugin extends Plugin {

	// Constants

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.wst.xquery.ui.tests";
	// The shared instance
	private static XQDTUITestsPlugin plugin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
	public static XQDTUITestsPlugin getDefault() {
		return plugin;
	}

}
