package com.cust.common.config.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: BecypressLoggingProperties.<br>
 * Date: 2017/9/20 15:14<br>
 * Author: ysj
 */
@ConfigurationProperties(prefix = "becypress-logging", ignoreUnknownFields = false)
public class BecypressLoggingProperties
{
    private final Logstash logstash = new Logstash();
    private final SpectatorMetrics spectatorMetrics = new SpectatorMetrics();

    public Logstash getLogstash()
    {
        return this.logstash;
    }

    public SpectatorMetrics getSpectatorMetrics()
    {
        return this.spectatorMetrics;
    }

    public class SpectatorMetrics
    {
        private boolean enabled = false;

        public SpectatorMetrics()
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

    public class Logstash
    {
        private boolean enabled = false;
        private String host = "localhost";
        private int port = 5000;
        private int queueSize = 512;

        public Logstash()
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

        public int getQueueSize()
        {
            return this.queueSize;
        }

        public void setQueueSize(int queueSize)
        {
            this.queueSize = queueSize;
        }
    }
}
