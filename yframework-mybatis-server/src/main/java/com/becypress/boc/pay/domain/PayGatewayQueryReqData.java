package com.becypress.boc.pay.domain;

import com.becypress.boc.pay.config.BocPayConstants;
import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: GatewayBocPayReqData.<br>
 * Date: 2017/11/3 10:09<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class PayGatewayQueryReqData extends AbstractBocPayReqData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "method")
    @SignField(name = "method")
    private String method;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "charset")
    @SignField(name = "charset")
    private String charset;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "sign_type")
    @SignField(name = "sign_type")
    private String signType;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "transaction_id")
    @SignField(name = "transaction_id")
    private String transactionId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "out_trade_no")
    @SignField(name = "out_trade_no")
    private String outTradeNo;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "pass_trade_no")
    @SignField(name = "pass_trade_no")
    private String passTradeNo;

    public PayGatewayQueryReqData()
    {
        super(BocPayConstants._PAY_ALP_GATEWAY);
        this.signType = "MD5";
        this.method = "dcorepay.alipay.query";
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getMethod()
    {
        return method;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    public String getSignType()
    {
        return signType;
    }

    public void setSignType(String signType)
    {
        this.signType = signType;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    @Override
    public String getOutTradeNo()
    {
        return outTradeNo;
    }

    @Override
    public void setOutTradeNo(String outTradeNo)
    {
        this.outTradeNo = outTradeNo;
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
