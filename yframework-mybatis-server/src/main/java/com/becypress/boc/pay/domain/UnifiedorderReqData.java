package com.becypress.boc.pay.domain;

import com.becypress.boc.pay.config.BocPayConstants;
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
public class UnifiedorderReqData extends AbstractBocPayReqData
{
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "wx_appid")
    @SignField(name = "wx_appid")
    private String wxAppid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "transaction_id")
    @SignField(name = "transaction_id")
    private String transactionId;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "pass_trade_no")
    @SignField(name = "pass_trade_no")
    private String passTradeNo;

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
    @JacksonXmlProperty(localName = "spbill_create_ip")
    @SignField(name = "spbill_create_ip")
    private String spbillCreateIp; //终端IP

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "time_start")
    @SignField(name = "time_start")
    private String timeStart; //交易起始时间,yyyyMMddHHmmss

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "time_expire")
    @SignField(name = "time_expire")
    private String timeExpire; //交易结束时间,yyyyMMddHHmmss

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "goods_tag")
    @SignField(name = "goods_tag")
    private String goodsTag; //商品标记

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "notify_url")
    @SignField(name = "notify_url")
    private String notifyUrl; //通知地址

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "trade_type")
    @SignField(name = "trade_type")
    private String tradeType; //交易类型

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "openid")
    @SignField(name = "openid")
    private String openid; //用户标识

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "product_id")
    @SignField(name = "product_id")
    private String productId; //商品标识

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "limit_pay")
    @SignField(name = "limit_pay")
    private String limitPay; //no_credit--指定不能使用信用卡支付

    public UnifiedorderReqData()
    {
        super(BocPayConstants._PAY_WX_UNIFIED_ORDER);
    }

    public String getWxAppid()
    {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid)
    {
        this.wxAppid = wxAppid;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    public String getPassTradeNo()
    {
        return passTradeNo;
    }

    public void setPassTradeNo(String passTradeNo)
    {
        this.passTradeNo = passTradeNo;
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

    public String getSpbillCreateIp()
    {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp)
    {
        this.spbillCreateIp = spbillCreateIp;
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

    public String getGoodsTag()
    {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag)
    {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl()
    {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl)
    {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType()
    {
        return tradeType;
    }

    public void setTradeType(String tradeType)
    {
        this.tradeType = tradeType;
    }

    public String getOpenid()
    {
        return openid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getLimitPay()
    {
        return limitPay;
    }

    public void setLimitPay(String limitPay)
    {
        this.limitPay = limitPay;
    }
}
