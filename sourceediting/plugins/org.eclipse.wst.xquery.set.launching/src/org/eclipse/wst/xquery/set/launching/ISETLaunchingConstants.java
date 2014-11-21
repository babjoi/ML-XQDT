/*******************************************************************************
 * Copyright (c) 2008 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.launching;

public interface ISETLaunchingConstants {

    public static final String SAUSALITO_SCRIPT_BASH = "sausalito";
    public static final String SAUSALITO_SCRIPT_BATCH = "sausalito.bat";

    public static final String SAUSALITO_ZORBA_EXECUTABLE_WIN = "zorba.exe";
    public static final String SAUSALITO_ZORBA_EXECUTABLE_NON_WIN = "zorba";

    public static final String SAUSALITO_EXECUTABLE_DIRECTORY = "bin";

    public static final String DEFAULT_CORE_SDK_NAME_PREFIX = "Sausalito CoreSDK ";
    public static final String DEFAULT_ZORBA_NAME = "Sausalito Zorba";
    public static final String DEFAULT_CORE_SDK_ID = "defaultCoreSDKInstall";
    public static final String DEFAULT_ZORBA_ID = "defaultZorbaInstall";

}
