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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.cert.Certificate;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * <p>Manages the Java KeyStores (jks files). The generated Java KeyStores as well
 * as the public and private keys are standard compliant and can be used with
 * other tools too.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class Keystore {
	/** KeyStore file name. */
	private String keyStoreFile = "";
	/** KeyStore password. */
	private char[] keyStorePassword = null;
	private KeyStore keyStore = null;

	/**
	 * Loads the given KeyStore identified by an absolute path and the corresponding KeyStore password.
	 * KeyStore type is normally JCEKS, which is handled by the SunJCE provider. Other KeyStore types
	 * should be possible too but may require a special provider.
	 *
	 * @param keyStoreFile The KeyStore file (absolute path and filename)
	 * @param keyStorePassword The corresponding KeyStore password
	 * @param keyStoreType The KeyStore type
	 * @throws Exception Exception during loading the given KeyStore
	 */
	public Keystore(String keyStoreFile, String keyStorePassword, String keyStoreType) throws Exception {
		this.keyStoreFile = keyStoreFile;
		this.keyStorePassword = keyStorePassword.toCharArray();

		if (keyStoreType.equals("JCEKS")) {
			keyStore = KeyStore.getInstance("JCEKS", "SunJCE");
		} else {
			keyStore = KeyStore.getInstance(keyStoreType);
		}

		keyStore.load(null, this.keyStorePassword);
	}

	/**
	 * Stores a newly created KeyStore.
	 *
	 * @throws Exception Exception during storing the new KeyStore
	 */
	public void store() throws Exception {
		FileOutputStream fos = null;
		FileInputStream fis = null;

		try {
			fos = new FileOutputStream(keyStoreFile);
			fis = new FileInputStream(keyStoreFile);

			keyStore.store(fos, this.keyStorePassword);
			keyStore.load(fis, this.keyStorePassword);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}

	/**
	 * Loads the KeyStore.
	 *
	 * @throws Exception Exception during loading the KeyStore
	 */
    public void load() throws Exception {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(keyStoreFile);

            keyStore.load(fis, this.keyStorePassword);
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * Finalizes the secret key generation by storing the complete KeyStore.
     *
     * @throws Exception Exception during storing the KeyStore
     */
	private void storeKey() throws Exception {
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(keyStoreFile);

			keyStore.store(fos, this.keyStorePassword);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public void storeCertificate(String alias, Certificate cert)
			throws Exception {
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(keyStoreFile);
			keyStore.setCertificateEntry(alias, cert);
			keyStore.store(fos, keyStorePassword);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public void storeKeyPair(KeyPair keyPair, String alias, String password)
			throws Exception {
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(keyStoreFile);
			keyStore.store(fos, keyStorePassword);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * Generates a new secret key with the given parameters and stores it in
	 * the loaded Java KeyStore.
	 *
	 * @param keyAlgorithm The secret key algorithm
	 * @param keyAlgorithmSize The secret key algorithm size
	 * @param keyAlias The private key alias (must be unique in the KeyStore)
	 * @param keyPassword The secret key password
	 * @return Secret key generation result
	 * @throws Exception General exception during key generation
	 */
	public boolean generateSecretKey(String keyAlgorithm, int keyAlgorithmSize,
		String keyAlias, String keyPassword) throws Exception {
		int sizeBefore = keyStore.size();

		KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
		keyGenerator.init(keyAlgorithmSize);
		SecretKeyEntry secretKeyEntry = new SecretKeyEntry(keyGenerator.generateKey());
		keyStore.setEntry(keyAlias, secretKeyEntry, new PasswordProtection(keyPassword.toCharArray()));

		int sizeAfter = keyStore.size();

		if (sizeAfter > sizeBefore) {
			storeKey();
			load();

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the secret key identified by the key alias and the key password.
	 *
	 * @param keyAlias The secret key alias
	 * @param keyPassword The secret key password
	 * @return The secret key, null if it does not exist in the KeyStore
	 * @throws Exception Any exception during loading the secret key
	 */
	public SecretKey getSecretKey(String keyAlias, char[] keyPassword) throws Exception {
		if (keyStore.containsAlias(keyAlias)) {
			SecretKeyEntry ske = (SecretKeyEntry) keyStore.getEntry(keyAlias, new PasswordProtection(keyPassword));

			return ske.getSecretKey();
		} else {
			return null;
		}
	}

	/**
	 * Checks whether the given key alias is contained in the current KeyStore.
	 *
	 * @param keyAlias The key alias to look for
	 * @return Key is contained in current KeyStore
	 */
	public boolean containsKey(String keyAlias) {
		try {
			return keyStore.containsAlias(keyAlias);
		} catch (KeyStoreException e) {
			return false;
		}
	}
}
