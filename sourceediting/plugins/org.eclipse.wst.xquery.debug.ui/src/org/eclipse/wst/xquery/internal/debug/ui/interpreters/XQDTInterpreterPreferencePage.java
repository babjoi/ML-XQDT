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
package org.eclipse.wst.xquery.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.ScriptInterpreterPreferencePage;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;

public class XQDTInterpreterPreferencePage extends ScriptInterpreterPreferencePage {

    public static final String PAGE_ID = "org.eclipse.wst.xquery.preferences.interpreters"; //$NON-NLS-1$

    public InterpretersBlock createInterpretersBlock() {
        return new XQDTInterpretersBlock();
    }
}
