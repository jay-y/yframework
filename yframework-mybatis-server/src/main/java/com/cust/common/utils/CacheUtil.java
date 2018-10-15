package com.cust.common.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.yframework.toolkit.StringUtil;

/**
 * Description: CacheUtil.<br>
 * Date: 2017/7/3 19:14<br>
 * Author: ysj
 */
@Component
public class CacheUtil
{
    private final CacheManager cacheManager;

    public CacheUtil(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }

    /**
     * Get the cache for class
     *
     * @param cls
     * @return the entity cache
     */
    public Cache get(Class<?> cls)
    {
        return get(cls, null);
    }

    /**
     * Get the cache for class and suffix.
     *
     * @param cls
     * @param suffix
     * @return the entity cache
     */
    public Cache get(Class<?> cls, String suffix)
    {
        if (StringUtil.isNotBlank(suffix))
        {
            return this.cacheManager.getCache(cls.getName() + suffix);
        }
        else
        {
            return this.cacheManager.getCache(cls.getName());
        }
    }
}
