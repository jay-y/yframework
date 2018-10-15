package com.becypress.conf.web.rest;

import com.becypress.conf.ConfigurationLoader;
import com.becypress.conf.config.ConfProperties;
import com.becypress.conf.domain.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Description: AbstractConfigurationResource.<br>
 * Date: 2018/1/3 11:53<br>
 * Author: ysj
 */
public abstract class AbstractConfigurationResource<Conf extends Configuration> implements ConfigurationResource<Conf>
{
    public abstract <Prop extends ConfProperties> Prop getProperties();

    public abstract <Loader extends ConfigurationLoader<Conf>> Loader getLoader();

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Conf> get(String id)
    {
        return ResponseEntity.ok(this.getLoader().getConfStorage(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<Map<String, Conf>> query()
    {
        return ResponseEntity.ok(this.getLoader().getConfStorage());
    }

    @Override
    @PostMapping
    public ResponseEntity<String> load()
    {
        this.getLoader().load((ConfProperties) this.getProperties());
        return ResponseEntity.ok(getLoader().getConfName() + "配置已全部加载");
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<String> load(@PathVariable String id, @RequestBody Conf conf)
    {
        this.getLoader().load(id, conf);
        return ResponseEntity.ok(getLoader().getConfName() + "配置已加载, ID: " + id);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> unload()
    {
        this.getLoader().unload();
        return ResponseEntity.ok(getLoader().getConfName() + "配置已全部卸载");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> unload(@PathVariable String id)
    {
        this.getLoader().unload(id);
        return ResponseEntity.ok(getLoader().getConfName() + "配置已卸载, ID: " + id);
    }
}
