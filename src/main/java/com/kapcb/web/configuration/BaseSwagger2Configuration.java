package com.kapcb.web.configuration;

import com.kapcb.web.properties.Swagger2Properties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <a>Title: BaseSwagger2Configuration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: BaseSwagger2Configuration <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 13:12
 */
public abstract class BaseSwagger2Configuration {

    @Bean
    public Docket createRestApi() {
        Swagger2Properties swagger2Properties = swagger2Properties();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swagger2Properties))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
        if (swagger2Properties.isEnableSecurity()) {
            docket.securitySchemes(securitySchemes()).securityContexts(securityContexts());
        }
        return docket;
    }

    private List<ApiKey> securitySchemes() {
        // 设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/*/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex)).build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }

    private ApiInfo apiInfo(Swagger2Properties swagger2Properties) {
        return new ApiInfoBuilder()
                .title(swagger2Properties.getTitle())
                .description(swagger2Properties.getDescription())
                .contact(new Contact(swagger2Properties.getContactName(), swagger2Properties.getContactUrl(), swagger2Properties.getContactEmail()))
                .version(swagger2Properties.getVersion()).build();
    }

    /**
     * 自定义swagger配置
     *
     * @return Swagger2Properties
     */
    public abstract Swagger2Properties swagger2Properties();

}
