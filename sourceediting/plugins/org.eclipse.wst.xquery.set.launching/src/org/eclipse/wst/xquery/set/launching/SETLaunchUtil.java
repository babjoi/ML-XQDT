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
package org.eclipse.wst.xquery.set.launching;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WebBrowserPreference;
import org.eclipse.wst.xquery.set.debug.core.ISETLaunchConfigurationConstants;

@SuppressWarnings("restriction")
public class SETLaunchUtil {

    public static Browser openBrowser(ILaunch launch) {
        final String startPage, host;
        // this id is needed for testing
        final Browser[] theBrowser = { null };
        final int port;
        try {
            ILaunchConfiguration config = launch.getLaunchConfiguration();
            startPage = config.getAttribute(ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME, "");
            host = config.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_HOST, "127.0.0.1");
            port = config.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_PORT, 8080);
        } catch (CoreException ce) {
            Status status = new Status(IStatus.ERROR, SETLaunchingPlugin.PLUGIN_ID,
                    "An error occured while preparing to open the browser", ce);
            SETLaunchingPlugin.getDefault().getLog().log(status);
            return null;
        }

        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
                    String urlStr = "http://" + host;
                    if (port != 80) {
                        urlStr += ":" + port;
                    }
                    urlStr += startPage;

                    URL url = new URL(urlStr);
                    IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
                    IWebBrowser browser = null;
                    if (WebBrowserPreference.EXTERNAL == WebBrowserPreference.getBrowserChoice()) {
                        browser = browserSupport.getExternalBrowser();
                    } else {

                        String browserTitle = "Sausalito: " + url.toString();
                        browser = browserSupport.createBrowser(IWorkbenchBrowserSupport.LOCATION_BAR
                                | IWorkbenchBrowserSupport.NAVIGATION_BAR | IWorkbenchBrowserSupport.STATUS
                                | SWT.MOZILLA, "SausalitoBrowser", browserTitle.toString(), browserTitle.toString());
                    }
                    //theBrowser[0] = browser;
                    browser.openURL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });

        return theBrowser[0];
    }

    public static void bringBrowserOnTop() {
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                for (IEditorReference ref : page.getEditorReferences()) {
                    if (ref.getId().equals("org.eclipse.ui.browser.editor")) {
                        IEditorPart part = ref.getEditor(true);
                        page.bringToTop(part);
                        break;
                    }
                }
            }
        });
    }
}