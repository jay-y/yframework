package com.becypress.conf.config;

import com.becypress.conf.ConfigurationLoader;
import com.becypress.conf.domain.Configuration;
import org.slf4j.Logger;

/**
 * Description: AbstractConfiguration.<br>
 * Date: 2018/1/2 13:57<br>
 * Author: ysj
 */
public abstract class AbstractConfiguration<Conf extends Configuration, Loader extends ConfigurationLoader<Conf>>
{
    public <Prop extends ConfProperties> AbstractConfiguration(Prop properties)
    {
        this.getLoader().load(properties);
    }

    protected abstract Logger getLogger();

    protected abstract Loader getLoader();

    protected void destory()
    {
        getLoader().unload();
    }
}
