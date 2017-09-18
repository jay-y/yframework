package org.yframework.toolkit;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: HttpUtil(HTTP远程访问).<br>
 * Date: 2015/01/30 14:07:55<br>
 * Author: ysj
 */
public enum HttpUtil
{
    INSTANCE;
    public final static String _UTF_8 = "UTF-8";
    public final static String _CONTENT_TYPE_JSON = "application/json";
    public final static String _CONTENT_TYPE_XML = "application/xml";
    private final static int _TIMEOUT_SOCKET = 10000;
    private final static int _TIMEOUT_CONNECT = 10000;
    private final static int _TIMEOUT_CONNECTION_REQUEST = 10000;
    private final static int _MAX_TOTAL = 50; //整个连接池最大连接数
    private final static int _DEFAULT_MAX_PER_ROUTE = 25; //路由的默认最大连接

    private final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    public final RequestConfig requestConfig;
    public final PoolingHttpClientConnectionManager connMgr;
    private CloseableHttpClient httpClient;
    private CloseableHttpClient httpClientSSL;

    HttpUtil()
    {
        requestConfig = RequestConfig.custom().
            setSocketTimeout(_TIMEOUT_SOCKET).
            setConnectTimeout(_TIMEOUT_CONNECT).
            setConnectionRequestTimeout(_TIMEOUT_CONNECTION_REQUEST).
            build();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().
            register("https", this.createSSLConnSocketFactory()).
            register("http", this.createConnSocketFactory()).
            build();
        connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connMgr.setMaxTotal(_MAX_TOTAL);
        connMgr.setDefaultMaxPerRoute(_DEFAULT_MAX_PER_ROUTE);
    }

    public CloseableHttpClient getHttpClient()
    {
        if (null == httpClient)
        {
            synchronized (INSTANCE)
            {
                this.httpClient = HttpClients.custom().
                    setConnectionManager(connMgr).
                    setDefaultRequestConfig(requestConfig).
                    build();
            }
        }
        return this.httpClient;
    }

    public CloseableHttpClient getHttpClientSSL() throws Exception
    {
        if (null == httpClientSSL)
        {
            synchronized (INSTANCE)
            {
                this.httpClientSSL = HttpClients.custom().
                    setConnectionManager(connMgr).
                    setDefaultRequestConfig(requestConfig).
                    build();
            }
        }
        return this.httpClientSSL;
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public String doGet(String url) throws IOException
    {
        return doGet(url, new HashMap<String, Object>());
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public String doGet(String url, Map<String, Object> params) throws IOException
    {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet())
        {
            if (i == 0)
            {
                param.append("?");
            }
            else
            {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpGet httpPost = new HttpGet(apiUrl);
        HttpResponse response = getHttpClient().execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null)
        {
            InputStream instream = entity.getContent();
            result = IOUtils.toString(instream, _UTF_8);
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl
     * @return
     */
    public String doPost(String apiUrl)
    {
        return doPost(apiUrl, new HashMap<>());
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public String doPost(String apiUrl, Map<String, Object> params)
    {
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        try
        {
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet())
            {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(_UTF_8)));
            response = getHttpClient().execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, _UTF_8);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    EntityUtils.consume(response.getEntity());
                }
                catch (IOException e)
                {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public String doPost(String apiUrl, Object json) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return doRequestWithJSON(httpPost, json.toString(), _UTF_8);
    }

    public String doPostWhithXML(String apiUrl, String xmlinfo) throws Exception
    {
        return doPostWhithXML(apiUrl, xmlinfo, _UTF_8);
    }

    public String doPostWhithXML(String apiUrl, String xmlinfo, String encoding) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return doRequestWithXML(httpPost, xmlinfo, encoding);
    }

    /**
     * 发送 PUT 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public String doPut(String apiUrl, Object json) throws Exception
    {
        HttpPut httpPut = new HttpPut(apiUrl);
        return doRequestWithJSON(httpPut, json.toString(), _UTF_8);
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public String doPostSSL(String apiUrl, Map<String, Object> params) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet())
        {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
            pairList.add(pair);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(_UTF_8)));
        CloseableHttpResponse response = getHttpClientSSL().execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK)
        {
            return null;
        }
        HttpEntity entity = response.getEntity();
        if (entity == null)
        {
            return null;
        }
        String httpStr = EntityUtils.toString(entity, _UTF_8);
        try
        {
            EntityUtils.consume(response.getEntity());
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     *
     * @param apiUrl API接口URL
     * @param json   JSON对象
     * @return
     */
    public String doPostSSL(String apiUrl, Object json) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return doRequestSSLWithJSON(httpPost, json.toString(), _UTF_8);
    }

    public String doPostSSLWhithXML(String apiUrl, String xmlinfo) throws Exception
    {
        return doPostSSLWhithXML(apiUrl, xmlinfo, _UTF_8);
    }

    public String doPostSSLWhithXML(String apiUrl, String xmlinfo, String encoding) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return doRequestSSLWithXML(httpPost, xmlinfo, encoding);
    }

    private String doRequestWithJSON(HttpEntityEnclosingRequestBase request, String json, String encoding) throws Exception
    {
        return this.doRequest(request, json, encoding, _CONTENT_TYPE_JSON);
    }

    private String doRequestWithXML(HttpEntityEnclosingRequestBase request, String message, String encoding) throws Exception
    {
        return this.doRequest(request, message, encoding, _CONTENT_TYPE_XML);
    }

    private String doRequestSSLWithJSON(HttpEntityEnclosingRequestBase request, String json, String encoding) throws Exception
    {
        return this.doRequestSSL(request, json, encoding, _CONTENT_TYPE_JSON);
    }

    private String doRequestSSLWithXML(HttpEntityEnclosingRequestBase request, String message, String encoding) throws Exception
    {
        return this.doRequestSSL(request, message, encoding, _CONTENT_TYPE_XML);
    }

    private String doRequest(HttpEntityEnclosingRequestBase request, String message, String encoding, String contentType) throws Exception
    {
        StringEntity stringEntity = new StringEntity(message, encoding);
        stringEntity.setContentEncoding(encoding);
        stringEntity.setContentType(contentType);
        request.setEntity(stringEntity);
        CloseableHttpResponse response = getHttpClient().execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, encoding);
        try
        {
            EntityUtils.consume(response.getEntity());
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    private String doRequestSSL(HttpEntityEnclosingRequestBase request, String message, String encoding, String contentType) throws Exception
    {
        StringEntity stringEntity = new StringEntity(message, encoding);
        stringEntity.setContentEncoding(encoding);
        stringEntity.setContentType(contentType);
        request.setEntity(stringEntity);
        CloseableHttpResponse response = getHttpClientSSL().execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, encoding);
        try
        {
            EntityUtils.consume(response.getEntity());
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    private ConnectionSocketFactory createConnSocketFactory()
    {
        return PlainConnectionSocketFactory.getSocketFactory();
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private ConnectionSocketFactory createSSLConnSocketFactory()
    {
//        SSLConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        SSLConnectionSocketFactory sslsf = null;
        try
        {
            //信任所有证书
            sslsf = new SSLConnectionSocketFactory(this.createIgnoreVerifySSL().getSocketFactory(), null, null, (x509Certificates, s) -> true);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return sslsf;
    }

    /**
     * 创建忽略验证的SSLContext
     *
     * @return
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public SSLContext createIgnoreVerifySSL() throws Exception
    {
        return new SSLContextBuilder().
            loadTrustMaterial(null, (x509Certificates, s) -> true).
            build();
    }
}
