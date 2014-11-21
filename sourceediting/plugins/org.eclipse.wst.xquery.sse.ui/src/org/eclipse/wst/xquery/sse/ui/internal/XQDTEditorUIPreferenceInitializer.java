/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Lionel Vilard (IBM) - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.wst.xquery.sse.ui.XQDTSSEUIPlugin;
import org.eclipse.wst.xquery.sse.ui.internal.style.XQDTLineStyleProvider;

/**
 * Preference initializer for the XQuery editor
 */
public class XQDTEditorUIPreferenceInitializer extends AbstractPreferenceInitializer {

    // Constants

    public static final String DEFAULT_TEXT_STYLE = "#000000|#FFFFFF|false|false|false|false";

    // Constructors

    public XQDTEditorUIPreferenceInitializer() {
    }

    // Implementation

    public void initializeDefaultPreferences() {
        IEclipsePreferences node = new DefaultScope().getNode(XQDTSSEUIPlugin.getDefault().getBundle()
                .getSymbolicName());

        // Style value should be in the following form:
        // Foreground RGB | Background RGB | Bold boolean | Italic boolean | Underline boolean | Strike-through boolean
        node.put(XQDTLineStyleProvider.CK_COMMENT, "#74B081|#FFFFFF|false|true|false|false");
        node.put(XQDTLineStyleProvider.CK_DOLLAR_EXPR, "#A86A61|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_FUNCTION_NAME, "#A86A61|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_KEYWORD, "#5A5CAD|#FFFFFF|true|false|false|false");
        node.put(XQDTLineStyleProvider.CK_OPERATOR, DEFAULT_TEXT_STYLE);
        node.put(XQDTLineStyleProvider.CK_PRAGMA, "#A86A61|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_STRING_LITERAL, "#C8C41D|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_TYPE, "#7EA656|#FFFFFF|false|false|false|false");
        // TODO: the following style defaults should come from the XML editor
        node.put(XQDTLineStyleProvider.CK_XML_ATTR_VALUE, "#C8C41D|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_XML_ATTR_NAME, "#AD746A|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_XML_TAG_NAME, "#AD746A|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_XML_COMMENT, "#74B081|#FFFFFF|false|true|false|false");
    }

}
