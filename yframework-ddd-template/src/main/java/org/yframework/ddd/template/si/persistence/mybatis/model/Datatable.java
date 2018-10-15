package org.yframework.ddd.template.si.persistence.mybatis.model;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.yframework.toolkit.y;

import java.io.InputStream;
import java.util.LinkedList;

/**
 * Description: Datatable.<br>
 * Date: 2018/10/12 下午2:34<br>
 * Author: ysj
 */
public class Datatable
{
    private String id;

    private String name;

    private String description;

    private LinkedList<Property> properties;

    public Datatable()
    {
    }

    public Datatable(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LinkedList<Property> getProperties()
    {
        return properties;
    }

    public void setProperties(LinkedList<Property> properties)
    {
        this.properties = properties;
    }

    public Datatable readConf(String location, String charset)
    {
        InputStream is = null;
        try
        {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            String path = location.replace("{filename}", this.id);
            Resource resource = resourceLoader.getResource(path);
            is = resource.getInputStream();
            String json = IOUtils.toString(is, charset);
            return y.util().json().jsonToObject(json, this.getClass());
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

    @Override
    public String toString()
    {
        return "Datatable{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", description='" + description + '\'' + ", properties=" + properties + '}';
    }
}
