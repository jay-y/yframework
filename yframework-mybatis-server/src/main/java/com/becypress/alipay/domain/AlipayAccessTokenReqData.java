package com.becypress.alipay.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.becypress.toolkit.model.PacketMessage;

import java.util.Map;

/**
 * Description: AccessTokenReqData.<br>
 * Date: 2017/11/17 17:43<br>
 * Author: ysj
 */
public class AlipayAccessTokenReqData implements AlipayRequest<AlipaySystemOauthTokenResponse>, PacketMessage
{
    private static final long serialVersionUID = 5853675893992212223L;
    private AlipayHashMap udfParams;
    private String apiVersion = "1.0";
    private String code;
    private String grantType;
    private String refreshToken;
    private String terminalType;
    private String terminalInfo;
    private String prodCode;
    private String notifyUrl;
    private String returnUrl;
    private boolean needEncrypt = false;
    private AlipayObject bizModel = null;

    public AlipayAccessTokenReqData()
    {
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return this.code;
    }

    public void setGrantType(String grantType)
    {
        this.grantType = grantType;
    }

    public String getGrantType()
    {
        return this.grantType;
    }

    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken()
    {
        return this.refreshToken;
    }

    public String getNotifyUrl()
    {
        return this.notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl)
    {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl()
    {
        return this.returnUrl;
    }

    public void setReturnUrl(String returnUrl)
    {
        this.returnUrl = returnUrl;
    }

    public String getApiVersion()
    {
        return this.apiVersion;
    }

    public void setApiVersion(String apiVersion)
    {
        this.apiVersion = apiVersion;
    }

    public void setTerminalType(String terminalType)
    {
        this.terminalType = terminalType;
    }

    public String getTerminalType()
    {
        return this.terminalType;
    }

    public void setTerminalInfo(String terminalInfo)
    {
        this.terminalInfo = terminalInfo;
    }

    public String getTerminalInfo()
    {
        return this.terminalInfo;
    }

    public void setProdCode(String prodCode)
    {
        this.prodCode = prodCode;
    }

    public String getProdCode()
    {
        return this.prodCode;
    }

    public String getApiMethodName()
    {
        return "alipay.system.oauth.token";
    }

    public Map<String, String> getTextParams()
    {
        AlipayHashMap txtParams = new AlipayHashMap();
        txtParams.put("code", this.code);
        txtParams.put("grant_type", this.grantType);
        txtParams.put("refresh_token", this.refreshToken);
        if (this.udfParams != null)
        {
            txtParams.putAll(this.udfParams);
        }

        return txtParams;
    }

    public void putOtherTextParam(String key, String value)
    {
        if (this.udfParams == null)
        {
            this.udfParams = new AlipayHashMap();
        }

        this.udfParams.put(key, value);
    }

    public Class<AlipaySystemOauthTokenResponse> getResponseClass()
    {
        return AlipaySystemOauthTokenResponse.class;
    }

    public boolean isNeedEncrypt()
    {
        return this.needEncrypt;
    }

    public void setNeedEncrypt(boolean needEncrypt)
    {
        this.needEncrypt = needEncrypt;
    }

    public AlipayObject getBizModel()
    {
        return this.bizModel;
    }

    public void setBizModel(AlipayObject bizModel)
    {
        this.bizModel = bizModel;
    }
}
