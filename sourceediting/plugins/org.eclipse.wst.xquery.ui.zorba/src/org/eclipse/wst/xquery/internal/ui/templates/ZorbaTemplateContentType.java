package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.xquery.internal.launching.zorba.ZorbaInstallType;
import org.eclipse.wst.xquery.ui.templates.XQueryTemplateVariables;

public class ZorbaTemplateContentType extends ScriptTemplateContextType {

    public static final String CONTEXT_TYPE_ID = "org.eclipse.wst.xquery.ui.templates.ZorbaTemplateContentType";

    public ZorbaTemplateContentType() {
    }

    public ZorbaTemplateContentType(String id) {
        super(id);
    }

    public ZorbaTemplateContentType(String id, String name) {
        super(id, name);
    }

    @Override
    public ScriptTemplateContext createContext(IDocument document, int completionPosition, int length,
            ISourceModule sourceModule) {
        if (sourceModule == null) {
            return null;
        }
        IInterpreterInstall install = null;
        try {
            install = ScriptRuntime.getInterpreterInstall(sourceModule.getScriptProject());
        } catch (CoreException e) {
        }
        if (install == null) {
            return null;
        }

        if (install.getInterpreterInstallType().getId().equals(ZorbaInstallType.INSTALL_TYPE_ID)) {
            return new ZorbaTemplateContext(this, document, completionPosition, length, sourceModule);
        }

        return null;
    }

    @Override
    protected void addGlobalResolvers() {
        super.addGlobalResolvers();

        addResolver(new ZorbaTemplateVariables.CollectionProperties());
        addResolver(new ZorbaTemplateVariables.IndexProperties());
        addResolver(new XQueryTemplateVariables.OrderingModes());
    }
}
