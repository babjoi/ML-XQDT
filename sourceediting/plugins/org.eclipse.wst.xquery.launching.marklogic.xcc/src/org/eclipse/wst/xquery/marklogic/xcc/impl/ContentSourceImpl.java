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
package org.eclipse.wst.xquery.marklogic.xcc.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.io.Base64;
import org.eclipse.wst.xquery.marklogic.io.IOHelper;
import org.eclipse.wst.xquery.marklogic.xcc.ContentSource;
import org.eclipse.wst.xquery.marklogic.xcc.Session;
import org.eclipse.wst.xquery.marklogic.xcc.UserCredentials;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ConnectionProvider;


public class ContentSourceImpl implements ContentSource {
    private static final String DEFAULT_LOGGER_NAME = "org.eclipse.wst.xquery.marklogic.xcc";
    private static final String XCC_LOGGING_CONFIG_FILE = "xcc.logging.properties";
    private static final String SYSTEM_LOGGING_CONFIG_CLASS = "java.util.logging.config.class";
    private static final String SYSTEM_LOGGING_CONFIG_FILE = "java.util.logging.config.file";

    private final ConnectionProvider connectionProvider;
    private final String user;
    private final String password;
    private final String contentBase;
    private Logger logger = newDefaultLogger();

    private static Random random = new Random();

    private static Logger newDefaultLogger() {
        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger(DEFAULT_LOGGER_NAME);

        if (logger != null) {
            return logger;
        }

        if ((System.getProperty(SYSTEM_LOGGING_CONFIG_CLASS) != null)
                || (System.getProperty(SYSTEM_LOGGING_CONFIG_FILE) != null)) {
            // If custom config file or class, don't override anything
            return Logger.getLogger(DEFAULT_LOGGER_NAME);
        }

        return customizedLogger(logManager);
    }

    public ContentSourceImpl(ConnectionProvider connectionProvider, String user, String password, String contentBase) {
        this.connectionProvider = connectionProvider;
        this.user = user;
        this.password = password;

        String cbName = contentBase;

        if (cbName != null) {
            cbName = cbName.trim();

            if (cbName.length() == 0) {
                cbName = null;
            }
        }

        this.contentBase = cbName;
    }

    public Session newSession() {
        if ((user == null) || (password == null)) {
            throw new IllegalStateException("No default user/password configured");
        }

        return (newSession(user, password));
    }

    public Session newSession(String userName, String password) {
        return (newSession(userName, password, null));
    }

    public Session newSession(String user, String password, String contentBaseArg) {
        if ((user == null) || (password == null)) {
            throw new IllegalStateException("Invalid authentication credentials");
        }

        String contentBase = (contentBaseArg == null) ? this.contentBase : contentBaseArg;

        return (new SessionImpl(this, connectionProvider, new Credentials(user, password), contentBase));
    }

    public Session newSession(String databaseId) {
        return (newSession(user, password, databaseId));
    }

    public Logger getDefaultLogger() {
        return logger;
    }

    public void setDefaultLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String toString() {
        return "user=" + ((user == null) ? "{none}" : user) + ", cb="
                + ((contentBase == null) ? "{none}" : contentBase) + " [provider: " + connectionProvider.toString()
                + "]";
    }

    // -------------------------------------------------------------

    private static Logger customizedLogger(LogManager logManager) {
        Properties props = loadPropertiesFromResource(XCC_LOGGING_CONFIG_FILE);
        Logger logger = Logger.getLogger(DEFAULT_LOGGER_NAME);
        List<Handler> handlers = getLoggerHandlers(logger, logManager, props);

        for (Iterator<Handler> it = handlers.iterator(); it.hasNext();) {
            logger.addHandler(it.next());
        }

        boolean useParentHandlers = getUseParentHandlersFlag(logger, logManager, props);

        logger.setUseParentHandlers(useParentHandlers);

        logManager.addLogger(logger);

        return logger;
    }

    private static Properties loadPropertiesFromResource(String path) {
        Properties props = new Properties();
        InputStream is = ContentSource.class.getResourceAsStream(path);

        if (is != null) {
            try {
                props.load(is);
            } catch (IOException e) {
                // do nothing, property file not found
            }
        }

        return props;
    }

    private static List<Handler> getLoggerHandlers(Logger logger, LogManager logManager, Properties props) {
        String propName = logger.getName() + ".handlers";
        String handlerPropVal = getPropertyValue(propName, logManager, props);

        if (handlerPropVal == null) {
            return new ArrayList<Handler>(0);
        }

        String[] handlerClassNames = handlerPropVal.split("\\\\s*,?\\\\s*");
        List<Handler> handlers = new ArrayList<Handler>(handlerClassNames.length);
        Level level = getLoggerLevel(logger, logManager, props);

        if (level != null)
            logger.setLevel(level);

        for (int i = 0; i < handlerClassNames.length; i++) {
            try {
                Class<? extends Handler> handlerClass = Class.forName(handlerClassNames[i]).asSubclass(Handler.class);
                Handler handler = handlerClass.newInstance();
                Formatter formatter = getFormatter(handler, logManager, props);

                handlers.add(handler);
                if (formatter != null)
                    handler.setFormatter(formatter);
                if (level != null)
                    handler.setLevel(level);
            } catch (Exception e) {
                // Do nothing, can't instantiate the handler class
            }
        }

        return handlers;
    }

    private static Formatter getFormatter(Handler handler, LogManager logManager, Properties props) {
        String propName = handler.getClass().getName() + ".formatter";
        String formatterClassName = getPropertyValue(propName, logManager, props);

        try {
            Class<? extends Formatter> clazz = Class.forName(formatterClassName).asSubclass(Formatter.class);
            Constructor<? extends Formatter> cons = null;

            try {
                cons = clazz.getConstructor(new Class[] { Properties.class, LogManager.class });
            } catch (Exception e) {
                // do nothing, may not be our LogFormatter class
            }

            if (cons != null) {
                return cons.newInstance(new Object[] { props, logManager });
            }

            return (Formatter)Class.forName(formatterClassName).newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    private static Level getLoggerLevel(Logger logger, LogManager logManager, Properties props) {
        String propName = logger.getName() + ".level";
        String levelName = getPropertyValue(propName, logManager, props);

        try {
            return Level.parse(levelName);
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean getUseParentHandlersFlag(Logger logger, LogManager logManager, Properties props) {
        String propName = logger.getName() + ".useParentHandlers";
        String propValue = getPropertyValue(propName, logManager, props);

        if (propValue == null) {
            return false;
        }

        try {
            return Boolean.valueOf(propValue).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    private static String getPropertyValue(String propName, LogManager logManager, Properties props) {
        String propVal = props.getProperty(propName);

        if (propVal != null) {
            return propVal.trim();
        }

        propVal = logManager.getProperty(propName);

        if (propVal != null) {
            return propVal.trim();
        }

        return null;
    }

    // -------------------------------------------------------------

    static class Credentials implements UserCredentials {
        private String user;
        private String password;

        public Credentials(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public String getUserName() {
            return user;
        }

        public String toHttpBasicAuth() {
            try {
                return ("basic " + Base64.encodeBytes((user + ":" + password).getBytes("UTF-8"),
                        Base64.DONT_BREAK_LINES));
            } catch (UnsupportedEncodingException e) {
                return ("basic " + Base64.encodeBytes((user + ":" + password).getBytes(), Base64.DONT_BREAK_LINES));
            }
        }

        private static final AtomicLong nonceCounter = new AtomicLong();

        // www-authenticate
        public String toHttpDigestAuth(String method, String uri, String challengeHeader) {

            if ((challengeHeader == null) || !challengeHeader.startsWith("Digest "))
                return null;

            String pairs[] = challengeHeader.substring("Digest ".length()).split(", +");

            Map<String, String> params = new HashMap<String, String>();

            for (String pair : pairs) {
                String nv[] = pair.split("=", 2);
                params.put(nv[0].toLowerCase(), nv[1].substring(1, nv[1].length() - 1));
            }

            String realm = params.get("realm");

            String HA1 = digestCalcHA1(user, realm, password);

            String nonce = params.get("nonce");
            String qop = params.get("qop");
            String opaque = params.get("opaque");

            byte[] bytes = new byte[16];

            synchronized (random) {
                random.nextBytes(bytes);
            }

            String cNonce = IOHelper.bytesToHex(bytes);

            String nonceCount = Long.toHexString(nonceCounter.incrementAndGet());

            String response = digestCalcResponse(HA1, nonce, nonceCount, cNonce, qop, method, uri);

            StringBuilder buf = new StringBuilder();

            buf.append("Digest username=\"");
            buf.append(user);
            buf.append("\", realm=\"");
            buf.append(realm);
            buf.append("\", nonce=\"");
            buf.append(nonce);
            buf.append("\", uri=\"");
            buf.append(uri);
            buf.append("\", qop=\"auth\", nc=\"");
            buf.append(nonceCount);
            buf.append("\", cnonce=\"");
            buf.append(cNonce);
            buf.append("\", response=\"");
            buf.append(response);
            buf.append("\", opaque=\"");
            buf.append(opaque);
            buf.append("\"");

            return buf.toString();
        }

        @Override
        public String toString() {
            return "user=" + user;
        }
    }

    public static String digestCalcResponse(String HA1, String nonce, String nonceCount, String cNonce, String qop,
            String method, String uri) {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            StringBuilder plaintext = new StringBuilder();

            plaintext.append(method);
            plaintext.append(":");
            plaintext.append(uri);

            digest.update(plaintext.toString().getBytes(), 0, plaintext.length());

            String HA2 = IOHelper.bytesToHex(digest.digest());

            plaintext.setLength(0);
            plaintext.append(HA1);
            plaintext.append(":");
            plaintext.append(nonce);
            plaintext.append(":");
            if (qop != null) {
                plaintext.append(nonceCount);
                plaintext.append(":");
                plaintext.append(cNonce);
                plaintext.append(":");
                plaintext.append(qop);
                plaintext.append(":");
            }
            plaintext.append(HA2);

            digest.update(plaintext.toString().getBytes(), 0, plaintext.length());

            return IOHelper.bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            // this really shouldn't happen
            throw new RuntimeException(e);
        }
    }

    public static String digestCalcHA1(String userName, String realm, String password) {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            StringBuilder plaintext = new StringBuilder();

            plaintext.append(userName);
            plaintext.append(":");
            plaintext.append(realm);
            plaintext.append(":");
            plaintext.append(password);

            digest.update(plaintext.toString().getBytes(), 0, plaintext.length());

            return IOHelper.bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            // this really shouldn't happen
            throw new RuntimeException(e);
        }
    }
}
