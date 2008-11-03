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
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.crypto.KeyGenerator;

/**
 * <p>Generates a new X.509 certificate or a new private key in a Java KeyStore with the help
 * of the <b>Digital Signature Wizard</b> or the <b>Encryption Wizard</b>.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class Certificate {
    /**
     * <p>Generates a new Java Keystore with a new X.509 certificate based on the settings in the wizard. Prepares the
     * command for the Java KeyTool to generate the keystore and the certificate.</p>
     *
     * <p><i>keyalg</i>, <i>alias</i>, <i>CN</i>, <i>keypass</i>, <i>keystore</i> and <i>storepass</i> are mandatory
     * fields. <i>OU</i>, <i>O</i>, <i>L</i>, <i>ST</i> and <i>C</i> are optional fields.</p>
     *
     * @param certificateData The HashMap with the certificate data
     * @throws Exception to indicate any exceptional condition
     * @return Result of the keystore generation
     */
    public boolean generateKeystoreAndCertificate(HashMap<String, String> certificateData) throws Exception {
        StringBuffer cmd = prepareKeytoolCommand(certificateData);

        if (cmd == null) {
            return false;
        } else {
            keytool(cmd.toString());

            return true;
        }
    }

    /**
     * <p>Generates a new X.509 certificate in an existing Java Keystore based on the settings in the wizard.
     * Prepares the command for the Java KeyTool to open the keystore and insert the certificate.</p>
     *
     * <p><i>keyalg</i>, <i>alias</i>, <i>CN</i>, <i>keypass</i>, <i>keystore</i> and <i>storepass</i> are mandatory
     * fields. <i>OU</i>, <i>O</i>, <i>L</i>, <i>ST</i> and <i>C</i> are optional fields.</p>
     *
     * @param certificateData The HashMap with the certificate data
     * @throws Exception to indicate any exceptional condition
     * @return Result of the keystore generation
     */
    public boolean generateCertificate(HashMap<String, String> certificateData) throws Exception {
        StringBuffer cmd = prepareKeytoolCommand(certificateData);

        if (cmd == null) {
            return false;
        } else {
            keytool(cmd.toString());

            return true;
        }
    }

    /**
     * Prepares the command for the keytool based on the information given by the certificate data HashMap.
     *
     * @param certificateData The HashMap with the certificate data
     * @return The command for the Java KeyTool
     */
    private StringBuffer prepareKeytoolCommand(HashMap<String, String> certificateData) {
        String dname = "";
        StringBuffer cmd;

        if (certificateData.get("keyalg") != null) {
            cmd = new StringBuffer("-genkey -keyalg " + certificateData.get("keyalg"));
        } else {
            return null;
        }

        // mandatory fields
        if (certificateData.get("alias") != null) {
            cmd.append(" -alias ").append(certificateData.get("alias"));
        } else {
            return null;
        }

        if (certificateData.get("CN") != null) {
            dname += "CN=" + certificateData.get("CN") + " ";
        } else {
            return null;
        }

        // optional fields
        if (certificateData.get("OU") != null) {
            dname += "OU=" + certificateData.get("OU") + " ";
        }
        if (certificateData.get("O") != null) {
            dname += "O=" + certificateData.get("O") + " ";
        }
        if (certificateData.get("L") != null) {
            dname += "L=" + certificateData.get("L") + " ";
        }
        if (certificateData.get("ST") != null) {
            dname += "ST=" + certificateData.get("ST") + " ";
        }
        if (certificateData.get("C") != null) {
            dname += "C=" + certificateData.get("C") + " ";
        }

        cmd.append(" -dname \"").append(dname).append("\"");

        // mandatory fields
        if (certificateData.get("keypass") != null) {
            cmd.append(" -keypass ").append(certificateData.get("keypass")).append(" -validity 365");
        } else {
            return null;
        }
        if (certificateData.get("keystore") != null) {
            cmd.append(" -keystore ").append("\"" + certificateData.get("keystore") + "\"");
        } else {
            return null;
        }
        if (certificateData.get("storepass") != null) {
            cmd.append(" -storepass ").append(certificateData.get("storepass"));
        } else {
            return null;
        }
        return cmd;
    }

    /**
     * Prepares the command for the Java KeyTool and calls it. Generates the Keystore and stores it in the currently
     * active Eclipse project directory using the entered <i>CommonName (CN)</i> as filename.
     *
     * @param cmd The command for the Java KeyTool
     * @throws Exception to indicate any exceptional condition
     */
    private void keytool(String cmd) throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(cmd, " ");
        String quoteBuffer = null;
        String token = null;
        while (st.hasMoreTokens()) {
            token = st.nextToken();

            if (quoteBuffer == null) {
                if (token.startsWith("\"")) {
                    quoteBuffer = token.substring(1);
                } else {
                    list.add(token);
                }
            } else {
                quoteBuffer += " " + token;
            }
            if (token.endsWith("\"")) {
                String str = quoteBuffer.substring(0, quoteBuffer.length() - 1);
                list.add(str);
                quoteBuffer = null;
            }
        }

        String[] args = new String[list.size()];
        list.toArray(args);
        // TODO Look for a better (and legal) way
//        KeyTool.main(args);
    }

    /**
     * Prepares the generation of a new private key. Some values in the HashMap may be empty, like the <i>storepass</i>
     * and the <i>private key password</i>.
     *
     * @param privateKeyData The private key data
     * @return Result of the private key generation
     */
    public boolean insertPrivateKey(HashMap<String, String> privateKeyData) {
        try {
            String keystoreFile = "";
            String storepass = "";
            String pkAlgorithm = "";
            int pkAlgorithmSize = 0;
            String pkAlias = "";
            String pkPassword = "";

            // mandatory fields
            if (privateKeyData.get("keystore") != null) {
                keystoreFile = privateKeyData.get("keystore");
            } else {
                return false;
            }
            if (privateKeyData.get("pkAlgorithm") != null) {
                pkAlgorithm = privateKeyData.get("pkAlgorithm");
            } else {
                return false;
            }
            if (privateKeyData.get("pkAlgorithmSize") != null) {
                pkAlgorithmSize = Integer.parseInt(privateKeyData.get("pkAlgorithmSize"));
            } else {
                return false;
            }
            if (privateKeyData.get("pkAlias") != null) {
                pkAlias = privateKeyData.get("pkAlias");
            } else {
                return false;
            }

            // optional fields
            if (privateKeyData.get("storepass") != null) {
                storepass = privateKeyData.get("storepass");
            }
            if (privateKeyData.get("pkPassword") != null) {
                pkPassword = privateKeyData.get("pkPassword");
            }

            generatePrivateKey(keystoreFile, storepass, pkAlgorithm, pkAlgorithmSize, pkAlias, pkPassword);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Generates a new private key with the given parameters and stores it in the selected Java KeyStore.
     *
     * @param keystoreFile The keystore file as String
     * @param storepass The keystore password
     * @param pkAlgorithm The private key algorithm
     * @param pkAlgorithmSize The private key algorithm size
     * @param pkAlias The private key alias (must be unique in the keystore)
     * @param pkPassword The private key password
     * @throws Exception to indicate any exceptional condition
     */
    private void generatePrivateKey(String keystoreFile, String storepass, String pkAlgorithm, int pkAlgorithmSize,
        String pkAlias, String pkPassword) throws Exception {
        FileInputStream fis = null;

        try {
            KeyStore keystore = KeyStore.getInstance("JCEKS", "SunJCE");
            fis = new FileInputStream(keystoreFile);
            keystore.load(fis, storepass.toCharArray());

            KeyGenerator keyGenerator = KeyGenerator.getInstance(pkAlgorithm);
            keyGenerator.init(pkAlgorithmSize);
            SecretKeyEntry skey = new SecretKeyEntry(keyGenerator.generateKey());
            keystore.setEntry(pkAlias, skey, new PasswordProtection(pkPassword.toCharArray()));
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * Generates a keystore with a new private key.
     *
     * @param keystoreData The keystore data
     * @return Keystore and private key generation result
     */
    public boolean generateKeystoreAndPrivateKey(HashMap<String, String> keystoreData) {
        // -storetype JCEKS
        try {
            KeyStore ks = KeyStore.getInstance("JCEKS", "SunJCE");
        } catch (KeyStoreException e) {
            return false;
        } catch (NoSuchProviderException e) {
            return false;
        }
        return true;
    }
}
