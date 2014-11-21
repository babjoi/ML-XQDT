/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.preferences;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wst.xquery.sse.ui.XQDTSSEUIPlugin;
import org.eclipse.wst.xquery.sse.ui.internal.restrictions.XQDTColorHelper;

/**
 * Class that reads the contributions to the <code>colorSchemes</code> extension point of the XQuery
 * editor.
 */
public class XQDTColorSchemeRegistry {

    // Constants

    protected static final String EXTENSION_POINT_ID = "colorSchemes"; //$NON-NLS-1$
    protected static final String TAG_SCHEME = "scheme"; //$NON-NLS-1$
    protected static final String ATTR_NAME = "name"; //$NON-NLS-1$
    protected static final String TAG_STYLE = "style"; //$NON-NLS-1$
    protected static final String ATTR_ID = "id"; //$NON-NLS-1$
    protected static final String ATTR_COLOR = "color"; //$NON-NLS-1$
    protected static final String ATTR_BOLD = "bold"; //$NON-NLS-1$
    protected static final String ATTR_ITALIC = "italic"; //$NON-NLS-1$
    protected static final String ATTR_UNDERLINE = "underline"; //$NON-NLS-1$
    protected static final String ATTR_STRIKE_THROUGH = "strike-through"; //$NON-NLS-1$

    private static Map<String, Map<String, String>> fColorSchemes;

    // implementation

    private class Style {
        String colorKey;
        String styleString;

        public Style(String key, String style) {
            colorKey = key;
            styleString = style;
        }
    }

    /**
     * Read from plug-in registry and parse it.
     */
    protected void readRegistry() {
        fColorSchemes = new TreeMap<String, Map<String, String>>();

        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint point = pluginRegistry.getExtensionPoint(XQDTSSEUIPlugin.getDefault().getBundle()
                .getSymbolicName(), EXTENSION_POINT_ID);
        if (point != null) {
            IConfigurationElement[] elements = point.getConfigurationElements();
            for (int i = 0; i < elements.length; i++) {
                readElement(elements[i]);
            }
        }
    }

    /**
     * Parse one color scheme.
     */
    private void readElement(IConfigurationElement element) {
        if (TAG_SCHEME.equals(element.getName())) {
            String schemeName = element.getAttribute(ATTR_NAME);

            Map<String, String> colorScheme = new HashMap<String, String>();

            IConfigurationElement[] styleElements = element.getChildren(TAG_STYLE);
            for (IConfigurationElement styleElement : styleElements) {
                Style style = processStyle(styleElement);
                colorScheme.put(style.colorKey, style.styleString);
            }

            fColorSchemes.put(schemeName, colorScheme);
        }
    }

    /**
     * Parse one style.
     */
    private Style processStyle(IConfigurationElement style) {
        String key = style.getAttribute(ATTR_ID);
        boolean bold = Boolean.parseBoolean(style.getAttribute(ATTR_BOLD));
        boolean italic = Boolean.parseBoolean(style.getAttribute(ATTR_ITALIC));
        boolean underline = Boolean.parseBoolean(style.getAttribute(ATTR_UNDERLINE));
        boolean strikeThrough = Boolean.parseBoolean(style.getAttribute(ATTR_STRIKE_THROUGH));
        RGB color = new RGB(0, 0, 0);
        String rgbStr = style.getAttribute(ATTR_COLOR);
        if (rgbStr != null) {
            color = XQDTColorHelper.toRGB(rgbStr);
            if (color == null) {
                rgbStr = "#FFFFFF";
            }
        }

        return new Style(key, rgbStr + "|" + "#FFFFFF" + "|" + bold + "|" + italic + "|" + underline + "|"
                + strikeThrough);
    }

    /**
     * Retrieves the color scheme map, reading if it necessary.
     */
    public static Map<String, Map<String, String>> getColorSchemes() {
        if (fColorSchemes == null) {
            new XQDTColorSchemeRegistry().readRegistry();
        }
        return fColorSchemes;
    }
}
