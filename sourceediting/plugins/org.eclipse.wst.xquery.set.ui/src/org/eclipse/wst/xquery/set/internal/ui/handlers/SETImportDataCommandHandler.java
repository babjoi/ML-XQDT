/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.ui.handlers;

import java.io.OutputStream;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.wst.xquery.set.internal.launching.jobs.SETDeleteDataJob;

public class SETImportDataCommandHandler extends SETCoreSDKCommandHandler {

    protected String getLabel() {
        return "Delete Data";
    }

    protected Job createHandlerJob(OutputStream output) {
        return new SETDeleteDataJob(getProject(), output);
    }
}
