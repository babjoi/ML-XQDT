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
package org.eclipse.wst.xquery.internal.debug.ui.interpreters;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.ui.StringVariableSelectionDialog;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.core.internal.environment.LazyFileHandle;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersMessages;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringDialogField;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterStandin;
import org.eclipse.dltk.ui.dialogs.StatusInfo;
import org.eclipse.dltk.utils.PlatformFileUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.xquery.debug.ui.interpreters.AbstractAddInterpreterDialogBlock;
import org.eclipse.wst.xquery.debug.ui.interpreters.AbstractInterpreterEnvironmentVariablesBlock;
import org.eclipse.wst.xquery.internal.launching.XQDTJavaInterpreterInstall;

@SuppressWarnings("restriction")
public class XQDTJavaInterpreterAddInterpreterDialogBlock extends AbstractAddInterpreterDialogBlock {

    private static final String INTERPRETER_LOCATION_LABEL_NAME = "Interpreter JAR/WAR:"; //$NON-NLS-1$
    private static final String INTERPRETER_MAIN_CLASS_LABEL_NAME = "Main class:"; //$NON-NLS-1$
    private static final String INTERPRETER_JAVA_ARGS_LABEL_NAME = "Java arguments:"; //$NON-NLS-1$

    public static final String[] POSSIBLE_EXTENSION_FILTER = { "*.jar;*.war" };

    protected StringDialogField fInterpreterNameField;
    protected StringButtonDialogField fInterpreterLocationField;
    protected StringDialogField fInterpreterMainClassField;
    protected StringDialogField fInterpreterJavaArgsField;
    protected StringDialogField fInterpreterArgsField;

    protected AbstractInterpreterEnvironmentVariablesBlock fEnvironmentVariablesBlock;

    private IStatus[] fStati;

    public XQDTJavaInterpreterAddInterpreterDialogBlock() {
        fStati = new IStatus[5];
        for (int i = 0; i < fStati.length; i++) {
            fStati[i] = new StatusInfo();
        }
    }

    protected void addControlsTo(Composite parent) {
        int numColumns = 3;

        fInterpreterNameField = new StringDialogField();
        fInterpreterNameField.setLabelText(InterpretersMessages.addInterpreterDialog_InterpreterEnvironmentName);
        fInterpreterNameField.doFillIntoGrid(parent, numColumns);

        fInterpreterLocationField = new StringButtonDialogField(new IStringButtonAdapter() {
            public void changeControlPressed(DialogField field) {
                browseForInstallation();
            }
        });
        fInterpreterLocationField.setLabelText(INTERPRETER_LOCATION_LABEL_NAME);
        fInterpreterLocationField.setButtonLabel(InterpretersMessages.addInterpreterDialog_browse1);
        fInterpreterLocationField.doFillIntoGrid(parent, numColumns);
        ((GridData)fInterpreterLocationField.getTextControl(null).getLayoutData()).widthHint = convertWidthInCharsToPixels(50);

        fInterpreterJavaArgsField = new StringDialogField();
        fInterpreterJavaArgsField.setLabelText(INTERPRETER_JAVA_ARGS_LABEL_NAME);
        fInterpreterJavaArgsField.doFillIntoGrid(parent, numColumns);

        fInterpreterMainClassField = new StringDialogField();
        fInterpreterMainClassField.setLabelText(INTERPRETER_MAIN_CLASS_LABEL_NAME);
        boolean foundJDTSearchTools = findJdtSearchTools();
        if (foundJDTSearchTools) {
            fInterpreterMainClassField.doFillIntoGrid(parent, numColumns - 1);
            Button button = new Button(parent, SWT.PUSH);
            button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            button.setText("Search...");
            button.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    searchForJavaMainClass();
                }
            });
        } else {
            fInterpreterMainClassField.doFillIntoGrid(parent, numColumns);
        }

        fInterpreterArgsField = new StringDialogField();
        fInterpreterArgsField.setLabelText(InterpretersMessages.AddInterpreterDialog_iArgs);
        fInterpreterArgsField.doFillIntoGrid(parent, numColumns - 1);
        Button button = new Button(parent, SWT.PUSH);
        button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        button.setText("Variables...");
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                StringVariableSelectionDialog dialog = new StringVariableSelectionDialog(getShell());
                dialog.open();
                String variable = dialog.getVariableExpression();
                if (variable != null) {
                    fInterpreterArgsField.getTextControl(null).insert(variable);
                }
            }
        });

        fEnvironmentVariablesBlock = createEnvironmentVariablesBlock();
        if (fEnvironmentVariablesBlock != null) {
            Label l = new Label(parent, SWT.NONE);
            l.setText(InterpretersMessages.AddScriptInterpreterDialog_interpreterEnvironmentVariables);
            GridData gd = new GridData(GridData.FILL_HORIZONTAL);
            gd.horizontalSpan = numColumns;
            l.setLayoutData(gd);

            Control block = fEnvironmentVariablesBlock.createControl(parent);
            gd = new GridData(GridData.FILL_BOTH);
            gd.horizontalSpan = numColumns;
            block.setLayoutData(gd);
        }
    }

    protected AbstractInterpreterEnvironmentVariablesBlock createEnvironmentVariablesBlock() {
        return null;
    }

    public void setFieldValuesToInterpreter(IInterpreterInstall install) {
        IEnvironment selectedEnv = fAddInterpreterDialog.getEnvironment();
        install.setInstallLocation(new LazyFileHandle(selectedEnv.getId(), new Path(getInterpreterLocation())));
        install.setName(getInterpreterName());
        String args = getInterpreterJavaArgs() + "|" + getInterpreterMainClass() + "|" + getInterpreterArgs();
        install.setInterpreterArgs(args);
        if (fEnvironmentVariablesBlock != null) {
            fEnvironmentVariablesBlock.performApply(install);
        }
    }

    protected IStatus validateInterpreterLocation() {
        IEnvironment selectedEnv = fAddInterpreterDialog.getEnvironment();
        String locationName = getInterpreterLocation();
        IStatus s = null;
        final IFileHandle file;
        if (locationName.length() == 0) {
            file = null;
            s = new StatusInfo(IStatus.INFO, InterpretersMessages.addInterpreterDialog_enterLocation);
        } else {
            file = PlatformFileUtils.findAbsoluteOrEclipseRelativeFile(selectedEnv, new Path(locationName));
            if (!file.exists()) {
                s = new StatusInfo(IStatus.ERROR, InterpretersMessages.addInterpreterDialog_locationNotExists);
            } else {
                final IStatus[] temp = new IStatus[1];
                BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {

                    public void run() {
                        temp[0] = fAddInterpreterDialog.getInterpreterType().validateInstallLocation(file, null, null,
                                null);
                    }
                });
                s = temp[0];
            }
        }
        if (s.isOK()) {
            String name = getInterpreterName();
            if ((name == null || name.length() == 0) && file != null) {
                // auto-generate interpreter name
                String pName = generateInterpreterName(file.toOSString());
                if (pName != null) {
                    fInterpreterNameField.setText(pName);
                }
            }
        }
        return s;
    }

    protected String getInterpreterMainClass() {
        return fInterpreterMainClassField.getText().trim();
    }

    protected String getInterpreterJavaArgs() {
        return fInterpreterJavaArgsField.getText().trim();
    }

    protected String getInterpreterLocation() {
        return fInterpreterLocationField.getText().trim();
    }

    protected String getInterpreterArgs() {
        return fInterpreterArgsField.getText().trim();
    }

    protected String getInterpreterName() {
        return fInterpreterNameField.getText().trim();
    }

    protected void browseForInstallation() {
        FileDialog dialog = new FileDialog(getShell());
        dialog.setFilterExtensions(POSSIBLE_EXTENSION_FILTER);
        String newPath = dialog.open();
        if (newPath != null) {
            fInterpreterLocationField.setText(newPath);
        }
    }

    public void initializeFields(IInterpreterInstall install) {
        if (install == null) {
            return;
        }
        if (install instanceof InterpreterStandin) {
            fInterpreterNameField.setText(install.getName());
            fInterpreterLocationField.setText(install.getRawInstallLocation().toOSString());
            String allArgs = install.getInterpreterArgs();
            String[] splits = allArgs.split("\\|");
            fInterpreterJavaArgsField.setText(splits[0]);
            fInterpreterMainClassField.setText(splits[1]);
            fInterpreterArgsField.setText(splits[2]);
            return;
        } else {
            XQDTJavaInterpreterInstall javaInstall = (XQDTJavaInterpreterInstall)install;
            fInterpreterNameField.setText(javaInstall.getName());
            fInterpreterLocationField.setText(javaInstall.getRawInstallLocation().toOSString());
            String javaArgs = javaInstall.getJavaArgs();
            String mainClass = javaInstall.getMainClass();
            String interpreterArgs = javaInstall.getJavaInterpreterArgs();

            fInterpreterMainClassField.setText(mainClass);
            fInterpreterJavaArgsField.setText(javaArgs);
            fInterpreterArgsField.setText(interpreterArgs);

            if (fEnvironmentVariablesBlock != null) {
                fEnvironmentVariablesBlock.initializeFrom(javaInstall, javaInstall.getInterpreterInstallType());
            }
        }
        setInterpreterNameStatus(validateInterpreterName());
        setInterpreterLocationStatus(validateInterpreterLocation());
        setInterpreterMainClassStatus(validateInterpreterMainClass());
        updateStatus();
    }

    @Override
    public void createFieldListeners() {
        fInterpreterNameField.setDialogFieldListener(new IDialogFieldListener() {
            public void dialogFieldChanged(DialogField field) {
                setInterpreterNameStatus(validateInterpreterName());
                updateStatus();
            }
        });

        fInterpreterLocationField.setDialogFieldListener(new IDialogFieldListener() {
            public void dialogFieldChanged(DialogField field) {
                setInterpreterLocationStatus(validateInterpreterLocation());
                updateStatus();
            }
        });

        fInterpreterMainClassField.setDialogFieldListener(new IDialogFieldListener() {
            public void dialogFieldChanged(DialogField field) {
                setInterpreterMainClassStatus(validateInterpreterMainClass());
                updateStatus();
            }
        });

    }

    protected IStatus validateInterpreterMainClass() {
        String mainClass = getInterpreterMainClass();
        if (mainClass.length() == 0) {
            return new StatusInfo(IStatus.INFO, "Enter the main class from the provided Java archive.");
        }

        if (mainClass.endsWith(".")) {
            return new StatusInfo(IStatus.ERROR, "The class name cannot terminate with a dot");
        }
        String[] splits = mainClass.split("\\.");
        for (int i = 0; i < splits.length; i++) {

            if (splits[i].length() == 0) {
                return new StatusInfo(IStatus.ERROR, "Invalid class name (zero length fragment)");
            }
            if (!Character.isJavaIdentifierStart(splits[i].charAt(0))) {
                return new StatusInfo(IStatus.ERROR, "Invalid class name (invalid start character '"
                        + splits[i].charAt(0) + "'for fragment: \"" + splits[i] + "\")");
            }
            for (int j = 1; j < splits[i].length(); j++) {
                if (!Character.isJavaIdentifierPart(splits[i].charAt(j))) {
                    return new StatusInfo(IStatus.ERROR, "Invalid class name (invalid character '"
                            + splits[i].charAt(j) + "' in fragment: \"" + splits[i] + "\")");
                }
            }
        }

        return new StatusInfo();
    }

    public void updateStatus() {
        IStatus max = null;
        for (int i = 0; i < fStati.length; i++) {
            IStatus curr = fStati[i];
            if (curr.matches(IStatus.ERROR)) {
                getStatusListener().statusChanged(curr);
                return;
            }
            if (max == null || curr.getSeverity() > max.getSeverity()) {
                max = curr;
            }
        }
        getStatusListener().statusChanged(max);
    }

    private void setInterpreterNameStatus(IStatus status) {
        fStati[0] = status;
    }

    private void setInterpreterLocationStatus(IStatus status) {
        fStati[1] = status;
    }

    private void setInterpreterMainClassStatus(IStatus status) {
        fStati[2] = status;
    }

    protected IStatus validateInterpreterName() {
        StatusInfo status = new StatusInfo();
        String name = getInterpreterName();
        if (name == null || name.length() == 0) {
            status.setInfo(InterpretersMessages.addInterpreterDialog_enterName);
        } else if (fRequestor.isDuplicateName(name, fEditedInterpreter)
                && (fEditedInterpreter == null || !name.equals(fEditedInterpreter.getName()))) {
            status.setError(InterpretersMessages.addInterpreterDialog_duplicateName);
        }
        return status;
    }

    @Override
    public void setFocus() {
        fInterpreterNameField.setFocus();
    }

    public String generateInterpreterName(String location) {
        return null;
    }

    private boolean findJdtSearchTools() {
//        Bundle jdtBundle = Platform.getBundle("org.eclipse.jdt.debug.ui");
//        if (jdtBundle == null) {
//            return false;
//        }
//        MyFilteredItemsSelectionDialog filterredDialog = new MyFilteredItemsSelectionDialog(getShell());
//        filterredDialog.open();
        return false;
    }

    private void searchForJavaMainClass() {
    }

}

//class MyFilteredItemsSelectionDialog extends FilteredItemsSelectionDialog {
//
//    public MyFilteredItemsSelectionDialog(Shell shell) {
//        super(shell);
//        setInitialPattern("**");
//        setTitle("Select the main class");
//        ArrayList<String> elems = new ArrayList<String>();
//        elems.add("one");
//        setInitialElementSelections(elems);
//    }
//
//    @Override
//    protected IStatus validateItem(Object item) {
//        // TODO Auto-generated method stub
//        return Status.OK_STATUS;
//    }
//
//    @Override
//    protected Comparator getItemsComparator() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public String getElementName(Object item) {
//        return item.toString();
//    }
//
//    private static final String DIALOG_SETTINGS = "org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog"; //$NON-NLS-1$
//
//    protected IDialogSettings getDialogSettings() {
//        IDialogSettings settings = DLTKUIPlugin.getDefault().getDialogSettings().getSection(DIALOG_SETTINGS);
//
//        if (settings == null) {
//            settings = DLTKUIPlugin.getDefault().getDialogSettings().addNewSection(DIALOG_SETTINGS);
//        }
//
//        return settings;
//    }
//
//    @Override
//    protected void fillContentProvider(AbstractContentProvider contentProvider, ItemsFilter itemsFilter,
//            IProgressMonitor progressMonitor) throws CoreException {
//        if (itemsFilter instanceof MyItemsFilter) {
//            contentProvider.add("a string", itemsFilter);
//
//        }
//
//    }
//
//    protected ItemsFilter createFilter() {
//        return new MyItemsFilter();
//    }
//
//    protected Control createExtendedContentArea(Composite parent) {
//        return null;
//    }
//
//    private class MyItemsFilter extends ItemsFilter {
//
//        @Override
//        public boolean isConsistentItem(Object item) {
//            // TODO Auto-generated method stub
//            return false;
//        }
//
//        @Override
//        public boolean matchItem(Object item) {
//            // TODO Auto-generated method stub
//            return true;
//        }
//
//    }
//
//};