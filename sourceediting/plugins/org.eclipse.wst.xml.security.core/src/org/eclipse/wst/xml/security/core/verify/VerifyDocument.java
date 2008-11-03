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
package org.eclipse.wst.xml.security.core.verify;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.eclipse.wst.xml.security.core.utils.Globals;
import org.eclipse.wst.xml.security.core.utils.SignatureNamespaceContext;
import org.eclipse.wst.xml.security.core.utils.Utils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 * <p>Verifies all signatures in the selected XML document and displays the results in the <i>XML
 * Digital Signatures View</i>.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class VerifyDocument {
    /**
     * This method verifies all XML Digital Signatures in the XML document.
     *
     * @param signatureFileName Path and filename of the XML document
     * @return Result of verification
     * @throws Exception to indicate any exceptional condition
     */
    public ArrayList<VerificationResult> verify(final String signatureFileName) throws Exception {
        XMLSignature signature = null;
        KeyInfo keyInfo = null;
        boolean schemaValidate = false;
        final String signatureSchemaFile = "data/xmldsig-core-schema.xsd"; //$NON-NLS-1$
        File file = new File(signatureFileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ArrayList<VerificationResult> signatures = new ArrayList<VerificationResult>();

        if (schemaValidate) {
            dbf.setAttribute(Globals.SCHEMA, Boolean.TRUE);
            dbf.setAttribute(Globals.DOM, Boolean.TRUE);
            dbf.setValidating(true);
            dbf.setAttribute(Globals.SAX, Boolean.TRUE);
        }

        dbf.setNamespaceAware(true);
        dbf.setAttribute(Globals.SAX_NAMESPACES, Boolean.TRUE);

        if (schemaValidate) {
            dbf.setAttribute(Globals.EXTERNAL_SCHEMA_LOC,
                    org.apache.xml.security.utils.Constants.SignatureSpecNS + " " //$NON-NLS-1$
                            + signatureSchemaFile);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());

        if (schemaValidate) {
            db.setEntityResolver(new org.xml.sax.EntityResolver() {
                public org.xml.sax.InputSource resolveEntity(final String publicId, final String systemId)
                    throws org.xml.sax.SAXException {
                    if (systemId.endsWith("xmldsig-core-schema.xsd")) { //$NON-NLS-1$
                        try {
                            return new org.xml.sax.InputSource(new FileInputStream(
                                    signatureSchemaFile));
                        } catch (FileNotFoundException ex) {
                            throw new org.xml.sax.SAXException(ex);
                        }
                    }
                    return null;
                }
            });
        }

        XPath xpath = XPathFactory.newInstance().newXPath();
        NamespaceContext ns = new SignatureNamespaceContext();
        InputSource inputSource = new InputSource(new java.io.FileInputStream(file));
        xpath.setNamespaceContext(ns);
        NodeList signatureNodes = (NodeList) xpath.evaluate("//ds:Signature", inputSource, XPathConstants.NODESET);
        int signatureNodesLength = signatureNodes.getLength();
        Element signatureElement = null;
        String signatureId = ""; //$NON-NLS-1$
        String status = "";
        String type = "", algorithm = ""; //$NON-NLS-1$ //$NON-NLS-2$

        for (int i = 0; i < signatureNodesLength; i++) {
            try {
                signatureElement = (Element) signatureNodes.item(i);

                if (signatureElement != null) { // document contains Signature element
                    signatureId = signatureElement.getAttribute("Id"); //$NON-NLS-1$
                    signature = new XMLSignature(signatureElement, file.toURI().toString());
                    keyInfo = signature.getKeyInfo();

                    if (keyInfo != null) { // signature contains KeyInfo element
                        X509Certificate cert = keyInfo.getX509Certificate();
                        PublicKey pk = keyInfo.getPublicKey();

                        if (cert != null) { // X509 certificate
                            type = cert.getType();
                            algorithm = cert.getSigAlgName();
                            if (signature.checkSignatureValue(cert)) {
                                status = Verification.VALID;
                            } else {
                                status = Verification.INVALID;
                            }
                        } else if (pk != null) { // public key
                            type = ""; //$NON-NLS-1$
                            algorithm = pk.getAlgorithm();
                            if (signature.checkSignatureValue(pk)) {
                                status = Verification.VALID;
                            } else {
                                status = Verification.INVALID;
                            }
                        } else { // neither certificate nor public key
                            status = Verification.UNKNOWN;
                        }
                    } else { // no KeyInfo element
                        status = Verification.UNKNOWN;
                    }
                }

                signatures.add(new VerificationResult(status, signatureId, type, algorithm, signature));
            } catch (Exception ex) {
                signatures.add(new VerificationResult(Verification.UNKNOWN, signatureId, type, algorithm, signature));
                Utils.logError(ex, "Error during signature verification"); //$NON-NLS-1$
            }
        }

        return signatures;
    }
}
