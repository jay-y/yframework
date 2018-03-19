package com.becypress.wechat.domain;

import com.becypress.toolkit.model.PacketMessage;

/**
 * Description: WechatPayResData.<br>
 * Date: 2018/3/2 下午11:55<br>
 * Author: ysj
 */
public interface WechatPayResData extends PacketMessage
{
    WechatPayResData sign(String key);
}
