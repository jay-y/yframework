package com.becypress.boc.pay.domain;

import com.becypress.boc.pay.config.BocPayConstants;
import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 微信退款进度查询
 */
@JacksonXmlRootElement(localName = "xml")
public class RefundqueryReqData extends AbstractBocPayReqData
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
    @JacksonXmlProperty(localName = "pass_refund_no")
    @SignField(name = "pass_refund_no")
    private String passRefundNo; // 通道退款单号

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "refund_id")
    @SignField(name = "refund_id")
    private String refundId; // 微信退款单号

    public RefundqueryReqData(String api)
    {
        super(api);
    }

    public RefundqueryReqData()
    {
        super(BocPayConstants._PAY_WX_REFUND_QUERY);
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

    @Override
    public String toString()
    {
        return "RefundqueryReqData{" + "wxAppid='" + wxAppid + '\'' + ", transactionId='" + transactionId + '\'' + ", passTradeNo='" + passTradeNo + '\'' + ", outRefundNo='" + outRefundNo + '\'' + ", passRefundNo='" + passRefundNo + '\'' + ", refundId='" + refundId + '\'' + '}' + super.toString();
    }
}
