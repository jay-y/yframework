package com.cust.system.domain;

import org.yframework.mybatis.auditing.domain.AbstractAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author zk
 */
@Entity
@Table(name = "sys_file_info")
public class FileInfo extends AbstractAuditingEntity<String>
{
    private static final long serialVersionUID = 1L;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    @Column(name = "md5")
    private String md5;

    public FileInfo()
    {
    }

    public FileInfo(String id)
    {
        super(id);
    }


    public String getFileName()
    {
        return fileName;
    }

    public FileInfo fileName(String fileName)
    {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public FileInfo storeName(String storeName)
    {
        this.storeName = storeName;
        return this;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getPath()
    {
        return path;
    }

    public FileInfo path(String path)
    {
        this.path = path;
        return this;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getUrl()
    {
        return url;
    }

    public FileInfo url(String url)
    {
        this.url = url;
        return this;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getMd5()
    {
        return md5;
    }

    public FileInfo md5(String md5)
    {
        this.md5 = md5;
        return this;
    }

    public void setMd5(String md5)
    {
        this.md5 = md5;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        FileInfo fileInfo = (FileInfo) o;
        if (fileInfo.getId() == null || getId() == null)
        {
            return false;
        }
        return Objects.equals(getId(), fileInfo.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString()
    {
        return "FileInfo{" + "fileName='" + fileName + '\'' + ", storeName='" + storeName + '\'' + ", path='" + path + '\'' + ", url='" + url + '\'' + ", md5='" + md5 + '\'' + '}' + super.toString();
    }
}
