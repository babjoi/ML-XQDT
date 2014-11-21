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
package org.eclipse.wst.xquery.debug.debugger.zorba;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;
import org.eclipse.wst.xquery.internal.launching.zorba.ZorbaInstall;
import org.eclipse.wst.xquery.internal.launching.zorba.ZorbaRunnerConfigurator;

public class ZorbaDebuggerRunnerFactory implements IInterpreterRunnerFactory {

    public IInterpreterRunner createRunner(IInterpreterInstall install) {
        if (install instanceof ZorbaInstall) {
            return new ZorbaDebuggerRunner(install, new ZorbaRunnerConfigurator(install));
        }
        return null;
    }

}
