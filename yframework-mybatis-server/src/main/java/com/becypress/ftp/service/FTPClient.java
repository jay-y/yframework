package com.becypress.ftp.service;

import com.becypress.ftp.config.FTPProperties;
import com.becypress.ftp.domain.FTPConfig;
import com.becypress.toolkit.Becypress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: FTPClient.<br>
 * Date: 2018/2/6 下午5:39<br>
 * Author: ysj
 */
public interface FTPClient
{
    Logger log = LoggerFactory.getLogger(FTPClient.class);

    FTPProperties getFTPProperties();

    FTPConfig getFTPConfigStorage();

    default org.apache.commons.net.ftp.FTPClient get()
    {
        return Becypress.UTIL.extra().ftp().getFTPClient(this.getFTPConfigStorage().getAddress(), this.getFTPConfigStorage().getPort(), this.getFTPConfigStorage().getUsername(), this.getFTPConfigStorage().getPassword());
    }
}
