package com.becypress.ftp.service;

import com.becypress.ftp.FTPLoader;
import com.becypress.ftp.config.FTPProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: FTPClients.<br>
 * Date: 2018/2/6 下午6:13<br>
 * Author: ysj
 */
@Service
public class FTPClients
{
    private final FTPProperties ftpProperties;

    private final Map<String, FTPClient> clientMap = new ConcurrentHashMap<>();

    public FTPClients(FTPProperties ftpProperties)
    {
        this.ftpProperties = ftpProperties;
    }

    public Map<String, FTPClient> getClientMap()
    {
        return clientMap;
    }

    public FTPClient getClient(String id)
    {
        return getClientMap().computeIfAbsent(id, k -> new FTPClientImpl(ftpProperties, FTPLoader.INSTANCE.getConfStorage(id)));
    }
}
