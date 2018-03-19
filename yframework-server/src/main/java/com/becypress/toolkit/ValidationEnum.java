package com.becypress.toolkit;

import com.becypress.toolkit.error.CustomParameterizedException;
import org.yframework.toolkit.StringUtil;
import org.yframework.toolkit.y;

/**
 * ValidationEnum<BR>
 * ------------------------------------------<BR>
 * <BR>
 * Copyright©  : 2017 by Flying_L<BR>
 * Author      : Flying_L <BR>
 * Date        : 2017年10月27日<BR>
 * Description :<BR>
 * <p>
 * 1、
 * </p>
 */
public enum ValidationEnum
{
    TRUE((input, param) -> input != null && input instanceof Boolean && (Boolean) input, "判断TRUE"), FALSE((input, param) -> input != null && input instanceof Boolean && !(Boolean) input, "判断FALSE"), NULL((input, param) -> input == null, "判断NULL"), NOT_NULL((input, param) -> input != null, "判断非NULL"), BLANK((input, param) -> (input == null || StringUtil.isBlank(input.toString())), "判断空白"), NOT_BLANK((input, param) -> (input != null && StringUtil.isNotBlank(input.toString())), "判断非空白"), REGEXP_MATCH((input, param) -> !(param == null || !(param instanceof RegexpEnum)) && ((RegexpEnum) param).isMatches(input == null ? "" : input.toString()), "判断正则匹配"), REGEXP_NOT_MATCH((input, param) -> !(param == null || !(param instanceof RegexpEnum)) && ((RegexpEnum) param).isNotMatches(input == null ? "" : input.toString()), "判断正则不匹配"), COMPARE_GT((input, param) -> !(input == null || param == null || !(input instanceof Comparable)) && ((Comparable) input).compareTo(param) > 0, "判断大于"), COMPARE_GET((input, param) -> !(input == null || param == null || !(input instanceof Comparable)) && ((Comparable) input).compareTo(param) >= 0, "判断大于等于"), COMPARE_LT((input, param) -> !(input == null || param == null || !(input instanceof Comparable)) && ((Comparable) input).compareTo(param) < 0, "判断小于"), COMPARE_LET((input, param) -> !(input == null || param == null || !(input instanceof Comparable)) && ((Comparable) input).compareTo(param) <= 0, "判断小于等于"), COMPARE_EQ((input, param) -> (input == null && param == null) || (input != null && input.equals(param)), "判断等于"), COMPARE_NE((input, param) -> (input == null && param != null) || (input != null && !input.equals(param)), "判断不等于"), DATE_YYYYMMDD((input, param) ->
{
    if (input == null)
    {
        return true;
    }
    if (RegexpEnum.DATE_YYYYMMDD.isNotMatches(input.toString()))
    {
        return false;
    }
    String dateStr = input.toString();
    return y.util().time().get(y.util().time().parseDate(dateStr, "yyyyMMdd"), "yyyyMMdd").equals(dateStr);
}, "判断日期");

    /**
     * 校验器
     */
    private ValidateProvider provider = null;

    /**
     * 说明
     */
    private String description = null;

    public ValidateProvider getProvider()
    {
        return provider;
    }

    public void setProvider(ValidateProvider provider)
    {
        this.provider = provider;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    ValidationEnum(ValidateProvider provider, String description)
    {
        this.provider = provider;
        this.description = description;
    }

    public boolean valid(Object input, String message)
    {
        return this.valid(input, null, message);
    }

    public boolean valid(Object input, Object param, String message)
    {
        if (this.getProvider().validate(input, param))
        {
            return true;
        }
        throw new CustomParameterizedException(message, input == null ? "null" : input.toString());
    }

    public boolean invalid(Object input, String message)
    {
        return this.invalid(input, null, message);
    }

    public boolean invalid(Object input, Object param, String message)
    {
        if (!this.getProvider().validate(input, param))
        {
            return true;
        }
        throw new CustomParameterizedException(message, input == null ? "null" : input.toString());
    }
}
