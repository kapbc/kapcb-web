package com.kapcb.framework.web.configuration;

import com.kapcb.framework.web.api.ApiVersionRequestMappingHandlerMapping;
import com.kapcb.framework.web.properties.ApiVersionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;

/**
 * <a>Title: WebMvcRegistrations </a>
 * <a>Author: Kapcb <a>
 * <a>Description: WebMvcRegistrations <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/30 22:26
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ApiVersionProperties.class)
public class WebMvcRegistrationsConfiguration implements WebMvcRegistrations {

    @Resource
    private ApiVersionProperties apiVersionProperties;

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        if (!apiVersionProperties.getEnable()) {
            return WebMvcRegistrations.super.getRequestMappingHandlerMapping();
        }
        log.info("[ begin to create restful api version control ]");
        return new ApiVersionRequestMappingHandlerMapping(apiVersionProperties);
    }

}
