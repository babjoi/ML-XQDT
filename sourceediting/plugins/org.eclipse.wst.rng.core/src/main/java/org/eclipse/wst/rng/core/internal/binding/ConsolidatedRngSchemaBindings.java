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

import java.util.HashSet;
import java.util.Set;

public enum ConsolidatedRngSchemaBindings implements IRngSchemaBindingSet {
	INSTANCE;
	
	private UserSchemaBindings userBindings = new UserSchemaBindings();
	
	private PluginSchemaBindings pluginBindings = PluginSchemaBindings.INSTANCE;
	
	// @Override
	public Set<RngSchemaBinding> getBindings() {
		Set<RngSchemaBinding> consolidated = new HashSet<RngSchemaBinding>();
		consolidated.addAll(userBindings.getBindings());
		consolidated.addAll(pluginBindings.getBindings());
		
		return consolidated;
	}
	
	// Override
	public boolean contains(String namespace) {
		return userBindings.contains(namespace) || pluginBindings.contains(namespace);
	}
	
	// Override
	public RngSchemaBinding get(String namespace) {
		RngSchemaBinding binding = userBindings.get(namespace);
		if (binding == null) {
			return pluginBindings.get(namespace);
		} else {
			return binding;
		}
	}
	
	public void reloadWorkingUserBindings() {
		userBindings.reload();
	}
}
