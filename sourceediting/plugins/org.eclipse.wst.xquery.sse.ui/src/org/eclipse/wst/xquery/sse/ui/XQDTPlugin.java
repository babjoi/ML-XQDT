/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui;

import java.io.IOException;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.wst.common.ui.internal.UIPlugin;
import org.eclipse.wst.sse.ui.internal.Logger;
import org.eclipse.wst.xquery.sse.ui.internal.quickassist.XQDTTemplateContexTypeIDs;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class XQDTPlugin extends UIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.wst.xquery.ui.sse";

	// The shared instance
	private static XQDTPlugin plugin;

	// Template store
	private TemplateStore templateStore;

	// Context Type registry
	private ContextTypeRegistry contextTypeRegistry;

	/**
	 * The constructor
	 */
	public XQDTPlugin() {
	}

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
	public static XQDTPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the template store for the xml editor templates.
	 * 
	 * @return the template store for the xml editor templates
	 */
	public TemplateStore getTemplateStore() {
		if (templateStore == null) {
			templateStore = new ContributionTemplateStore(getTemplateContextRegistry(), getPreferenceStore(),
					"org.eclipse.wst.xquery.templates");

			try {
				templateStore.load();
			} catch (IOException e) {
				Logger.logException(e);
			}
		}
		return templateStore;
	}

	/**
	 * Returns the template context type registry for the XQDT plugin.
	 * 
	 * @return the template context type registry for the XQDT plugin
	 */
	public ContextTypeRegistry getTemplateContextRegistry() {
		if (contextTypeRegistry == null) {
			ContributionContextTypeRegistry registry = new ContributionContextTypeRegistry(
					"org.eclipse.wst.xquery.sse.ui.contextTypeRegistry");

			// registry.addContextType(XQDTTemplateContexTypeIDs.PROLOG1);

			contextTypeRegistry = registry;
		}

		return contextTypeRegistry;
	}
}
