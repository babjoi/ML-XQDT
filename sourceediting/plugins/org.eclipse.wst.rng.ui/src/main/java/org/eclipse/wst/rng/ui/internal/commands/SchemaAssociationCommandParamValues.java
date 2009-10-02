/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.ui.internal.commands;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.IParameterValues;

public class SchemaAssociationCommandParamValues implements IParameterValues {

	public Map<?, ?> getParameterValues() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Associate RELAX NG Schema", "new");
		map.put("Change RELAX NG Schema", "change");
		map.put("Remove RELAX NG Schema", "remove");
		return map;
	}

}
