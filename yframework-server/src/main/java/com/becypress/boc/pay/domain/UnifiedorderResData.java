package com.becypress.boc.pay.domain;

import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: UnifiedorderReqData.<br>
 * Date: 2017/9/15 17:13<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class UnifiedorderResData extends AbstractBocPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "wx_appid")
    @SignField(name = "wx_appid")
    private String wxAppid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_type")
    @SignField(name = "trade_type")
    private String tradeType;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "prepay_id")
    @SignField(name = "prepay_id")
    private String prepayId; // 预支付ID

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "code_url")
    @SignField(name = "code_url")
    private String codeUrl;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "jsapi_appid")
    @SignField(name = "jsapi_appid")
    private String jsapiAppid;  //JSAPI支付公众号ID

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "jsapi_timestamp")
    @SignField(name = "jsapi_timestamp")
    private String jsapiTimestamp;  //JSAPI支付时间戳

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "jsapi_noncestr")
    @SignField(name = "jsapi_noncestr")
    private String jsapiNoncestr; //  用户是否关注公众账号，Y- 关注，N-未关注，仅在公众账号类型支付

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "bank_type")
    @SignField(name = "bank_type")
    private String bankType; // JSAPI支付随机字符串

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "jsapi_package")
    @SignField(name = "jsapi_package")
    private String jsapiPackage; // JSAPI 订单详情扩展字符串

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "jsapi_signtype")
    @SignField(name = "jsapi_signtype")
    private String jsapiSigntype; //JSAPI 签名方式

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "jsapi_paysign")
    @SignField(name = "jsapi_paysign")
    private String jsapiPaysign; // JSAPI签名

    public String getWxAppid()
    {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid)
    {
        this.wxAppid = wxAppid;
    }

    public String getTradeType()
    {
        return tradeType;
    }

    public void setTradeType(String tradeType)
    {
        this.tradeType = tradeType;
    }

    public String getPrepayId()
    {
        return prepayId;
    }

    public void setPrepayId(String prepayId)
    {
        this.prepayId = prepayId;
    }

    public String getCodeUrl()
    {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl)
    {
        this.codeUrl = codeUrl;
    }

    public String getJsapiAppid()
    {
        return jsapiAppid;
    }

    public void setJsapiAppid(String jsapiAppid)
    {
        this.jsapiAppid = jsapiAppid;
    }

    public String getJsapiTimestamp()
    {
        return jsapiTimestamp;
    }

    public void setJsapiTimestamp(String jsapiTimestamp)
    {
        this.jsapiTimestamp = jsapiTimestamp;
    }

    public String getJsapiNoncestr()
    {
        return jsapiNoncestr;
    }

    public void setJsapiNoncestr(String jsapiNoncestr)
    {
        this.jsapiNoncestr = jsapiNoncestr;
    }

    public String getBankType()
    {
        return bankType;
    }

    public void setBankType(String bankType)
    {
        this.bankType = bankType;
    }

    public String getJsapiPackage()
    {
        return jsapiPackage;
    }

    public void setJsapiPackage(String jsapiPackage)
    {
        this.jsapiPackage = jsapiPackage;
    }

    public String getJsapiSigntype()
    {
        return jsapiSigntype;
    }

    public void setJsapiSigntype(String jsapiSigntype)
    {
        this.jsapiSigntype = jsapiSigntype;
    }

    public String getJsapiPaysign()
    {
        return jsapiPaysign;
    }

    public void setJsapiPaysign(String jsapiPaysign)
    {
        this.jsapiPaysign = jsapiPaysign;
    }
}
