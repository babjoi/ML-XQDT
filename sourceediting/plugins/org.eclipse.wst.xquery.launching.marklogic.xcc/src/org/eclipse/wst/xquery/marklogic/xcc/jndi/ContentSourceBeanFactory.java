/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.xcc.jndi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

import org.eclipse.wst.xquery.marklogic.xcc.ContentSourceFactory;


/**
 * <p>
 * This class implements the JNDI {@link ObjectFactory} interface and may be used in a J2EE
 * container as a source of {@link org.eclipse.wst.xquery.marklogic.xcc.ContentSource} instances. This class is
 * preferred over {@link ContentSourceBean} if you have the option because it does a little more
 * consistency checking when creating new objects. Neither does it depend on a container-provided
 * bean factory.
 * </p>
 * <p>
 * JNDI resources are typically specified as part of the container configuration. The specifics of
 * configuration vary by container. Below is an example of using this class with Apache <a
 * href="http://tomcat.apache.org/tomcat-5.5-doc/index.html">Tomcat version 5.5</a> to configure a
 * {@link org.eclipse.wst.xquery.marklogic.xcc.ContentSource}. Further details of JNDI configuration in Tomcat are
 * available on the <a
 * href="http://tomcat.apache.org/tomcat-5.5-doc/jndi-resources-howto.html">JNDI-HowTo page</a>.
 * </p>
 * <p>
 * There are two places where JNDI resources can be configured in the Tomcat 5.5 container. One is
 * globally in Tomcat's config file in $CATALINA_HOME/conf/server.xml, the other is within an
 * individual webapp in META-INF/context.xml. In either file, an XML element like this should be
 * placed as a child of the &lt;Context&gt; element, like this:
 * </p>
 * 
 * <pre class="codesample">
 * &lt;Context path=&quot;/&quot;&gt;
 *    &lt;Resource name=&quot;marklogic/ContentSource&quot; auth=&quot;Container&quot;
 *         type=&quot;org.eclipse.wst.xquery.marklogic.xcc.ContentSource&quot;
 *         factory=&quot;org.eclipse.wst.xquery.marklogic.xcc.jndi.ContentSourceBeanFactory&quot;
 *         host=&quot;somehost.mycorp.com&quot; port=&quot;8003&quot; user=&quot;fred&quot; password=&quot;hush&quot;
 *         contentbase=&quot;productiondb&quot;/&gt;
 * &lt;Context&gt;
 * </pre>
 * <p>
 * An alternate means of specifying the content source is via the "url" attribute, like this:
 * </p>
 * 
 * <pre class="codesample">
 * &lt;Context path=&quot;/&quot;&gt;
 *    &lt;Resource name=&quot;marklogic/ContentSource&quot; auth=&quot;Container&quot;
 *         type=&quot;org.eclipse.wst.xquery.marklogic.xcc.ContentSource&quot;
 *         factory=&quot;org.eclipse.wst.xquery.marklogic.xcc.jndi.ContentSourceBeanFactory&quot;
 *         url=&quot;xcc://fred:hush@somehost.mycorp.com:8003/productiondb&quot;/&gt;
 * &lt;Context&gt;
 * </pre>
 * <p>
 * The value of the "name" attribute is the JNDI lookup key that you will use in your code (servlet,
 * JSP scriptlet, etc) to locate the {@link org.eclipse.wst.xquery.marklogic.xcc.ContentSource}. A typical method to do
 * the lookup would look something like this:
 * </p>
 * 
 * <pre class="codesample">
 * private ContentSource findContentSource() throws ServletException {
 *     try {
 *         Context initCtx = new InitialContext();
 *         Context envCtx = (Context)initCtx.lookup(&quot;java:comp/env&quot;);
 * 
 *         return (ContentSource)envCtx.lookup(&quot;marklogic/ContentSource&quot;);
 *     } catch (NamingException e) {
 *         throw new ServletException(&quot;ContentSource lookup failed: &quot; + e, e);
 *     }
 * }
 * </pre>
 */
public class ContentSourceBeanFactory implements ObjectFactory {
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?,?> environment) throws Exception {
        Reference ref = (Reference)obj;
        Enumeration<RefAddr> addrs = ref.getAll();
        String host = null;
        int port = 0;
        String user = null;
        String password = null;
        String contentBase = null;
        URI url = null;

        while (addrs.hasMoreElements()) {
            RefAddr addr = addrs.nextElement();
            String attrName = addr.getType();
            String attrValue = (String)addr.getContent();

            if (attrName.equals("host")) {
                host = attrValue;
            }

            if (attrName.equals("user")) {
                user = attrValue;
            }

            if (attrName.equals("password")) {
                password = attrValue;
            }

            if (attrName.equals("contentBase")) {
                contentBase = attrValue;
            }
            if (attrName.equals("contentbase")) {
                contentBase = attrValue;
            }

            if (attrName.equals("url")) {
                try {
                    url = new URI(attrValue);
                } catch (URISyntaxException e) {
                    throw new NamingException("Bad URL: " + attrValue);
                }
            }

            if (attrName.equals("port")) {
                try {
                    port = Integer.parseInt(attrValue);
                } catch (NumberFormatException e) {
                    throw new NamingException("Invalid port value " + attrValue);
                }
            }
        }

        if ((url == null) && ((host == null) || (port == 0))) {
            throw new NamingException("At least URL or host and port attributes must be specified");
        }

        if (url != null) {
            return ContentSourceFactory.newContentSource(url);
        } else {
            return ContentSourceFactory.newContentSource(host, port, user, password, contentBase);
        }
    }
}
