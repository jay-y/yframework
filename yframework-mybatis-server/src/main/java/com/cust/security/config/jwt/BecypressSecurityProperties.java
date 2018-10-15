package com.cust.security.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * Description: BecypressSecurityProperties.<br>
 * Date: 2017/9/29 14:54<br>
 * Author: ysj
 */
@ConfigurationProperties(prefix = "becypress-security", ignoreUnknownFields = false)
public class BecypressSecurityProperties
{
    private final RememberMe rememberMe = new RememberMe();
    private final ClientAuthorization clientAuthorization = new ClientAuthorization();
    private final Authentication authentication = new Authentication();

    public BecypressSecurityProperties()
    {
    }

    public RememberMe getRememberMe()
    {
        return this.rememberMe;
    }

    public ClientAuthorization getClientAuthorization()
    {
        return this.clientAuthorization;
    }

    public Authentication getAuthentication()
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
        private final Oauth oauth = new Oauth();
        private final Jwt jwt = new Jwt();

        public Authentication()
        {
        }

        public Oauth getOauth()
        {
            return this.oauth;
        }

        public Jwt getJwt()
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
