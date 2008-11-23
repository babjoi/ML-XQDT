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
package org.eclipse.wst.xml.security.core.verify;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * <p>The content provider for the XML Signatures View. Prepares the signatures for display.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class SignatureContentProvider implements IStructuredContentProvider {
    /** Contains all signature information. */
    private Object[] signatures = null;

    /**
     * Returns the elements to display in the viewer when its input is set to the given element.
     * These elements are presented as rows in a table. The result is not modified by the viewer.
     *
     * @param inputElement The input element
     * @return The elements to display
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof List) {
            signatures = ((List) inputElement).toArray();
        } else {
            signatures = null;
        }

        return signatures;
    }

    /**
     * Disposes this content provider.
     */
    public void dispose() {
    }

    /**
     * Notifies this content provider that the given viewer's input has been switched to a different
     * element.
     *
     * @param viewer The viewer
     * @param oldInput The old input element, or null if the viewer did not previously have an input
     * @param newInput The new input element, or null if the viewer does not have an input
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }
}
