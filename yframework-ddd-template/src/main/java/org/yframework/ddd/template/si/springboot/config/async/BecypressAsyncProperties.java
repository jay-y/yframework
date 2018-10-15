package org.yframework.ddd.template.si.springboot.config.async;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: BecypressAsyncProperties.<br>
 * Date: 2017/9/20 15:14<br>
 * Author: ysj
 */
@ConfigurationProperties(prefix = "becypress-async", ignoreUnknownFields = false)
public class BecypressAsyncProperties
{
    private int corePoolSize = 2;
    private int maxPoolSize = 50;
    private int queueCapacity = 10000;

    public int getCorePoolSize()
    {
        return this.corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize)
    {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize()
    {
        return this.maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize)
    {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueueCapacity()
    {
        return this.queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity)
    {
        this.queueCapacity = queueCapacity;
    }
}
