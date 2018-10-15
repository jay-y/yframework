package org.yframework.ddd.template.si.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

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
    private final Biz biz = new Biz();
    private final Ribbon ribbon = new Ribbon();
    private final Http http = new Http();
    private final Mail mail = new Mail();
    private final CorsConfiguration cors = new CorsConfiguration();

    public Biz getBiz()
    {
        return biz;
    }

    public Ribbon getRibbon()
    {
        return ribbon;
    }

    public Http getHttp()
    {
        return this.http;
    }

    public Mail getMail()
    {
        return this.mail;
    }

    public CorsConfiguration getCors()
    {
        return this.cors;
    }

    public static class Biz
    {
        private String packageName;

        public String getPackageName()
        {
            return packageName;
        }

        public void setPackageName(String packageName)
        {
            this.packageName = packageName;
        }
    }

    public static class Ribbon
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

    public static class Http
    {
        private final Cache cache = new Cache();
        public Version version;

        public Http()
        {
            this.version = Version.V_1_1;
        }

        public Cache getCache()
        {
            return this.cache;
        }

        public Version getVersion()
        {
            return this.version;
        }

        public void setVersion(Version version)
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

    public static class Mail
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
}
