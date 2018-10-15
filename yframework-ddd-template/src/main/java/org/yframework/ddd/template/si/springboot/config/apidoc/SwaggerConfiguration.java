package org.yframework.ddd.template.si.springboot.config.apidoc;

import com.fasterxml.classmate.TypeResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.ByteBuffer;

@Configuration
@ConditionalOnClass({ApiInfo.class, BeanValidatorPluginsConfiguration.class, BecypressSwaggerProperties.class})
@EnableConfigurationProperties({BecypressSwaggerProperties.class})
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
@Profile({"apidoc"})
public class SwaggerConfiguration
{
    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    public SwaggerConfiguration()
    {
    }

    @Bean
    public Docket swaggerSpringfoxDocket(BecypressSwaggerProperties properties)
    {
        this.log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(properties.getContactName(), properties.getContactUrl(), properties.getContactEmail());
        ApiInfo apiInfo = new ApiInfo(properties.getTitle(), properties.getDescription(), properties.getVersion(), properties.getTermsOfServiceUrl(), contact, properties.getLicense(), properties.getLicenseUrl());
        Docket docket = (new Docket(DocumentationType.SWAGGER_2)).
                apiInfo(apiInfo).
                forCodeGeneration(true).
                directModelSubstitute(ByteBuffer.class, String.class).
                genericModelSubstitutes(new Class[]{ResponseEntity.class}).
                select().
                paths(PathSelectors.regex(properties.getDefaultIncludePattern())).
                build();
        watch.stop();
        this.log.debug("Started Swagger in {} ms", Long.valueOf(watch.getTotalTimeMillis()));
        return docket;
    }

    @Bean
    PageableParameterBuilderPlugin pageableParameterBuilderPlugin(TypeNameExtractor nameExtractor, TypeResolver resolver)
    {
        return new PageableParameterBuilderPlugin(nameExtractor, resolver);
    }
}
