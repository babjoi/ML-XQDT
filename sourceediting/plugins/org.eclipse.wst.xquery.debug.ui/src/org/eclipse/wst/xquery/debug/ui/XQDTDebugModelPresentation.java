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
package org.eclipse.wst.xquery.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.ui.IEditorInput;
import org.eclipse.wst.xquery.ui.IXQDTUIPluginConstants;

public class XQDTDebugModelPresentation extends ScriptDebugModelPresentation {

    public String getEditorId(IEditorInput input, Object element) {
        String editorId = EditorUtility.getEditorID(input, element);
        if (editorId == null) {
            editorId = IXQDTUIPluginConstants.XQDT_EDITOR_ID;
        }

        return editorId;
    }

}
