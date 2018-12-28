package org.yframework.ddd.template.si.persistence.mybatis.model;

import org.springframework.data.annotation.Persistent;
import org.yframework.ddd.common.domain.event.IEvent;
import org.yframework.ddd.common.domain.support.IAggregateRoot;
import org.yframework.toolkit.y;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.yframework.ddd.template.si.persistence.mybatis.config.AggregateRootConstants.*;

/**
 * Description: 通用聚合根.<br>
 * Date: 2018/9/29 下午6:03<br>
 * Author: ysj
 */
public class CommonAggregateRoot extends ConcurrentHashMap implements Map, IAggregateRoot<String>
{
    @Persistent
    private String resourceId;

    @Persistent
    private String location;

    @Persistent
    private String charset;

    public CommonAggregateRoot()
    {
    }

    @Override
    public Object put(Object key, Object value)
    {
        key = y.util().string().toCamelCase(String.valueOf(key));
        return super.put(key, value);
    }

    public String getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(String resourceId)
    {
        this.resourceId = resourceId;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    @Override
    public String getId()
    {
        return (String) this.get(_KEY_ID);
    }

    @Override
    public void setId(String id)
    {
        this.put(_KEY_ID, id);
    }

    @Override
    public String getName()
    {
        return (String) this.get(_KEY_NAME);
    }

    public void setName(String name)
    {
        this.put(_KEY_NAME, name);
    }

    @Override
    public int getVersion()
    {
        return (int) this.get(_KEY_VERSION);
    }

    public void setVersion(int version)
    {
        this.put(_KEY_VERSION, version);
    }

    @Override
    public List<IEvent> getEvents()
    {
        return (List<IEvent>) this.get(_KEY_EVENTS);
    }

    public void setEvents(List<IEvent> events)
    {
        this.put(_KEY_EVENTS, events);
    }

    public String getCreatedBy()
    {
        return (String) this.get(_KEY_CREATED_BY);
    }

    public void setCreatedBy(String createdBy)
    {
        this.put(_KEY_CREATED_BY, createdBy);
    }

    public String getCreatedDate()
    {
        return (String) this.get(_KEY_CREATED_DATE);
    }

    public void setCreatedDate(String createdDate)
    {
        this.put(_KEY_CREATED_DATE, createdDate);
    }

    public String getLastModifiedBy()
    {
        return (String) this.get(_KEY_LAST_MODIFIED_BY);
    }

    public void setLastModifiedBy(String createdBy)
    {
        this.put(_KEY_CREATED_BY, createdBy);
    }

    public String getLastModifiedDate()
    {
        return (String) this.get(_KEY_LAST_MODIFIED_DATE);
    }

    public void setLastModifiedDate(String createdDate)
    {
        this.put(_KEY_LAST_MODIFIED_DATE, createdDate);
    }

    public String getActivated()
    {
        return (String) this.get(_KEY_ACTIVATED);
    }

    public void setActivated(String createdDate)
    {
        this.put(_KEY_ACTIVATED, createdDate);
    }

    protected String generateId()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
