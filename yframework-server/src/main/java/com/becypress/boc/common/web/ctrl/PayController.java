package com.becypress.boc.common.web.ctrl;

import com.becypress.boc.common.config.PayConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: PayController.<br>
 * Date: 2017/11/6 11:07<br>
 * Author: ysj
 */
public interface PayController
{
    /**
     * 获取当前地址
     *
     * @param request
     * @return
     */
    default String getBasePath(HttpServletRequest request)
    {
        String contextPath = request.getContextPath();
        String port = 80 == request.getServerPort() ? "" : ":" + request.getServerPort();
        return request.getScheme() + "://" + request.getServerName() + port + contextPath;
    }

    /**
     * 获取终端代理标识
     *
     * @param request
     * @return
     */
    default String getAgent(HttpServletRequest request)
    {
        String key = null;
        String userAgent = request.getHeader("User-Agent");
        if (Optional.ofNullable(userAgent).isPresent())
        {
            for (int i = 0; i < PayConstants.RESG_USER_AGENT.length; i++)
            {
                String reg = PayConstants.RESG_USER_AGENT[i];
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(userAgent);
                if (matcher.matches())
                {
                    if (reg.equals(PayConstants.REG_USER_AGENT_ALIPAY))
                    {
                        key = PayConstants._ALIPAY;
                    }
                    else if (reg.equals(PayConstants.REG_USER_AGENT_WECHAT))
                    {
                        key = PayConstants._WECHAT;
                    }
                    else if (reg.equals(PayConstants.REG_USER_AGENT_UNION_PAY))
                    {
                        key = PayConstants._UNION_PAY;
                    }
                }
            }
        }
        return key;
    }
}