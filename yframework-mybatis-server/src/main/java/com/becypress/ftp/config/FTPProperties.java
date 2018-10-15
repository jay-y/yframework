package com.becypress.ftp.config;

import com.becypress.conf.config.ConfProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: FtpProperties.<br>
 * Date: 2018/2/6 下午5:22<br>
 * Author: ysj
 */
@ConfigurationProperties(prefix = "ftp", ignoreUnknownFields = false)
public class FTPProperties implements ConfProperties
{
    private String location;

    private String charset;

    @Override
    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }
}
