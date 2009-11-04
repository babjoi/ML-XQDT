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
import java.nio.channels.SocketChannel;

public class SocketChannelPool<K> {
    private final SocketResourcePool pool;

    public SocketChannelPool() {
        pool = new SocketResourcePool();
    }

    public SocketChannelPool(int limit) {
        pool = new SocketResourcePool(limit);
    }

    public void setLimit(int limit) {
        pool.setLimit(limit);
    }

    public boolean isEmpty(K key) {
        return pool.isEmpty(key);
    }

    public void put(K key, SocketChannel channel, long expireTimeMillis) {
        pool.put(key, channel, expireTimeMillis);
    }

    public void put(K key, SocketChannel channel) {
        pool.put(key, channel);
    }

    public SocketChannel get(K key) {
        SocketChannel channel = pool.get(key);

        if ((channel != null) && (!channel.isOpen())) {
            throw new IllegalStateException("pooled channel has been closed: " + channel);
        }

        return channel;
    }

    public long size(K key) {
        return pool.size(key);
    }

    // ----------------------------------------------------------

    // simple extension to ResourcePool that closes expired sockets
    private class SocketResourcePool extends ResourcePool<K, SocketChannel> {
        public SocketResourcePool(int limit) {
            super(limit);
        }

        public SocketResourcePool() {
        }

        @Override
        protected void itemExpired(PoolItem<SocketChannel> item) {
            SocketChannel channel = item.getValue();

            try {
                channel.close();
            } catch (IOException e) {
                // do nothing, channel is being disposed
            }
        }
    }
}
