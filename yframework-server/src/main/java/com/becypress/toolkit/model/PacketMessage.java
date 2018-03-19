package com.becypress.toolkit.model;

import com.becypress.toolkit.Becypress;

/**
 * Description: PacketMessage.<br>
 * Date: 2017/11/20 11:16<br>
 * Author: ysj
 */
public interface PacketMessage
{
    default String toXml()
    {
        return Becypress.UTIL.extra().xml().toXml(this);
    }

    default String toPrettyXml()
    {
        return Becypress.UTIL.extra().xml().toPrettyXml(this);
    }

    default String toJson()
    {
        return Becypress.UTIL.extra().json().toJson(this);
    }

    default String toPrettyJson()
    {
        return Becypress.UTIL.extra().json().toPrettyJson(this);
    }

    default <T extends PacketMessage> T fromXml(String xml)
    {
        return Becypress.UTIL.extra().xml().xmlToObject(xml, (Class<T>) this.getClass());
    }

    default <T extends PacketMessage> T fromJson(String xml)
    {
        return Becypress.UTIL.extra().json().jsonToObject(xml, (Class<T>) this.getClass());
    }
}
