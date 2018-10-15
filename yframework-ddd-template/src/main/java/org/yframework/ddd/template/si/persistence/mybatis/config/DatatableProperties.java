package org.yframework.ddd.template.si.persistence.mybatis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: DatatableProperties.<br>
 * Date: 2018/10/14 下午11:04<br>
 * Author: ysj
 */
@ConfigurationProperties(prefix = "datatable", ignoreUnknownFields = false)
public class DatatableProperties
{
    private String location;

    private String charset;

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
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
