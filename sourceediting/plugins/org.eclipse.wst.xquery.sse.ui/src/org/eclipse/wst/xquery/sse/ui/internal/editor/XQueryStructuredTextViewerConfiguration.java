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
package org.eclipse.wst.xquery.sse.ui.internal.editor;

import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.MultiPassContentFormatter;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration;
import org.eclipse.wst.sse.ui.internal.format.StructuredFormattingStrategy;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.xquery.sse.core.internal.document.XQueryDocumentPartitioner;
import org.eclipse.wst.xquery.sse.ui.internal.quickassist.XQDTTemplateCompletionProcessor;
import org.eclipse.wst.xquery.sse.ui.internal.quickassist.XQDTVariableContentAssistProcessor;
import org.eclipse.wst.xquery.sse.ui.internal.style.XQueryLineStyleProvider;

/**
 * XQuery configuration for the source view in the XQuery editor
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
@SuppressWarnings("restriction")
public class XQueryStructuredTextViewerConfiguration extends
		StructuredTextViewerConfiguration {

	// State

	/** Cached XQuery line style provider */
	final private LineStyleProvider[] lineStyleProvider;

	/** Cached XQuery content assist processors */
	final private IContentAssistProcessor[] contentAssistProcessor;

	/** List of configured content types */
	final private String[] contentTypes;

	// Constructors

	public XQueryStructuredTextViewerConfiguration() {
		lineStyleProvider = new LineStyleProvider[1];
		lineStyleProvider[0] = new XQueryLineStyleProvider();

		contentAssistProcessor = new IContentAssistProcessor[2];
		contentAssistProcessor[0] = new XQDTVariableContentAssistProcessor();
		contentAssistProcessor[1] = new XQDTTemplateCompletionProcessor();

		contentTypes = new String[1];
		contentTypes[0] = XQueryDocumentPartitioner.DEFAULT_XQUERY_PARTITION;
	}

	// Overrides

	@Override
	public LineStyleProvider[] getLineStyleProviders(
			ISourceViewer sourceViewer, String partitionType) {
		return lineStyleProvider;
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return contentTypes;
	}

	@Override
	protected IContentAssistProcessor[] getContentAssistProcessors(
			ISourceViewer sourceViewer, String partitionType) {
		return contentAssistProcessor;
	}

	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		IContentFormatter formatter = super.getContentFormatter(sourceViewer);

		if (formatter instanceof MultiPassContentFormatter) {
			((MultiPassContentFormatter) formatter)
					.setMasterStrategy(new StructuredFormattingStrategy(
							new XQueryFormatter()));
		}

		return formatter;
	}

}
