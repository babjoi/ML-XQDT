/*******************************************************************************
 * Copyright (c) 2009 Mark Logic Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Neth (Mark Logic) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.launching.marklogic;

import java.io.IOException;

import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.debug.core.model.IStreamsProxy;

public class MockStreamsProxy implements IStreamsProxy {

    protected MockStreamMonitor outStreamMonitor = new MockStreamMonitor();
    protected MockStreamMonitor errStreamMonitor = new MockStreamMonitor();

    public IStreamMonitor getErrorStreamMonitor() {
        return errStreamMonitor;
    }

    public IStreamMonitor getOutputStreamMonitor() {
        return outStreamMonitor;
    }

    public void out(String text) {
        outStreamMonitor.write(text);
    }

    public void err(String text) {
        errStreamMonitor.write(text);
    }

    public void write(String input) throws IOException {
    }

}
