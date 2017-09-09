package org.yframework.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.yframework.system.aop.logging.LoggingAspect;

//@Configuration
//@EnableAspectJAutoProxy
public class LoggingAspectConfiguration
{

    @Bean
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public LoggingAspect loggingAspect(Environment env)
    {
        return new LoggingAspect(env);
    }
}
