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
package org.eclipse.wst.xquery.internal.ui.text;

import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTContentAssistPreference extends ContentAssistPreference {

    private static XQDTContentAssistPreference instance;

    public static ContentAssistPreference getDefault() {
        if (instance == null) {
            instance = new XQDTContentAssistPreference();
        }
        return instance;
    }

    protected ScriptTextTools getTextTools() {
        return XQDTUIPlugin.getDefault().getTextTools();
    }

}
