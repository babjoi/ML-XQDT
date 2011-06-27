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
package org.eclipse.wst.xquery.internal.launching;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstall;

public class XQDTJavaInterpreterInstall extends XQDTInterpreterInstall {

    private String fJavaArgs;
    private String fMainClass;
    private String fInterpreterArgs;

    public XQDTJavaInterpreterInstall(IInterpreterInstallType type, String id) {
        super(type, id);
    }

    @Override
    public IInterpreterRunner getInterpreterRunner(String mode) {
        if (mode.equals(ILaunchManager.RUN_MODE)) {
            return new XQDTJavaInterpreterRunner(this);
        }

        return null;
    }

    public String getMainClass() {
        return fMainClass;
    }

    public String getJavaArgs() {
        return fJavaArgs;
    }

    public String getJavaInterpreterArgs() {
        return fInterpreterArgs;
    }

    @Override
    public String getInterpreterArgs() {
        return (fJavaArgs == null ? "" : fJavaArgs) + "|" + (fMainClass == null ? "" : fMainClass) + "|"
                + (fInterpreterArgs == null ? "" : fInterpreterArgs);
    }

    @Override
    public void setInterpreterArgs(String args) {
        String[] splits = args.split("\\|");
        if (splits.length == 3) {
            fJavaArgs = splits[0];
            fMainClass = splits[1];
            fInterpreterArgs = splits[2];
        }
    }

    @Override
    public void setInterpreterArguments(String[] args) {
        // TODO Auto-generated method stub
        super.setInterpreterArguments(args);
    }
}
