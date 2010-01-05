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

import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.wst.xquery.internal.core.XQDTContentType;

public class XQDTLanguageToolkit extends AbstractLanguageToolkit {

    protected static XQDTLanguageToolkit sToolkit = new XQDTLanguageToolkit();

    public String getLanguageContentType() {
        return XQDTContentType.XQUERY_CONTENT_TYPE;
    }

    public String getLanguageName() {
        return IXQDTConstants.LANGUAGE_NAME;
    }

    public String getNatureId() {
        return XQDTNature.NATURE_ID;
    }

    public static IDLTKLanguageToolkit getDefault() {
        return sToolkit;
    }

    public String getPreferenceQualifier() {
        return XQDTCorePlugin.PLUGIN_ID;
    }
}
