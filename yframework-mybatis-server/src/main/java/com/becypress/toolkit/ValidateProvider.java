package com.becypress.toolkit;

/**
 * ValidateProvider<BR>
 * ------------------------------------------<BR>
 * <BR>
 * Copyright©  : 2017 by Flying_L<BR>
 * Author      : Flying_L <BR>
 * Date        : 2017年10月27日<BR>
 * Description :<BR>
 * <p>
 * 1、
 * </p>
 */
@FunctionalInterface
public interface ValidateProvider<I, P>
{
    boolean validate(I input, P param);
}
