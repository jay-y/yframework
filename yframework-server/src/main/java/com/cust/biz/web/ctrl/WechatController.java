package com.cust.biz.web.ctrl;

import com.becypress.toolkit.error.CustomParameterizedException;
import com.becypress.wechat.domain.WechatAccessTokenResData;
import com.cust.biz.service.CustServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yframework.toolkit.StringUtil;

import java.net.URLEncoder;

/**
 * Description: WechatController.<br>
 * Date: 2017/11/17 10:33<br>
 * Author: ysj
 */
@Controller
@RequestMapping(value = "/wechat")
public class WechatController
{
    private final Logger log = LoggerFactory.getLogger(WechatController.class);
    private final CustServ serv;

    public WechatController(CustServ serv)
    {
        this.serv = serv;
    }

    @RequestMapping(value = "/notify/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String notify(@RequestParam(name = "signature", required = false) String signature, @RequestParam(name = "timestamp", required = false) String timestamp, @RequestParam(name = "nonce", required = false) String nonce, @RequestParam(name = "echostr", required = false) String echostr) throws Exception
    {
        log.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtil.isAnyBlank(signature, timestamp, nonce, echostr))
        {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }
        String token = serv.getWechatServ().getWechatConfigStorage().getToken();
        if (this.serv.getWechatServ().checkSignature(token, timestamp, nonce, signature))
        {
            return echostr;
        }
        return "非法请求";
    }

    @RequestMapping(value = "/authorize/{id}", method = RequestMethod.GET)
    public String authorize(@PathVariable String id, @RequestParam String redirectUri)
    {
        try
        {
            String redirect = serv.getWechatServ().getWechatProperties().getOrigin() + "/wechat/authorize/" + id + "/callback?redirectUri=" + URLEncoder.encode(redirectUri, serv.getWechatServ().getWechatProperties().getCharset());
            String authUrl = serv.getWechatServ().genWechatOauth2AuthorizationUrl(id, null, redirect);
            return "redirect:" + authUrl;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return "redirect:/app/error.html?msg=" + e.getMessage();
        }
    }

    @RequestMapping(value = "/authorize/{id}/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(value = "code", required = false) String authCode, @RequestParam String redirectUri)
    {
        try
        {
            if (StringUtil.isNotBlank(authCode))
            {
                WechatAccessTokenResData resData = this.serv.getWechatServ().apiWechatAccessToken(authCode);
                String openid = resData.getOpenid();
                if (redirectUri.contains("?"))
                {
                    redirectUri += "&openid=" + openid;
                }
                else
                {
                    redirectUri += "?openid=" + openid;
                }
            }
            else
            {
                throw new CustomParameterizedException("授权码不存在");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return "redirect:/app/error.html?msg=" + e.getMessage();
        }
        return "redirect:" + redirectUri;
    }
}
