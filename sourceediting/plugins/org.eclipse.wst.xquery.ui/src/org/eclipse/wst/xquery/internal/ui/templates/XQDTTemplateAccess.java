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
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTTemplateAccess extends ScriptTemplateAccess {

    private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.wst.xquery.templates";
    private static XQDTTemplateAccess fInstance;

    public static ScriptTemplateAccess getInstance() {
        if (fInstance == null) {
            fInstance = new XQDTTemplateAccess();
        }
        return fInstance;
    }

    protected String getContextTypeId() {
        return null;
    }

    protected String getCustomTemplatesKey() {
        return CUSTOM_TEMPLATES_KEY;
    }

    protected IPreferenceStore getPreferenceStore() {
        return XQDTUIPlugin.getDefault().getPreferenceStore();
    }

    @Override
    protected ContextTypeRegistry createContextTypeRegistry() {
        ContributionContextTypeRegistry registry = new ContributionContextTypeRegistry();
        String[] contentTypes = getContextTypeIds();
        if (contentTypes != null) {
            for (String contentType : contentTypes) {
                registry.addContextType(contentType);
            }
        }
        return registry;
    };

    protected String[] getContextTypeIds() {
        return new String[] { XQueryTemplateContentType.CONTEXT_TYPE_ID,
                XQueryUpdateTemplateContentType.CONTEXT_TYPE_ID, XQueryScriptingTemplateContentType.CONTEXT_TYPE_ID
        // TODO: what to do with the Full Text spec?
        // XQueryFullTextTemplateContentType.CONTEXT_TYPE_ID
        };
    }
}
