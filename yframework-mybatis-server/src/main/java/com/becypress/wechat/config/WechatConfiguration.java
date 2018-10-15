package com.becypress.wechat.config;

import com.becypress.conf.config.AbstractConfiguration;
import com.becypress.wechat.WechatLoader;
import com.becypress.wechat.domain.WechatMpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Description: WechatConfiguration.<br>
 * Date: 2017/9/12 09:44<br>
 * Author: ysj
 */
@Configuration
@EnableConfigurationProperties({WechatProperties.class})
@AutoConfigureOrder
public class WechatConfiguration extends AbstractConfiguration<WechatMpConfig, WechatLoader>
{
    private final Logger log = LoggerFactory.getLogger(WechatConfiguration.class);

    public WechatConfiguration(WechatProperties properties)
    {
        super(properties);
    }

    @Override
    protected Logger getLogger()
    {
        return log;
    }

    @Override
    protected WechatLoader getLoader()
    {
        return WechatLoader.INSTANCE;
    }

    @PreDestroy
    protected void destory()
    {
        super.destory();
    }
}
