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
package org.eclipse.wst.xquery.internal.launching.zorba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.core.model.ast.XQueryModuleImport;
import org.eclipse.wst.xquery.core.model.ast.XQueryStringLiteral;
import org.eclipse.wst.xquery.launching.AbstractLocalInterpreterSemanticValidator;

public class ZorbaSemanticValidator extends AbstractLocalInterpreterSemanticValidator {

    public ZorbaSemanticValidator(IInterpreterInstall install) {
        super(install);
    }

    public String[] buildCommandLine(ISourceModule module) {
        IInterpreterInstall install = getInterpreterInstall();
        String fileName = module.getResource().getLocation().toOSString();

        ModuleDeclaration decl = SourceParserUtil.getModuleDeclaration(module);

        String checkType = "--compile-only";

        // if this is a library module and it imports modules using location hints,
        // perform only a parse instead of a compilation that will probably fail anyway
        if (decl instanceof XQueryLibraryModule) {
            XQueryLibraryModule lm = (XQueryLibraryModule)decl;
            for (XQueryModuleImport imp : lm.getImports()) {
                List<XQueryStringLiteral> hints = imp.getHints();
                if (hints.size() > 0) {
                    checkType = "--parse-only";
                    break;
                }
            }
        }

        List<String> cmdLine = new ArrayList<String>(5);
        cmdLine.add(install.getInstallLocation().toOSString());
        cmdLine.add(checkType);
        cmdLine.add("-x");
        if (decl instanceof XQueryLibraryModule) {
            cmdLine.add("-l");
        }
        if (install.getInterpreterArguments() != null) {
            cmdLine.addAll(Arrays.asList(install.getInterpreterArguments()));
        }
        cmdLine.add("-f");
        cmdLine.add("-q");
        cmdLine.add(fileName);

        return cmdLine.toArray(new String[cmdLine.size()]);
    }

}
