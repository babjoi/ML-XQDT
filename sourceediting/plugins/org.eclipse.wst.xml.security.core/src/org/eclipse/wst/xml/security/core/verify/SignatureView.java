/*******************************************************************************
 * Copyright (c) 2009 Dominik Schadow - http://www.xml-sicherheit.de
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.verify;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.PluginTransferData;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wst.xml.security.core.XmlSecurityPlugin;
import org.eclipse.wst.xml.security.core.actions.ShowSignatureProperties;
import org.eclipse.wst.xml.security.core.utils.IContextHelpIds;

/**
 * <p>Displays the <i>XML Digital Signatures</i> view with all discovered digital signatures in the
 * current XML document. Shows the properties of every signature and enables a rescan of the opened
 * XML document for new signatures.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class SignatureView extends ViewPart {
    /** TableViewer in the view. */
    private TableViewer signatures;
    /** Double click on a signature in the view. */
    private ShowSignatureProperties doubleClickAction = new ShowSignatureProperties();
    /** Digital Signature view ID. */
    public static final String ID = "org.eclipse.wst.xml.security.core.views.SignatureView";
    /** The system clipboard. */
    private Clipboard clipboard = null;

    /**
     * Sets the input for the view.
     *
     * @param results Input as ArrayList
     */
    public void setInput(final ArrayList<VerificationResult> results) {
        signatures.setInput(results);
        updateViewInfo(results);
    }

    /**
     * Disposes the view, frees all system resources.
     */
    public void dispose() {
        clipboard.dispose();
        clipboard = null;
        super.dispose();
    }

    /**
     * Creates the view layout and content.
     *
     * @param parent The parent composite
     */
    public void createPartControl(final Composite parent) {
        final int iconColumn = 20;
        final int smallColumn = 100;
        final int largeColumn = 200;

        signatures = new TableViewer(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        signatures.setContentProvider(new SignatureContentProvider());
        signatures.setLabelProvider(new SignatureLabelProvider());
        signatures.setComparator(new SignatureComparator());

        getSite().setSelectionProvider(signatures);

        signatures.getTable().setLinesVisible(true);
        signatures.getTable().setHeaderVisible(true);

        TableColumn cStatus = new TableColumn(signatures.getTable(), SWT.LEFT, 0);
        cStatus.setText("");
        cStatus.setToolTipText(Messages.signatureStatus);
        cStatus.setWidth(iconColumn);
        cStatus.setAlignment(SWT.CENTER);

        TableColumn cId = new TableColumn(signatures.getTable(), SWT.LEFT, 1);
        cId.setText(Messages.signatureId);
        cId.setToolTipText(Messages.signatureId);
        cId.setWidth(largeColumn);
        cId.setAlignment(SWT.LEFT);

        TableColumn cType = new TableColumn(signatures.getTable(), SWT.LEFT, 2);
        cType.setText(Messages.signatureType);
        cType.setToolTipText(Messages.signatureType);
        cType.setWidth(smallColumn);
        cType.setAlignment(SWT.LEFT);

        TableColumn cAlgorithm = new TableColumn(signatures.getTable(), SWT.LEFT, 3);
        cAlgorithm.setText(Messages.signatureAlgorithm);
        cAlgorithm.setToolTipText(Messages.signatureAlgorithm);
        cAlgorithm.setWidth(largeColumn);
        cAlgorithm.setAlignment(SWT.LEFT);

        initCopyAndPaste();
        initContextMenu();
        hookDoubleClickAction();
        contributeToActionBars();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(signatures.getControl(), IContextHelpIds.SIGNATURE_VIEW);
    }

    /**
     * Initializes the copy and paste functionality of the XML Digital Signature View. Verification
     * Results can be copied in a readable String format.
     */
    private void initCopyAndPaste() {
        clipboard = new Clipboard(getSite().getShell().getDisplay());
        IAction copyAction = new Action() {
            public void run() {
                setClipboardData();
            }
        };
        getViewSite().getActionBars().setGlobalActionHandler("copy", copyAction);
    }

    /**
     * Copies the selected Verification Result to the clipboard.
     *
     * @return Result was copied to clipboard.
     */
    private boolean setClipboardData() {
        VerificationResult result = getSelectedItem();

        if (result == null) {
            return false;
        }

        PluginTransferData pluginData = new PluginTransferData("org.eclipse.wst.xml.security.core.verificationResultCopyAction",
                result.resultToByteArray());
        String text = result.resultToReadableString();
        TextTransfer textTransfer = TextTransfer.getInstance();
        PluginTransfer pluginTransfer = PluginTransfer.getInstance();
        Transfer[] transfers = new Transfer[] {textTransfer, pluginTransfer};
        Object[] data = new Object[] {text, pluginData};
        clipboard.setContents(data, transfers);

        return true;
    }

    /**
     * Determines the selected Verification Result object in the view.
     *
     * @return The selected Verification Result
     */
    private VerificationResult getSelectedItem() {
        IStructuredSelection selection = (IStructuredSelection) signatures.getSelection();
        VerificationResult result = (VerificationResult) selection.getFirstElement();
        return result;
    }

    /**
     * Context menu available in the view.
     */
    private void initContextMenu() {
        MenuManager manager = new MenuManager();
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        Menu menu = manager.createContextMenu(signatures.getControl());
        signatures.getControl().setMenu(menu);
        getSite().registerContextMenu(manager, signatures);
    }

    /**
     * Adds actions (and icons) to the action bar of the view.
     */
    private void contributeToActionBars() {
        IActionBars bars = getViewSite().getActionBars();
        bars.getMenuManager().add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        bars.getToolBarManager().add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    /**
     * Writes the message into the log file.
     *
     * @param message The message to log
     * @param ex The exception to log
     */
    protected void log(final String message, final Exception ex) {
        XmlSecurityPlugin plugin = XmlSecurityPlugin.getDefault();
        IStatus status = new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 0,
                message, ex);
        plugin.getLog().log(status);
    }

    /**
     * Double click in the view.
     */
    private void hookDoubleClickAction() {
        signatures.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(final DoubleClickEvent event) {
                ISelection selection = signatures.getSelection();

                if (selection != null && selection instanceof IStructuredSelection) {
                    Object o = ((IStructuredSelection) selection).getFirstElement();

                    if (o instanceof VerificationResult) {
                        doubleClickAction.run((VerificationResult) o);
                    }
                }

            }
        });
    }

    /**
     * Passes the focus request to the viewer's control.
     */
    public void setFocus() {
        signatures.getControl().setFocus();
    }

    /**
     * Updates the info text of the view showing how many total signatures are
     * available in the XML document and how many of them are valid, invalid and
     * unknown.
     *
     * @param results Input as ArrayList
     */
    private void updateViewInfo(final ArrayList<VerificationResult> results) {
        int totalSignatures = 0;
        int validSignatures = 0;
        int invalidSignatures = 0;
        int unknownSignatures = 0;

        try {
            totalSignatures = results.size();
            for (VerificationResult result : results) {
                if ("valid".equals(result.getStatus())) {
                    validSignatures++;
                } else if ("invalid".equals(result.getStatus())) {
                    invalidSignatures++;
                } else {
                    unknownSignatures++;
                }
            }
        } catch (Exception ex) {
            totalSignatures = 0;
            validSignatures = 0;
            invalidSignatures = 0;
            unknownSignatures = 0;
        }

        setContentDescription(NLS.bind(Messages.signatureInfo,
            new Object[] {totalSignatures, validSignatures, invalidSignatures, unknownSignatures}));
    }
}
