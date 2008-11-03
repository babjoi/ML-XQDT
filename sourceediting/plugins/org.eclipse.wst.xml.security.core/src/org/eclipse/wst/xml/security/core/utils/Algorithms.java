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

/**
 * <p>This utility class contains a public list of different algorithms
 * and corresponding key sizes available in the XML Security Tools.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public final class Algorithms {
    /**
     * Private Constructor to avoid instantiation.
     */
    private Algorithms() { }

    /** Key file algorithms to encrypt a key file. */
    public static final String[] KEY_FILE_ALOGRITHMS = {"AES", "Blowfish", "DES", "Triple DES"};
    /** Key file algorithm sizes. */
    public static final String[] KEY_FILE_ALGORITHMS_SIZES = {"56", "128", "192", "256",
        "384", "448"};
    /** Canonicalization algorithms. */
    public static final String[] CANONICALIZATION_ALOGRITHMS = {"Exclusive without comments",
            "Exclusive with comments", "Inclusive without comments", "Inclusive with comments"};
    /** Canonicalization algorithms for the Basic Security Profile. */
    public static final String[] CANONICALIZATION_ALOGRITHMS_BSP = {"Exclusive without comments"};
    /** Transformation algorithms for the Basic Security Profile. */
    public static final String[] TRANSFORMATION_ALOGRITHMS_BSP = {"Exclusive without comments"};
    /** Transformation algorithms including none. */
    public static final String[] TRANSFORMATION_ALOGRITHMS = {"Exclusive without comments",
        "Exclusive with comments", "Inclusive without comments", "Inclusive with comments", "None"};
    /** Message Digest algorithms. */
    public static final String[] MD_ALOGRITHMS = {"MD5", "RIPEMD 160", "SHA 1", "SHA 256",
        "SHA 384", "SHA 512"};
    /** Message Digest algorithms for the Basic Security Profile. */
    public static final String[] MD_ALOGRITHMS_BSP = {"SHA 1"};
    /** Signature algorithms. */
    public static final String[] SIGNATURE_ALOGRITHMS = {"DSA with SHA 1 (DSS)",
        "Elliptic Curve DSA (ECDSA)", "RSA with MD5", "RSA", "RSA with RIPEMD160",
        "RSA with SHA 1", "RSA with SHA 256", "RSA with SHA 384", "RSA with SHA 512"};
    /** Signature algorithms DSA. */
    public static final String[] SIGNATURE_ALOGRITHMS_DSA = {"DSA with SHA 1 (DSS)"};
    /** Signature algorithms EC. */
    public static final String[] SIGNATURE_ALOGRITHMS_EC = {"Elliptic Curve DSA (ECDSA)"};
    /** Signature algorithms RSA. */
    public static final String[] SIGNATURE_ALOGRITHMS_RSA = {"RSA with MD5", "RSA",
        "RSA with RIPEMD160", "RSA with SHA 1", "RSA with SHA 256", "RSA with SHA 384",
        "RSA with SHA 512"};
    /** Signature algorithms for the Basic Security Profile. */
    public static final String[] SIGNATURE_ALOGRITHMS_BSP = {"RSA with SHA 1"};
    /** None algorithm. */
    public static final String[] NONE_ALGORITHM = {"None"};
    /** Key wrap algorithms without the Basic Security Profile. */
    public static final String[] KEY_WRAP_ALGORITHMS = {"AES-128 Key Wrap",
        "AES-192 Key Wrap", "AES-256 Key Wrap", "Triple DES Key Wrap"};
    /** Key wrap algorithms for the Basic Security Profile. */
    public static final String[] KEY_WRAP_ALGORITHMS_BSP = {"AES-128 Key Wrap", "AES-256 Key Wrap",
        "Triple DES Key Wrap"};
    /** Encryption algorithms without the Basic Security Profile. */
    public static final String[] ENCRYPTION_ALGORITHMS = {"AES 128", "AES 192",
        "AES 256", "Triple DES"};
    /** Encryption algorithms for the Basic Security Profile. */
    public static final String[] ENCRYPTION_ALGORITHMS_BSP = {"AES 128", "AES 256", "Triple DES"};
    /** Key sizes for the Advanced Encryption Standard (AES) algorithm. */
    public static final String[] KEY_SIZES_AES = {"128", "192", "256"};
    /** Key sizes for the Blowfish algorithm. */
    public static final String[] KEY_SIZES_BLOWFISH = {"128", "192", "256"};
    /** Key sizes for the DES algorithm. */
    public static final String[] KEY_SIZES_DES = {"56"};
    /** Key sizes for the Triple DES algorithm. */
    public static final String[] KEY_SIZES_DESEDE = {"112", "168"};
    /** Certificate algorithms (Java Keystores). */
    public static final String[] CERTIFICATE_ALGORITHMS = {"DSA", "EC", "RSA"};
    /** Certificate algorithms RSA (Java Keystores). */
    public static final String[] CERTIFICATE_ALGORITHMS_RSA = {"RSA"};
}
