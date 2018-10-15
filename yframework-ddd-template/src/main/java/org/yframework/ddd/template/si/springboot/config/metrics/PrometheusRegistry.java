package org.yframework.ddd.template.si.springboot.config.metrics;
//
//import com.codahale.metrics.MetricRegistry;
//import io.prometheus.client.CollectorRegistry;
//import io.prometheus.client.dropwizard.DropwizardExports;
//import io.prometheus.client.exporter.MetricsServlet;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Configuration;
//import org.yframework.system.config.ApplicationProperties;
//
//@Configuration
//@ConditionalOnClass({CollectorRegistry.class})
//public class PrometheusRegistry implements ServletContextInitializer {
//    private final Logger log = LoggerFactory.getLogger(PrometheusRegistry.class);
//    private final MetricRegistry metricRegistry;
//    private final ApplicationProperties properties;
//
//    public PrometheusRegistry(MetricRegistry metricRegistry, ApplicationProperties properties) {
//        this.metricRegistry = metricRegistry;
//        this.properties = properties;
//    }
//
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        if(this.properties.getMetrics().getPrometheus().isEnabled()) {
//            String endpoint = this.properties.getMetrics().getPrometheus().getEndpoint();
//            this.log.info("Initializing Metrics Prometheus endpoint at {}", endpoint);
//            CollectorRegistry collectorRegistry = new CollectorRegistry();
//            collectorRegistry.register(new DropwizardExports(this.metricRegistry));
//            servletContext.addServlet("prometheusMetrics", new MetricsServlet(collectorRegistry)).addMapping(new String[]{endpoint});
//        }
//
//    }
//}
