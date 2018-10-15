package com.becypress.wechat.domain;

import com.becypress.sign.annotation.SignField;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Description: WechatNormalRedPackReqData.<br>
 * Date: 2018/3/1 下午7:12<br>
 * Author: ysj
 */
@JacksonXmlRootElement(localName = "xml")
public class WechatNormalRedPackReqData extends AbstractWechatPayReqData
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
    @JacksonXmlProperty(localName = "send_name")
    @SignField(name = "send_name")
    private String sendName;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "re_openid")
    @SignField(name = "re_openid")
    private String reOpenid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "total_amount")
    @SignField(name = "total_amount")
    private Integer totalAmount;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "total_num")
    @SignField(name = "total_num")
    private Integer totalNum;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "wishing")
    @SignField(name = "wishing")
    private String wishing;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "client_ip")
    @SignField(name = "client_ip")
    private String clientIp;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "act_name")
    @SignField(name = "act_name")
    private String actName;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "remark")
    @SignField(name = "remark")
    private String remark;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "scene_id")
    @SignField(name = "scene_id")
    private String sceneId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "risk_info")
    @SignField(name = "risk_info")
    private String riskInfo;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "consume_mch_id")
    @SignField(name = "consume_mch_id")
    private String consumeMchId;

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

    public String getSendName()
    {
        return sendName;
    }

    public void setSendName(String sendName)
    {
        this.sendName = sendName;
    }

    public String getReOpenid()
    {
        return reOpenid;
    }

    public void setReOpenid(String reOpenid)
    {
        this.reOpenid = reOpenid;
    }

    public Integer getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalNum()
    {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum)
    {
        this.totalNum = totalNum;
    }

    public String getWishing()
    {
        return wishing;
    }

    public void setWishing(String wishing)
    {
        this.wishing = wishing;
    }

    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }

    public String getActName()
    {
        return actName;
    }

    public void setActName(String actName)
    {
        this.actName = actName;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getSceneId()
    {
        return sceneId;
    }

    public void setSceneId(String sceneId)
    {
        this.sceneId = sceneId;
    }

    public String getRiskInfo()
    {
        return riskInfo;
    }

    public void setRiskInfo(String riskInfo)
    {
        this.riskInfo = riskInfo;
    }

    public String getConsumeMchId()
    {
        return consumeMchId;
    }

    public void setConsumeMchId(String consumeMchId)
    {
        this.consumeMchId = consumeMchId;
    }
}
