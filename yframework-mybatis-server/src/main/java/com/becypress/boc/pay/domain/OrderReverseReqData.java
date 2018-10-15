package com.becypress.boc.pay.domain;

import com.becypress.boc.pay.config.BocPayConstants;
import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 微信退款请求参数
 */
@JacksonXmlRootElement(localName = "xml")
public class OrderReverseReqData extends AbstractBocPayReqData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "wx_appid")
    @SignField(name = "wx_appid")
    private String wxAppid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "transaction_id")
    @SignField(name = "transaction_id")
    private String transactionId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "pass_trade_no")
    @SignField(name = "pass_trade_no")
    private String passTradeNo;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "out_refund_no")
    @SignField(name = "out_refund_no")
    private String outRefundNo; // 商户退款单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "total_fee")
    @SignField(name = "total_fee")
    private Integer totalFee; // 总金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_fee")
    @SignField(name = "refund_fee")
    private Integer refundFee; // 退款金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_fee_type")
    @SignField(name = "refund_fee_type")
    private String refundFeeType; // 货币类型

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "op_user_id")
    @SignField(name = "op_user_id")
    private String opUserId; // 操作员


    public OrderReverseReqData(String api)
    {
        super(api);
    }

    public OrderReverseReqData()
    {
        super(BocPayConstants._PAY_WX_REFUND);
    }

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

    public String getOutRefundNo()
    {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo)
    {
        this.outRefundNo = outRefundNo;
    }

    public Integer getTotalFee()
    {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee)
    {
        this.totalFee = totalFee;
    }

    public Integer getRefundFee()
    {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee)
    {
        this.refundFee = refundFee;
    }

    public String getRefundFeeType()
    {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType)
    {
        this.refundFeeType = refundFeeType;
    }

    public String getOpUserId()
    {
        return opUserId;
    }

    public void setOpUserId(String opUserId)
    {
        this.opUserId = opUserId;
    }

    @Override
    public String toString()
    {
        return "OrderReverseReqData{" + "wxAppid='" + wxAppid + '\'' + ", transactionId='" + transactionId + '\'' + ", passTradeNo='" + passTradeNo + '\'' + ", outRefundNo='" + outRefundNo + '\'' + ", totalFee=" + totalFee + ", refundFee=" + refundFee + ", refundFeeType='" + refundFeeType + '\'' + ", opUserId='" + opUserId + '\'' + '}' + super.toString();
    }
}
