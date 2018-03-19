package com.becypress.wechat.service;

import com.becypress.wechat.WechatLoader;
import com.becypress.wechat.config.WechatProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: WechatServs.<br>
 * Date: 2018/1/4 17:24<br>
 * Author: ysj
 */
@Service
public class WechatServs
{
    private final WechatProperties wechatProperties;

    private final Map<String, WechatServ> servMap = new ConcurrentHashMap<>();

    public WechatServs(WechatProperties wechatProperties)
    {
        this.wechatProperties = wechatProperties;
    }

    public Map<String, WechatServ> getServMap()
    {
        return servMap;
    }

    public WechatServ getServ(String id)
    {
        return getServMap().computeIfAbsent(id, k -> new WechatServImpl(wechatProperties, WechatLoader.INSTANCE.getConfStorage(id)));
    }
}
