package com.becypress.boc.pay.config;

import com.becypress.conf.config.ConfProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: BocPayProperties.<br>
 * Date: 2017/9/15 18:43<br>
 * Author: ysj
 */
@ConfigurationProperties(prefix = "bocpay", ignoreUnknownFields = false)
public class BocPayProperties implements ConfProperties
{
    private String location;

    private String gateway;

    private String charset;

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getGateway()
    {
        return gateway;
    }

    public void setGateway(String gateway)
    {
        this.gateway = gateway;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }
}
