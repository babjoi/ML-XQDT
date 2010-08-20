package org.eclipse.wst.xquery.set.internal.core.builder;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryStringLiteral;

public class SETURICheckerParticipant implements IBuildParticipant {

    public static final String INVALID_LOGICAL_URI = "INVALID_LOGICAL_URI";
    public static final String INVALID_MODULE_NAME = "INVALID_MODULE_NAME";

    private URI logicalURI;

    public SETURICheckerParticipant(URI logicalURI) {
        this.logicalURI = logicalURI;
    }

    public void build(IBuildContext context) throws CoreException {
        try {
            String fileName = context.getFileName();
            String moduleName = fileName.substring(0, fileName.lastIndexOf('.'));
            IPath moduleLocation = context.getFile().getLocation();
            int pathSegment = moduleLocation.segmentCount();
            if (pathSegment < 2) {
                return;
            }
            String enclosingFolder = moduleLocation.segment(pathSegment - 2);
            if (enclosingFolder.equals("lib")) {
                moduleName = "lib/" + moduleName;
            } else if (!enclosingFolder.equals("handlers")) {
                return;
            }
            URI expectedURI = new URI(logicalURI + moduleName);
            ISourceModule sourceModule = context.getSourceModule();
            ModuleDeclaration module = SourceParserUtil.getModuleDeclaration(sourceModule);
            if (module instanceof XQueryLibraryModule) {
                XQueryLibraryModule libraryModule = (XQueryLibraryModule)module;
                XQueryStringLiteral namespaceURI = libraryModule.getModuleDeclaration().getNamespaceUri();
                String URI = namespaceURI.toString();
                URI moduleURI = new URI(URI);
                Collection<String> arguments = new LinkedList<String>();
                if (!expectedURI.equals(moduleURI)) {
                    String errorMessage;
                    if (!logicalURI.equals(URI.substring(0, URI.lastIndexOf('/')))) {
                        //logical uri not matching
                        errorMessage = "Incorrect module namespace, wrong project logical URI.";
                        arguments.add(INVALID_LOGICAL_URI);
                    } else {
                        //module name not matching
                        String name = URI.substring(URI.lastIndexOf('/') + 1);
                        errorMessage = "Incorrect module namespace, wrong module name: " + name + ".";
                        arguments.add(INVALID_MODULE_NAME);
                    }
                    int start = namespaceURI.sourceStart();
                    int end = namespaceURI.sourceEnd();
                    int line = context.getLineTracker().getLineNumberOfOffset(start);
                    DefaultProblem problem = new DefaultProblem(fileName, errorMessage, IProblem.ImportRelated,
                            arguments.toArray(new String[arguments.size()]), ProblemSeverities.Error, start, end, line);
                    context.getProblemReporter().reportProblem(problem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
