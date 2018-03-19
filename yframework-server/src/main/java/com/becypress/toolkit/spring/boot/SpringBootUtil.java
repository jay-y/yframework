package com.becypress.toolkit.spring.boot;

import com.becypress.toolkit.spring.boot.utils.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description: SpringBootUtil.<br>
 * Date: 2017/10/2 14:53<br>
 * Author: ysj
 */
public enum SpringBootUtil
{
    INSTANCE;

    /**
     * used to run the application.
     *
     * @param cls  the application class
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public void startup(Class<?> cls, String[] args) throws UnknownHostException
    {
        Logger log = LoggerFactory.getLogger(cls);
        SpringApplication app = new SpringApplication(cls);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null)
        {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        //XXX应用运行中，激活的链接为：
                        "Application '{}' is running! Access URLs:\n\t" +
                        //本地访问：
                        "Local: \t\t{}://localhost:{}\n\t" +
                        //外部访问：
                        "External: \t{}://{}:{}\n\t" +
                        //激活配置：
                        "Profile(s): \t{}\n----------------------------------------------------------",
                //应用名
                env.getProperty("spring.application.name"),
                //协议， 端口
                protocol, env.getProperty("server.port"),
                //协议， 地址，端口
                protocol, InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
                //配置名
                env.getActiveProfiles());
//        String configServerStatus = env.getProperty("configserver.status");
//        log.info("\n----------------------------------------------------------\n\t" +
//                "Config Server: \t{}\n----------------------------------------------------------",
//            configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
    }
}
