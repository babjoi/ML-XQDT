/*******************************************************************************
 * Copyright (c) 2008 by Buddhika Laknath <blaknath@gmail.com>  
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0, and is available at
 * http://www.eclipse.org/legal/epl-v10.html 
 *******************************************************************************/

package org.eclipse.wst.xquery.core.internal.regions;

public interface XQueryRegionContext {
	
	
	public static final String XQUERY_SPECIAL= "SPECIAL";
	public static final String XQUERY_KEYWORDS = "KEYWORDS";
	public static final String XQUERY_LEFT_CUR_BRACKET = "LEFT_CUR_BRACKET";
	public static final String XQUERY_RIGHT_CUR_BRACKET = "RIGHT_CUR_BRACKET";
	public static final String XQUERY_COMMENT = "COMMENT";
	public static final String XQUERY_TEXT = "TEXT";
	public static final String XQUERY_URL = "URL";	
	public static final String XQUERY_RIGHT_BRACKET = "RIGHT_BRACKET"; 
	public static final String XQUERY_LEFT_BRACKET = "LEFT_BRACKET"; 
	public static final String XQUERY_VARIABLE = "VARIABLE";
	public static final String XQUERY_VARIABLE_$ = "$"; 	
	public static final String XQUERY_UNDEFINED = "UNDEFINED"; 

}
