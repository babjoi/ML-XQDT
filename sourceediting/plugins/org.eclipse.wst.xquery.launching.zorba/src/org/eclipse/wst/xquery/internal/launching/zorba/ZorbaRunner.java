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

import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;

public class ZorbaRunner extends AbstractInterpreterRunner {

    private ZorbaRunnerConfigurator fConfigurator;

    protected ZorbaRunner(IInterpreterInstall install, ZorbaRunnerConfigurator configurator) {
        super(install);
        fConfigurator = configurator;
    }

    protected String[] renderCommandLine(InterpreterConfig config) {
        String[] cmdLine = super.renderCommandLine(config);
        return fConfigurator.renderCommandLine(config, cmdLine);
    }

    @Override
    protected String[] getEnvironmentVariablesAsStrings(InterpreterConfig config) {
        String[] env = super.getEnvironmentVariablesAsStrings(config);
        return fConfigurator.addInternalVarsToRunnerEnv(config, env);
    }
}
