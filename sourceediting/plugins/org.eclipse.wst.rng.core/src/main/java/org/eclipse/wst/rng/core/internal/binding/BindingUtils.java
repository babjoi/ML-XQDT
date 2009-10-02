/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.core.internal.binding;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.wst.rng.contentassist.IRngSchema.RngSchemaSyntax;
import org.eclipse.wst.rng.core.internal.RngConstants;
import org.eclipse.wst.rng.core.internal.UriUtil;


public final class BindingUtils {
	private BindingUtils() {}
	
	public static Map<String, String> parseBindingProcessingInstructionData(String data) {
		Map<String, String> attMap = new HashMap<String, String>();
		String[] attNames = {
				RngConstants.BINDING_INSTRUCTION_URI_PSEUDO_ATT_NAME,
				RngConstants.BINDING_INSTRUCTION_TYPE_PSEUDO_ATT_NAME,
				RngConstants.BINDING_INSTRUCTION_MODE_PSEUDO_ATT_NAME};
		
		for (int i = 0; i < attNames.length; i++) {
			String attName = attNames[i];
			Pattern attValuePattern = Pattern.compile(attName + "=\\\"([^\\\"]+)\\\"");
			Matcher attValueMatcher = attValuePattern.matcher(data);
			if (attValueMatcher.find()) {
				String attVal = attValueMatcher.group(1);
				attMap.put(attName, attVal);
			}	
		}
		
		return attMap;
	}
	
	public static String convertSchemaSyntaxToMimeType(RngSchemaSyntax schemaSyntax) {
		switch (schemaSyntax) {
		case XML:
			return RngConstants.MIME_XML1;
		case COMPACT:
			return RngConstants.MIME_RNC;
		}
		return null; // never happens
	}
	
	public static RngSchemaSyntax convertMimeTypeToSchemaSyntax(String mimeType) {
		if (RngConstants.MIME_XML1.equals(mimeType) || RngConstants.MIME_XML2.equals(mimeType)) {
			return RngSchemaSyntax.XML;
		} else if (RngConstants.MIME_RNC.equals(mimeType)) {
			return RngSchemaSyntax.COMPACT;
		} else {
			return null;
		}
	}
	
	public static RngSchemaSyntax guessSchemaSyntax(String schemaUri) {
		if (UriUtil.resourceExists(schemaUri)) {
			try {
				return guessSchemaSyntax(schemaUri, UriUtil.openResource(schemaUri));
			} catch (IOException e) {
				return null;
			}
		}
		return null;
	}
	
	public static RngSchemaSyntax guessSchemaSyntax(String schemaUri, InputStream schemaInputStream) {
		try {
			IContentType[] cTypes = Platform.getContentTypeManager().findContentTypesFor(schemaInputStream, schemaUri);
			for (int i = 0; i < cTypes.length; i++) {
				IContentType cType = cTypes[i];
				if (RngConstants.CONTENT_TYPE_RNG_SCHEMA.equals(cType.getId()) ||
						RngConstants.CONTENT_TYPE_XML.equals(cType.getId())) {
					return RngSchemaSyntax.XML;
				} else if (RngConstants.CONTENT_TYPE_RNC_SCHEMA.equals(cType.getId())) {
					return RngSchemaSyntax.COMPACT; 
				}
			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}
