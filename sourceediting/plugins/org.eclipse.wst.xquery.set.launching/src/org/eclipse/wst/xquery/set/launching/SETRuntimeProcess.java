package org.eclipse.wst.xquery.set.launching;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerManager;

public class SETRuntimeProcess extends RuntimeProcess {

    @SuppressWarnings("unchecked")
    public SETRuntimeProcess(ILaunch launch, Process process, String name, Map attributes) {
        super(launch, process, name, attributes);

    }

    public void terminate() throws DebugException {
        try {
            ILaunchConfiguration config = getLaunch().getLaunchConfiguration();
            IProject project = SETLaunchConfigurationDelegate.getProject(config);
            ServerManager.getInstance().stopServer(project);
        } catch (CoreException ce) {
            throw new DebugException(new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                    "Could not stop the running sausalito server.", ce));
        }
        super.terminate();
    }

}
