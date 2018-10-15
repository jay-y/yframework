package com.becypress.boc.pay.config;

import com.becypress.boc.pay.BocPayLoader;
import com.becypress.boc.pay.domain.BocPayConfig;
import com.becypress.conf.config.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Description: BocPayConfiguration.<br>
 * Date: 2017/9/12 09:44<br>
 * Author: ysj
 */
@Configuration
@EnableConfigurationProperties({BocPayProperties.class})
@AutoConfigureOrder
public class BocPayConfiguration extends AbstractConfiguration<BocPayConfig, BocPayLoader>
{
    private final Logger log = LoggerFactory.getLogger(BocPayConfiguration.class);

    public BocPayConfiguration(BocPayProperties properties)
    {
        super(properties);
    }

    @Override
    protected Logger getLogger()
    {
        return log;
    }

    @Override
    protected BocPayLoader getLoader()
    {
        return BocPayLoader.INSTANCE;
    }

    @PreDestroy
    public void destory()
    {
        BocPayLoader.INSTANCE.unload();
    }
}
