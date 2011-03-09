/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.quickassist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.ui.XQDTSSEUIPlugin;
import org.eclipse.wst.xquery.sse.ui.internal.preferences.XQDTTemplatePreferencePage;

/**
 * Propose code completion based on templates.
 * 
 * Templates include:
 * <ul>
 * <li>User-defined templates. See {@link XQDTTemplatePreferencePage}</li>
 * <li>Builtins functions</li>
 * <li>TODO: XML character template</li>
 * </ul>
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQDTTemplateCompletionProcessor extends TemplateCompletionProcessor {

    // Methods

    /**
     * Test is the template matches the prefix and context
     */
    protected boolean isMatching(String prefix, Template template, TemplateContext context) {
        return template.getName().startsWith(prefix) && template.matches(prefix, context.getContextType().getId());
    }

    // Overrides

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.templates.TemplateCompletionProcessor#getContextType(org.eclipse.jface
     * .text.ITextViewer, org.eclipse.jface.text.IRegion)
     */
    @Override
    protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
        return new XQDTTemplateContextType();
    }

    @Override
    protected Image getImage(Template template) {
        return null;
    }

    @Override
    protected Template[] getTemplates(String contextTypeId) {
        final TemplateStore store = getTemplateStore();
        if (store != null) {
            List<Template> templates = new ArrayList<Template>();
            Template[] t = store.getTemplates();
            if (t != null) {
                for (int j = 0; j < t.length; j++) {
                    templates.add(t[j]);
                }
            }
            Template[] array = new Template[templates.size()];
            return templates.toArray(array);
        }

        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {

        ITextSelection selection = (ITextSelection)viewer.getSelectionProvider().getSelection();

        // adjust offset to end of normalized selection
        if (selection.getOffset() == offset) {
            offset = selection.getOffset() + selection.getLength();
        }

        String prefix = extractPrefix(viewer, offset);
        Region region = new Region(offset - prefix.length(), prefix.length());
        TemplateContext context = createContext(viewer, region);
        if (context == null) {
            return new ICompletionProposal[0];
        }

        context.setVariable("selection", selection.getText()); // name of the selection variables {line, word}_selection //$NON-NLS-1$

        Template[] templates = getTemplates(context.getContextType().getId());

        List matches = new ArrayList();
        for (int i = 0; i < templates.length; i++) {
            Template template = templates[i];
            try {
                context.getContextType().validate(template.getPattern());
            } catch (TemplateException e) {
                continue;
            }
            if (isMatching(prefix, template, context)) {
                matches.add(createProposal(template, context, (IRegion)region, getRelevance(template, prefix)));
            }
        }

        Collections.sort(matches, fgProposalComparator);

        return (ICompletionProposal[])matches.toArray(new ICompletionProposal[matches.size()]);
    }

    @Override
    protected TemplateContext createContext(ITextViewer viewer, IRegion region) {
        TemplateContextType contextType = getContextType(viewer, region);
        if (contextType != null) {
            IStructuredDocument document = (IStructuredDocument)viewer.getDocument();

            // Compute position to include previous keyword
            int offset = region.getOffset();
            int length = region.getLength();

            IStructuredDocumentRegion sdregion = document.getRegionAtCharacterOffset(offset);
            if (sdregion != null && sdregion.getPrevious() != null) {
                String token = sdregion.getPrevious().getText().trim();
                if (token.equals("declare") || token.equals("import") || token.equals("module")) {
                    offset = sdregion.getPrevious().getStart();
                    length += sdregion.getPrevious().getLength();
                }
            }

            return new DocumentTemplateContext(contextType, document, offset, length);
        }
        return null;
    }

    // Helpers

    private TemplateStore getTemplateStore() {
        return XQDTSSEUIPlugin.getDefault().getTemplateStore();
    }

    @SuppressWarnings("rawtypes")
    private static final class ProposalComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((TemplateProposal)o2).getRelevance() - ((TemplateProposal)o1).getRelevance();
        }
    }

    @SuppressWarnings("rawtypes")
    private static final Comparator fgProposalComparator = new ProposalComparator();

}
