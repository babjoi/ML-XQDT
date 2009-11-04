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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericResourcePool<K, V> {
    private Map<K, List<PoolItem>> pools = Collections.synchronizedMap(new HashMap<K, List<PoolItem>>());
    private int limit = Integer.MAX_VALUE;

    public GenericResourcePool(int limit) {
        this.limit = limit;
    }

    public GenericResourcePool() {
        this(Integer.MAX_VALUE);
    }

    public void setLimit(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must be a non-zero positive value");
        }

        this.limit = limit;
    }

    public boolean isEmpty(K key) {
        List<PoolItem> list = findList(pools, key);

        return ((list == null) || list.isEmpty());
    }

    public void put(K key, V resource, long expireTimeMillis) {
        addItemToList(key, resource, expireTimeMillis);
    }

    public void put(K key, V obj) {
        put(key, obj, -1);
    }

    public V get(K key) {
        return (getItemFromList(key));
    }

    public long size(K key) {
        List<PoolItem> list = findList(pools, key);

        return ((list == null) ? 0 : list.size());
    }

    // --------------------------------------------------------

    // override to receive notification of expired items
    protected void itemExpired(PoolItem item) {
        // do nothing by default
    }

    // overrideable for unit testing purposes
    protected long getCurrentTime() {
        return (System.currentTimeMillis());
    }

    // --------------------------------------------------------

    private V getItemFromList(K key) {
        List<PoolItem> list = findList(pools, key);
        long now = getCurrentTime();

        if (list == null) {
            return (null);
        }

        while (true) {
            PoolItem item = null;

            synchronized (list) {
                if (list.isEmpty()) {
                    return (null);
                }

                item = list.remove(0);
            }

            if (item == null) {
                return (null);
            }

            if (item.hasExpired(now)) {
                itemExpired(item);
            } else {
                return (item.getValue());
            }
        }
    }

    private void addItemToList(K key, V resource, long expireTimeMillis) {
        List<PoolItem> list = findOrCreateList(pools, key);

        if (list.size() >= limit) {
            return;
        }

        PoolItem item = new PoolItem(resource, expireTimeMillis);

        if (!item.hasExpired(getCurrentTime())) {
            list.add(item);
        }
    }

    private List<PoolItem> findList(Map<K, List<PoolItem>> pools, K key) {
        return pools.get(key);
    }

    private List<PoolItem> findOrCreateList(Map<K, List<PoolItem>> pools, K key) {
        List<PoolItem> list;

        synchronized (pools) {
            list = findList(pools, key);

            if (list == null) {
                list = Collections.synchronizedList(new ArrayList<PoolItem>());
                pools.put(key, list);
            }
        }

        return (list);
    }

    // --------------------------------------------------------

    protected class PoolItem {
        private V item;
        private long expireTime;

        public PoolItem(V item, long expireTime) {
            this.item = item;
            this.expireTime = expireTime;
        }

        public V getValue() {
            return (item);
        }

        public boolean hasExpired(long currTime) {
            return ((expireTime != -1) && (currTime >= expireTime));
        }
    }
}
