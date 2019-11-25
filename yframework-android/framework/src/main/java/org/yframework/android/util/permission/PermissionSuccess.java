package org.yframework.android.util.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: PermissionSuccess<br>
 * Comments Name: PermissionSuccess<br>
 * Date: 2015-11-17 13:55<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-19 13:55<br>
 * Editor: ysj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionSuccess {
    int value() default PermissionUtil.PERMISSIONS_REQUEST_CODE;
}
