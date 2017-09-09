package org.yframework.system.web.filter;

import org.yframework.system.config.ApplicationProperties;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CachingHttpHeadersFilter implements Filter
{
    private static final long LAST_MODIFIED = System.currentTimeMillis();
    private long cacheTimeToLive;
    private ApplicationProperties properties;

    public CachingHttpHeadersFilter(ApplicationProperties properties)
    {
        this.cacheTimeToLive = TimeUnit.DAYS.toMillis(1461L);
        this.properties = properties;
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.cacheTimeToLive = TimeUnit.DAYS.toMillis((long) this.properties.getHttp().getCache().getTimeToLiveInDays());
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", "max-age=" + this.cacheTimeToLive + ", public");
        httpResponse.setHeader("Pragma", "cache");
        httpResponse.setDateHeader("Expires", this.cacheTimeToLive + System.currentTimeMillis());
        httpResponse.setDateHeader("Last-Modified", LAST_MODIFIED);
        chain.doFilter(request, response);
    }
}