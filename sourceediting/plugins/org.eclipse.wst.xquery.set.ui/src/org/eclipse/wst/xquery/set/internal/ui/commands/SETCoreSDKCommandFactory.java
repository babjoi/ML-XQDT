/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.set.internal.ui.commands;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

public class SETCoreSDKCommandFactory extends ExtensionContributionFactory {

    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
        CommandContributionItemParameter parameter = new CommandContributionItemParameter(serviceLocator,
                "org.eclipse.wst.xquery.set.ui.popup.coresdk.deleteData",
                "org.eclipse.wst.xquery.set.ui.commands.coresdk.deleteData", SWT.POP_UP);

        CommandContributionItem item = new CommandContributionItem(parameter);
        item.setVisible(true);
        additions.addContributionItem(item, null);

        Command c = createCommand("org.eclipse.wst.xquery.set.ui.commands.coresdk.deleteData");
        System.out.println("done");
    }

    private Command createCommand(String id) {
        IWorkbench workbench = PlatformUI.getWorkbench();
        ICommandService cmdService = (ICommandService)workbench.getService(ICommandService.class);

        Category coreSdkCat = cmdService.getCategory("org.eclipse.wst.xquery.set.ui.commands.coresdk");
        Command command = cmdService.getCommand(id);
        if (!command.isDefined()) {
            command.define("Eat That Taco", "Go for the taco.", coreSdkCat);
        }

        return command;
    }
}
