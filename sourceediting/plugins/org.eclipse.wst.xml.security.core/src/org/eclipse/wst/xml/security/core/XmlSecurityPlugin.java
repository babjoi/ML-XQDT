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
package org.eclipse.wst.xml.security.core;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

/**
 * <p>The <b>XML Security Tools</b> allows users to experiment with the complex W3C
 * recommendations on signatures and encryption with the Extensible Markup Language and to gather
 * information about the theoretical background of these recommendations.</p>
 *
 * <p>Arbitrary XML documents can be canonicalized, signed, verified as well as encrypted and decrypted inside most of
 * the users' famous Eclipse XML editor plug-in. The plug-ins main intention is to let users experiment with all
 * possible features of XML security in an easy and interesting way and to provide a tool to learn all about the
 * extensive W3C recommendations.</p>
 *
 * <p>This is the main plug-in class for the <b>XML Security Tools</b> to be used in the workbench. This class does
 * the initialization work for the <b>Apache XML Security</b> library and the resource bundle initialization. Starts and
 * stops the complete plug-in.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class XmlSecurityPlugin extends AbstractUIPlugin {
    /** Storage for the user preferences of the XML Security Tools. */
    private ScopedPreferenceStore preferenceStore;
    /** The main instance of the XML Security Tools. */
    private static XmlSecurityPlugin plugin;

    /**
     * The main constructor of the XML Security Tools.
     */
    public XmlSecurityPlugin() {
    }

    /**
     * This method is called upon XML Security Tools activation. It initializes the Apache XML Security library first
     * before any other action occurs.
     *
     * @param context The bundle context
     * @throws Exception to indicate any exceptional condition
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        org.apache.xml.security.Init.init();
        plugin = this;
    }

    /**
     * This method is called when the XML Security Tools is stopped.
     *
     * @param context The bundle context
     * @throws Exception to indicate any exceptional condition
     */
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        preferenceStore = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance of the XML Security Tools.
     *
     * @return The XML Security Tools instance
     */
    public static XmlSecurityPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     *
     * @param path The path
     * @return The image descriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(getId(), path);
    }

    /**
     * Gets the XML Security Tools id from the OSGi-Bundle.
     *
     * @return The Plug-In id
     */
    public static String getId() {
        return getDefault().getBundle().getSymbolicName();
    }

    /**
     * <p>Returns exactly one preference store for the XML Security Tools. This preference store is always lazily
     * created.<br>
     * This preference store is used to hold persistent settings for this plug-in in the context of a workbench.</p>
     *
     * <p>If an error occurs reading the preference store, an empty preference store is quietly created, initialized
     * with defaults, and returned.</p>
     *
     * @return The preference store
     */
    public IPreferenceStore getPreferenceStore() {
        if (preferenceStore == null) {
            preferenceStore = new ScopedPreferenceStore(new InstanceScope(), getBundle().getSymbolicName());
        }
        return preferenceStore;
    }

    @Override
    protected void initializeImageRegistry(ImageRegistry registry) {
        registry.put("sig_valid_small", getImageDescriptor("icons/sig_valid_small.gif"));
        registry.put("sig_invalid_small", getImageDescriptor("icons/sig_invalid_small.gif"));
        registry.put("sig_unknown_small", getImageDescriptor("icons/sig_unknown_small.gif"));
        registry.put("sig_valid_large", getImageDescriptor("icons/sig_valid_large.gif"));
        registry.put("sig_invalid_large", getImageDescriptor("icons/sig_invalid_large.gif"));
        registry.put("sig_unknown_large", getImageDescriptor("icons/sig_unknown_large.gif"));
        registry.put("echo_password", getImageDescriptor("icons/but_echo_password.gif"));
    }

    /**
     * Returns the active workbench window.
     *
     * @return The active workbench window
     */
    public static IWorkbenchWindow getActiveWorkbenchWindow() {
        return getDefault().getWorkbench().getActiveWorkbenchWindow();
    }
}
