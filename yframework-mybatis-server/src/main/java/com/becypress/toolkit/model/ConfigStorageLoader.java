package com.becypress.toolkit.model;

import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Map;

/**
 * Description: ConfigStorageLoader.<br>
 * Date: 2017/10/31 19:09<br>
 * Author: ysj
 */
public interface ConfigStorageLoader<Config, Annotation>
{
    Config getConfigStorage(String name);

    Config putConfigStorage(String key, Config config);

    void removeConfigStorage(String name);

    void load(Map<String, Config> configMap, ApplicationContext context) throws IOException;

    Config load(Map<String, Config> configMap, Annotation mapping);

    void unload(ApplicationContext context);

    void unload(Annotation mapping);
}
