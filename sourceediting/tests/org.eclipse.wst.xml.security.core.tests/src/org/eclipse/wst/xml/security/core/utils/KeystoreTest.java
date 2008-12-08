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
package org.eclipse.wst.xml.security.core.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>JUnit tests for {@link org.eclipse.wst.xml.security.core.utils.Keystore}.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class KeystoreTest {
    private XmlSecurityCertificate certificate = new XmlSecurityCertificate();
    private Keystore keyStore = null;
    private Keystore sampleKeyStore = null;
    private static final String KEYSTORE_FILE = "resources/keystore.jks";
    private static final String SAMPLE_KEYSTORE_FILE = "resources/sample_keystore.jks";

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        File tempFile = new File(KEYSTORE_FILE);
        if (tempFile.exists()) {
            assertTrue(tempFile.delete());
        }
    }

    /**
     * Set up method. Sets up the sample keystore used in these test cases.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        keyStore = new Keystore(KEYSTORE_FILE, "storepassword", "JCEKS");
        sampleKeyStore = new Keystore(SAMPLE_KEYSTORE_FILE, "correct", "JCEKS");
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#Keystore(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testKeystore() {
        assertNotNull(keyStore);
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#store()}.
     */
    @Test
    public void testStore() {
        try {
            assertNotNull(keyStore);

            keyStore.store();

            File tempFile = new File(KEYSTORE_FILE);

            assertTrue(tempFile.exists());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#load()}.
     */
    @Test
    public void testLoad() {
        try {
            Keystore temp = new Keystore(SAMPLE_KEYSTORE_FILE, "correct", "JCEKS");
            assertNotNull(temp);

            assertTrue(temp.load());

            temp = new Keystore(SAMPLE_KEYSTORE_FILE, "wrong", "JCEKS");
            assertNotNull(temp);

            assertFalse(temp.load());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#generateSecretKey(java.lang.String, int, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testGenerateSecretKey() {
        try {
            assertNotNull(keyStore);

            keyStore.store();

            File tempFile = new File(KEYSTORE_FILE);

            assertTrue(tempFile.exists());

            assertTrue(keyStore.generateSecretKey(Algorithms.KEY_FILE_ALOGRITHMS[0],
                    Integer.parseInt(Algorithms.KEY_SIZES_AES[0]), "testkey", "keypassword"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#generateCertificate(java.lang.String, java.security.cert.Certificate)}.
     */
    @Test
    public void testGenerateCertificate() {
        try {
            assertNotNull(keyStore);

            keyStore.store();

            assertTrue(keyStore.generateCertificate("testcert", certificate));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#getSecretKey(java.lang.String, char[])}.
     */
    @Test
    public void testGetSecretKey() {
        try {
            assertNotNull(keyStore);

            keyStore.store();

            assertTrue(keyStore.generateSecretKey(Algorithms.KEY_FILE_ALOGRITHMS[0],
                    Integer.parseInt(Algorithms.KEY_SIZES_AES[0]), "testkey", "keypassword"));

            assertNotNull(keyStore.getSecretKey("testkey", "keypassword".toCharArray()));
        } catch (Exception ex) {
            ex.printStackTrace();
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#containsKey(java.lang.String)}.
     */
    @Test
    public void testContainsKey() {
        try {
            assertNotNull(keyStore);

            keyStore.store();

            File tempFile = new File(KEYSTORE_FILE);

            assertTrue(tempFile.exists());

            assertTrue(keyStore.generateSecretKey(Algorithms.KEY_FILE_ALOGRITHMS[0],
                    Integer.parseInt(Algorithms.KEY_SIZES_AES[0]), "testkey", "keypassword"));

            assertTrue(keyStore.containsKey("testkey"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
}
