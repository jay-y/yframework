package com.becypress.alipay.config;

import com.becypress.alipay.AlipayLoader;
import com.becypress.alipay.domain.AlipayConfig;
import com.becypress.conf.config.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Description: AlipayConfiguration.<br>
 * Date: 2017/10/31 09:44<br>
 * Author: ysj
 */
@Configuration
@EnableConfigurationProperties({AlipayProperties.class})
@AutoConfigureOrder
public class AlipayConfiguration extends AbstractConfiguration<AlipayConfig, AlipayLoader>
{
    private final Logger log = LoggerFactory.getLogger(AlipayConfiguration.class);

    public AlipayConfiguration(AlipayProperties properties)
    {
        super(properties);
    }

    @Override
    protected Logger getLogger()
    {
        return log;
    }

    @Override
    protected AlipayLoader getLoader()
    {
        return AlipayLoader.INSTANCE;
    }

    @PreDestroy
    protected void destory()
    {
        super.destory();
    }
}
