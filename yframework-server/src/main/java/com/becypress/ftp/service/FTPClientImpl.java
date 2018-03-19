package com.becypress.ftp.service;

import com.becypress.ftp.config.FTPProperties;
import com.becypress.ftp.domain.FTPConfig;

/**
 * Description: FTPClientImpl.<br>
 * Date: 2018/2/6 下午6:11<br>
 * Author: ysj
 */
public class FTPClientImpl implements FTPClient
{
    private final FTPProperties ftpProperties;

    private final FTPConfig ftpConfigStorage;

    public FTPClientImpl(FTPProperties ftpProperties, FTPConfig ftpConfigStorage)
    {
        this.ftpProperties = ftpProperties;
        this.ftpConfigStorage = ftpConfigStorage;
    }

    @Override
    public FTPProperties getFTPProperties()
    {
        return ftpProperties;
    }

    @Override
    public FTPConfig getFTPConfigStorage()
    {
        return ftpConfigStorage;
    }
}
