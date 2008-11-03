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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * <p>Compares the signatures in the view by their signature id.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class SignatureComparator extends ViewerComparator {
    /**
     * Compares the signatures by signature ID.
     *
     * @param viewer The viewer to sort
     * @param signature1 The first VerificationResult object
     * @param signature2 The second VerficationResult object
     * @return The sorted order
     */
    public int compare(final Viewer viewer, final Object signature1, final Object signature2) {
        if (!(signature1 instanceof VerificationResult && signature2 instanceof VerificationResult)) {
            return 1;
        }

        String id1 = ((VerificationResult) signature1).getId();
        String id2 = ((VerificationResult) signature2).getId();

        return getComparator().compare(id1, id2);
    }
}
