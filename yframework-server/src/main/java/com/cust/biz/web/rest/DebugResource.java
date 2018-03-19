package com.cust.biz.web.rest;

import com.becypress.alipay.AlipayLoader;
import com.becypress.alipay.config.AlipayProperties;
import com.becypress.toolkit.Becypress;
import com.becypress.wechat.WechatLoader;
import com.becypress.wechat.config.WechatProperties;
import com.cust.biz.web.vo.ResourceVO;
import com.cust.common.utils.CacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yframework.toolkit.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: DebugResource.<br>
 * Date: 2017/9/7 00:34<br>
 * Author: ysj
 */
@Api(description = "调试接口")
@RestController
@RequestMapping("/api/debug")
@EnableConfigurationProperties({WechatProperties.class, AlipayProperties.class})
public class DebugResource
{
    private static final String _CACHE_KEY_RESOURCES = "cache-resources";

    private final Logger log = LoggerFactory.getLogger(DebugResource.class);

    private final CacheManager cacheManager;

    private final WechatProperties wechatProperties;

    private final AlipayProperties alipayProperties;

    private final ApplicationContext context;

    private final CacheUtil cache;

    public DebugResource(CacheManager cacheManager, WechatProperties wechatProperties, AlipayProperties alipayProperties, ApplicationContext context, CacheUtil cache)
    {
        this.cacheManager = cacheManager;
        this.wechatProperties = wechatProperties;
        this.alipayProperties = alipayProperties;
        this.context = context;
        this.cache = cache;
    }

    @ApiOperation("获取资源信息")
    @GetMapping("/get/resources")
    public ResponseEntity<List<ResourceVO>> getResources()
    {
        Cache.ValueWrapper valueWrapper = cache.get(this.getClass()).get(_CACHE_KEY_RESOURCES);
        List<ResourceVO> resourceVOList = null != valueWrapper ? (List<ResourceVO>) valueWrapper.get() : new ArrayList<>();
        Map<String, Object> resources = context.getBeansWithAnnotation(RequestMapping.class);
        resources.forEach((k, v) ->
        {
            try
            {
                v = Becypress.UTIL.aop().getTarget(v);
                Api apiAnno = v.getClass().getAnnotation(Api.class);
                RequestMapping mappingAnno = v.getClass().getAnnotation(RequestMapping.class);
                if (null != mappingAnno)
                {
                    String description = null != apiAnno ? (StringUtil.isNotBlank(apiAnno.description()) ? apiAnno.description() : (StringUtil.isNotBlank(apiAnno.value()) ? apiAnno.value() : k)) : k;
                    String[] mappings = mappingAnno.value();
                    String mapping = mappings[0];
                    resourceVOList.add(new ResourceVO(mapping, description));
                }
            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
            }
        });
        return ResponseEntity.ok(resourceVOList);
    }

    @ApiOperation("清理缓存")
    @GetMapping("/clear/cache")
    public ResponseEntity<String> clearCache(@RequestParam(required = false) String key)
    {
        if (StringUtil.isBlank(key))
        {
            key = "cache-datas";
        }
        this.clearCache4Key(key);
        return ResponseEntity.ok("清理" + key + "缓存成功");
    }

    private void clearCache4Key(String key)
    {
        switch (key)
        {
            case "wechat":
                WechatLoader.INSTANCE.unload();
                WechatLoader.INSTANCE.load(wechatProperties);
                break;
            case "alipay":
                AlipayLoader.INSTANCE.unload();
                AlipayLoader.INSTANCE.load(alipayProperties);
                break;
        }
        cacheManager.getCache(key).clear();
    }
}
