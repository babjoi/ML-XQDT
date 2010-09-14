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
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xquery.sse.core.internal.model.XQueryStructuredModel;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.core.internal.sdregions.XQueryStructuredDocumentRegion;
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

    // State

    /** Context type ID */
    protected String contextTypeID;

    // Methods

    /**
     * Gets the context the given node lives in
     */
    protected TemplateContextType getContextType(IASTNode node) {
        if (node != null) {
            final IASTNode prev = node.getPreviousASTNodeSibling();
            if (prev != null) {
                switch (prev.getType()) {
                case IASTNode.VARDECL:
                case IASTNode.FUNCTIONDECL:
                    return XQDTTemplateContexTypeIDs.ALL_BUT_PROLOG1_TCT;
                }
            }
        }

        // Coudn't figure out context => could be in any
        return XQDTTemplateContexTypeIDs.ALL_TCT;
    }

    // Overrides

    @Override
    protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
        final IStructuredDocument document = (IStructuredDocument)viewer.getDocument();
        final XQueryStructuredDocumentRegion sdregion = (XQueryStructuredDocumentRegion)document
                .getRegionAtCharacterOffset(region.getOffset());

        if (sdregion != null) {

            final XQueryStructuredModel model = (XQueryStructuredModel)StructuredModelManager.getModelManager()
                    .getModelForRead(document);

            final IASTNode node = model.getASTNode(sdregion);

            TemplateContextType tct = getContextType(node);

            model.releaseFromRead();

            return tct;
        }

        // Coudn't figure out context => allow all
        return XQDTTemplateContexTypeIDs.ALL_TCT;
    }

    @Override
    protected Image getImage(Template template) {
        return null;
    }

    @Override
    protected Template[] getTemplates(String contextTypeId) {
        String[] ids;

        // Is this a logical context type ID?
        if (contextTypeId == XQDTTemplateContexTypeIDs.PROLOG12) {
            ids = new String[] { XQDTTemplateContexTypeIDs.PROLOG1, XQDTTemplateContexTypeIDs.PROLOG2 };
        } else if (contextTypeId == XQDTTemplateContexTypeIDs.ALL) {
            ids = new String[] { XQDTTemplateContexTypeIDs.PROLOG1, XQDTTemplateContexTypeIDs.PROLOG2,
                    XQDTTemplateContexTypeIDs.EXPR };
        } else if (contextTypeId == XQDTTemplateContexTypeIDs.ALL_BUT_PROLOG1) {
            ids = new String[] { XQDTTemplateContexTypeIDs.PROLOG2, XQDTTemplateContexTypeIDs.EXPR };
        } else {
            ids = new String[] { contextTypeId };
        }

        final TemplateStore store = getTemplateStore();
        if (store != null) {
            List<Template> templates = new ArrayList<Template>();

            for (int i = 0; i < ids.length; i++) {
                Template[] t = store.getTemplates(ids[i]);
                if (t != null) {
                    for (int j = 0; j < t.length; j++) {
                        templates.add(t[j]);
                    }
                }
            }

            Template[] array = new Template[templates.size()];
            return templates.toArray(array);
        }

        return null;
    }

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

        List<ICompletionProposal> matches = new ArrayList<ICompletionProposal>();
        for (int i = 0; i < templates.length; i++) {
            Template template = templates[i];

            final int relevance = getRelevance(template, prefix);
            if (relevance > 0) {
                matches.add(createProposal(template, context, (IRegion)region, relevance));
            }
        }

        Collections.sort(matches, fgProposalComparator);

        return matches.toArray(new ICompletionProposal[matches.size()]);
    }

    // Helpers

    private TemplateStore getTemplateStore() {
        return XQDTSSEUIPlugin.getDefault().getTemplateStore();
    }

    private static final class ProposalComparator implements Comparator<ICompletionProposal> {
        public int compare(ICompletionProposal o1, ICompletionProposal o2) {
            return ((TemplateProposal)o2).getRelevance() - ((TemplateProposal)o1).getRelevance();
        }
    }

    private static final Comparator<ICompletionProposal> fgProposalComparator = new ProposalComparator();

}
