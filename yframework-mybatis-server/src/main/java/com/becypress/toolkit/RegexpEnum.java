package com.becypress.toolkit;

import java.util.regex.Pattern;

/**
 * RegexpEnum<BR>
 * ------------------------------------------<BR>
 * <BR>
 * Copyright©  : 2017 by Flying_L<BR>
 * Author      : Flying_L <BR>
 * Date        : 2017年10月26日<BR>
 * Description :<BR>
 * <p>
 * 1、
 * </p>
 */
public enum RegexpEnum
{
    DATE_YYYYMMDD("^(19|20)\\d{2}(0\\d|1[0-2])([0-2]\\d|3[01])$", "日期 yyyyMMdd"),  ///^((((19|20)\d{2})-(0?[13-9]|1[012])-(0?[1-9]|[12]\d|30))|(((19|20)\d{2})-(0?[13578]|1[02])-31)|(((19|20)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$/
    DATE_YYYYMMDD_SPLIT("^(19|20)\\d{2}-(0\\d|1[0-2])-([0-2]\\d|3[01])$", "日期 yyyy-MM-dd"), DATETIME_YYYYMMDDHHMMSS("^(19|20)\\d{2}(0\\d|1[0-2])([0-2]\\d|3[01])([01]\\d|2[0-4])([0-5]\\d){2}$", "日期时间 yyyyMMddHHmmss"), DATETIME_YYYYMMDDHHMMSS_SPLIT("^(19|20)\\d{2}-(0\\d|1[0-2])-([0-2]\\d|3[01]) ([01]\\d|2[0-4])(:[0-5]\\d){2}$", "日期时间 yyyy-MM-dd HH:mm:ss"), TIMESTAMP("^1\\d{12}$", "时间戳：毫秒数"), FLAG_01("^[01]$", "标记，仅接受01"), FLAG_012("^[012]$", "标记，仅接受012"),

    TOPUP_DURATION_STATUS("^[1-9]\\d{0,2}$", "月卡时长类型，会用于时间计算，需要使用正则校验"), PLATE_NUM("^[粤浙京沪川津渝鄂赣冀蒙鲁苏辽吉皖湘黑琼贵桂云藏陕甘宁青豫闽新晋港澳][a-zA-Z][a-zA-Z0-9]{4,6}[学警消边通森金电港澳]?$", "车牌"), PLATE_COLOR("^[0-4]$", "车牌颜色"), //0：未知、1：蓝色、2：黄色、3：白色、4：黑色
    ACCT_NO("^\\d{16,20}$", "银行卡"), CURRENCY("^\\w{1,5}", "币种"), TOPUP_CHANNEL("^\\d$", "月卡充值渠道"), PAY_CHANNEL("^[12348]$", "停车场缴费渠道"), PAY_RECORD_STATUS("^[139]$", "缴费记录支付状态"), LOCK_WXID("^\\w{8,}$", "锁定解锁时用到的微信ID"), BLUECARD_USETYPE("^[0-7]$", "蓝卡系统用户类型"), BLUECARD_CARTYPE("^[12]$", "蓝卡系统固定车类型"),;

    /**
     * 正则表达式字符串
     */
    private String regexp = null;

    /**
     * 说明
     */
    private String description = null;

    public String getRegexp()
    {
        return regexp;
    }

    public void setRegexp(String regexp)
    {
        this.regexp = regexp;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    RegexpEnum(String regexp, String description)
    {
        this.regexp = regexp;
        this.description = description;
    }

    public boolean isMatches(CharSequence input)
    {
        return Pattern.compile(this.regexp).matcher(input).matches();
    }

    public boolean isNotMatches(CharSequence input)
    {
        return !this.isMatches(input);
    }
}
