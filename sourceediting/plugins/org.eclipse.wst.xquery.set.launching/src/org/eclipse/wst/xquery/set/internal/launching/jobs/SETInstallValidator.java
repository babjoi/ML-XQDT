package org.eclipse.wst.xquery.set.internal.launching.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.wst.xquery.set.launching.SETLaunchingPlugin;

public class SETInstallValidator implements IRunnableWithProgress {

    private IPath fScriptPath;
    private IStatus fResult;

    public SETInstallValidator(IPath path) {
        fScriptPath = path;
    }

    public void run(IProgressMonitor monitor) {
        monitor.beginTask("Verifying Sausalito CoreSDK installation", 2);

        IPath testPath = fScriptPath.removeLastSegments(1).append("uriresolver");
        if (Platform.getOS().equals(Platform.OS_WIN32)) {
            testPath = testPath.addFileExtension("exe");
        }

        List<String> testCmd = new ArrayList<String>();
        testCmd.add(testPath.toOSString());
        testCmd.add("-c");
        testCmd.add("http://www.28msec.com/");

        ProcessBuilder pb = new ProcessBuilder(testCmd);

        try {
            monitor.worked(1);
            monitor.setTaskName("Testing the Sausaito CoreSDK installation found...");

            Process p = pb.start();
            int error = p.waitFor();

            monitor.worked(1);

            if (error != 0) {
                fResult = log(IStatus.ERROR, "Sausalito CoreSDK not working properly.", null);
            }

            fResult = Status.OK_STATUS;
            return;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            fResult = log(IStatus.ERROR, "Sausalito CoreSDK not working properly.", ioe);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            fResult = log(IStatus.ERROR, "Sausalito CoreSDK not working properly.", ie);
        }

        monitor.done();
    }

    public static IStatus log(int severity, String message, Throwable t) {
        Status status = new Status(severity, SETLaunchingPlugin.PLUGIN_ID, message, t);
        SETLaunchingPlugin.log(status);
        return status;
    }

    public IStatus getResult() {
        return fResult;
    }
}
