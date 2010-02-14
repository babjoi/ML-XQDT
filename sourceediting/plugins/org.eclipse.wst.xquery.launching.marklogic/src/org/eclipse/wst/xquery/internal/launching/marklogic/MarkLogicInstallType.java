/*******************************************************************************
 * Copyright (c) 2009 Mark Logic Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Neth (Mark Logic) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.launching.marklogic;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstallType;

public class MarkLogicInstallType extends XQDTInterpreterInstallType {

    public static final String INSTALL_TYPE_ID = "org.eclipse.wst.xquery.launching.MarkLogicInstallType"; //$NON-NLS-1$
    private static final String INSTALL_TYPE_NAME = "MarkLogic Server"; //$NON-NLS-1$

    public String getName() {
        return INSTALL_TYPE_NAME;
    }

    protected IInterpreterInstall doCreateInterpreterInstall(String id) {
        return new MarkLogicInstall(this, id);
    }

    @Override
    public IStatus validateInstallLocation(IFileHandle installLocation) {
        return createStatus(IStatus.OK, "", null);
    }

    @Override
    public IEnvironment getEnvironment() {
        return EnvironmentManager.getLocalEnvironment();
    }

    @Override
    public String getResolverFacetId() {
        return IMarkLogicConstants.MARKLOGIC_RESOLVER_FACET_ID;
    }

}