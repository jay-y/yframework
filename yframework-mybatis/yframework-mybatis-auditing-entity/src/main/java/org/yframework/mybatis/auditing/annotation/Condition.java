package org.yframework.mybatis.auditing.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: Condition.<br>
 * Date: 2017/11/28 10:51<br>
 * Author: ysj
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition
{
    boolean like() default true;
}
