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
package org.eclipse.wst.xml.security.core.decrypt;

/**
 * <p>Model for the <i>XML Decryption Wizard</i>.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class Decryption {
    /** XML document to decrypt. */
    private String file;
    /** The KeyStore. */
    private String keyStore;
    /** The KeyStore password. */
    private String keyStorePassword;
    /** Encryption ID. */
    private String encryptionId;
    /** The secrets key name (alias). */
	private String keyName;
	/** The secrets key password. */
	private char[] keyPassword;

    /**
     * The XML document to decrypt.
     *
     * @param file The selected XML document to decrypt
     */
    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * Sets the KeyStore in which the secret key was stored.
     *
     * @param keyStore KeyStore containing the secret key
     */
    public void setKeyStore(final String keyStore) {
        this.keyStore = keyStore;
    }

    /**
     * Sets the KeyStore password.
     *
     * @param keyStorePassword KeyStore password
     */
    public void setKeyStorePassword(final String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * Sets the encryption ID.
     *
     * @param encryptionId The encryption ID
     */
    public void setEncryptionId(final String encryptionId) {
        this.encryptionId = encryptionId;
    }

    /**
     * Sets the key alias name.
     *
     * @param keyName The key alias
     */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * Sets the key password.
	 *
	 * @param keyPassword The key password
	 */
	public void setKeyPassword(char[] keyPassword) {
		this.keyPassword = keyPassword;
	}

    /**
     * Returns the XML document to decrypt.
     *
     * @return The XML document to decrypt
     */
    public String getFile() {
        return file;
    }

    /**
     * Returns the KeyStore.
     *
     * @return The KeyStore
     */
    public String getKeyStore() {
        return keyStore;
    }

    /**
     * Returns the KeyStore password.
     *
     * @return The KeyStore password
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * Returns the encryption ID.
     *
     * @return Encryption ID.
     */
    public String getEncryptionId() {
        return encryptionId;
    }

    /**
     * Returns the secret keys name (alias).
     *
     * @return The key name
     */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * Returns the secret keys password.
	 *
	 * @return The key password
	 */
	public char[] getKeyPassword() {
		return keyPassword;
	}
}
