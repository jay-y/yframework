package org.yframework.mybatis.auditing.generator;

import java.io.Serializable;

/**
 * Description: Generator.<br>
 * Date: 2017/9/7 19:51<br>
 * Author: ysj
 */
public interface Generator<ID extends Serializable>
{
    String name();

    ID generate();
}
