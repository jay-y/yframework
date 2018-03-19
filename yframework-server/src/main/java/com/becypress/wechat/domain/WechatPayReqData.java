package com.becypress.wechat.domain;

import com.becypress.toolkit.model.PacketMessage;

/**
 * Description: WechatPayReqData.<br>
 * Date: 2018/3/2 下午11:54<br>
 * Author: ysj
 */
public interface WechatPayReqData extends PacketMessage
{
    WechatPayReqData sign(String key);
}
