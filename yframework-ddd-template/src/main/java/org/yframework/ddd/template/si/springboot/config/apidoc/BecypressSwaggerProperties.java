package org.yframework.ddd.template.si.springboot.config.apidoc;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: SwaggerProperties.<br>
 * Date: 2017/9/20 14:58<br>
 * Author: ysj
 */
@ConfigurationProperties(prefix = "becypress-swagger", ignoreUnknownFields = false)
public class BecypressSwaggerProperties
{
    private String title = "Application API";
    private String description = "API documentation";
    private String version = "0.0.1";
    private String termsOfServiceUrl;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String license;
    private String licenseUrl;
    private String defaultIncludePattern = "/api/.*";

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getVersion()
    {
        return this.version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getTermsOfServiceUrl()
    {
        return this.termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl)
    {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getContactName()
    {
        return this.contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactUrl()
    {
        return this.contactUrl;
    }

    public void setContactUrl(String contactUrl)
    {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail()
    {
        return this.contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public String getLicense()
    {
        return this.license;
    }

    public void setLicense(String license)
    {
        this.license = license;
    }

    public String getLicenseUrl()
    {
        return this.licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl)
    {
        this.licenseUrl = licenseUrl;
    }

    public String getDefaultIncludePattern()
    {
        return this.defaultIncludePattern;
    }

    public void setDefaultIncludePattern(String defaultIncludePattern)
    {
        this.defaultIncludePattern = defaultIncludePattern;
    }
}
