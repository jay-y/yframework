package com.cust.system.service.dto;

import com.cust.system.domain.FileInfo;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;

/**
 * @author zk
 */
public class FileInfoDTO implements AuditingEntityDTO<String>
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String fileName;

    private String storeName;

    private String path;

    private String url;

    private String md5;

    private boolean activated = true;

    public FileInfoDTO()
    {
    }

    public FileInfoDTO(String id)
    {
        this.id = id;
    }

    public FileInfoDTO(String id, String fileName, String storeName, String path, String url, String md5, boolean activated)
    {
        this.id = id;
        this.fileName = fileName;
        this.storeName = storeName;
        this.path = path;
        this.url = url;
        this.md5 = md5;
        this.activated = activated;
    }

    public FileInfoDTO(FileInfo fileInfo)
    {
        this(fileInfo.getId(), fileInfo.getFileName(), fileInfo.getStoreName(), fileInfo.getPath(), fileInfo.getUrl(), fileInfo.getMd5(), fileInfo.isActivated());
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getMd5()
    {
        return md5;
    }

    public void setMd5(String md5)
    {
        this.md5 = md5;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    @Override
    public String toString()
    {
        return "FileInfoDTO{" + "id='" + id + '\'' + ", fileName='" + fileName + '\'' + ", storeName='" + storeName + '\'' + ", path='" + path + '\'' + ", url='" + url + '\'' + ", md5='" + md5 + '\'' + ", activated=" + activated + '}';
    }
}
