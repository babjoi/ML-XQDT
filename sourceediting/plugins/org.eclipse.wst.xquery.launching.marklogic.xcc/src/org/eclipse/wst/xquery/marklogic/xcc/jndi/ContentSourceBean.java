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
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.xcc.ContentSource;
import org.eclipse.wst.xquery.marklogic.xcc.ContentSourceFactory;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.XccConfigException;


/**
 * <p>
 * This is a Java Bean compatible implementation of {@link ContentSource} intended for use in a JNDI
 * context or container where configuration must be done via setter methods.
 * </p>
 * <p>
 * Use of this class to directly instantiate a {@link ContentSource} is discouraged. If you wish to
 * programmatically create an instance use the primary factory class {@link ContentSourceFactory}
 * instead.
 * </p>
 * <p>
 * This class depends on a container-provided {@link javax.naming.spi.ObjectFactory} implementation.
 * If you have the option of configuring a custom bean factory, you are encouraged to use
 * {@link ContentSourceBeanFactory} instead.
 * </p>
 * </p> An example of configuring Tomcat to use this bean would look like this:
 * <p>
 * 
 * <pre class="codesample">
 * &lt;Context path=&quot;/&quot;&gt;
 *    &lt;Resource name=&quot;marklogic/ContentSource&quot; auth=&quot;Container&quot;
 *        type=&quot;org.eclipse.wst.xquery.marklogic.xcc.jndi.ContentSourceBean&quot;
 *        factory=&quot;org.apache.naming.factory.BeanFactory&quot;
 *         host=&quot;somehost.mycorp.com&quot; port=&quot;8003&quot; user=&quot;fred&quot; password=&quot;hush&quot;
 *         contentbase=&quot;productiondb&quot;/&gt;
 * &lt;Context&gt;
 * </pre>
 * <p>
 * See {@link ContentSourceBeanFactory} for more details and an example of looking up a
 * {@link ContentSource} with JNDI. The lookup code will be identical for both configuration
 * approaches.
 * </p>
 * 
 * @see ContentSourceBeanFactory
 */
public class ContentSourceBean implements ContentSource {
    private ContentSource cs = null;
    private String host = null;
    private int port = 0;
    private String user = null;
    private String password = null;
    private String contentBase = null;
    private URI url = null;

    public ContentSourceBean() {
    }

    // ----------------------------------------------------------
    // ContentSource interface

    public Session newSession() {
        return getContentSource().newSession();
    }

    public Session newSession(String databaseId) {
        return getContentSource().newSession(databaseId);
    }

    public Session newSession(String userName, String password) {
        return getContentSource().newSession(userName, password);
    }

    public Session newSession(String userName, String password, String databaseId) {
        return getContentSource().newSession(userName, password, databaseId);
    }

    public Logger getDefaultLogger() {
        return getContentSource().getDefaultLogger();
    }

    public void setDefaultLogger(Logger logger) {
        getContentSource().setDefaultLogger(logger);
    }

    @Override
    public String toString() {
        return getContentSource().toString();
    }

    // ----------------------------------------------------------
    // Bean setter methods exposed to JNDI container

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContentBase(String contentBase) {
        this.contentBase = contentBase;
    }

    public void setUrl(String url) throws URISyntaxException {
        this.url = new URI(url);
    }

    // ----------------------------------------------------------

    private ContentSource getContentSource() {
        if (cs != null) {
            return cs;
        }

        if (url == null) {
            cs = ContentSourceFactory.newContentSource(host, port, user, password, contentBase);
        } else {
            try {
                cs = ContentSourceFactory.newContentSource(url);
            } catch (XccConfigException e) {
                throw new IllegalArgumentException("Cannot create ContentSource from URI: " + e);
            }
        }

        return cs;
    }
}
