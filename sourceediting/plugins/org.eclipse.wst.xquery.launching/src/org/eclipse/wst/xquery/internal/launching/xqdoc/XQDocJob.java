/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William Candillon (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.launching.xqdoc;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.launching.xqdoc.AbstractXQDocRuntime;

public class XQDocJob extends Job {

    private AbstractXQDocRuntime fRuntime;

    public XQDocJob(AbstractXQDocRuntime runtime) {
        super("Generating XQuery documentation");
        fRuntime = runtime;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        try {
            fRuntime.run();
        } catch (Exception e) {
            e.printStackTrace();
            return Status.CANCEL_STATUS;
        }

        return Status.OK_STATUS;
    }
}
