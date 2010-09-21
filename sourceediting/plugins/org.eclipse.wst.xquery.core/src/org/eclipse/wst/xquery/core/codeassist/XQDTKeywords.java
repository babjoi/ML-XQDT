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
/**
 * 
 */
package org.eclipse.wst.xquery.core.codeassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.core.utils.LanguageUtil;

public class XQDTKeywords implements IXQDTKeywords {

    public static String[] findByPrefix(String prefix, int languageLevel) {
        List<String> result = new ArrayList<String>();
        if (LanguageUtil.isLanguage(languageLevel, IXQDTLanguageConstants.LANGUAGE_XQUERY)) {
            for (int i = 0; i < KEYWORDS_XQUERY_11.length; i++) {
                if (KEYWORDS_XQUERY_11[i].startsWith(prefix)) {
                    result.add(KEYWORDS_XQUERY_11[i]);
                }
            }
            if (LanguageUtil.isLanguage(languageLevel, IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE)) {
                for (int i = 0; i < KEYWORDS_XQUERY_UPDATE.length; i++) {
                    if (KEYWORDS_XQUERY_UPDATE[i].startsWith(prefix)) {
                        result.add(KEYWORDS_XQUERY_UPDATE[i]);
                    }
                }
                if (LanguageUtil.isLanguage(languageLevel, IXQDTLanguageConstants.LANGUAGE_XQUERY_SCRIPTING)) {
                    for (int i = 0; i < KEYWORDS_XQUERY_SCRIPTING.length; i++) {
                        if (KEYWORDS_XQUERY_SCRIPTING[i].startsWith(prefix)) {
                            result.add(KEYWORDS_XQUERY_SCRIPTING[i]);
                        }
                    }
                }
            }
        }
        if (LanguageUtil.isLanguage(languageLevel, IXQDTLanguageConstants.LANGUAGE_XQUERY_ZORBA)) {
            for (int i = 0; i < KEYWORDS_ZORBA.length; i++) {
                if (KEYWORDS_ZORBA[i].startsWith(prefix)) {
                    result.add(KEYWORDS_ZORBA[i]);
                }
            }
        }

        return result.toArray(new String[result.size()]);
    }
}
