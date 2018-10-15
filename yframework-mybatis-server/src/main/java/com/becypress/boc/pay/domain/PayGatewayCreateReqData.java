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
public class PayGatewayCreateReqData extends AbstractBocPayReqData
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
    @JacksonXmlProperty(localName = "openid")
    @SignField(name = "openid")
    private String openid; //用户标识

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "body")
    @SignField(name = "body")
    private String body;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "detail")
    @SignField(name = "detail")
    private String detail;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "attach")
    @SignField(name = "attach")
    private String attach;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "fee_type")
    @SignField(name = "fee_type")
    private String feeType; //货币类型

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "total_fee")
    @SignField(name = "total_fee")
    private Integer totalFee; //总金额

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "store_appid")
    @SignField(name = "store_appid")
    private String storeAppid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "op_user")
    @SignField(name = "op_user")
    private String opUser;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "time_start")
    @SignField(name = "time_start")
    private String timeStart; //交易起始时间,yyyyMMddHHmmss

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "time_expire")
    @SignField(name = "time_expire")
    private String timeExpire; //交易结束时间,yyyyMMddHHmmss

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "notify_url")
    @SignField(name = "notify_url")
    private String notifyUrl; //通知地址

    public PayGatewayCreateReqData()
    {
        super(BocPayConstants._PAY_ALP_GATEWAY);
        this.signType = "MD5";
        this.method = "dcorepay.alipay.create";
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

    public String getOpenid()
    {
        return openid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public String getAttach()
    {
        return attach;
    }

    public void setAttach(String attach)
    {
        this.attach = attach;
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

    public String getStoreAppid()
    {
        return storeAppid;
    }

    public void setStoreAppid(String storeAppid)
    {
        this.storeAppid = storeAppid;
    }

    public String getOpUser()
    {
        return opUser;
    }

    public void setOpUser(String opUser)
    {
        this.opUser = opUser;
    }

    public String getTimeStart()
    {
        return timeStart;
    }

    public void setTimeStart(String timeStart)
    {
        this.timeStart = timeStart;
    }

    public String getTimeExpire()
    {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire)
    {
        this.timeExpire = timeExpire;
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
