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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.internal.launching.utils.BuiltinModuleUtil;

public abstract class XQDTInterpreterInstallType extends AbstractInterpreterInstallType implements
        IEnvironmentAwareInterpreterInstallType {

    private List<ModuleSearchPath> fBuiltinModuleSearchPaths;
    private Map<String, String> fBuiltinDocs;

    public String getNatureId() {
        return XQDTNature.NATURE_ID;
    }

    protected String getPluginId() {
        return XQDTLaunchingPlugin.PLUGIN_ID;
    }

    protected String[] getPossibleInterpreterNames() {
        return new String[] {};
    }

    /**
     * Subclasses may override this method to return a list of resource paths to be searched for
     * builtin modules.
     * 
     * @return an array of absolute resource paths to be searched.
     */
    public ModuleSearchPath[] getBuiltinModuleSearchPaths() {
        if (fBuiltinModuleSearchPaths == null) {
            fBuiltinModuleSearchPaths = BuiltinModuleUtil.readBuiltinModuleSearchPaths(this);
        }
        return fBuiltinModuleSearchPaths.toArray(new ModuleSearchPath[fBuiltinModuleSearchPaths.size()]);
    }

    public Map<String, String> getBuiltinDocs() {
        if (fBuiltinDocs == null) {
            fBuiltinDocs = BuiltinModuleUtil.readBuiltinDocs(this);
        }
        return fBuiltinDocs;
    }

    protected IPath createPathFile(IDeployment deployment) throws IOException {
        return new Path("");
    }

    protected ILog getLog() {
        return XQDTLaunchingPlugin.getDefault().getLog();
    }

    public String getResolverFacetId() {
        return null;
    }

    public IEnvironment getEnvironment() {
        return EnvironmentManager.getLocalEnvironment();
    }
}