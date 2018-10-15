package com.cust.common.config.metrics;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.jcache.JCacheGaugeSet;
import com.codahale.metrics.jvm.*;
import com.netflix.spectator.api.Registry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ExportMetricReader;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.metrics.spectator.SpectatorMetricReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;


@Configuration
@ConditionalOnClass(BecypressMetricsProperties.class)
@EnableMetrics(proxyTargetClass = true)
@EnableConfigurationProperties({BecypressMetricsProperties.class})
public class MetricsConfiguration extends MetricsConfigurerAdapter
{

    private static final String PROP_METRIC_REG_JVM_MEMORY = "jvm.memory";
    private static final String PROP_METRIC_REG_JVM_GARBAGE = "jvm.garbage";
    private static final String PROP_METRIC_REG_JVM_THREADS = "jvm.threads";
    private static final String PROP_METRIC_REG_JVM_FILES = "jvm.files";
    private static final String PROP_METRIC_REG_JVM_BUFFERS = "jvm.buffers";

    private static final String PROP_METRIC_REG_JCACHE_STATISTICS = "jcache.statistics";
    private final Logger log = LoggerFactory.getLogger(MetricsConfiguration.class);

    private final MetricRegistry metricRegistry = new MetricRegistry();

    private final HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

    private final BecypressMetricsProperties properties;

    private HikariDataSource hikariDataSource;

    public MetricsConfiguration(BecypressMetricsProperties properties)
    {
        this.properties = properties;
    }

    @Autowired(required = false)
    public void setHikariDataSource(HikariDataSource hikariDataSource)
    {
        this.hikariDataSource = hikariDataSource;
    }

    @Override
    @Bean
    public MetricRegistry getMetricRegistry()
    {
        return metricRegistry;
    }

    @Override
    @Bean
    public HealthCheckRegistry getHealthCheckRegistry()
    {
        return healthCheckRegistry;
    }

    @PostConstruct
    public void init()
    {
        log.debug("Registering JVM gauges");
        metricRegistry.register(PROP_METRIC_REG_JVM_MEMORY, new MemoryUsageGaugeSet());
        metricRegistry.register(PROP_METRIC_REG_JVM_GARBAGE, new GarbageCollectorMetricSet());
        metricRegistry.register(PROP_METRIC_REG_JVM_THREADS, new ThreadStatesGaugeSet());
        metricRegistry.register(PROP_METRIC_REG_JVM_FILES, new FileDescriptorRatioGauge());
        metricRegistry.register(PROP_METRIC_REG_JVM_BUFFERS, new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));

        metricRegistry.register(PROP_METRIC_REG_JCACHE_STATISTICS, new JCacheGaugeSet());
        if (hikariDataSource != null)
        {
            log.debug("Monitoring the datasource");
            hikariDataSource.setMetricRegistry(metricRegistry);
        }
        if (properties.getJmx().isEnabled())
        {
            log.debug("Initializing Metrics JMX reporting");
            JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
            jmxReporter.start();
        }
        if (properties.getLogs().isEnabled())
        {
            log.info("Initializing Metrics Log reporting");
            final Slf4jReporter reporter = Slf4jReporter.forRegistry(metricRegistry).outputTo(LoggerFactory.getLogger("metrics")).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
            reporter.start(properties.getLogs().getReportFrequency(), TimeUnit.SECONDS);
        }
    }

    /* Spectator metrics log reporting */
    @Bean
    @ConditionalOnProperty("application.logging.spectator-metrics.enabled")
    @ExportMetricReader
    public SpectatorMetricReader SpectatorMetricReader(Registry registry)
    {
        log.info("Initializing Spectator Metrics Log reporting");
        return new SpectatorMetricReader(registry);
    }

    @Bean
    @ConditionalOnProperty("application.logging.spectator-metrics.enabled")
    @ExportMetricWriter
    public MetricWriter metricWriter()
    {
        return new SpectatorLogMetricWriter();
    }
}
