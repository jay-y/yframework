package com.becypress.ftp.config;

import com.becypress.conf.config.AbstractConfiguration;
import com.becypress.ftp.FTPLoader;
import com.becypress.ftp.domain.FTPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Description: FtpConfiguration.<br>
 * Date: 2018/2/6 下午5:22<br>
 * Author: ysj
 */
@Configuration
@EnableConfigurationProperties({FTPProperties.class})
@AutoConfigureOrder
public class FTPConfiguration extends AbstractConfiguration<FTPConfig, FTPLoader>
{
    private final Logger log = LoggerFactory.getLogger(FTPConfiguration.class);

    public FTPConfiguration(FTPProperties properties)
    {
        super(properties);
    }

    @Override
    protected Logger getLogger()
    {
        return log;
    }

    @Override
    protected FTPLoader getLoader()
    {
        return FTPLoader.INSTANCE;
    }

    @PreDestroy
    protected void destory()
    {
        super.destory();
    }
}
