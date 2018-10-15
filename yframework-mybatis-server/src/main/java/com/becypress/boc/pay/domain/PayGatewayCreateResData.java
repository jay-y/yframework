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
public class PayGatewayCreateResData extends AbstractBocPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_no")
    private String tradeNo;

    public String getTradeNo()
    {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo)
    {
        this.tradeNo = tradeNo;
    }
}
