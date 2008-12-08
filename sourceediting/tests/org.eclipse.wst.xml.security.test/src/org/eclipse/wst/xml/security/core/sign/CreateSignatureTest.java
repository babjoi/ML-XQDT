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
package org.eclipse.wst.xml.security.core.sign;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.xml.security.utils.XMLUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import org.eclipse.wst.xml.security.core.utils.Keystore;
import org.eclipse.wst.xml.security.core.verify.VerificationResult;
import org.eclipse.wst.xml.security.core.verify.VerifySignature;

/**
 * <p>JUnit test class for {@link org.eclipse.wst.xml.security.core.sign.CreateSignature}.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class CreateSignatureTest {
    /** The signature model used in these tests. */
    private Signature signature = null;
    /** The signature id. */
    private static final String SIGNATURE_ID = "JUnitTest";
    /** The temporary file containing the signature. */
    private static final String SIGNED_FILE_NAME = "result.xml";

    /**
     * Sets up the Apache XML Security API.
     *
     * @throws Exception to indicate any exceptional condition
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        org.apache.xml.security.Init.init();
    }

    /**
     * Prepares the SignatureWizard object.
     *
     * @throws Exception to indicate any exceptional condition
     */
    @Before
    public void setUp() throws Exception {
        Keystore sampleKeyStore = new Keystore("resources/sample_keystore.jks", "correct", "JCEKS");
        sampleKeyStore.load();

        signature = new Signature();
        signature.setBsp(false);
        signature.setLaunchEncryptionWizard(false);
        signature.setCanonicalizationAlgorithm("Exclusive with comments");
        signature.setKeyAlias("sample");
        signature.setKeyPassword("correct".toCharArray());
        signature.setDetachedFile(null);
        signature.setFile("resources/FirstSteps.xml");
        signature.setKeystore(sampleKeyStore);
        signature.setKeystorePassword("correct".toCharArray());
        signature.setMessageDigestAlgorithm("SHA 1");
        signature.setResource("document");
        signature.setSignatureAlgorithm("DSA with SHA 1 (DSS)");
        signature.setSignatureId(SIGNATURE_ID);
        signature.setSignatureProperties(null);
        signature.setSignatureType("enveloped");
        signature.setTransformationAlgorithm("None");
        signature.setXpath(null);
    }

    /**
     * Kills the SignatureWizard object and deletes the signed XML document.
     *
     * @throws Exception to indicate any exceptional condition
     */
    @After
    public void tearDown() throws Exception {
        signature = null;

        try {
            File file = new File(SIGNED_FILE_NAME);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception ex) {
            ;
        }
    }

    /**
     * Test method for
     * {@link org.eclipse.wst.xml.security.core.sign.CreateSignature#sign(org.eclipse.wst.xml.security.core.sign.Signature, org.eclipse.jface.text.ITextSelection)}
     * .
     */
    @Test
    public void testSign() {
        CreateSignature sign = new CreateSignature();
        VerifySignature verify = new VerifySignature();
        Document result = null;
        try {
            result = sign.sign(signature, null, null);

            FileOutputStream fos = new FileOutputStream(SIGNED_FILE_NAME);
            if (result != null) {
                XMLUtils.outputDOM(result, fos);
            }
            fos.flush();
            fos.close();

            ArrayList<VerificationResult> signatures = verify
                    .verify(SIGNED_FILE_NAME, SIGNATURE_ID);
            assertEquals("valid", (signatures.get(0)).getStatus());

            signatures = verify.verify(SIGNED_FILE_NAME, "wrongID");
            assertEquals("unknown", (signatures.get(0)).getStatus());
        } catch (Exception ex) {
            fail(ex.getLocalizedMessage());
        }
    }
}
