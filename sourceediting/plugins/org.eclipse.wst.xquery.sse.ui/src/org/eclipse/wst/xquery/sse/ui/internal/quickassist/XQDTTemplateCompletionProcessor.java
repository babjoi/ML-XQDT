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
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xquery.sse.core.internal.model.XQueryStructuredModel;
import org.eclipse.wst.xquery.sse.core.internal.model.ast.IASTNode;
import org.eclipse.wst.xquery.sse.ui.XQDTPlugin;
import org.eclipse.wst.xquery.sse.ui.internal.preferences.XQDTTemplatePreferencePage;

/**
 * Propose code completion based on templates.
 * 
 * Templates include:
 * <ul>
 * <li>User-defined templates. See {@link XQDTTemplatePreferencePage}
 * <li>Builtins functions
 * <li>TODO: XML character template
 * </ul>
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQDTTemplateCompletionProcessor extends TemplateCompletionProcessor {

	// State

	/** Context type ID */
	protected String contextTypeID;

	// Overrides

	@Override
	protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
		final IStructuredDocument document = (IStructuredDocument) viewer.getDocument();
		final IStructuredDocumentRegion sdregion = document.getRegionAtCharacterOffset(region.getOffset());

		if (region != null) {
			final XQueryStructuredModel model = (XQueryStructuredModel) StructuredModelManager.getModelManager()
					.getModelForRead(document);
			final IASTNode node = model.getASTNode(sdregion);
			model.releaseFromRead();

			if (node != null) {
				// TODO: accurate completion
			}

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
		} else {
			ids = new String[] { contextTypeId };
		}

		final TemplateStore store = getTemplateStore();
		if (store != null) {
			List<Template> templates = new ArrayList<Template>();

			for (int i = 0; i < ids.length; i++) {
				Template[] t = store.getTemplates(ids[i]);
				if (t != null) {
					for (int j = 0; j < t.length; j++)
						templates.add(t[j]);
				}
			}

			Template[] array = new Template[templates.size()];
			return templates.toArray(array);
		}

		return null;
	}

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		ITextSelection selection = (ITextSelection) viewer.getSelectionProvider().getSelection();

		// adjust offset to end of normalized selection
		if (selection.getOffset() == offset)
			offset = selection.getOffset() + selection.getLength();

		String prefix = extractPrefix(viewer, offset);
		Region region = new Region(offset - prefix.length(), prefix.length());
		TemplateContext context = createContext(viewer, region);
		if (context == null)
			return new ICompletionProposal[0];

		context.setVariable("selection", selection.getText()); // name of the selection variables {line, word}_selection //$NON-NLS-1$

		Template[] templates = getTemplates(context.getContextType().getId());

		List<ICompletionProposal> matches = new ArrayList<ICompletionProposal>();
		for (int i = 0; i < templates.length; i++) {
			Template template = templates[i];

			try {
				context.getContextType().validate(template.getPattern());
			} catch (TemplateException e) {
				continue;
			}

			matches.add(createProposal(template, context, (IRegion) region, getRelevance(template, prefix)));
		}

		Collections.sort(matches, fgProposalComparator);

		return (ICompletionProposal[]) matches.toArray(new ICompletionProposal[matches.size()]);
	}

	// Helpers

	private TemplateStore getTemplateStore() {
		return XQDTPlugin.getDefault().getTemplateStore();
	}

	private static final class ProposalComparator implements Comparator<ICompletionProposal> {
		public int compare(ICompletionProposal o1, ICompletionProposal o2) {
			return ((TemplateProposal) o2).getRelevance() - ((TemplateProposal) o1).getRelevance();
		}
	}

	private static final Comparator<ICompletionProposal> fgProposalComparator = new ProposalComparator();

}
