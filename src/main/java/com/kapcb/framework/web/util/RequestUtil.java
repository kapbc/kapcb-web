package com.kapcb.framework.web.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kapcb.framework.common.constants.enums.StringPool;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * <a>Title: RequestUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RequestUtil <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 15:25
 */
@Slf4j
@UtilityClass
public class RequestUtil {

    @NonNull
    public static boolean ajaxRequest(HttpServletRequest request) {
        String xRequest = request.getHeader(StringPool.REQUEST_HEAD_X_REQUESTED_WITH.value());
        log.info("the request head is {}", xRequest);
        return StringPool.REQUEST_XML_HTTP_REQUEST.value().equals(xRequest);
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个, 而是一串IP值, 这种情况要获取ip, 只需要取X-Forwarded-For中第一个非unknown的有效IP字符串
     * 如：X-Forwarded-For: 192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.110就是ip地址
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader(StringPool.HTTP_REQUEST_X_FOR_WARDED_FOR.value());
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(StringPool.HTTP_REQUEST_UN_KNOWN.value(), ip)) {
            ip = request.getHeader(StringPool.HTTP_REQUEST_PROXY_CLIENT_IP.value());
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(StringPool.HTTP_REQUEST_UN_KNOWN.value(), ip)) {
            ip = request.getHeader(StringPool.HTTP_REQUEST_WL_PROXY_CLIENT_IP.value());
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(StringPool.HTTP_REQUEST_UN_KNOWN.value(), ip)) {
            ip = request.getHeader(StringPool.HTTP_REQUEST_HTTP_CLIENT_IP.value());
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(StringPool.HTTP_REQUEST_UN_KNOWN.value(), ip)) {
            ip = request.getHeader(StringPool.HTTP_REQUEST_HTTP_X_FORWARDED_FOR.value());
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase(StringPool.HTTP_REQUEST_UN_KNOWN.value(), ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getCity(String ipAddress) {
        Assert.hasLength(ipAddress, "ip address can not be null or empty");
        String requestUrl = StrUtil.format(StringPool.REQUEST_CITY_HTTP_URL.value(), ipAddress);
        String s = HttpUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(s);
        return (String) jsonObject.get("addr");
    }

    public static String getBrowser(HttpServletRequest httpServletRequest) {
        Assert.notNull(httpServletRequest, "request can not be null");
        UserAgent userAgent = UserAgentUtil.parse(httpServletRequest.getHeader(StringPool.HTTP_REQUEST_USER_AGENT.value()));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    public static String getAuthorization(HttpServletRequest httpServletRequest) {
        Assert.notNull(httpServletRequest, "request can not be null");
        return httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
    }

    public static String getAccessToken(String authorization) {
        Assert.hasLength(authorization, "authorization can not be null");
        String accessToken = null;
        if (authorization.startsWith(StringPool.AUTHORIZATION_BEARER.value())) {
            accessToken = authorization.substring(StringPool.AUTHORIZATION_BEARER.value().length());
        }
        return accessToken;
    }

    public static String getAccessToken(HttpServletRequest httpServletRequest) {
        return getAccessToken(getAuthorization(httpServletRequest));
    }

    public static void main(String[] args) {
        String city = getCity("122.10.128.255");
        System.out.println("city = " + city);
    }

}
