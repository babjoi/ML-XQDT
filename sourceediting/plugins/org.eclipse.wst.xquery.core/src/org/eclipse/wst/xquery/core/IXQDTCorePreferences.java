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
package org.eclipse.wst.xquery.core;

public interface IXQDTCorePreferences {

    // XQuery language names
    public static final String LANGUAGE_NAME_XQUERY = "XQuery 1.1";
    public static final String LANGUAGE_NAME_XQUERY_UPDATE = "XQuery 1.1 + Update Facility";
    public static final String LANGUAGE_NAME_XQUERY_SCRIPTING = "XQuery 1.1 + Scripting Extensions";
    public static final String LANGUAGE_OPTION_NAME_FULTEXT = "Full Text";

    // XQuery language options
    public static final String LANGUAGE_LEVEL = "language.level";
    public static final String LANGUAGE_OPTION_USE_FULL_TEXT = "language.use_full_text";

    // XQuery URI Resolvers
    public static final String URI_RESOLVER_PREFERENCE_KEY = "uriResolver";

    // preference constants
    public static final String DEFAULT_INTERPRETER_FOUND = "defaultInterpreterFound";

}
