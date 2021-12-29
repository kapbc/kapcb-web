package com.kapcb.framework.web.api;

import cn.hutool.core.util.NumberUtil;
import com.kapcb.framework.web.annotation.api.ApiVersion;
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
     * @param apiVersionRequestCondition
     * @return
     */
    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition apiVersionRequestCondition) {
        return new ApiVersionRequestCondition(apiVersion, apiVersionProperties, versionPlaceholderIndex);
    }

    /**
     * 校验请求URI中是否包含版本号, 包含则进行正则匹配
     *
     * @param httpServletRequest HttpServletRequest
     * @return ApiVersionRequestCondition
     *
     * https://blog.csdn.net/weixin_39255905/article/details/110391515?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164079420716780269862621%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164079420716780269862621&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-1-110391515.pc_search_result_control_group&utm_term=RequestCondition%E5%AE%9E%E7%8E%B0%E7%89%88%E6%9C%AC%E5%8F%B7&spm=1018.2226.3001.4187
     */
    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        // 校验请求url中是否包含版本信息
        String requestURI = httpServletRequest.getRequestURI();
        String[] versionPath = requestURI.split("/");
        Double version = Double.valueOf(versionPath[versionPlaceholderIndex].substring(1));

        // pathVersion的值大于等于apiVersionValue皆可匹配，除非ApiVersion的deprecated值已被标注为true
        double value = this.apiVersion.value();

        return null;
    }

    /**
     * 重写比较方式
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
