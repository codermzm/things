package com.things.frame.cache.mybatis;

import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * {@code LoggingCache} adapter for Ehcache.
 *
 * @version $Id: LoggingEhcache.java 3454 2010-12-29 20:35:44Z simone.tripodi $
 */
public final class LoggingEhcache extends LoggingCache {

    public LoggingEhcache(final String id) {
        super(new EhcacheCache(id));
    }

}
