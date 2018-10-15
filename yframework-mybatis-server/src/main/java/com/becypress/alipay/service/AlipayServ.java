package com.becypress.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.becypress.alipay.config.AlipayConstants;
import com.becypress.alipay.config.AlipayProperties;
import com.becypress.alipay.domain.AlipayAccessTokenReqData;
import com.becypress.alipay.domain.AlipayConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.StringUtil;
import org.yframework.toolkit.y;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: AlipayServ.<br>
 * Date: 2017/10/31 18:51<br>
 * Author: ysj
 */
public interface AlipayServ
{
    Object _LOCK = new Object();
    Logger log = LoggerFactory.getLogger(AlipayServ.class);
    Map<String, Object> alipayCache = new ConcurrentHashMap<>();

    AlipayProperties getAlipayProperties();

    AlipayConfig getAlipayConfigStorage();

    AlipayClient getAlipayClient();

    /**
     * 获取网页代授权token
     *
     * @param appid
     * @param code
     * @return
     * @throws Exception
     */
    default String getAlipayAccessToken(String appid, String code) throws Exception
    {
        String key = appid + AlipayConstants._KEY_ACCESS_TOKEN;
        String token = (String) getAlipayDataAfterAutoExpires(key);
        if (StringUtil.isBlank(token))
        {
            synchronized (_LOCK)
            {
                token = (String) alipayCache.get(key);
                if (StringUtil.isBlank(token))
                {
                    log.info("开始远程获取{}...", AlipayConstants._KEY_ACCESS_TOKEN);
                    AlipaySystemOauthTokenResponse resData = this.apiAlipayAccessToken(code);
                    token = resData.getAccessToken();
                    //提前半个小时过期
                    long expiresIn = (Long.valueOf(resData.getExpiresIn()) / 2) * 1000;
                    this.setAlipayDataAndAutoExpires(key, token, expiresIn);
                    log.info("远程获取{}成功: {}", AlipayConstants._KEY_ACCESS_TOKEN, token);
                }
                return token;
            }
        }
        return token;
    }

    /**
     * 设置数据及过期时间
     *
     * @param preKey
     * @param data
     * @param expiresIn
     * @return
     */
    default Object setAlipayDataAndAutoExpires(String preKey, Object data, long expiresIn)
    {
        alipayCache.put(preKey, data.toString());
        alipayCache.put(preKey + "-" + data.toString(), System.currentTimeMillis());
        alipayCache.put(preKey + "-" + data.toString() + "-expires_in", expiresIn);
        return data;
    }

    default Object getAlipayDataAfterAutoExpires(String preKey)
    {
        Object data = alipayCache.get(preKey);
        if (null != data)
        {
            //按过期时间自动过期
            Long oldTime = (Long) alipayCache.get(preKey + "-" + data.toString());
            Long expiresIn = (Long) alipayCache.get(preKey + "-" + data.toString() + "-expires_in");
            if (null != oldTime && null != expiresIn)
            {
                long remaining = System.currentTimeMillis() - oldTime;
                if (remaining >= expiresIn)
                {
                    alipayCache.remove(preKey);
                    alipayCache.remove(preKey + "-" + data.toString());
                    alipayCache.remove(preKey + "-" + data.toString() + "-expires_in");
                    data = null;
                }
            }

        }
        return data;
    }

    default void clearAlipayCache()
    {
        alipayCache.clear();
    }


    default String genAlipayOauth2AuthorizationUrl(String appid, String scope, String redirectUri) throws Exception
    {
        if (StringUtil.isBlank(redirectUri))
        {
            throw new IllegalArgumentException("回调页面不能为空.");
        }
        scope = null != scope ? scope : "auth_base";
        return getAlipayProperties().getOauth() + "?app_id=" + appid + "&scope=" + scope + "&redirect_uri=" + redirectUri;
    }

    default <Res extends AlipaySystemOauthTokenResponse> Res apiAlipayAccessToken(String code) throws Exception
    {
        AlipayAccessTokenReqData request = new AlipayAccessTokenReqData();
        request.setGrantType(AlipayConstants._AUTHORIZATION_CODE);
        request.setCode(code);
        AlipaySystemOauthTokenResponse response = getAlipayClient().execute(request);
        log.info("Response: {}", y.util().json().toPrettyJson(response));
        return (Res) response;
    }

    default String checkSignature(HttpServletRequest request) throws AlipayApiException
    {
        Map<String, String> params = new HashMap<>();
        if (null != request)
        {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey)
            {
                params.put(key, request.getParameter(key));
            }
        }
        if (!AlipaySignature.rsaCheckV2(params, this.getAlipayConfigStorage().getAlipayPubKey(), this.getAlipayProperties().getCharset(), this.getAlipayProperties().getSignType()))
        {
            throw new AlipayApiException("alipay verify sign fail.");
        }
        return "<success>" + Boolean.TRUE.toString() + "</success>" + "<biz_content>" + this.getAlipayConfigStorage().getApiPubKey() + "</biz_content>";
    }

    default String encryptAndSign(String bizContent, boolean isEncrypt, boolean isSign) throws AlipayApiException
    {
        StringBuilder sb = new StringBuilder();
        String charset = this.getAlipayProperties().getCharset();
        String alipayPublicKey = this.getAlipayConfigStorage().getAlipayPubKey();
        String cusPrivateKey = this.getAlipayConfigStorage().getApiKey();
        String signType = this.getAlipayProperties().getSignType();
        if (StringUtils.isEmpty(charset))
        {
            charset = com.alipay.api.AlipayConstants.CHARSET_GBK;
        }
        sb.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>");
        if (isEncrypt)
        {// 加密
            sb.append("<alipay>");
            String encrypted = AlipaySignature.rsaEncrypt(bizContent, alipayPublicKey, charset);
            sb.append("<response>").append(encrypted).append("</response>");
            sb.append("<encryption_type>AES</encryption_type>");
            if (isSign)
            {
                String sign = AlipaySignature.rsaSign(encrypted, cusPrivateKey, charset, signType);
                sb.append("<sign>").append(sign).append("</sign>");
                sb.append("<sign_type>");
                sb.append(signType);
                sb.append("</sign_type>");
            }
            sb.append("</alipay>");
        }
        else if (isSign)
        {// 不加密，但需要签名
            sb.append("<alipay>");
            sb.append("<response>").append(bizContent).append("</response>");
            String sign = AlipaySignature.rsaSign(bizContent, cusPrivateKey, charset, signType);
            sb.append("<sign>").append(sign).append("</sign>");
            sb.append("<sign_type>");
            sb.append(signType);
            sb.append("</sign_type>");
            sb.append("</alipay>");
        }
        else
        {// 不加密，不加签
            sb.append(bizContent);
        }
        return sb.toString();
    }
}
