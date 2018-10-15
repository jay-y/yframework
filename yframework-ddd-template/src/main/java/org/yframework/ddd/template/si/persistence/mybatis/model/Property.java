package org.yframework.ddd.template.si.persistence.mybatis.model;

import java.util.List;

/**
 * Description: 属性.<br>
 * Date: 2018/10/10 上午11:27<br>
 * Author: ysj
 */
public class Property
{
    private String name;

    private String alias;

    private String type;

    private String description;

    private boolean required;

    private List<String> roles;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isRequired()
    {
        return required;
    }

    public void setRequired(boolean required)
    {
        this.required = required;
    }

    public List<String> getRoles()
    {
        return roles;
    }

    public void setRoles(List<String> roles)
    {
        this.roles = roles;
    }

    @Override
    public String toString()
    {
        return "Property{" + "name='" + name + '\'' + ", alias='" + alias + '\'' + ", type='" + type + '\'' + ", description='" + description + '\'' + ", required=" + required + ", roles=" + roles + '}';
    }
}
