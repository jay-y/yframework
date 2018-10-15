package com.becypress.boc.pay.config;

/**
 * Description: BocPayConstants.<br>
 * Date: 2017/9/15 17:43<br>
 * Author: ysj
 */
public class BocPayConstants
{
    //统一订单提交（微信）- 1.0
    public static final String _PAY_WX_UNIFIED_ORDER = "/pay/unifiedorder";

    //订单查询（微信）- 1.0
    public static final String _PAY_WX_QUERY_ORDER = "/pay/orderquery";

    //订单关闭（微信）- 1.0
    public static final String _PAY_WX_CLOSE_ORDER = "/pay/closeorder";

    //申请退款（微信）- 1.0
    public static final String _PAY_WX_REFUND = "/pay/refund";

    //查询退款（微信）- 1.0
    public static final String _PAY_WX_REFUND_QUERY = "/pay/refundquery";

    //下载对账单（微信）- 1.0
    public static final String _PAY_WX_DOWNLOAD_BILL = "/pay/downloadbill";

    //交易预创建接口（支付宝）- 1.0
    public static final String _PAY_ALP_PRECREATE = "/alipay/precreate";

    //订单查询（支付宝）- 1.0
    public static final String _PAY_ALP_QUERY_ORDER = "/alipay/orderquery";

    //交易创建接口（支付宝）- 2.0
    public static final String _PAY_ALP_GATEWAY = "/pay/gateway";
}
