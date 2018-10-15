package com.becypress.conf;

import com.becypress.conf.config.ConfProperties;
import com.becypress.conf.domain.Configuration;
import com.becypress.toolkit.error.CustomException;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Description: ConfigurationLoader.<br>
 * Date: 2018/1/2 12:01<br>
 * Author: ysj
 */
public interface ConfigurationLoader<Conf extends Configuration>
{
    Logger getLogger();

    String getConfName();

    List<Conf> genConfs(String json) throws IOException;

    Map<String, Conf> getConfStorage();

    void setConfStorage(Map<String, Conf> confStorage);

    default Conf getConfStorage(String id)
    {
        Conf confStorage = getConfStorage().get(id);
        if (confStorage == null)
        {
            throw new CustomException("Please initialize the " + this.getConfName());
        }
        return confStorage;
    }

    default Conf putConfStorage(String key, Conf confStorage)
    {
        getConfStorage().put(key, confStorage);
        return confStorage;
    }

    default void removeConfStorage(String key)
    {
        getConfStorage().remove(key);
    }

    default <Prop extends ConfProperties> void load(Prop properties)
    {
        try
        {
            this.load(this.genConfs(properties.readConf()));
        }
        catch (IOException e)
        {
            getLogger().error(e.getMessage(), e);
            getLogger().warn("Load configuration failed, path: {}", properties.getLocation());
        }
    }

    default void load(List<Conf> configs)
    {
        configs.forEach(v -> this.load(v.getId(), v));
    }

    default void load(String key, Conf conf)
    {
        this.putConfStorage(key, conf);
        getLogger().info("Load {} configuration: \n{}", this.getConfName(), conf);
    }

    default void unload()
    {
        this.getConfStorage().forEach((k, v) -> this.unload(k));
    }

    default void unload(String key)
    {
        this.removeConfStorage(key);
        getLogger().info("Unload {} configuration: {}", this.getConfName(), key);
    }
}
