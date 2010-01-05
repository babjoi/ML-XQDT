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

import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.xquery.core.codeassist.IXQDTCompletionConstants.CompletionPrefixType;
import org.eclipse.wst.xquery.core.text.XQDTWordDetector;

public abstract class XQDTPrefixedTemplateCompletionProcessor extends ScriptTemplateCompletionProcessor {

    protected String fPrefixPrefix;
    protected CompletionPrefixType fPrefixType;

    private Image fImage;

    public XQDTPrefixedTemplateCompletionProcessor(ScriptContentAssistInvocationContext context) {
        super(context);
    }

    protected abstract ImageDescriptor getImageDescriptor();

    @Override
    protected Image getImage(Template template) {
        ImageDescriptor descriptor = getImageDescriptor();
        if (descriptor == null) {
            return null;
        }

        if (fImage == null) {
            fImage = getImageDescriptor().createImage();
        }

        return fImage;
    }

    @Override
    protected boolean isMatchingTemplate(Template template, String prefix, TemplateContext context) {
        if (!template.getName().startsWith(prefix)) {
            return false;
        }

        if (template.matches(prefix, context.getContextType().getId())) {
            return true;
        }

        return false;
    }

    @Override
    protected String extractPrefix(ITextViewer viewer, int offset) {
        int i = offset;
        IDocument document = viewer.getDocument();
        char[] content = document.get().toCharArray();
        if (i > content.length) {
            return "";
        }

        XQDTWordDetector wd = new XQDTWordDetector();

        String word = extractWord(content, offset, wd, true);
        if (fPrefixType == CompletionPrefixType.COLON) {
            int prefixLen = word.length();
            String namespace = extractWord(content, offset - (prefixLen + 1), wd, false);
            fPrefixPrefix = namespace;
        }

        return word;
    }

    private String extractWord(char[] content, int offset, XQDTWordDetector wd, boolean checkPrefixPrefix) {
        int i = offset;
        char ch = 0;
        while (i > 0) {
            ch = content[i - 1];

            if (!wd.isWordPart(ch)) {
                break;
            }
            i--;
        }
        if (checkPrefixPrefix) {
            if (ch == ':') {
                fPrefixType = CompletionPrefixType.COLON;
            } else if (ch == '$') {
                fPrefixType = CompletionPrefixType.DOLLAR;
            } else if (ch == '&') {
                fPrefixType = CompletionPrefixType.AMPERSAND;
            } else {
                fPrefixType = CompletionPrefixType.NORMAL;
            }
        }
        return new String(content, i, offset - i);
    }

}
