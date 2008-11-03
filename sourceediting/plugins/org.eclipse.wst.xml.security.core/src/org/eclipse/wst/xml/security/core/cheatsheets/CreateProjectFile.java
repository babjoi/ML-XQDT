/*******************************************************************************
 * Copyright (c) 2008 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.cheatsheets;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;


/**
 * <p>Cheat Sheet action. Creates a new project (if it doesn't exist) and a new XML
 * document (if it doesn't exist) containing the sample XML document structure
 * from the help pages.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class CreateProjectFile extends Action implements ICheatSheetAction {
    /** The example project to create. */
    private static final String EXAMPLE_PROJECT = "XML-Security"; //$NON-NLS-1$
    /** The example XML document to create. */
    private static final String EXAMPLE_FILE = "FirstSteps.xml"; //$NON-NLS-1$
    /** First steps sample XML document. */
    private static final StringBuffer FIRST_STEPS = new StringBuffer(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" //$NON-NLS-1$
            + "<Invoice>\n" //$NON-NLS-1$
            + " <ID>IN 2008/00645</ID>\n" //$NON-NLS-1$
            + " <IssueDate>2008-03-30</IssueDate>\n" //$NON-NLS-1$
            + " <BuyerParty>\n" //$NON-NLS-1$
            + "  <ID>458746</ID>\n" //$NON-NLS-1$
            + "  <name>John Doe</name>\n" //$NON-NLS-1$
            + " </BuyerParty>\n" //$NON-NLS-1$
            + " <PaymentMeans>\n" //$NON-NLS-1$
            + "  <PayeeFinancialAccount>\n" //$NON-NLS-1$
            + "   <ID>07044961</ID>\n" //$NON-NLS-1$
            + "   <Name>The Specialists Company</Name>\n" //$NON-NLS-1$
            + "   <AccountTypeCode>Credit</AccountTypeCode>\n" //$NON-NLS-1$
            + "   <FinancialInstitutionBranch>\n" //$NON-NLS-1$
            + "    <ID>776631</ID>\n" //$NON-NLS-1$
            + "    <Institution>LOYDGB852</Institution>\n" //$NON-NLS-1$
            + "   </FinancialInstitutionBranch>\n" //$NON-NLS-1$
            + "  </PayeeFinancialAccount>\n" //$NON-NLS-1$
            + " </PaymentMeans>\n" //$NON-NLS-1$
            + " <TotalAmount currencyID=\"€\">382.00</TotalAmount>\n" //$NON-NLS-1$
            + " <!-- item 1 -->\n" //$NON-NLS-1$
            + " <InvoiceLine id=\"1\">\n" //$NON-NLS-1$
            + "  <Quantity>2</Quantity>\n" //$NON-NLS-1$
            + "  <LineAmount currencyID=\"€\">205.00</LineAmount>\n" //$NON-NLS-1$
            + "  <Item id=\"236WV\">\n" //$NON-NLS-1$
            + "   <BasePrice currencyID=\"€\">102.50</BasePrice>\n" //$NON-NLS-1$
            + "  </Item>\n" //$NON-NLS-1$
            + " </InvoiceLine>\n" //$NON-NLS-1$
            + " <!-- item 2 -->\n" //$NON-NLS-1$
            + " <InvoiceLine id=\"2\">\n" //$NON-NLS-1$
            + "  <Quantity>1</Quantity>\n" //$NON-NLS-1$
            + "  <LineAmount currencyID=\"€\">177.00</LineAmount>\n" //$NON-NLS-1$
            + "  <Item id=\"193DX\">\n" //$NON-NLS-1$
            + "   <BasePrice currencyID=\"€\">177.00</BasePrice>\n" //$NON-NLS-1$
            + "  </Item>\n" //$NON-NLS-1$
            + " </InvoiceLine>\n" //$NON-NLS-1$
            + "</Invoice>"); //$NON-NLS-1$

    /**
     * Creates the project and the sample XML document if neither doesn't exist.
     *
     * @param params Parameters for the action
     * @param manager The Cheat Sheet manager
     */
    public void run(final String[] params, final ICheatSheetManager manager) {
        try {
            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            IWorkspaceRoot root = workspace.getRoot();
            IProject sampleProject = root.getProject(EXAMPLE_PROJECT);

            // Create project if it does not yet exist
            if (!sampleProject.exists()) {
                sampleProject.create(null);
            }

            // Open the project if it is closed
            if (!sampleProject.isOpen()) {
                sampleProject.open(null);
            }

            IPath path = new Path(EXAMPLE_FILE);
            IFile sampleFile = sampleProject.getFile(path);
            IWorkbench workbench = PlatformUI.getWorkbench();

            if (!sampleFile.exists()) {
                // Create the new file and fill it with the sample content
                sampleFile.create(new ByteArrayInputStream(FIRST_STEPS.toString().getBytes("UTF-8")), true, null);
            }

            IEditorDescriptor desc = workbench.getEditorRegistry().getDefaultEditor(sampleFile.getName());
            workbench.getActiveWorkbenchWindow().getActivePage().openEditor(new FileEditorInput(sampleFile),
                    desc.getId());
        } catch (Exception ex) {
            showErrorDialog(Messages.error, Messages.createProjectFileXMLError, ex);
        }
    }

    /**
     * Shows an error dialog with a details button for detailed error
     * information. The error message and error details are automatically
     * written to the workspace log file.
     *
     * @param title The title of the error dialog
     * @param message The message of the error dialog
     * @param ex The exception
     */
    protected void showErrorDialog(final String title, final String message, final Exception ex) {
        String localizedMessage = ex.getLocalizedMessage();
        if (localizedMessage == null) {
            localizedMessage = Messages.errorReasonUnavailable;
        }

        XmlSecurityPlugin plugin = XmlSecurityPlugin.getDefault();
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        IStatus status = new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0, localizedMessage, ex);
        plugin.getLog().log(status);

        ErrorDialog errorDialog = new ErrorDialog(shell, title, message, status, IStatus.ERROR);
        errorDialog.open();
    }
}
