package com.becypress.boc.pay.domain;

import com.becypress.boc.pay.config.BocPayConstants;
import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: OrderqueryReqData.<br>
 * Date: 2017/9/18 13:59<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class OrderqueryReqData extends AbstractBocPayReqData
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
    @JacksonXmlProperty(localName = "trade_no")
    @SignField(name = "trade_no")
    private String tradeNo;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "pass_trade_no")
    @SignField(name = "pass_trade_no")
    private String passTradeNo;

    public OrderqueryReqData(String api)
    {
        super(api);
    }

    public OrderqueryReqData()
    {
        super(BocPayConstants._PAY_WX_QUERY_ORDER);
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

    public String getTradeNo()
    {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo)
    {
        this.tradeNo = tradeNo;
    }

    public String getPassTradeNo()
    {
        return passTradeNo;
    }

    public void setPassTradeNo(String passTradeNo)
    {
        this.passTradeNo = passTradeNo;
    }
}
