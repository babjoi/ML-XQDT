package org.eclipse.wst.xquery.set.internal.launching;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.wst.xquery.set.core.SETProjectConfigUtil;

public class CoreSDKErrorReportReader extends SemanticCheckErrorReportReader {

    public CoreSDKErrorReportReader(ISourceModule module, String data) {
        super(module, data);
    }

    @Override
    public List<SemanticCheckError> getErrors() {
        List<SemanticCheckError> errors = super.getErrors();
        List<SemanticCheckError> newErrors = new LinkedList<SemanticCheckError>();
        for (SemanticCheckError error : errors) {
            if ("XQST0088".equals(error.getErrorCode())) {
                try {
                    SemanticCheckError newError = checkModuleURI(error, fModule);
                    newErrors.add(newError);
                } catch (CoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                newErrors.add(error);
            }
        }
        return newErrors;
    }

    private SemanticCheckError checkModuleURI(SemanticCheckError oldError, ISourceModule sourceModule)
            throws CoreException {
        try {
            String logicalURI = SETProjectConfigUtil.readProjectConfig(sourceModule.getResource().getProject())
                    .getLogicalUri().toString();
            String fileName = sourceModule.getResource().getName();
            String moduleName = fileName.substring(0, fileName.lastIndexOf('.'));
            IPath moduleLocation = sourceModule.getResource().getLocation();
            int pathSegment = moduleLocation.segmentCount();
            if (pathSegment < 2) {
                return null;
            }
            String enclosingFolder = moduleLocation.segment(pathSegment - 2);
            if (enclosingFolder.equals("lib")) {
                moduleName = "lib/" + moduleName;
            } else if (!enclosingFolder.equals("handlers")) {
                return null;
            }
            URI expectedURI = new URI(logicalURI + moduleName);
            ModuleDeclaration module = SourceParserUtil.getModuleDeclaration(sourceModule);
            if (module instanceof XQueryLibraryModule) {
                XQueryLibraryModule libraryModule = (XQueryLibraryModule)module;
                XQueryStringLiteral namespaceURI = libraryModule.getModuleDeclaration().getNamespaceUri();
                String URI = namespaceURI.toString();
                URI moduleURI = new URI(URI);
                if (!expectedURI.equals(moduleURI)) {
                    String errorMessage;
                    String problemID;
                    String currentLogicalURI = URI.substring(0, URI.lastIndexOf('/') + 1);
                    if (!logicalURI.toString().equals(currentLogicalURI)) {
                        //logical uri not matching
                        errorMessage = "The module namespace declaration \""
                                + URI
                                + "\" does not match the expected namespace \""
                                + expectedURI
                                + "\".\nSee http://www.28msec.com/support_sausalito_project_structure/index#structure_handler_modules.";
                        problemID = "SAUSA0001";
                    } else {
                        //module name not matching
                        //String name = URI.substring(URI.lastIndexOf('/') + 1);
                        errorMessage = "The module namespace declaration \""
                                + URI
                                + "\" does not match the expected namespace \""
                                + expectedURI
                                + "\".\nSee http://www.28msec.com/support_sausalito_project_structure/index#structure_library_modules.";
                        problemID = "SAUSA0002";
                    }

                    int start = namespaceURI.sourceStart();
                    int end = namespaceURI.sourceEnd();
                    ISourceLineTracker lineTracker = TextUtils.createLineTracker(sourceModule.getSource());
                    int line = lineTracker.getLineNumberOfOffset(start);
                    return new SemanticCheckError(oldError.getOriginatingFileName(), problemID, errorMessage, line,
                            start, end);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
