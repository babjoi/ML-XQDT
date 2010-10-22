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
package org.eclipse.wst.xquery.internal.debug.ui;

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.breakpoints.BreakpointUtils;
import org.eclipse.dltk.debug.ui.breakpoints.Messages;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xquery.debug.core.IXQDTDebugConstants;

public class XQDTToggleBreakpointAdapter extends ScriptToggleBreakpointAdapter {

    protected String getDebugModelId() {
        return IXQDTDebugConstants.DEBUG_MODEL_ID;
    }

    public boolean canToggleBreakpoints(IWorkbenchPart part, ISelection selection) {
        return true;
    }

    public void toggleBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
        ITextEditor editor = getTextEditor(part);
        if (editor == null) {
            return;
        }
        IDocumentProvider documentProvider = editor.getDocumentProvider();
        if (documentProvider == null) {
            return;
        }
        IDocument document = documentProvider.getDocument(editor.getEditorInput());

        int line = BREAKPOINT_LINE_NOT_FOUND;
        if (selection instanceof ITextSelection) {
            line = ((ITextSelection)selection).getStartLine() + 1;
        }
        if (line == BREAKPOINT_LINE_NOT_FOUND) {
            return;
        }

        ISelection newSel = translateToMembers(part, selection);
        IMethod function = null;
        if (newSel instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection)newSel;
            for (Iterator<?> i = strucSelection.iterator(); i.hasNext();) {
                Object thing = i.next();
                if (thing instanceof IMethod) {
                    IMethod member = (IMethod)thing;
                    int offset = member.getSourceRange().getOffset();
                    int functionLine = BREAKPOINT_LINE_NOT_FOUND;
                    try {
                        functionLine = document.getLineOfOffset(offset) + 1;
                    } catch (BadLocationException e) {
                    }
                    if (line == functionLine) {
                        function = (IMethod)thing;
                        break;
                    }
                }
            }
        }
//        if (function != null) {
//            toggleMethodBreakpoints(part, new StructuredSelection(new Object[] { function, document, line }));
//            return;
//        }

        toggleLineBreakpoints(part, selection);
    }

    @Override
    public boolean canToggleLineBreakpoints(IWorkbenchPart part, ISelection selection) {
        return true;
    }

    public boolean canToggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) {
        return true;
    }

    public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection) {
        return false;
    }

    public void toggleMethodBreakpoints(final IWorkbenchPart part, final ISelection selection) throws CoreException {

        Job job = new Job("Toggle Function Breakpoint") { //$NON-NLS-1$
            protected IStatus run(IProgressMonitor monitor) {
                final ITextEditor editor = getTextEditor(part);
                if (editor != null && selection instanceof IStructuredSelection) {
                    if (monitor.isCanceled()) {
                        return Status.CANCEL_STATUS;
                    }

                    Object[] elements = ((IStructuredSelection)selection).toArray();
                    IMethod function = (IMethod)elements[0];
                    IDocument document = (IDocument)elements[1];
                    int lineNumber = ((Integer)elements[2]).intValue();

                    try {
                        report(null, part);

                        final String debugModelId = getDebugModelId();
                        final IBreakpoint breakpoint = BreakpointUtils.findLineBreakpoint(editor, lineNumber,
                                debugModelId);

                        if (breakpoint != null) {
                            // if breakpoint already exists, delete it
                            breakpoint.delete();
                        } else {
                            lineNumber = findBreakpointLine(document, lineNumber - 1, getValidator()) + 1;

                            if (lineNumber != BREAKPOINT_LINE_NOT_FOUND) {
                                // Check if already breakpoint set to the same function
                                if (BreakpointUtils.findLineBreakpoint(editor, lineNumber, debugModelId) == null) {
                                    BreakpointUtils.addMethodEntryBreakpoint(editor, lineNumber,
                                            function.getFullyQualifiedName());
                                } else {
                                    report(NLS.bind(Messages.ScriptToggleBreakpointAdapter_breakpointAlreadySetAtLine,
                                            new Object[] { new Integer(lineNumber) }), part);
                                }
                            } else {
                                report(Messages.ScriptToggleBreakpointAdapter_invalidBreakpointPosition, part);
                            }
                        }
                    } catch (CoreException e) {
                        DLTKDebugUIPlugin.log(e);
                    }
                }

                return Status.OK_STATUS;
            }

        };
        job.setSystem(true);
        job.schedule();
    }

    public void toggleWatchpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
    }

}
