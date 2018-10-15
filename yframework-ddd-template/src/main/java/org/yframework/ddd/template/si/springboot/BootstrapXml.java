package org.yframework.ddd.template.si.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.yframework.ddd.template.si.springboot.utils.DefaultProfileUtil;

/**
 * Description: BootstrapXml.<br>
 * Date: 2018/9/29 下午5:39<br>
 * Author: ysj
 */
public class BootstrapXml extends SpringBootServletInitializer
{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        /**
         * set a default to use when no profile is configured.
         */
        DefaultProfileUtil.addDefaultProfile(application.application());
        return application.sources(Bootstrap.class);
    }
}
