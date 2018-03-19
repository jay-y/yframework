package com.becypress.boc.pay.service;

import com.becypress.boc.pay.config.BocPayProperties;
import com.becypress.boc.pay.domain.BocPayConfig;

/**
 * Description: BocPayServImpl.<br>
 * Date: 2018/1/4 15:55<br>
 * Author: ysj
 */
public class BocPayServImpl implements BocPayServ
{
    private final BocPayProperties bocPayProperties;

    private final BocPayConfig bocPayConfig;

    public BocPayServImpl(BocPayProperties bocPayProperties, BocPayConfig bocPayConfig)
    {
        this.bocPayProperties = bocPayProperties;
        this.bocPayConfig = bocPayConfig;
    }

    @Override
    public BocPayProperties getBocPayProperties()
    {
        return bocPayProperties;
    }

    @Override
    public BocPayConfig getBocPayConfigStorage()
    {
        return this.bocPayConfig;
    }
}
