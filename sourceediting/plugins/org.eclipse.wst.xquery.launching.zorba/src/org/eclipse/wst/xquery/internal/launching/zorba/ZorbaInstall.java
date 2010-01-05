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
package org.eclipse.wst.xquery.internal.launching.zorba;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.wst.xquery.core.semantic.ISemanticValidator;
import org.eclipse.wst.xquery.launching.ISemanticValidatingInterpreterInstall;
import org.eclipse.wst.xquery.launching.XQDTInterpreterInstall;

public class ZorbaInstall extends XQDTInterpreterInstall implements ISemanticValidatingInterpreterInstall {

    //private ZorbaBuiltinsHelper helper = new ZorbaBuiltinsHelper();

    public ZorbaInstall(IInterpreterInstallType type, String id) {
        super(type, id);
    }

//    @Override
//    public String getBuiltinModuleContent(String name) {
//        final Map<String, String> sources = helper.getSources();
//        return sources.get(name);
//    }

//    @Override
//    public String[] getBuiltinModules() {
//        final Map<String, String> sources = helper.getSources();
//        return sources.keySet().toArray(new String[sources.size()]);
//    }

    @Override
    public IInterpreterRunner getInterpreterRunner(String mode) {
        IInterpreterRunner runner = super.getInterpreterRunner(mode);

        if (runner != null) {
            return runner;
        }

        if (mode.equals(ILaunchManager.RUN_MODE)) {
            return new ZorbaRunner(this);
        }

        return null;
    }

    public ISemanticValidator getSemanticValidator() {
        return new ZorbaSemanticValidator(this);
    }

    @Override
    protected String getDisplayName(String module) {
        String[] splits = module.split("/");
        if (splits.length < 3) {
            return module;
        }

        String lastSegment = splits[splits.length - 1];
        if (lastSegment.endsWith(".xq")) {
            splits[splits.length - 1] = lastSegment.substring(0, lastSegment.lastIndexOf(".xq"));
        }

        StringBuffer result = new StringBuffer();
        result.append("http://");
        result.append(splits[2]);
        result.append(".");
        result.append(splits[1]);
        result.append(".");
        result.append(splits[0]);
        for (int i = 3; i < splits.length; i++) {
            result.append("/");
            result.append(splits[i]);
        }

        return result.toString();
    }
}
