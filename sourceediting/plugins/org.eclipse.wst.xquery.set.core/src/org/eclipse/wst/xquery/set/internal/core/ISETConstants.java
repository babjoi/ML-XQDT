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
package org.eclipse.wst.xquery.set.internal.core;

import org.eclipse.wst.xquery.internal.launching.zorba.IZorbaConstants;

public interface ISETConstants extends IZorbaConstants {

    // TODO: create special code to contribute such modules
    public static final String SAUSALITO_MODULE_PREFIX = "http://www.28msec.com/modules/";

    // names of the output console started by SETCoreSDKCommandAction 
    public static final String SAUSALITO_COMMAND_IMORT_DATA = "Import data";
    public static final String SAUSALITO_COMMAND_DELETE_DATA = "Delete data";

}
