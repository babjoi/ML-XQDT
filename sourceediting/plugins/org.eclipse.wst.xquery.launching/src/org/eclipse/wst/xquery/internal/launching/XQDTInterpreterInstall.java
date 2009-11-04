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

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.wst.xquery.core.IUriResolver;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.internal.core.XQDTUriResolver;
import org.eclipse.wst.xquery.launching.IUriResolving;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public abstract class XQDTInterpreterInstall extends AbstractInterpreterInstall implements IUriResolving {

    private Map<String, String> builtinSources;

    public XQDTInterpreterInstall(IInterpreterInstallType type, String id) {
        super(type, id);
    }

    public String getNatureId() {
        return XQDTNature.NATURE_ID;
    }

    public IUriResolver getResolver() {
        return new XQDTUriResolver();
    }

    private synchronized Map<String, String> getBuiltinSources() {
        if (builtinSources == null) {
            builtinSources = new HashMap<String, String>();
            Bundle bundle = FrameworkUtil.getBundle(this.getClass());
            XQDTInterpreterInstallType installType = (XQDTInterpreterInstallType)getInterpreterInstallType();
            for (String builtinRoot : installType.getBuiltinModuleRoots()) {
                Enumeration<?> urls = bundle.findEntries(builtinRoot, "*.xq*", true);
                while (urls.hasMoreElements()) {
                    try {
                        URL url = (URL)urls.nextElement();
                        String path = url.getPath()
                                .substring(url.getPath().indexOf(builtinRoot) + builtinRoot.length());
                        String content = new String(Util.getInputStreamAsCharArray(url.openStream(), -1, "UTF-8"));
                        builtinSources.put(path, content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return builtinSources;
    }

    @Override
    public String getBuiltinModuleContent(String name) {
        return getBuiltinSources().get(name);
    }

    @Override
    public String[] getBuiltinModules() {
        final Map<String, String> sources = getBuiltinSources();
        return sources.keySet().toArray(new String[sources.size()]);
    }
}
