package org.yframework.system.config.liquibase;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.StopWatch;

public class AsyncSpringLiquibase extends SpringLiquibase
{
    private final Logger logger = LoggerFactory.getLogger(AsyncSpringLiquibase.class);
    private final TaskExecutor taskExecutor;
    private final Environment env;

    public AsyncSpringLiquibase(@Qualifier("taskExecutor") TaskExecutor taskExecutor, Environment env)
    {
        this.taskExecutor = taskExecutor;
        this.env = env;
    }

    public void afterPropertiesSet() throws LiquibaseException
    {
        if (!this.env.acceptsProfiles(new String[]{"no-liquibase"}))
        {
            if (this.env.acceptsProfiles(new String[]{"dev", "heroku"}))
            {
                this.taskExecutor.execute(() ->
                {
                    try
                    {
                        this.logger.warn("Starting Liquibase asynchronously, your database might not be ready at startup!");
                        this.initDb();
                    }
                    catch (LiquibaseException var2)
                    {
                        this.logger.error("Liquibase could not start correctly, your database is NOT ready: {}", var2.getMessage(), var2);
                    }

                });
            }
            else
            {
                this.logger.debug("Starting Liquibase synchronously");
                this.initDb();
            }
        }
        else
        {
            this.logger.debug("Liquibase is disabled");
        }

    }

    protected void initDb() throws LiquibaseException
    {
        StopWatch watch = new StopWatch();
        watch.start();
        super.afterPropertiesSet();
        watch.stop();
        this.logger.debug("Liquibase has updated your database in {} ms", Long.valueOf(watch.getTotalTimeMillis()));
        if (watch.getTotalTimeMillis() > 5000L)
        {
            this.logger.warn("Warning, Liquibase took more than 5 seconds to start up!");
        }

    }
}
