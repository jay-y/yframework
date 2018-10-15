package com.cust.system.web.vo;

/**
 * View Model object for storing the user's key and password.
 */
public class KeyAndPasswordVO
{

    private String key;

    private String newPassword;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }
}
