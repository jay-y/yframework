package org.yframework.ddd.core.application.hanlder;

/**
 * Description: 命令处理.<br>
 * Date: 2018/9/29 下午4:05<br>
 * Author: ysj
 */
public interface ICommandHandler<Cmd> extends IHandler
{
    void send(Cmd command);
}