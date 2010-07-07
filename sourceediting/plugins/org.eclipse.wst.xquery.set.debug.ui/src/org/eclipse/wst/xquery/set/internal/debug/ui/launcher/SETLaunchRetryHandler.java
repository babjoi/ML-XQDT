package org.eclipse.wst.xquery.set.internal.debug.ui.launcher;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xquery.set.internal.debug.ui.dialogs.BusySocketDialog;
import org.eclipse.wst.xquery.set.internal.launching.ISETLaunchRetryHandler;
import org.eclipse.wst.xquery.set.internal.launching.server.Server;

public class SETLaunchRetryHandler implements ISETLaunchRetryHandler {

    public boolean retry(IStatus status, final Server server) {
        final boolean[] result = { false };

        if (status != null && server != null) {
            Throwable exception = status.getException();
            if (exception != null) {
                if (exception.getMessage().contains("is already in use")) {
                    Display.getDefault().syncExec(new Runnable() {

                        public void run() {
                            Shell shell = Display.getDefault().getActiveShell();
                            BusySocketDialog dialog = new BusySocketDialog(shell, server);
                            if (dialog.open() == TrayDialog.OK) {
                                server.setHost(dialog.getHost());
                                server.setPort(dialog.getPort());
                                try {
                                    server.updateLaunchConfiguration();
                                    result[0] = true;
                                } catch (CoreException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }

                        }
                    });
                }
            }
        }
        return result[0];
    }

}
