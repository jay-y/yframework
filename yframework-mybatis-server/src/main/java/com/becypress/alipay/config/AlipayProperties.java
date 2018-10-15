package com.becypress.alipay.config;

import com.becypress.alipay.domain.AlipayConfig;
import com.becypress.conf.config.ConfProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "alipay", ignoreUnknownFields = false)
public class AlipayProperties implements ConfProperties
{
    private String location;

    private String origin;

    private String gateway;

    private String oauth;

    private String charset;

    private String format;

    private String signType;

    private Map<String, AlipayConfig> configs;

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }

    public String getGateway()
    {
        return gateway;
    }

    public void setGateway(String gateway)
    {
        this.gateway = gateway;
    }

    public String getOauth()
    {
        return oauth;
    }

    public void setOauth(String oauth)
    {
        this.oauth = oauth;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    public String getFormat()
    {
        return format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public String getSignType()
    {
        return signType;
    }

    public void setSignType(String signType)
    {
        this.signType = signType;
    }

    public Map<String, AlipayConfig> getConfigs()
    {
        return configs;
    }

    public void setConfigs(Map<String, AlipayConfig> configs)
    {
        this.configs = configs;
    }
}
