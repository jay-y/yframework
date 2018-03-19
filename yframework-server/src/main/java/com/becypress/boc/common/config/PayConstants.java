package com.becypress.boc.common.config;

/**
 * Description: PayConstants.<br>
 * Date: 2018/1/4 15:27<br>
 * Author: ysj
 */
public class PayConstants
{
    public static final String _ALIPAY = "alipay";

    public static final String _WECHAT = "wechat";

    public static final String _UNION_PAY = "unionpay";

    public static final String REG_USER_AGENT_ALIPAY = ".*AlipayClient.*";

    public static final String REG_USER_AGENT_WECHAT = ".*MicroMessenger.*";

    public static final String REG_USER_AGENT_UNION_PAY = ".*UnionPay.*";

    public static final String[] RESG_USER_AGENT = {
            REG_USER_AGENT_ALIPAY, REG_USER_AGENT_WECHAT, REG_USER_AGENT_UNION_PAY
    };
}
