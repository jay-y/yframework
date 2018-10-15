package com.becypress.alipay;

import com.becypress.alipay.domain.AlipayConfig;
import com.becypress.conf.ConfigurationLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.json.JacksonUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: AlipayLoader.<br>
 * Date: 2017/8/23 20:59<br>
 * Author: ysj
 */
public enum AlipayLoader implements ConfigurationLoader<AlipayConfig>
{
    INSTANCE;
    private final Logger log = LoggerFactory.getLogger(AlipayLoader.class);
    private final Map<String, AlipayConfig> configStorageMap = new ConcurrentHashMap<>();

    @Override
    public Logger getLogger()
    {
        return log;
    }

    @Override
    public String getConfName()
    {
        return "alipay";
    }

    @Override
    public List<AlipayConfig> genConfs(String json) throws IOException
    {
        return JacksonUtil.INSTANCE.get().readValue(json, new TypeReference<List<AlipayConfig>>()
        {
        });
    }

    @Override
    public Map<String, AlipayConfig> getConfStorage()
    {
        return configStorageMap;
    }

    @Override
    public void setConfStorage(Map<String, AlipayConfig> confStorage)
    {
        this.configStorageMap.putAll(confStorage);
    }
}
