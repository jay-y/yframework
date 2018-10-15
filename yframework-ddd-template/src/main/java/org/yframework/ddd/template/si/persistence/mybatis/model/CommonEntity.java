package org.yframework.ddd.template.si.persistence.mybatis.model;

import org.yframework.ddd.core.domain.model.IEntity;

import javax.persistence.Entity;
import java.util.concurrent.ConcurrentHashMap;

import static org.yframework.ddd.template.si.persistence.mybatis.config.AggregateRootConstants._KEY_ID;

/**
 * Description: 通用实体.<br>
 * Date: 2018/10/7 下午3:59<br>
 * Author: ysj
 */
@Entity
public class CommonEntity extends ConcurrentHashMap implements IEntity<String>
{
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
}
