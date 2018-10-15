package org.yframework.ddd.template.si.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricsDropwizardAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.yframework.ddd.template.si.persistence.mybatis.config.DatatableProperties;
import org.yframework.ddd.template.si.springboot.config.ApplicationProperties;
import org.yframework.ddd.template.si.springboot.config.Constants;
import org.yframework.ddd.template.si.springboot.utils.SpringBootUtil;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Description: Bootstrap.<br>
 * Date: 2018/9/29 下午5:39<br>
 * Author: ysj
 */
@MapperScan({
        "org.yframework.ddd.template.core.application",
        "org.yframework.ddd.template.si.persistence"
})
@ComponentScan({
        "${application.biz.package-name}"
})
@EnableAutoConfiguration(exclude = {MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class, MetricsDropwizardAutoConfiguration.class})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class, DatatableProperties.class})
public class Bootstrap
{
    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    private final Environment env;

    public Bootstrap(Environment env)
    {
        this.env = env;
    }

    /**
     * Initializes server.
     * <p>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="http://jhipster.github.io/profiles/">http://jhipster.github.io/profiles/</a>.
     */
    @PostConstruct
    public void init()
    {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION))
        {
            log.error("You have misconfigured your application! It should not run " + "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_CLOUD))
        {
            log.error("You have misconfigured your application! It should not" + "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public static void main(String[] args) throws UnknownHostException
    {
        SpringBootUtil.INSTANCE.startup(Bootstrap.class, args);
    }
}
