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
package org.eclipse.wst.xquery.marklogic.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class HttpHeaders {
    private static final String PREFIX = "X-X-";
    private static final String HTTP_RESPONSE_KEY = PREFIX + "HTTP-RESPONSE-LINE";
    private static final String RESPONSE_CODE_KEY = PREFIX + "HTTP-RESPONSE-CODE";
    private static final String RESPONSE_MSG_KEY = PREFIX + "HTTP-RESPONSE-MESSAGE";

    private static final String HTTP_REQUEST_KEY = PREFIX + "HTTP-REQUEST-LINE";
    private static final String REQUEST_METHOD_KEY = PREFIX + "HTTP-REQUEST-METHOD";
    private static final String REQUEST_PATH_KEY = PREFIX + "HTTP-REQUEST-PATH";
    private static final String REQUEST_VERSION_KEY = PREFIX + "HTTP-REQUEST-VERSION";

    private Map<String, String> headers = new LinkedHashMap<String, String>();

    // ---------------------------------------------------------------

    public void clear() {
        headers.clear();
    }

    public int size() {
        return (headers.size());
    }

    public void setHeaderNormalized(String name, String value) {
        setHeader(name.toLowerCase(), value);
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public String getHeaderNormalized(String name) {
        return getHeader(name.toLowerCase());
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    // ---------------------------------------------------------------

    public Iterator<String> iterator() {
        Set<String> keys = new LinkedHashSet<String>(headers.size());

        for (Iterator<String> it = headers.keySet().iterator(); it.hasNext();) {
            String key = it.next();

            if (key.startsWith(PREFIX)) {
                continue;
            }

            keys.add(key);
        }

        return (keys.iterator());
    }

    public Iterator<String> iteratorAll() {
        return (Collections.unmodifiableMap(headers).keySet().iterator());
    }

    // ---------------------------------------------------------------

    public void setResponseValues(String line) throws IOException {
        setHeader(HTTP_RESPONSE_KEY, line);

        String[] parts = line.split("\\s+", 3);

        if ((parts.length < 2) || (!parts[0].startsWith("HTTP/1."))) {
            throw new IOException("Malformed HTTP Response: " + line);
        }

        String codeStr = parts[1];

        try {
            Integer.parseInt(codeStr);
            setHeader(RESPONSE_CODE_KEY, codeStr);
        } catch (NumberFormatException e) {
            throw new IOException("Malformed HTTP Response code: " + codeStr);
        }

        String msg = (parts.length > 2) ? parts[2] : "";

        setHeader(RESPONSE_MSG_KEY, msg);
    }

    public String getResponseLine() {
        return (getHeader(HTTP_RESPONSE_KEY));
    }

    public int getResponseCode() {
        String hdr = getHeader(RESPONSE_CODE_KEY);

        if (hdr == null) {
            return (-1);
        }

        return Integer.parseInt(hdr);
    }

    public String getResponseMessage() {
        return (getHeader(RESPONSE_MSG_KEY));
    }

    public void setRequestValues(String method, String path, String version) {
        setHeader(REQUEST_METHOD_KEY, method);
        setHeader(REQUEST_PATH_KEY, path);
        setHeader(REQUEST_VERSION_KEY, version);

        setHeader(HTTP_REQUEST_KEY, method + " " + path + " HTTP/1.1");
    }

    public String getRequestLine() {
        return (getHeader(HTTP_REQUEST_KEY));
    }

    public String getRequestMethod() {
        return (getHeader(REQUEST_METHOD_KEY));
    }

    public String getRequestPath() {
        return (getHeader(REQUEST_PATH_KEY));
    }

    public String getRequestVersion() {
        return (getHeader(REQUEST_VERSION_KEY));
    }

    public int getContentLength() {
        String lengthStr = getHeaderNormalized("content-length");

        if (lengthStr == null) {
            return (-1);
        }

        try {
            return (Integer.parseInt(lengthStr));
        } catch (NumberFormatException e) {
            return (-1);
        }
    }

    public String getContentType() {
        String value = getHeaderNormalized("content-type");

        if (value == null) {
            return (null);
        }

        String[] parts = value.split("\\s*;\\s*");

        return (parts[0]);
    }

    public String getContentTypeField(String fieldName) {
        return (getHeaderSubValue("content-type", fieldName, ";"));
    }

    // ---------------------------------------------------------------

    public StringBuffer toStringBuffer(StringBuffer userSb) {
        StringBuffer sb = (userSb == null) ? new StringBuffer(1024) : userSb;
        String lineSep = "\r\n";

        sb.append(getRequestLine());
        sb.append(lineSep);

        for (Iterator<String> it = iterator(); it.hasNext();) {
            String key = it.next();

            sb.append(key).append(": ").append(getHeader(key));
            sb.append(lineSep);
        }

        sb.append(lineSep);

        return (sb);
    }

    @Override
    public String toString() {
        return (toStringBuffer(null).toString());
    }

    public int writeHeaders(OutputStream os) throws IOException {
        byte[] bytes = toString().getBytes("UTF-8");

        os.write(bytes);

        return (bytes.length);

//		ByteArrayOutputStream bos = new ByteArrayOutputStream (256);
//		byte [] lineSepBytes = "\r\n".getBytes ("UTF-8");
//
//		bos.write (getRequestLine ().getBytes());
//		bos.write (lineSepBytes);
//
//		for (Iterator it = iterator(); it.hasNext ();) {
//			String key = (String) it.next ();
//
//			String header = key + ": " + getHeader (key);
//
//			bos.write (header.getBytes ("UTF-8"));
//			bos.write (lineSepBytes);
//		}
//
//		bos.write (lineSepBytes);
//		bos.flush ();
//
//		byte[] headerBytes = bos.toByteArray();
//
//		os.write (headerBytes);
//
//		return (headerBytes.length);
    }

    // ---------------------------------------------------------------

    public void parseResponseHeaders(InputStream is) throws IOException {
        clear();

        String line;

        try {
            line = nextHeaderLine(is);
        } catch (IOException e) {
            IOException newex = new IOException("Error parsing HTTP headers: " + e.getMessage());

            newex.setStackTrace(e.getStackTrace());

            throw newex;
        }

        setResponseValues(line);

        parsePlainHeaders(is);
    }

    public void parsePlainHeaders(InputStream is) throws IOException {
        String line;

        while ((line = nextHeaderLine(is)) != null) {
            String[] hdrParts = line.split("\\s*:\\s*", 2);

            if (hdrParts.length != 2) {
                throw new IOException("Malformed header line: " + line);
            }

            setHeaderNormalized(hdrParts[0], hdrParts[1]);
        }
    }

    private String nextHeaderLine(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder(64);

        while (true) {
            int b = is.read();

            if (b == -1) {
                throw new IOException("Premature EOF, partial header line read: '" + sb.toString() + "'");
            }

            if (b == '\r') {
                continue;
            }

            if (b == '\n') {
                break;
            }

            sb.append((char)b);
        }

        return (sb.length() > 0) ? sb.toString() : null;
    }

    // ---------------------------------------------------------------

    public String getHeaderSubValue(String headerName, String subName, String delim) {
        String headerValue = getHeader(headerName);

        if (headerValue == null) {
            return (null);
        }

        String[] parts = headerValue.split("\\s*" + delim + "\\s*");

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            if (part.startsWith(subName)) {
                String[] subParts = part.split("\\s*=\\s*");

                if (subParts.length == 2) {
                    return (subParts[1]);
                }
            }
        }

        return (null);
    }

    public Integer getHeaderSubValueInt(String headerName, String subName, String delim) {
        String strValue = getHeaderSubValue(headerName, subName, delim);

        if (strValue == null) {
            return (null);
        }

        try {
            return (Integer.decode(strValue));
        } catch (NumberFormatException e) {
            return (null);
        }
    }

    // ---------------------------------------------------------------

    static String extractCsvSubValue(String csv, String subName) {
        String[] parts = csv.split("\\s*,\\s*");

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            if (part.startsWith(subName)) {
                String[] subParts = part.split("\\s*=\\s*");

                if (subParts.length == 2) {
                    return (subParts[1]);
                }
            }
        }

        return (null);
    }

    static Integer extractCsvIntSubValue(String csv, String subName) {
        String subString = extractCsvSubValue(csv, subName);

        if (subString == null) {
            return (null);
        }

        try {
            return Integer.decode(subString);
        } catch (NumberFormatException e) {
            return (null);
        }
    }

    // ---------------------------------------------------------------
}
