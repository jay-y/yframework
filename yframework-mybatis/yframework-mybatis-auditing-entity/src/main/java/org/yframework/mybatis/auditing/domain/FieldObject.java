package org.yframework.mybatis.auditing.domain;

import java.io.Serializable;

/**
 * Description: FieldObject.<br>
 * Date: 2017/9/8 09:52<br>
 * Author: ysj
 */
public class FieldObject implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Class<?> type;

    private String name;

    private Object value;

    public FieldObject(Class<?> type, String name, Object value)
    {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public Class<?> getType()
    {
        return type;
    }

    public void setType(Class<?> type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }
}
