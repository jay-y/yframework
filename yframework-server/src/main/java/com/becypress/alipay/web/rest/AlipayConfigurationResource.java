package com.becypress.alipay.web.rest;

import com.becypress.alipay.AlipayLoader;
import com.becypress.alipay.config.AlipayProperties;
import com.becypress.alipay.domain.AlipayConfig;
import com.becypress.conf.ConfigurationLoader;
import com.becypress.conf.config.ConfProperties;
import com.becypress.conf.web.rest.AbstractConfigurationResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: AlipayConfigurationResource.<br>
 * Date: 2018/1/3 14:20<br>
 * Author: ysj
 */
@RestController
@RequestMapping("/api/conf/alipay")
public class AlipayConfigurationResource extends AbstractConfigurationResource<AlipayConfig>
{
    private final AlipayProperties properties;

    public AlipayConfigurationResource(AlipayProperties properties)
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
        return AlipayLoader.INSTANCE;
    }
}
