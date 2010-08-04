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
package org.eclipse.wst.xquery.set.core;

import java.net.URI;

public class SETProjectConfig {

    private URI fLogicalUri;
    private String fStartPage;
    private String fVersion;
    private String fApiVersion;

    public SETProjectConfig() {
    }

    public SETProjectConfig(URI logicalUri, String startPage, String version, String apiVersion) {
        fLogicalUri = logicalUri;
        fStartPage = startPage;
        fVersion = version;
        fApiVersion = apiVersion;
    }

    public URI getLogicalUri() {
        return fLogicalUri;
    }

    public void setLogicalUri(URI logicalUri) {
        fLogicalUri = logicalUri;
    }

    public String getStartPage() {
        return fStartPage;
    }

    public void setStartPage(String startPage) {
        fStartPage = startPage;
    }

    public String getVersion() {
        return fVersion;
    }

    public void setVersion(String version) {
        fVersion = version;
    }

    public String getApiVersion() {
        return fApiVersion;
    }

    public void setApiVersion(String apiVersion) {
        fApiVersion = apiVersion;
    }

}
