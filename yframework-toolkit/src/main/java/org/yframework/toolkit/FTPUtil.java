package org.yframework.toolkit;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: FTPUtil.<br>
 * Date: 2017/8/31 11:13<br>
 * Author: ysj
 */
public enum FTPUtil
{
    INSTANCE;
    private static final int _BUFFER_SIZE = 2 * 1024;
    private static final int _DATA_TIMEOUT = 30 * 1000;
    private final Logger log = LoggerFactory.getLogger(FTPUtil.class);
    private final Map<String, FTPClient> clients = new HashMap<>();
    private final String separator = System.getProperty("file.separator");

    /**
     * 获取FTPClient对象
     *
     * @param host FTP主机服务器
     * @param prot FTP端口,默认为21
     * @param uid  FTP登录用户ID
     * @param pwd  登录密码
     * @return
     */
    public FTPClient getFTPClient(String host, int prot, String uid, String pwd)
    {
        String url = formatUrl(host, prot);
        String key = formatKey(url, uid, pwd);
        FTPClient client = clients.get(key);
        if (null == client)
        {
            try
            {
                client = new FTPClient();
                client.connect(host, prot);// 连接FTP服务器
                client.login(uid, pwd);// 登陆FTP服务器
                client.setFileType(FTP.BINARY_FILE_TYPE);
                client.enterLocalPassiveMode();
                client.setBufferSize(_BUFFER_SIZE);
                client.setDataTimeout(_DATA_TIMEOUT);
                if (!FTPReply.isPositiveCompletion(client.getReplyCode()))
                {
                    log.error("URL:{},客户端创建失败,异常:用户名或密码错误", url);
                    client.disconnect();
                    return null;
                }
                else
                {
                    clients.put(key, client);
                    log.info("URL:{},客户端创建成功", url);
                    return client;
                }
            }
            catch (SocketException e)
            {
                log.error("URL:{},连接错误,异常:{}", url, e.getMessage());
            }
            catch (IOException e)
            {
                log.error("URL:{},IO错误,异常:{}", url, e.getMessage());
            }
            return client;
        }
        else if (client.isConnected())
        {
            return client;
        }
        else
        {
            clients.remove(key);
            return getFTPClient(host, prot, uid, pwd);
        }
    }

    /**
     * 上传
     *
     * @param file
     * @param client
     * @throws Exception
     */
    public void upload(FTPClient client, File file) throws IOException
    {
        if (file.isDirectory())
        {
            client.makeDirectory(file.getName());
            client.changeWorkingDirectory(file.getName());
            String[] files = file.list();
            if (null != files && files.length > 0)
            {
                for (String fileName : files)
                {
                    File tmpFile = new File(file.getPath() + separator + fileName);
                    if (tmpFile.isDirectory())
                    {
                        this.upload(client, tmpFile);
                        client.changeToParentDirectory();
                    }
                    else
                    {
                        this.uploadFile(client, tmpFile, tmpFile.getName());
                    }
                }
            }
            else
            {
                log.warn("{}为空,无法上传", file.getName());
            }
        }
        else
        {
            File tmpFile = new File(file.getPath());
            this.uploadFile(client, tmpFile, tmpFile.getName());
        }
    }

    public void closeFTPClient(FTPClient client)
    {
        String url = formatUrl(client.getRemoteAddress().toString(), client.getRemotePort());
        if (client.isConnected())
        {
            try
            {
                client.logout();
                client.disconnect();
                log.info("URL:{},客户端关闭成功", url);
            }
            catch (IOException e)
            {
                log.error("URL:{},客户端关闭失败,异常:{}", url, e.getMessage());
            }
        }
    }

    public Map<String, FTPClient> getAll()
    {
        return clients;
    }

    public void closeAll() throws IOException
    {
        clients.forEach((key, client) ->
        {
            this.closeFTPClient(client);
            clients.remove(key);
        });
    }

    /**
     * 上传文件
     *
     * @param client
     * @param file
     * @param remoteName
     */
    private void uploadFile(FTPClient client, File file, String remoteName)
    {
        try
        {
            //传文件
            String localPathName = file.getPath();
            File f = new File(localPathName);
            InputStream is = new FileInputStream(f);
            client.storeFile(remoteName, is);
            is.close();
            log.info("{}上传成功", file.getName());
        }
        catch (Exception e)
        {
            log.error("{}上传有误,异常:{}", file.getName(), e.getMessage());
        }

    }

    private String formatUrl(String host, int prot)
    {
        return new StringBuilder("ftp://").
                append(host).
                append(":").
                append(prot).
                toString();
    }

    private String formatKey(String url, String uid, String pwd)
    {
        return new StringBuilder(url).
                append("_").
                append(uid).
                append("_").
                append(pwd).
                append("_").
                toString();
    }
}
