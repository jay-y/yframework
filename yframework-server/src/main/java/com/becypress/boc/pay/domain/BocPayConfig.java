package com.becypress.boc.pay.domain;

import com.becypress.conf.domain.Configuration;
import org.yframework.mybatis.auditing.domain.AbstractAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description: BocPayConfig.<br>
 * Date: 2017/9/15 16:43<br>
 * Author: ysj
 */
@Entity
@Table(name = "boc_pay_config")
public class BocPayConfig extends AbstractAuditingEntity<String> implements Configuration
{
    @Column(name = "name")
    private String name; //配置名称

    @Column(name = "api_key")
    private String apiKey; //中行分配的APPID

    @Column(name = "app_id")
    private String appId; //中行分配的APPID

    @Column(name = "mch_id")
    private String mchId; //中行分配的商户ID

    @Column(name = "key")
    private String key; // 签名使用的密钥

    public BocPayConfig()
    {
    }

    public BocPayConfig(String id)
    {
        super(id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getMchId()
    {
        return mchId;
    }

    public void setMchId(String mchId)
    {
        this.mchId = mchId;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "BocPayConfig{" + "name='" + name + '\'' + ", appId='" + appId + '\'' + ", mchId='" + mchId + '\'' + ", key='" + key + '\'' + "} " + super.toString();
    }
}
