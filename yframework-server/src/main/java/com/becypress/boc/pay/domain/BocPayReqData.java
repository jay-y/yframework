package com.becypress.boc.pay.domain;

import com.becypress.toolkit.model.PacketMessage;

/**
 * Description: BocPayReqData.<br>
 * Date: 2017/9/15 17:14<br>
 * Author: ysj
 */
public interface BocPayReqData extends PacketMessage
{
    String getApi();

    String getAppid();

    void setAppid(String appid);

    String getMchId();

    void setMchId(String mchId);

    BocPayReqData sign(String key);
}
