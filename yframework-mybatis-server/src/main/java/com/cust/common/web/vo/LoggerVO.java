package com.cust.common.web.vo;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * View Model object for storing a Logback logger.
 */
public class LoggerVO
{

    private String name;

    private String level;

    public LoggerVO(Logger logger)
    {
        this.name = logger.getName();
        this.level = logger.getEffectiveLevel().toString();
    }

    @JsonCreator
    public LoggerVO()
    {
        // Empty public constructor used by Jackson.
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    @Override
    public String toString()
    {
        return "LoggerVM{" + "name='" + name + '\'' + ", level='" + level + '\'' + '}';
    }
}
