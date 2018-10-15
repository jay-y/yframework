package org.yframework.ddd.si.bus;

/**
 * Description: 消息总线.<br>
 * Date: 2018/9/29 下午3:03<br>
 * Author: ysj
 */
public interface MessageBus
{
    <Event> void publish(String commandId, Event event);
}
