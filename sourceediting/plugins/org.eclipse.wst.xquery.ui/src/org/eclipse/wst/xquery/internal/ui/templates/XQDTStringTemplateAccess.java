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
package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTStringTemplateAccess extends ScriptTemplateAccess {

    private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.wst.xquery.stringTemplates";
    private static XQDTStringTemplateAccess fInstance;

    public static ScriptTemplateAccess getInstance() {
        if (fInstance == null) {
            return new XQDTStringTemplateAccess();
        }
        return fInstance;
    }

    protected String getContextTypeId() {
        return XQDTStringTemplateContentType.CONTEXT_TYPE_ID;
    }

    protected String getCustomTemplatesKey() {
        return CUSTOM_TEMPLATES_KEY;
    }

    protected IPreferenceStore getPreferenceStore() {
        return XQDTUIPlugin.getDefault().getPreferenceStore();
    }

}
