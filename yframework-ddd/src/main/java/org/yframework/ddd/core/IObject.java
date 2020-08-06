package org.yframework.ddd.core;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public interface IObject extends Serializable
{
    default <T extends IObject> T fromMap(Map<String, Object> map)
    {
        ObjectUtil.INSTANCE.populate(this, map);
        return (T) this;
    }

    default Map<String, Object> toMap()
    {
        return Optional.ofNullable(ObjectUtil.INSTANCE.toMap(this)).orElse(new LinkedHashMap<>());
    }
}
