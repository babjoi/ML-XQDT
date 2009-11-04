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
package org.eclipse.wst.xquery.set.internal.launching;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.wst.xquery.launching.AbstractLocalInterpreterSemanticValidator;

public class CoreSDKSemanticValidator extends AbstractLocalInterpreterSemanticValidator {

    public CoreSDKSemanticValidator(IInterpreterInstall install) {
        super(install);
    }

    public String[] buildCommandLine(ISourceModule module) {
        IInterpreterInstall install = getInterpreterInstall();
        String fileName = module.getResource().getLocation().toOSString();

        List<String> cmdLine = new ArrayList<String>(5);

        cmdLine.add(install.getInstallLocation().toOSString());
        cmdLine.add("test");
        cmdLine.add("code");
        cmdLine.add("-f");
        cmdLine.add(fileName);
        cmdLine.add("-x");

        return cmdLine.toArray(new String[cmdLine.size()]);
    }
}
