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
package org.eclipse.wst.xquery.sse.core.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * Placeholder for messages
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQueryMessages extends NLS {
	// Static

	private static final String BUNDLE_NAME = "org.eclipse.wst.xquery.sse.core.internal.XQueryPluginResources";//$NON-NLS-1$

	private static ResourceBundle fResourceBundle;

	public static String errorXQST0070_MD_UI_;
	public static String errorXQST0088_UI_;
	public static String errorXQST0088_VR_UI_;


	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, XQueryMessages.class);
	}

	// Constructors

	private XQueryMessages() {
		// cannot create new instance
	}

	// Methods

	public static ResourceBundle getResourceBundle() {
		try {
			if (fResourceBundle == null) {
				fResourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
			}
		} catch (MissingResourceException x) {
			fResourceBundle = null;
		}
		return fResourceBundle;
	}
}
