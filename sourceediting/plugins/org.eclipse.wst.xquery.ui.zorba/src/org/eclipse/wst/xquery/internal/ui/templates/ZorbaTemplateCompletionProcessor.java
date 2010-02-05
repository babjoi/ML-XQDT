package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;

public class ZorbaTemplateCompletionProcessor extends ScriptTemplateCompletionProcessor {

    public ZorbaTemplateCompletionProcessor(ScriptContentAssistInvocationContext context) {
        super(context);
    }

    @Override
    protected String getContextTypeId() {
        return ZorbaTemplateContentType.CONTEXT_TYPE_ID;
    }

    @Override
    protected ScriptTemplateAccess getTemplateAccess() {
        return ZorbaTemplateAccess.getInstance();
    }

}