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

import org.eclipse.wst.xml.security.core.verify.VerificationResult;
import org.eclipse.wst.xml.security.core.verify.VerifySignature;

/**
 * <p>JUnit test class for {@link org.eclipse.wst.xml.security.core.sign.CreateSignature}.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class CreateSignatureTest {
    /** The SignatureWizard object to test. */
    private Signature sigWiz = null;
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
        sigWiz = new Signature();
        sigWiz.setBsp(false);
        sigWiz.setLaunchEncryptionWizard(false);
        sigWiz.setCanonicalizationAlgorithm("Exclusive with comments");
        sigWiz.setKeyAlias("dominik");
        sigWiz.setKeyPassword("dominik".toCharArray());
        sigWiz.setDetachedFile(null);
        sigWiz.setFile("resources/FirstSteps.xml");
        sigWiz.setKeystore("resources/dos.jks");
        sigWiz.setKeystorePassword("dominik".toCharArray());
        sigWiz.setMessageDigestAlgorithm("SHA 1");
        sigWiz.setResource("document");
        sigWiz.setSignatureAlgorithm("DSA with SHA 1 (DSS)");
        sigWiz.setSignatureId(SIGNATURE_ID);
        sigWiz.setSignatureProperties(null);
        sigWiz.setSignatureType("enveloped");
        sigWiz.setTransformationAlgorithm("None");
        sigWiz.setXpath(null);
    }

    /**
     * Kills the SignatureWizard object and deletes the signed XML document.
     *
     * @throws Exception to indicate any exceptional condition
     */
    @After
    public void tearDown() throws Exception {
        sigWiz = null;

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
            result = sign.sign(sigWiz, null, null);

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
