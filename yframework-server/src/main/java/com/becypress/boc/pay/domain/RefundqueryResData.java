package com.becypress.boc.pay.domain;

import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 微信退款进度查询返回
 */
@JacksonXmlRootElement(localName = "xml")
public class RefundqueryResData extends AbstractBocPayResData
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
    @JacksonXmlProperty(localName = "refund_count")
    @SignField(name = "refund_count")
    private Integer refundCount; // 退款笔数

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "out_refund_no_0")
    @SignField(name = "out_refund_no_0")
    private String outRefundNo; // 商户退款单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "pass_refund_no_0")
    @SignField(name = "pass_refund_no_0")
    private String passRefundNo; // 通道退款单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_id_0")
    @SignField(name = "refund_id_0")
    private String refundId; // 微信退款单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_channel_0")
    @SignField(name = "refund_channel_0")
    private String refundChannel; // 退款渠道

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_fee_0")
    @SignField(name = "refund_fee_0")
    private Integer refundFee; //

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "fee_type_0")
    @SignField(name = "fee_type_0")
    private String feeType; //

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "coupon_refund_fee_0")
    @SignField(name = "coupon_refund_fee_0")
    private Integer couponRefundFee; // 代金券或立减优惠退款金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_status_0")
    @SignField(name = "refund_status_0")
    private String refundStatus; // 退款状态


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

    public Integer getRefundCount()
    {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount)
    {
        this.refundCount = refundCount;
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

    public String getFeeType()
    {
        return feeType;
    }

    public void setFeeType(String feeType)
    {
        this.feeType = feeType;
    }

    public Integer getCouponRefundFee()
    {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee)
    {
        this.couponRefundFee = couponRefundFee;
    }

    public String getRefundStatus()
    {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus)
    {
        this.refundStatus = refundStatus;
    }

    @Override
    public String toString()
    {
        return "RefundqueryResData{" + "wxAppid='" + wxAppid + '\'' + ", transactionId='" + transactionId + '\'' + ", passTradeNo='" + passTradeNo + '\'' + ", outTradeNo='" + outTradeNo + '\'' + ", refundCount=" + refundCount + ", outRefundNo='" + outRefundNo + '\'' + ", passRefundNo='" + passRefundNo + '\'' + ", refundId='" + refundId + '\'' + ", refundChannel='" + refundChannel + '\'' + ", refundFee=" + refundFee + ", feeType='" + feeType + '\'' + ", couponRefundFee=" + couponRefundFee + ", refundStatus='" + refundStatus + '\'' + '}' + super.toString();
    }
}
