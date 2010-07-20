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
package org.eclipse.wst.xquery.internal.ui.text.rules;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.text.ScriptPresentationReconciler;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TypedPosition;
import org.eclipse.jface.text.TypedRegion;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.core.model.ast.IXQDTSemanticPositionProvider;
import org.eclipse.wst.xquery.core.model.ast.XQueryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryStringLiteral;
import org.eclipse.wst.xquery.core.model.ast.XQueryXmlAttributeValueText;
import org.eclipse.wst.xquery.core.model.ast.XQueryXmlElementContentText;
import org.eclipse.wst.xquery.internal.ui.text.IXQDTPartitions;
import org.eclipse.wst.xquery.internal.ui.text.XQDTPartitionScanner;

public class XQDTPartitioner extends FastPartitioner implements IElementChangedListener {

    //private static final String CONTENT_TYPES_CATEGORY= "__content_types_category"; //$NON-NLS-1$
    private ScriptEditor fEditor;
    private IPresentationReconciler fPresentationReconciler;

    public XQDTPartitioner(IPartitionTokenScanner scanner, String[] legalContentTypes) {
        super(scanner, legalContentTypes);
    }

    public void setEditor(ScriptEditor editor, IPreferenceStore preferences) {
        fEditor = editor;
        ScriptTextTools textTools = fEditor.getTextTools();
        ScriptSourceViewerConfiguration config = textTools.createSourceViewerConfiguraton(preferences, editor);
        fPresentationReconciler = config.getPresentationReconciler(editor.getScriptSourceViewer());
    }

    private int fLastEditedOffset;

    @Override
    public IRegion documentChanged2(DocumentEvent e) {
        try {
            fLastEditedOffset = e.getOffset();

            Assert.isTrue(e.getDocument() == fDocument);
            Position[] category = getPositions();
            IRegion line = fDocument.getLineInformationOfOffset(e.getOffset());
            int reparseStart = line.getOffset();

            IDocumentExtension3 extension = null;
            if (fDocument instanceof IDocumentExtension3) {
                extension = (IDocumentExtension3)fDocument;
                ITypedRegion offsetPartition = extension.getPartition(IXQDTPartitions.XQDT_PARTITIONING, e.fOffset,
                        false);
                // reparseStart = Math.max(reparseStart, offsetPartition.getOffset());
                reparseStart = offsetPartition.getOffset();
            }

            int partitionStart = -1;
            String contentType = null;
            int newLength = e.getText() == null ? 0 : e.getText().length();

            String positionCategory = getManagingPositionCategories()[0];

            int first = fDocument.computeIndexInCategory(positionCategory, reparseStart);
            if (first > 0) {
                TypedPosition partition = (TypedPosition)category[first - 1];
                if (partition.includes(reparseStart)) {
                    partitionStart = partition.getOffset();
                    contentType = partition.getType();
                    if (e.getOffset() == partition.getOffset() + partition.getLength()) {
                        reparseStart = partitionStart;
                    }
                    --first;
                } else if (reparseStart == e.getOffset()
                        && reparseStart == partition.getOffset() + partition.getLength()) {
                    partitionStart = partition.getOffset();
                    contentType = partition.getType();
                    reparseStart = partitionStart;
                    --first;
                } else {
                    partitionStart = partition.getOffset() + partition.getLength();
                    contentType = IDocument.DEFAULT_CONTENT_TYPE;
                }
            }

            fPositionUpdater.update(e);
            for (int i = first; i < category.length; i++) {
                Position p = category[i];
                if (p.isDeleted) {
                    rememberDeletedOffset(e.getOffset());
                    break;
                }
            }
            clearPositionCache();
            category = getPositions();

            fScanner.setPartialRange(fDocument, reparseStart, fDocument.getLength() - reparseStart, contentType,
                    partitionStart);

            int behindLastScannedPosition = reparseStart;
            IToken token = fScanner.nextToken();

            while (!token.isEOF()) {

                contentType = getTokenContentType(token);

                if (!isSupportedContentType(contentType)) {
                    token = fScanner.nextToken();
                    continue;
                }

                int start = fScanner.getTokenOffset();
                int length = fScanner.getTokenLength();

                behindLastScannedPosition = start + length;
                int lastScannedPosition = behindLastScannedPosition - 1;

                // remove all affected positions
                while (first < category.length) {
                    TypedPosition p = (TypedPosition)category[first];
                    if (lastScannedPosition >= p.offset + p.length
                            || (p.overlapsWith(start, length) && (!fDocument.containsPosition(positionCategory, start,
                                    length) || !contentType.equals(p.getType())))) {

                        rememberRegion(p.offset, p.length);
                        fDocument.removePosition(positionCategory, p);
                        ++first;

                    } else {
                        break;
                    }
                }

                // if position already exists and we have scanned at least the
                // area covered by the event, we are done
                if (fDocument.containsPosition(positionCategory, start, length)) {
                    if (lastScannedPosition >= e.getOffset() + newLength) {
                        return createRegion();
                    }
                    ++first;
                } else {
                    // insert the new type position
                    try {
                        fDocument.addPosition(positionCategory, new TypedPosition(start, length, contentType));
                        rememberRegion(start, length);
                    } catch (BadPositionCategoryException x) {
                    } catch (BadLocationException x) {
                    }
                }

                token = fScanner.nextToken();
            }

            first = fDocument.computeIndexInCategory(positionCategory, behindLastScannedPosition);

            clearPositionCache();
            category = getPositions();
            TypedPosition p;
            while (first < category.length) {
                p = (TypedPosition)category[first++];
                fDocument.removePosition(positionCategory, p);
                rememberRegion(p.offset, p.length);
            }

        } catch (BadPositionCategoryException x) {
            // should never happen on connected documents
        } catch (BadLocationException x) {
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            clearPositionCache();
        }

        return createRegion();
    }

    public void elementChanged(ElementChangedEvent event) {
        IModelElement source = event.getDelta().getElement();

        if (source == fEditor.getInputModelElement() && event.getDelta().getElement() instanceof ISourceModule) {
            ISourceModule module = (ISourceModule)event.getDelta().getElement();
            XQueryModule xqModule = (XQueryModule)SourceParserUtil.getModuleDeclaration(module);

            // find the ranges where we must update the semantic partitions
            // this is a costly process that can even block the editor
            // so we have to minimise it as much as possible
            List<ISourceRange> ranges = new ArrayList<ISourceRange>();

            if (XQDTCorePlugin.DEBUG_DOCUMENT_PARTITIONER) {
                System.out.println("Affected Children: " + event.getDelta().getAffectedChildren().length);
            }
            for (IModelElementDelta delta : event.getDelta().getAffectedChildren()) {
                if (XQDTCorePlugin.DEBUG_DOCUMENT_PARTITIONER) {
                    System.out.println("Change kind: " + delta.getKind() + "; " + delta.getElement());
                }
                // process the affected child (function) only get only if changed or added
                if ((delta.getKind() == IModelElementDelta.ADDED || delta.getKind() == IModelElementDelta.CHANGED)
                        && delta.getElement() instanceof IMethod) {
                    IMethod function = (IMethod)delta.getElement();
                    try {
                        ISourceRange range = function.getSourceRange();
                        if (XQDTCorePlugin.DEBUG_DOCUMENT_PARTITIONER) {
                            System.out.println("Range start: " + range.getOffset());
                            System.out.println("Range end: " + (range.getOffset() + range.getLength()));
                        }
                        ranges.add(range);
                    } catch (ModelException me) {
                        if (XQDTCorePlugin.DEBUG) {
                            me.printStackTrace();
                        }
                    }
                }
            }

            // If we did not find affected children, the change must be a fine grained one.
            // For this we use the last edited offset to determine the last edited code
            // block (ISourceReference)
            try {
                if (ranges.size() == 0) {
                    IModelElement element = null;
                    // if we just opened the file
                    if (fLastEditedOffset == 0) {
                        ranges.add(module.getSourceRange());
                    } else {
                        element = module.getElementAt(fLastEditedOffset);
                        if (element != null && element instanceof ISourceReference) {
                            ranges.add(((ISourceReference)element).getSourceRange());
                        }
                    }
                } else {
                    boolean allModule = false;
                    for (ISourceRange sourceRange : ranges) {
                        if (fLastEditedOffset < sourceRange.getOffset()) {
                            allModule = true;
                            break;
                        } else if (fLastEditedOffset < sourceRange.getOffset() + sourceRange.getLength()) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (allModule) {
                        ranges.clear();
                        ranges.add(module.getSourceRange());
                    }

                }
            } catch (ModelException e) {
                if (XQDTCorePlugin.DEBUG) {
                    e.printStackTrace();
                }
            }

            if (xqModule != null && ranges.size() > 0) {
                updateSemanticPartitions(xqModule, ranges);
            }
        }
    }

    private void rememberRegion(int offset, int length) {
        // remember start offset
        if (fStartOffset == -1) {
            fStartOffset = offset;
        } else if (offset < fStartOffset) {
            fStartOffset = offset;
        }

        // remember end offset
        int endOffset = offset + length;
        if (fEndOffset == -1) {
            fEndOffset = endOffset;
        } else if (endOffset > fEndOffset) {
            fEndOffset = endOffset;
        }
    }

    private void rememberDeletedOffset(int offset) {
        fDeleteOffset = offset;
    }

    private IRegion createRegion() {
        if (fDeleteOffset == -1) {
            if (fStartOffset == -1 || fEndOffset == -1) {
                return null;
            }
            return new Region(fStartOffset, fEndOffset - fStartOffset);
        } else if (fStartOffset == -1 || fEndOffset == -1) {
            return new Region(fDeleteOffset, 0);
        } else {
            int offset = Math.min(fDeleteOffset, fStartOffset);
            int endOffset = Math.max(fDeleteOffset, fEndOffset);
            return new Region(offset, endOffset - offset);
        }
    }

    public void updateSemanticPartitions(XQueryModule module, List<ISourceRange> ranges) {
        String category = getContentTypeCategory();
        if (category == null) {
            return;
        }

        List<Position> removedPositions = deleteSemanticPartitions(category, ranges);

        List<XQueryStringLiteral> strings = module.getStringLiterals();
        List<Position> addedPositions = updatePartitions(
                strings.toArray(new IXQDTSemanticPositionProvider[strings.size()]), category,
                IXQDTPartitions.XQDT_STRING, ranges);
        List<XQueryXmlElementContentText> contents = module.getXmlElementContentText();
        addedPositions.addAll(updatePartitions(contents.toArray(new IXQDTSemanticPositionProvider[contents.size()]),
                category, IXQDTPartitions.XQDT_XML_ELEMENT_CONTENT, ranges));
        List<XQueryXmlAttributeValueText> values = module.getXmlAttributeValuesText();
        addedPositions.addAll(updatePartitions(values.toArray(new IXQDTSemanticPositionProvider[values.size()]),
                category, IXQDTPartitions.XQDT_XML_ATTRIBUTE_VALUE, ranges));

        if (XQDTCorePlugin.DEBUG_DOCUMENT_PARTITIONER) {
            System.out.println("Removed positions: " + removedPositions.size());
            System.out.println("Added positions: " + addedPositions.size());
        }

        if (XQDTCorePlugin.DEBUG_DOCUMENT_PARTITIONER) {
            System.out.println(Calendar.getInstance().getTimeInMillis() + ": start applyPositions");
        }
        applyPositions(addedPositions, removedPositions);
        if (XQDTCorePlugin.DEBUG_DOCUMENT_PARTITIONER) {
            System.out.println(Calendar.getInstance().getTimeInMillis() + ": end applyPositions");
        }
    }

    private void applyPositions(List<Position> addedPositions, List<Position> removedPositions) {
        clearPositionCache();

        final TextPresentation textPresentation = createPresentation(addedPositions, removedPositions);

        if (textPresentation == null) {
            return;
        }

        if (fEditor == null) {
            return;
        }
        IWorkbenchPartSite site = fEditor.getSite();
        if (site == null) {
            return;
        }
        Shell shell = site.getShell();
        if (shell == null || shell.isDisposed()) {
            return;
        }
        Display display = shell.getDisplay();
        if (display == null || display.isDisposed()) {
            return;
        }

        display.asyncExec(new Runnable() {

            public void run() {
                System.out.println("changeTextPresentation2");
                fEditor.getScriptSourceViewer().changeTextPresentation(textPresentation, false);
            }
        });
    }

    public TextPresentation createPresentation(List<Position> addedPositions, List<Position> removedPositions) {
        ISourceViewer sourceViewer = fEditor.getScriptSourceViewer();
        if (sourceViewer == null) {
            return null;
        }

        IDocument document = sourceViewer.getDocument();
        if (document == null) {
            return null;
        }

        int minStart = Integer.MAX_VALUE;
        int maxEnd = Integer.MIN_VALUE;
        for (int i = 0, n = removedPositions.size(); i < n; i++) {
            Position position = removedPositions.get(i);
            int offset = position.getOffset();
            minStart = Math.min(minStart, offset);
            maxEnd = Math.max(maxEnd, offset + position.getLength());
        }
        for (int i = 0, n = addedPositions.size(); i < n; i++) {
            Position position = addedPositions.get(i);
            int offset = position.getOffset();
            minStart = Math.min(minStart, offset);
            maxEnd = Math.max(maxEnd, offset + position.getLength());
        }

        if (minStart < maxEnd && fPresentationReconciler instanceof ScriptPresentationReconciler) {
            return ((ScriptPresentationReconciler)fPresentationReconciler).createRepairDescription(new Region(minStart,
                    maxEnd - minStart), document);
        }

        return null;
    }

    private String getContentTypeCategory() {
        for (String category : fDocument.getPositionCategories()) {
            if (category.startsWith(IXQDTPartitions.CONTENT_TYPES_CATEGORY)) {
                return category;
            }
        }
        return null;
    }

    private List<Position> deleteSemanticPartitions(String category, List<ISourceRange> ranges) {
        List<Position> positions = new ArrayList<Position>();
        int rangeIndex = 0;
        if (ranges.size() == 0) {
            return positions;
        }

        ISourceRange range = ranges.get(rangeIndex);
        try {
            for (Position position : fDocument.getPositions(category)) {
                if (position.getOffset() < range.getOffset()) {
                    continue;
                } else if (position.getOffset() > range.getOffset() + range.getLength()) {
                    while (++rangeIndex < ranges.size() && position.getOffset() > range.getOffset() + range.getLength()) {
                        range = ranges.get(rangeIndex);
                    }
                    if (rangeIndex == ranges.size()) {
                        return positions;
                    }
                }

                if (XQDTPartitionScanner.isSemanticPartitionType(((TypedPosition)position).getType())) {
                    fDocument.removePosition(category, position);
                    positions.add(position);
                }
            }
        } catch (BadPositionCategoryException e) {
            e.printStackTrace();
        }

        return positions;
    }

    private List<Position> updatePartitions(IXQDTSemanticPositionProvider[] providers, String category,
            String positionType, List<ISourceRange> ranges) {
        List<Position> positions = new ArrayList<Position>();

        int rangeIndex = 0;
        if (ranges.size() == 0) {
            return positions;
        }

        ISourceRange range = ranges.get(rangeIndex);
        for (IXQDTSemanticPositionProvider provider : providers) {
            TypedPosition position = provider.getTypedPosition(positionType);

            if (position.getOffset() < range.getOffset()) {
                continue;
            } else if (position.getOffset() > range.getOffset() + range.getLength()) {
                while (++rangeIndex < ranges.size() && position.getOffset() < range.getOffset()) {
                    range = ranges.get(rangeIndex);
                }
                if (rangeIndex == ranges.size()) {
                    return positions;
                }
            }

            try {
                fDocument.addPosition(category, position);
                positions.add(position);
            } catch (BadLocationException e) {
                e.printStackTrace();
            } catch (BadPositionCategoryException e) {
                e.printStackTrace();
            }
        }
        return positions;
    }

    public ITypedRegion getPartitionFromDocument(int offset) {
        try {
            String positionCategory = getManagingPositionCategories()[0];
            Position[] category = fDocument.getPositions(positionCategory);

            if (category == null || category.length == 0) {
                return new TypedRegion(0, fDocument.getLength(), IDocument.DEFAULT_CONTENT_TYPE);
            }

            int index = fDocument.computeIndexInCategory(positionCategory, offset);

            if (index < category.length) {

                TypedPosition next = (TypedPosition)category[index];

                if (offset == next.offset) {
                    return new TypedRegion(next.getOffset(), next.getLength(), next.getType());
                }

                if (index == 0) {
                    return new TypedRegion(0, next.offset, IDocument.DEFAULT_CONTENT_TYPE);
                }

                TypedPosition previous = (TypedPosition)category[index - 1];
                if (previous.includes(offset)) {
                    return new TypedRegion(previous.getOffset(), previous.getLength(), previous.getType());
                }

                int endOffset = previous.getOffset() + previous.getLength();
                return new TypedRegion(endOffset, next.getOffset() - endOffset, IDocument.DEFAULT_CONTENT_TYPE);
            }

            TypedPosition previous = (TypedPosition)category[category.length - 1];
            if (previous.includes(offset)) {
                return new TypedRegion(previous.getOffset(), previous.getLength(), previous.getType());
            }

            int endOffset = previous.getOffset() + previous.getLength();
            return new TypedRegion(endOffset, fDocument.getLength() - endOffset, IDocument.DEFAULT_CONTENT_TYPE);

        } catch (BadPositionCategoryException x) {
        } catch (BadLocationException x) {
        }

        return new TypedRegion(0, fDocument.getLength(), IDocument.DEFAULT_CONTENT_TYPE);
    }
}
