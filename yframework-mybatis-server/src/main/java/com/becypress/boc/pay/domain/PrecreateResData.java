package com.becypress.boc.pay.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: PrecreateResData.<br>
 * Date: 2017/10/31 18:06<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class PrecreateResData extends AbstractBocPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "code")
    private String code;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "msg")
    private String msg;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "qr_code")
    private String qrCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "nonce_str")
    private String nonceStr;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "sub_code")
    private String subCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "sub_msg")
    private String subMsg;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getQrCode()
    {
        return qrCode;
    }

    public void setQrCode(String qrCode)
    {
        this.qrCode = qrCode;
    }

    public String getSubCode()
    {
        return subCode;
    }

    public void setSubCode(String subCode)
    {
        this.subCode = subCode;
    }

    public String getSubMsg()
    {
        return subMsg;
    }

    public void setSubMsg(String subMsg)
    {
        this.subMsg = subMsg;
    }
}
