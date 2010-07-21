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
package org.eclipse.wst.xquery.sse.ui.internal.breakpoint;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.internal.debug.core.model.ScriptDebugModel;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.wst.sse.ui.internal.provisional.extensions.ISourceEditingTextTools;
import org.eclipse.wst.sse.ui.internal.provisional.extensions.breakpoint.IBreakpointProvider;

/**
 * XQuery breakpoint provider.
 * 
 * Delegate breakpoints management to DLTK.
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */

@SuppressWarnings("restriction")
public class BreakpointProvider implements IBreakpointProvider {

	// State

	/** The text tools */
	ISourceEditingTextTools tool;

	// Implements IBreakpointProvider

	public IStatus addBreakpoint(IDocument document, IEditorInput input, int lineNumber, int offset)
			throws CoreException {

		IResource resource = getResource(input);
		if (resource != null) {

			try {
				final int startChar = document.getLineOffset(lineNumber - 1);
				final int endChar = startChar + document.getLineLength(lineNumber - 1);

				// Create breakpoint in DLTK.
				ScriptDebugModel.createLineBreakpoint(resource, resource.getFullPath(), lineNumber, startChar, endChar,
						true, null);

				return Status.OK_STATUS;
			} catch (BadLocationException e) {

			}

		}

		// Fallback.
		return Status.CANCEL_STATUS;
	}

	public void setSourceEditingTextTools(ISourceEditingTextTools tool) {
		this.tool = tool;
	}

	public IResource getResource(IEditorInput input) {
		return (IResource) input.getAdapter(IResource.class);
	}
}
