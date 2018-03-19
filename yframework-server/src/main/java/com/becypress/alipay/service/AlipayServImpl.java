package com.becypress.alipay.service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.becypress.alipay.config.AlipayProperties;
import com.becypress.alipay.domain.AlipayConfig;

/**
 * Description: AlipayServImpl.<br>
 * Date: 2018/1/4 16:13<br>
 * Author: ysj
 */
public class AlipayServImpl implements AlipayServ
{
    private final AlipayProperties alipayProperties;

    private final AlipayConfig alipayConfigStorage;

    private final AlipayClient alipayClient;

    public AlipayServImpl(AlipayProperties alipayProperties, AlipayConfig alipayConfigStorage)
    {
        this.alipayProperties = alipayProperties;
        this.alipayConfigStorage = alipayConfigStorage;
        this.alipayClient = new DefaultAlipayClient(alipayProperties.getGateway(), alipayConfigStorage.getApiKey(), alipayConfigStorage.getApiPrvKey(), alipayProperties.getFormat(), alipayProperties.getCharset(), alipayConfigStorage.getAlipayPubKey(), alipayProperties.getSignType());
    }

    @Override
    public AlipayProperties getAlipayProperties()
    {
        return alipayProperties;
    }

    @Override
    public AlipayConfig getAlipayConfigStorage()
    {
        return alipayConfigStorage;
    }

    @Override
    public AlipayClient getAlipayClient()
    {
        return alipayClient;
    }
}
