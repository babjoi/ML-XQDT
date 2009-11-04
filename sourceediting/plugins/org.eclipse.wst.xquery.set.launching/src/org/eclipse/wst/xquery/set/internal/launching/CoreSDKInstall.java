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
package org.eclipse.wst.xquery.set.internal.launching;

import java.util.Map;

import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.wst.xquery.core.semantic.ISemanticValidator;
import org.eclipse.wst.xquery.internal.launching.zorba.ZorbaBuiltinsHelper;
import org.eclipse.wst.xquery.launching.ISemanticValidatingInterpreterInstall;
import org.eclipse.wst.xquery.set.core.SETNature;

public class CoreSDKInstall extends AbstractInterpreterInstall implements ISemanticValidatingInterpreterInstall {

    private ZorbaBuiltinsHelper fBuiltinHelper = new ZorbaBuiltinsHelper();

    public CoreSDKInstall(IInterpreterInstallType type, String id) {
        super(type, id);
    }

    public String getNatureId() {
        return SETNature.NATURE_ID;
    }

    @Override
    public String getBuiltinModuleContent(String name) {
        final Map<String, String> sources = fBuiltinHelper.getSources();
        return sources.get(name);
    }

    @Override
    public String[] getBuiltinModules() {
        final Map<String, String> sources = fBuiltinHelper.getSources();
        return sources.keySet().toArray(new String[sources.size()]);
    }

    public ISemanticValidator getSemanticValidator() {
        return new CoreSDKSemanticValidator(this);
    }
}
