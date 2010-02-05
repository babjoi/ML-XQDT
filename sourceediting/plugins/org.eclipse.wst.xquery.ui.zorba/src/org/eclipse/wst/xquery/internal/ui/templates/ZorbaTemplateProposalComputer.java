package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class ZorbaTemplateProposalComputer extends ScriptCompletionProposalComputer {

    @Override
    protected TemplateCompletionProcessor createTemplateProposalComputer(ScriptContentAssistInvocationContext context) {
        return new ZorbaTemplateCompletionProcessor(context);
    }

}
