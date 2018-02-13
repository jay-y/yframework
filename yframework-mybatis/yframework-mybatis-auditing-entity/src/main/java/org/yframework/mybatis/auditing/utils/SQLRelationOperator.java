package org.yframework.mybatis.auditing.utils;

/**
 * Description: Operator.<br>
 * Date: 2018/2/12 下午3:07<br>
 * Author: ysj
 */
public enum SQLRelationOperator
{
    EQ(" = "), NE(" <> "), LE(" <= "), LT(" < "), GE(" >= "), GT(" > "), LIKE(" LIKE ");

    private String value;

    SQLRelationOperator(String value)
    {
        this.value = value;
    }

    public String get()
    {
        return value;
    }
}
