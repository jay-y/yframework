package org.yframework.android.util.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: PermissionError<br>
 * Comments Name: PermissionError<br>
 * Date: 2015-11-18 13:55<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-19 13:55<br>
 * Editor: ysj
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionError {
    int value() default PermissionUtil.PERMISSIONS_REQUEST_CODE;
}
