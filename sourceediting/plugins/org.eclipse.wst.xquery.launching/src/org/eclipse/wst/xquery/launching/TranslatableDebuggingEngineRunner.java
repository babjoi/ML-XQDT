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
package org.eclipse.wst.xquery.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.DebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.wst.xquery.debug.dbgp.client.IDbgpTranslator;

public abstract class TranslatableDebuggingEngineRunner extends DebuggingEngineRunner {

    public TranslatableDebuggingEngineRunner(IInterpreterInstall install) {
        super(install);
    }

    protected abstract boolean needsDbgpTranslator(PreferencesLookupDelegate delegate);

    @Override
    protected IProcess startProcess(InterpreterConfig config, ILaunch launch, IProgressMonitor monitor,
            PreferencesLookupDelegate delegate) throws CoreException {
        IProcess process = super.startProcess(config, launch, monitor, delegate);

        if (needsDbgpTranslator(delegate)) {
            IScriptProject project = ScriptRuntime.getScriptProject(launch.getLaunchConfiguration());

            IDbgpTranslator translator = getDbgpTranslator(config, project);
            if (translator == null)
                abort("The DGBP Translator could not be started", null);
            translator.start();
        }
        return process;
    }

    protected abstract IDbgpTranslator getDbgpTranslator(InterpreterConfig config, IScriptProject project);
}
