package com.cust.system.web.vo;

import java.util.List;

/**
 * Description: AccountVO.<br>
 * Date: 2017/12/28 18:03<br>
 * Author: ysj
 */
public class AccountVO
{
    private List<String> authorities;

    public List<String> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(List<String> authorities)
    {
        this.authorities = authorities;
    }
}
