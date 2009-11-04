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
package org.eclipse.wst.xquery.internal.ui.text;

import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTStringCompletionProcessor extends ScriptCompletionProcessor {

    public XQDTStringCompletionProcessor(IEditorPart editor, ContentAssistant assistant, String partition) {
        super(editor, assistant, partition);
        setCompletionProposalAutoActivationCharacters(new char[] { '&' });
    }

    protected String getNatureId() {
        return XQDTNature.NATURE_ID;
    }

    protected CompletionProposalLabelProvider getProposalLabelProvider() {
        return new CompletionProposalLabelProvider();
    }

    protected IPreferenceStore getPreferenceStore() {
        return XQDTUIPlugin.getDefault().getPreferenceStore();
    }

}
