package org.yframework.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Properties specific.
 * <p>
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties
{
    private final Rsa rsa = new Rsa();
    private final Async async = new Async();
    private final Http http = new Http();
    private final Cache cache = new Cache();
    private final Mail mail = new Mail();
    private final Security security = new Security();
    private final Metrics metrics = new Metrics();
    private final CorsConfiguration cors = new CorsConfiguration();
    private final Social social = new Social();
    private final Gateway gateway = new Gateway();
    private final Ribbon ribbon = new Ribbon();
    private final Registry registry = new Registry();
    private final Logging logging = new Logging();

    public Rsa getRsa()
    {
        return rsa;
    }

    public Async getAsync()
    {
        return this.async;
    }

    public Http getHttp()
    {
        return this.http;
    }

    public Cache getCache()
    {
        return this.cache;
    }

    public Mail getMail()
    {
        return this.mail;
    }

    public Registry getRegistry()
    {
        return this.registry;
    }

    public Security getSecurity()
    {
        return this.security;
    }

    public Metrics getMetrics()
    {
        return this.metrics;
    }

    public CorsConfiguration getCors()
    {
        return this.cors;
    }

    public Social getSocial()
    {
        return this.social;
    }

    public Gateway getGateway()
    {
        return this.gateway;
    }

    public Ribbon getRibbon()
    {
        return this.ribbon;
    }

    public Logging getLogging()
    {
        return this.logging;
    }

    public class Rsa
    {
        private String prvkeyPath;
        private String pubkeyPath;
        private String prvkeyCode;
        private String pubkeyCode;

        public String getPrvkeyPath()
        {
            return prvkeyPath;
        }

        public void setPrvkeyPath(String prvkeyPath)
        {
            this.prvkeyPath = prvkeyPath;
        }

        public String getPubkeyPath()
        {
            return pubkeyPath;
        }

        public void setPubkeyPath(String pubkeyPath)
        {
            this.pubkeyPath = pubkeyPath;
        }

        public String getPrvkeyCode()
        {
            return prvkeyCode;
        }

        public void setPrvkeyCode(String prvkeyCode)
        {
            this.prvkeyCode = prvkeyCode;
        }

        public String getPubkeyCode()
        {
            return pubkeyCode;
        }

        public void setPubkeyCode(String pubkeyCode)
        {
            this.pubkeyCode = pubkeyCode;
        }
    }

    private class Registry
    {
        private String password;

        private Registry()
        {
        }

        public String getPassword()
        {
            return this.password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }
    }

    public class Ribbon
    {
        private String[] displayOnActiveProfiles;

        public Ribbon()
        {
        }

        public String[] getDisplayOnActiveProfiles()
        {
            return this.displayOnActiveProfiles;
        }

        public void setDisplayOnActiveProfiles(String[] displayOnActiveProfiles)
        {
            this.displayOnActiveProfiles = displayOnActiveProfiles;
        }
    }

    public class Gateway
    {
        private final Gateway.RateLimiting rateLimiting = new Gateway.RateLimiting();
        private Map<String, List<String>> authorizedMicroservicesEndpoints = new LinkedHashMap();

        public Gateway()
        {
        }

        public Gateway.RateLimiting getRateLimiting()
        {
            return this.rateLimiting;
        }

        public Map<String, List<String>> getAuthorizedMicroservicesEndpoints()
        {
            return this.authorizedMicroservicesEndpoints;
        }

        public void setAuthorizedMicroservicesEndpoints(Map<String, List<String>> authorizedMicroservicesEndpoints)
        {
            this.authorizedMicroservicesEndpoints = authorizedMicroservicesEndpoints;
        }

        public class RateLimiting
        {
            private boolean enabled = false;
            private long limit = 100000L;
            private int durationInSeconds = 3600;

            public RateLimiting()
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

            public long getLimit()
            {
                return this.limit;
            }

            public void setLimit(long limit)
            {
                this.limit = limit;
            }

            public int getDurationInSeconds()
            {
                return this.durationInSeconds;
            }

            public void setDurationInSeconds(int durationInSeconds)
            {
                this.durationInSeconds = durationInSeconds;
            }
        }
    }

    public class Social
    {
        private String redirectAfterSignIn = "/#/home";

        public Social()
        {
        }

        public String getRedirectAfterSignIn()
        {
            return this.redirectAfterSignIn;
        }

        public void setRedirectAfterSignIn(String redirectAfterSignIn)
        {
            this.redirectAfterSignIn = redirectAfterSignIn;
        }
    }

    public class Logging
    {
        private final Logging.Logstash logstash = new Logging.Logstash();
        private final Logging.SpectatorMetrics spectatorMetrics = new Logging.SpectatorMetrics();

        public Logging()
        {
        }

        public Logging.Logstash getLogstash()
        {
            return this.logstash;
        }

        public Logging.SpectatorMetrics getSpectatorMetrics()
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

    public class Metrics
    {
        private final Metrics.Jmx jmx = new Metrics.Jmx();
        private final Metrics.Graphite graphite = new Metrics.Graphite();
        private final Metrics.Prometheus prometheus = new Metrics.Prometheus();
        private final Metrics.Logs logs = new Metrics.Logs();

        public Metrics()
        {
        }

        public Metrics.Jmx getJmx()
        {
            return this.jmx;
        }

        public Metrics.Graphite getGraphite()
        {
            return this.graphite;
        }

        public Metrics.Prometheus getPrometheus()
        {
            return this.prometheus;
        }

        public Metrics.Logs getLogs()
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

    public class Security
    {
        private final Security.RememberMe rememberMe = new Security.RememberMe();
        private final Security.ClientAuthorization clientAuthorization = new Security.ClientAuthorization();
        private final Security.Authentication authentication = new Security.Authentication();

        public Security()
        {
        }

        public Security.RememberMe getRememberMe()
        {
            return this.rememberMe;
        }

        public Security.ClientAuthorization getClientAuthorization()
        {
            return this.clientAuthorization;
        }

        public Security.Authentication getAuthentication()
        {
            return this.authentication;
        }

        public class RememberMe
        {
            @NotNull
            private String key;

            public RememberMe()
            {
            }

            public String getKey()
            {
                return this.key;
            }

            public void setKey(String key)
            {
                this.key = key;
            }
        }

        public class Authentication
        {
            private final Security.Authentication.Oauth oauth = new Security.Authentication.Oauth();
            private final Security.Authentication.Jwt jwt = new Security.Authentication.Jwt();

            public Authentication()
            {
            }

            public Security.Authentication.Oauth getOauth()
            {
                return this.oauth;
            }

            public Security.Authentication.Jwt getJwt()
            {
                return this.jwt;
            }

            public class Jwt
            {
                private String secret;
                private long tokenValidityInSeconds = 1800L;
                private long tokenValidityInSecondsForRememberMe = 2592000L;

                public Jwt()
                {
                }

                public String getSecret()
                {
                    return this.secret;
                }

                public void setSecret(String secret)
                {
                    this.secret = secret;
                }

                public long getTokenValidityInSeconds()
                {
                    return this.tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long tokenValidityInSeconds)
                {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

                public long getTokenValidityInSecondsForRememberMe()
                {
                    return this.tokenValidityInSecondsForRememberMe;
                }

                public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe)
                {
                    this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
                }
            }

            public class Oauth
            {
                private String clientId;
                private String clientSecret;
                private int tokenValidityInSeconds = 1800;

                public Oauth()
                {
                }

                public String getClientId()
                {
                    return this.clientId;
                }

                public void setClientId(String clientId)
                {
                    this.clientId = clientId;
                }

                public String getClientSecret()
                {
                    return this.clientSecret;
                }

                public void setClientSecret(String clientSecret)
                {
                    this.clientSecret = clientSecret;
                }

                public int getTokenValidityInSeconds()
                {
                    return this.tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(int tokenValidityInSeconds)
                {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }
            }
        }

        public class ClientAuthorization
        {
            private String accessTokenUri;
            private String tokenServiceId;
            private String clientId;
            private String clientSecret;

            public ClientAuthorization()
            {
            }

            public String getAccessTokenUri()
            {
                return this.accessTokenUri;
            }

            public void setAccessTokenUri(String accessTokenUri)
            {
                this.accessTokenUri = accessTokenUri;
            }

            public String getTokenServiceId()
            {
                return this.tokenServiceId;
            }

            public void setTokenServiceId(String tokenServiceId)
            {
                this.tokenServiceId = tokenServiceId;
            }

            public String getClientId()
            {
                return this.clientId;
            }

            public void setClientId(String clientId)
            {
                this.clientId = clientId;
            }

            public String getClientSecret()
            {
                return this.clientSecret;
            }

            public void setClientSecret(String clientSecret)
            {
                this.clientSecret = clientSecret;
            }
        }
    }

    public class Mail
    {
        private String from = "";
        private String baseUrl = "";

        public Mail()
        {
        }

        public String getFrom()
        {
            return this.from;
        }

        public void setFrom(String from)
        {
            this.from = from;
        }

        public String getBaseUrl()
        {
            return this.baseUrl;
        }

        public void setBaseUrl(String baseUrl)
        {
            this.baseUrl = baseUrl;
        }
    }

    public class Cache
    {
        private final Cache.Hazelcast hazelcast = new Cache.Hazelcast();
        private final Cache.Ehcache ehcache = new Cache.Ehcache();

        public Cache()
        {
        }

        public Cache.Hazelcast getHazelcast()
        {
            return this.hazelcast;
        }

        public Cache.Ehcache getEhcache()
        {
            return this.ehcache;
        }

        public class Ehcache
        {
            private int timeToLiveSeconds = 3600;
            private long maxEntries = 100L;

            public Ehcache()
            {
            }

            public int getTimeToLiveSeconds()
            {
                return this.timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds)
            {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public long getMaxEntries()
            {
                return this.maxEntries;
            }

            public void setMaxEntries(long maxEntries)
            {
                this.maxEntries = maxEntries;
            }
        }

        public class Hazelcast
        {
            private int timeToLiveSeconds = 3600;
            private int backupCount = 1;

            public Hazelcast()
            {
            }

            public int getTimeToLiveSeconds()
            {
                return this.timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds)
            {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public int getBackupCount()
            {
                return this.backupCount;
            }

            public void setBackupCount(int backupCount)
            {
                this.backupCount = backupCount;
            }
        }
    }

    public static class Http
    {
        private final Http.Cache cache = new Http.Cache();
        public Http.Version version;

        public Http()
        {
            this.version = Http.Version.V_1_1;
        }

        public Http.Cache getCache()
        {
            return this.cache;
        }

        public Http.Version getVersion()
        {
            return this.version;
        }

        public void setVersion(Http.Version version)
        {
            this.version = version;
        }

        public class Cache
        {
            private int timeToLiveInDays = 1461;

            public Cache()
            {
            }

            public int getTimeToLiveInDays()
            {
                return this.timeToLiveInDays;
            }

            public void setTimeToLiveInDays(int timeToLiveInDays)
            {
                this.timeToLiveInDays = timeToLiveInDays;
            }
        }

        public enum Version
        {
            V_1_1, V_2_0;

            Version()
            {
            }
        }
    }

    public class Async
    {
        private int corePoolSize = 2;
        private int maxPoolSize = 50;
        private int queueCapacity = 10000;

        public Async()
        {
        }

        public int getCorePoolSize()
        {
            return this.corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize)
        {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize()
        {
            return this.maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize)
        {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity()
        {
            return this.queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity)
        {
            this.queueCapacity = queueCapacity;
        }
    }
}
