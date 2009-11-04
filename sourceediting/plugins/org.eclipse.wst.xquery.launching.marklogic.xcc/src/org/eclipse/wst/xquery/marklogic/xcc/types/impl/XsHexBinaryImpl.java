/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.xcc.types.impl;

import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSHexBinary;

public class XsHexBinaryImpl extends AbstractStringItem implements XSHexBinary {
    public XsHexBinaryImpl(String bodyString) {
        super(ValueType.XS_HEX_BINARY, bodyString);

        validateHex(bodyString);
    }

    public byte[] asBinaryData() {
        return (convertHexToBinary(asString()));
    }

    // ---------------------------------------------------------

    private void validateHex(String bodyString) {
        int len = bodyString.length();

        for (int i = 0; i < len; i++) {
            char c = Character.toLowerCase(bodyString.charAt(i));

            if (Character.isDigit(c))
                continue;

            if ((c >= 'a') && (c <= 'f')) {
                continue;
            }

            throw new IllegalArgumentException("Illegal character in hex string: '" + c + "', index=" + i);
        }
    }

    private byte[] convertHexToBinary(String hex) {
        int strLen = hex.length();
        byte[] binary = new byte[(strLen + 1) / 2];
        int binIdx = 0;

        for (int currIdx = 0; currIdx < strLen; currIdx += 2) {
            char hi = hex.charAt(currIdx);
            char lo = (currIdx < (strLen - 1)) ? hex.charAt(currIdx + 1) : '0';
            int val = (Character.digit(hi, 16) << 4) | Character.digit(lo, 16);

            binary[binIdx++] = (byte)(val & 0xff);
        }

        return (binary);
    }
}
