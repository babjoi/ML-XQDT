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
package org.eclipse.wst.xquery.set.internal.core.parser;

import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.wst.xquery.core.IXQDTLanguageConstants;
import org.eclipse.wst.xquery.internal.core.parser.XQDTSourceParser;
import org.eclipse.wst.xquery.internal.core.parser.antlr.XQueryParser;

public class SETSourceParser extends XQDTSourceParser {

    @Override
    protected XQueryParser prepareParser(String fileName, char[] source, IProblemReporter reporter) {
        XQueryParser parser = super.prepareParser(fileName, source, reporter);
        parser.setLanguageLevel(IXQDTLanguageConstants.LANGUAGE_XQUERY_SCRIPTING);
        return parser;
    }

}