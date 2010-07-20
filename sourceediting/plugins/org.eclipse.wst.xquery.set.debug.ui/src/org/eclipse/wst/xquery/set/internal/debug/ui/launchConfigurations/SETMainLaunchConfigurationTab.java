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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.xquery.core.model.ast.XQueryFunctionDecl;
import org.eclipse.wst.xquery.core.model.ast.XQueryLibraryModule;
import org.eclipse.wst.xquery.set.core.ISETCoreConstants;
import org.eclipse.wst.xquery.set.core.SETNature;
import org.eclipse.wst.xquery.set.core.SETProjectConfig;
import org.eclipse.wst.xquery.set.core.SETProjectConfigUtil;
import org.eclipse.wst.xquery.set.debug.core.ISETLaunchConfigurationConstants;
import org.eclipse.wst.xquery.set.internal.launching.server.ServerManager;
import org.eclipse.wst.xquery.set.internal.ui.SETEditProjectConfigDialog;
import org.eclipse.wst.xquery.set.launching.SETRuntimeProcessFactory;

@SuppressWarnings("restriction")
public class SETMainLaunchConfigurationTab extends MainLaunchConfigurationTab {

    private SocketSelectionBlock fSocketBlock;
    private Button fClearCollectionsCheckButton;
    private Button fIndentCheckButton;

    private Text fScriptText;
    private Button fHandlerFunctionButton;
    private Button fPublicResourceButton;

    private SETProjectConfig fConfig;

    public SETMainLaunchConfigurationTab(String mode) {
        super(mode);
    }

    public String getNatureID() {
        return SETNature.NATURE_ID;
    }

    @Override
    protected boolean validate() {
        return super.validate() && validateSocket();
    }

    @Override
    protected boolean validateProject() {
        if (!super.validateProject()) {
            setErrorMessage("Select a valid Sausalito project");
            return false;
        }

        if (ServerManager.getInstance().isProjectStarted(getProject().getProject())) {
            setErrorMessage("The Project \"" + getProjectName() + "\" is already started");
            return false;
        }
        return true;
    }

    @Override
    protected boolean validateScript() {
        String startPage = getScriptName();
        IProject project = getProject().getProject();
        if (project.getFile(ISETCoreConstants.PROJECT_DIRECTORY_PUBLIC + "/" + startPage).exists()) {
            setErrorMessage(null);
            return true;
        }

        Path path = new Path(startPage);
        if (path.segmentCount() != 2) {
            setErrorMessage("Invalid start page. Use either a resource in the \""
                    + ISETCoreConstants.PROJECT_DIRECTORY_PUBLIC
                    + "\" directory or the format: /handler_module/function");
            return false;
        }

        IFolder folder = project.getFolder(ISETCoreConstants.PROJECT_DIRECTORY_HANDLER);
        if (!folder.isAccessible()) {
            setErrorMessage("The \"" + ISETCoreConstants.PROJECT_DIRECTORY_HANDLER + "\" directory is not accessible");
            return false;
        }
        String handlerName = path.segment(0);
        ISourceModule module = DLTKCore.createSourceModuleFrom(folder.getFile(handlerName + ".xq"));
        if (module == null || !module.exists()) {
            setErrorMessage("Could not find the '" + handlerName + ".xq' module in the \""
                    + ISETCoreConstants.PROJECT_DIRECTORY_HANDLER + "\" directory folder");
            return false;
        }

        ModuleDeclaration modDecl = SourceParserUtil.getModuleDeclaration(module);
        if (!(modDecl instanceof XQueryLibraryModule)) {
            setErrorMessage("The start page must point to a valid XQuery library module");
            return false;
        }
        String functionName = path.segment(1);
        XQueryLibraryModule libMod = (XQueryLibraryModule)modDecl;
        String prefix = libMod.getNamespacePrefix();
        XQueryFunctionDecl method = libMod.getFunction(prefix + ":" + functionName + 0);
        if (method == null) {
            setErrorMessage("No function '" + functionName + "' with 0 arguments is defined in the '" + handlerName
                    + ".xq' handler module");
            return false;
        }

        setErrorMessage(null);
        return true;
    }

    private boolean validateSocket() {
        String host = getHost();
        int port = getPort();
        if (!ServerManager.getInstance().isSocketFree(host, port)) {
            setErrorMessage("The socket " + host + ":" + port + " is already used");
            return false;
        }
        return true;
    }

    @Override
    public Image getImage() {
        return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_MODULE);
    }

    @Override
    protected void doCreateControl(Composite composite) {
        createMainModuleEditor(composite, "Start page");
    }

    public void createControl(Composite parent) {
        Composite comp = new Composite(parent, SWT.NONE);
        setControl(comp);

        GridLayout topLayout = new GridLayout();
        topLayout.verticalSpacing = 0;
        comp.setLayout(topLayout);

        createProjectEditor(comp);
        createVerticalSpacer(comp, 5);

        doCreateControl(comp);
        createVerticalSpacer(comp, 5);

        createSocketBlock(comp);
        createVerticalSpacer(comp, 5);

        createOptionsGroup(comp);
        createVerticalSpacer(comp, 5);

        createDebugOptionsGroup(comp);

        Dialog.applyDialogFont(comp);
    }

    protected void createMainModuleEditor(Composite parent, String text) {
        Group scriptGroup = new Group(parent, SWT.NONE);
        scriptGroup.setText(text);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        scriptGroup.setLayoutData(gd);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        scriptGroup.setLayout(layout);

        String labelText = "Select either an existing resource from the \""
                + ISETCoreConstants.PROJECT_DIRECTORY_PUBLIC + "\" directory or handler function:";
        SWTFactory.createLabel(scriptGroup, labelText, 2);

        fScriptText = new Text(scriptGroup, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;

        fScriptText.setLayoutData(gd);
        fScriptText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                updateLaunchConfigurationDialog();
            }
        });

        Composite buttonComp = new Composite(scriptGroup, SWT.NONE);
        gd = new GridData();
        gd.horizontalAlignment = GridData.END;
        gd.horizontalSpan = 2;
        buttonComp.setLayoutData(gd);
        layout = new GridLayout(2, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        buttonComp.setLayout(layout);

        fHandlerFunctionButton = createPushButton(buttonComp, "Handler function...", null);
        fHandlerFunctionButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                handleHandlerFunctionButtonSelected();
            }
        });

        fPublicResourceButton = createPushButton(buttonComp, "Public resource...", null);
        fPublicResourceButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                handlePublicResourceButtonSelected();
            }
        });
    }

    protected void createSocketBlock(Composite comp) {
        fSocketBlock = new SocketSelectionBlock(comp);
        fSocketBlock.addSelectionListener(getWidgetListener());
    }

    protected void createOptionsGroup(Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setText("Options");
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        if (parent.getLayout() instanceof GridLayout) {
            gd.horizontalSpan = ((GridLayout)parent.getLayout()).numColumns;
        }
        group.setLayoutData(gd);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        group.setLayout(layout);

        createOptions(group);
    }

    protected void createOptions(Composite group) {
        addClearCollectionsButton(group);
        addIndentButton(group);
    }

    private void addClearCollectionsButton(Composite group) {
        fClearCollectionsCheckButton = createCheckButton(group, "Clear collection data");
        fClearCollectionsCheckButton.addSelectionListener(getWidgetListener());
        createVerticalSpacer(group, 1);
    }

    private void addIndentButton(Composite group) {
        fIndentCheckButton = createCheckButton(group, "Indent results");
        fIndentCheckButton.addSelectionListener(getWidgetListener());
        createVerticalSpacer(group, 1);
    }

    @Override
    protected void setEnableScriptField(boolean enabled) {
        fScriptText.setEnabled(enabled);
        fHandlerFunctionButton.setEnabled(enabled && isValidProject());
    }

    @Override
    protected void updateMainModuleFromConfig(ILaunchConfiguration config) {
        fScriptText.setText(getMainModuleName(config));
    }

    @Override
    protected String getScriptName() {
        return fScriptText.getText().trim();
    }

    @Override
    protected void setScriptName(String value) {
        fScriptText.setText(value);
    }

    private String getMainModuleName(ILaunchConfiguration config) {
        return LaunchConfigurationUtils.getString(config, ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME,
                EMPTY_STRING);
    }

    protected void handleHandlerFunctionButtonSelected() {
        String startPage = SETEditProjectConfigDialog
                .getHandlerFunctionStartPage(getProject().getProject(), getShell());
        if (!startPage.equals("")) {
            fScriptText.setText(startPage);
        }
    }

    protected void handlePublicResourceButtonSelected() {
        String startPage = SETEditProjectConfigDialog.getPublicResourceStartPage(getProject().getProject(), getShell());
        if (!startPage.equals("")) {
            fScriptText.setText(startPage);
        }
    }

    @Override
    protected void doPerformApply(ILaunchConfigurationWorkingCopy config) {
        config.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_HOST, fSocketBlock.fIpCombo.getText());
        config.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_PORT,
                Integer.parseInt(fSocketBlock.fPortSpinner.getText()));
        config.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_INDENT, fIndentCheckButton.getSelection());
        config.setAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_CLEAR_COLLECTIONS,
                fClearCollectionsCheckButton.getSelection());
        config.setAttribute(DebugPlugin.ATTR_PROCESS_FACTORY_ID, SETRuntimeProcessFactory.PROCESS_FACTORY_ID);

        super.doPerformApply(config);

        // save the new start page to the sausalito config file
        if (getProject() != null && getProject().getProject().isAccessible()) {
            fConfig = SETProjectConfigUtil.readProjectConfig(getProject().getProject());
            String startPage = getScriptName();
            if (fConfig != null && startPage.length() != 0 && !startPage.equals(fConfig.getStartPage())) {
                fConfig.setStartPage(startPage);
                SETProjectConfigUtil.writeProjectConfig(getProject().getProject(), fConfig);
            }
        }
    }

    @Override
    protected void doInitializeForm(ILaunchConfiguration config) {
        super.doInitializeForm(config);

        IScriptProject project = getProject();
        if (project.exists() && project.getProject().isAccessible()) {
            fConfig = SETProjectConfigUtil.readProjectConfig(project.getProject());
        }
        fScriptText.setText(getProjectStartPage());
        fSocketBlock.fIpCombo.setText(getHost(config));
        fSocketBlock.fPortSpinner.setSelection(getPort(config));
        fIndentCheckButton.setSelection(getIndent(config));
        fClearCollectionsCheckButton.setSelection(getClearCollections(config));

    }

    private String getHost(ILaunchConfiguration config) {
        return LaunchConfigurationUtils.getString(config, ISETLaunchConfigurationConstants.ATTR_XQDT_SET_HOST,
                "127.0.0.1");
    }

    private String getHost() {
        return fSocketBlock.fIpCombo.getText();
    }

    private int getPort(ILaunchConfiguration config) {
        int value = 8080;
        try {
            if (config != null) {
                value = config.getAttribute(ISETLaunchConfigurationConstants.ATTR_XQDT_SET_PORT, 8080);
            }
        } catch (CoreException e) {
            DLTKLaunchingPlugin.log(e);
        }

        return value;
    }

    private int getPort() {
        return Integer.parseInt(fSocketBlock.fPortSpinner.getText());
    }

    private boolean getIndent(ILaunchConfiguration config) {
        return LaunchConfigurationUtils
                .getBoolean(config, ISETLaunchConfigurationConstants.ATTR_XQDT_SET_INDENT, false);
    }

    private boolean getClearCollections(ILaunchConfiguration config) {
        return LaunchConfigurationUtils.getBoolean(config,
                ISETLaunchConfigurationConstants.ATTR_XQDT_SET_CLEAR_COLLECTIONS, false);
    }

    @Override
    protected void setDefaults(ILaunchConfigurationWorkingCopy configuration, IModelElement element) {
        super.setDefaults(configuration, element);

        // overwrite the value written by the parent
        String startPage = getProjectStartPage();
        configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME, startPage);

    }

    @Override
    protected void projectChanged() {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        if (project.isAccessible()) {
            fConfig = SETProjectConfigUtil.readProjectConfig(project);
            setScriptName(getProjectStartPage());
        }
    }

    private String getProjectStartPage() {
        if (fConfig == null) {
            return "";
        }
        return fConfig.getStartPage();
    }
}
