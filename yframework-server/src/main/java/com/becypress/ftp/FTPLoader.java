package com.becypress.ftp;

import com.becypress.conf.ConfigurationLoader;
import com.becypress.ftp.domain.FTPConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.json.JacksonUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: FtpLoader.<br>
 * Date: 2018/2/6 下午5:25<br>
 * Author: ysj
 */
public enum FTPLoader implements ConfigurationLoader<FTPConfig>
{
    INSTANCE;

    private final Logger log = LoggerFactory.getLogger(FTPConfig.class);
    private final Map<String, FTPConfig> configStorageMap = new ConcurrentHashMap<>();

    @Override
    public Logger getLogger()
    {
        return log;
    }

    @Override
    public String getConfName()
    {
        return "ftp";
    }

    @Override
    public List<FTPConfig> genConfs(String json) throws IOException
    {
        return JacksonUtil.INSTANCE.get().readValue(json, new TypeReference<List<FTPConfig>>()
        {
        });
    }

    @Override
    public Map<String, FTPConfig> getConfStorage()
    {
        return configStorageMap;
    }

    @Override
    public void setConfStorage(Map<String, FTPConfig> confStorage)
    {
        this.configStorageMap.putAll(confStorage);
    }
}
