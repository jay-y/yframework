package com.becypress.boc.pay.domain;

import com.becypress.boc.pay.config.BocPayConstants;
import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: PrecreateReqData.<br>
 * Date: 2017/10/31 18:06<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class PrecreateReqData extends AbstractBocPayReqData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "subject")
    @SignField(name = "subject")
    private String subject;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "body")
    @SignField(name = "body")
    private String body;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "goods_detail")
    @SignField(name = "goods_detail")
    private String goodsDetail;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "store_id")
    @SignField(name = "store_id")
    private String storeId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "operator_id")
    @SignField(name = "operator_id")
    private String operatorId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "fee_type")
    @SignField(name = "fee_type")
    private String feeType;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "total_amount")
    @SignField(name = "total_amount")
    private Integer totalAmount;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "timeout_express")
    @SignField(name = "timeout_express")
    private String timeoutExpress;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "notify_url")
    @SignField(name = "notify_url")
    private String notifyUrl;

    public PrecreateReqData()
    {
        super(BocPayConstants._PAY_ALP_PRECREATE);
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getGoodsDetail()
    {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail)
    {
        this.goodsDetail = goodsDetail;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
    }

    public String getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(String operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getFeeType()
    {
        return feeType;
    }

    public void setFeeType(String feeType)
    {
        this.feeType = feeType;
    }

    public Integer getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getTimeoutExpress()
    {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress)
    {
        this.timeoutExpress = timeoutExpress;
    }

    public String getNotifyUrl()
    {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl)
    {
        this.notifyUrl = notifyUrl;
    }
}
