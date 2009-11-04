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
package org.eclipse.wst.xquery.launching;

import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;
import org.eclipse.dltk.launching.sourcelookup.DBGPSourceLookupParticipant;
import org.eclipse.dltk.launching.sourcelookup.ScriptSourceLookupDirector;
import org.eclipse.dltk.launching.sourcelookup.ScriptSourceLookupParticipant;

public class XQDTSourceLookupDirector extends ScriptSourceLookupDirector {

    /*
     * @see org.eclipse.debug.core.sourcelookup.ISourceLookupDirector#initializeParticipants()
     */
    public void initializeParticipants() {
        addParticipants(new ISourceLookupParticipant[] { new DBGPSourceLookupParticipant() });
        addParticipants(new ISourceLookupParticipant[] { new ScriptSourceLookupParticipant() });

        // TODO: This should be the final version of the source lookup when
        // Zorba will be able to handle only namespace URI's
        // addParticipants(new ISourceLookupParticipant[] { new XQDTModuleSourceLookupParticipant()
        // });
    }
}
