/*******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.core.internal.validation;

import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionList;
import org.eclipse.wst.validation.AbstractValidator;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.operations.IWorkbenchContext;
import org.eclipse.wst.validation.internal.operations.LocalizedMessage;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xquery.sse.core.internal.parser.XQueryTokenizer;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegion;
import org.eclipse.wst.xquery.sse.core.internal.regions.XQueryRegions;

/**
 * Source validation "as-we-type"
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public class XQuerySourceValidator extends AbstractValidator implements IValidator {

	// Constructors

	public XQuerySourceValidator() {
	}

	// Implements IValidator

	public void cleanup(IReporter reporter) {
		// TODO Auto-generated method stub
	}

	public void validate(IValidationContext helper, IReporter reporter) throws ValidationException {
		String[] uris = helper.getURIs();
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		if (uris.length > 0) {
			IFile currentFile = null;

			for (int i = 0; i < uris.length && !reporter.isCancelled(); i++) {
				// might be called with just project path?
				IPath path = new Path(uris[i]);
				if (path.segmentCount() > 1) {
					currentFile = wsRoot.getFile(path);
					validate(currentFile, reporter);

				} else if (uris.length == 1) {
					validateV1Project(helper, reporter);
				}
			}
		} else
			validateV1Project(helper, reporter);

	}

	// Helpers

	private void validate(IFile currentFile, IReporter reporter) {
		Message message = new LocalizedMessage(IMessage.LOW_SEVERITY, currentFile.getFullPath().toString().substring(1));
		reporter.displaySubtask(this, message);

		IStructuredModel model = null;
		try {
			model = StructuredModelManager.getModelManager().getModelForRead(currentFile);

			// Lexical-level validation
			IStructuredDocument document = null;
			if (model != null) {
				document = model.getStructuredDocument();

				IStructuredDocumentRegion validationRegion = document.getFirstStructuredDocumentRegion();
				while (validationRegion != null) {
					validate(validationRegion, reporter);
					validationRegion = validationRegion.getNext();
				}
			}

			// Syntactic-level validation
			// Just create annotations from ModelBuilder messages.

		} catch (Exception e) {
			// Logger.logException(e);
		} finally {
			if (model != null) {
				model.releaseFromRead();
			}
		}
	}

	public void validate(IStructuredDocumentRegion sdregion, IReporter reporter) {
		final ITextRegionList regions = sdregion.getRegions();
		for (int i = regions.size() - 1; i >= 0; i--) {
			final XQueryRegion region = (XQueryRegion) regions.get(i);
			final int errState = region.getErrorLexicalState();

			if (region.getType() == XQueryRegions.UNDEFINED || errState != -1) {

				String text;
				switch (errState) {
				case XQueryTokenizer.TS_ENDAXISSTEP:
				case XQueryTokenizer.TS_ENDPRIMARY:
					text = "Syntax Error. Invalid character after a single expression (ExprSingle)";
					break;
				case XQueryTokenizer.TS_NSDECLSEP:
				case XQueryTokenizer.TS_XQUERYVERSIONSEPARATOR:
				case XQueryTokenizer.TS_MODULESEP:
				case XQueryTokenizer.TS_SEPARATOR:
					text = "Syntax Error. Missing ';' character";
					break;
				case XQueryTokenizer.TS_XQUERYENCODING:
				default:
					text = "Syntax Error.";
				}

				IMessage message = new LocalizedMessage(IMessage.HIGH_SEVERITY, text);

				message.setOffset(sdregion.getStartOffset() + region.getStart());
				message.setLength(region.getTextLength());
				message.setLineNo(sdregion.getParentDocument().getLineOfOffset(
						sdregion.getStartOffset() + region.getStart()));
				reporter.addMessage(this, message);
			}
		}
	}

	/**
	 * @param helper
	 * @param reporter
	 */
	private void validateV1Project(IValidationContext helper, final IReporter reporter) {
		// if uris[] length 0 -> validate() gets called for each project
		if (helper instanceof IWorkbenchContext) {
			IProject project = ((IWorkbenchContext) helper).getProject();
			IResourceProxyVisitor visitor = new IResourceProxyVisitor() {
				public boolean visit(IResourceProxy proxy) throws CoreException {
					if (shouldValidate(proxy)) {
						validate((IFile) proxy.requestResource(), reporter);
					}
					return true;
				}

			};
			try {
				// collect all xquery files for the project
				project.accept(visitor, IResource.DEPTH_INFINITE);
			} catch (CoreException e) {
				// Logger.logException(e);
			}
		}
	}

	private boolean shouldValidate(IResourceProxy proxy) {
		if (proxy.getType() == IResource.FILE) {
			String name = proxy.getName();
			if (name.toLowerCase(Locale.US).endsWith(".xq")) {
				return true;
			}
		}
		return shouldValidate(proxy.requestResource(), false);
	}

	private boolean shouldValidate(IResource file, boolean checkExtension) {
		if (file == null || !file.exists() || file.getType() != IResource.FILE)
			return false;

		// TODO: other extensions..
		if (checkExtension) {
			String extension = file.getFileExtension();
			if (extension != null && "xq".endsWith(extension.toLowerCase(Locale.US)))
				return true;
		}
		return false;
	}

}
