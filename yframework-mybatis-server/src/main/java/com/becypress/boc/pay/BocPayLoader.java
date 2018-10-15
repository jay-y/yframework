package com.becypress.boc.pay;

import com.becypress.boc.pay.domain.BocPayConfig;
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
 * Description: BocPayLoader.<br>
 * Date: 2017/10/31 18:41<br>
 * Author: ysj
 */
public enum BocPayLoader implements ConfigurationLoader<BocPayConfig>
{
    INSTANCE;
    private final Logger log = LoggerFactory.getLogger(BocPayLoader.class);
    private final Map<String, BocPayConfig> configStorageMap = new ConcurrentHashMap<>();

    @Override
    public Logger getLogger()
    {
        return log;
    }

    @Override
    public String getConfName()
    {
        return "bocpay";
    }

    @Override
    public List<BocPayConfig> genConfs(String json) throws IOException
    {
        return JacksonUtil.INSTANCE.get().readValue(json, new TypeReference<List<BocPayConfig>>()
        {
        });
    }

    @Override
    public Map<String, BocPayConfig> getConfStorage()
    {
        return configStorageMap;
    }

    @Override
    public void setConfStorage(Map<String, BocPayConfig> confStorage)
    {
        this.configStorageMap.putAll(confStorage);
    }
}
