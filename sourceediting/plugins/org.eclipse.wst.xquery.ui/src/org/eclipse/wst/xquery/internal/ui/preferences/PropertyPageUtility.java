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
package org.eclipse.wst.xquery.internal.ui.preferences;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PropertyPageUtility {

    public static Combo addComboBox(Composite parent, String label, String[] values) {
        Label labelControl = new Label(parent, SWT.LEFT);
        labelControl.setFont(JFaceResources.getDialogFont());
        labelControl.setText(label);

        Combo comboBox = newComboControl(parent, values);

        return comboBox;
    }

    private static Combo newComboControl(Composite composite, String[] values) {
        Combo comboBox = new Combo(composite, SWT.READ_ONLY);
        comboBox.setItems(values);
        comboBox.setFont(JFaceResources.getDialogFont());

        return comboBox;
    }

    public static void addSeparator(Composite parent) {
        GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        if (parent.getLayout() instanceof GridLayout)
            gd.horizontalSpan = ((GridLayout)parent.getLayout()).numColumns;

        Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(gd);
    }

}
