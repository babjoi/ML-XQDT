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
package org.eclipse.wst.xquery.internal.debug.ui.zorba.interpreters;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.xquery.debug.ui.interpreters.AbstractInterpreterEnvironmentVariablesBlock;
import org.eclipse.wst.xquery.debug.ui.interpreters.AddLocalInterpreterDialogBlock;
import org.eclipse.wst.xquery.debug.ui.interpreters.LocalInterpreterEnvironmentVariablesBlock;
import org.eclipse.wst.xquery.debug.ui.zorba.ZorbaDebugUIPlugin;

public class ZorbaAddInterpreterDialogBlock extends AddLocalInterpreterDialogBlock {

    @Override
    @SuppressWarnings("restriction")
    public void setFocus() {
        fInterpreterLocationField.setFocus();
    }

    public String generateInterpreterName(String location) {
        String genName = null;

        ProcessBuilder pb = new ProcessBuilder(location, "--version");
        try {
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = br.readLine();
            String version = line.substring(line.indexOf(':') + 2);
            genName = "Zorba " + version;
        } catch (Exception e) {
            if (ZorbaDebugUIPlugin.DEBUG) {
                e.printStackTrace();
            }
            IPath path = new Path(location);
            if (path.segmentCount() > 0) {
                genName = path.lastSegment();
            }
        }

        // Add number if interpreter with such name already exists.
        String pName = genName;
        if (pName != null) {
            int index = 0;
            while (!validateGeneratedName(pName)) {
                pName = genName + "(" + String.valueOf(++index) //$NON-NLS-1$
                        + ")"; //$NON-NLS-1$
            }
        }
        return pName;
    }

    @Override
    protected AbstractInterpreterEnvironmentVariablesBlock createEnvironmentVariablesBlock() {
        return new LocalInterpreterEnvironmentVariablesBlock(fAddInterpreterDialog);
    }
}
