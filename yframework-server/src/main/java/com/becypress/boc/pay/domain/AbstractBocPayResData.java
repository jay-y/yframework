package com.becypress.boc.pay.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Description: AbstractBocPayResData.<br>
 * Date: 2017/9/15 17:32<br>
 * Author: ysj
 */
public abstract class AbstractBocPayResData implements BocPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "version")
    private String version;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "return_code")
    private String returnCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "return_msg")
    private String returnMsg;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "appid")
    private String appid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "mch_id")
    private String mchId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "device_info")
    private String deviceInfo;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "nonce_str")
    private String nonceStr;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "sign")
    private String sign;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "result_code")
    private String resultCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "err_code")
    private String errCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "err_code_des")
    private String errCodeDes;

    public String getVersion()
    {
        return version;
    }

    public String getReturnCode()
    {
        return returnCode;
    }

    public String getReturnMsg()
    {
        return returnMsg;
    }

    public String getAppid()
    {
        return appid;
    }

    public String getMchId()
    {
        return mchId;
    }

    public String getDeviceInfo()
    {
        return deviceInfo;
    }

    public String getNonceStr()
    {
        return nonceStr;
    }

    public String getSign()
    {
        return sign;
    }

    public String getResultCode()
    {
        return resultCode;
    }

    public String getErrCode()
    {
        return errCode;
    }

    public String getErrCodeDes()
    {
        return errCodeDes;
    }
}
