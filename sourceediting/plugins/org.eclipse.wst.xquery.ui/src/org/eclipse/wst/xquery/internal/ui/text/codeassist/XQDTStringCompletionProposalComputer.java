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
package org.eclipse.wst.xquery.internal.ui.text.codeassist;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class XQDTStringCompletionProposalComputer extends ScriptCompletionProposalComputer {

    protected TemplateCompletionProcessor createTemplateProposalComputer(ScriptContentAssistInvocationContext context) {
        return new XQDTStringTemplateCompletionProcessor(context);
    }

    @Override
    protected ScriptCompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context) {
        return new XQDTStringCompletionProposalCollector(context.getSourceModule());
    }

}
