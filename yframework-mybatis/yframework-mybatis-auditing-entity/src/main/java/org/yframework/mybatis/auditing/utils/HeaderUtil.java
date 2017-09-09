package org.yframework.mybatis.auditing.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil
{
    public static final String X_APP_ALERT = "X-App-Alert";

    public static final String X_APP_PARAMS = "X-App-Params";

    public static final String X_APP_ERROR = "X-App-Error";

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "app";

    private HeaderUtil()
    {
    }

    public static HttpHeaders createAlert(String message, String param)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_APP_ALERT, message);
        headers.add(X_APP_PARAMS, param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param)
    {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param)
    {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param)
    {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage)
    {
        log.error("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_APP_ERROR, "error." + errorKey);
        headers.add(X_APP_PARAMS, entityName);
        return headers;
    }
}
