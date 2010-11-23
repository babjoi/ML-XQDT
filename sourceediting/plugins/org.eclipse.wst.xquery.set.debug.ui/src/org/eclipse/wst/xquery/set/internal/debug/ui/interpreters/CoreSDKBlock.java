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
package org.eclipse.wst.xquery.set.internal.debug.ui.interpreters;

import org.eclipse.wst.xquery.internal.debug.ui.interpreters.XQDTInterpretersBlock;
import org.eclipse.wst.xquery.set.core.SETNature;

public class CoreSDKBlock extends XQDTInterpretersBlock {

    @Override
    protected String getCurrentNature() {
        return SETNature.NATURE_ID;
    }

}
