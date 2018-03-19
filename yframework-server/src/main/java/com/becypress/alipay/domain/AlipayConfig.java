package com.becypress.alipay.domain;

import com.becypress.conf.domain.Configuration;
import org.yframework.mybatis.auditing.domain.AbstractAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description: AlipayConfig.<br>
 * Date: 2017/9/12 02:10<br>
 * Author: ysj
 */
@Entity
@Table(name = "alipay_config")
public class AlipayConfig extends AbstractAuditingEntity<String> implements Configuration
{
    @Column(name = "name")
    private String name;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_prv_key")
    private String apiPrvKey;

    @Column(name = "api_pub_key")
    private String apiPubKey;

    @Column(name = "alipay_pub_key")
    private String alipayPubKey;

    @Column(name = "token")
    private String token;

    @Column(name = "aes_key")
    private String aesKey;

    @Column(name = "description")
    private String description;

    @Column(name = "oauth2redirect_uri")
    private String oauth2redirectUri;

    @Column(name = "http_proxy_host")
    private String httpProxyHost;

    @Column(name = "http_proxy_port")
    private int httpProxyPort;

    @Column(name = "http_proxy_username")
    private String httpProxyUsername;

    @Column(name = "http_proxy_password")
    private String httpProxyPassword;

    @Column(name = "jsapi_ticket")
    private String jsapiTicket;

    @Column(name = "jsapi_ticket_expires_time")
    private long jsapiTicketExpiresTime;

    @Column(name = "card_api_ticket")
    private String cardApiTicket;

    @Column(name = "card_api_ticket_expires_time")
    private long cardApiTicketExpiresTime;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getApiPrvKey()
    {
        return apiPrvKey;
    }

    public void setApiPrvKey(String apiPrvKey)
    {
        this.apiPrvKey = apiPrvKey;
    }

    public String getApiPubKey()
    {
        return apiPubKey;
    }

    public void setApiPubKey(String apiPubKey)
    {
        this.apiPubKey = apiPubKey;
    }

    public String getAlipayPubKey()
    {
        return alipayPubKey;
    }

    public void setAlipayPubKey(String alipayPubKey)
    {
        this.alipayPubKey = alipayPubKey;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getAesKey()
    {
        return aesKey;
    }

    public void setAesKey(String aesKey)
    {
        this.aesKey = aesKey;
    }

    public String getOauth2redirectUri()
    {
        return oauth2redirectUri;
    }

    public void setOauth2redirectUri(String oauth2redirectUri)
    {
        this.oauth2redirectUri = oauth2redirectUri;
    }

    public String getHttpProxyHost()
    {
        return httpProxyHost;
    }

    public void setHttpProxyHost(String httpProxyHost)
    {
        this.httpProxyHost = httpProxyHost;
    }

    public int getHttpProxyPort()
    {
        return httpProxyPort;
    }

    public void setHttpProxyPort(int httpProxyPort)
    {
        this.httpProxyPort = httpProxyPort;
    }

    public String getHttpProxyUsername()
    {
        return httpProxyUsername;
    }

    public void setHttpProxyUsername(String httpProxyUsername)
    {
        this.httpProxyUsername = httpProxyUsername;
    }

    public String getHttpProxyPassword()
    {
        return httpProxyPassword;
    }

    public void setHttpProxyPassword(String httpProxyPassword)
    {
        this.httpProxyPassword = httpProxyPassword;
    }

    public String getJsapiTicket()
    {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket)
    {
        this.jsapiTicket = jsapiTicket;
    }

    public long getJsapiTicketExpiresTime()
    {
        return jsapiTicketExpiresTime;
    }

    public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime)
    {
        this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
    }

    public String getCardApiTicket()
    {
        return cardApiTicket;
    }

    public void setCardApiTicket(String cardApiTicket)
    {
        this.cardApiTicket = cardApiTicket;
    }

    public long getCardApiTicketExpiresTime()
    {
        return cardApiTicketExpiresTime;
    }

    public void setCardApiTicketExpiresTime(long cardApiTicketExpiresTime)
    {
        this.cardApiTicketExpiresTime = cardApiTicketExpiresTime;
    }
}
