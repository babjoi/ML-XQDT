/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.restrictions;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper;

/**
 * Class that groups the restricted access to
 * <code>org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper</code>.
 */
public class XQDTColorHelper extends ColorHelper {

    public static RGB toRGB(String anRGBString) {
        return org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper.toRGB(anRGBString);
    }

    public static String toRGBString(RGB anRGB) {
        return org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper.toRGBString(anRGB);
    }

    public static String[] unpackStylePreferences(String preference) {
        return org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper.unpackStylePreferences(preference);
    }

    public static String packStylePreferences(String[] stylePrefs) {
        return org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper.packStylePreferences(stylePrefs);
    }

}
