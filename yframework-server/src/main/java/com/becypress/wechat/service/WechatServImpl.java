package com.becypress.wechat.service;

import com.becypress.wechat.config.WechatProperties;
import com.becypress.wechat.domain.WechatMpConfig;

/**
 * Description: WechatServImpl.<br>
 * Date: 2018/1/4 17:24<br>
 * Author: ysj
 */
public class WechatServImpl implements WechatServ
{
    private final WechatProperties wechatProperties;

    private final WechatMpConfig wechatConfigStorage;

    public WechatServImpl(WechatProperties wechatProperties, WechatMpConfig wechatConfigStorage)
    {
        this.wechatProperties = wechatProperties;
        this.wechatConfigStorage = wechatConfigStorage;
    }

    @Override
    public WechatProperties getWechatProperties()
    {
        return wechatProperties;
    }

    @Override
    public WechatMpConfig getWechatConfigStorage()
    {
        return wechatConfigStorage;
    }
}
