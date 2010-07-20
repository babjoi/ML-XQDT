package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class ZorbaTemplateProposalComputer extends ScriptCompletionProposalComputer {

    protected TemplateCompletionProcessor createTemplateProposalComputer(ScriptContentAssistInvocationContext context) {
        return new ZorbaTemplateCompletionProcessor(context);
    }

    protected ScriptCompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context) {
        return null;
    }

}
