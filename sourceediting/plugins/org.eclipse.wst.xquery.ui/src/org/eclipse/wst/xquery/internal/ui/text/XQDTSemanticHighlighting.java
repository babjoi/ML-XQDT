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
package org.eclipse.wst.xquery.internal.ui.text;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;

public class XQDTSemanticHighlighting extends SemanticHighlighting {

    private final String preferenceKey;
    private final String displayName;
    private final boolean enablement;

    public XQDTSemanticHighlighting(String preferenceKey, String displayName) {
        this(preferenceKey, displayName, true);
    }

    public XQDTSemanticHighlighting(String preferenceKey, String displayName, boolean enablement) {
        Assert.isNotNull(preferenceKey);
        this.preferenceKey = preferenceKey;
        this.displayName = displayName;
        this.enablement = enablement;
    }

    @Override
    public String getPreferenceKey() {
        return preferenceKey;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean isSemanticOnly() {
        return displayName != null;
    }

    @Override
    public String getEnabledPreferenceKey() {
        return enablement ? super.getEnabledPreferenceKey() : null;
    }

    @Override
    public int hashCode() {
        return preferenceKey.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof XQDTSemanticHighlighting) {
            final XQDTSemanticHighlighting other = (XQDTSemanticHighlighting)obj;
            return preferenceKey.equals(other.preferenceKey);
        }
        return false;
    }
}
