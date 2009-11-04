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
package org.eclipse.wst.xquery.internal.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.dltk.ui.wizards.NewElementWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.wst.xquery.core.XQDTNature;
import org.eclipse.wst.xquery.internal.ui.preferences.XQDTBuildPathsBlock;
import org.eclipse.wst.xquery.ui.XQDTUIPlugin;

public class XQDTNewProjectWizard extends NewElementWizard implements INewWizard, IExecutableExtension {

    public static final String WIZARD_ID = "org.eclipse.wst.xquery.ui.wizards.newproject"; //$NON-NLS-1$

    private ProjectWizardFirstPage fFirstPage;
    private ProjectWizardSecondPage fSecondPage;

    private IConfigurationElement fConfigElement;

    public XQDTNewProjectWizard() {
        // setDefaultPageImageDescriptor(SETPluginImages.DESC_WIZBAN_NEW_PROJECT);
        setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
        setWindowTitle("New XQuery Project");
    }

    @Override
    public void addPages() {
        // First page
        fFirstPage = new ProjectWizardFirstPage() {

            // XQDTInterpreterGroup fInterpreterGroup;

            final class XQDTInterpreterGroup extends AbstractInterpreterGroup {

                public XQDTInterpreterGroup(Composite composite) {
                    super(composite);
                }

                protected String getCurrentLanguageNature() {
                    return XQDTNature.NATURE_ID;
                }

                protected String getIntereprtersPreferencePageId() {
                    return "org.eclipse.wst.xquery.preferences.interpreters"; //$NON-NLS-1$
                }
            };

            protected boolean interpeterRequired() {
                return true;
            }

            protected IInterpreterGroup createInterpreterGroup(Composite parent) {
                return new XQDTInterpreterGroup(parent);
            }

        };

        fFirstPage.setTitle("Create a XQuery project");
        fFirstPage.setDescription("Create a XQuery project in the workspace or in an external location");
        addPage(fFirstPage);

        // Second page
        fSecondPage = new ProjectWizardSecondPage(fFirstPage) {

            protected IPreferenceStore getPreferenceStore() {
                return XQDTUIPlugin.getDefault().getPreferenceStore();
            }

            protected BuildpathsBlock createBuildpathBlock(IStatusChangeListener listener) {
                return new XQDTBuildPathsBlock(new BusyIndicatorRunnableContext(), listener, 0, useNewSourcePage(),
                        null);
            }

            protected String getScriptNature() {
                return XQDTNature.NATURE_ID;
            }

        };

        addPage(fSecondPage);
    }

    protected void finishPage(IProgressMonitor monitor) throws InterruptedException, CoreException {
        fSecondPage.performFinish(monitor); // use the full progress monitor
    }

    @Override
    public boolean performFinish() {
        boolean res = super.performFinish();
        if (res) {
            BasicNewProjectResourceWizard.updatePerspective(fConfigElement);
            selectAndReveal(fSecondPage.getScriptProject().getProject());
        }

        return res;
    }

    /*
     * Stores the configuration element for the wizard. The config element will be used in
     * <code>performFinish</code> to set the result perspective.
     */
    public void setInitializationData(IConfigurationElement cfig, String propertyName, Object data) {
        fConfigElement = cfig;
    }

    public boolean performCancel() {
        fSecondPage.performCancel();
        return super.performCancel();
    }

    public IModelElement getCreatedElement() {
        return DLTKCore.create(fFirstPage.getProjectHandle());
    }
}
