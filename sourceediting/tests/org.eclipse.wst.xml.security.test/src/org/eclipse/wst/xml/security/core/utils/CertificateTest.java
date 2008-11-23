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
import static org.junit.Assert.fail;

import java.io.File;
import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

/**
 * <p>JUnit tests for the Certificate class.</p>
 *
 * @author Dominik Schadow
 * @version 0.5.0
 */
public class CertificateTest {
  /** The Certificate instance. */
  private XmlSecurityCertificate cert = new XmlSecurityCertificate("dummy");

  /**
   * Tear down method. Deletes the generated Java Keystore.
   */
  @After
  public void tearDown() {
    try {
      File file = new File("dos.jks");
      if (file.exists()) {
        file.delete();
      }
    } catch (Exception ex) {
      fail("Keystore does not exist");
    }
  }

  /**
   * Test for certificate generation (Java Keystore).
   */
  @Test
  public void testGenerateCertificate() {
    try {
      HashMap<String, String> certificateData = new HashMap<String, String>();
      certificateData.put("keyalg", "DSA");
//      assertEquals(false, cert.generateKeystoreAndCertificate(certificateData));
      certificateData.put("alias", "dos");
      certificateData.put("CN", "dos");
      certificateData.put("keypass", "unitTest");
      certificateData.put("keystore", "dos.jks");
      certificateData.put("storepass", "unitTest");
//      assertEquals(true, cert.generateKeystoreAndCertificate(certificateData));

      File file = new File("dos.jks");
      assertEquals(true, file.exists());
    } catch (Exception ex) {
      fail("Keystore does not exist");
    }
  }
}
