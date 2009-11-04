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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.dbgp.internal.utils.Base64Helper;

@SuppressWarnings("restriction")
public abstract class XmlElement {

    private static final String PACKET_WITH_DATA_TEMPLATE = "<%1$s%2$s>%3$s</%1$s>";
    private static final String PACKET_WITHOUT_DATA_TEMPLATE = "<%1$s%2$s/>";

    private String fName;
    private Map<String, String> fAttributes = new HashMap<String, String>();
    private String fData;
    private boolean fEncodeData = true;

    public XmlElement(String name) {
        fName = name;
    }

    public void addAttribute(String name, String value) {
        fAttributes.put(name, value);
    }

    public String getAttribute(String name) {
        return fAttributes.get(name);
    }

    public void removeAttribute(String name) {
        fAttributes.remove(name);
    }

    public String getData() {
        return fData;
    }

    public void setData(String data) {
        setData(data, false);
    }

    public void setData(String data, boolean encode) {
        fData = data;
        fEncodeData = encode;
    }

    public String toXml() {
        if (fData != null) {
            return String.format(PACKET_WITH_DATA_TEMPLATE, fName, attributesToXml(fAttributes), prepareData(fData));
        }
        return String.format(PACKET_WITHOUT_DATA_TEMPLATE, fName, attributesToXml(fAttributes));
    }

    @Override
    public String toString() {
        return toXml();
    }

    private String attributesToXml(Map<String, String> attributes) {
        StringBuffer sb = new StringBuffer();
        for (String name : attributes.keySet()) {
            sb.append(" " + name + "='" + attributes.get(name) + "'");
        }
        return sb.toString();
    }

    private String prepareData(String fData) {
        if (fEncodeData) {
            String encoded = Base64Helper.encodeString(fData);
            return "<![CDATA[" + encoded + "]]>";
        }
        return fData;
    }
}
