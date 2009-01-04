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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.security.PrivateKey;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>JUnit tests for {@link org.eclipse.wst.xml.security.core.utils.Keystore}. Uses the existing
 * sample keystore (<i>resources/sample_keystore.jks</i>) for loading operations and creates a
 * temporary keystore (<i>resources/temp_keystore.jks</i>) for creating operations. The sample
 * keystore is not changed in these tests.</p>
 *
 * <p>The sample keystore was generated with the following command (single line):<br/>
 * <code>keytool -genkey -alias sampleKey -keypass sampleKey -keystore sample_keystore.jks
 * -storepass sampleKeystore</code></p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class KeystoreTest {
    private XmlSecurityCertificate certificate = new XmlSecurityCertificate();
    private Keystore keystore = null;
    private Keystore tempKeystore = null;
    private static final String TEMP_KEYSTORE_PATH = "resources/temp_keystore.jks";
    private static final String KEYSTORE_PATH = "resources/sample_keystore.jks";
    private static final String KEYSTORE_PASSWORD = "sampleKeystore";
    private static final String KEY_ALIAS = "sampleKey";
    private static final String KEY_PASSWORD = "sampleKey";

    /**
     * Makes sure that the temporary keystore file used in these tests does not exist
     * any more.
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
     * Deletes the temporary keystore file created for these tests.
     *
     * @throws Exception during deleting temporary keystore file
     */
    @AfterClass
    public static void setUpAfterClass() throws Exception {
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
        keystore = new Keystore(KEYSTORE_PATH, KEYSTORE_PASSWORD, Globals.KEYSTORE_TYPE);
        tempKeystore = new Keystore(TEMP_KEYSTORE_PATH, KEYSTORE_PASSWORD, Globals.KEYSTORE_TYPE);
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#Keystore(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testKeystore() {
        assertNotNull(keystore);
        assertNotNull(tempKeystore);
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#store()}.
     */
    @Test
    public void testStore() {
        try {
            File tempFile = new File(TEMP_KEYSTORE_PATH);

            assertFalse(tempFile.exists());

            assertNotNull(tempKeystore);
            tempKeystore.store();

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
            assertNotNull(keystore);
            assertTrue(keystore.load());

            Keystore temp = new Keystore(KEYSTORE_PATH, "wrong", Globals.KEYSTORE_TYPE);
            assertNotNull(temp);
            assertFalse(temp.load());

            assertNotNull(tempKeystore);
            assertTrue(tempKeystore.load());

            temp = new Keystore(TEMP_KEYSTORE_PATH, "wrong", Globals.KEYSTORE_TYPE);
            assertNotNull(temp);
            assertFalse(temp.load());
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
            assertNotNull(tempKeystore);
            tempKeystore.store();

            assertTrue(tempKeystore.generateCertificate(KEY_ALIAS, certificate));

            assertTrue(tempKeystore.containsKey(KEY_ALIAS));
            assertFalse(tempKeystore.containsKey("wrong"));

            assertNotNull(tempKeystore.getCertificate(KEY_ALIAS));
            assertNull(tempKeystore.getCertificate("wrong"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#generateSecretKey(java.lang.String, char, javax.crypto.SecretKey)}.
     */
    @Test
    public void testGenerateSecretKey() {
        try {
            assertNotNull(tempKeystore);
            tempKeystore.store();

            KeyGenerator kg = KeyGenerator.getInstance("DES");
            kg.init(56);

            assertTrue(tempKeystore.generateSecretKey(KEY_ALIAS, KEY_PASSWORD.toCharArray(), kg.generateKey()));

            tempKeystore.store();

            SecretKey key = tempKeystore.getSecretKey(KEY_ALIAS, KEY_PASSWORD.toCharArray());
            assertNotNull(key);

            assertEquals("DES", key.getAlgorithm());
            assertEquals("RAW", key.getFormat());

            key = tempKeystore.getSecretKey(KEY_ALIAS, "wrong".toCharArray());
            assertNull(key);

            kg = KeyGenerator.getInstance("AES");
            kg.init(256);

            assertTrue(tempKeystore.generateSecretKey(KEY_ALIAS + "2", KEY_PASSWORD.toCharArray(), kg.generateKey()));

            tempKeystore.store();

            key = tempKeystore.getSecretKey(KEY_ALIAS + "2", KEY_PASSWORD.toCharArray());
            assertNotNull(key);

            assertEquals("AES", key.getAlgorithm());
            assertEquals("RAW", key.getFormat());

            key = tempKeystore.getSecretKey(KEY_ALIAS + "2", "wrong".toCharArray());
            assertNull(key);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#getPrivateKey(java.lang.String, char[])}.
     */
    @Test
    public void testGetPrivateKey() {
        try {
            assertNotNull(keystore);
            keystore.load();

            PrivateKey key = keystore.getPrivateKey(KEY_ALIAS, KEY_PASSWORD.toCharArray());
            assertNotNull(key);

            assertEquals("DSA", key.getAlgorithm());
            assertEquals("PKCS#8", key.getFormat());

            assertNotNull(tempKeystore);
            tempKeystore.store();

            key = tempKeystore.getPrivateKey(KEY_ALIAS, KEY_PASSWORD.toCharArray());
            assertNull(key);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#containsKey(java.lang.String)}.
     */
    @Test
    public void testContainsKey() {
        try {
            assertNotNull(keystore);
            keystore.load();

            assertTrue(keystore.containsKey(KEY_ALIAS));
            assertFalse(keystore.containsKey("wrong"));

            assertNotNull(tempKeystore);
            tempKeystore.store();

            assertFalse(tempKeystore.containsKey(KEY_ALIAS));
            assertFalse(tempKeystore.containsKey("wrong"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test method for {@link org.eclipse.wst.xml.security.core.utils.Keystore#getCertificate(java.lang.String)}.
     */
    @Test
    public void testGetCertificate() {
        try {
            assertNotNull(keystore);
            keystore.load();

            assertNotNull(keystore.getCertificate(KEY_ALIAS));
            assertNull(keystore.getCertificate("wrong"));

            assertNotNull(tempKeystore);
            tempKeystore.store();

            assertNull(tempKeystore.getCertificate(KEY_ALIAS));
            assertNull(tempKeystore.getCertificate("wrong"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
}
