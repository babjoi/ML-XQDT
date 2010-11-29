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
package org.eclipse.wst.xquery.internal.ui.editor;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.wst.xquery.core.XQDTLanguageToolkit;
import org.eclipse.wst.xquery.internal.ui.folding.XQDTFoldingStructureProvider;
import org.eclipse.wst.xquery.internal.ui.text.IXQDTPartitions;
import org.eclipse.wst.xquery.internal.ui.text.XQDTXMLAutoInserter;
import org.eclipse.wst.xquery.internal.ui.text.rules.XQDTPartitioner;
import org.eclipse.wst.xquery.ui.IXQDTUIPluginConstants;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

@SuppressWarnings("restriction")
public class XQDTEditor extends ScriptEditor {

    public static final String EDITOR_CONTEXT = "#XQDTEditorContext";

    public static final String RULER_CONTEXT = "#XQDTRulerContext";

    private IFoldingStructureProvider fFoldingProvider = null;

    private ICharacterPairMatcher fPairMatcher = new DefaultCharacterPairMatcher("{}[]()".toCharArray(),
            IXQDTPartitions.XQDT_PARTITIONING);

    private VerifyKeyListener fXmlAutoCloseTag = new XQDTXMLAutoInserter(this);

    @Override
    protected void initializeEditor() {
        super.initializeEditor();
        setEditorContextMenuId(EDITOR_CONTEXT);
        setRulerContextMenuId(RULER_CONTEXT);
    }

    public String getEditorId() {
        return IXQDTUIPluginConstants.ID_XQDT_EDITOR;
    }

    public IDLTKLanguageToolkit getLanguageToolkit() {
        return XQDTLanguageToolkit.getDefault();
    }

    protected IPreferenceStore getScriptPreferenceStore() {
        return XQDTUIPlugin.getDefault().getPreferenceStore();
    }

    @Override
    public ScriptTextTools getTextTools() {
        return XQDTUIPlugin.getDefault().getTextTools();
    }

    // TODO: This is a temporary fix for the cummulating listening
    // partitioners in DLTKCore. This was raising
    // BadLocationException because the old partitioners were
    // storing old documents.
    private XQDTPartitioner fPartitioner;

    @Override
    protected void connectPartitioningToElement(IEditorInput input, IDocument document) {
        if (document instanceof IDocumentExtension3) {
            IDocumentExtension3 extension = (IDocumentExtension3)document;
            if (extension.getDocumentPartitioner(IXQDTPartitions.XQDT_PARTITIONING) == null) {
                XQDTUIPlugin.getDefault().getTextTools()
                        .setupDocumentPartitioner(document, IXQDTPartitions.XQDT_PARTITIONING);
                XQDTPartitioner partitioner = (XQDTPartitioner)extension
                        .getDocumentPartitioner(IXQDTPartitions.XQDT_PARTITIONING);
                partitioner.setEditor(this, getPreferenceStore());
                DLTKCore.removeElementChangedListener(fPartitioner);
                fPartitioner = partitioner;
                DLTKCore.addElementChangedListener(fPartitioner);
            }
        }
    }

    @Override
    protected FoldingActionGroup createFoldingActionGroup() {
        return new FoldingActionGroup(this, getViewer(), getPreferenceStore());
    }

    @Override
    protected IFoldingStructureProvider getFoldingStructureProvider() {
        if (fFoldingProvider == null) {
            fFoldingProvider = new XQDTFoldingStructureProvider();
        }
        return fFoldingProvider;
    }

    protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
        support.setCharacterPairMatcher(fPairMatcher);
        support.setMatchingCharacterPainterPreferenceKeys(MATCHING_BRACKETS, MATCHING_BRACKETS_COLOR);

        super.configureSourceViewerDecorationSupport(support);
    }

    @Override
    public void dispose() {
        if (fFoldingProvider != null) {
            fFoldingProvider.uninstall();
        }

        IDocument document = this.getDocumentProvider().getDocument(getEditorInput());

        if (document instanceof IDocumentExtension3) {
            IDocumentExtension3 extension = (IDocumentExtension3)document;
            IDocumentPartitioner partitioner = extension.getDocumentPartitioner(IXQDTPartitions.XQDT_PARTITIONING);
            if (partitioner != null) {
                DLTKCore.removeElementChangedListener((XQDTPartitioner)partitioner);
            }
        }

        ISourceViewer sourceViewer = getSourceViewer();
        if (sourceViewer instanceof ITextViewerExtension) {
            ((ITextViewerExtension)sourceViewer).removeVerifyKeyListener(fXmlAutoCloseTag);
        }

        super.dispose();
    }

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        ISourceViewer sourceViewer = getSourceViewer();
        if (sourceViewer instanceof ITextViewerExtension) {
            ((ITextViewerExtension)sourceViewer).prependVerifyKeyListener(fXmlAutoCloseTag);
        }
    }

    // @Override
    // protected void doSetInput(IEditorInput input) throws CoreException {
    // super.doSetInput(input);
    // IModelElement element = getInputModelElement();
    // if (element != null) {
    // notifyDoSetInput(element);
    // }
    // }

    // protected void notifyDoSetInput(IModelElement element) {
    // Object[] listeners2 = doSetInputListeners.getListeners();
    // for (int i = 0; i < listeners2.length; i++) {
    // IRubyEditorListener listener = (IRubyEditorListener) listeners2[i];
    // if (listener != null) {
    // listener.notifyDoSetInput(element);
    // }
    // }
    // }

}
