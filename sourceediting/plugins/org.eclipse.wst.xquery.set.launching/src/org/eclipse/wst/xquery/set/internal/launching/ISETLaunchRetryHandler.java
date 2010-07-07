package org.eclipse.wst.xquery.set.internal.launching;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.xquery.set.internal.launching.server.Server;

public interface ISETLaunchRetryHandler {

    public boolean retry(IStatus status, Server setver);

}
