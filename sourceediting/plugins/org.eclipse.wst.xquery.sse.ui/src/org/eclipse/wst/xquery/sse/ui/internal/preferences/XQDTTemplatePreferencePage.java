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
package org.eclipse.wst.xquery.sse.ui.internal.preferences;

import org.eclipse.ui.texteditor.templates.TemplatePreferencePage;
import org.eclipse.wst.xquery.sse.ui.XQDTPlugin;

/**
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQDTTemplatePreferencePage extends TemplatePreferencePage {

	// Constructors

	public XQDTTemplatePreferencePage() {
		super();

		setTemplateStore(XQDTPlugin.getDefault().getTemplateStore());
		setContextTypeRegistry(XQDTPlugin.getDefault().getTemplateContextRegistry());
	}

	// Overrides

	@Override
	protected boolean isShowFormatterSetting() {
		return false;
	}

}
