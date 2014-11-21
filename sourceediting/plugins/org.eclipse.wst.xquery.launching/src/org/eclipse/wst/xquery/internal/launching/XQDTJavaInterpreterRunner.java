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
package org.eclipse.wst.xquery.internal.launching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.InterpreterConfig;

public class XQDTJavaInterpreterRunner extends AbstractInterpreterRunner {

    private static final String JAVA_EXEC = "java";
    private static final String JAVA_CP_ARG = "-cp";

    protected XQDTJavaInterpreterRunner(XQDTJavaInterpreterInstall install) {
        super(install);
    }

    protected String[] renderCommandLine(InterpreterConfig config) {
        // it must be of Java type since it was passed through the constructor
        XQDTJavaInterpreterInstall interpreter = (XQDTJavaInterpreterInstall)getInstall();

        // start building the command line
        final List<String> items = new ArrayList<String>();

        // add the java executable and classpath option
        items.add(JAVA_EXEC);
        items.addAll(Arrays.asList(interpreter.getJavaArgs().split(" ")));

        // add the path to the jar file 
        items.add(JAVA_CP_ARG);
        items.add(interpreter.getInstallLocation().toOSString());

        // add the main class 
        items.add(interpreter.getMainClass());

        // add the launch config interpreter arguments
        for (Object arg : config.getInterpreterArgs()) {
            items.add((String)arg);
        }

        // add the interpreter install arguments
        items.addAll(Arrays.asList(interpreter.getJavaInterpreterArgs().split(" ")));

        String queryFile = interpreter.getEnvironment().convertPathToString(config.getScriptFilePath());

        // see if the ${query_file} variable was used, and replace it script file path
        boolean foundQueryVar = false;
        for (int i = 0; i < items.size(); i++) {
            String argItem = items.get(i);
            if (argItem.contains("${query_file}")) {
                items.set(i, argItem.replace("${query_file}", queryFile));
                for (Object arg : config.getScriptArgs()) {
                    items.add((String)arg);
                }
                foundQueryVar = true;
            }
        }

        // if the ${query_file} was not found, add now the script file path
        if (!foundQueryVar) {
            items.add(queryFile);
            for (Object arg : config.getScriptArgs()) {
                items.add((String)arg);
            }
        }

        return items.toArray(new String[items.size()]);
    }
}
