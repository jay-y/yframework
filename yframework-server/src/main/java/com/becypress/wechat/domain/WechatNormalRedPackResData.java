package com.becypress.wechat.domain;

import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: WechatNormalRedPackResData.<br>
 * Date: 2018/3/1 下午8:01<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class WechatNormalRedPackResData extends AbstractWechatPayResData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "mch_billno")
    @SignField(name = "mch_billno")
    private String mchBillno;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "mch_id")
    @SignField(name = "mch_id")
    private String mchId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "wxappid")
    @SignField(name = "wxappid")
    private String wxappid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "re_openid")
    @SignField(name = "re_openid")
    private String reOpenid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "total_amount")
    @SignField(name = "total_amount")
    private String totalAmount;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "send_listid")
    @SignField(name = "send_listid")
    private String sendListid;

    public String getMchBillno()
    {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno)
    {
        this.mchBillno = mchBillno;
    }

    public String getMchId()
    {
        return mchId;
    }

    public void setMchId(String mchId)
    {
        this.mchId = mchId;
    }

    public String getWxappid()
    {
        return wxappid;
    }

    public void setWxappid(String wxappid)
    {
        this.wxappid = wxappid;
    }

    public String getReOpenid()
    {
        return reOpenid;
    }

    public void setReOpenid(String reOpenid)
    {
        this.reOpenid = reOpenid;
    }

    public String getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getSendListid()
    {
        return sendListid;
    }

    public void setSendListid(String sendListid)
    {
        this.sendListid = sendListid;
    }
}
