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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.launching.IInterpreterInstall;

public class XQDTJavaInterpreterInstallType extends XQDTInterpreterInstallType {

    private static final String INSTALL_TYPE_NAME = "Java XQuery Engine"; //$NON-NLS-1$

    public static final String[] POSSIBLE_EXTENSIONS = { "jar", "war" };

    protected IInterpreterInstall doCreateInterpreterInstall(String id) {
        return new XQDTJavaInterpreterInstall(this, id);
    }

    public String getName() {
        return INSTALL_TYPE_NAME;
    }

    @Override
    public IStatus validatePossiblyName(IFileHandle installLocation) {
        String extension = installLocation.getPath().getFileExtension();
        for (String ext : POSSIBLE_EXTENSIONS) {
            if (extension.toLowerCase().equals(ext)) {
                return createStatus(IStatus.OK, "", null); //$NON-NLS-1$
            }
        }
        return createStatus(IStatus.ERROR, "Only JAR and WAR files are supported.", null); //$NON-NLS-1$
    }

}
