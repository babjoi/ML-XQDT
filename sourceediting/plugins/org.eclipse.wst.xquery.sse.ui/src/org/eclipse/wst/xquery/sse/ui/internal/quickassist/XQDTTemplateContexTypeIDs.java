/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.sse.ui.internal.quickassist;

import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.wst.xquery.sse.ui.XQDTSSEUIPlugin;

/**
 * 
 * @author <a href="villard@us.ibm.com">Lionel Villard</a>
 */
public interface XQDTTemplateContexTypeIDs {

	ContextTypeRegistry REGISTRY = XQDTSSEUIPlugin.getDefault().getTemplateContextRegistry();

	// Atomic context type IDs

	String PROLOG1 = "org.eclipse.wst.xquery.sse.ui.contextType.prolog1";
	int PROLOG1_ID = 0;
	TemplateContextType PROLOG1_TCT = REGISTRY.getContextType(PROLOG1);

	String PROLOG2 = "org.eclipse.wst.xquery.sse.ui.contextType.prolog2";
	int PROLOG2_ID = 1;
	TemplateContextType PROLOG2_TCT = REGISTRY.getContextType(PROLOG2);

	String EXPR = "org.eclipse.wst.xquery.sse.ui.contextType.expr";
	TemplateContextType EXPR_TCT = REGISTRY.getContextType(EXPR);

	// Logical context type IDS (not in the registry)

	String PROLOG12 = "org.eclipse.wst.xquery.sse.ui.contextType.prolog12";
	TemplateContextType PROLOG12_TCT = new TemplateContextType(PROLOG12);

	String ALL = "org.eclipse.wst.xquery.sse.ui.contextType.all";
	TemplateContextType ALL_TCT = new TemplateContextType(ALL);
	
	String ALL_BUT_PROLOG1 = "org.eclipse.wst.xquery.sse.ui.contextType.allbutprolog1";
	TemplateContextType ALL_BUT_PROLOG1_TCT = new TemplateContextType(ALL_BUT_PROLOG1);


	String VERSIONDEL = "org.eclipse.wst.xquery.sse.ui.contextType.versiondecl";
	TemplateContextType VERSIONDECL_TCT = new TemplateContextType(VERSIONDEL);
}
