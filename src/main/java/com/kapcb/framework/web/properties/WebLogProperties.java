package com.kapcb.framework.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <a>Title: WebLogProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description: WebLogProperties <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/9 21:38
 */
@Data
@ConfigurationProperties(prefix = "kapcb.web.log", ignoreInvalidFields = true)
public class WebLogProperties {

    private boolean enable = false;

    private boolean methodName = false;

    private boolean className = false;

    private boolean requestUri = false;

    private boolean requestUrl = false;

    private boolean applicationName = false;

    private boolean requestTime = false;

    private boolean requestCostTime = false;

    private boolean returnValue = false;

    private boolean exceptionMessage = false;

}
