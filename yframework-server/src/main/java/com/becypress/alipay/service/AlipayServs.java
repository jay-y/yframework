package com.becypress.alipay.service;

import com.becypress.alipay.AlipayLoader;
import com.becypress.alipay.config.AlipayProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: AlipayServs.<br>
 * Date: 2018/1/4 16:14<br>
 * Author: ysj
 */
@Service
public class AlipayServs
{
    private final AlipayProperties alipayProperties;

    private final Map<String, AlipayServ> servMap = new ConcurrentHashMap<>();

    public AlipayServs(AlipayProperties alipayProperties)
    {
        this.alipayProperties = alipayProperties;
    }

    public Map<String, AlipayServ> getServMap()
    {
        return servMap;
    }

    public AlipayServ getServ(String id)
    {
        return getServMap().computeIfAbsent(id, k -> new AlipayServImpl(alipayProperties, AlipayLoader.INSTANCE.getConfStorage(id)));
    }
}
