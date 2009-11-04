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
package org.eclipse.wst.xquery.marklogic.xcc.impl.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.wst.xquery.marklogic.http.HttpChannel;
import org.eclipse.wst.xquery.marklogic.io.IOHelper;
import org.eclipse.wst.xquery.marklogic.xcc.Content;
import org.eclipse.wst.xquery.marklogic.xcc.ContentCreateOptions;
import org.eclipse.wst.xquery.marklogic.xcc.ContentPermission;
import org.eclipse.wst.xquery.marklogic.xcc.DocumentFormat;
import org.eclipse.wst.xquery.marklogic.xcc.DocumentRepairLevel;
import org.eclipse.wst.xquery.marklogic.xcc.Request;
import org.eclipse.wst.xquery.marklogic.xcc.RequestOptions;
import org.eclipse.wst.xquery.marklogic.xcc.ResultSequence;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.ContentInsertException;
import org.eclipse.wst.xquery.marklogic.xcc.exceptions.RequestException;
import org.eclipse.wst.xquery.marklogic.xcc.impl.RequestImpl;
import org.eclipse.wst.xquery.marklogic.xcc.impl.SessionImpl;
import org.eclipse.wst.xquery.marklogic.xcc.spi.ServerConnection;


public class ContentInsertController extends AbstractRequestController {
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    static final int DEFAULT_BUFFER_SIZE = 128 * 1024;
    static final int MAX_BUFFER_SIZE = 12 * 1024 * 1024;
    private static final int DATA_CHUNK = 0;
    private static final int COMMIT = 1;
    private static final int NO_COMMIT = 2;

    private static final Map<Integer, ResponseHandler> handlers = new HashMap<Integer, ResponseHandler>(8);

    static {
        addDefaultHandler(handlers, new UnrecognizedCodeHandler());
        addHandler(handlers, HttpURLConnection.HTTP_UNAVAILABLE, new ServiceUnavailableHandler());
        addHandler(handlers, HttpURLConnection.HTTP_INTERNAL_ERROR, new ServerExceptionHandler());
        addHandler(handlers, HttpURLConnection.HTTP_UNAUTHORIZED, new UnauthorizedHandler());
        addHandler(handlers, HttpURLConnection.HTTP_NOT_FOUND, new NotFoundCodeHandler());
        addHandler(handlers, HttpURLConnection.HTTP_BAD_REQUEST, new NotFoundCodeHandler());
        addHandler(handlers, HttpURLConnection.HTTP_OK, new GoodInsertResponseHandler());
        addHandler(handlers, HTTP_TEMPORARY_REDIRECT, new EntityResolveHandler());
    }

    // --------------------------------------------------------

    private final Content[] contents;
    private final boolean commit;
    private final ByteBuffer headerBuffer = ByteBuffer.allocate(16);
    private final LinkedList<Content> processedContent = new LinkedList<Content>();
    private ByteBuffer dataBuffer = null;

    public ContentInsertController(Content[] contents, boolean commit) {
        super(handlers);

        //noinspection RedundantCast
        this.contents = contents.clone();
        this.commit = commit;
    }

    // --------------------------------------------------------
    // Invoked by superclass template method

    @Override
    public ResultSequence serverDialog(ServerConnection connection, Request request, RequestOptions options,
            Logger logger) throws RequestException, IOException {
        assertRestartable(processedContent, request);

        LinkedList<ContentDecorator> remaining = toLinkedList(contents);

        logger.fine("beginning content insert dialog, " + remaining.size() + " documents queued");

        HttpChannel http = new HttpChannel(connection.channel(), "PUT", "/", 0, options.getTimeoutMillis(), logger);

        while (remaining.size() > 0) {
            if (logger.isLoggable(Level.FINE))
                logger.fine("" + processedContent.size() + " items sent, " + remaining.size() + " remaining");

            ContentDecorator content = remaining.remove(0);
            boolean commit = this.commit && (remaining.size() == 0);

            if (!content.isEntity()) {
                processedContent.add(content);
            }

            if (logger.isLoggable(Level.FINE)) {
                if (content.isEntity()) {
                    logger.fine("processing entity '" + content.getLocation() + "' for document '" + content.getUri()
                            + "'");
                } else {
                    logger.fine("processing '" + content.getUri() + "'");
                }
            }

            resetHttpChannel(http, request, options, content, commit);

            issueRequest(http, content, commit, logger);

            int code = http.getResponseCode();

            ContentDecorator entityContent = null;

            try {
                ResponseHandler handler = findHandler(code);
                entityContent = (ContentDecorator)handler.handleResponse(http, code, request, content, logger);
            } catch (ContentInsertException e) {
                connection.close();
                throw e;
            } finally {
                if (connection.isOpen()) {
                    setConnectionTimeout(connection, http);
                }
            }

            if (entityContent != null) {
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("queueing entity content for '" + entityContent.getUri() + "', location: "
                            + entityContent.getLocation());
                }

                remaining.addFirst(entityContent);
            }
        }

        closeContent(processedContent);

        logger.fine("finished content insert dialog, " + contents.length + " documents successfully inserted");

        return null;
    }

    // --------------------------------------------------------

    private static final int PLATEAU = 2000;
    private static final int PLATEAU_SHORT_CIRCUIT = 20;

    @Override
    protected long interTryDelay(long delay, int currentTry) {
        if ((currentTry == 0) || (delay <= 0)) {
            return 0;
        }

        if (currentTry >= PLATEAU_SHORT_CIRCUIT)
            return PLATEAU;

        long millis = delay * (1 << (currentTry - 1));

        // FIXME: Need to add API methods to ContentCreateOptions to customize this
        return (millis > PLATEAU) ? PLATEAU : millis;
    }

    // --------------------------------------------------------

    private void resetHttpChannel(HttpChannel http, Request request, RequestOptions options, Content content,
            boolean commit) {
        SessionImpl session = (SessionImpl)request.getSession();
        String pathUri = (content.getUri() == null) ? null : makeReqUri(content, request, commit);

        String method = "PUT";
        http.reset(method, pathUri);

        http.setRequestContentType("text/xml");
        addCommonHeaders(http, session, method, pathUri, options);
        http.setRequestHeader("Connection", "keep-alive");
    }

    // --------------------------------------------------------

    private void issueRequest(HttpChannel http, ContentDecorator content, boolean commit, Logger logger)
            throws IOException {
        String uri = content.getUri();

        if (logger.isLoggable(Level.FINE)) {
            if (content.isEntity()) {
                logger.fine("sending entity (location=" + content.getLocation() + ") for uri=" + uri + ", size="
                        + content.size());
            } else {
                logger.fine("sending content: uri=" + uri + ", size=" + content.size());
            }
        }

        ByteBuffer dataBuffer = allocDataBuffer(content);
        byte[] dataBytes = dataBuffer.array();
        InputStream inStream = content.openDataStream();
        boolean checkBOM = mayHaveBOM(content);
        int rc;

        if (content.isEntity()) {
            http.suppressHeaders();
        }

        while ((rc = inStream.read(dataBytes)) != -1) {
            dataBuffer.clear();
            dataBuffer.limit(rc);

            if (checkBOM) {
                checkBOM = false;

                if ((rc >= 3) && hasBOM(dataBytes)) {
                    rc -= 3;
                    dataBuffer.position(3);
                    logger.finest("suppressed UTF-8 BOM");
                }
            }

            writeChunkHeader(http, DATA_CHUNK, rc, logger);

            if (logger.isLoggable(Level.FINEST))
                logger.finest("writing " + rc + " bytes of data");
            http.write(dataBuffer);
        }

        inStream.close();

        writeChunkHeader(http, (commit) ? COMMIT : NO_COMMIT, 0, logger);

        logger.fine("finished sending content: commit=" + commit);
    }

    private boolean mayHaveBOM(ContentDecorator content) {
        ContentCreateOptions options = content.getCreateOptions();
        String encoding = (options == null) ? "utf-8" : options.getEncoding();
        DocumentFormat fmt = (options == null) ? DocumentFormat.NONE : options.getFormat();
        boolean isUtf8 = (options == null) || ("utf-8".equalsIgnoreCase(encoding) || "utf8".equalsIgnoreCase(encoding));

        return isUtf8 && (fmt != DocumentFormat.BINARY) && (fmt != DocumentFormat.NONE);
    }

    private boolean hasBOM(byte[] bytes) {
        // UTF-8 encoded Byte-Order-Mark is efbbbf as the first three bytes
        return ((bytes[0] & 0xff) == 0xef) && ((bytes[1] & 0xff) == 0xbb) && ((bytes[2] & 0xff) == 0xbf);
    }

    // ------------------------------------------------------------

    private void assertRestartable(LinkedList<Content> processedContent, Request request) throws ContentInsertException {
        while (processedContent.size() > 0) {
            ContentDecorator content = (ContentDecorator)processedContent.removeFirst();

            if (content.isPristine()) {
                continue;
            }

            if (content.isRewindable()) {
                try {
                    // TODO: write a test case to verify rewinds
                    content.rewind();
                } catch (IOException e) {

                    processedContent.clear();

                    // TODO: write a test case for this
                    throw new ContentInsertException("Cannot auto-restart insert, error rewinding content: "
                            + content.getUri(), request, content.getOriginal(), e);
                }

                continue;
            }

            processedContent.clear();

            throw new ContentInsertException("Cannot auto-restart insert, non-rewindable content already processed: "
                    + content.getUri(), request, content.getOriginal());
        }
    }

    private void closeContent(LinkedList<Content> processedContent) {
        while (processedContent.size() > 0) {
            Content content = processedContent.removeFirst();

            content.close();
        }
    }

    // ------------------------------------------------------------

    private LinkedList<ContentDecorator> toLinkedList(Content[] array) {
        LinkedList<ContentDecorator> list = new LinkedList<ContentDecorator>();

        for (int i = 0; i < array.length; i++) {
            list.add(new ContentDecorator(array[i]));
        }

        return (list);
    }

    private void writeChunkHeader(HttpChannel http, int code, int count, Logger logger) throws IOException {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("writing chunk header: " + code + count);
        }

        headerBuffer.clear();
        headerBuffer.put((byte)('0' + code));
        headerBuffer.put(Integer.toString(count).getBytes());
        headerBuffer.put((byte)'\r');
        headerBuffer.put((byte)'\n');

        headerBuffer.flip();

        http.write(headerBuffer);
    }

    // package visible for unit testing
    ByteBuffer allocDataBuffer(Content content) {
        ContentCreateOptions options = content.getCreateOptions();
        int userSize = ((options == null) ? -1 : options.getBufferSize());
        int bufSize = (int)((content.size() == -1) ? DEFAULT_BUFFER_SIZE : content.size());

        bufSize = (userSize == -1) ? bufSize : Math.min(bufSize, userSize);
        bufSize = Math.min(bufSize, MAX_BUFFER_SIZE);

        if ((dataBuffer == null) || (dataBuffer.capacity() < bufSize)) {
            dataBuffer = ByteBuffer.allocate(bufSize);
        }

        return dataBuffer;
    }

    // package visible for unit testing
    static String makeReqUri(Content content, Request request, boolean commit) {
        ContentCreateOptions options = (content.getCreateOptions() == null) ? new ContentCreateOptions() : content
                .getCreateOptions();
        RequestOptions requestOptions = (request == null) ? new RequestOptions() : request.getEffectiveOptions();
        StringBuffer sb = new StringBuffer(256);

        sb.append("/insert?uri=");
        IOHelper.urlEncodeToStringBuffer(sb, content.getUri());

        if (!commit) {
            sb.append("&nocommit");
        }

        if (options.getLocale() != null) {
            if (request == null) {
                sb.append("&locale=").append(options.getLocale().toString());
            } else {
                requestOptions.setLocale(options.getLocale());
            }
        }

        if (options.getLanguage() != null) {
            sb.append("&lang=");
            IOHelper.urlEncodeToStringBuffer(sb, options.getLanguage());
        }

        if (options.getNamespace() != null) {
            sb.append("&defaultns=");
            IOHelper.urlEncodeToStringBuffer(sb, options.getNamespace());
        }

        if (options.getQuality() != 0) {
            sb.append("&quality=").append(options.getQuality());
        }

        if (options.getResolveEntities()) {
            sb.append("&resolve");
        }

        if (options.getResolveBufferSize() != 0) {
            sb.append("&resolvesiz=").append(options.getResolveBufferSize());
        }

        if (options.getRepairLevel() == DocumentRepairLevel.NONE) {
            sb.append("&repair=none");
        }

        if (options.getRepairLevel() == DocumentRepairLevel.FULL) {
            sb.append("&repair=full");
        }

        if (!ContentCreateOptions.DEFAULT_ENCODING.equalsIgnoreCase(options.getEncoding())) {
            sb.append("&encoding=").append(options.getEncoding());
        }

        if (options.getFormat() == DocumentFormat.XML) {
            sb.append("&format=xml");
        }

        if (options.getFormat() == DocumentFormat.TEXT) {
            sb.append("&format=text");
        }

        if (options.getFormat() == DocumentFormat.BINARY) {
            sb.append("&format=binary");
        }

        if (options.getPlaceKeys() != null) {
            BigInteger[] keys = options.getPlaceKeys();

            for (int i = 0; i < keys.length; i++) {
                sb.append("&placeKey=").append(keys[i].toString());
            }
        }

        if (options.getCollections() != null) {
            String[] collections = options.getCollections();

            if (collections.length == 0) {
                sb.append("&nocolls");
            } else {
                for (int i = 0; i < collections.length; i++) {
                    sb.append("&coll=");
                    IOHelper.urlEncodeToStringBuffer(sb, collections[i]);
                }
            }
        }

        if (options.getPermissions() != null) {
            ContentPermission[] perms = options.getPermissions();

            if (perms.length == 0) {
                sb.append("&noperms");
            } else {
                for (int i = 0; i < perms.length; i++) {
                    ContentPermission perm = perms[i];
                    String symbol = (perm.getCapability() == null) ? "N" : perm.getCapability().getSymbol();

                    sb.append("&perm=").append(symbol);
                    IOHelper.urlEncodeToStringBuffer(sb, perm.getRole());
                }
            }
        }

        if (request != null) {
            ((RequestImpl)request).encodeQueryOptions(sb, requestOptions);
        }

        return sb.substring(0);
    }

    // ------------------------------------------------------------

    static class ContentDecorator implements Content {
        private final Content content;
        private final Content parent;
        private final String location;
        private boolean pristine = true;

        public ContentDecorator(Content content) {
            this.content = content;
            this.parent = null;
            this.location = null;
        }

        public ContentDecorator(Content entity, Content parent, String location) {
            this.content = entity;
            this.parent = parent;
            this.location = location;
        }

        // ----------------------------------------------------
        // Content interface

        public String getUri() {
            return (parent == null) ? content.getUri() : parent.getUri();
        }

        public InputStream openDataStream() throws IOException {
            pristine = false;

            return content.openDataStream();
        }

        public ContentCreateOptions getCreateOptions() {
            return content.getCreateOptions();
        }

        public boolean isRewindable() {
            return content.isRewindable();
        }

        public void rewind() throws IOException {
            pristine = false;

            content.rewind();
        }

        public long size() {
            return (content.size());
        }

        public void close() {
            pristine = false;

            content.close();
        }

        // -----------------------------------------------------
        // decorations

        public boolean isEntity() {
            return location != null;
        }

        public String getLocation() {
            return location;
        }

        public boolean isPristine() {
            return pristine;
        }

        public Content getOriginal() {
            return content;
        }
    }
}

/*
 * [CLIENT]: -->PUT
 * /insert?uri=%2Ftestdocs%2Ffooinsertentity.xml&resolve&resolvesiz=1048576&format=xml HTTP/1.1
 * Content-Type: text/xml User-Agent: Java/1.4.2_09 MarkXDBC/3.0-5 Host: localhost:9050 Accept:
 * text/html, text/xml, image/gif, image/jpeg, * /* Connection: keep-alive Authorization: basic
 * eGRiYzp4ZGJj
 * 
 * 0159 <!DOCTYPE root PUBLIC "http://marklogic.com/foo/blah" [<!ENTITY myentity SYSTEM
 * "file:///tmp/junittmpentity"> ]> <root><blah foo="boo">&myentity;</blah></root>10 <--
 * 
 * [SERVER]: -->HTTP/1.1 307 Temporary Redirect Location: file:/tmp/junittmpentity Content-Length: 0
 * 
 * <--
 * 
 * [CLIENT]: -->013 <--
 * 
 * [CLIENT]: -->1139642485452<--
 * 
 * [CLIENT]: -->10 <--
 * 
 * [SERVER]: -->HTTP/1.1 200 OK Content-Length: 0 Connection: Keep-Alive Keep-Alive: timeout=1
 * 
 * <--
 */

