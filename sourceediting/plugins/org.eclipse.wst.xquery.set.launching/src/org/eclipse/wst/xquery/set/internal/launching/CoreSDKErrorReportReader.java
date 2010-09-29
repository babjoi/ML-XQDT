/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.launching;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.builder.ISourceLineTracker;
import org.eclipse.dltk.utils.TextUtils;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryStringLiteral;
import org.eclipse.wst.xquery.core.semantic.SemanticCheckError;
import org.eclipse.wst.xquery.core.semantic.SemanticCheckErrorReportReader;
import org.eclipse.wst.xquery.set.core.ISETCoreConstants;
import org.eclipse.wst.xquery.set.core.utils.SETProjectConfigUtil;

public class CoreSDKErrorReportReader extends SemanticCheckErrorReportReader {

    public CoreSDKErrorReportReader(ISourceModule module, String data) {
        super(module, data);
    }

    @Override
    public List<SemanticCheckError> getErrors() {
        List<SemanticCheckError> errors = super.getErrors();
        List<SemanticCheckError> newErrors = new LinkedList<SemanticCheckError>();
        for (SemanticCheckError error : errors) {
            SemanticCheckError newError = processError(error);
            if (newError != null) {
                newErrors.add(newError);
            }
        }
        return newErrors;
    }

    private SemanticCheckError processError(SemanticCheckError oldError) {
        try {
            if ("XQST0088".equals(oldError.getErrorCode())) {
                IResource moduleResource = fModule.getResource();
                String projectLogicalUriString = SETProjectConfigUtil.readProjectConfig(moduleResource.getProject())
                        .getLogicalUri().toString();
                String fileName = moduleResource.getName();
                String moduleName = fileName.substring(0, fileName.lastIndexOf('.'));
                IPath moduleLocation = moduleResource.getFullPath();
                int pathSegment = moduleLocation.segmentCount();
                if (pathSegment < 2) {
                    return oldError;
                }
                String enclosingFolder = moduleLocation.segment(pathSegment - 2);
                if (enclosingFolder.equals(ISETCoreConstants.PROJECT_DIRECTORY_LIBRARY)) {
                    moduleName = ISETCoreConstants.PROJECT_DIRECTORY_LIBRARY + "/" + moduleName;
                } else if (!enclosingFolder.equals(ISETCoreConstants.PROJECT_DIRECTORY_HANDLER)) {
                    return oldError;
                }
                URI projectLogicalUri = new URI(projectLogicalUriString);
                URI expectedUri = projectLogicalUri.resolve(moduleName);

                ModuleDeclaration module = SourceParserUtil.getModuleDeclaration(fModule);
                if (module instanceof XQueryLibraryModule) {
                    XQueryLibraryModule libraryModule = (XQueryLibraryModule)module;
                    XQueryStringLiteral nsStringLiteral = libraryModule.getModuleDeclaration().getNamespaceUri();
                    String nsString = nsStringLiteral.toString();
                    URI moduleUri = null;
                    try {
                        moduleUri = new URI(nsString);
                    } catch (URISyntaxException use) {
                    }
                    if (moduleUri == null || !expectedUri.equals(moduleUri)) {
                        String errorMessage;
                        String problemID;
                        if (!expectedUri.toString().equals(nsString)) {
                            //logical uri not matching
                            errorMessage = "The module namespace declaration \""
                                    + nsString
                                    + "\" does not match the expected namespace \""
                                    + expectedUri
                                    + "\".\nSee http://www.28msec.com/support_sausalito_project_structure/index#structure_handler_modules.";
                            problemID = "SAUSA0001";
                        } else {
                            //module name not matching
                            //String name = URI.substring(URI.lastIndexOf('/') + 1);
                            errorMessage = "The module namespace declaration \""
                                    + nsString
                                    + "\" does not match the expected namespace \""
                                    + expectedUri
                                    + "\".\nSee http://www.28msec.com/support_sausalito_project_structure/index#structure_library_modules.";
                            problemID = "SAUSA0002";
                        }

                        int start = nsStringLiteral.sourceStart() + 1;
                        int end = nsStringLiteral.sourceEnd();
                        ISourceLineTracker lineTracker = TextUtils.createLineTracker(fModule.getSource());
                        int line = lineTracker.getLineNumberOfOffset(start);
                        return new SemanticCheckError(oldError.getOriginatingFileName(), problemID, errorMessage, line,
                                start, end);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldError;
    }
}
