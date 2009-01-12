/*******************************************************************************
 * Copyright (c) 2008 Dominik Schadow - http://www.xml-sicherheit.de All rights reserved. This
 * program and the accompanying materials are made available under the terms of the Eclipse Public
 * License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>JUnit tests for the Certificate class.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class CertificateTest {
    private Keystore tempKeystore = null;
    /** The Certificate instance. */
    private XmlSecurityCertificate cert = new XmlSecurityCertificate("dummy");
    private static final String TEMP_KEYSTORE_PATH = "resources/temp_keystore.jks";
    private static final String KEYSTORE_PASSWORD = "sampleKeystore";

    /**
     * Makes sure that the temporary keystore file used in these tests does not exist any more.
     *
     * @throws Exception during deleting temporary keystore file
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        File tempFile = new File(TEMP_KEYSTORE_PATH);
        if (tempFile.exists()) {
            assertTrue(tempFile.delete());
        }
    }

    /**
     * Set up method. Sets up the sample keystore used in these test cases.
     *
     * @throws Exception during loading the sample keystore
     */
    @Before
    public void setUp() throws Exception {
        tempKeystore = new Keystore(TEMP_KEYSTORE_PATH, KEYSTORE_PASSWORD, Globals.KEYSTORE_TYPE);
    }

    /**
     * Test for certificate generation (Java Keystore).
     */
    @Test
    public void testGenerateCertificate() {
        try {
            HashMap<String, String> certificateData = new HashMap<String, String>();
            certificateData.put("keyalg", "DSA");
            // assertEquals(false, cert.generateKeystoreAndCertificate(certificateData));
            certificateData.put("alias", "dos");
            certificateData.put("CN", "dos");
            certificateData.put("keypass", "unitTest");
            certificateData.put("keystore", "dos.jks");
            certificateData.put("storepass", "unitTest");
            // assertEquals(true, cert.generateKeystoreAndCertificate(certificateData));


            File tempFile = new File(TEMP_KEYSTORE_PATH);
            assertFalse(tempFile.exists());

            assertNotNull(tempKeystore);
            tempKeystore.store();

            assertTrue(tempFile.exists());
        } catch (Exception ex) {
            fail("Keystore does not exist");
        }
    }
}
