package com.becypress.boc.pay.domain;

import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 微信退款请求返回
 */
@JacksonXmlRootElement(localName = "xml")
public class OrderReverseResData extends AbstractBocPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "wx_appid")
    @SignField(name = "wx_appid")
    private String wxAppid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "transaction_id")
    @SignField(name = "transaction_id")
    private String transactionId; // 微信支付订单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "pass_trade_no")
    @SignField(name = "pass_trade_no")
    private String passTradeNo; // 通道订单号：通道的统一订单号，即微信支付通知中的“商户单号”

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "out_trade_no")
    @SignField(name = "out_trade_no")
    private String outTradeNo; // 商户订单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "out_refund_no")
    @SignField(name = "out_refund_no")
    private String outRefundNo; // 商户退款单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "pass_refund_no")
    @SignField(name = "pass_refund_no")
    private String passRefundNo; // 通道退款单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_id")
    @SignField(name = "refund_id")
    private String refundId; // 微信退款单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_channel")
    @SignField(name = "refund_channel")
    private String refundChannel; // 退款渠道

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_fee")
    @SignField(name = "refund_fee")
    private Integer refundFee; // 退款总金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "coupon_refund_fee")
    @SignField(name = "coupon_refund_fee")
    private Integer couponRefundFee; // 代金券或立减优惠退款金额


    public String getWxAppid()
    {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid)
    {
        this.wxAppid = wxAppid;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    public String getPassTradeNo()
    {
        return passTradeNo;
    }

    public void setPassTradeNo(String passTradeNo)
    {
        this.passTradeNo = passTradeNo;
    }

    public String getOutTradeNo()
    {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo)
    {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo()
    {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo)
    {
        this.outRefundNo = outRefundNo;
    }

    public String getPassRefundNo()
    {
        return passRefundNo;
    }

    public void setPassRefundNo(String passRefundNo)
    {
        this.passRefundNo = passRefundNo;
    }

    public String getRefundId()
    {
        return refundId;
    }

    public void setRefundId(String refundId)
    {
        this.refundId = refundId;
    }

    public String getRefundChannel()
    {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel)
    {
        this.refundChannel = refundChannel;
    }

    public Integer getRefundFee()
    {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee)
    {
        this.refundFee = refundFee;
    }

    public Integer getCouponRefundFee()
    {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee)
    {
        this.couponRefundFee = couponRefundFee;
    }

    @Override
    public String toString()
    {
        return "OrderReverseResData{" + "wxAppid='" + wxAppid + '\'' + ", transactionId='" + transactionId + '\'' + ", passTradeNo='" + passTradeNo + '\'' + ", outTradeNo='" + outTradeNo + '\'' + ", outRefundNo='" + outRefundNo + '\'' + ", passRefundNo='" + passRefundNo + '\'' + ", refundId='" + refundId + '\'' + ", refundChannel='" + refundChannel + '\'' + ", refundFee=" + refundFee + ", couponRefundFee=" + couponRefundFee + '}' + super.toString();
    }
}
