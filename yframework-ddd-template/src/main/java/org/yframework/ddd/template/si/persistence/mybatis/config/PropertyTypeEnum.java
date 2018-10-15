package org.yframework.ddd.template.si.persistence.mybatis.config;

/**
 * Description: PropertyType.<br>
 * Date: 2018/10/10 下午2:06<br>
 * Author: ysj
 */
public enum PropertyTypeEnum
{
    STRING("string", String.class),
    DATE("date", java.time.Instant.class),
    BOOLEAN("boolean", Boolean.class);

    private String name;

    private Class<?> cls;

    PropertyTypeEnum(String name, Class<?> cls)
    {
        this.name = name;
        this.cls = cls;
    }

    public String getName()
    {
        return name;
    }

    public Class<?> getCls()
    {
        return cls;
    }
}
