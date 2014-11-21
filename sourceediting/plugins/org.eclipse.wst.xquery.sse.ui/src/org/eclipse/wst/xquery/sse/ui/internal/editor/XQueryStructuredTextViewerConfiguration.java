/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lionel Villard (IBM) - initial API and implementation
 *     Gabriel Petrovay (IBM) - extended to XQuery comment and string partitions
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.editor;

import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.MultiPassContentFormatter;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration;
import org.eclipse.wst.sse.ui.internal.contentassist.StructuredContentAssistant;
import org.eclipse.wst.sse.ui.internal.format.StructuredFormattingStrategy;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.xquery.sse.core.text.IXQDTPartitions;
import org.eclipse.wst.xquery.sse.ui.internal.formatter.XQDTFormatter;
import org.eclipse.wst.xquery.sse.ui.internal.style.XQDTLineStyleProvider;

/**
 * XQuery configuration for the source view in the XQuery editor.
 */
public class XQueryStructuredTextViewerConfiguration extends StructuredTextViewerConfiguration {

    // State

    /** Cached XQuery line style provider */
    final private LineStyleProvider[] lineStyleProvider;

    /** List of configured content types */
    final private String[] contentTypes;

    // Constructors

    public XQueryStructuredTextViewerConfiguration() {
        lineStyleProvider = new LineStyleProvider[1];
        lineStyleProvider[0] = new XQDTLineStyleProvider();

        contentTypes = new String[3];
        contentTypes[0] = IXQDTPartitions.XQUERY_DEFAULT;
        contentTypes[1] = IXQDTPartitions.XQUERY_COMMENT;
        contentTypes[2] = IXQDTPartitions.XQUERY_STRING;
    }

    // Overrides

    @Override
    public LineStyleProvider[] getLineStyleProviders(ISourceViewer sourceViewer, String partitionType) {
        return lineStyleProvider;
    }

    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return contentTypes;
    }

    @Override
    public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
        IContentFormatter formatter = super.getContentFormatter(sourceViewer);

        if (formatter instanceof MultiPassContentFormatter) {
            ((MultiPassContentFormatter)formatter).setMasterStrategy(new StructuredFormattingStrategy(
                    new XQDTFormatter()));
        }

        return formatter;
    }

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        StructuredContentAssistant assistant = (StructuredContentAssistant)super.getContentAssistant(sourceViewer);
        assistant.enableAutoInsert(true);
        return assistant;
    }

}
