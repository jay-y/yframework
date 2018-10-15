package com.becypress.boc.pay.service;

import com.becypress.boc.pay.BocPayLoader;
import com.becypress.boc.pay.config.BocPayProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: BocPayServs.<br>
 * Date: 2018/1/4 15:55<br>
 * Author: ysj
 */
@Service
public class BocPayServs
{
    private final BocPayProperties bocPayProperties;

    private final Map<String, BocPayServ> servMap = new ConcurrentHashMap<>();

    public BocPayServs(BocPayProperties bocPayProperties)
    {
        this.bocPayProperties = bocPayProperties;
    }

    public Map<String, BocPayServ> getServMap()
    {
        return servMap;
    }

    public BocPayServ getServ(String id)
    {
        return getServMap().computeIfAbsent(id, k -> new BocPayServImpl(bocPayProperties, BocPayLoader.INSTANCE.getConfStorage(id)));
    }
}
