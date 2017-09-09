package org.yframework.toolkit.fixed.annotation;

import org.yframework.toolkit.fixed.FixedStringAlignEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: FixedStringProperty.<br>
 * Date: 2017/8/24 16:45<br>
 * Author: ysj
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FixedStringProperty
{
    //起始索引
    int index() default 0;

    int length() default -1;

    String fill() default " ";

    FixedStringAlignEnum align() default FixedStringAlignEnum.A;

    boolean isCut() default true;
}
