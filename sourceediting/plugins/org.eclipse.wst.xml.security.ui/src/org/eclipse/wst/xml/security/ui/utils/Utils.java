/*******************************************************************************
 * Copyright (c) 2009 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.ui.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xml.security.ui.XSTUIPlugin;

/**
 * <p>Utility methods for the <b>XML Security Tools</b> UI plug-in.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class Utils {
    /**
     * Logs the given error message to the default error log using the <b>XML Security Tools</b> UI plug-in id.
     *
     * @param message The message to log
     * @param ex The exception log
     */
    public static void log(final String message, final Exception ex) {
        IStatus status = new Status(IStatus.ERROR, XSTUIPlugin.getDefault().getBundle().getSymbolicName(), 0, message, ex);
        XSTUIPlugin.getDefault().getLog().log(status);
    }

    /**
     * Shows an error dialog with an details button for detailed error information.
     *
     * @param title The title of the message box
     * @param message The message to display
     * @param ex The exception
     */
    public static void showErrorDialog(final Shell shell, final String title, final String message, final Exception ex) {
        String reason = ex.getMessage();
        if (reason == null || "".equals(reason)) { //$NON-NLS-1$
            reason = Messages.Utils_0;
        }

        IStatus status = new Status(IStatus.ERROR, XSTUIPlugin.getDefault().getBundle().getSymbolicName(), 0, reason, ex);

        ErrorDialog.openError(shell, title, message, status);
    }
}
