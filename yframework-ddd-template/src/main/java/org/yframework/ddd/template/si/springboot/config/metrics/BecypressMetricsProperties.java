package org.yframework.ddd.template.si.springboot.config.metrics;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "becypress-metrics", ignoreUnknownFields = false)
public class BecypressMetricsProperties
{
    private final Jmx jmx = new Jmx();
    private final Graphite graphite = new Graphite();
    private final Prometheus prometheus = new Prometheus();
    private final Logs logs = new Logs();

    public BecypressMetricsProperties()
    {
    }

    public Jmx getJmx()
    {
        return this.jmx;
    }

    public Graphite getGraphite()
    {
        return this.graphite;
    }

    public Prometheus getPrometheus()
    {
        return this.prometheus;
    }

    public Logs getLogs()
    {
        return this.logs;
    }

    public class Logs
    {
        private boolean enabled = false;
        private long reportFrequency = 60L;

        public Logs()
        {
        }

        public long getReportFrequency()
        {
            return this.reportFrequency;
        }

        public void setReportFrequency(int reportFrequency)
        {
            this.reportFrequency = (long) reportFrequency;
        }

        public boolean isEnabled()
        {
            return this.enabled;
        }

        public void setEnabled(boolean enabled)
        {
            this.enabled = enabled;
        }
    }

    public class Prometheus
    {
        private boolean enabled = false;
        private String endpoint = "/prometheusMetrics";

        public Prometheus()
        {
        }

        public boolean isEnabled()
        {
            return this.enabled;
        }

        public void setEnabled(boolean enabled)
        {
            this.enabled = enabled;
        }

        public String getEndpoint()
        {
            return this.endpoint;
        }

        public void setEndpoint(String endpoint)
        {
            this.endpoint = endpoint;
        }
    }

    public class Graphite
    {
        private boolean enabled = false;
        private String host = "localhost";
        private int port = 2003;
        private String prefix = "jhipsterApplication";

        public Graphite()
        {
        }

        public boolean isEnabled()
        {
            return this.enabled;
        }

        public void setEnabled(boolean enabled)
        {
            this.enabled = enabled;
        }

        public String getHost()
        {
            return this.host;
        }

        public void setHost(String host)
        {
            this.host = host;
        }

        public int getPort()
        {
            return this.port;
        }

        public void setPort(int port)
        {
            this.port = port;
        }

        public String getPrefix()
        {
            return this.prefix;
        }

        public void setPrefix(String prefix)
        {
            this.prefix = prefix;
        }
    }

    public class Jmx
    {
        private boolean enabled = true;

        public Jmx()
        {
        }

        public boolean isEnabled()
        {
            return this.enabled;
        }

        public void setEnabled(boolean enabled)
        {
            this.enabled = enabled;
        }
    }
}
