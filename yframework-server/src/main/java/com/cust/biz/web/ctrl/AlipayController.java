package com.cust.biz.web.ctrl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.becypress.alipay.config.AlipayProperties;
import com.becypress.toolkit.error.CustomParameterizedException;
import com.cust.biz.service.CustServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yframework.toolkit.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * Description: AlipayController.<br>
 * Date: 2017/11/17 10:33<br>
 * Author: ysj
 */
@Controller
@RequestMapping(value = "/alipay")
public class AlipayController
{
    private final Logger log = LoggerFactory.getLogger(AlipayController.class);
    private final CustServ serv;

    private AlipayProperties alipayProperties;

    public AlipayController(CustServ serv)
    {
        this.serv = serv;
        this.alipayProperties = serv.getAlipayServ().getAlipayProperties();
    }

    @RequestMapping(value = "/notify/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String notify(@PathVariable String id, HttpServletRequest request)
    {
        String rsp = "";
        try
        {
            //验签，并返回报文
            rsp = serv.getAlipayServ().checkSignature(request);
        }
        catch (AlipayApiException ex)
        {
            log.error(id + "-支付宝验签异常: " + ex.getMessage(), ex);
        }
        finally
        {
            //响应结果加签及返回
            try
            {
                //对响应内容加签
                rsp = serv.getAlipayServ().encryptAndSign(rsp, false, true);
                //http 内容应答
                log.info(id + "-响应支付宝: " + rsp);
            }
            catch (AlipayApiException ex)
            {
                log.error(id + "-支付宝加签异常: " + ex.getMessage(), ex);
            }
        }
        return rsp;
    }

    @RequestMapping(value = "/authorize/{id}", method = RequestMethod.GET)
    public String authorize(@PathVariable String id, @RequestParam String redirectUri)
    {
        try
        {
            String redirect = alipayProperties.getOrigin() + "/alipay/authorize/" + id + "/callback?redirectUri=" + URLEncoder.encode(redirectUri, alipayProperties.getCharset());
            String authUrl = serv.getAlipayServ().genAlipayOauth2AuthorizationUrl(id, null, redirect);
            return "redirect:" + authUrl;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return "redirect:/app/error.html?msg=" + e.getMessage();
        }
    }

    @RequestMapping(value = "/authorize/{id}/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(value = "auth_code", required = false) String authCode, @RequestParam String redirectUri)
    {
        try
        {
            if (StringUtil.isNotBlank(authCode))
            {
                AlipaySystemOauthTokenResponse resData = this.serv.getAlipayServ().apiAlipayAccessToken(authCode);
                String openid = resData.getUserId();
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
