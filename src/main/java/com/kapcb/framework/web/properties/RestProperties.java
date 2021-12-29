package com.kapcb.framework.web.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <a>Title: RestProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RestProperties <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/29 22:30
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "kapcb.rest")
public class RestProperties {

    private Boolean enable = false;

    private Integer connectTimeOut = 10000;

    private Integer readTimeOut = 10000;

    private Boolean enableProxy = false;

    private String proxyHostName;

    private int port;

}
