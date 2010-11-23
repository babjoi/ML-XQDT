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
package org.eclipse.wst.xquery.set.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.ScriptInterpreterPreferencePage;
import org.eclipse.wst.xquery.set.ui.ISETUIConstants;

public class SETCoreSDKPreferencePage extends ScriptInterpreterPreferencePage {

    public static final String PAGE_ID = ISETUIConstants.ID_INTEREPRTERS_PREFERENCE_PAGE;

    @Override
    public InterpretersBlock createInterpretersBlock() {
        return new SETCoreSDKBlock();
    }

}
