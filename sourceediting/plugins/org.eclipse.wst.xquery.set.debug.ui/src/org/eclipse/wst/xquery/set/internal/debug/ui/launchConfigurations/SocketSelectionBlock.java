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
package org.eclipse.wst.xquery.set.internal.debug.ui.launchConfigurations;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class SocketSelectionBlock {

    public Combo fIpCombo;
    public Spinner fPortSpinner;

    public SocketSelectionBlock(Composite parent) {
        createIPAdressSection(parent);
    }

    protected void createIPAdressSection(Composite parent) {
        Group mainGroup = new Group(parent, SWT.NONE);
        mainGroup.setText("Application server endpoint");
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        mainGroup.setLayoutData(gd);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        mainGroup.setLayout(layout);

        Composite comp = new Composite(mainGroup, SWT.NONE);
        GridLayout topLayout = new GridLayout(2, false);
        comp.setLayout(topLayout);

        addIPCombo(comp);
        addPortSpinner(comp);
    }

    private void addIPCombo(Composite comp) {
        Label label = new Label(comp, SWT.NONE);
        label.setText("IP Address:");

        fIpCombo = SWTFactory.createCombo(comp, SWT.READ_ONLY, 1, getIPAddresses());
    }

    private void addPortSpinner(Composite comp) {
        Label label = new Label(comp, SWT.NONE);
        label.setText("Port:");

        fPortSpinner = new Spinner(comp, SWT.BORDER | SWT.RIGHT);
        fPortSpinner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        fPortSpinner.setMinimum(0);
        fPortSpinner.setMaximum(65535);
        fPortSpinner.setIncrement(1);
        fPortSpinner.setPageIncrement(100);
    }

    private String[] getIPAddresses() {
        List<String> ipstr = new ArrayList<String>();
        String localhost = "127.0.0.1";
        ipstr.add(localhost);

        try {
            InetAddress[] ips = Inet4Address.getAllByName(Inet4Address.getLocalHost().getHostName());

            for (int i = 0; i < ips.length; i++) {
                if (ips[i] instanceof Inet4Address && !ips[i].getHostAddress().equals(localhost))
                    ipstr.add(ips[i].getHostAddress());
            }
        } catch (UnknownHostException e) {
        }
        return ipstr.toArray(new String[ipstr.size()]);
    }

    public void addSelectionListener(SelectionListener listener) {
        fIpCombo.addSelectionListener(listener);
        fPortSpinner.addSelectionListener(listener);
    }
}