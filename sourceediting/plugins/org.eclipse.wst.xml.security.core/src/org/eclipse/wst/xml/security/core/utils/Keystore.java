/*******************************************************************************
 * Copyright (c) 2008 Dominik Schadow - http://www.xml-sicherheit.de All rights reserved. This
 * program and the accompanying materials are made available under the terms of the Eclipse Public
 * License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Dominik Schadow - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.security.core.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.cert.Certificate;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * <p>
 * Manages the Java KeyStores (jks files). The generated Java KeyStores as well as the public and
 * private keys are standard compliant and can be used with other tools too.
 * </p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class Keystore {
    /** KeyStore file name. */
    private String file = "";
    /** KeyStore password. */
    private char[] password = null;
    private KeyStore keyStore = null;

    /**
     * Loads the given KeyStore identified by an absolute path and the corresponding KeyStore
     * password. KeyStore type is normally JCEKS, which is handled by the SunJCE provider. Other
     * KeyStore types should be possible too but may require a special provider.
     *
     * @param file The KeyStore file (absolute path and filename)
     * @param password The corresponding KeyStore password
     * @param type The KeyStore type
     * @throws Exception Exception during loading the given KeyStore
     */
    public Keystore(String file, String password, String type) throws Exception {
        this.file = file;
        this.password = password.toCharArray();

        if ("JCEKS".equals(type)) {
            keyStore = KeyStore.getInstance("JCEKS", "SunJCE");
        } else {
            keyStore = KeyStore.getInstance(type);
        }

        keyStore.load(null, this.password);
    }

    /**
     * Stores a newly created KeyStore. Remember to call <code>load()</code>
     * again to get access to the extended KeyStore.
     *
     * @throws Exception Exception during storing the new KeyStore
     */
    public void store() throws Exception {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);

            keyStore.store(fos, password);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * Loads the KeyStore.
     *
     * @throws Exception Exception during closing the FileInputStream
     */
    public boolean load() throws Exception {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

            keyStore.load(fis, password);

            return true;
        } catch (IOException ex) {
            return false;
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * Generates a new secret key with the given parameters and stores it in the selected Java
     * KeyStore.
     *
     * @param algorithm The secret key algorithm
     * @param algorithmSize The secret key algorithm size
     * @param alias The private key alias (must be unique in the KeyStore)
     * @param password The secret key password
     * @return Secret key generation result
     * @throws Exception General exception during key generation
     */
    public boolean generateSecretKey(String algorithm, int algorithmSize, String alias, String password) throws Exception {
        int sizeBefore = keyStore.size();

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(algorithmSize);
        SecretKeyEntry secretKeyEntry = new SecretKeyEntry(keyGenerator.generateKey());
        keyStore.setEntry(alias, secretKeyEntry, new PasswordProtection(password.toCharArray()));

        int sizeAfter = keyStore.size();

        if (sizeAfter > sizeBefore) {
            //store();

            return true;
        } else {
            return false;
        }
    }

    /**
     * Generates a new certificate with the given parameters and stores it in the selected Java
     * KeyStore.
     *
     * @param alias The certificate key alias (must be unique in the KeyStore)
     * @param cert The certificate
     * @return Certificate key generation result
     * @throws Exception General exception during certificate generation
     */
    public boolean generateCertificate(String alias, Certificate cert) throws Exception {
        int sizeBefore = keyStore.size();

        keyStore.setCertificateEntry(alias, cert);

        int sizeAfter = keyStore.size();

        if (sizeAfter > sizeBefore) {
            //store();

            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the secret key identified by the key alias and the key password.
     *
     * @param alias The secret key alias
     * @param password The secret key password
     * @return The secret key, null if it does not exist in the KeyStore
     * @throws Exception Any exception during loading the secret key
     */
    public SecretKey getSecretKey(String alias, char[] password) throws Exception {
        if (keyStore.containsAlias(alias)) {
            SecretKeyEntry ske = (SecretKeyEntry) keyStore.getEntry(alias, new PasswordProtection(password));

            return ske.getSecretKey();
        } else {
            return null;
        }
    }

    /**
     * Checks whether the given key alias is contained in the current KeyStore.
     *
     * @param alias The key alias to look for
     * @return Key is contained in current KeyStore
     */
    public boolean containsKey(String alias) {
        try {
            return keyStore.containsAlias(alias);
        } catch (KeyStoreException e) {
            return false;
        }
    }

    public Certificate getCertificate(String alias) {
        try {
            if (keyStore.containsAlias(alias)) {
                return keyStore.getCertificate(alias);
            } else {
                return null;
            }
        } catch (KeyStoreException e) {
            return null;
        }
    }
}
