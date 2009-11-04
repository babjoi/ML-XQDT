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
package org.eclipse.wst.xquery.marklogic.io;

import java.io.IOException;
import java.net.Socket;

public class SocketPool<K> {
    private final SocketResourcePool pool;

    public SocketPool() {
        pool = new SocketResourcePool();
    }

    public SocketPool(int limit) {
        pool = new SocketResourcePool(limit);
    }

    public void setLimit(int limit) {
        pool.setLimit(limit);
    }

    public boolean isEmpty(K key) {
        return pool.isEmpty(key);
    }

    public void put(K key, Socket socket, long expireMillis) {
        pool.put(key, socket, expireMillis);
    }

    public void put(K key, Socket socket) {
        pool.put(key, socket);
    }

    public Socket get(K key) {
        return pool.get(key);
    }

    public long size(K key) {
        return pool.size(key);
    }

    // ----------------------------------------------------------

    // simple extension to ResourcePool that closes expired sockets
    private class SocketResourcePool extends ResourcePool<K, Socket> {
        public SocketResourcePool(int limit) {
            super(limit);
        }

        public SocketResourcePool() {
        }

        @Override
        protected void itemExpired(PoolItem<Socket> item) {
            Socket socket = item.getValue();

            try {
                socket.close();
            } catch (IOException e) {
                // do nothing, socket is being disposed
            }
        }
    }
}
