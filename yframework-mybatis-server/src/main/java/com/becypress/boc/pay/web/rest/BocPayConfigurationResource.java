package com.becypress.boc.pay.web.rest;

import com.becypress.boc.pay.BocPayLoader;
import com.becypress.boc.pay.config.BocPayProperties;
import com.becypress.boc.pay.domain.BocPayConfig;
import com.becypress.conf.ConfigurationLoader;
import com.becypress.conf.config.ConfProperties;
import com.becypress.conf.web.rest.AbstractConfigurationResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: BocPayConfigurationResource.<br>
 * Date: 2018/1/3 14:07<br>
 * Author: ysj
 */
@RestController
@RequestMapping("/api/conf/boc-pay")
public class BocPayConfigurationResource extends AbstractConfigurationResource<BocPayConfig>
{
    private final BocPayProperties properties;

    public BocPayConfigurationResource(BocPayProperties properties)
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
        return BocPayLoader.INSTANCE;
    }
}
