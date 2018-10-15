package com.becypress.boc.pay.domain;

import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: BocPayReturnData.<br>
 * Date: 2017/9/15 17:13<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class BocPayReturnData implements BocPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "version")
    @SignField(name = "version")
    private String version;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "return_code")
    @SignField(name = "return_code")
    private String returnCode;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "return_msg")
    @SignField(name = "return_msg")
    private String returnMsg;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "code")
    @SignField(name = "code")
    private String code;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "msg")
    @SignField(name = "msg")
    private String msg;

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
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
}
