package org.eclipse.wst.xquery.sse.ui.internal.style;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.wst.xquery.sse.ui.XQDTSSEUIPlugin;

/**
 * Preference initializer for Syntax coloring
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    // Constructor

    public PreferenceInitializer() {
    }

    // Overrides

    @Override
    public void initializeDefaultPreferences() {
        IEclipsePreferences node = new DefaultScope().getNode(XQDTSSEUIPlugin.getDefault().getBundle()
                .getSymbolicName());

        // Style value should be in the form of Foreground RGB String |
        // Background
        // RGB String | Bold true/false | Italic true/false | Strikethrough
        // true/false | Underline true/false

        node.put(XQDTLineStyleProvider.CK_KEYWORD, "#5A5CAD|#FFFFFF|true|false|false|false");
        node.put(XQDTLineStyleProvider.CK_STRING_LITERAL, "#C8C41D|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_XML_ATTR_VALUE, "#C8C41D|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_XML_ATTR_NAME, "#AD746A|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_XML_TAG_NAME, "#AD746A|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_TYPE, "#7EA656|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_FUNCTION_NAME, "#A86A61|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_DOLLAR_EXPR, "#A86A61|#FFFFFF|false|false|false|false");
        node.put(XQDTLineStyleProvider.CK_PRAGMA, "#A86A61|#FFFFFF|false|false|false|false");

        node.put(XQDTLineStyleProvider.CK_COMMENT, "#74B081|#FFFFFF|false|true|false|false");
        node.put(XQDTLineStyleProvider.CK_XML_COMMENT, "#74B081|#FFFFFF|false|true|false|false");
    }

}
