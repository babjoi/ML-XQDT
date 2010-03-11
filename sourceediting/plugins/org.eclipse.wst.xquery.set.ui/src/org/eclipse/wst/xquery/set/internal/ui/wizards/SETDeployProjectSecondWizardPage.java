package org.eclipse.wst.xquery.set.internal.ui.wizards;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.xquery.set.launching.deploy.DeployInfo;

public class SETDeployProjectSecondWizardPage extends WizardPage {

    private class WidgetListener extends SelectionAdapter implements ModifyListener, IDialogFieldListener {

        public void widgetSelected(SelectionEvent e) {
            if (e.widget == fDeployDataCheckButton) {
                fDeployDataDescriptionLabel.setEnabled(fDeployDataCheckButton.getSelection());
            }
            isValid();
        }

        public void dialogFieldChanged(DialogField field) {
            if (field == fCustomServerRadioField) {
                fCustomServerText.setEnabled(true);
            } else if (field == fDefaultServerRadioField) {
                fCustomServerText.setEnabled(false);
            }
            isValid();
        }

        public void modifyText(ModifyEvent e) {
            isValid();
        }
    }

    private Button fDeployDataCheckButton;
    private Label fDeployDataDescriptionLabel;

    private Label fDefaultServerLabel;
    private Text fCustomServerText;
    private SelectionButtonDialogField fDefaultServerRadioField;
    private SelectionButtonDialogField fCustomServerRadioField;

    private WidgetListener fListener = new WidgetListener();

    private IScriptProject fProject;

    private static final String DESCRIPTION = "Choose custom deployment options";

    protected SETDeployProjectSecondWizardPage(IScriptProject project) {
        super("Deploy Sausalito Project");
        setTitle("Deployment Options");
        setDescription(DESCRIPTION);

        fProject = project;
    }

    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        setControl(composite);

        //createDeployDataButton(composite);
        //SWTFactory.createVerticalSpacer(composite, 5);
        createServerOptionsGroup(composite);

    }

    @SuppressWarnings("unused")
    private void createDeployDataButton(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout layout = new GridLayout(2, false);
        composite.setLayout(layout);

        fDeployDataCheckButton = SWTFactory.createCheckButton(composite, "Deploy project data", 2);
        fDeployDataCheckButton.addSelectionListener(fListener);
        fDeployDataCheckButton.setSelection(true);

        fDeployDataDescriptionLabel = new Label(composite, SWT.WRAP);
        fDeployDataDescriptionLabel
                .setText("If this option is enabled, after a succesful project deployment, the local data\n"
                        + "in the \"bulkload\" directory will be appended to the deployed project data.");
        GridData gd = new GridData();
        gd.horizontalSpan = 2;
        gd.horizontalIndent = 20;
        fDeployDataDescriptionLabel.setLayoutData(gd);
        fDeployDataDescriptionLabel.setEnabled(false);

    }

    private void createServerOptionsGroup(Composite composite) {
        Group group = SWTFactory.createGroup(composite, "Deployment URL", 3, 1, GridData.FILL_HORIZONTAL);

        fDefaultServerRadioField = new SelectionButtonDialogField(SWT.RADIO);
        fDefaultServerRadioField.setLabelText("Default server: ");
        fDefaultServerRadioField.setSelection(true);
        fDefaultServerRadioField.setDialogFieldListener(fListener);
        fDefaultServerRadioField.doFillIntoGrid(group, 2);

        fDefaultServerLabel = new Label(group, SWT.NONE);
        fDefaultServerLabel.setText(DeployInfo.DEFAULT_DEPLOYMENT_SERVER);

        fCustomServerRadioField = new SelectionButtonDialogField(SWT.RADIO);
        fCustomServerRadioField.setLabelText("Custom server: ");
        fCustomServerRadioField.setDialogFieldListener(fListener);
        fCustomServerRadioField.doFillIntoGrid(group, 2);

        fCustomServerText = SWTFactory.createText(group, SWT.BORDER, 1, "");
        fCustomServerText.addModifyListener(fListener);
        fCustomServerText.setEnabled(false);
    }

    private void isValid() {
        if (fCustomServerRadioField.isSelected()) {
            String server = fCustomServerText.getText();
            try {
                URL url = new URL(server);

                String authority = url.getAuthority();
                if (authority == null || !authority.matches("([\\p{Alnum}]+\\.)+\\p{Alpha}{2,}")) {
                    setErrorMessage("The URL authority must have at least two segments.");
                    setPageComplete(false);
                    return;
                }
            } catch (MalformedURLException mue) {
                setErrorMessage("The deployment URL is invalid.");
                setPageComplete(false);
                return;
            }
        }

        setErrorMessage(null);
        setPageComplete(true);
        return;
    }

    DeployInfo configureDeployInfo(DeployInfo info) {
        String host = info.getHost();
        if (fCustomServerRadioField.isSelected()) {
            host = fCustomServerText.getText();
        }
        return new DeployInfo(info.getProject(), info.getProjectConfig(), info.getApplicationName(),
                info.getUserName(), info.getPassword(), info.getDeployType(), host);

    }
}
