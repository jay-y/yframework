package com.becypress.wechat.domain;

import com.becypress.sign.annotation.SignField;
import com.becypress.sign.utils.SignCtrl;
import com.becypress.wechat.utils.WechatSignCoder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Description: AbstractWechatPayResData.<br>
 * Date: 2018/3/1 下午7:55<br>
 * Author: ysj
 */
public abstract class AbstractWechatPayResData implements WechatPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "sign")
    private String sign;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "nonce_str")
    @SignField(name = "nonce_str")
    private String nonceStr;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "return_code")
    @SignField(name = "return_code")
    private String returnCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "return_msg")
    @SignField(name = "return_msg")
    private String returnMsg;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "result_code")
    @SignField(name = "result_code")
    private String resultCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "err_code")
    @SignField(name = "err_code")
    private String errCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "err_code_des")
    @SignField(name = "err_code_des")
    private String errCodeDes;

    public String getSign()
    {
        return sign;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    public String getNonceStr()
    {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr)
    {
        this.nonceStr = nonceStr;
    }

    public String getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(String returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getReturnMsg()
    {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg)
    {
        this.returnMsg = returnMsg;
    }

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getErrCode()
    {
        return errCode;
    }

    public void setErrCode(String errCode)
    {
        this.errCode = errCode;
    }

    public String getErrCodeDes()
    {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes)
    {
        this.errCodeDes = errCodeDes;
    }

    public AbstractWechatPayResData sign(String key)
    {
        this.sign = SignCtrl.INSTANCE.gen(this, key, WechatSignCoder.INSTANCE);
        return this;
    }
}
