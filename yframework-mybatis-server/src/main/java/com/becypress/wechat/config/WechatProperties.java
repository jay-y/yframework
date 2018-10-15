package com.becypress.wechat.config;

import com.becypress.conf.config.ConfProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wechat", ignoreUnknownFields = false)
public class WechatProperties implements ConfProperties
{
    private String location;

    private String origin;

    private String charset;

    private String oauth;

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

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    public String getOauth()
    {
        return oauth;
    }

    public void setOauth(String oauth)
    {
        this.oauth = oauth;
    }
}
