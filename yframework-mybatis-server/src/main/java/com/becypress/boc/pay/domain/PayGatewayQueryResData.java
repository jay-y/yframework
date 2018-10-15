package com.becypress.boc.pay.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: PayGatewayResData.<br>
 * Date: 2017/11/18 23:15<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class PayGatewayQueryResData extends AbstractBocPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_state")
    private String tradeState;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "openid")
    private String openid;  //用户标识

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_type")
    private String tradeType;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "total_fee")
    private Integer totalFee; //总金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "fee_type")
    private String feeType; //货币类型

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "coupon_fee")
    private Integer couponFee; // 代金券或立减优惠

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "transaction_id")
    private String transactionId; // 支付宝交易号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "pass_trade_no")
    private String passTradeNo; // 通道订单号：通道的统一订单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "out_trade_no")
    private String outTradeNo; // 商户订单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "attach")
    private String attach;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "buyer_logon_id")
    private String buyerLogonId; // 支付宝：买家支付宝账号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "fund_bill_list")
    private String fundBillList;

    public String getTradeState()
    {
        return tradeState;
    }

    public void setTradeState(String tradeState)
    {
        this.tradeState = tradeState;
    }

    public String getOpenid()
    {
        return openid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getTradeType()
    {
        return tradeType;
    }

    public void setTradeType(String tradeType)
    {
        this.tradeType = tradeType;
    }

    public Integer getTotalFee()
    {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee)
    {
        this.totalFee = totalFee;
    }

    public String getFeeType()
    {
        return feeType;
    }

    public void setFeeType(String feeType)
    {
        this.feeType = feeType;
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

    public String getBuyerLogonId()
    {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId)
    {
        this.buyerLogonId = buyerLogonId;
    }

    public String getFundBillList()
    {
        return fundBillList;
    }

    public void setFundBillList(String fundBillList)
    {
        this.fundBillList = fundBillList;
    }
}
