package com.becypress.boc.pay.domain;

import com.becypress.boc.pay.utils.BocPaySignCoder;
import com.becypress.sign.annotation.SignField;
import com.becypress.sign.utils.SignCtrl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.yframework.mybatis.auditing.generator.IDGenerator;

/**
 * Description: AbstractBocPayReqData.<br>
 * Date: 2017/9/15 17:32<br>
 * Author: ysj
 */
public abstract class AbstractBocPayReqData implements BocPayReqData
{
    @JsonIgnore
    private String api;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "version")
    @SignField(name = "version")
    private String version;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "appid")
    @SignField(name = "appid")
    private String appid; //中行分配的APPID

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "mch_id")
    @SignField(name = "mch_id")
    private String mchId; //中行分配的商户ID

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "terminal_id")
    @SignField(name = "terminal_id")
    private String terminalId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "device_info")
    @SignField(name = "device_info")
    private String deviceInfo;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "out_trade_no")
    @SignField(name = "out_trade_no")
    private String outTradeNo;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "nonce_str")
    @SignField(name = "nonce_str")
    private String nonceStr;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "sign")
    private String sign;

    public AbstractBocPayReqData(String api)
    {
        this.api = api;
        this.nonceStr = IDGenerator.UUID.generate();
    }

    @Override
    public String getApi()
    {
        return api;
    }

    public void setApi(String api)
    {
        this.api = api;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getAppid()
    {
        return appid;
    }

    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    public String getMchId()
    {
        return mchId;
    }

    public void setMchId(String mchId)
    {
        this.mchId = mchId;
    }

    public String getTerminalId()
    {
        return terminalId;
    }

    public void setTerminalId(String terminalId)
    {
        this.terminalId = terminalId;
    }

    public String getDeviceInfo()
    {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo)
    {
        this.deviceInfo = deviceInfo;
    }

    public String getOutTradeNo()
    {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo)
    {
        this.outTradeNo = outTradeNo;
    }

    public String getNonceStr()
    {
        return nonceStr;
    }

    public String getSign()
    {
        return sign;
    }

    public AbstractBocPayReqData sign(String key)
    {
        this.sign = SignCtrl.INSTANCE.gen(this, key, BocPaySignCoder.INSTANCE);
        return this;
    }
}
