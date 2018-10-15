package com.becypress.toolkit;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.StringUtil;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public enum HttpUtil
{
    INSTANCE;

    public static final String _UTF_8 = "UTF-8";
    public static final String _CONTENT_TYPE_JSON = "application/json";
    public static final String _CONTENT_TYPE_XML = "application/xml";
    public static final String _CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    private static final String _CERT_PATH = "/user/apiclient_cert.p12";
    private static final String _CERT_PASSWORD = "1253759901";
    private static final int _TIMEOUT_SOCKET = 10000;
    private static final int _TIMEOUT_CONNECT = 10000;
    private static final int _TIMEOUT_CONNECTION_REQUEST = 10000;
    private static final int _MAX_TOTAL = 50;
    private static final int _DEFAULT_MAX_PER_ROUTE = 25;
    private final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    public RequestConfig requestConfig = RequestConfig.custom().
            setSocketTimeout(_TIMEOUT_SOCKET).
            setConnectTimeout(_TIMEOUT_CONNECT).
            setConnectionRequestTimeout(_TIMEOUT_CONNECTION_REQUEST).
            build();
    public PoolingHttpClientConnectionManager connMgr;
    private CloseableHttpClient httpClient;
    private CloseableHttpClient httpClientSSL;
    private String certPath;
    private String certPassword;

    HttpUtil()
    {
    }

    public void init()
    {
        try
        {
            if (null == this.connMgr)
            {
                synchronized (INSTANCE)
                {
                    if (null == this.connMgr)
                    {
                        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().
                                register("http", this.createConnSocketFactory()).
                                register("https", this.createCustSSLConnSocketFactory()).
                                build();
                        this.connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                        this.connMgr.setMaxTotal(_MAX_TOTAL);
                        this.connMgr.setDefaultMaxPerRoute(_DEFAULT_MAX_PER_ROUTE);
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }

    public void reload()
    {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().
                register("http", this.createConnSocketFactory()).
                register("https", this.createCustSSLConnSocketFactory()).
                build();
        this.connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        this.connMgr.setMaxTotal(_MAX_TOTAL);
        this.connMgr.setDefaultMaxPerRoute(_DEFAULT_MAX_PER_ROUTE);
        this.httpClient = HttpClients.custom().
                setConnectionManager(this.connMgr).
                setDefaultRequestConfig(this.requestConfig).
                build();
        this.httpClientSSL = HttpClients.custom().
                setConnectionManager(this.connMgr).
                setDefaultRequestConfig(this.requestConfig).
                build();
    }

    public CloseableHttpClient getHttpClient()
    {
        if (null == this.httpClient)
        {
            synchronized (INSTANCE)
            {
                init();
                if (null == this.httpClient)
                {
                    this.httpClient = HttpClients.custom().
                            setConnectionManager(this.connMgr).
                            setDefaultRequestConfig(this.requestConfig).
                            build();
                }
            }
        }

        return this.httpClient;
    }

    public CloseableHttpClient getHttpClientSSL() throws Exception
    {
        if (null == this.httpClientSSL)
        {
            synchronized (INSTANCE)
            {
                if (null == this.httpClientSSL)
                {
                    init();
                    this.httpClientSSL = HttpClients.custom().
                            setConnectionManager(this.connMgr).
                            setDefaultRequestConfig(this.requestConfig).
                            build();
                }
            }
        }

        return this.httpClientSSL;
    }

    public String getCertPath()
    {
        return StringUtil.isBlank(certPath) ? _CERT_PATH : certPath;
    }

    public void setCertPath(String certPath)
    {
        this.certPath = certPath;
    }

    public String getCertPassword()
    {
        return StringUtil.isBlank(certPassword) ? _CERT_PASSWORD : certPassword;
    }

    public void setCertPassword(String certPassword)
    {
        this.certPassword = certPassword;
    }

    public String doGet(String url) throws IOException
    {
        return this.doGet(url, new HashMap());
    }

    public String doGet(String url, Map<String, Object> params) throws IOException
    {
        StringBuffer param = new StringBuffer();
        int i = 0;

        for (Iterator result = params.keySet().iterator(); result.hasNext(); ++i)
        {
            String httpPost = (String) result.next();
            if (i == 0)
            {
                param.append("?");
            }
            else
            {
                param.append("&");
            }

            param.append(httpPost).append("=").append(params.get(httpPost));
        }

        String apiUrl = url + param;
        String var11 = null;
        HttpGet var12 = new HttpGet(apiUrl);
        CloseableHttpResponse response = this.getHttpClient().execute(var12);
        HttpEntity entity = response.getEntity();
        if (entity != null)
        {
            InputStream instream = entity.getContent();
            var11 = IOUtils.toString(instream, "UTF-8");
        }

        return var11;
    }

    public String doPost(String apiUrl)
    {
        return this.doPost(apiUrl, (Map) (new HashMap()));
    }

    public String doPost(String apiUrl, Map<String, Object> params)
    {
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try
        {
            ArrayList e = new ArrayList(params.size());
            Iterator entity = params.entrySet().iterator();

            while (entity.hasNext())
            {
                Entry entry = (Entry) entity.next();
                BasicNameValuePair pair = new BasicNameValuePair((String) entry.getKey(), entry.getValue().toString());
                e.add(pair);
            }

            httpPost.setEntity(new UrlEncodedFormEntity(e, Charset.forName("UTF-8")));
            response = this.getHttpClient().execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            httpStr = EntityUtils.toString(entity1, "UTF-8");
        }
        catch (IOException var18)
        {
            this.log.error(var18.getMessage(), var18);
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    EntityUtils.consume(response.getEntity());
                }
                catch (IOException var17)
                {
                    this.log.error(var17.getMessage(), var17);
                }
            }

        }

        return httpStr;
    }

    public String doPost(String apiUrl, Object json) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return this.doRequestWithJSON(httpPost, json.toString(), _UTF_8);
    }

    public String doPostWithForm(String apiUrl, String form) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return this.doRequestWithForm(httpPost, form, _UTF_8);
    }

    public String doPostWithXML(String apiUrl, String xmlinfo) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return this.doRequestWithXML(httpPost, xmlinfo, _UTF_8);
    }

    public String doPut(String apiUrl, Object json) throws Exception
    {
        HttpPut httpPut = new HttpPut(apiUrl);
        return this.doRequestWithJSON(httpPut, json.toString(), _UTF_8);
    }

    private String doRequestWithForm(HttpEntityEnclosingRequestBase request, String form, String encoding) throws Exception
    {
        return this.doRequest(request, form, encoding, _CONTENT_TYPE_FORM);
    }

    private String doRequestWithJSON(HttpEntityEnclosingRequestBase request, String json, String encoding) throws Exception
    {
        return this.doRequest(request, json, encoding, _CONTENT_TYPE_JSON);
    }

    private String doRequestWithXML(HttpEntityEnclosingRequestBase request, String message, String encoding) throws Exception
    {
        return this.doRequest(request, message, encoding, _CONTENT_TYPE_XML);
    }

    private String doRequest(HttpEntityEnclosingRequestBase request, String message, String encoding, String contentType) throws Exception
    {
        this.log.debug("DO REQUEST: " + message);
        StringEntity stringEntity = new StringEntity(message, encoding);
        stringEntity.setContentEncoding(encoding);
        stringEntity.setContentType(contentType);
        request.setEntity(stringEntity);
        CloseableHttpResponse response = this.getHttpClient().execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, encoding);

        try
        {
            EntityUtils.consume(response.getEntity());
        }
        catch (IOException var10)
        {
            this.log.error(var10.getMessage(), var10);
        }

        return result;
    }

    public String doPostSSL(String apiUrl, Map<String, Object> params) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        ArrayList pairList = new ArrayList(params.size());
        Iterator response = params.entrySet().iterator();

        while (response.hasNext())
        {
            Entry statusCode = (Entry) response.next();
            BasicNameValuePair entity = new BasicNameValuePair((String) statusCode.getKey(), statusCode.getValue().toString());
            pairList.add(entity);
        }

        httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(_UTF_8)));
        CloseableHttpResponse response1 = this.getHttpClientSSL().execute(httpPost);
        int statusCode1 = response1.getStatusLine().getStatusCode();
        if (statusCode1 != 200)
        {
            return null;
        }
        else
        {
            HttpEntity entity1 = response1.getEntity();
            if (entity1 == null)
            {
                return null;
            }
            else
            {
                String httpStr = EntityUtils.toString(entity1, _UTF_8);

                try
                {
                    EntityUtils.consume(response1.getEntity());
                }
                catch (IOException var10)
                {
                    this.log.error(var10.getMessage(), var10);
                }

                return httpStr;
            }
        }
    }

    public String doPostSSL(String apiUrl, Object json) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return this.doRequestSSLWithJSON(httpPost, json.toString(), _UTF_8);
    }

    public String doPostSSLWithForm(String apiUrl, String form) throws Exception
    {
        return this.doPostSSLWithForm(apiUrl, form, _UTF_8);
    }

    public String doPostSSLWithForm(String apiUrl, String form, String encoding) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return this.doRequestSSLWithForm(httpPost, form, encoding);
    }

    public String doPostSSLWithXML(String apiUrl, String xmlinfo) throws Exception
    {
        return this.doPostSSLWithXML(apiUrl, xmlinfo, _UTF_8);
    }

    public String doPostSSLWithXML(String apiUrl, String xmlinfo, String encoding) throws Exception
    {
        HttpPost httpPost = new HttpPost(apiUrl);
        return this.doRequestSSLWithXML(httpPost, xmlinfo, encoding);
    }

    private String doRequestSSLWithJSON(HttpEntityEnclosingRequestBase request, String json, String encoding) throws Exception
    {
        return this.doRequestSSL(request, json, encoding, _CONTENT_TYPE_JSON);
    }

    private String doRequestSSLWithForm(HttpEntityEnclosingRequestBase request, String form, String encoding) throws Exception
    {
        return this.doRequestSSL(request, form, encoding, _CONTENT_TYPE_FORM);
    }

    private String doRequestSSLWithXML(HttpEntityEnclosingRequestBase request, String message, String encoding) throws Exception
    {
        return this.doRequestSSL(request, message, encoding, _CONTENT_TYPE_XML);
    }

    private String doRequestSSL(HttpEntityEnclosingRequestBase request, String message, String encoding, String contentType) throws Exception
    {
        this.log.debug("DO REQUEST WITH SSL: " + message);
        StringEntity stringEntity = new StringEntity(message, encoding);
        stringEntity.setContentEncoding(encoding);
        stringEntity.setContentType(contentType);
        request.setEntity(stringEntity);
        CloseableHttpResponse response = this.getHttpClientSSL().execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, encoding);

        try
        {
            EntityUtils.consume(response.getEntity());
        }
        catch (IOException var10)
        {
            this.log.error(var10.getMessage(), var10);
        }

        return result;
    }

    private ConnectionSocketFactory createConnSocketFactory()
    {
        return PlainConnectionSocketFactory.getSocketFactory();
    }

    private ConnectionSocketFactory createIgnoreSSLConnSocketFactory()
    {
        SSLConnectionSocketFactory sslsf = null;
        try
        {
            sslsf = new SSLConnectionSocketFactory(this.createIgnoreVerifySSL().getSocketFactory(), (String[]) null, (String[]) null, (x509Certificates, s) ->
            {
                return true;
            });
        }
        catch (Exception var3)
        {
            this.log.error(var3.getMessage(), var3);
        }

        return sslsf;
    }

    private ConnectionSocketFactory createCustSSLConnSocketFactory()
    {
        SSLConnectionSocketFactory sslsf = null;
        KeyStore keyStore;
        FileInputStream instream = null;// 加载本地的证书进行https加密传输
        try
        {
            char[] certPasswordArrary = getCertPassword().toCharArray();
            keyStore = KeyStore.getInstance("PKCS12");
            instream = new FileInputStream(new File(getCertPath()));// 加载本地的证书进行https加密传输
            keyStore.load(instream, certPasswordArrary);// 设置证书密码
            SSLContext sslcontext = org.apache.http.conn.ssl.SSLContexts.custom().
                    loadKeyMaterial(keyStore, certPasswordArrary).
                    build();
            // Allow TLSv1 protocol only
            sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            try
            {
                if (null != instream)
                {
                    instream.close();
                }
            }
            catch (IOException e)
            {
                log.error(e.getMessage(), e);
            }
        }
//        SSLConnectionSocketFactory sslsf = null;
//        try
//        {
//            sslsf = new SSLConnectionSocketFactory(this.createCustVerifySSL(_CERT_PATH, _CERT_PASSWORD));
//        }
//        catch (Exception var3)
//        {
//            this.log.error(var3.getMessage(), var3);
//        }
        return sslsf;
    }

    public SSLContext createIgnoreVerifySSL() throws Exception
    {
        return (new SSLContextBuilder()).loadTrustMaterial((KeyStore) null, (x509Certificates, s) ->
        {
            return true;
        }).build();
    }

    public SSLContext createCustVerifySSL(String keyStorePath, String keyStorepass)
    {
        SSLContext sc = null;
        FileInputStream instream = null;
        KeyStore trustStore;
        try
        {
            trustStore = KeyStore.getInstance("PKCS12");
            instream = new FileInputStream(new File(keyStorePath));
            trustStore.load(instream, keyStorepass.toCharArray());
            // 相信自己的CA和所有自签名的证书
            sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            try
            {
                if (null != instream)
                {
                    instream.close();
                }
            }
            catch (IOException e)
            {
                log.error(e.getMessage(), e);
            }
        }
        return sc;
    }
}