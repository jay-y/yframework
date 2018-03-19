package com.becypress.wechat;

import com.becypress.conf.ConfigurationLoader;
import com.becypress.wechat.domain.WechatMpConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.json.JacksonUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: WechatLoader.<br>
 * Date: 2017/8/23 20:59<br>
 * Author: ysj
 */
public enum WechatLoader implements ConfigurationLoader<WechatMpConfig>
{
    INSTANCE;
    private final Logger log = LoggerFactory.getLogger(WechatLoader.class);
    private final Map<String, WechatMpConfig> configStorageMap = new ConcurrentHashMap<>();

    @Override
    public Logger getLogger()
    {
        return log;
    }

    @Override
    public String getConfName()
    {
        return "wechat";
    }

    @Override
    public List<WechatMpConfig> genConfs(String json) throws IOException
    {
        return JacksonUtil.INSTANCE.get().readValue(json, new TypeReference<List<WechatMpConfig>>()
        {
        });
    }

    @Override
    public Map<String, WechatMpConfig> getConfStorage()
    {
        return configStorageMap;
    }

    @Override
    public void setConfStorage(Map<String, WechatMpConfig> confStorage)
    {
        this.configStorageMap.putAll(confStorage);
    }
}
