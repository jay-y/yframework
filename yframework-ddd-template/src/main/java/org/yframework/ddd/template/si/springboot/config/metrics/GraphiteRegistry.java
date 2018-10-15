package org.yframework.ddd.template.si.springboot.config.metrics;

//import com.codahale.metrics.MetricRegistry;
//import com.codahale.metrics.graphite.Graphite;
//import com.codahale.metrics.graphite.GraphiteReporter;
//import java.net.InetSocketAddress;
//import java.util.concurrent.TimeUnit;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Configuration;
//import org.yframework.system.config.ApplicationProperties;
//
//@Configuration
//@ConditionalOnClass({Graphite.class})
//public class GraphiteRegistry {
//    private final Logger log = LoggerFactory.getLogger(GraphiteRegistry.class);
//    private final ApplicationProperties properties;
//
//    public GraphiteRegistry(MetricRegistry metricRegistry, ApplicationProperties properties) {
//        this.properties = properties;
//        if(this.properties.getMetrics().getGraphite().isEnabled()) {
//            this.log.info("Initializing Metrics Graphite reporting");
//            String graphiteHost = properties.getMetrics().getGraphite().getHost();
//            Integer graphitePort = Integer.valueOf(properties.getMetrics().getGraphite().getPort());
//            String graphitePrefix = properties.getMetrics().getGraphite().getPrefix();
//            Graphite graphite = new Graphite(new InetSocketAddress(graphiteHost, graphitePort.intValue()));
//            GraphiteReporter graphiteReporter = GraphiteReporter.forRegistry(metricRegistry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).prefixedWith(graphitePrefix).build(graphite);
//            graphiteReporter.start(1L, TimeUnit.MINUTES);
//        }
//
//    }
//}
