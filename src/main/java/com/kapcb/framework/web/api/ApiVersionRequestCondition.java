package com.kapcb.framework.web.api;

import cn.hutool.core.util.NumberUtil;
import com.kapcb.framework.web.annotation.api.ApiVersion;
import com.kapcb.framework.web.exception.BusinessException;
import com.kapcb.framework.web.properties.ApiVersionProperties;
import lombok.Data;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * <a>Title: ApiVersionRequestCondition </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ApiVersionRequestCondition <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/30 0:03
 * @since 1.0
 */
@Data
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    private ApiVersion apiVersion;

    private ApiVersionProperties apiVersionProperties;

    private Integer versionPlaceholderIndex;

    public ApiVersionRequestCondition(ApiVersion apiVersion, ApiVersionProperties apiVersionProperties, Integer versionPlaceholderIndex) {
        this.apiVersion = apiVersion;
        this.apiVersionProperties = apiVersionProperties;
        this.versionPlaceholderIndex = versionPlaceholderIndex;
    }

    /**
     * 创建新的实例
     *
     * @param apiVersionRequestCondition ApiVersionRequestCondition
     * @return ApiVersionRequestCondition
     */
    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition apiVersionRequestCondition) {
        return new ApiVersionRequestCondition(apiVersionRequestCondition.getApiVersion(), apiVersionRequestCondition.getApiVersionProperties(), apiVersionRequestCondition.getVersionPlaceholderIndex());
    }

    /**
     * 校验请求URI中是否包含版本号, 包含则进行正则匹配
     *
     * @param httpServletRequest HttpServletRequest
     * @return ApiVersionRequestCondition
     */
    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        // 校验请求url中是否包含版本信息
        String requestURI = httpServletRequest.getRequestURI();
        String[] versionPath = requestURI.split("/");
        Double pathVersion = Double.valueOf(versionPath[versionPlaceholderIndex].substring(1));

        // pathVersion的值大于等于annotationVersionValue皆可匹配，除非ApiVersion的deprecated值已被标注为true
        double annotationVersionValue = this.apiVersion.value();
        if (pathVersion > annotationVersionValue) {
            double minimumVersion = apiVersionProperties.getMinimumVersion();
            if ((this.getApiVersion().deprecated() || minimumVersion > pathVersion) && NumberUtil.equals(pathVersion, annotationVersionValue)) {
                throw new BusinessException("弃用版本接口" + requestURI);
            } else if (this.getApiVersion().deprecated()) {
                // 继续匹配
                return null;
            }
            // 匹配成功
            return this;
        }
        // 继续匹配
        return null;
    }

    /**
     * 重写比较方式
     * 当出现多个符合匹配条件的ApiVersionCondition，优先匹配版本号较大的
     *
     * @param apiVersionRequestCondition ApiVersionRequestCondition
     * @param httpServletRequest         HttpServletRequest
     * @return int
     */
    @Override
    public int compareTo(ApiVersionRequestCondition apiVersionRequestCondition, HttpServletRequest httpServletRequest) {
        return NumberUtil.compare(apiVersionRequestCondition.apiVersion.value(), apiVersion.value());
    }

}
