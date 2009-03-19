/*******************************************************************************
 * Copyright (c) 2009 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.cryptography;

import java.util.HashMap;

import javax.security.auth.x500.X500Principal;

/**
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class Principal {

    public static X500Principal generatePrincipal(HashMap<String, String> certificateData) {
        String data = "";

        for (String key : certificateData.keySet()) {
            String value = certificateData.get(key);

            if (!"".equals(value)) {
                data += key + "=" + value + ", ";
            }
        }

        if (data.endsWith(", ")) {
            data = data.substring(0, data.length() - 2);
        }

        return new X500Principal(data);
    }
}
