package org.yframework.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricsDropwizardAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.yframework.system.config.ApplicationProperties;
import org.yframework.system.config.Constants;
import org.yframework.system.config.DefaultProfileUtil;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@ComponentScan
@EnableAutoConfiguration(exclude = {MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class, MetricsDropwizardAutoConfiguration.class})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class SystemApplication
{
    private static final Logger log = LoggerFactory.getLogger(SystemApplication.class);

    private final Environment env;

    public SystemApplication(Environment env)
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
    public void initApplication()
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
        SpringApplication app = new SpringApplication(SystemApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null)
        {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t" + "External: \t{}://{}:{}\n\t" + "Profile(s): \t{}\n----------------------------------------------------------", env.getProperty("spring.application.name"), protocol, env.getProperty("server.port"), protocol, InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"), env.getActiveProfiles());
//        String configServerStatus = env.getProperty("configserver.status");
//        log.info("\n----------------------------------------------------------\n\t" +
//                "Config Server: \t{}\n----------------------------------------------------------",
//            configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
    }
}
