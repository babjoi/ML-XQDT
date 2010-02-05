package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class ZorbaTemplateAccess extends ScriptTemplateAccess {

    private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.wst.xquery.templates";
    private static ZorbaTemplateAccess fInstance;

    public static ScriptTemplateAccess getInstance() {
        if (fInstance == null) {
            fInstance = new ZorbaTemplateAccess();
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
        return new String[] { ZorbaTemplateContentType.CONTEXT_TYPE_ID };
    }
}
