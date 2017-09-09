package org.yframework.system.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = {MetricsConfiguration.class})
@AutoConfigureBefore(value = {WebConfigurer.class, DatabaseConfiguration.class})
public class CacheConfiguration
{
//    /**
//     * Used by Spring Security, to get events from Hazelcast.
//     *
//     * @return the session registry
//     */
//    @Bean
//    public SessionRegistry sessionRegistry()
//    {
//        return new SessionRegistryImpl();
//    }
}
