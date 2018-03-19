package com.becypress.boc.pay.domain;

import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: OrderqueryResData.<br>
 * Date: 2017/9/15 17:13<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class OrderqueryResData extends AbstractBocPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "wx_appid")
    @SignField(name = "wx_appid")
    private String wxAppid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_state")
    @SignField(name = "trade_state")
    private String tradeState; // 交易状态

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_state_desc")
    @SignField(name = "trade_state_desc")
    private String tradeStateDesc; // 交易状态描述

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "openid")
    @SignField(name = "openid")
    private String openid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "is_subscribe")
    @SignField(name = "is_subscribe")
    private String isSubscribe; //  用户是否关注公众账号，Y- 关注，N-未关注，仅在公众账号类型支付

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_type")
    @SignField(name = "trade_type")
    private String tradeType;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "bank_type")
    @SignField(name = "bank_type")
    private String bankType; // JSAPI支付随机字符串

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "fee_type")
    @SignField(name = "fee_type")
    private String feeType; //货币类型

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "total_fee")
    @SignField(name = "total_fee")
    private Integer totalFee; //总金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "coupon_fee")
    @SignField(name = "coupon_fee")
    private Integer couponFee; // 代金券或立减优惠

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
    @JacksonXmlProperty(localName = "attach")
    @SignField(name = "attach")
    private String attach;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "time_end")
    @SignField(name = "time_end")
    private String timeEnd; // 支付完成时间

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "code")
    @SignField(name = "code")
    private String code; // 支付宝：状态码

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_no")
    @SignField(name = "trade_no")
    private String tradeNo; // 支付宝支付时返回：支付宝交易号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_status")
    @SignField(name = "trade_status")
    private String tradeStatus; // 支付宝支付时返回：交易状态：

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "receipt_amount")
    @SignField(name = "receipt_amount")
    private Integer receiptAmount; // 支付宝：实收金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "invoice_amount")
    @SignField(name = "invoice_amount")
    private Integer invoiceAmount; // 支付宝：开票金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "buyer_pay_amount")
    @SignField(name = "buyer_pay_amount")
    private Integer buyerPayAmount; // 支付宝：付款金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "point_amount")
    @SignField(name = "point_amount")
    private Integer pointAmount; // 支付宝：集分宝支付金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "buyer_user_id")
    @SignField(name = "buyer_user_id")
    private String buyerUserId; // 支付宝：买家支付宝用户号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "buyer_logon_id")
    @SignField(name = "buyer_logon_id")
    private String buyerLogonId; // 支付宝：买家支付宝账号

    public String getWxAppid()
    {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid)
    {
        this.wxAppid = wxAppid;
    }

    public String getTradeState()
    {
        return tradeState;
    }

    public void setTradeState(String tradeState)
    {
        this.tradeState = tradeState;
    }

    public String getTradeStateDesc()
    {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc)
    {
        this.tradeStateDesc = tradeStateDesc;
    }

    public String getOpenid()
    {
        return openid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getIsSubscribe()
    {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe)
    {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType()
    {
        return tradeType;
    }

    public void setTradeType(String tradeType)
    {
        this.tradeType = tradeType;
    }

    public String getBankType()
    {
        return bankType;
    }

    public void setBankType(String bankType)
    {
        this.bankType = bankType;
    }

    public String getFeeType()
    {
        return feeType;
    }

    public void setFeeType(String feeType)
    {
        this.feeType = feeType;
    }

    public Integer getTotalFee()
    {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee)
    {
        this.totalFee = totalFee;
    }

    public Integer getCouponFee()
    {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee)
    {
        this.couponFee = couponFee;
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

    public String getAttach()
    {
        return attach;
    }

    public void setAttach(String attach)
    {
        this.attach = attach;
    }

    public String getTimeEnd()
    {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd)
    {
        this.timeEnd = timeEnd;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getTradeNo()
    {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo)
    {
        this.tradeNo = tradeNo;
    }

    public String getTradeStatus()
    {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus)
    {
        this.tradeStatus = tradeStatus;
    }

    public Integer getReceiptAmount()
    {
        return receiptAmount;
    }

    public void setReceiptAmount(Integer receiptAmount)
    {
        this.receiptAmount = receiptAmount;
    }

    public Integer getInvoiceAmount()
    {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Integer invoiceAmount)
    {
        this.invoiceAmount = invoiceAmount;
    }

    public Integer getBuyerPayAmount()
    {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(Integer buyerPayAmount)
    {
        this.buyerPayAmount = buyerPayAmount;
    }

    public Integer getPointAmount()
    {
        return pointAmount;
    }

    public void setPointAmount(Integer pointAmount)
    {
        this.pointAmount = pointAmount;
    }

    public String getBuyerUserId()
    {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId)
    {
        this.buyerUserId = buyerUserId;
    }

    public String getBuyerLogonId()
    {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId)
    {
        this.buyerLogonId = buyerLogonId;
    }
}
