package com.kapcb.framework.web.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <a>Title: RequestHolder </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RequestHolder <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/19 23:11
 */
@UtilityClass
public class RequestHolder {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

}
