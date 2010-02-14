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
package org.eclipse.wst.xquery.internal.launching.utils;

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
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.wst.xquery.launching.IXQDTBuiltinDocProvider;
import org.eclipse.wst.xquery.launching.IXQDTLaunchingConstants;
import org.eclipse.wst.xquery.launching.ModuleSearchPath;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstall;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstallType;
import org.eclipse.wst.xquery.launching.XQDTLaunchingPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class BuiltinModuleUtil {

    public static List<ModuleSearchPath> readBuiltinModuleSearchPaths(IInterpreterInstallType interpreterInstallType) {
        List<ModuleSearchPath> paths = new ArrayList<ModuleSearchPath>();
        IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(XQDTLaunchingPlugin.PLUGIN_ID,
                IXQDTLaunchingConstants.BUILTIN_MODULES_EXTENSION_ID);

        IConfigurationElement[] searchPaths = extPoint.getConfigurationElements();
        for (IConfigurationElement searchPath : searchPaths) {
            String installTypeId = searchPath
                    .getAttribute(IXQDTLaunchingConstants.BUILTIN_MODULES_INTERPRETER_INSTALL_TYPE_ID_ATTRIBUTE);
            if (installTypeId.equals(interpreterInstallType.getId())) {
                String path = searchPath.getAttribute(IXQDTLaunchingConstants.BUILTIN_MODULES_PATH_ATTRIBUTE);
                boolean relative = Boolean.parseBoolean(searchPath
                        .getAttribute(IXQDTLaunchingConstants.BUILTIN_MODULES_RELATIVE_ATTRIBUTE));
                paths.add(new ModuleSearchPath(path, relative));
            }
        }
        return paths;
    }

    public static Map<String, String> readBuiltinDocs(IInterpreterInstallType interpreterInstallType) {
        IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(XQDTLaunchingPlugin.PLUGIN_ID,
                IXQDTLaunchingConstants.BUILTIN_MODULES_EXTENSION_ID);

        IConfigurationElement[] searchPaths = extPoint.getConfigurationElements();
        for (IConfigurationElement searchPath : searchPaths) {
            String installTypeId = searchPath
                    .getAttribute(IXQDTLaunchingConstants.BUILTIN_MODULES_INTERPRETER_INSTALL_TYPE_ID_ATTRIBUTE);
            if (installTypeId.equals(interpreterInstallType.getId())) {
                try {
                    IXQDTBuiltinDocProvider docProvider = (IXQDTBuiltinDocProvider)searchPath
                            .createExecutableExtension(IXQDTLaunchingConstants.BUILTIN_MODULES_DOC_PROVIDER_ATTRIBUTE);
                    return docProvider.getDocs();
                } catch (CoreException e) {
                    continue;
                }
            }
        }

        return new HashMap<String, String>();
    }

    public static Map<String, String> readBuiltinSources(XQDTInterpreterInstall install) {
        Map<String, String> sources = new HashMap<String, String>();
        Bundle bundle = FrameworkUtil.getBundle(install.getClass());
        XQDTInterpreterInstallType installType = (XQDTInterpreterInstallType)install.getInterpreterInstallType();
        for (ModuleSearchPath searchPath : installType.getBuiltinModuleSearchPaths()) {
            try {
                String strPath = searchPath.getPath();
                IPath path = new Path(strPath);
                if (strPath.contains("${interpreterContainer}")) {
                    strPath = strPath.replace("${interpreterContainer}", install.getInstallLocation().getParent()
                            .toOSString());
                    path = new Path(strPath);
                    strPath = path.toPortableString();

                }
                if (searchPath.isRelative()) {
                    Enumeration<?> urls = bundle.findEntries(path.toPortableString(), "*.xq*", true);
                    while (urls.hasMoreElements()) {
                        URL url = (URL)urls.nextElement();
                        String module = url.getPath().substring(url.getPath().indexOf(strPath) + strPath.length() + 1);
                        String displayName = install.getDisplayName(module);
                        String content = new String(Util.getInputStreamAsCharArray(url.openStream(), -1, "UTF-8"));
                        sources.put(displayName, content);
                    }
                } else {
                    IFileSystem fs = EFS.getLocalFileSystem();
                    IFileStore st = fs.getStore(path);
                    List<IFileStore> foundStores = findModulesIn(st);
                    for (IFileStore store : foundStores) {
                        URL url = store.toURI().toURL();
                        String module = url.getPath().substring(url.getPath().indexOf(strPath) + strPath.length() + 1);
                        String displayName = install.getDisplayName(module);
                        String content = new String(Util.getInputStreamAsCharArray(url.openStream(), -1, "UTF-8"));
                        sources.put(displayName, content);
                    }
                }

            } catch (Exception e) {
                XQDTLaunchingPlugin.getDefault().getLog().log(
                        new Status(IStatus.ERROR, XQDTLaunchingPlugin.PLUGIN_ID, e.getMessage(), e));
            }
        }
        return sources;
    }

    private static List<IFileStore> findModulesIn(IFileStore store) {
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

}
