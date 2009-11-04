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
package org.eclipse.wst.xquery.set.internal.launching;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class CoreSDKInstallType implements IInterpreterInstallType, IExecutableExtension {

    private static final String INSTALL_TYPE_NAME = "Sausalito CoreSDK";
    private static final String INTERPRETER_NAME = "sausalito";

    private List<IInterpreterInstall> fInterpreters = new ArrayList<IInterpreterInstall>();
    private String fId;

    protected IPath createPathFile(IDeployment deployment) throws IOException {
        return null;
    }

    protected IInterpreterInstall doCreateInterpreterInstall(String id) {
        return new CoreSDKInstall(this, id);
    }

    protected ILog getLog() {
        return SETLaunchingPlugin.getDefault().getLog();
    }

    protected String getPluginId() {
        return SETLaunchingPlugin.PLUGIN_ID;
    }

    public String getName() {
        return INSTALL_TYPE_NAME;
    }

    public String getNatureId() {
        return SETNature.NATURE_ID;
    }

    public IInterpreterInstall createInterpreterInstall(String id) {
        if (findInterpreterInstall(id) != null) {
            String format = "Duplicate Sausalito CoreSDK: {0}";
            throw new IllegalArgumentException(MessageFormat.format(format, new Object[] { id }));
        }

        IInterpreterInstall install = doCreateInterpreterInstall(id);
        fInterpreters.add(install);
        return install;
    }

    public void disposeInterpreterInstall(String id) {
        Iterator<IInterpreterInstall> it = fInterpreters.iterator();
        while (it.hasNext()) {
            IInterpreterInstall install = it.next();
            if (install.getId().equals(id)) {
                it.remove();
                ScriptRuntime.fireInterpreterRemoved(install);
                return;
            }
        }
    }

    public IInterpreterInstall findInterpreterInstall(String id) {
        Iterator<IInterpreterInstall> it = fInterpreters.iterator();
        while (it.hasNext()) {
            IInterpreterInstall install = it.next();
            if (install.getId().equals(id)) {
                return install;
            }
        }

        return null;
    }

    public IInterpreterInstall findInterpreterInstallByName(String name) {
        Iterator<IInterpreterInstall> it = fInterpreters.iterator();
        while (it.hasNext()) {
            IInterpreterInstall install = it.next();
            if (install.getName().equals(name)) {
                return install;
            }
        }
        return null;
    }

    public synchronized LibraryLocation[] getDefaultLibraryLocations(final IFileHandle installLocation) {
        return getDefaultLibraryLocations(installLocation, null);
    }

    public synchronized LibraryLocation[] getDefaultLibraryLocations(final IFileHandle installLocation,
            EnvironmentVariable[] variables) {
        return getDefaultLibraryLocations(installLocation, variables, null);
    }

    public synchronized LibraryLocation[] getDefaultLibraryLocations(IFileHandle installLocation,
            EnvironmentVariable[] variables, IProgressMonitor monitor) {
        IPath modulesPath = installLocation.getFullPath().removeLastSegments(2);
        return new LibraryLocation[] { new LibraryLocation(modulesPath.append("modules")) };
    }

    public String getId() {
        return fId;
    }

    public IInterpreterInstall[] getInterpreterInstalls() {
        return fInterpreters.toArray(new IInterpreterInstall[fInterpreters.size()]);
    }

    public IStatus validateInstallLocation(IFileHandle installLocation) {
        if (!installLocation.exists()) {
            return createStatus(IStatus.ERROR, "Invalid Sausalito script location", null);
        }

        // when the request comes from the UI
        if (installLocation.isFile()) {
            return validatePossiblyName(installLocation);
        } else {
            IFileHandle handle = installLocation.getChild("bin").getChild(
                    "sausalito" + (Platform.getOS().equals(Platform.OS_WIN32) ? ".bat" : ""));
            IStatus status = validatePossiblyName(handle);
            if (status.getSeverity() == IStatus.OK) {
                installLocation = handle;
            }
            return status;
        }

    }

    public IStatus validatePossiblyName(IFileHandle installLocation) {
        final String name = installLocation.getName();
        IPath nPath = new Path(name);

        IExecutionEnvironment execEnv = (IExecutionEnvironment)installLocation.getEnvironment().getAdapter(
                IExecutionEnvironment.class);

        if (execEnv != null && execEnv.isValidExecutableAndEquals(INTERPRETER_NAME.toLowerCase(), nPath)) {
            return createStatus(IStatus.OK, "", null);
        } else {
            return createStatus(IStatus.ERROR, "Sausalito script was not found", null);
        }
    }

    public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
            throws CoreException {
        fId = config.getAttribute("id");

    }

    protected IStatus createStatus(int severity, String message, Throwable throwable) {
        return new Status(severity, getPluginId(), 0, message, throwable);
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fId == null) ? 0 : fId.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CoreSDKInstallType other = (CoreSDKInstallType)obj;
        if (fId == null) {
            if (other.fId != null) {
                return false;
            }
        } else if (!fId.equals(other.fId)) {
            return false;
        }
        return true;
    }

}
