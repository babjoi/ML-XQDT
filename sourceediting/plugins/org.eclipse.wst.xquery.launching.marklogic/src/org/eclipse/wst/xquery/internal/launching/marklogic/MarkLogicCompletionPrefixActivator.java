/*******************************************************************************
 * Copyright (c) 2009 Mark Logic Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sam Neth (Mark Logic) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.internal.launching.marklogic;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.internal.core.codeassist.IImplicitImportActivator;
import org.eclipse.wst.xquery.internal.core.utils.LanguageUtil;

public class MarkLogicCompletionPrefixActivator implements IImplicitImportActivator {

    /* (non-Javadoc)
     * @see org.eclipse.wst.xquery.internal.launching.marklogic.IImplicitImportActivator#activateForModule(org.eclipse.dltk.core.ISourceModule)
     */
    public boolean activateForModule(ISourceModule module) {
        int languageLevel = LanguageUtil.getLanguageLevel(module);
        return (languageLevel & IXQDTLanguageConstants.LANGUAGE_XQUERY_MARK_LOGIC) == IXQDTLanguageConstants.LANGUAGE_XQUERY_MARK_LOGIC;
    }
}
