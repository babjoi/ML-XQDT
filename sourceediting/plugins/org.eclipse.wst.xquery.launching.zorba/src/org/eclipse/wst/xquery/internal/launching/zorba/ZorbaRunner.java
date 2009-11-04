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

import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;

public class ZorbaRunner extends AbstractInterpreterRunner {

    private static List<String> DEFAULT_ARGS = Arrays.asList(new String[] { "-f", "-q" });

    protected ZorbaRunner(IInterpreterInstall install) {
        super(install);
    }

    protected String[] renderCommandLine(InterpreterConfig config) {
        String[] cmdLine = super.renderCommandLine(config);
        List<String> newCmdLine = new ArrayList<String>(Arrays.asList(cmdLine));

        int index = newCmdLine.size() - config.getScriptArgs().size() - 1;
        newCmdLine.addAll(index, DEFAULT_ARGS);

        return newCmdLine.toArray(new String[newCmdLine.size()]);
    }

}
