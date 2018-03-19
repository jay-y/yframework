package com.cust.biz.web.vo;

/**
 * Description: ResourceVO.<br>
 * Date: 2018/2/24 下午4:07<br>
 * Author: ysj
 */
public class ResourceVO
{
    private String mapping;

    private String description;

    public ResourceVO(String mapping, String description)
    {
        this.mapping = mapping;
        this.description = description;
    }

    public String getMapping()
    {
        return mapping;
    }

    public void setMapping(String mapping)
    {
        this.mapping = mapping;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
