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
import java.util.List;

import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;

public class XQDTJavaInterpreterRunner extends AbstractInterpreterRunner {

    private static final String JAVA_EXEC = "java";
    private static final String JAVA_CP_ARG = "-cp";

    protected XQDTJavaInterpreterRunner(IInterpreterInstall install) {
        super(install);
    }

    protected String[] renderCommandLine(InterpreterConfig config) {
        IInterpreterInstall interpreter = getInstall();

        List<String> interpreterOwnArgs = new ArrayList<String>();
        for (String arg : interpreter.getInterpreterArguments()) {
            interpreterOwnArgs.add(arg);
        }
        // remove the main class argument
        String mainClass = interpreterOwnArgs.remove(0);

        // start building the command line
        final List<String> items = new ArrayList<String>();

        // add the java executable and classpath option
        items.add(JAVA_EXEC);
        items.add(JAVA_CP_ARG);

        // add the path to the jar file 
        items.add(interpreter.getInstallLocation().toOSString());
        // add the main class 
        items.add(mainClass);

        // add the launch config interpreter arguments
        for (Object arg : config.getInterpreterArgs()) {
            items.add((String)arg);
        }

        // add the interpreter install arguments
        if (interpreterOwnArgs != null) {
            items.addAll(interpreterOwnArgs);
        }

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
