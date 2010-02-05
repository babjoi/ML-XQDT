package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.dltk.core.IPreferencesLookupDelegate;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.templates.IScriptTemplateIndenter;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.TabExpandScriptTemplateIndenter;
import org.eclipse.dltk.ui.text.util.TabStyle;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class ZorbaTemplateContext extends ScriptTemplateContext {

    protected ZorbaTemplateContext(TemplateContextType type, IDocument document, int completionOffset,
            int completionLength, ISourceModule sourceModule) {
        super(type, document, completionOffset, completionLength, sourceModule);
    }

    protected IScriptTemplateIndenter getIndenter() {
        IPreferencesLookupDelegate prefs = getPreferences();
        if (TabStyle.SPACES == TabStyle.forName(prefs.getString(XQDTUIPlugin.PLUGIN_ID,
                CodeFormatterConstants.FORMATTER_TAB_CHAR))) {
            return new TabExpandScriptTemplateIndenter(prefs.getInt(XQDTUIPlugin.PLUGIN_ID,
                    CodeFormatterConstants.FORMATTER_TAB_SIZE));
        }
        return super.getIndenter();
    }

    public String[] getCollectionProperties() {
        return new String[] { "mutable", "const", "append-only", "queue", "ordered", "unordered" };
    }

    public String[] getNodeModifiers() {
        return new String[] { "mutable", "read-only" };
    }

    public String[] getIndexProperties() {
        return new String[] { "unique", "non unique", "value range", "value equality", "automatically maintained",
                "manually maintained" };
    }

    public String[] getOrderingModes() {
        return new String[] { "ordered", "unordered" };
    }

}
