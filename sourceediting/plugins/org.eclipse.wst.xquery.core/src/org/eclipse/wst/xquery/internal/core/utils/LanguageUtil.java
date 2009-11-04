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
package org.eclipse.wst.xquery.internal.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.wst.xquery.core.IXQDTCorePreferences;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.core.XQDTCorePlugin;
import org.eclipse.wst.xquery.core.model.ast.XQueryModule;

public class LanguageUtil {

    private static final Pattern versionDecl = Pattern.compile("^xquery version \"(.*)\".*$", Pattern.MULTILINE);

    public static int getLanguageLevel(ISourceModule module) {
        try {
            XQueryModule xqModule = (XQueryModule)SourceParserUtil.getModuleDeclaration(module);
            xqModule.getVersion();
            int languageLevel = getDeclaredLanguageLevel(module.getSource());
            if (languageLevel >= 0) {
                return languageLevel;
            }
        } catch (ModelException e) {
            e.printStackTrace();
        }

        return getLanguageLevel(module.getScriptProject().getProject());
    }

    public static int getDeclaredLanguageLevel(String source) {
        if (source != null) {
            Matcher matcher = versionDecl.matcher(source.split("\n")[0]);
            if (matcher.matches()) {
                String version = matcher.group(1);
                if (version.equals("1.0-ml")) {
                    return IXQDTLanguageConstants.LANGUAGE_XQUERY_MARK_LOGIC;
                }
            }
        }

        return -1;
    }

    public static int getLanguageLevel(IFile file, char source[]) {
        int newline = 0;

        while ((newline < source.length) && (newline < 1000) & (source[newline] != '\n')) {
            ++newline;
        }

        int languageLevel = getDeclaredLanguageLevel(new String(source, 0, newline));

        if (languageLevel >= 0) {
            return languageLevel;
        } else {
            return getLanguageLevel(file.getProject());
        }
    }

    /*
     * Not used, safe only once it first looks for open buffers to evaluate.
     * 
     * public static int getLanguageLevel(IFile file) { try { BufferedReader reader = new
     * BufferedReader(new InputStreamReader(file.getContents()));
     * 
     * try { int languageLevel = getDeclaredLanguageLevel(reader.readLine());
     * 
     * if (languageLevel >= 0) { return languageLevel; } } finally { reader.close(); } } catch
     * (IOException e) { e.printStackTrace(); } catch (CoreException e) { e.printStackTrace(); }
     * 
     * return getLanguageLevel(file.getProject()); }
     */

    public static int getLanguageLevel(IProject project) {
        PreferencesLookupDelegate delegate = new PreferencesLookupDelegate(project);

        String language = delegate.getString(XQDTCorePlugin.PLUGIN_ID, IXQDTCorePreferences.LANGUAGE_LEVEL);
        boolean fulltext = delegate.getBoolean(XQDTCorePlugin.PLUGIN_ID,
                IXQDTCorePreferences.LANGUAGE_OPTION_USE_FULL_TEXT);

        int mask = 0;
        if (language.equals(IXQDTCorePreferences.LANGUAGE_NAME_XQUERY_UPDATE)) {
            mask = IXQDTLanguageConstants.LANGUAGE_XQUERY_UPDATE;
        } else if (language.equals(IXQDTCorePreferences.LANGUAGE_NAME_XQUERY_SCRIPTING)) {
            mask = IXQDTLanguageConstants.LANGUAGE_XQUERY_SCRIPTING;
        }
        if (fulltext) {
            mask |= IXQDTLanguageConstants.LANGUAGE_XQUERY_FULLTEXT;
        }

        mask |= IXQDTLanguageConstants.LANGUAGE_XQUERY_ZORBA;

        return mask;
    }

    public static boolean hasProjectLanguage(IProject project, int targetLanguageLevel) {
        int currentLanguageLevel = getLanguageLevel(project);
        return ((currentLanguageLevel & targetLanguageLevel) == targetLanguageLevel);
    }

    public static boolean isLanguage(int currentLanguageLevel, int targetLanguageLevel) {
        return ((currentLanguageLevel & targetLanguageLevel) == targetLanguageLevel);
    }

    // public static boolean isLanguageIntersecting(byte currentLanguageLevel,
    // byte targetLanguageLevel) {
    // return (currentLanguageLevel == 0 || (currentLanguageLevel &
    // targetLanguageLevel) != 0);
    // }
    //    
    // public static boolean isXQueryOnly(byte currentLanguageLevel) {
    // return currentLanguageLevel == XPath.LANGUAGE_XQUERY;
    // }
    //
    // public static boolean isXQueryUpdate(byte currentLanguageLevel) {
    // return isLanguage(currentLanguageLevel, XPath.LANGUAGE_XQUERY_UPDATE);
    // }
    //
    // public static boolean isXQueryFulltext(byte currentLanguageLevel) {
    // return isLanguage(currentLanguageLevel, XPath.LANGUAGE_XQUERY_FULLTEXT);
    // }
    //
    // public static boolean isXQueryScripting(byte currentLanguageLevel) {
    // return isLanguage(currentLanguageLevel, XPath.LANGUAGE_XQUERY_SCRIPTING);
    // }
    //
    // public static byte getLanguageFlagForPreferences(String name, boolean
    // isFulltext) {
    // byte fulltext = (isFulltext ? XPath.LANGUAGE_XQUERY_FULLTEXT : 0);
    // if (name.equals("XQuery"))
    // return (byte)(XPath.LANGUAGE_XQUERY | fulltext);
    // if (name.equals("XQuery Update"))
    // return (byte)(XPath.LANGUAGE_XQUERY_UPDATE | fulltext);
    //
    // return (byte)(XPath.LANGUAGE_XQUERY_SCRIPTING | fulltext);
    // }
    //    
    // public static boolean hasProjectLanguageContext(IProject project, String
    // contextID) {
    // byte projectLanguageLevel =
    // XQueryCorePlugin.getProjectASTParserOptions(project).getLanguageFlag();
    // return isLanguage(projectLanguageLevel,
    // getLanguageFlagForContextId(contextID));
    // }
    //    
    // public static byte getLanguageFlagForContextId(String contextID) {
    // if (contextID.equals("xquery"))
    // return XPath.LANGUAGE_XQUERY;
    // if (contextID.equals("update"))
    // return XPath.LANGUAGE_XQUERY_UPDATE;
    // if (contextID.equals("fulltext"))
    // return XPath.LANGUAGE_XQUERY_FULLTEXT;
    // if (contextID.equals("scripting"))
    // return XPath.LANGUAGE_XQUERY_SCRIPTING;
    //
    // return XPath.LANGUAGE_XQUERY;
    // }
    //    
    // public static byte getProjectLanguageTypeFlag(IProject project) {
    // return
    // XQueryCorePlugin.getProjectASTParserOptions(project).getLanguageFlag();
    // }
    //    
    // public static byte setFulltextForLanguage(byte currentLevel, boolean
    // isFulltext) {
    // if (isFulltext)
    // return (byte)(currentLevel | XPath.LANGUAGE_XQUERY_FULLTEXT);
    // else
    // return (byte)(currentLevel & ~XPath.LANGUAGE_XQUERY_FULLTEXT);
    // }

}