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

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.wst.xquery.core.IXQDTUriResolver;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.core.utils.ResolverUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

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
            fBuiltinSources = new HashMap<String, String>();
            Bundle bundle = FrameworkUtil.getBundle(this.getClass());
            XQDTInterpreterInstallType installType = (XQDTInterpreterInstallType)getInterpreterInstallType();
            for (ModuleSearchPath searchPath : installType.getBuiltinModuleSearchPaths()) {
                try {
                    String strPath = searchPath.getPath();
                    IPath path = new Path(strPath);
                    if (strPath.contains("${interpreterContainer}")) {
                        strPath = strPath.replace("${interpreterContainer}", this.getInstallLocation().getParent()
                                .toOSString());
                        path = new Path(strPath);
                        strPath = path.toPortableString();

                    }
                    if (searchPath.isRelative()) {
                        Enumeration<?> urls = bundle.findEntries(path.toPortableString(), "*.xq*", true);
                        while (urls.hasMoreElements()) {
                            URL url = (URL)urls.nextElement();
                            String module = url.getPath().substring(
                                    url.getPath().indexOf(strPath) + strPath.length() + 1);
                            String displayName = getDisplayName(module);
                            String content = new String(Util.getInputStreamAsCharArray(url.openStream(), -1, "UTF-8"));
                            fBuiltinSources.put(displayName, content);
                        }
                    } else {
                        IFileSystem fs = EFS.getLocalFileSystem();
                        IFileStore st = fs.getStore(path);
                        List<IFileStore> foundStores = findModulesIn(st);
                        for (IFileStore store : foundStores) {
                            URL url = store.toURI().toURL();
                            String module = url.getPath().substring(
                                    url.getPath().indexOf(strPath) + strPath.length() + 1);
                            String displayName = getDisplayName(module);
                            String content = new String(Util.getInputStreamAsCharArray(url.openStream(), -1, "UTF-8"));
                            fBuiltinSources.put(displayName, content);
                        }
                    }

                } catch (Exception e) {
                    XQDTLaunchingPlugin.getDefault().getLog().log(
                            new Status(IStatus.ERROR, XQDTLaunchingPlugin.PLUGIN_ID, e.getMessage(), e));
                }
            }
        }
        return fBuiltinSources;
    }

    protected String getDisplayName(String module) {
        return module;
    }

    private List<IFileStore> findModulesIn(IFileStore store) {
        List<IFileStore> results = new ArrayList<IFileStore>();

        IFileInfo info = store.fetchInfo();
        if (info.isDirectory()) {
            try {
                IFileStore[] childStores = store.childStores(EFS.NONE, null);
                for (IFileStore child : childStores) {
                    results.addAll(findModulesIn(child));
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        } else if (info.exists()) {
            if (info.getName().matches(".*\\.xq.{0,4}")) {
                results.add(store);
            }
        }

        return results;
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
