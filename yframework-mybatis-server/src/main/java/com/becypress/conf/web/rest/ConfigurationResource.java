package com.becypress.conf.web.rest;

import com.becypress.conf.ConfigurationLoader;
import com.becypress.conf.config.ConfProperties;
import com.becypress.conf.domain.Configuration;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Description: ConfigurationResource.<br>
 * Date: 2018/1/3 11:44<br>
 * Author: ysj
 */
public interface ConfigurationResource<Conf extends Configuration>
{
    <Prop extends ConfProperties> Prop getProperties();

    <Loader extends ConfigurationLoader<Conf>> Loader getLoader();

    ResponseEntity<Conf> get(String id);

    ResponseEntity<Map<String, Conf>> query();

    ResponseEntity<String> load();

    ResponseEntity<String> load(String id, Conf conf);

    ResponseEntity<String> unload();

    ResponseEntity<String> unload(String id);
}
