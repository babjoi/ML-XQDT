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

import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptPresentationReconciler;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.SingleTokenScriptScanner;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.dltk.ui.text.util.AutoEditUtils;
import org.eclipse.dltk.ui.text.util.TabStyle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.texteditor.ITextEditor;

public class XQDTSourceViewerConfiguration extends ScriptSourceViewerConfiguration {

    private AbstractScriptScanner fCodeScanner;
    private AbstractScriptScanner fXmlCommentScanner;
    private AbstractScriptScanner fXmlCdataScanner;
    private AbstractScriptScanner fCommentScanner;
    private AbstractScriptScanner fStringScanner;
    private AbstractScriptScanner fXmlAttributeValueScanner;
    private AbstractScriptScanner fXmlElementContentScanner;

    public XQDTSourceViewerConfiguration(IColorManager colorManager, IPreferenceStore preferenceStore,
            ITextEditor editor, String partitioning) {
        super(colorManager, preferenceStore, editor, partitioning);
    }

    @Override
    public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
        return new IAutoEditStrategy[] { new DefaultIndentLineAutoEditStrategy() };
    }

    @Override
    public String[] getIndentPrefixes(ISourceViewer sourceViewer, String contentType) {
        if (fPreferenceStore == null) {
            return super.getIndentPrefixes(sourceViewer, contentType);
        }

        final XQDTPreferenceInterpreter prefs = new XQDTPreferenceInterpreter(fPreferenceStore);
        final int tabWidth = prefs.getTabSize();
        final int indentWidth = prefs.getIndentSize();
        if (indentWidth < tabWidth) {
            return new String[] { AutoEditUtils.getNSpaces(indentWidth), "" };
        } else if (prefs.getTabStyle() == TabStyle.TAB) {
            return getIndentPrefixesForTab(tabWidth);
        } else {
            return getIndentPrefixesForSpaces(tabWidth);
        }
    }

    protected ContentAssistPreference getContentAssistPreference() {
        return XQDTContentAssistPreference.getDefault();
    }

    @Override
    public IInformationPresenter getOutlinePresenter(ScriptSourceViewer viewer, boolean doCodeResolve) {
        return null;
    }

    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return IXQDTPartitions.XQDT_PARTITION_TYPES;
    }

    @Override
    protected void initializeScanners() {
        this.fCodeScanner = new XQDTCodeScanner(getColorManager(), fPreferenceStore);
        this.fCommentScanner = new SingleTokenScriptScanner(getColorManager(), fPreferenceStore,
                IXQDTColorConstants.XQDT_COMMENT);
        this.fXmlCommentScanner = new SingleTokenScriptScanner(getColorManager(), fPreferenceStore,
                IXQDTColorConstants.XQDT_XML_COMMENT);
        this.fXmlCdataScanner = new SingleTokenScriptScanner(getColorManager(), fPreferenceStore,
                IXQDTColorConstants.XQDT_XML_CDATA);
        this.fStringScanner = new SingleTokenScriptScanner(getColorManager(), fPreferenceStore,
                IXQDTColorConstants.XQDT_STRING);
        this.fXmlAttributeValueScanner = new SingleTokenScriptScanner(getColorManager(), fPreferenceStore,
                IXQDTColorConstants.XQDT_XML_ATTRIBUTE_VALUE);
        this.fXmlElementContentScanner = new SingleTokenScriptScanner(getColorManager(), fPreferenceStore,
                IXQDTColorConstants.XQDT_XML_ELEMENT_CONTENT);
    }

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new ScriptPresentationReconciler();
        reconciler.setDocumentPartitioning(this.getConfiguredDocumentPartitioning(sourceViewer));

        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(this.fCodeScanner);
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        dr = new DefaultDamagerRepairer(this.fCommentScanner);
        reconciler.setDamager(dr, IXQDTPartitions.XQDT_COMMENT);
        reconciler.setRepairer(dr, IXQDTPartitions.XQDT_COMMENT);

        dr = new DefaultDamagerRepairer(this.fXmlCommentScanner);
        reconciler.setDamager(dr, IXQDTPartitions.XQDT_XML_COMMENT);
        reconciler.setRepairer(dr, IXQDTPartitions.XQDT_XML_COMMENT);

        dr = new DefaultDamagerRepairer(this.fXmlCdataScanner);
        reconciler.setDamager(dr, IXQDTPartitions.XQDT_XML_CDATA);
        reconciler.setRepairer(dr, IXQDTPartitions.XQDT_XML_CDATA);

        dr = new DefaultDamagerRepairer(this.fStringScanner);
        reconciler.setDamager(dr, IXQDTPartitions.XQDT_STRING);
        reconciler.setRepairer(dr, IXQDTPartitions.XQDT_STRING);

        dr = new DefaultDamagerRepairer(this.fXmlAttributeValueScanner);
        reconciler.setDamager(dr, IXQDTPartitions.XQDT_XML_ATTRIBUTE_VALUE);
        reconciler.setRepairer(dr, IXQDTPartitions.XQDT_XML_ATTRIBUTE_VALUE);

        dr = new DefaultDamagerRepairer(this.fXmlElementContentScanner);
        reconciler.setDamager(dr, IXQDTPartitions.XQDT_XML_ELEMENT_CONTENT);
        reconciler.setRepairer(dr, IXQDTPartitions.XQDT_XML_ELEMENT_CONTENT);

        return reconciler;
    }

    @Override
    public void handlePropertyChangeEvent(PropertyChangeEvent event) {
        if (this.fCodeScanner.affectsBehavior(event)) {
            this.fCodeScanner.adaptToPreferenceChange(event);
        }
        if (this.fXmlCommentScanner.affectsBehavior(event)) {
            this.fXmlCommentScanner.adaptToPreferenceChange(event);
        }
    }

    @Override
    public boolean affectsTextPresentation(PropertyChangeEvent event) {
        return this.fCodeScanner.affectsBehavior(event) || this.fXmlCommentScanner.affectsBehavior(event);
    }

    @Override
    protected void alterContentAssistant(ContentAssistant assistant) {
        IContentAssistProcessor xqueryProcessor = new XQDTCompletionProcessor(getEditor(), assistant,
                IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(xqueryProcessor, IDocument.DEFAULT_CONTENT_TYPE);

        IContentAssistProcessor stringProcessor = new XQDTStringCompletionProcessor(getEditor(), assistant,
                IXQDTPartitions.XQDT_STRING);
        assistant.setContentAssistProcessor(stringProcessor, IXQDTPartitions.XQDT_STRING);

    }

}
