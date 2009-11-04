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

import org.eclipse.dltk.ui.editor.highlighting.ISemanticHighlighter;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xquery.internal.ui.text.rules.XQDTPartitioner;

public class XQDTTextTools extends ScriptTextTools {

    public static final boolean USE_SEMANTIC_HL = true;

    private IPartitionTokenScanner fPartitionScanner;

    public XQDTTextTools(boolean autoDisposeOnDisplayDispose) {
        super(IXQDTPartitions.XQDT_PARTITIONING, IXQDTPartitions.XQDT_LEGAL_PARTITION_TYPES,
                autoDisposeOnDisplayDispose);
        fPartitionScanner = new XQDTPartitionScanner();
    }

    @Override
    public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(IPreferenceStore preferenceStore,
            ITextEditor editor, String partitioning) {
        return new XQDTSourceViewerConfiguration(getColorManager(), preferenceStore, editor, partitioning);
    }

    @Override
    public IPartitionTokenScanner getPartitionScanner() {
        return fPartitionScanner;
    }

    @Override
    public SemanticHighlighting[] getSemanticHighlightings() {
        if (!USE_SEMANTIC_HL) {
            return super.getSemanticHighlightings();
        }
        return XQDTSemanticHighlighter.getSemanticHighlightings();
    }

    @Override
    public ISemanticHighlighter getSemanticPositionUpdater() {
        if (!USE_SEMANTIC_HL) {
            return super.getSemanticPositionUpdater();
        }

        return new XQDTSemanticHighlighter();
    }

    @Override
    public IDocumentPartitioner createDocumentPartitioner() {
        IPartitionTokenScanner scaner = getPartitionScanner();
        if (scaner == null)
            return null;

        return new XQDTPartitioner(scaner, IXQDTPartitions.XQDT_LEGAL_PARTITION_TYPES);
    }

    @Override
    public void setupDocumentPartitioner(IDocument document, String partitioning) {
        IDocumentPartitioner partitioner = createDocumentPartitioner();
        if (partitioner != null) {
            partitioner.connect(document);

            if (document instanceof IDocumentExtension3) {
                IDocumentExtension3 extension3 = (IDocumentExtension3)document;
                extension3.setDocumentPartitioner(partitioning, partitioner);
            } else {
                document.setDocumentPartitioner(partitioner);
            }
        }
    }

}
