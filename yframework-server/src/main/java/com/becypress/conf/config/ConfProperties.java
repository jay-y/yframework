package com.becypress.conf.config;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;

/**
 * Description: ConfProperties.<br>
 * Date: 2018/1/2 14:05<br>
 * Author: ysj
 */
public interface ConfProperties
{
    String getLocation();

    String getCharset();

    default String readConf()
    {
        InputStream is = null;
        try
        {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(this.getLocation());
            is = resource.getInputStream();
            return IOUtils.toString(is, this.getCharset());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }
}
