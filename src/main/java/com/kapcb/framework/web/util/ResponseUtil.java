package com.kapcb.framework.web.util;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kapcb.framework.common.constants.enums.LongPool;
import com.kapcb.framework.common.constants.enums.StringPool;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <a>Title: ResponseUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ResponseUtil <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 15:25
 */
@Slf4j
@UtilityClass
public class ResponseUtil {

    @NonNull
    public static void setHeader(@NonNull HttpServletResponse httpServletResponse, @Nullable String type) {
        // default png
        if (StringUtils.equalsAnyIgnoreCase(type, StringPool.IMAGE_SUFFIX_GIF.value())) {
            httpServletResponse.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            httpServletResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
        }

        httpServletResponse.setHeader(HttpHeaders.PRAGMA, StringPool.RESPONSE_HEAD_NO_CACHE.value());
        httpServletResponse.setHeader(HttpHeaders.CACHE_CONTROL, StringPool.RESPONSE_HEAD_NO_CACHE.value());
        httpServletResponse.setDateHeader(HttpHeaders.EXPIRES, LongPool.ZERO.value());
    }

    /**
     * 设置响应
     *
     * @param httpServletResponse HttpServletResponse
     * @param contentType         String
     * @param status              Integer
     * @param value               响应内容
     * @param <T>                 T
     * @return boolean
     */
    public static <T> boolean setUpResponse(HttpServletResponse httpServletResponse, String contentType, Integer status, T value) throws IOException {
        httpServletResponse.setContentType(contentType);
        httpServletResponse.setStatus(status);
        httpServletResponse.getOutputStream().write(JSONObject.toJSONString(value).getBytes(StandardCharsets.UTF_8));
        return true;
    }

    /**
     * 设置成功响应
     *
     * @param httpServletResponse HttpServletResponse
     * @param value               响应内容
     * @param <T>                 T
     * @return boolean
     * @throws IOException IOException
     */
    public static <T> boolean setUpSuccessResponse(HttpServletResponse httpServletResponse, T value) throws IOException {
        return setUpResponse(httpServletResponse, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_OK, value);
    }

    /**
     * 设置失败响应
     *
     * @param httpServletResponse HttpServletResponse
     * @param value               响应内容
     * @param <T>                 T
     * @return boolean
     * @throws IOException IOException
     */
    public static <T> boolean setUpFailureResponse(HttpServletResponse httpServletResponse, T value) throws IOException {
        return setUpResponse(httpServletResponse, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, value);
    }

    public static <T> void setUpResponse(HttpServletResponse response, String contentType, T data) throws IOException {
        response.setContentType(contentType);
        response.getOutputStream().write(JSON.toJSONBytes(data));
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    public static <T> void setUpJSONResponse(HttpServletResponse response, T data) throws IOException {
        setUpResponse(response, ContentType.JSON.getValue(), data);
    }
}
