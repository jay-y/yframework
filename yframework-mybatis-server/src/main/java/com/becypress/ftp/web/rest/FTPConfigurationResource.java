package com.becypress.ftp.web.rest;

import com.becypress.conf.ConfigurationLoader;
import com.becypress.conf.config.ConfProperties;
import com.becypress.conf.web.rest.AbstractConfigurationResource;
import com.becypress.ftp.FTPLoader;
import com.becypress.ftp.config.FTPProperties;
import com.becypress.ftp.domain.FTPConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: FTPConfigurationResource.<br>
 * Date: 2018/2/6 下午6:16<br>
 * Author: ysj
 */
@RestController
@RequestMapping("/api/conf/ftp")
public class FTPConfigurationResource extends AbstractConfigurationResource<FTPConfig>
{
    private final FTPProperties properties;

    public FTPConfigurationResource(FTPProperties properties)
    {
        this.properties = properties;
    }

    @Override
    public ConfProperties getProperties()
    {
        return properties;
    }

    @Override
    public ConfigurationLoader getLoader()
    {
        return FTPLoader.INSTANCE;
    }
}
