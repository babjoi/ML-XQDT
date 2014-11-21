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
package org.eclipse.wst.xquery.debug.core;

import java.util.Arrays;

import org.eclipse.dltk.debug.core.model.ComplexScriptType;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptTypeFactory;
import org.eclipse.wst.xquery.debug.core.model.XQDTSequenceType;
import org.eclipse.wst.xquery.debug.core.model.XQDTXSAtomicType;

public class XQDTTypeFactory implements IScriptTypeFactory {

    private static final String[] simpleTypes = { "ENTITIES", "ENTITY", "ID", "IDREF", "IDREFS", "NCName", "NMTOKEN",
            "NMTOKENS", "NOTATION", "Name", "QName", "anyAtomicType", "anySimpleType", "anyType", "anyURI",
            "base64Binary", "boolean", "byte", "date", "dateTime", "dayTimeDuration", "decimal", "double", "duration",
            "float", "gDay", "gMonth", "gMonthDay", "gYear", "gYearMonth", "hexBinary", "int", "integer", "language",
            "long", "negativeInteger", "nonNegativeInteger", "nonPositiveInteger", "normalizedString",
            "positiveInteger", "short", "string", "time", "token", "unsignedByte", "unsignedInt", "unsignedShort",
            "unsignedLong", "untyped", "untypedAtomic", "yearMonthDuration" };

    public IScriptType buildType(String typeString) {
        String type = typeString;
        if (typeString.endsWith("*") || typeString.endsWith("+")) {
            return new XQDTSequenceType(typeString);
        } else if (typeString.endsWith("?")) {
            type = typeString.substring(0, typeString.length() - 1);
        }

        // this means we are handling an item or node type
        if (type.contains("(") || !type.contains(":")) {
            return new ComplexScriptType(typeString);
        }

        int columnIndex = type.indexOf(':');
        String prefix = type.substring(0, columnIndex);
        if (!"xs".equals(prefix)) {
            return new ComplexScriptType(typeString);
        }

        String localName = type.substring(columnIndex + 1);
        int index = Arrays.binarySearch(simpleTypes, localName);
        if (index >= 0) {
            return new XQDTXSAtomicType(typeString);
        }

        return new ComplexScriptType(typeString);
    }

}
