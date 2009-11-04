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
package org.eclipse.wst.xquery.debug.dbgp;

import org.eclipse.dltk.dbgp.DbgpRequest;
import org.eclipse.dltk.dbgp.exceptions.DbgpProtocolException;
import org.eclipse.dltk.dbgp.internal.utils.Base64Helper;

@SuppressWarnings("restriction")
public class DbgpRequestParser {

    public static DbgpRequest parse(String cmdStr) throws DbgpProtocolException {
        DbgpRequest request = null;

        if (!cmdStr.matches("(\\w+)\\s(.+)"))
            throw new DbgpProtocolException(ErrorMessages.DbgpError.COMMAND_PARSE_ERROR.toString());
        String[] parts = cmdStr.split("\\s", 2);

        request = new DbgpRequest(parts[0]);
        String[] args = parts[1].split("\\s");
        int argc = args.length;
        if (args[args.length - 2].equals("--")) {
            request.setData(Base64Helper.decodeString(args[args.length - 1]));
            argc = argc - 2;
        }

        // String[] args = parts[1].split("\\s");
        for (int i = 0; i < argc; i += 2) {
            request.addOption(args[i], args[i + 1]);
        }
        return request;
    }

}
