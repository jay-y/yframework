package org.yframework.ddd.template.si.persistence.mybatis.config;

import java.util.Arrays;

/**
 * Description: SQL操作枚举.<br>
 * Date: 2018/10/10 上午11:24<br>
 * Author: ysj
 */
public enum SQLOperationEnum
{
    LK(" LIKE ", "模糊匹配"),
    LE(" <= ", "小于等于"),
    RE(" >= ", "大于等于"),
    NE(" != ", "不等于"),
    EQ(" = ", "等于"),
    RQ(" > ", "大于"),
    LQ(" < ", "小于");

    protected String vaule;

    protected String description;

    SQLOperationEnum(String vaule, String description)
    {
        this.vaule = vaule;
        this.description = description;
    }

    public static SQLOperationEnum get(final String name)
    {
        return Arrays.stream(SQLOperationEnum.values()).
                filter(o -> o.name().equals(name)).
                findFirst().orElse(null);
    }

    public String getVaule()
    {
        return vaule;
    }

    public void setVaule(String vaule)
    {
        this.vaule = vaule;
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
