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

import java.util.Map;

import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.wst.xquery.core.IXQDTUriResolver;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.core.utils.ResolverUtil;
import org.eclipse.wst.xquery.internal.launching.utils.BuiltinModuleUtil;

public abstract class XQDTInterpreterInstall extends AbstractInterpreterInstall implements IUriResolving {

    private Map<String, String> fBuiltinSources;

    public XQDTInterpreterInstall(IInterpreterInstallType type, String id) {
        super(type, id);
    }

    public String getNatureId() {
        return XQDTNature.NATURE_ID;
    }

    public IXQDTUriResolver getResolver() {
        return ResolverUtil.createDefaultUriResolver();
    }

    private synchronized Map<String, String> getBuiltinSources() {
        if (fBuiltinSources == null) {
            fBuiltinSources = BuiltinModuleUtil.readBuiltinSources(this);
        }
        return fBuiltinSources;
    }

    public String getDisplayName(String module) {
        return module;
    }

    @Override
    public String getBuiltinModuleContent(String name) {
        return getBuiltinSources().get(name);
    }

    @Override
    public String[] getBuiltinModules() {
        Map<String, String> sources = getBuiltinSources();
        return sources.keySet().toArray(new String[sources.size()]);
    }
}
