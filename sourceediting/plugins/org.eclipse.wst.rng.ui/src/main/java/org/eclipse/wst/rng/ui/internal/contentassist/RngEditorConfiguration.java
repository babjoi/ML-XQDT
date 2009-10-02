/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.ui.internal.contentassist;

import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.wst.sse.core.text.IStructuredPartitions;
import org.eclipse.wst.xml.core.text.IXMLPartitions;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;


public class RngEditorConfiguration extends	StructuredTextViewerConfigurationXML {

	public RngEditorConfiguration() {}
	
	@Override
	protected IContentAssistProcessor[] getContentAssistProcessors(
			ISourceViewer sourceViewer, String partitionType) {
		
		if (partitionType == IStructuredPartitions.DEFAULT_PARTITION
				|| partitionType == IXMLPartitions.XML_DEFAULT) {
			return new IContentAssistProcessor[]  {new RngContentAssistProcessor()};
		} else {
			return super.getContentAssistProcessors(sourceViewer, partitionType);
		}
	}
	
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		IContentAssistant contentAssistant = super.getContentAssistant(sourceViewer);
		if (contentAssistant instanceof ContentAssistant) {
			((ContentAssistant) contentAssistant).setAutoActivationDelay(0);
		}
		return contentAssistant;
	}
}
