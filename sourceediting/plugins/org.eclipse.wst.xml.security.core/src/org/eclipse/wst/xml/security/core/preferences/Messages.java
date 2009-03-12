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
package org.eclipse.wst.xml.security.core.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * <p>Externalized strings for the org.eclipse.wst.xml.security.core.preferences package.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public final class Messages extends NLS {
    /** The bundle name. */
    private static final String BUNDLE_NAME = "org.eclipse.wst.xml.security.core.preferences.messages";

    /**
     * Constructor.
     */
    private Messages() {
    }

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    /** Signatures preference page externalized strings. */
    public static String signaturePrefsAlgoCanon, signaturePrefsAlgoMD, signaturePrefsAlgoSign,
            signaturePrefsAlgoTransform, signaturePrefsAlgorithms, signaturePrefsDesc, signaturePrefsId,
            signaturePrefsKeyAlias, signaturePrefsKeystore, signaturePrefsKeystoreFile,
            signaturePrefsKeystoreFileSelect, signaturePrefsResource, signaturePrefsResourceDocument,
            signaturePrefsResourceSelection, signaturePrefsResourceXPath, signaturePrefsResourceXPathExpression,
            signaturePrefsSignatureId, signaturePrefsSignatureType;
    /** Canonicalization preference page externalized strings. */
    public static String xmlSecurityPrefsCanonTarget, xmlSecurityPrefsCanonType, xmlSecurityPrefsDesc;
    /** Encryption preference page externalized strings. */
    public static String encryptionPrefsKeyName, encryptionPrefsAlgoKeyWrap, encryptionPrefsAlgorithms,
            encryptionPrefsDesc, encryptionPrefsEncryptionId, encryptionPrefsEncryptionType, encryptionPrefsId,
            encryptionPrefsKey, encryptionPrefsKeyFile, encryptionPrefsKeyFileSelect, encryptionPrefsResource,
            encryptionPrefsResourceDocument, encryptionPrefsResourceSelection, encryptionPrefsResourceXPath,
            encryptionPrefsResourceXPathExpression, encryptionPrefsEncryptionAlgorithm;
}
