/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.ui.internal.preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.rng.contentassist.IRngSchema.RngSchemaSyntax;
import org.eclipse.wst.rng.core.internal.binding.RngSchemaBinding;


class RngBindingDetailsView {
	private final Text text;
	
	RngBindingDetailsView(Composite parent) {		
		Color backgroundColor = parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
		
		text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		GridData data = new GridData(GridData.FILL_BOTH);
		data.heightHint = 55;
		text.setLayoutData(data);
		
		text.setEditable(false);
		text.setBackground(backgroundColor);
	}
	
	public void display(RngSchemaBinding schemaBinding) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Namespace: ");
		sb.append("\t");
		sb.append(schemaBinding.getNamespace());
		sb.append("\n");
		
		sb.append("Schema URI: ");
		sb.append("\t");
		sb.append(schemaBinding.getSchemaUri());
		sb.append("\n");
		
		sb.append("Syntax: ");
		sb.append("\t\t\t");
		sb.append(schemaBinding.getSchemaSyntax() == RngSchemaSyntax.COMPACT ? "Compact" : "XML");
		
		text.setText(sb.toString());
	}
	
	public void clear() {
		text.setText("");
	}
}
