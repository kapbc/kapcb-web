package com.kapcb.framework.web.api;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.kapcb.framework.web.annotation.api.ApiVersion;
import com.kapcb.framework.web.properties.ApiVersionProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * <a>Title: ApiVersionRequestMappingHandlerMapping </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ApiVersionRequestMappingHandlerMapping <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/30 22:09
 * @since 1.0
 */
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private ApiVersionProperties apiVersionProperties;

    public ApiVersionRequestMappingHandlerMapping(ApiVersionProperties apiVersionProperties) {
        this.apiVersionProperties = apiVersionProperties;
    }

    /**
     * 作用于类上的注解
     *
     * @param handlerType handlerType
     * @return RequestCondition<?>
     */
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        // 扫描类或接口上的 {@link ApiVersion} 注解
        ApiVersion annotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createRequestCondition(handlerType, annotation);
    }

    /**
     * 作用于方法上的注解
     *
     * @param method Method
     * @return RequestCondition<?>
     */
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion annotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createRequestCondition(method.getDeclaringClass(), annotation);
    }

    private RequestCondition<ApiVersionRequestCondition> createRequestCondition(Class<?> handlerType, ApiVersion apiVersion) {
        // 确认是否进行版本控制 @ApiVersion 注解不为空
        if (apiVersion == null) {
            return null;
        }

        // 确认是否进行版本控制 @RequestMapping 注解包含版本占位符
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
        if (requestMapping == null) {
            return null;
        }

        String[] requestMappingPath = requestMapping.value();
        if (StrUtil.isAllEmpty(requestMappingPath) || !requestMappingPath[0].contains(apiVersionProperties.getVersionPlaceholder())) {
            return null;
        }

        // 解析版本占位符索引位置
        String[] versionPlaceholderValues = StrUtil.split(requestMappingPath[0], "/");
        Integer index = null;
        for (int i = 0; i < versionPlaceholderValues.length; i++) {
            if (StrUtil.equals(versionPlaceholderValues[i], apiVersionProperties.getVersionPlaceholder())) {
                index = i;
                break;
            }
        }

        // 确认是否进行版本控制-占位符索引确认
        if (index == null) {
            return null;
        }

        double value = apiVersion.value();
        Assert.isTrue(value >= 1, "api version must be greater than or equal to 1");

        // 创建 RequestCondition
        return new ApiVersionRequestCondition(apiVersion, apiVersionProperties, index);
    }

}
