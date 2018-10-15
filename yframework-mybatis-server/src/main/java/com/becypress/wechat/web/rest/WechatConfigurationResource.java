package com.becypress.wechat.web.rest;

import com.becypress.conf.ConfigurationLoader;
import com.becypress.conf.config.ConfProperties;
import com.becypress.conf.web.rest.AbstractConfigurationResource;
import com.becypress.wechat.WechatLoader;
import com.becypress.wechat.config.WechatProperties;
import com.becypress.wechat.domain.WechatMpConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: WechatConfigurationResource.<br>
 * Date: 2018/1/3 14:23<br>
 * Author: ysj
 */
@RestController
@RequestMapping("/api/conf/wechat")
public class WechatConfigurationResource extends AbstractConfigurationResource<WechatMpConfig>
{
    private final WechatProperties properties;

    public WechatConfigurationResource(WechatProperties properties)
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
        return WechatLoader.INSTANCE;
    }
}
