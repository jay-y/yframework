package com.becypress.wechat.domain;

import com.becypress.sign.annotation.SignField;
import com.becypress.sign.utils.SignCtrl;
import com.becypress.wechat.utils.WechatSignCoder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.yframework.mybatis.auditing.generator.IDGenerator;

/**
 * Description: AbstractWechatPayReqData.<br>
 * Date: 2018/3/1 下午7:37<br>
 * Author: ysj
 */
public abstract class AbstractWechatPayReqData implements WechatPayReqData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "sign")
    private String sign;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "nonce_str")
    @SignField(name = "nonce_str")
    private String nonceStr;

    public AbstractWechatPayReqData()
    {
        this.nonceStr = IDGenerator.UUID.generate();
    }

    public String getNonceStr()
    {
        return nonceStr;
    }

    public String getSign()
    {
        return sign;
    }

    public AbstractWechatPayReqData sign(String key)
    {
        this.sign = SignCtrl.INSTANCE.gen(this, key, WechatSignCoder.INSTANCE);
        return this;
    }
}
