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

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wst.rng.contentassist.IRngSchema.RngSchemaSyntax;


public enum PluginSchemaBindings implements IRngSchemaBindingSet {
	INSTANCE;	
	
	private static final String EXTENSION_POINT_ID = "org.eclipse.wst.rng.core.schemaBinding";
	
	private final Map<String, RngSchemaBinding> bindings = new HashMap<String, RngSchemaBinding>();
	
	private PluginSchemaBindings() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		
		for (IConfigurationElement cfgElement : config) {
			try {
				String namespace = cfgElement.getAttribute("namespace");
				URI schemaUri = new URI(cfgElement.getAttribute("schema-uri"));
				
				if (schemaUri.getScheme() == null) {
					schemaUri = new URI("platform:/plugin/" + cfgElement.getContributor().getName() + "/" + schemaUri.toString());
				}
				
				RngSchemaSyntax schemaSyntax = RngSchemaSyntax.valueOf(cfgElement.getAttribute("schema-syntax"));
				bindings.put(namespace, new RngSchemaBinding(namespace, schemaUri, schemaSyntax));
			} catch (Exception e) {
				continue;
			}
		} 
	}
	
	// Override
	public Set<RngSchemaBinding> getBindings() {
		return new HashSet<RngSchemaBinding>(bindings.values());
	}
	
	// Override
	public boolean contains(String namespace) {
		return bindings.containsKey(namespace);
	}
	
	// Override
	public RngSchemaBinding get(String namespace) {
		return bindings.get(namespace);
	}
}
