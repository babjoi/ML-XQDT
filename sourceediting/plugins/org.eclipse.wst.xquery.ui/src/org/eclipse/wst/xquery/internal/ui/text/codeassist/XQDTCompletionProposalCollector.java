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

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.IScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.LazyScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptOverrideCompletionProposal;
import org.eclipse.swt.graphics.Image;

public class XQDTCompletionProposalCollector extends ScriptCompletionProposalCollector {

    public XQDTCompletionProposalCollector(ISourceModule cu) {
        super(cu);
    }

    protected ScriptCompletionProposal createOverrideCompletionProposal(IScriptProject scriptProject,
            ISourceModule compilationUnit, String name, String[] paramTypes, int start, int length, String label,
            String string) {
        return new ScriptOverrideCompletionProposal(scriptProject, compilationUnit, name, paramTypes, start, length,
                label, string + "()");
    }

    protected ScriptCompletionProposal createScriptCompletionProposal(String completion, int replaceStart, int length,
            Image image, String displayString, int i) {
        return new XQDTCompletionProposal(completion, replaceStart, length, image, displayString, i);
    }

    protected ScriptCompletionProposal createScriptCompletionProposal(String completion, int replaceStart, int length,
            Image image, String displayString, int i, boolean isInDoc) {
        return new XQDTCompletionProposal(completion, replaceStart, length, image, displayString, i, isInDoc);
    }

    protected char[] getVarTrigger() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected CompletionProposalLabelProvider createLabelProvider() {
        return new XQDTCompletionProposalLabelProvider();
    }

    @Override
    protected IScriptCompletionProposal createScriptCompletionProposal(CompletionProposal proposal) {
        switch (proposal.getKind()) {
        case CompletionProposal.METHOD_REF:
            return createMethodReferenceProposal(proposal);
        }
        return super.createScriptCompletionProposal(proposal);
    }

    private IScriptCompletionProposal createMethodReferenceProposal(CompletionProposal methodProposal) {
        LazyScriptCompletionProposal proposal = new XQDTFunctionCompletionProposal(methodProposal,
                getInvocationContext());
        return proposal;
    }

}
