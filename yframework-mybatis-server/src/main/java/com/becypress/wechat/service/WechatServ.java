package com.becypress.wechat.service;

import com.becypress.toolkit.Becypress;
import com.becypress.toolkit.model.PacketMessage;
import com.becypress.wechat.config.WechatConstants;
import com.becypress.wechat.config.WechatProperties;
import com.becypress.wechat.domain.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.StringUtil;

import java.util.Arrays;

/**
 * Description: WechatServ.<br>
 * Date: 2017/10/31 18:53<br>
 * Author: ysj
 */
public interface WechatServ
{
    Logger log = LoggerFactory.getLogger(WechatServ.class);

    WechatProperties getWechatProperties();

    WechatMpConfig getWechatConfigStorage();

    default String genWechatOauth2AuthorizationUrl(String appid, String scope, String redirectUri) throws Exception
    {
        if (StringUtil.isBlank(redirectUri))
        {
            throw new IllegalArgumentException("回调页面不能为空.");
        }
        scope = null != scope ? scope : "snsapi_base";
        return getWechatProperties().getOauth() + "?appid=" + appid + "&redirect_uri=" + redirectUri + "&response_type=code&scope=" + scope + "&state=STATE#wechat_redirect";
    }

    default <Req extends WechatPayReqData, Res extends WechatPayResData> Res apiSendNormalRedPack(Req reqData) throws Exception
    {
        String url = WechatConstants._API_MCH_MMPAYMKTTRANSFERS_SENDREDPACK;
        log.info("Request: {}", url);
        reqData.sign(getWechatConfigStorage().getMchKey());
        String result = Becypress.UTIL.http().doPostSSLWithXML(url, reqData.toXml());
        log.info("Response: {}", result);
        return new WechatNormalRedPackResData().fromXml(result);
    }

    default <Res extends PacketMessage> Res apiWechatAccessToken(String code) throws Exception
    {
        String url = String.format(WechatConstants._API_SNS_OAUTH2_ACCESS_TOKEN + "?appid=%s&secret=%s&code=%s&grant_type=%s", getWechatConfigStorage().getApiKey(), getWechatConfigStorage().getApiSecret(), code, WechatConstants._AUTHORIZATION_CODE);
        log.info("Request: {}", url);
        String result = Becypress.UTIL.extra().http().doGet(url);
        log.info("Response: {}", result);
        return new WechatAccessTokenResData().fromJson(result);
    }

    default boolean checkSignature(String token, String timestamp, String nonce, String signature)
    {
        try
        {
            return this.genSignature(new String[]{token, timestamp, nonce}).equals(signature);
        }
        catch (Exception var5)
        {
            log.error("Checking signature failed, and the reason is :" + var5.getMessage());
            return false;
        }
    }

    default String genSignature(String... arr)
    {
        if (StringUtils.isAnyEmpty(arr))
        {
            throw new IllegalArgumentException("非法请求参数，有部分参数为空 : " + Arrays.toString(arr));
        }
        else
        {
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            for (String a : arr)
            {
                sb.append(a);
            }
            return DigestUtils.sha1Hex(sb.toString());
        }
    }
}
