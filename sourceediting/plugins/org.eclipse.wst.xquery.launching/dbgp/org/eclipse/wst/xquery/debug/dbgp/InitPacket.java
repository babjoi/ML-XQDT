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

import java.net.URI;

public class InitPacket extends XmlElement {

    public InitPacket(String ideKey, long threadId, URI fileUri) {
        super("init");
        addAttribute("appid", "xqdt_debugger");
        addAttribute("idekey", ideKey);
        addAttribute("thread", "" + threadId);
        addAttribute("parent", "");
        addAttribute("language", "xquery");
        addAttribute("fileuri", fileUri.toString());
        addAttribute("protocol_version", "1.0");
    }

}
